/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;

/**
 * Bits field.
 * <p>
 * The octets are numbered from left to right from 1 to {@code N}. Big endian bits are numbered
 * from right to left from 1 to {@code N*8}. Below is an example of a bits with from bit
 * position 12, to bit position 8, in octets with length 3.
 * <pre>{@code
 * +---------------+---------------+---------------+
 * |       0       |       1       |       2       | --> Octet Index (left to right)
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *          2                   1
 * |4|3|2|1|0|9|8|7|6|5|4|3|2|1|0|9|8|7|6|5|4|3|2|1| <-- Bit Position (right to left)
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *                         |         |
 *                      from=12    to=8
 * |<---------------- NumOctets=3 ---------------->|
 * }</pre>
 */
final class BitsField {
    private static final char[] NAME_UPPERCASE = new char[256];

    private static final double GRAYCODE_LSB = 100.0;
    private static final double GRAYCODE_MIN = -1200.0;
    private static final double GRAYCODE_MAX = 126700.0;

    private static final int OCTAL_WIDTH = 5;
    private static final int OCTAL_DECODE_MASK = ~(~0 << BITS_FIELD_ENCODING_OCTAL_NUM_BITS);
    private static final String[] OCTAL_DECODE_TABLE = new String[1 << BITS_FIELD_ENCODING_OCTAL_NUM_BITS];

    private static final char[] IA5_DECODE_TABLE = {
            ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', ' ', ' ', ' ', ' ', ' '
    };

    private static final BitsValue[] EMPTY_VALUES = new BitsValue[0];
    private static final String SPARE_DESC = "Spare bit set to 0";
    private static final String FX_DESC = "Field Extension";

    static {
        // Numeric characters: 0..9
        for (int i = 48; i < 58; i++) {
            NAME_UPPERCASE[i] = (char) i;
        }

        // Uppercase characters: A..Z
        for (int i = 65; i < 91; i++) {
            NAME_UPPERCASE[i] = (char) i;
        }

        // Underscore character.
        NAME_UPPERCASE[0x5f] = '_';

        for (int i = 0; i < OCTAL_DECODE_TABLE.length; i++) {
            OCTAL_DECODE_TABLE[i] = String.format("0%04o", i);
        }
    }

    static String getOctalString(final int value) {
        return OCTAL_DECODE_TABLE[value & OCTAL_DECODE_MASK];
    }

    static Position position(final int numOctets, final int from, final int to) {
        return new Position(numOctets, from, to);
    }

