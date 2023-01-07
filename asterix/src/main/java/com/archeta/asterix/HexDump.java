/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.archeta.asterix.Buffer.SHOULD_BOUNDS_CHECK;

public final class HexDump {
    private static final String EMPTY_STRING = "";
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String[] BYTE2HEX_PAD = new String[256];
    private static final byte[] HEX2B;

    static {
        // Generate the lookup table that converts a byte into a 2-digit hexadecimal integer.
        for (int i = 0; i < BYTE2HEX_PAD.length; i++) {
            final String s = Integer.toHexString(i);
            BYTE2HEX_PAD[i] = i > 0xf ? s : ('0' + s);
        }

        // Generate the lookup table that converts a hex char into its decimal value. 
        // The size of the table is such that the JVM is capable of save any bounds-check if a 
        // char type is used as an index.
        HEX2B = new byte[Character.MAX_VALUE + 1];
        Arrays.fill(HEX2B, (byte) -1);
        HEX2B['0'] = (byte) 0;
        HEX2B['1'] = (byte) 1;
        HEX2B['2'] = (byte) 2;
        HEX2B['3'] = (byte) 3;
        HEX2B['4'] = (byte) 4;
        HEX2B['5'] = (byte) 5;
        HEX2B['6'] = (byte) 6;
        HEX2B['7'] = (byte) 7;
        HEX2B['8'] = (byte) 8;
        HEX2B['9'] = (byte) 9;
        HEX2B['A'] = (byte) 10;
        HEX2B['B'] = (byte) 11;
        HEX2B['C'] = (byte) 12;
        HEX2B['D'] = (byte) 13;
        HEX2B['E'] = (byte) 14;
        HEX2B['F'] = (byte) 15;
        HEX2B['a'] = (byte) 10;
        HEX2B['b'] = (byte) 11;
        HEX2B['c'] = (byte) 12;
        HEX2B['d'] = (byte) 13;
        HEX2B['e'] = (byte) 14;
        HEX2B['f'] = (byte) 15;
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents
     * {@code byte} {@code value}.
     *
     * @param value the {@code byte} value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i8(final int value) {
        return BYTE2HEX_PAD[value & 0xff];
    }

    public static void i8(final int value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[value & 0xff]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents the given
     * {@code short} {@code value}.
     *
     * @param value the {@code short} value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i16(final int value) {
        return BYTE2HEX_PAD[(value >>> 8) & 0xff] + BYTE2HEX_PAD[value & 0xff];
    }

    public static void i16(final int value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(value >>> 8) & 0xff]).append(BYTE2HEX_PAD[value & 0xff]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents
     * 24-bit {@code int} {@code value}.
     *
     * @param value the value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i24(final int value) {
        return BYTE2HEX_PAD[(value >> 16) & 0xff] +
                BYTE2HEX_PAD[(value >> 8) & 0xff] +
                BYTE2HEX_PAD[value & 0xff];
    }

    public static void i24(final int value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(value >> 16) & 0xff])
                .append(BYTE2HEX_PAD[(value >> 8) & 0xff])
                .append(BYTE2HEX_PAD[value & 0xff]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents the given
     * {@code int} {@code value}.
     *
     * @param value the {@code int} value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i32(final int value) {
        return BYTE2HEX_PAD[(value >> 24) & 0xff] +
                BYTE2HEX_PAD[(value >> 16) & 0xff] +
                BYTE2HEX_PAD[(value >> 8) & 0xff] +
                BYTE2HEX_PAD[value & 0xff];
    }

    public static void i32(final int value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(value >> 24) & 0xff])
                .append(BYTE2HEX_PAD[(value >> 16) & 0xff])
                .append(BYTE2HEX_PAD[(value >> 8) & 0xff])
                .append(BYTE2HEX_PAD[value & 0xff]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents
     * 40-bit {@code long} {@code value}.
     *
     * @param value the value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i40(final long value) {
        return BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)] +
                BYTE2HEX_PAD[(int) (value & 0xff)];
    }

