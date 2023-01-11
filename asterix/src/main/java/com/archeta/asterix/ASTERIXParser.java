/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static com.archeta.asterix.ASTERIXConstants.NUM_CATEGORY_NUMBERS;
import static com.archeta.asterix.ASTERIXConstants.RECORD_MIN_NUM_OCTETS;
import static com.archeta.asterix.ASTERIXIds.*;

public final class ASTERIXParser {
    public static final int RES_OK = 0;

    public static final int ERR_OFFSET = -1;
    public static final int ERR_RECORD_MIN_NUM_OCTETS = -2;
    public static final int ERR_MIN_CAPACITY = -3;
    public static final int ERR_CATEGORY_NOT_SUPPORTED = -4;
    public static final int ERR_NOT_SUFFICIENT_NUM_OCTETS = -5;
    public static final int ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT = -6;
    public static final int ERR_RECORD_FSPEC_LAST_BIT = -7;
    public static final int ERR_PARSE_DATA_ITEM_LENGTH = -8;
    public static final int ERR_RECORD_NUM_OCTETS = -9;

    public static final int ERR_PCAP_MAGIC_NUMBER = -21;
    public static final int ERR_PCAP_UNSUPPORTED_NETWORK_TYPE = -22;
    public static final int ERR_PCAP_TS_USEC_GREATER_THAN_ONE_SECONDS = -23;
    public static final int ERR_PCAP_NUM_CAPTURED_PACKETS_EXCEEDS_SNAPLEN = -24;
    public static final int ERR_PCAP_NUM_CAPTURED_PACKETS_EXCEEDS_ORIGLEN = -25;
    public static final int ERR_PCAP_NUM_BYTES_REMAINING = -26;
    public static final int ERR_PCAP_LINK_LAYER_PROTOCOL_TYPE = -27;
    public static final int ERR_PCAP_LINUX_SLL_LINK_LAYER_ADDR_LENGTH = -28;
    public static final int ERR_PCAP_LINUX_SLL_PROTOCOL_TYPE = -29;
    public static final int ERR_PCAP_IP_VERSION = -30;
    public static final int ERR_PCAP_IPV4_IHL = -31;
    public static final int ERR_PCAP_IPV4_LENGTH = -32;
    public static final int ERR_PCAP_IPV4_INVALID_FLAGS = -33;
    public static final int ERR_PCAP_IPV4_PROTO = -34;
    public static final int ERR_PCAP_UDP_LENGTH = -35;

    private static final ImmutableLong2ObjectHashMap<ASTERIXCategory> CATEGORIES = createCategories(
            Cat021V0Dot23.createCategory(),
            Cat021V0Dot231.createCategory(),
            Cat021V0Dot24.createCategory(),
            Cat021V0Dot26.createCategory(),
            Cat021V1Dot0.createCategory(),
            Cat021V1Dot7.createCategory(),
            Cat021V2Dot1.createCategory(),
            Cat021V2Dot2.createCategory(),
            Cat021V2Dot6.createCategory(),
            Cat034V1Dot29.createCategory(),
            Cat048V1Dot30.createCategory(),
            Cat048V1Dot31.createCategory());

    private static ImmutableLong2ObjectHashMap<ASTERIXCategory> createCategories(final ASTERIXCategory... categories) {
        final int n = categories.length;
        final ImmutableLong2ObjectHashMap.Builder<ASTERIXCategory> b = new ImmutableLong2ObjectHashMap.Builder<>(n);
        ASTERIXCategory category;
        for (int i = 0; i < n; i++) {
            category = categories[i];
            if (b.put(category.id, category) != null) {
                throw new IllegalArgumentException("duplicate " + getCategoryIdString(category.id) + ' ' + category.name);
            }
        }

        return b.build();
    }

    public static ASTERIXParser create(final long... categoryIds) {
        // Array of Categories indexed by the Category number, each Category not set is null.
        final ASTERIXCategory[] categories = new ASTERIXCategory[NUM_CATEGORY_NUMBERS];
        final int n = categoryIds.length;
        for (int i = 0; i < n; i++) {
            final long id = categoryIds[i];
            final ASTERIXCategory category = CATEGORIES.get(id);
            if (null == category) {
                throw new IllegalArgumentException("unsupported " + getCategoryIdString(id));
            }

            final int num = getCategoryNumber(id);
            if (categories[num] != null) {
                throw new IllegalArgumentException("duplicate " + getCategoryIdString(id) + ' ' + category.name);
            }

            categories[num] = category;
        }

        return new ASTERIXParser(categories);
    }

    private final ASTERIXCategory[] categories;

    private ASTERIXParser(final ASTERIXCategory[] categories) {
        this.categories = categories;
    }

    public int parsePCAP(final File pcapFile, final DataBlockConsumer blockConsumer, final RecordConsumer recordConsumer) throws IOException {
        return PcapParser.parse(pcapFile, new PacketHandlerImpl(blockConsumer, recordConsumer));
    }