    static BitsField unsigned(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.UNSIGNED, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField signed(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.SIGNED, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField umeasure(final long id, final Position pos, final String name,
                              final double lsb, final UnitOfMeasure unit, final String description) {
        final double max = pos.getMaxValue(true) * lsb;
        return new BitsField(BitsFieldEncoding.MEASURE_UNSIGNED, id, name, pos, description, 0, EMPTY_VALUES, lsb, unit, 0, max);
    }

    static BitsField umeasure(final long id, final Position pos, final String name,
                              final double lsb, final UnitOfMeasure unit, final double max, final String description) {
        return new BitsField(BitsFieldEncoding.MEASURE_UNSIGNED, id, name, pos, description, 0, EMPTY_VALUES, lsb, unit, 0, max);
    }

    static BitsField smeasure(final long id, final Position pos, final String name,
                              final double lsb, final UnitOfMeasure unit, final String description) {
        final double min = pos.getMinValue() * lsb;
        final double max = pos.getMaxValue(false) * lsb;
        return new BitsField(BitsFieldEncoding.MEASURE_SIGNED, id, name, pos, description, 0, EMPTY_VALUES, lsb, unit, min, max);
    }

    static BitsField smeasure(final long id, final Position pos, final String name,
                              final double lsb, final UnitOfMeasure unit,
                              final double min, final double max, final String description) {
        return new BitsField(BitsFieldEncoding.MEASURE_SIGNED, id, name, pos, description, 0, EMPTY_VALUES, lsb, unit, min, max);
    }

    static BitsField graycode(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.MEASURE_GRAYCODE, id, name, pos, description,
                0, EMPTY_VALUES, GRAYCODE_LSB, UnitOfMeasure.ALTITUDE_FT, GRAYCODE_MIN, GRAYCODE_MAX);
    }

    static BitsField hex(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.HEX, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField ia5(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.IA5, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField octal(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.OCTAL, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField ascii(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.ASCII, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField asciichar(final long id, final Position pos, final String name, final String description) {
        return new BitsField(BitsFieldEncoding.ASCII_CHAR, id, name, pos, description, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField spare(final long id, final Position pos) {
        return new BitsField(BitsFieldEncoding.SPARE, id, SPARE, pos, SPARE_DESC, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField fx(final long id, final Position pos) {
        return new BitsField(BitsFieldEncoding.FX, id, FX, pos, FX_DESC, 0, EMPTY_VALUES, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField bit(final long id, final Position pos, final String name, final String description,
                         final BitsValue value0, final BitsValue value1) {
        final BitsValue[] values = {value0, value1};
        return new BitsField(BitsFieldEncoding.VALUE, id, name, pos, description, values.length, values, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField bits(final long id, final Position pos, final String name, final String description,
                          final BitsValue value0, final BitsValue value1, final BitsValue value2, final BitsValue value3) {
        final BitsValue[] values = {value0, value1, value2, value3};
        return new BitsField(BitsFieldEncoding.VALUES, id, name, pos, description, values.length, values, 0, UnitOfMeasure.NONE, 0, 0);
    }

    static BitsField bits(final long id, final Position pos, final String name, final String description,
                          final BitsValue value0, final BitsValue value1, final BitsValue value2, final BitsValue value3,
                          final BitsValue... values) {

        if (values.length == 0) {
            final BitsValue[] vals = {value0, value1, value2, value3};
            return new BitsField(BitsFieldEncoding.VALUES, id, name, pos, description, vals.length, vals, 0, UnitOfMeasure.NONE, 0, 0);
        }

        final int numValues = 4 + values.length;
        final int sz = 1 << pos.numBits;
        if (numValues > sz) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `numValues` %s is not fit in %s bits",
                    getBitsFieldIdString(id, name), numValues, pos.numBits));
        }

        final BitsValue[] vals = new BitsValue[sz];
        vals[0] = value0;
        vals[1] = value1;
        vals[2] = value2;
        vals[3] = value3;
        System.arraycopy(values, 0, vals, 4, numValues - 4);
        for (int j = numValues; j < sz; j++) {
            vals[j] = BitsValue.of(j, "Not Used");
        }

        return new BitsField(BitsFieldEncoding.VALUES, id, name, pos, description, numValues, vals, 0, UnitOfMeasure.NONE, 0, 0);
    }

    /**
     * The data field which wraps this bits field.
     */
    final DataField field;

    /**
     * The bits from position within the octets.
     */
    private final int from;

    /**
     * The bits to position within the octets.
     */
    private final int to;

    // Pre-calculated constant values that depends on the bit positions:

    /**
     * The octet index of bits within the octets to begin from.
     */
    private final int octetIndex;

    /**
     * The number of bits: from - to + 1, pre-calculated for reuse.
     */
    private final int numBits;

    /**
     * The maximum value of the bits.
     */
    private final long lmax;

    /**
     * Indicates that the bits value is unsigned.
     */
    private final boolean unsigned;

    /**
     * LSB used by BITS_FIELD_ENCODING_MEASURE_SIGNED, BITS_FIELD_ENCODING_MEASURE_UNSIGNED,
     * or BITS_FIELD_ENCODING_GRAYCODE.
     */
    private final double lsb;

    /**
     * Indicates the bits are at a single octet, for example 15:12.
     */
    private final boolean atSingleOctet;

    /**
     * Indicates the bits are at octet boundary, for example 24:8.
     */
    private final boolean atOctetBoundary;

    /**
     * The bits shift from the beginning of the bit position.
     */
    private final int fromBitIndexAtFirstOctet;

    /**
     * The bit index from the end of the bit position.
     */
    private final int toBitIndexAtLastOctet;

    /**
     * The bits mask used for bit positions in a single octet, atSingleOctet = true.
     */
    private final int atSingleOctetBitMask;

    private final int width;
    private final int group;
    private final int encoding;
    private final int valuesIndexMask;
    private final BitsValue[] values;

    private BitsField(
            final BitsFieldEncoding encoding,
            final long id,
            final String name,
            final Position pos,
            final String description,
            final int numValues,
            final BitsValue[] values,
            final double lsb,
            final UnitOfMeasure unit,
            final double min,
            final double max) {

        final int fromId = getBitsFieldFromPosition(id);
        if (pos.from != fromId) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `from` bit position not matched with from bits field id, %s",
                    getBitsFieldIdString(id, name), pos));
        }

        checkName(name, id, false);
        checkEncoding(encoding, name, id);

        if (Strings.isBlank(description)) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `description` must not null nor blank",
                    getBitsFieldIdString(id, name)));
        }

        this.encoding = encoding.value();
        switch (this.encoding) {
            case BITS_FIELD_ENCODING_UNSIGNED:
            case BITS_FIELD_ENCODING_SIGNED:
                break;
            case BITS_FIELD_ENCODING_VALUE:
                Position.checkEncodingValue(pos, values, numValues, name, id);
                break;
            case BITS_FIELD_ENCODING_VALUES:
                Position.checkEncodingValues(pos, values, numValues, name, id);
                break;
            case BITS_FIELD_ENCODING_MEASURE_UNSIGNED:
                Position.checkEncodingMeasureUnsigned(pos, lsb, unit, max, name, id);
                break;
            case BITS_FIELD_ENCODING_MEASURE_SIGNED:
                Position.checkEncodingMeasureSigned(pos, lsb, unit, min, max, name, id);
                break;
            case BITS_FIELD_ENCODING_MEASURE_GRAYCODE:
                Position.checkEncodingGraycode(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_IA5:
                Position.checkEncodingIA5(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_HEX:
                Position.checkEncodingHex(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_OCTAL:
                Position.checkEncodingOctal(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_ASCII:
                Position.checkEncodingAscii(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_ASCII_CHAR:
                Position.checkEncodingAsciiChar(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_SPARE:
                Position.checkEncodingSpare(pos, name, id);
                break;
            case BITS_FIELD_ENCODING_FX:
                Position.checkEncodingFX(pos, name, id);
                break;
            default:
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `encoding` unsupported %s",
                        getBitsFieldIdString(id, name), encoding));
        }

        final int numOctets = pos.numOctets;
        this.from = pos.from;
        this.to = pos.to;
        this.values = values;
        this.valuesIndexMask = ~(~0 << pos.numBits);
        this.lsb = lsb;

        unsigned = this.encoding != BITS_FIELD_ENCODING_SIGNED &&
                this.encoding != BITS_FIELD_ENCODING_MEASURE_SIGNED &&
                this.encoding != BITS_FIELD_ENCODING_MEASURE_GRAYCODE;

        octetIndex = ((numOctets << 3) - from) >> 3;
        numBits = from - to + 1;

        final long lmin;
        if (numBits == BITS_FIELD_MAX_NUM_BITS) {
            lmin = unsigned ? 0 : Long.MIN_VALUE;
            lmax = Long.MAX_VALUE; // Saturated with 63-bits max long value.
        } else {
            final long maxv = ~(~0L << numBits);
            if (unsigned) {
                lmin = 0;
                lmax = maxv;
            } else {
                lmax = maxv >> 1;
                lmin = -lmax - 1;
            }
        }

        final int numBitsOctets = numBits >> 3;
        final int fromBit = from - 1;
        final int toBit = to - 1;
        atSingleOctet = numBits < 8 && (fromBit >> 3) == (toBit >> 3);
        atOctetBoundary = (from & 7) == 0 && (to & 7) == 1;
        fromBitIndexAtFirstOctet = fromBit & 7;
        toBitIndexAtLastOctet = toBit & 7;
        atSingleOctetBitMask = ~(~0 << numBits);
        group = numBits / BITS_FIELD_ENCODING_IA5_CHAR_GROUP_NUM_BITS;
        if (this.encoding == BITS_FIELD_ENCODING_ASCII) {
            width = numBits >> 3;
        } else if (this.encoding == BITS_FIELD_ENCODING_IA5) {
            width = numBits / BITS_FIELD_ENCODING_IA5_CHAR_GROUP;
        } else if (this.encoding == BITS_FIELD_ENCODING_OCTAL) {
            width = OCTAL_WIDTH;
        } else {
            width = numBitsOctets;
        }

        field = new DataField(id, name, description, numOctets, from, to, numBits, lmin, lmax, unsigned,
                width, group, encoding, unit, lsb, min, max, numValues, values);
    }

    boolean getBoolean(final Buffer buffer, final int offset) {
        return ((buffer.getByte(offset + octetIndex) >> fromBitIndexAtFirstOctet) & 1) == 1;
    }

    byte getByte(final Buffer buffer, final int offset) {
        return buffer.getByte(offset + octetIndex);
    }

    BitsValue getBitsValue(final Buffer buffer, final int offset) {
        return encoding == BITS_FIELD_ENCODING_VALUE || encoding == BITS_FIELD_ENCODING_VALUES ?
                values[(int) (getLong(buffer, offset) & valuesIndexMask)] : BitsValue.ZERO;
    }

    long getLong(final Buffer buffer, final int offset) {
        if (numBits == 1) {
            return (buffer.getByte(offset + octetIndex) >> fromBitIndexAtFirstOctet) & 1;
        } else {
            final long bits = getBits(buffer, offset);
            return unsigned || bits <= lmax ? bits : -((~bits & lmax) + 1);
        }
    }

    double getDouble(final Buffer buffer, final int offset) {
        if (encoding == BITS_FIELD_ENCODING_MEASURE_GRAYCODE) {
            return getGrayCoded(buffer, offset);
        } else {
            return getLong(buffer, offset) * lsb;
        }
    }

    String getString(final Buffer buffer, final int offset) {
        if (encoding == BITS_FIELD_ENCODING_HEX) {
            return HexDump.from(getLong(buffer, offset), width);
        } else if (encoding == BITS_FIELD_ENCODING_OCTAL) {
            return OCTAL_DECODE_TABLE[(int) (getLong(buffer, offset) & 0xfff)];
        } else if (encoding == BITS_FIELD_ENCODING_ASCII) {
            return buffer.getStringAsciiNoLength(offset + octetIndex, width);
        } else if (encoding == BITS_FIELD_ENCODING_IA5) {
            final char[] chars = new char[width];
            for (int i = 0, j = offset + octetIndex, k = 0; i < group; i++, j += 4) {
                final long v = buffer.getUInt48(j);
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 42) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 36) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 30) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 24) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 18) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 12) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) ((v >> 6) & 0x3f)];
                chars[k++] = IA5_DECODE_TABLE[(int) (v & 0x3f)];
            }

            return new String(chars);
        } else {
            return "";
        }
    }

