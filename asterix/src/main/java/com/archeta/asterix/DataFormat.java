/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;
import static com.archeta.asterix.ASTERIXParser.ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT;
import static com.archeta.asterix.BitsFieldList.FMT_FIXED;
import static com.archeta.asterix.BitsFieldList.MAX_WIDTH;

final class DataFormat {
    private static final String EMPTY_DESCRIPTION = "Empty Data Format";
    private static final String EXPLICIT_DESCRIPTION = "Explicit format";
    private static final String[] OCTETS = {
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen"
    };

    static DataFormat empty(final long id) {
        checkEmptyFormatId(id);
        return new DataFormat(id, EMPTY_DESCRIPTION, "0", BitsFieldList.empty(id), null, false, null, null, null);
    }

    static DataFormat fixed(final long id, final int numOctets, final BitsField... fields) {
        checkFixedFormatId(id);
        final BitsFieldList bfs = BitsFieldList.create(id, numOctets, fields);
        final String desc = getOctetsString(bfs.numOctets) + "-octet fixed length Data Item";
        final String len = Integer.toString(bfs.numOctets);
        return new DataFormat(id, desc, len, bfs, null, true, null, null, null);
    }

    static DataFormat repetitive(final long id, final int numOctets, String description, final BitsField... fields) {
        checkRepetitiveFormatId(id);
        final BitsFieldList bfs = BitsFieldList.create(id, numOctets, fields);
        final String desc = "Repetitive Data Item starting with a one-octet Field Repetition Indicator (REP) followed by at least one "
                + (null == description ? "data" : description);
        final String len = "1+" + bfs.numOctets + "*n";
        return new DataFormat(id, desc, len, bfs, null, false, null, null, null);
    }

    static DataFormat mbdata(final long id, final BitsField... fields) {
        checkRepetitiveFormatId(id);
        final BitsFieldList bfs = BitsFieldList.create(id, ModeSMBData.NUM_OCTETS, fields);
        final String desc = "Repetitive Data Item starting with a one-octet Field Repetition Indicator (REP) followed by at least one BDS report comprising one seven octet BDS register and one octet BDS code";
        final String len = "1+" + bfs.numOctets + "*n";
        return new DataFormat(id, desc, len, bfs, new ModeSMBData(id), false, null, null, null);
    }

    static DataFormat extended(final long id, final Part firstPart, final Part... extents) {
        checkExtendedFormatId(id);
        final int firstPartNumOctets = firstPart.fields.numOctets;
        final boolean sameExtents = extents.length < 1;
        final String desc = "Variable length Data Item comprising a first part of " +
                getOctetsString(firstPartNumOctets).toLowerCase() + "-octet, followed by " +
                (getOctetsString(sameExtents ? firstPartNumOctets : extents[0].fields.numOctets).toLowerCase()) +
                "-octet extents as necessary";
        final Extended e = new Extended(firstPart, extents);
        final String len = Integer.toString(firstPartNumOctets) + '+';
        return new DataFormat(id, desc, len, null, null, false, e, null, null);
    }