    public int parse(
            final Buffer buffer,
            final int offset,
            final int length,
            final DataBlock block,
            final Consumer<DataBlock> blockConsumer,
            final Consumer<Record> recordConsumer) {

        if (offset < 0) {
            return ERR_OFFSET;
        }

        if (length < RECORD_MIN_NUM_OCTETS) {
            return ERR_RECORD_MIN_NUM_OCTETS;
        }

        final int capacity = buffer.capacity();
        final int end = offset + length;
        if (end > capacity) {
            return ERR_MIN_CAPACITY;
        }

        int off = offset, catno, len;
        do {
            // Parse CAT field, 1-octet.
            catno = buffer.getByte(off) & 0xff;

            // Finds Category from categories definition.
            final ASTERIXCategory cat = categories[catno];
            if (null == cat) {
                return ERR_CATEGORY_NOT_SUPPORTED;
            }

            // Parse the buffer starting from offset up to length.
            len = cat.uap.parse(buffer, off, end, block, recordConsumer);
            if (len < 0) {
                return len;
            }

            if (block != null && blockConsumer != null) {
                blockConsumer.accept(block);
            }

            off += len;
        } while (off < end);

        return RES_OK;
    }

    public void forEachDataField(final DataFieldConsumer consumer) {
        final ASTERIXCategory[] categories = this.categories;
        ASTERIXCategory category;
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            category = categories[i];
            if (category != null) {
                category.uap.forEachDataField(consumer);
            }
        }
    }

    public void appendCategories(final boolean isShort, final Appendable out) throws IOException {
        final ASTERIXCategory[] categories = this.categories;
        ASTERIXCategory category;
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            category = categories[i];
            if (category != null) {
                category.append(isShort, out);
            }
        }
    }

    public void appendCategory(
            final long categoryId,
            final boolean withDataItems,
            final boolean withDataFields,
            final boolean withUAP,
            final boolean isShort,
            final Appendable output) throws IOException {

        final ASTERIXCategory category = categories[getCategoryNumber(categoryId)];
        if (null != category && category.id == categoryId) {
            category.append(withDataItems, withDataFields, withUAP, isShort, output);
        }
    }

    public void appendDataItem(
            final long dataItemId,
            final boolean isShort,
            final boolean withDataFields,
            final Appendable output) throws IOException {

        final ASTERIXCategory category = categories[getCategoryNumber(dataItemId)];
        final DataItem item;
        if (null != category &&
                category.id == getCategoryId(dataItemId) &&
                (item = category.uap.findDataItemByNumber(getDataItemNumber(dataItemId))) != null) {
            item.append(isShort, withDataFields, output);
        }
    }

    public void appendRecord(
            final Record record,
            final boolean withHeader,
            final boolean line,
            final boolean valueOnly,
            final Appendable output) throws IOException {

        final long categoryId = record.getCategoryId();
        final ASTERIXCategory category = categories[getCategoryNumber(categoryId)];
        if (null != category && category.id == categoryId) {
            record.append(withHeader, line, valueOnly, category.uap, output);
        }
    }

    public boolean hasCategory(final long categoryId) {
        final ASTERIXCategory category = categories[getCategoryNumber(categoryId)];
        return null != category && category.id == categoryId;
    }

    public boolean hasDataItem(final long dataItemId, final Record record) {
        return findDataItemOffset(dataItemId, record) != -1;
    }

    public int findDataItemOffset(final long dataItemId, final Record record) {
        final ASTERIXCategory category = findCategory(dataItemId, record.getCategoryId());
        final int fspecIndex;
        if (null == category || (fspecIndex = category.uap.findDataItemFSPECIndex(dataItemId)) < 0) {
            return -1;
        }

        return record.getDataItemOffset(fspecIndex);
    }

    public boolean hasDataField(final long bitsFieldId, final Record record) {
        return hasDataField(bitsFieldId, 0, record);
    }

    public boolean hasDataField(final long bitsFieldId, final int repIndex, final Record record) {
        return findDataFieldOffset(bitsFieldId, repIndex, record) != -1;
    }

    public int findDataFieldOffset(final long bitsFieldId, final Record record) {
        return findDataFieldOffset(bitsFieldId, 0, record);
    }

    public int findDataFieldOffset(final long bitsFieldId, final int repIndex, final Record record) {
        final ASTERIXCategory category = findCategory(bitsFieldId, record.getCategoryId());
        if (null == category) {
            return -1;
        }

        return category.uap.findDataFieldOffset(bitsFieldId, repIndex, record);
    }

    public boolean getBoolean(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getBoolean(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public boolean getBoolean(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getBoolean(record.getData(), dataFieldOffset);
    }

    public byte getByte(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getByte(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public byte getByte(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getByte(record.getData(), dataFieldOffset);
    }

    public long getLong(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getLong(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public long getLong(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getLong(record.getData(), dataFieldOffset);
    }

    public double getDouble(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getDouble(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public double getDouble(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getDouble(record.getData(), dataFieldOffset);
    }

    public BitsValue getBitsValue(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getBitsValue(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public BitsValue getBitsValue(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getBitsValue(record.getData(), dataFieldOffset);
    }

    public String getString(final long bitsFieldId, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getString(record.getData(), findDataFieldOffset(bitsFieldId, record));
    }

    public String getString(final long bitsFieldId, final int dataFieldOffset, final Record record) {
        return getBitsField(bitsFieldId, record.getCategoryId()).getString(record.getData(), dataFieldOffset);
    }

    public Appendable getString(final long bitsFieldId, final Record record, final Appendable out) throws IOException {
        return getBitsField(bitsFieldId, record.getCategoryId()).getString(record.getData(), findDataFieldOffset(bitsFieldId, record), out);
    }

    public Appendable getString(final long bitsFieldId, final int dataFieldOffset, final Record record, final Appendable out) throws IOException {
        return getBitsField(bitsFieldId, record.getCategoryId()).getString(record.getData(), dataFieldOffset, out);
    }

    public int getAllDataItemFields(final long dataItemId, final Record record, final DataFieldValueConsumer consumer) {
        final long categoryId = record.getCategoryId();
        final ASTERIXCategory category = categories[getCategoryNumber(categoryId)];
        if (null == category || category.id != categoryId) {
            return 0;
        }

        final UserApplicationProfile uap = category.uap;
        final int fspecIndex = uap.findDataItemFSPECIndex(dataItemId);
        final DataItem item;
        final int offset;
        if (fspecIndex < 0 ||
                (item = uap.getDataItemByFSPECIndex(fspecIndex)) == null ||
                (offset = record.getDataItemOffset(fspecIndex)) < 0) {
            return 0;
        }

        return item.format.getAllDataFields(record.getData(), offset, item, null, null, 0, consumer);
    }

    public int getAllDataFields(final Record record, final DataFieldValueConsumer consumer) {
        final long categoryId = record.getCategoryId();
        final ASTERIXCategory cat = categories[getCategoryNumber(categoryId)];
        if (null == cat || cat.id != categoryId) {
            return 0;
        }

        int length = 0;
        final UserApplicationProfile uap = cat.uap;
        final int fspecLength = uap.getFSPECLength();
        int offset;
        DataItem item;
        final Buffer data = record.getData();
        for (int i = 0; i < fspecLength; i++) {
            offset = record.getDataItemOffset(i);
            if (offset >= 0) {
                item = uap.getDataItemByFSPECIndex(i);
                length += item.format.getAllDataFields(data, offset, item, null, null, 0, consumer);
            }
        }

        return length;
    }

    private ASTERIXCategory findCategory(final long id, final long recordCategoryId) {
        final ASTERIXCategory category = categories[getCategoryNumber(id)];
        if (null != category && category.id == getCategoryId(id) && category.id == recordCategoryId) {
            return category;
        }

        return null;
    }

    private BitsField getBitsField(final long bitsFieldId, final long recordCategoryId) {
        final ASTERIXCategory category = categories[getCategoryNumber(bitsFieldId)];
        if (null == category) {
            throw new ASTERIXParseException(ERR_CATEGORY_NOT_SUPPORTED,
                    "unsupported category " + getCategoryIdString(bitsFieldId));
        }

        if (category.id != recordCategoryId) {
            throw new ASTERIXParseException(ERR_CATEGORY_NOT_SUPPORTED,
                    "unsupported category " + getCategoryIdString(recordCategoryId));
        }

        final BitsField field = category.uap.findBitsField(bitsFieldId);
        if (null == field) {
            throw new ASTERIXParseException(ERR_CATEGORY_NOT_SUPPORTED,
                    "unknown bits field " + getBitsFieldIdString(bitsFieldId));
        }

        return field;
    }

    private final class PacketHandlerImpl implements PcapParser.PacketHandler {
        private final DataBlock block = new DataBlock();
        private final Consumer<DataBlock> blockConsumer;
        private final Consumer<Record> recordConsumer;
        private int blockNumber = 0;
        private int recordNumber = 0;
        private int packetNumber = 0;
        private long ts = 0L;

        private PacketHandlerImpl(final DataBlockConsumer blockConsumer, final RecordConsumer recordConsumer) {
            this.blockConsumer = null == blockConsumer ? null : (b) -> {
                blockNumber++;
                blockConsumer.accept(b, blockNumber, packetNumber, ts);
            };

            this.recordConsumer = null == recordConsumer ? null : (r) -> {
                recordNumber++;
                recordConsumer.accept(r, recordNumber, blockNumber, packetNumber, ts);
            };
        }

        public int onNext(final Buffer buffer, final int offset, final int length, final int packetNumber, final long ts) {
            this.packetNumber = packetNumber;
            this.ts = ts;
            return parse(buffer, offset, length, block, blockConsumer, recordConsumer);
        }
    }
}