    Appendable getString(final Buffer buffer, final int offset, final Appendable out) throws IOException {
        if (encoding == BITS_FIELD_ENCODING_HEX) {
            HexDump.append(getLong(buffer, offset), width, out);
        } else if (encoding == BITS_FIELD_ENCODING_OCTAL) {
            out.append(OCTAL_DECODE_TABLE[(int) (getLong(buffer, offset) & 0xfff)]);
        } else if (encoding == BITS_FIELD_ENCODING_ASCII) {
            return buffer.getStringAsciiNoLength(offset + octetIndex, width, out);
        } else if (encoding == BITS_FIELD_ENCODING_IA5) {
            for (int i = 0, j = offset + octetIndex; i < group; i++, j += 4) {
                final long v = buffer.getUInt48(j);
                out.append(IA5_DECODE_TABLE[(int) ((v >> 42) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 36) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 30) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 24) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 18) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 12) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) ((v >> 6) & 0x3f)])
                        .append(IA5_DECODE_TABLE[(int) (v & 0x3f)]);
            }
        }

        return out;
    }

    long getBits(final Buffer buffer, final int offset) {
        // The byte index of the begin-octets.
        final int fromByteIndex = offset + octetIndex;

        if (atSingleOctet) {
            return (buffer.getByte(fromByteIndex) >> toBitIndexAtLastOctet) & atSingleOctetBitMask;
        }

        if (atOctetBoundary) {
            return buffer.getBits(fromByteIndex, numBits);
        }

        // The bit indices and octet indices are from right to left, starting from 0.
        final int fromBitIndex = from - 1;               // The index of the beginning position of the bits.
        final int toBitIndex = to - 1;                   // The index of the end position of the bits.
        final int fromBitOctetIndex = fromBitIndex >> 3; // The index of the octet of the `from` bit position.
        final int toBitOctetIndex = toBitIndex >> 3;     // The index of the octet of the `to` bit position.

        // Retrieves the hi bits part, for example: 12:4/24
        // +---------------+---------------+---------------+
        // |       2       |       1       |       0       |
        // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
        //          2                   1
        // |4|3|2|1|0|9|8|7|6|5|4|3|2|1|0|9|8|7|6|5|4|3|2|1|
        // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
        //                          |               |
        //                       from=12          to=4
        //                          |--hi-| |--lo---|
        //                           12:9      8:4
        // |<---------------- NumOctets=3 ---------------->|
        // This will get bits 12:9.
        final long hi = buffer.getBits(fromByteIndex, fromBitIndexAtFirstOctet + 1, 1);

        // The end bit is at the end of an octet.
        if (toBitIndexAtLastOctet == 0) {
            final int numLowBits = (fromBitOctetIndex - toBitOctetIndex) << 3;
            return (hi << numLowBits) | buffer.getBits(fromByteIndex + 1, numLowBits);
        }

        // The end bit is at the next octet of the beginning bit, from:to span 2 octets.
        if (toBitOctetIndex == fromBitOctetIndex - 1) {
            // Retrieves the lo bits part, for example this will get bits 7:4.
            final int lo = buffer.getBits(fromByteIndex + 1, 8, toBitIndexAtLastOctet + 1) >> toBitIndexAtLastOctet;
            return (hi << (8 - toBitIndexAtLastOctet)) | lo;
        }

        final int numLowBits = 8 - toBitIndexAtLastOctet;
        final int numMidOctets = fromBitOctetIndex - toBitOctetIndex - 1;
        final int numMidBits = numMidOctets << 3;
        final long mid = buffer.getBits(fromByteIndex + 1, numMidBits) << numLowBits;
        final int lo = buffer.getBits(fromByteIndex + 1 + numMidOctets, 8, toBitIndexAtLastOctet + 1) >> toBitIndexAtLastOctet;
        return (hi << (numMidBits + numLowBits)) | mid | lo;
    }

    void getDataField(
            final Buffer buffer,
            final int offset,
            final DataItem item,
            final Subitem subitem,
            final Subfield subfield,
            final Part part,
            final int repIndex,
            final DataFieldValueConsumer consumer) {

        switch (encoding) {
            case BITS_FIELD_ENCODING_UNSIGNED:
            case BITS_FIELD_ENCODING_SIGNED:
            case BITS_FIELD_ENCODING_HEX:
            case BITS_FIELD_ENCODING_OCTAL:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getLong(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_VALUE:
            case BITS_FIELD_ENCODING_VALUES:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getBitsValue(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_MEASURE_UNSIGNED:
            case BITS_FIELD_ENCODING_MEASURE_SIGNED:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getDouble(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_MEASURE_GRAYCODE:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getGrayCoded(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_IA5:
            case BITS_FIELD_ENCODING_ASCII:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getString(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_ASCII_CHAR:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getByte(buffer, offset));
                break;
            case BITS_FIELD_ENCODING_SPARE:
            case BITS_FIELD_ENCODING_FX:
                consumer.accept(item, subitem, subfield, part, repIndex, field, getBoolean(buffer, offset));
                break;
            default:
                break;
        }
    }

    private double getGrayCoded(final Buffer buffer, final int offset) {
        final long value = getLong(buffer, offset);

        // ASTERIX Code (AST):
        //  11 10  9  8  7  6  5  4  3  2  1  0
        //  C1 A1 C2 A2 C4 A4 B1 D1 B2 D2 B4 D4
        // Conversion input (INP):
        //  11 10  9  8  7  6  5  4  3  2  1  0
        //  D1 D2 D4 A1 A2 A4 B1 B2 B4 C1 C2 C4
        final int v = (int) (value) & 0x7ff; // D1 is not used
        final int val =                        //    AST->INP
                ((v & 0b000000000100) << 8)        // D2  2 -> 10
                        | ((v & 0b000000000001) << 9)  // D4  0 -> 9
                        | ((v & 0b010000000000) >> 2)  // A1 10 -> 8
                        | ((v & 0b000100000000) >> 1)  // A2  8 -> 7
                        | ((v & 0b000001000000))       // A4  6 -> 6
                        | ((v & 0b000000100000))       // B1  5 -> 5
                        | ((v & 0b000000001000) << 1)  // B2  3 -> 4
                        | ((v & 0b000000000010) << 2)  // B4  1 -> 3
                        //| ((v & 0b100000000000) >> 9)// C1 11 -> 2
                        | ((v & 0b001000000000) >> 8)  // C2  9 -> 1
                        | ((v & 0b000010000000) >> 7); // C4  7 -> 0

        int result = val ^ (val >> 8);
        result ^= (val >> 4);
        result ^= (val >> 2);
        result ^= (val >> 1);
        result -= (((result >> 8) * 6) + (((result & 15) / 5) << 1));
        return (result - 13) * GRAYCODE_LSB;
    }

    static void checkEncoding(final BitsFieldEncoding encoding, final String name, final long id) {
        final int enc = getBitsFieldEncoding(id);
        if (enc != encoding.value()) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `encoding` must be %s, encoding=%s",
                    getBitsFieldIdString(id, name),
                    getBitsFieldEncodingString(encoding.value()),
                    getBitsFieldEncodingString(enc)));
        }
    }

    static void checkName(final String name, final long id, final boolean bitPresence) {
        if (null == name) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `name` must not null",
                    getIdString(id, bitPresence)));
        }

        final int length = name.length();
        if (length < BITS_FIELD_MIN_NAME_LENGTH || length > BITS_FIELD_MAX_NAME_LENGTH) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `name` length must be in range [%s,%s], length=%s",
                    getIdString(id, bitPresence),
                    BITS_FIELD_MIN_NAME_LENGTH,
                    BITS_FIELD_MAX_NAME_LENGTH,
                    length));
        }

        final char ch0 = name.charAt(0);
        if ((ch0 < 'A' || ch0 > 'Z') && (ch0 < '0' || ch0 > '9')) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `name` first character must be alphanumeric, name[0]=`%s`",
                    getIdString(id, bitPresence), ch0));
        }

        for (int i = 1; i < length; i++) {
            if (NAME_UPPERCASE[name.charAt(i) & 0xff] == 0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `name` must contains uppercase or numeric chars, name=`%s`",
                        getIdString(id, bitPresence), name));
            }
        }

        if (name.charAt(length - 1) == '_') {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `name` must not end with underscore character, name=`%s`",
                    getIdString(id, bitPresence), name));
        }
    }

    private static String getIdString(final long id, final boolean bitPresence) {
        return bitPresence ? getDataFieldIdString(id) : getBitsFieldIdString(id);
    }

    static final class Position {
        private static final double MAX_VALUE = 2.0 * Long.MAX_VALUE;

        final int numOctets;
        final int numBits;
        final int from;
        final int to;
        final String description;

        private Position(final int numOctets, final int from, final int to) {
            if (numOctets < 1 || numOctets > DATA_FIELD_MAX_NUM_OCTETS) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "`numOctets` must be in range [1,%s], numOctets=%s",
                        DATA_FIELD_MAX_NUM_OCTETS, numOctets));
            }

            if (from < 1) {
                throw new ASTERIXFormatException(Fmt.sprintf("`from` bit position must be positive, from=%s", from));
            }

            if (to < 1) {
                throw new ASTERIXFormatException(Fmt.sprintf("`to` bit position must be positive, to=%s", to));
            }

            final int maxBitPosition = numOctets << 3;
            if (from > maxBitPosition) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "`from` bit position must be in range [1,%s], numOctets=%s from=%s",
                        maxBitPosition, numOctets, from));
            }

            if (to > from) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "`to` bit position must be in range [1,%s], numOctets=%s from=%s to=%s",
                        from, numOctets, from, to));
            }

            final int numBits = from - to + 1;
            if (numBits > BITS_FIELD_MAX_NUM_BITS) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "maximum number of bits is %s, numOctets=%s from=%s to=%s numBits=%s",
                        BITS_FIELD_MAX_NUM_BITS, numOctets, from, to, numBits));
            }

            this.numOctets = numOctets;
            this.numBits = numBits;
            this.from = from;
            this.to = to;
            this.description = "numOctets=" + numOctets + " from=" + from + " to=" + to + " numBits=" + numBits;
        }

        double getMinValue() {
            if (numBits == BITS_FIELD_MAX_NUM_BITS) {
                return Long.MIN_VALUE;
            }

            final long max = ~(~0L << numBits);
            return -(max >> 1) - 1;
        }

        double getMaxValue(final boolean unsigned) {
            if (numBits == BITS_FIELD_MAX_NUM_BITS) {
                return unsigned ? MAX_VALUE : Long.MAX_VALUE;
            }

            final long max = ~(~0L << numBits);
            return unsigned ? max : max >> 1;
        }

        public String toString() {
            return description;
        }

        static void checkAtOctetBoundary(final Position pos, final String name, final long id) {
            final boolean notAtOctetBoundary = ((pos.from - 1) & 7) != 7 || ((pos.to - 1) & 7) != 0;
            if (notAtOctetBoundary) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bit position must be at octet boundary, %s",
                        getBitsFieldIdString(id, name), pos));
            }
        }

        static void checkEncodingHex(final Position pos, final String name, final long id) {
            // Check bits positions are in octet boundary.
            checkAtOctetBoundary(pos, name, id);

            final int numOctets = pos.numBits >> 3;

            // Check the number of octets of bits are between 1 and 8.
            if (numOctets < 1 || numOctets > 8) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `numOctets` must be in range [1,8], numOctets=%s %s",
                        getBitsFieldIdString(id, name), numOctets, pos));
            }
        }

        static void checkEncodingOctal(final Position pos, final String name, final long id) {
            if (pos.numBits != 5 && pos.numBits != BITS_FIELD_ENCODING_OCTAL_NUM_BITS) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s number of bits must be 5 or %s, numBits=%s %s",
                        getBitsFieldIdString(id, name),
                        BITS_FIELD_ENCODING_OCTAL_NUM_BITS,
                        pos.numBits,
                        pos));
            }

            // Check that the to bit position is at the end of an octet.
            if (((pos.to - 1) & 7) != 0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `to` bit position must be at the end of an octet, %s",
                        getBitsFieldIdString(id, name), pos));
            }
        }

        static void checkEncodingAscii(final Position pos, final String name, final long id) {
            // Check bits positions are in octet boundary.
            checkAtOctetBoundary(pos, name, id);

            // Check number of bits must be multiple of 8, each Ascii character is represented with 8 bits.
            if (((pos.numBits - 1) & 7) != 7) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s number of bits must be multiple of 8, numBits=%s %s",
                        getBitsFieldIdString(id, name), pos.numBits, pos));
            }
        }

        static void checkEncodingAsciiChar(final Position pos, final String name, final long id) {
            // Check bits positions are in octet boundary.
            checkAtOctetBoundary(pos, name, id);

            // Check that the number of bits must be 8.
            if (pos.numBits != BITS_FIELD_ENCODING_ASCII_CHAR_NUM_BITS) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s number of bits must be %s, numBits=%s %s",
                        getBitsFieldIdString(id, name),
                        BITS_FIELD_ENCODING_ASCII_CHAR_NUM_BITS,
                        pos.numBits,
                        pos));
            }
        }

        static void checkEncodingIA5(final Position pos, final String name, final long id) {
            // Check bits positions are in octet boundary.
            checkAtOctetBoundary(pos, name, id);

            // Check bits are grouped per 6 characters, each IA5 character is represented with 6 bits.
            if ((pos.numBits % BITS_FIELD_ENCODING_IA5_CHAR_GROUP_NUM_BITS) != 0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s number of bits must be multiple of %s (6 chars), numBits=%s %s",
                        getBitsFieldIdString(id, name),
                        BITS_FIELD_ENCODING_IA5_CHAR_GROUP_NUM_BITS,
                        pos.numBits,
                        pos));
            }
        }

        static void checkEncodingValue(
                final Position pos,
                final BitsValue[] values,
                final int numValues,
                final String name,
                final long id) {

            // Check single bit position.
            if (pos.from != pos.to) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `from` bit position must be equals `to` bit position, %s",
                        getBitsFieldIdString(id, name), pos));
            }

            // Check values is not null and has length 2.
            if (null == values || values.length != 2) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `values` must not null and must have length 2, values=%s %s",
                        getBitsFieldIdString(id, name), null == values ? "null" : values.length, pos));
            }

            // Check numValues is 2.
            if (numValues != 2) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `numValues` must be 2, numValues=%s %s",
                        getBitsFieldIdString(id, name), numValues, pos));
            }

            final BitsValue value0 = values[0];
            final BitsValue value1 = values[1];

            // Check value0 must not null.
            if (null == value0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s value0 must not null, %s",
                        getBitsFieldIdString(id, name), pos));
            }

            // Check value1 must not null.
            if (null == value1) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s value1 must not null, %s",
                        getBitsFieldIdString(id, name), pos));
            }

            // Check that value0 is indeed has value 0.
            if (value0.value() != 0) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bits value must be 0 for value0, value0=%s %s",
                        getBitsFieldIdString(id, name), value0.value(), pos));
            }

            // Check that value1 is indeed has value 1.
            if (value1.value() != 1) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s bits value must be 1 for value1, value1=%s %s",
                        getBitsFieldIdString(id, name), value1.value(), pos));
            }
        }

        static void checkEncodingValues(
                final Position pos,
                final BitsValue[] values,
                final int numValues,
                final String name,
                final long id) {

            final int sz = 1 << pos.numBits;
            if (numValues > sz) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `numValues` %s is not fit in %s bits",
                        getBitsFieldIdString(id, name), numValues, pos.numBits));
            }

            if (values.length != sz) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `values` must have length %s, length=%s %s",
                        getBitsFieldIdString(id, name), sz, values.length, pos));
            }

            BitsValue bv;
            for (int i = 0; i < sz; i++) {
                bv = values[i];
                if (null == bv) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s bits value at %s must not null",
                            getBitsFieldIdString(id, name), i));
                }

                if (bv.value() != i) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s bits value at %s must be %s, value=%s",
                            getBitsFieldIdString(id, name), i, i, bv.value()));
                }
            }
        }

        static void checkEncodingGraycode(final Position pos, final String name, final long id) {
            // Check number of bits must be 12.
            if (pos.numBits != BITS_FIELD_ENCODING_GRAYCODE_NUM_BITS) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s number of bits must be %s, numBits=%s %s",
                        getBitsFieldIdString(id, name),
                        BITS_FIELD_ENCODING_GRAYCODE_NUM_BITS,
                        pos.numBits,
                        pos));
            }
        }

        static void checkEncodingMeasureSigned(
                final Position pos,
                final double lsb,
                final UnitOfMeasure unit,
                final double min,
                final double max,
                final String name,
                final long id) {

            if (null == unit) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `unit` must not null",
                        getBitsFieldIdString(id, name)));
            }

            if (lsb > 0) {
                final double scaledMin = pos.getMinValue() * lsb;
                if (min < scaledMin) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s `min` must be <= %s, min=%s lsb=%s %s",
                            getBitsFieldIdString(id, name), scaledMin, min, lsb, pos));
                }

                final double scaledMax = pos.getMaxValue(false) * lsb;
                if (max > scaledMax) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s `max` must be <= %s, max=%s lsb=%s %s",
                            getBitsFieldIdString(id, name), scaledMax, max, lsb, pos));
                }

                if (!(max > min)) {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s `max` must be >= min, min=%s max=%s lsb=%s %s",
                            getBitsFieldIdString(id, name), min, max, lsb, pos));
                }
            } else {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `lsb` must be > 0, lsb=%s %s",
                        getBitsFieldIdString(id, name), lsb, pos));
            }
        }

        static void checkEncodingMeasureUnsigned(
                final Position pos,
                final double lsb,
                final UnitOfMeasure unit,
                final double max,
                final String name,
                final long id) {

            if (null == unit) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `unit` must not null",
                        getBitsFieldIdString(id, name)));
            }

            if (lsb > 0) {
                if (max > 0) {
                    final double scaledMax = pos.getMaxValue(true) * lsb;
                    if (max > scaledMax) {
                        throw new ASTERIXFormatException(Fmt.sprintf(
                                "%s `max` must be <= %s, max=%s lsb=%s %s",
                                getBitsFieldIdString(id, name), scaledMax, max, lsb, pos));
                    }
                } else {
                    throw new ASTERIXFormatException(Fmt.sprintf(
                            "%s `max` must be > 0, max=%s %s",
                            getBitsFieldIdString(id, name), max, pos));
                }
            } else {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `lsb` must be > 0, lsb=%s %s",
                        getBitsFieldIdString(id, name), lsb, pos));
            }
        }

        static void checkEncodingFX(final Position pos, final String name, final long id) {
            // Check single bit.
            if (pos.from != pos.to) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `from` bit position must be equals `to` bit position, %s",
                        getBitsFieldIdString(id, name), pos));
            }

            // Check that the bit position is at the end of an octet.
            if ((pos.from & 0x7) != 1) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `from` bit position must be at the end of an octet, %s",
                        getBitsFieldIdString(id, name), pos));
            }
        }

        static void checkEncodingSpare(final Position pos, final String name, final long id) {
            // Check single bit.
            if (pos.from != pos.to) {
                throw new ASTERIXFormatException(Fmt.sprintf(
                        "%s `from` bit position must equals `to` bit position, %s",
                        getBitsFieldIdString(id, name), pos));
            }
        }
    }
}