    static DataFormat compound(final long id, final Subfield... subfields) {
        checkCompoundFormatId(id);
        final int numSubfields = subfields.length;
        if (((numSubfields - 1) & 0x7) != 7) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `subfields` length must be multiple of 8, length=%s",
                    getDataFieldIdString(id), numSubfields));
        }

        final int primarySubfieldNumOctets = numSubfields >> 3;
        checkPrimarySubfieldNumOctets(primarySubfieldNumOctets, id);

        Subfield subfield;
        for (int i = 0, bitPresence = numSubfields, number = 1; i < numSubfields; i++, bitPresence--, number++) {
            subfield = subfields[i];
            if ((i & 7) == 7 && !FX.equals(subfield.name)) {
                throw new IllegalArgumentException(Fmt.sprintf(
                        "%s `subfield` at %s must be FX, name=%s",
                        getDataFieldIdString(id),
                        i, subfield.name));
            }

            if (subfield.bitPresence != bitPresence) {
                throw new IllegalArgumentException(Fmt.sprintf(
                        "%s `subfield` at %s `bit` presence must be %s, bit=%s",
                        getDataFieldIdString(id, subfield.name),
                        i, bitPresence, subfield.bitPresence));
            }

            if (subfield.number != number) {
                throw new IllegalArgumentException(Fmt.sprintf(
                        "%s `subfield` at %s `number` must be %s, bit=%s",
                        getDataFieldIdString(id, subfield.name),
                        i, number, subfield.number));
            }
        }

        final Compound c = new Compound(subfields);
        final String octet = c.primarySubfieldNumOctets == 1
                ? getOctetsString(c.primarySubfieldNumOctets).toLowerCase()
                : "up to " + getOctetsString(c.primarySubfieldNumOctets).toLowerCase();
        final String desc = "Compound Data Item, comprising a primary subfield of "
                + octet + "-octet, followed by the indicated subfields";
        return new DataFormat(id, desc, "1+", null, null, false, null, c, null);
    }

    static DataFormat explicit(final long id) {
        checkExplicitFormatId(id);
        return new DataFormat(id, EXPLICIT_DESCRIPTION, "+1", null, null, false, null, null, Explicit.EMPTY);
    }

    static DataFormat explicit(final long id, final int majorVersion, final int minorVersion, final Subitem... subitems) {
        checkExplicitFormatId(id);
        checkVersion(majorVersion, minorVersion, id);
        final int numSubitems = subitems.length;
        if (((numSubitems - 1) & 0x7) != 7) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `subitems` length must be multiple of 8, length=%s",
                    getDataFieldIdString(id), numSubitems));
        }

        final int itemsIndicatorNumOctets = numSubitems >> 3;
        checkItemsIndicatorNumOctets(itemsIndicatorNumOctets, id);

        Subitem subitem;
        for (int i = 0, bitPresence = numSubitems, number = 1; i < numSubitems; i++, bitPresence--, number++) {
            subitem = subitems[i];

            if (subitem.bitPresence != bitPresence) {
                throw new IllegalArgumentException(Fmt.sprintf(
                        "%s `subitem` at %s `bit` presence must be %s, bit=%s",
                        getDataFieldIdString(id, subitem.name),
                        i, bitPresence, subitem.bitPresence));
            }

            if (subitem.number != number) {
                throw new IllegalArgumentException(Fmt.sprintf(
                        "%s `subitem` at %s `number` must be %s, bit=%s",
                        getDataFieldIdString(id, subitem.name),
                        i, number, subitem.number));
            }
        }

        final Explicit e = new Explicit(subitems);
        final String desc = EXPLICIT_DESCRIPTION + " Version " + majorVersion + '.' + minorVersion;
        return new DataFormat(id, desc, "+1", null, null, false, null, null, e);
    }

    static String getOctetsString(final int numOctets) {
        if (numOctets > 0 && numOctets <= 16) {
            return OCTETS[numOctets - 1];
        }

        if (numOctets > 0) {
            return Integer.toString(numOctets);
        }

        throw new IllegalArgumentException("`numOctets` must be positive, found: " + numOctets);
    }

    static void checkVersion(final int majorVersion, final int minorVersion, final long id) {
        if (majorVersion < 0) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `major` version number must be nonnegative, major=%s",
                    getDataItemIdString(id), majorVersion));
        }

        if (minorVersion < 0) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `minor` version number must be nonnegative, minor=%s",
                    getDataItemIdString(id), minorVersion));
        }
    }

    static void checkFixedFormatId(final long id) {
        final int dataItemFormat = getDataItemFormat(id);
        if (dataItemFormat == DATA_FORMAT_FIXED) {
            final long categoryId = getCategoryId(id);
            final long dataItemId = dataItemId(categoryId, getDataItemNumber(id), DATA_FORMAT_FIXED);
            final long subAId = subAId(dataItemId, 0, DATA_FORMAT_FIXED);
            final long dataFieldId = subBId(subAId, 0, DATA_FORMAT_FIXED);
            if (dataFieldId != getDataFieldId(id)) {
                throw new ASTERIXFormatException("[FIXED] data field id");
            }
        }

        if (dataItemFormat == DATA_FORMAT_COMPOUND) {
            final int subAFmt = getSubAFormat(id);
            if (subAFmt != DATA_FORMAT_FIXED) {
                throw new ASTERIXFormatException("[COMPOUND] type must be FIXED");
            }
        } else if (dataItemFormat == DATA_FORMAT_EXPLICIT) {

        }
    }

    static void checkRepetitiveFormatId(final long id) {
        final int dataItemFormat = getDataItemFormat(id);
        if (dataItemFormat == DATA_FORMAT_REPETITIVE) {
            final long categoryId = getCategoryId(id);
            final long dataItemId = dataItemId(categoryId, getDataItemNumber(id), DATA_FORMAT_REPETITIVE);
            final long subAId = subAId(dataItemId, 0, DATA_FORMAT_FIXED);
            final long dataFieldId = subBId(subAId, 0, DATA_FORMAT_FIXED);
            if (dataFieldId != getDataFieldId(id)) {
                throw new ASTERIXFormatException("[REPETITIVE] data field id");
            }
        }
    }

    static void checkExtendedFormatId(final long id) {
        final int fmt = getDataItemFormat(id);
        if (fmt != DATA_FORMAT_EXTENDED && fmt != DATA_FORMAT_COMPOUND && fmt != DATA_FORMAT_EXPLICIT) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s data item format must be %s, %s, or %s, found: %s",
                    getDataFieldIdString(id),
                    getDataFormatString(DATA_FORMAT_EXPLICIT),
                    getDataFormatString(DATA_FORMAT_COMPOUND),
                    getDataFormatString(DATA_FORMAT_EXPLICIT),
                    getDataFormatString(fmt)));
        }
    }

    static void checkCompoundFormatId(final long id) {
        final int fmt = getDataItemFormat(id);
        if (fmt != DATA_FORMAT_COMPOUND && fmt != DATA_FORMAT_EXPLICIT) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s data item format must be %s or %s, found: %s",
                    getDataFieldIdString(id),
                    getDataFormatString(DATA_FORMAT_COMPOUND),
                    getDataFormatString(DATA_FORMAT_EXPLICIT),
                    getDataFormatString(fmt)));
        }

        if (fmt == DATA_FORMAT_EXPLICIT && getSubAFormat(id) != DATA_FORMAT_COMPOUND) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s subfield1 format must be %s, found: %s",
                    getDataFieldIdString(id),
                    getDataFormatString(DATA_FORMAT_COMPOUND),
                    getDataFormatString(getSubAFormat(id))));
        }
    }

    static void checkEmptyFormatId(final long id) {
        // TODO
    }

    static void checkExplicitFormatId(final long id) {
        final int fmt = getDataItemFormat(id);
        if (fmt != DATA_FORMAT_EXPLICIT) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s data item format must be %s, found: %s",
                    getDataFieldIdString(id),
                    getDataFormatString(DATA_FORMAT_EXPLICIT),
                    getDataFormatString(fmt)));
        }
    }

    static void checkPrimarySubfieldNumOctets(final int primarySubfieldNumOctets, final long id) {
        if (primarySubfieldNumOctets < 1 || primarySubfieldNumOctets > PRIMARY_SUBFIELD_MAX_NUM_OCTETS) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `numOctets` for primary subfield must be in range [1,%s], numOctets=%s",
                    getDataFieldIdString(id), PRIMARY_SUBFIELD_MAX_NUM_OCTETS, primarySubfieldNumOctets));
        }
    }

    static void checkItemsIndicatorNumOctets(final int itemsIndicatorNumOctets, final long id) {
        if (itemsIndicatorNumOctets < 1 || itemsIndicatorNumOctets > ITEMS_INDICATOR_MAX_NUM_OCTETS) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `numOctets` for items indicator must be in range [1,%s], numOctets=%s",
                    getDataFieldIdString(id), ITEMS_INDICATOR_MAX_NUM_OCTETS, itemsIndicatorNumOctets));
        }
    }

    static void checkSubfieldFormat(final DataFormat format) {
        // TODO
    }

    static void checkSubitemFormat(final DataFormat format) {
        // TODO
    }

    final long id;
    final String description;
    private final String lengthDescription;
    private final BitsFieldList fields;
    private final ModeSMBData mbdata;
    private final Extended extended;
    private final Compound compound;
    private final Explicit explicit;
    private final boolean fixed;

    private DataFormat(
            final long id,
            final String description,
            final String lengthDescription,
            final BitsFieldList fields,
            final ModeSMBData mbdata,
            final boolean fixed,
            final Extended extended,
            final Compound compound,
            final Explicit explicit) {

        this.id = id;
        this.fixed = fixed;
        this.mbdata = mbdata;
        this.fields = fields;
        this.extended = extended;
        this.compound = compound;
        this.explicit = explicit;
        this.description = description;
        this.lengthDescription = lengthDescription;
    }

    int getNumOctets() {
        return fields != null ? fields.numOctets : -1;
    }

    String getLengthDescription() {
        return lengthDescription;
    }

    int parseLength(final Buffer buffer, final int offset) {
        if (fields != null) {
            if (fixed) {
                return fields.numOctets;
            }

            // Repetition factor is in first octet, include the length field itself.
            final int rep = buffer.getByte(offset) & 0xff;
            return 1 /* REP */ + rep * fields.numOctets;
        }

        if (extended != null) {
            return extended.parseLength(buffer, offset);
        }

        if (compound != null) {
            return compound.parseLength(buffer, offset);
        }

        if (explicit != null) {
            return buffer.getByte(offset) & 0xff; // Returns the LEN indicator.
        }

        return -1;
    }

    int findDataFieldOffset(
            final Buffer buffer,
            final int offset,
            final int repIndex,
            final boolean isExplicitDataFormat,
            final long bitsFieldId) {

        if (fields != null) {
            if (fixed) {
                return offset;
            }

            if (repIndex < 0) {
                return -1;
            }

            return offset + 1 + repIndex * fields.numOctets;
        }

        final int bitPresence = isExplicitDataFormat ? getSubBBitPresence(bitsFieldId) : getSubABitPresence(bitsFieldId);

        if (extended != null) {
            return extended.findDataFieldOffset(buffer, offset, bitPresence, repIndex);
        }

        if (compound != null) {
            return compound.findDataFieldOffset(buffer, offset, bitPresence, repIndex, bitsFieldId);
        }

        if (explicit != null) {
            return explicit.findDataFieldOffset(buffer, offset, bitPresence, repIndex, bitsFieldId);
        }

        return -1;
    }

    int getAllDataFields(
            final Buffer buffer,
            final int offset,
            final DataItem item,
            final Subitem subitem,
            final Subfield subfield,
            final int repIndex,
            final DataFieldValueConsumer consumer) {

        if (fields != null) {
            if (fixed) {
                return fields.getAllDataFields(buffer, offset, item, subitem, subfield, null, -1, consumer);
            }

            final int rep = buffer.getByte(offset) & 0xff;
            int off = offset + 1;
            if (null == mbdata) {
                for (int i = 0; i < rep; i++) {
                    off += fields.getAllDataFields(buffer, off, item, subitem, subfield, null, repIndex, consumer);
                }

            } else {
                for (int i = 0; i < rep; i++) {
                    off += mbdata.getAllDataFields(buffer, off, item, subitem, subfield, repIndex, consumer);
                }

            }

            return off - offset;
        }

        if (extended != null) {
            return extended.getAllDataFields(buffer, offset, item, subitem, subfield, consumer);
        }

        if (compound != null) {
            return compound.getAllDataFields(buffer, offset, item, subitem, repIndex, consumer);
        }

        if (explicit != null) {
            return explicit.getAllDataFields(buffer, offset, item, repIndex, consumer);
        }

        return 0;
    }

    void forEachDataField(final DataItem item, final Subitem subitem, final Subfield subfield, final DataFieldConsumer consumer) {
        if (fields != null) {
            fields.forEachDataField(item, subitem, subfield, null, consumer);
            return;
        }

        if (extended != null) {
            extended.forEachDataField(item, subitem, subfield, consumer);
            return;
        }

        if (compound != null) {
            compound.forEachDataField(item, subitem, consumer);
            return;
        }

        if (explicit != null) {
            explicit.forEachDataField(item, consumer);
        }
    }

    void addBitsFieldsTo(final List<BitsFieldList> dst) {
        if (fields != null) {
            dst.add(fields);
            return;
        }

        if (extended != null) {
            dst.add(extended.fields);
            return;
        }

        if (compound != null) {
            dst.add(compound.fields);
            return;
        }

        if (explicit != null) {
            dst.add(explicit.fields);
        }
    }

    void append(final String prefix, final boolean isShort, final Appendable out) throws IOException {
        if (fields != null) {
            fields.append(prefix, isShort, out);
            return;
        }

        if (extended != null) {
            extended.append(prefix, isShort, out);
            return;
        }

        if (compound != null) {
            compound.append(prefix, isShort, out);
            return;
        }

        if (explicit != null) {
            explicit.append(prefix, isShort, out);
        }
    }

    static final class Extended {
        private final int firstPartNumOctets;
        private final int extentNumOctets;
        private final Part firstPart;
        private final Part[] extents;
        private final boolean sameExtents;
        private final BitsFieldList fields;

        private Extended(final Part firstPart, final Part[] extents) {
            this.firstPartNumOctets = firstPart.fields.numOctets;
            final int numExtents = extents.length;
            this.sameExtents = numExtents < 1;
            this.extentNumOctets = sameExtents ? 0 : extents[0].fields.numOctets;
            this.firstPart = firstPart;
            this.extents = new Part[numExtents];
            System.arraycopy(extents, 0, this.extents, 0, numExtents);

            final List<BitsFieldList> bfl = new ArrayList<>();
            bfl.add(firstPart.fields);
            for (int i = 0; i < numExtents; i++) {
                bfl.add(extents[i].fields);
            }

            fields = BitsFieldList.create(bfl);
        }

        int parseLength(final Buffer buffer, final int offset) {
            // ASTERIX standards allows for using Variable length format (EXTENDED) with 0 extent.
            // The first part is repeated until the FX bit is 0.
            // Initializes the octet offset begins with the last octet of the first part.
            int off = offset + firstPartNumOctets - 1;
            final int numExtents = extents.length;
            if (numExtents == 0) {
                // Iterates over the parts by checking the last octet of the previous
                // part has FX bit set to 1.
                while ((buffer.getByte(off) & 0x01) == 1) {
                    off += firstPartNumOctets;
                }
            } else {
                if (sameExtents) {
                    // Iterates over the extents with same structure, check that the last octet
                    // of the previous part has FX bit set to 1.
                    while ((buffer.getByte(off) & 0x01) == 1) {
                        // Moves the offset to the last octet of next extent.
                        off += extentNumOctets;
                    }
                } else {
                    // Iterates over the extents, check that the last octet of the previous
                    // part has FX bit set to 1.
                    for (int i = 0; i < numExtents && (buffer.getByte(off) & 0x01) == 1; i++) {
                        // Moves the offset to the last octet of next extent.
                        off += extentNumOctets;
                    }
                }
            }

            // The Data Field length is the number of octets that has been parsed.
            return off - offset + 1;
        }

        int findDataFieldOffset(final Buffer buffer, final int offset, final int part, final int repIndex) {
            // ASTERIX standards allows for using the Variable length format (ExtendedDataFormat)
            // with 0 extent. The first part is repeated until the FX bit is 0.
            final int numExtents = extents.length;
            if (numExtents == 0) {
                // In this case, part number must be 0.
                if (part != 0) {
                    return -1;
                }

                // Repetitive index must be non-negative.
                if (repIndex < 0) {
                    return -1;
                }

                // Repetitive index 0 means the first part, just returns the beginIndex.
                if (repIndex == 0) {
                    return offset;
                }

                // Initializes the octet offset begins with the last octet of the first part.
                int off = offset + firstPartNumOctets - 1;
                for (int i = 0; i < repIndex; i++) {
                    // Check if the last octet of the previous part has FX bit set 1.
                    if ((buffer.getByte(off) & 0x01) != 1) {
                        return -1;
                    }

                    // Moves index to the last octet of next part.
                    off += firstPartNumOctets;
                }

                return off;
            }

            // Part number 0 means the first part, just returns the beginIndex.
            if (part == 0) {
                return offset;
            }

            final int requestedExtent = part - 1;
            // Initializes the octet offset begins with the last octet of the first part.
            int off = offset + firstPartNumOctets - 1;
            for (int i = 0; i < numExtents && (buffer.getByte(off) & 0x01) == 1; i++) {
                if (i == requestedExtent) {
                    return off + 1;
                }

                off += extentNumOctets;
            }

            return -1;
        }

        int getAllDataFields(
                final Buffer buffer,
                final int offset,
                final DataItem item,
                final Subitem subitem,
                final Subfield subfield,
                final DataFieldValueConsumer consumer) {

            final Part[] extents = this.extents;
            final int numExtents = extents.length;

            // ASTERIX standards allows for using Variable length format (EXTENDED) with 0 extent.
            // The first part is repeated until the FX bit is 0.
            // Initializes the octet offset begins with the last octet of the first part.
            if (numExtents == 0) {
                // Initializes the octet offset begins with the last octet of the first part.
                int off = offset - 1;
                do {
                    // Retrieves the fields from the current part.
                    firstPart.fields.getAllDataFields(buffer, off + 1, item, subitem, subfield, firstPart, -1, consumer);
                    // Moves off to the last octet of next part.
                    off += firstPartNumOctets;
                } while ((buffer.getByte(off) & 0x01) == 1);

                // The Data Field length is the number of octets that has been parsed.
                return off - offset + 1;
            } else {
                // Retrieves all fields from the first part.
                firstPart.fields.getAllDataFields(buffer, offset, item, subitem, subfield, firstPart, -1, consumer);

                // Initializes the octet offset begins with the last octet of the first part.
                int off = offset + firstPartNumOctets - 1;

                if (sameExtents) {
                    // Iterates over the extents with same structure, check that the last octet of the previous
                    // part has FX bit set to 1.
                    final Part part = extents[0];
                    while ((buffer.getByte(off) & 0x01) == 1) {
                        // Retrieves the fields from the current part.
                        part.fields.getAllDataFields(buffer, off + 1, item, subitem, subfield, part, -1, consumer);
                        // Moves the offset to the last octet of next extent.
                        off += extentNumOctets;
                    }
                } else {
                    Part part;
                    // Iterates over the extents, check that the last octet of the previous part has FX bit set to 1.
                    for (int i = 0; i < numExtents && (buffer.getByte(off) & 0x01) == 1; i++) {
                        // Retrieves the fields from the current part.
                        part = extents[i];
                        part.fields.getAllDataFields(buffer, off + 1, item, subitem, subfield, part, -1, consumer);
                        // Moves the offset to the last octet of next extent.
                        off += extentNumOctets;
                    }
                }

                // The Data Field length is the number of octets that has been parsed.
                return off - offset + 1;
            }
        }

        void forEachDataField(final DataItem item, final Subitem subitem, final Subfield subfield, final DataFieldConsumer consumer) {
            firstPart.fields.forEachDataField(item, subitem, subfield, firstPart, consumer);
            final Part[] extents = this.extents;
            final int numExtents = extents.length;
            for (int i = 0; i < numExtents; i++) {
                final Part extent = extents[i];
                extent.fields.forEachDataField(item, subitem, subfield, extent, consumer);
            }
        }

        void append(final String dataItemIdString, final boolean isShort, final Appendable out) throws IOException {
            firstPart.fields.append(dataItemIdString, isShort, out);
            final Part[] extents = this.extents;
            final int numExtents = extents.length;
            for (int i = 0; i < numExtents; i++) {
                extents[i].fields.append(dataItemIdString, isShort, out);
            }
        }
    }

    static final class Compound {
        private final BitsField[] primarySubfieldBitPresences;
        private final int primarySubfieldNumOctets;
        private final Subfield[] subfields;
        private final Subfield[] subfieldsByBitPresences;
        private final BitsFieldList fields;

        private Compound(final Subfield[] subfields) {
            final int numSubfields = subfields.length;
            primarySubfieldNumOctets = numSubfields >> 3;
            this.subfields = new Subfield[numSubfields];
            System.arraycopy(subfields, 0, this.subfields, 0, numSubfields);
            primarySubfieldBitPresences = new BitsField[numSubfields];
            subfieldsByBitPresences = new Subfield[numSubfields];
            final List<BitsFieldList> bfl = new ArrayList<>();
            for (int i = 0, j = numSubfields - 1; i < numSubfields; i++, j--) {
                final Subfield subfield = subfields[i];
                final int from = subfield.bitPresence;
                final int number = subfield.number;
                final String name = subfield.name;
                final String desc = subfield.description;
                final long bitsFieldId = bitsFieldId(getDataFieldId(subfield.getId()), from, BITS_FIELD_ENCODING_VALUE);
                final BitsField.Position pos = BitsField.position(primarySubfieldNumOctets, from, from);
                final BitsValue value0 = BitsValue.of(0, "Absence of Subfield #" + number);
                final BitsValue value1 = BitsValue.of(1, "Presence of Subfield #" + number);
                primarySubfieldBitPresences[i] = BitsField.bit(bitsFieldId, pos, name, desc, value0, value1);
                subfieldsByBitPresences[j] = subfield;
                subfield.format.addBitsFieldsTo(bfl);
            }

            fields = BitsFieldList.create(bfl);
        }

        int parseLength(final Buffer buffer, final int offset) {
            // Initializes the offset of the buffer to start parsing.
            int off = offset;

            // Parses the Primary Subfield octets and concatenate each octet as long value.
            // The Primary Subfield value is used to parse the bit presence below without accessing
            // the buffer. This is possible because PRIMARY_SUBFIELD_MAX_NUM_OCTETS will enforce
            // the Compound Data Format to have Primary Subfield that fit within a long value.
            final int maxPrimarySubfieldNumOctets = this.primarySubfieldNumOctets;
            int primarySubfieldNumOctets = 1;
            long primarySubfieldValue = buffer.getByte(off++) & 0xffL;
            while ((primarySubfieldValue & 0x01) == 1 // Checks that the current octet has FX bit set to 1.
                    // Checks the number of octets of the Primary Subfield is within limit.
                    && primarySubfieldNumOctets <= maxPrimarySubfieldNumOctets) {
                // Concatenates the Primary Subfield octet value.
                primarySubfieldValue = (primarySubfieldValue << 8) | (buffer.getByte(off++) & 0xffL);
                // Increments the number of octets of the Primary Subfield.
                primarySubfieldNumOctets++;
            }

            // Checks that the Primary Subfield last octet has FX bit set to 0.
            if ((primarySubfieldValue & 0x01) != 0) {
                return ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT;
            }

            // Now parse the Primary Subfield long value for each bit presence without accessing the buffer.

            // Initializes the Primary Subfield bit presence mask with MSB.
            long mask = 1L << ((primarySubfieldNumOctets << 3) - 1);
            // Initialize the bit presence with the MSB of the Primary Subfield.
            int bit = (maxPrimarySubfieldNumOctets << 3) - 1;

            final Subfield[] subfieldsByBitPresences = this.subfieldsByBitPresences;
            int subfieldNumOctets;
            Subfield subfield;
            do {
                if ((bit & 7) != 0 // Primary Subfield bit presence is not FX.
                        // Primary Subfield bit presence is set to 1.
                        && (primarySubfieldValue & mask) != 0
                        // Subfield exists for the bit presence.
                        && null != (subfield = subfieldsByBitPresences[bit])) {
                    // Parses the number of octets of the Subfield using its format.
                    subfieldNumOctets = subfield.format.parseLength(buffer, off);
                    // Moves off to the next Subfield.
                    off += subfieldNumOctets;
                }

                mask >>>= 1; // Shift right the mask 1 bit position for next bit.
                bit--;       // Decrements the bit position for next primary subfield.
            } while (mask != 0 && bit > 0);

            return off - offset;
        }

        int findDataFieldOffset(
                final Buffer buffer,
                final int offset,
                final int bitPresence,
                final int repIndex,
                final long bitsFieldId) {

            // Parses the Primary Subfield octets and concatenate each octet as long value.
            // The Primary Subfield value is used to parse the bit presence below without accessing the
            // buffer. This is possible because DataFormats.COMPOUND_SUBFIELD_MAX_BIT_PRESENCE will enforce
            // the Compound Data Format to have Primary Subfield that fit within a long value.
            final int maxPrimarySubfieldNumOctets = this.primarySubfieldNumOctets;
            int primarySubfieldNumOctets = 1;
            int off = offset;
            long primarySubfieldValue = buffer.getByte(off++) & 0xff;
            while ((primarySubfieldValue & 0x01) == 1 // Checks that the current octet has FX bit set to 1.
                    // Checks the number of octets of the Primary Subfield is within limit.
                    && primarySubfieldNumOctets <= maxPrimarySubfieldNumOctets) {
                // Concatenates the Primary Subfield octet value.
                primarySubfieldValue = (primarySubfieldValue << 8) | (buffer.getByte(off++) & 0xff);
                // Increments the number of octets of the Primary Subfield.
                primarySubfieldNumOctets++;
            }

            // Checks that the Primary Subfield last octet has FX bit set to 0.
            if ((primarySubfieldValue & 0x01) != 0) {
                return -1;
            }

            // Now parse the Primary Subfield long value for each bit presence without accessing the buffer.

            // Initializes the Primary Subfield bit presence mask with MSB.
            long mask = 1L << ((primarySubfieldNumOctets << 3) - 1);
            // Initialize the bit presence with the MSB of the Primary Subfield.
            int bit = (maxPrimarySubfieldNumOctets << 3) - 1;

            final Subfield[] subfieldsByBitPresences = this.subfieldsByBitPresences;
            int subfieldNumOctets;
            Subfield subfield;
            do {
                if ((bit & 7) != 0 // Primary Subfield bit presence is not FX.
                        // Primary Subfield bit presence is set to 1.
                        && (primarySubfieldValue & mask) != 0
                        // Subfield exists for bit-presence.
                        && null != (subfield = subfieldsByBitPresences[bit])) {

                    if (subfield.bitPresence == bitPresence) {
                        return subfield.format.findDataFieldOffset(buffer, off, repIndex, false, bitsFieldId);
                    }

                    // Parses the number of octets of the Subfield using its format.
                    subfieldNumOctets = subfield.format.parseLength(buffer, off);
                    // Moves offset to the next Subfield.
                    off += subfieldNumOctets;
                }

                mask >>>= 1; // Shift right the mask 1 bit position for next bit.
                bit--;       // Decrements bit-position for next bit.
            } while (mask != 0);

            return -1;
        }

        int getAllDataFields(
                final Buffer buffer,
                final int offset,
                final DataItem item,
                final Subitem subitem,
                final int repIndex,
                final DataFieldValueConsumer consumer) {

            // Initializes the offset of the buffer to start parsing.
            int off = offset;

            // Parses the Primary Subfield octets and concatenate each octet as long value.
            // The Primary Subfield value is used to parse the bit presence below without accessing
            // the buffer. This is possible because PRIMARY_SUBFIELD_MAX_NUM_OCTETS will enforce
            // the Compound Data Format to have Primary Subfield that fit within a long value.
            final int maxPrimarySubfieldNumOctets = this.primarySubfieldNumOctets;
            int primarySubfieldNumOctets = 1;
            long primarySubfieldValue = buffer.getByte(off++) & 0xffL;
            while ((primarySubfieldValue & 0x01) == 1 // Checks that the current octet has FX bit set to 1.
                    // Checks the number of octets of the Primary Subfield is within limit.
                    && primarySubfieldNumOctets <= maxPrimarySubfieldNumOctets) {
                // Concatenates the Primary Subfield octet value.
                primarySubfieldValue = (primarySubfieldValue << 8) | (buffer.getByte(off++) & 0xffL);
                // Increments the number of octets of the Primary Subfield.
                primarySubfieldNumOctets++;
            }

            // Checks that the Primary Subfield last octet has FX bit set to 0.
            if ((primarySubfieldValue & 0x01) != 0) {
                return ERR_COMPOUND_PRIMARY_SUBFIELD_LAST_BIT;
            }

            // Now parse the Primary Subfield long value for each bit presence without accessing the buffer.

            // Initializes the Primary Subfield bit presence mask with MSB.
            long mask = 1L << ((primarySubfieldNumOctets << 3) - 1);
            // Initialize the bit presence with the MSB of the Primary Subfield.
            int bit = (maxPrimarySubfieldNumOctets << 3) - 1;

            final Subfield[] subfieldsByBitPresences = this.subfieldsByBitPresences;
            int subfieldNumOctets;
            Subfield subfield;
            do {
                if ((bit & 7) != 0 // Primary Subfield bit presence is not FX.
                        // Primary Subfield bit presence is set to 1.
                        && (primarySubfieldValue & mask) != 0
                        // Subfield exists for the bit presence.
                        && null != (subfield = subfieldsByBitPresences[bit])) {
                    // Parses the number of octets of the Subfield using its format.
                    subfieldNumOctets = subfield.format.getAllDataFields(buffer, off, item, subitem, subfield, repIndex, consumer);
                    // Moves off to the next Subfield.
                    off += subfieldNumOctets;
                }

                mask >>>= 1; // Shift right the mask 1 bit position for next bit.
                bit--;       // Decrements the bit position for next primary subfield.
            } while (mask != 0 && bit > 0);

            return off - offset;
        }

        void forEachDataField(final DataItem item, final Subitem subitem, final DataFieldConsumer consumer) {
            final Subfield[] subfields = this.subfields;
            final int numSubfields = subfields.length;
            for (int i = 0; i < numSubfields; i++) {
                final Subfield subfield = subfields[i];
                subfield.format.forEachDataField(item, subitem, subfield, consumer);
            }
        }

        void append(final String prefix, final boolean isShort, final Appendable out) throws IOException {
            if (!isShort) {
                final Subfield[] subfieldsByBitPresences = this.subfieldsByBitPresences;
                final BitsField[] primarySubfieldBitPresences = this.primarySubfieldBitPresences;
                final int numPrimarySubfieldBitPresences = primarySubfieldBitPresences.length;
                for (int i = 0; i < numPrimarySubfieldBitPresences; i++) {
                    final DataField bitsPresence = primarySubfieldBitPresences[i].field;
                    final Subfield subfield = subfieldsByBitPresences[bitsPresence.fromBitPosition - 1];
                    out.append(String.format(FMT_FIXED, prefix + '/' + bitsPresence.name,
                            String.format("Subfield #%-2d: %s", subfield.number,
                                    Strings.abbreviate(bitsPresence.description, MAX_WIDTH - 14))));
                    BitsFieldList.append(bitsPresence, out);
                }
            }

            final Subfield[] subfields = this.subfields;
            final int numSubfields = subfields.length;
            for (int i = 0; i < numSubfields; i++) {
                final Subfield subfield = subfields[i];
                subfield.format.append(prefix + '/' + subfield.name, isShort, out);
            }
        }
    }

    static final class Explicit {
        private static final Explicit EMPTY = new Explicit(new Subitem[0]);

        private final BitsField[] itemsIndicatorBitPresences;
        private final int itemsIndicatorNumOctets;
        private final Subitem[] subitems;
        private final Subitem[] subitemsByBitPresences;
        private final BitsFieldList fields;

        private Explicit(final Subitem[] subitems) {
            final int numSubitems = subitems.length;
            itemsIndicatorNumOctets = numSubitems >> 3;
            this.subitems = new Subitem[numSubitems];
            System.arraycopy(subitems, 0, this.subitems, 0, numSubitems);
            itemsIndicatorBitPresences = new BitsField[numSubitems];
            subitemsByBitPresences = new Subitem[numSubitems];
            final List<BitsFieldList> bfl = new ArrayList<>();
            for (int i = 0, j = numSubitems - 1; i < numSubitems; i++, j--) {
                final Subitem subitem = subitems[i];
                final int from = subitem.bitPresence;
                final int number = subitem.number;
                final String name = subitem.name;
                final String desc = subitem.description;
                final long bitsFieldId = bitsFieldId(getDataFieldId(subitem.getId()), from, BITS_FIELD_ENCODING_VALUE);
                final BitsField.Position pos = BitsField.position(itemsIndicatorNumOctets, from, from);
                final BitsValue value0 = BitsValue.of(0, "Absence of Subitem #" + number);
                final BitsValue value1 = BitsValue.of(1, "Presence of Subitem #" + number);
                itemsIndicatorBitPresences[i] = BitsField.bit(bitsFieldId, pos, name, desc, value0, value1);
                subitemsByBitPresences[j] = subitem;
                subitem.format.addBitsFieldsTo(bfl);
            }

            fields = BitsFieldList.create(bfl);
        }

        int findDataFieldOffset(
                final Buffer buffer,
                final int offset,
                final int bitPresence,
                final int repIndex,
                final long bitsFieldId) {

            int off = offset + 1; // Skip LEN field
            final int itemIndicatorValue = buffer.getByte(off++) & 0xff;
            final int msb = (itemsIndicatorNumOctets << 3) - 1;
            final Subitem[] subitemsByBitPresences = this.subitemsByBitPresences;
            int mask = 1 << msb;
            int bit = msb;
            int itemNumOctets;
            Subitem subitem;
            do {
                if ((bit & 7) != 0 // Items Indicator bit is not FX
                        // Items Indicator bit presence is set to 1.
                        && (itemIndicatorValue & mask) != 0
                        // Subitem exists for bit-presence.
                        && null != (subitem = subitemsByBitPresences[bit])) {

                    if (subitem.bitPresence == bitPresence) {
                        return subitem.format.findDataFieldOffset(buffer, off, repIndex, true, bitsFieldId);
                    }

                    // Parses the number of octets of the Subitem using its format.
                    itemNumOctets = subitem.format.parseLength(buffer, off);
                    // Moves index to the next Subitem.
                    off += itemNumOctets;
                }

                mask >>>= 1; // Shift right the mask 1 bit position for next bit.
                bit--;       // Decrements bit-position for next bit.
            } while (mask != 0);

            return -1;
        }

        int getAllDataFields(final Buffer buffer, final int offset, final DataItem item, final int repIndex, final DataFieldValueConsumer consumer) {
            int off = offset;
            // Get the LEN field (1-octet).
            final int length = buffer.getByte(off++) & 0xff;
            // Get the value of the Items Indicator (1-octet).
            final int itemsIndicatorValue = buffer.getByte(off++) & 0xff;
            final int msb = (itemsIndicatorNumOctets << 3) - 1;
            final Subitem[] subitemsByBitPresences = this.subitemsByBitPresences;
            int mask = 1 << msb;
            int bit = msb;
            int itemNumOctets;
            Subitem subitem;
            do {
                if ((bit & 7) != 0 // Items Indicator bit is not FX
                        // Items Indicator bit presence is set to 1.
                        && (itemsIndicatorValue & mask) != 0
                        // Subitem exists for bit-presence.
                        && null != (subitem = subitemsByBitPresences[bit])) {
                    // Retrieves all fields for the current Subitem.
                    itemNumOctets = subitem.format.getAllDataFields(buffer, off, item, subitem, null, repIndex, consumer);
                    // Moves offset to the next Subitem.
                    off += itemNumOctets;
                }

                mask >>>= 1; // Shift right the mask 1 bit position for next bit.
                bit--;       // Decrements bit-position for next bit.
            } while (mask != 0);

            return length;
        }

        void forEachDataField(final DataItem item, final DataFieldConsumer consumer) {
            final Subitem[] subitems = this.subitems;
            final int numSubitems = subitems.length;
            for (int i = 0; i < numSubitems; i++) {
                final Subitem subitem = subitems[i];
                subitem.format.forEachDataField(item, subitem, null, consumer);
            }
        }

        void append(final String prefix, final boolean isShort, final Appendable out) throws IOException {
            if (!isShort) {
                final Subitem[] subitemsByBitPresences = this.subitemsByBitPresences;
                final BitsField[] itemsIndicatorBitPresences = this.itemsIndicatorBitPresences;
                final int numItemsIndicatorBitPresences = itemsIndicatorBitPresences.length;
                for (int i = 0; i < numItemsIndicatorBitPresences; i++) {
                    final DataField bitPresence = itemsIndicatorBitPresences[i].field;
                    final Subitem subitem = subitemsByBitPresences[bitPresence.fromBitPosition - 1];
                    out.append(String.format(FMT_FIXED, prefix + '/' + bitPresence.name,
                            String.format("Subitem #%-2d: %s", subitem.number,
                                    Strings.abbreviate(bitPresence.description, MAX_WIDTH - 14))));
                    BitsFieldList.append(bitPresence, out);
                }
            }

            final Subitem[] subitems = this.subitems;
            final int numSubitems = subitems.length;
            for (int i = 0; i < numSubitems; i++) {
                final Subitem subitem = subitems[i];
                final int subAFmt = getSubAFormat(subitem.format.id);
                if (subAFmt == DATA_FORMAT_FIXED ||
                        subAFmt == DATA_FORMAT_REPETITIVE ||
                        subAFmt == DATA_FORMAT_EXTENDED ||
                        subAFmt == DATA_FORMAT_COMPOUND) {
                    subitem.format.append(prefix + '/' + subitem.name, isShort, out);
                }
            }
        }
    }
}
