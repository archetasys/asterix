/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.archeta.asterix.ASTERIXIds.*;
import static com.archeta.asterix.ASTERIXConstants.FX;
import static com.archeta.asterix.ASTERIXConstants.SPARE;

final class BitsFieldList {
    static final int MAX_WIDTH = 40;
    static final String FMT_FIXED = "%-24s %-" + MAX_WIDTH + "s   ";
    private static final String LF = System.lineSeparator();
    private static final String FMT_BITS_FORMAT = "%2d %2d %-10s";
    private static final String FX0 = "End of Data Item";
    private static final String FX1 = "Extension into next extent";
    private static final String NA = "N/A";
    private static final String INDENT = new String(createIdentBytes(), StandardCharsets.US_ASCII);
    private static final BitsField[] EMPTY = new BitsField[0];

    static BitsFieldList empty(final long id) {
        return new BitsFieldList(id, 0, EMPTY);
    }

    static BitsFieldList create(final long id, final int numOctets, final BitsField... fields) {
        checkBitsFields(id, numOctets, fields);
        final int len = fields.length;
        final BitsField[] copy = new BitsField[len];
        System.arraycopy(fields, 0, copy, 0, len);
        return new BitsFieldList(id, numOctets, copy);
    }

    static BitsFieldList create(final List<BitsFieldList> fields) {
        final int sz = fields.size();
        int n = 0;
        for (int i = 0; i < sz; i++) {
            n += fields.get(i).fields.length;
        }

        final BitsField[] copy = new BitsField[n];
        BitsFieldList bfl;
        for (int i = 0, j = 0; i < sz; i++) {
            bfl = fields.get(i);
            n = bfl.fields.length;
            System.arraycopy(bfl.fields, 0, copy, j, n);
            j += n;
        }

        return new BitsFieldList(0, 0, copy);
    }

    private static byte[] createIdentBytes() {
        final byte[] b = new byte[84];
        Arrays.fill(b, (byte) 0x20);
        return b;
    }

