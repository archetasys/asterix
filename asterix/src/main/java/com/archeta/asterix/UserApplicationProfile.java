/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;
import static com.archeta.asterix.ASTERIXParser.*;

final class UserApplicationProfile {
    private static final int NULL_INDEX = -1;
    private static final int NULL_NUM_OCTETS = -1;
    private static final int RECORD_MAX_NUM_OCTETS = 0xffff;
    private static final Comparator<DataItem> DATA_ITEM_NUMBER_COMPARATOR = Comparator.comparingInt(DataItem::getNumber);
    private static final String LF = System.lineSeparator();
    private static final String FMT_UAP_ITEM = "%2s %-60s %c %s";

    static long sp(final long categoryId, final int fspecMaxNumOctets, final int bit) {
        return bit(categoryId, fspecMaxNumOctets, bit, false);
    }

    static long fx(final long categoryId, final int fspecMaxNumOctets, final int bit) {
        return bit(categoryId, fspecMaxNumOctets, bit, true);
    }

    private static long bit(final long categoryId, final int fspecMaxNumOctets, final int bit, final boolean fx) {
        // Data Item identifier using reserved number DATA_ITEM_UAP_FX or DATA_ITEM_UAP_SP with DATA_FORMAT_EMPTY.
        final long dataItemId = dataItemId(categoryId, fx ? DATA_ITEM_UAP_FX : DATA_ITEM_UAP_SP, DATA_FORMAT_EMPTY);
        // Subfield identifier A using number of octets in UAP FSPEC as the bit presence with DATA_FORMAT_EMPTY.
        final long subAId = subAId(dataItemId, fspecMaxNumOctets, DATA_FORMAT_EMPTY);
        // Subfield identifier B using 0 as the bit presence with DATA_FORMAT_EMPTY.
        final long subBId = subBId(subAId, 0, DATA_FORMAT_EMPTY);
        // The id is using bits field identifier with bit position in UAP and bits field encoding FX/SPARE.
        return bitsFieldId(subBId, bit, fx ? BITS_FIELD_ENCODING_FX : BITS_FIELD_ENCODING_SPARE);
    }

    private static ImmutableLong2ObjectHashMap<BitsField> createBitsFields(final List<BitsFieldList> bfs, final long categoryId) {
        final ImmutableLong2ObjectHashMap.Builder<BitsField> b = new ImmutableLong2ObjectHashMap.Builder<>();
        final int len = bfs.size();
        BitsFieldList bfl;
        BitsField bf;
        int sz;
        for (int i = 0; i < len; i++) {
            bfl = bfs.get(i);
            sz = bfl.size();
            for (int j = 0; j < sz; j++) {
                bf = bfl.get(j);
                if (b.put(bf.field.id, bf) != null) {
                    throw new ASTERIXFormatException(Fmt.sprintf("%s duplicate bits field %s",
                            getCategoryIdString(categoryId),
                            getBitsFieldIdString(bf.field.id, bf.field.name)));
                }
            }
        }

        return b.build();
    }

    /**
     * The ASTERIX Category identifier.
     */
    final long categoryId;

    /**
     * Map from Data Item Numbers to index in the FSPEC.
     */
    private final int[] dataItemNumbersToFSPECIndex;

    /**
     * Data Items array with index matched the FSPEC.
     */
    private final DataItem[] fspecDataItems;

    /**
     * Map from Data Item Numbers to Data Item.
     */
    private final DataItem[] dataItemsByNumber;

    /**
     * Data Items array sorted by number.
     */
    private final DataItem[] dataItems;

    /**
     * Maximum number of octets of the FSPEC.
     */
    private final int fspecMaxNumOctets;

    /**
     * The length of the FSPEC.
     */
    private final int fspecLength;

    /**
     * Cache number of octets of Data Items with data format is FIXED.
     */
    private final int[] fixedDataItemNumOctets;

    /**
     * Map from bits field identifier to BitsField.
     */
    private final ImmutableLong2ObjectHashMap<BitsField> bitsFieldsById;