    public static void i40(final long value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)])
                .append(BYTE2HEX_PAD[(int) (value & 0xff)]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents
     * 48-bit {@code long} {@code value}.
     *
     * @param value the value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i48(final long value) {
        return BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)] +
                BYTE2HEX_PAD[(int) (value & 0xff)];
    }

    public static void i48(final long value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)])
                .append(BYTE2HEX_PAD[(int) (value & 0xff)]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents
     * 56-bit {@code long} {@code value}.
     *
     * @param value the value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i56(final long value) {
        return BYTE2HEX_PAD[(int) ((value >> 48) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)] +
                BYTE2HEX_PAD[(int) (value & 0xff)];
    }

    public static void i56(final long value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(int) ((value >> 48) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)])
                .append(BYTE2HEX_PAD[(int) (value & 0xff)]);
    }

    /**
     * Returns the {@code String} which contains the hexadecimal digits that represents the given
     * {@code long} {@code value}.
     *
     * @param value the {@code long} value to be converted to hexadecimal digits
     * @return the {@code String} which contains the hexadecimal digits
     */
    public static String i64(final long value) {
        return BYTE2HEX_PAD[(int) ((value >> 56) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 48) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)] +
                BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)] +
                BYTE2HEX_PAD[(int) (value & 0xff)];
    }

    public static void i64(final long value, final Appendable out) throws IOException {
        out.append(BYTE2HEX_PAD[(int) ((value >> 56) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 48) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 40) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 32) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 24) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 16) & 0xff)])
                .append(BYTE2HEX_PAD[(int) ((value >> 8) & 0xff)])
                .append(BYTE2HEX_PAD[(int) (value & 0xff)]);
    }

    public static String from(final long value, final int width) {
        switch (width) {
            case 1:
                return i8((byte) value);
            case 2:
                return i16((short) value);
            case 3:
                return i24((int) value);
            case 4:
                return i32((int) value);
            case 5:
                return i40(value);
            case 6:
                return i48(value);
            case 7:
                return i56(value);
            case 8:
                return i64(value);
            default:
                throw new IllegalArgumentException("illegal width: " + width);
        }
    }

    public static String from(final byte[] bytes) {
        final int capacity = bytes.length;
        if (capacity == 0) {
            return EMPTY_STRING;
        }

        return HexUtil.from(bytes, 0, capacity);
    }

    public static String from(final byte[] bytes, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, bytes.length);
        }

        if (length == 0) {
            return EMPTY_STRING;
        }

        return HexUtil.from(bytes, offset, length);
    }

    public static String from(final ByteBuffer byteBuffer) {
        final int capacity = byteBuffer.capacity();
        if (capacity < 1) {
            return EMPTY_STRING;
        }

        return HexUtil.from(byteBuffer, 0, capacity);
    }

    public static String from(final ByteBuffer byteBuffer, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, byteBuffer.capacity());
        }

        if (length == 0) {
            return EMPTY_STRING;
        }

        return HexUtil.from(byteBuffer, offset, length);
    }

    public static String from(final Buffer buffer) {
        final int capacity = buffer.capacity();
        if (capacity < 1) {
            return EMPTY_STRING;
        }

        return HexUtil.from(buffer, 0, capacity);
    }

    public static String from(final Buffer buffer, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, buffer.capacity());
        }

        if (length == 0) {
            return EMPTY_STRING;
        }

        return HexUtil.from(buffer, offset, length);
    }

    public static void append(final long value, final int width, final Appendable out) throws IOException {
        switch (width) {
            case 1:
                i8((byte) value, out);
                break;
            case 2:
                i16((short) value, out);
                break;
            case 3:
                i24((int) value, out);
                break;
            case 4:
                i32((int) value, out);
                break;
            case 5:
                i40(value, out);
                break;
            case 6:
                i48(value, out);
                break;
            case 7:
                i56(value, out);
                break;
            case 8:
                i64(value, out);
                break;
            default:
                throw new IllegalArgumentException("illegal width: " + width);
        }
    }

    public static void append(final byte[] bytes, final Appendable out) throws IOException {
        final int capacity = bytes.length;
        if (capacity > 0) {
            HexUtil.append(bytes, 0, bytes.length, out);
        }
    }

    public static void append(final byte[] bytes, final int offset, final int length, final Appendable out) throws IOException {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, bytes.length);
        }

        if (length > 0) {
            HexUtil.append(bytes, offset, length, out);
        }
    }

    public static void append(final ByteBuffer buffer, final Appendable out) throws IOException {
        final int capacity = buffer.capacity();
        if (capacity > 0) {
            HexUtil.append(buffer, 0, capacity, out);
        }
    }

    public static void append(final ByteBuffer buffer, final int offset, final int length, final Appendable out) throws IOException {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, buffer.capacity());
        }

        if (length > 0) {
            HexUtil.append(buffer, offset, length, out);
        }
    }

    public static void append(final Buffer buffer, final Appendable out) throws IOException {
        final int capacity = buffer.capacity();
        if (capacity > 0) {
            HexUtil.append(buffer, 0, capacity, out);
        }
    }

    public static void append(final Buffer buffer, final int offset, final int length, final Appendable out) throws IOException {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, buffer.capacity());
        }

        if (length > 0) {
            HexUtil.append(buffer, offset, length, out);
        }
    }

    /**
     * Returns an {@code byte} array with content decoded from the given {@code hexDump}.
     *
     * @param hexDump the {@link CharSequence} which contains the hex dump
     * @return a newly created {@code byte} array
     */
    public static byte[] decode(final CharSequence hexDump) {
        return decode(hexDump, 0, hexDump.length());
    }

    /**
     * Returns a {@code byte} array with content decoded from the given {@code hexDump}.
     *
     * @param hexDump the {@link CharSequence} which contains the hex dump
     * @param offset  the offset in {@code hexDump} to start decode
     * @param length  the length of {@code hexDump} to decode
     * @return a newly created {@code byte} array
     */
    public static byte[] decode(final CharSequence hexDump, final int offset, final int length) {
        if (length < 0 || (length & 1) != 0) {
            throw new IllegalArgumentException("expected length is nonnegative and even, found: " + length);
        }

        if (length == 0) {
            return EMPTY_BYTE_ARRAY;
        }

        boundsCheck(offset, length, hexDump.length());
        final int n = length >> 1;
        final byte[] bytes = new byte[n];
        for (int i = 0, c = offset, d1, d2; i < n; ++i) {
            d1 = HEX2B[hexDump.charAt(c++)];
            d2 = HEX2B[hexDump.charAt(c++)];
            if (d1 < 0 || d2 < 0) {
                throw new NumberFormatException("invalid hex digit `" + hexDump.charAt(c - 2) + hexDump.charAt(c - 1) + '`');
            }

            bytes[i] = (byte) ((d1 << 4) + d2);
        }

        return bytes;
    }

    private static void boundsCheck(final int offset, final int length, final int capacity) {
        if ((offset | length | (offset + length) | (capacity - (offset + length))) < 0) {
            throw new IndexOutOfBoundsException("offset=" + offset + " length=" + length + " capacity=" + capacity);
        }
    }

    /**
     * Private constructor.
     */
    private HexDump() {
    }

    private static final class HexUtil {
        private static final char[] HEXDUMP_TABLE = new char[256 * 4];

        static {
            final char[] digits = "0123456789abcdef".toCharArray();
            for (int i = 0; i < 256; i++) {
                HEXDUMP_TABLE[i << 1] = digits[i >>> 4 & 0x0f];
                HEXDUMP_TABLE[(i << 1) + 1] = digits[i & 0x0f];
            }
        }

        private static String from(final byte[] bytes, final int offset, final int length) {
            final int endOffset = offset + length;
            final char[] buf = new char[length << 1];
            for (int srcOffset = offset, dstOffset = 0; srcOffset < endOffset; srcOffset++, dstOffset += 2) {
                System.arraycopy(HEXDUMP_TABLE, (bytes[srcOffset] & 0xff) << 1, buf, dstOffset, 2);
            }

            return new String(buf);
        }

        private static String from(final ByteBuffer byteBuffer, final int offset, final int length) {
            final int endOffset = offset + length;
            final char[] buf = new char[length << 1];
            for (int srcOffset = offset, dstOffset = 0; srcOffset < endOffset; srcOffset++, dstOffset += 2) {
                System.arraycopy(HEXDUMP_TABLE, (byteBuffer.get(srcOffset) & 0xff) << 1, buf, dstOffset, 2);
            }

            return new String(buf);
        }

        private static String from(final Buffer buffer, final int offset, final int length) {
            final int endOffset = offset + length;
            final char[] buf = new char[length << 1];
            for (int srcOffset = offset, dstOffset = 0; srcOffset < endOffset; srcOffset++, dstOffset += 2) {
                System.arraycopy(HEXDUMP_TABLE, (buffer.getByte(srcOffset) & 0xff) << 1, buf, dstOffset, 2);
            }

            return new String(buf);
        }

        private static void append(final byte[] bytes, final int offset, final int length, final Appendable out) throws IOException {
            final int endOffset = offset + length;
            for (int srcOffset = offset, i; srcOffset < endOffset; srcOffset++) {
                i = (bytes[srcOffset] & 0xff) << 1;
                out.append(HEXDUMP_TABLE[i]);
                out.append(HEXDUMP_TABLE[i + 1]);
            }
        }

        private static void append(final ByteBuffer byteBuffer, final int offset, final int length, final Appendable out) throws IOException {
            final int endOffset = offset + length;
            for (int srcOffset = offset, i; srcOffset < endOffset; srcOffset++) {
                i = (byteBuffer.get(srcOffset) & 0xff) << 1;
                out.append(HEXDUMP_TABLE[i]);
                out.append(HEXDUMP_TABLE[i + 1]);
            }
        }

        private static void append(final Buffer buffer, final int offset, final int length, final Appendable out) throws IOException {
            final int endOffset = offset + length;
            for (int srcOffset = offset, i; srcOffset < endOffset; srcOffset++) {
                i = (buffer.getByte(srcOffset) & 0xff) << 1;
                out.append(HEXDUMP_TABLE[i]);
                out.append(HEXDUMP_TABLE[i + 1]);
            }
        }

        private HexUtil() {
        }
    }
}