    private static void checkBitsFields(final long id, final int numOctets, final BitsField[] fields) {
        final int len = fields.length;
        if (len < 1) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s bits fields must not empty",
                    getDataFieldIdString(id)));
        }

        final Set<String> names = new HashSet<>(len);
        int nextBit = numOctets << 3;
        for (int i = 0; i < len; i++) {
            final DataField field = fields[i].field;

            // Check if the BitsFields already complete, nextBit = 0, to avoid add BitsField anymore.
            if (nextBit == 0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s can not add more bit at %s, numOctets=%s from=%s to=%s",
                        getBitsFieldIdString(field.id, field.name),
                        i,
                        field.numOctets,
                        field.fromBitPosition,
                        field.toBitPosition));
            }

            // Check the Data Field identifier of the bits field match with the enclosing
            // Data Field identifier.
            if (getDataFieldId(id) != getDataFieldId(field.id)) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bits field at %s data field id not matched",
                        getDataFieldIdString(id), i));
            }

            // Check if the field's number of octets matched.
            if (field.numOctets != numOctets) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bits field at %s must have number of octets %s, numOctets=%s from=%s to=%s",
                        getBitsFieldIdString(field.id, field.name),
                        field.numOctets,
                        field.fromBitPosition,
                        field.toBitPosition));
            }

            // Check that the BitsField to be added is at continuous position, the `from` bit
            // position is equal to nextBit.
            if (field.fromBitPosition != nextBit) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bits field at %s `from` bit position must be at %s, numOctets=%s from=%s to=%s",
                        getBitsFieldIdString(field.id, field.name),
                        i,
                        nextBit,
                        field.numOctets,
                        field.fromBitPosition,
                        field.toBitPosition));
            }

            // Move next bit to the end of BitsField position, to make sure continuous position of
            // adding next BitsField as checked above.
            nextBit = field.toBitPosition - 1;

            // FX and Spare bit fields not need to check for duplicate name.
            if (!FX.equals(field.name) && !SPARE.equals(field.name)) {
                // Check for duplicate name for other bits field (e.g. which has value)
                if (names.contains(field.name)) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s bits field `name` `%s` already used",
                            getBitsFieldIdString(field.id, field.name)));
                }

                names.add(field.name);
            }
        }

        // Check that the BitsFields exhaust the number of octets, nextBit = 0.
        if (nextBit != 0) {
            final DataField field = fields[len - 1].field;
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s last bit is not 1, %s numOctets=%s from=%s to=%s",
                    getDataFieldIdString(id),
                    getBitsFieldIdString(field.id, field.name),
                    field.numOctets,
                    field.fromBitPosition,
                    field.toBitPosition));
        }
    }

    final long id;
    final int numOctets;
    private final BitsField[] fields;

    private BitsFieldList(final long id, final int numOctets, final BitsField[] fields) {
        this.id = id;
        this.numOctets = numOctets;
        this.fields = fields;
    }

    int size() {
        return fields.length;
    }

    BitsField get(final int index) {
        return fields[index];
    }

    void forEachDataField(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part, final DataFieldConsumer consumer) {
        final BitsField[] fields = this.fields;
        final int numFields = fields.length;
        for (int i = 0; i < numFields; i++) {
            consumer.accept(item, subitem, subfield, part, fields[i].field);
        }
    }

    int getAllDataFields(
            final Buffer buffer,
            final int offset,
            final DataItem item,
            final Subitem subitem,
            final Subfield subfield,
            final Part part,
            final int repIndex,
            final DataFieldValueConsumer consumer) {

        final BitsField[] fields = this.fields;
        final int numFields = fields.length;
        for (int i = 0; i < numFields; i++) {
            fields[i].getDataField(buffer, offset, item, subitem, subfield, part, repIndex, consumer);
        }

        return numOctets;
    }

    void append(final String prefix, final boolean isShort, final Appendable out) throws IOException {
        final BitsField[] fields = this.fields;
        final int numFields = fields.length;
        if (isShort) {
            for (int i = 0; i < numFields; i++) {
                final BitsField field = fields[i];
                final String name = field.field.name;
                if (!FX.equals(name) && !SPARE.equals(name)) {
                    out.append(String.format("%-24s", prefix + '/' + name)).append(' ')
                            .append('#').append(' ').append(field.field.description).append(LF);
                }
            }
        } else {
            for (int i = 0; i < numFields; i++) {
                final BitsField field = fields[i];
                out.append(String.format(FMT_FIXED, prefix + '/' + field.field.name,
                        Strings.abbreviate(field.field.description, MAX_WIDTH)));
                append(field.field, out);
            }
        }
    }

    static void append(final DataField field, final Appendable output) throws IOException {
        final BitsFieldEncoding encoding = field.encoding;
        output.append(String.format(FMT_BITS_FORMAT, field.fromBitPosition, field.toBitPosition, encoding.getDisplayName()));
        switch (encoding) {
            case MEASURE_GRAYCODE:
            case MEASURE_SIGNED:
            case MEASURE_UNSIGNED: {
                output.append("LSB = ").append(Double.toString(field.lsb));
                final String symbol = field.unit.symbol();
                final boolean hasSymbol = !symbol.isEmpty();
                if (hasSymbol) {
                    output.append(symbol);
                }

                output.append(',').append(' ')
                        .append("Range = [").append(Double.toString(field.min)).append(',').append(' ')
                        .append(Double.toString(field.max)).append(']');

                if (hasSymbol) {
                    output.append(symbol);
                }
                break;
            }
            case VALUE: {
                final BitsValue bv0 = field.getBitsValue(0);
                final BitsValue bv1 = field.getBitsValue(1);
                output.append(Integer.toString(bv0.value()))
                        .append(' ').append(Strings.abbreviate(bv0.description(), MAX_WIDTH))
                        .append(LF).append(INDENT).append(Integer.toString(bv1.value()))
                        .append(' ').append(Strings.abbreviate(bv1.description(), MAX_WIDTH));
                break;
            }
            case VALUES: {
                for (int i = 0, sz = field.getNumBitsValue(); i < sz; i++) {
                    final BitsValue bv = field.getBitsValue(i);
                    if (i > 0) {
                        output.append(LF).append(INDENT);
                    }

                    output.append(Integer.toString(bv.value())).append(' ')
                            .append(Strings.abbreviate(bv.description(), MAX_WIDTH));
                }
                break;
            }
            case SPARE:
                output.append(field.description);
                break;
            case FX:
                output.append('0').append(' ').append(FX0).append(LF)
                        .append(INDENT).append('1').append(' ').append(FX1);
                break;
            default:
                output.append(NA);
                break;
        }

        output.append(LF);
    }
}