    UserApplicationProfile(final long categoryId, final DataItem... items) {
        // Check the number of Data Items must be multiple of 8.
        final int fspecLength = items.length;
        if (((fspecLength - 1) & 0x7) != 7) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s number of data items must be multiple of 8",
                    getCategoryIdString(categoryId)));
        }

        // Check the number of Data Items not exceed maximum number of FSPEC bits which is 64.
        if (fspecLength < DATA_ITEMS_MIN_LENGTH || fspecLength >= DATA_ITEMS_MAX_LENGTH) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s number of Data Items must be in range [%s,%s], nitems=%s",
                    getCategoryIdString(categoryId), DATA_ITEMS_MIN_LENGTH, DATA_ITEMS_MAX_LENGTH, fspecLength));
        }

        this.fspecLength = fspecLength;
        fspecMaxNumOctets = fspecLength >> 3;
        dataItemNumbersToFSPECIndex = new int[NUM_DATA_ITEM_NUMBERS];
        dataItemsByNumber = new DataItem[NUM_DATA_ITEM_NUMBERS];
        fixedDataItemNumOctets = new int[fspecLength];
        Arrays.fill(dataItemNumbersToFSPECIndex, NULL_INDEX);
        Arrays.fill(fixedDataItemNumOctets, NULL_NUM_OCTETS);

        final ImmutableLong2ObjectHashMap.Builder<DataItem> uniqueItems = new ImmutableLong2ObjectHashMap.Builder<>();
        final DataItem[] dataItems = new DataItem[fspecLength];
        final List<BitsFieldList> bfs = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < fspecLength; i++) {
            final DataItem item = items[i];
            final long itemId = item.getId();
            final int num = item.getNumber();

            // Check FX bit at the LSB in an octet.
            if ((i & 7) == 7 && num != DATA_ITEM_UAP_FX) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s data item at %s must be FX not %s",
                        getCategoryIdString(categoryId), i, getDataItemIdString(itemId)));
            }

            final int fmt = getDataItemFormat(itemId);
            if (num == DATA_ITEM_UAP_FX) {
                final long expectedId = fx(categoryId, fspecMaxNumOctets, fspecLength - i);
                if (expectedId != itemId) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s invalid id %s for FX bit at %s in UAP, must be %s",
                            getCategoryIdString(categoryId),
                            getBitsFieldIdString(itemId),
                            i,
                            getBitsFieldIdString(expectedId)));
                }
            } else if (num == DATA_ITEM_UAP_SP) {
                final long expectedId = sp(categoryId, fspecMaxNumOctets, fspecLength - i);
                if (expectedId != itemId) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s invalid id %s for Spare bit at %s in UAP, must be %s",
                            getCategoryIdString(categoryId),
                            getBitsFieldIdString(itemId),
                            i,
                            getBitsFieldIdString(expectedId)));
                }
            } else {
                // Check for duplicate Data Item number, FX and SPARE are excluded.
                if (uniqueItems.put(num, item) != null) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s duplicate data item number %s at %s",
                            getCategoryIdString(categoryId), num, i));
                }

                // Cache the length of Data Items with format FIXED.
                if (fmt == DATA_FORMAT_FIXED) {
                    final int numOctets = item.format.getNumOctets();
                    if (numOctets < 1) {
                        throw new ASTERIXFormatException(Fmt.sprintf(
                                "%s data item format is fixed at %s must have number of octets > 0, numOctets=%s",
                                getDataItemIdString(itemId), i, numOctets));
                    }

                    fixedDataItemNumOctets[i] = numOctets;
                }

                dataItemNumbersToFSPECIndex[num] = i;
                dataItemsByNumber[num] = item;
                dataItems[j++] = item;
                item.format.addBitsFieldsTo(bfs);
            }
        }

        fspecDataItems = new DataItem[fspecLength];
        System.arraycopy(items, 0, fspecDataItems, 0, fspecLength);
        bitsFieldsById = createBitsFields(bfs, categoryId);
        this.dataItems = new DataItem[j];
        System.arraycopy(dataItems, 0, this.dataItems, 0, j);
        Arrays.sort(this.dataItems, DATA_ITEM_NUMBER_COMPARATOR);
        this.categoryId = categoryId;
    }

    DataItem findDataItemByNumber(final int number) {
        return dataItemsByNumber[number];
    }

    int getFSPECLength() {
        return fspecLength;
    }

    DataItem getDataItemByFSPECIndex(final int fspecIndex) {
        return fspecDataItems[fspecIndex];
    }

    BitsField findBitsField(final long bitsFieldId) {
        return bitsFieldsById.get(bitsFieldId);
    }

    int findDataItemFSPECIndex(final long dataItemId) {
        if (getCategoryId(dataItemId) != categoryId) {
            return -1;
        }

        return dataItemNumbersToFSPECIndex[getDataItemNumber(dataItemId)];
    }

    void forEachDataField(final DataFieldConsumer consumer) {
        for (int i = 0, n = dataItems.length; i < n; i++) {
            final DataItem item = dataItems[i];
            item.format.forEachDataField(item, null, null, consumer);
        }
    }

    int findDataFieldOffset(final long bitsFieldId, final int repIndex, final Record record) {
        if (getCategoryId(bitsFieldId) != categoryId) {
            return -1;
        }

        if (bitsFieldsById.get(bitsFieldId) == null) {
            return -1;
        }

        final int dataItemNumber = getDataItemNumber(bitsFieldId);
        final int fspecIndex = dataItemNumbersToFSPECIndex[dataItemNumber];
        if (fspecIndex < 0) {
            return -1;
        }

        final int offset = record.getDataItemOffset(fspecIndex);
        if (offset < 0) {
            return -1;
        }

        final DataItem item = dataItemsByNumber[dataItemNumber];
        if (null == item) {
            return -1;
        }

        final int format = getDataItemFormat(item.format.id);
        final boolean isExplicitDataFormat = format == DATA_FORMAT_EXPLICIT;
        return item.format.findDataFieldOffset(record.getData(), offset, repIndex, isExplicitDataFormat, bitsFieldId);
    }

    void append(
            final boolean withDataItems,
            final boolean withDataFields,
            final boolean withUAP,
            final boolean isShort,
            final Appendable out) throws IOException {

        final DataItem[] dataItems = this.dataItems;
        final int numDataItems = dataItems.length;
        if (withDataItems) {
            out.append("==== Data Items:").append(LF);
            DataItem item;
            for (int i = 0; i < numDataItems; i++) {
                item = dataItems[i];
                out.append("Data Item").append(' ')
                        .append(getDataItemIdString(item.getId())).append(' ').append(item.name).append(LF)
                        .append("  Definition: ").append(item.definition).append(LF)
                        .append("  Format    : ").append(item.format.description).append(LF);
            }
        }

        if (withDataFields) {
            if (!isShort) {
                out.append(LF).append("==== Bits:").append(LF);
            }

            for (int i = 0; i < numDataItems; i++) {
                dataItems[i].append(isShort, true, out);
            }
        }

        if (withUAP) {
            out.append(LF).append("UAP").append(LF);
            append(out);
        }
    }

    void append(final Appendable out) throws IOException {
        final DataItem[] fspecDataItems = this.fspecDataItems;
        for (int i = 0; i < fspecLength; i++) {
            final DataItem item = fspecDataItems[i];
            final boolean fx = (i & 7) == 7;
            final String frn = fx ? "FX" : Integer.toString(i - (i >> 3) + 1);
            final int num = item.getNumber();
            if (num == DATA_ITEM_UAP_SP) {
                out.append(String.format(FMT_UAP_ITEM, frn, "-        Spare", '-', "-")).append(LF);
            } else if (num == DATA_ITEM_UAP_FX) {
                out.append(String.format(FMT_UAP_ITEM, frn, "-        Field Extension Indicator", '-', "-")).append(LF);
            } else {
                final String s = num == DATA_ITEM_RE || num == DATA_ITEM_SP ? "  " : " ";
                out.append(String.format(FMT_UAP_ITEM, frn,
                                Strings.abbreviate(getDataItemIdString(item.getId()) + s + item.name, 60),
                                item.mandatory ? 'M' : 'O',
                                item.format.getLengthDescription()))
                        .append(LF);
            }
        }
    }

    int parse(final Buffer buffer, final int blockOffset, final int end, final DataBlock block, final Consumer<Record> consumer) {
        if (blockOffset < 0 || end < 0) {
            return ERR_OFFSET;
        }

        final int available = end - blockOffset;
        if (available < RECORD_MIN_NUM_OCTETS) {
            return ERR_RECORD_MIN_NUM_OCTETS;
        }

        int offset = blockOffset + 1; // Skip category.

        // Parse LEN field, 2 octets.
        final int len = buffer.getShort(offset) & 0xffff;
        if (len < RECORD_MIN_NUM_OCTETS) {
            return ERR_RECORD_MIN_NUM_OCTETS;
        }

        // There is not enough data, but parse what is there.
        final int blockLength = len > available ? available : len;

        final long categoryId = this.categoryId;
        if (block != null) {
            block.reset(categoryId, blockLength);
        }

        final int fspecMaxNumOctets = this.fspecMaxNumOctets;
        final int[] fixedDataItemNumOctets = this.fixedDataItemNumOctets;
        final DataItem[] fspecDataItems = this.fspecDataItems;
        final int endOfBlock = blockOffset + blockLength;
        offset += 2;
        int fspecOffset = offset;
        do {
            // Parses the FSPEC octets and concatenate each octet as long value.
            int fspecNumOctets = 1;
            long fspec = buffer.getByte(offset++) & 0xffL;
            while ((fspec & 0x01) == 1 &&// Checks that the current FSPEC octet has FX bit set to 1.
                    // Checks the number of octets of the FSPEC is within limit.
                    fspecNumOctets <= fspecMaxNumOctets &&
                    // Checks that we parse up to the end offset.
                    offset < endOfBlock) {
                // Concatenates the FSPEC octet value.
                fspec = (fspec << 8L) | (buffer.getByte(offset++) & 0xffL);
                // Increments the number of octets of the FSPEC.
                fspecNumOctets++;
            }

            // Check if the FSPEC is end with last FX bit is 0.
            if ((fspec & 0x01) != 0) {
                return ERR_RECORD_FSPEC_LAST_BIT;
            }

            // Initializes FSPEC bit mask with MSB.
            // Mask used to check whether the FSPEC bit is on or offset.
            long fspecMask = 1L << ((fspecNumOctets << 3) - 1);
            // Initializes the Data Items number of octets.
            int dataItemsNumOctets = 0;
            // Initializes the FSPEC index with 0. The FRN in UAP is numbered from 1 at the MSB.
            int fspecIndex = 0;
            // Initialize the Data Item offset to begin parsing each Data Item.
            int dataItemOffset = fspecOffset + fspecNumOctets;

            final Record record;
            if (block != null) {
                record = block.addRecord();
                record.reset(categoryId, buffer, fspecOffset, fspecNumOctets, fspecMaxNumOctets, fspec);
            } else {
                record = null;
            }

            DataItem dataItem;
            int dataItemLength;
            // Now parse the FSPEC long value for each bit presence without accessing the buffer.
            do {
                // Check if the FSPEC bit is set to 1.
                if ((fspec & fspecMask) != 0) {
                    // Parse the Data Field length using the Data Format used by the Data Item.
                    dataItemLength = fixedDataItemNumOctets[fspecIndex];
                    if (dataItemLength < 0) {
                        // Checks that the Data Item at the offset is not null.
                        if (null != (dataItem = fspecDataItems[fspecIndex])) {
                            dataItemLength = dataItem.format.parseLength(buffer, offset);
                        }
                    }

                    // Data Format returns negative to indicate error parsing the Data Field length.
                    if (dataItemLength < 0) {
                        if (dataItemLength == ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT) {
                            return ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT;
                        } else {
                            return ERR_PARSE_DATA_ITEM_LENGTH;
                        }
                    }

                    // Check the that the parsed length is within buffer's boundary.
                    if ((endOfBlock - (offset + dataItemLength)) < 0) {
                        return ERR_NOT_SUFFICIENT_NUM_OCTETS;
                    }

                    // Sets the Data Item offset at the FSPEC index.
                    if (record != null) {
                        record.setDataItemOffset(fspecIndex, dataItemOffset);
                    }

                    // Increments the Data Field offset with the parsed length.
                    dataItemOffset += dataItemLength;
                    // Moves the buffer offset to the next Data Field.
                    offset += dataItemLength;
                    // Increments total number of octets of the Data Fields.
                    dataItemsNumOctets += dataItemLength;
                }

                fspecMask >>>= 1;            // Shift right the mask 1 bit position for next bit.
                fspecIndex++;                // Increments the FRN to the next.
                if ((fspecIndex & 7) == 7) { // Check if the bit position is at the end of an octet (FX bit)
                    fspecMask >>>= 1;        // Skip the FX bit and shift right the mask 1 bit position at next octet.
                    fspecIndex++;            // Increments the FRN to start at next octet.
                }
            } while (fspecMask != 0);

            final int recordNumOctets = CAT_LEN_NUM_OCTETS + fspecNumOctets + dataItemsNumOctets;
            if ((dataItemsNumOctets | recordNumOctets - RECORD_MIN_NUM_OCTETS | RECORD_MAX_NUM_OCTETS - recordNumOctets) < 0) {
                return ERR_RECORD_NUM_OCTETS;
            }

            if (record != null) {
                record.setDataItemsNumOctets(dataItemsNumOctets);
                if (consumer != null) {
                    consumer.accept(record);
                }
            }

            fspecOffset += fspecNumOctets + dataItemsNumOctets;
        } while (offset < endOfBlock);

        return offset - blockOffset;
    }
}
