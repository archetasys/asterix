/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.archeta.asterix.Buffer.SHOULD_BOUNDS_CHECK;

public final class HexDumpPretty {
    private static final String EMPTY_STRING = "";

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
        if (capacity == 0) {
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

    public static void append(final byte[] bytes, final Appendable out) throws IOException {
        final int capacity = bytes.length;
        if (capacity > 0) {
            HexUtil.append(bytes, 0, capacity, out);
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

    public static void append(final ByteBuffer byteBuffer, final Appendable out) throws IOException {
        final int capacity = byteBuffer.capacity();
        if (capacity > 0) {
            HexUtil.append(byteBuffer, 0, capacity, out);
        }
    }

    public static void append(final ByteBuffer byteBuffer, final int offset, final int length, final Appendable out) throws IOException {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length, byteBuffer.capacity());
        }

        if (length > 0) {
            HexUtil.append(byteBuffer, offset, length, out);
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

    private static void boundsCheck(final int offset, final int length, final int capacity) {
        if ((offset | length | (offset + length) | (capacity - (offset + length))) < 0) {
            throw new IndexOutOfBoundsException("offset=" + offset + " length=" + length + " capacity=" + capacity);
        }
    }

    /**
     * Private constructor.
     */
    private HexDumpPretty() {
    }

    private static final class HexUtil {
        private static final String LF = System.lineSeparator();
        private static final char[] BYTE2CHAR = new char[256];
        private static final String[] BYTE2HEX = new String[256];
        private static final String[] ROW_PREFIXES = new String[65536 >>> 4];
        private static final String[] HEX_PADDING = new String[16];
        private static final String[] BYTE_PADDING = new String[16];

        static {
            // Generate the lookup table for hex dump paddings
            for (int i = 0; i < HEX_PADDING.length; i++) {
                final int padding = HEX_PADDING.length - i;
                final StringBuilder sb = new StringBuilder(padding * 3);
                for (int j = 0; j < padding; j++) {
                    sb.append("   ");
                }

                HEX_PADDING[i] = sb.toString();
            }

            // Generate the lookup table for byte dump paddings
            for (int i = 0; i < BYTE_PADDING.length; i++) {
                final int padding = BYTE_PADDING.length - i;
                final StringBuilder sb = new StringBuilder(padding);
                for (int j = 0; j < padding; j++) {
                    sb.append(' ');
                }

                BYTE_PADDING[i] = sb.toString();
            }

            // Generate the lookup table for the start-offset header in each row (up to 64KiB).
            for (int i = 0; i < ROW_PREFIXES.length; i++) {
                final StringBuilder sb = new StringBuilder(12);
                sb.append(LF);
                sb.append(Long.toHexString(i << 4 & 0xffffffffL | 0x100000000L));
                sb.setCharAt(sb.length() - 9, '|');
                sb.append('|');
                ROW_PREFIXES[i] = sb.toString();
            }

            // Generate the lookup table for byte-to-hex-dump conversion
            for (int i = 0; i < BYTE2HEX.length; i++) {
                BYTE2HEX[i] = ' ' + HexDump.i8(i);
            }

            // Generate the lookup table for byte-to-char conversion
            for (int i = 0; i < BYTE2CHAR.length; i++) {
                if (i <= 0x1f || i >= 0x7f) {
                    BYTE2CHAR[i] = '.';
                } else {
                    BYTE2CHAR[i] = (char) i;
                }
            }
        }

        private static String from(final byte[] bytes, final int offset, final int length) {
            final int rows = (length >> 4) + ((length & 0xf) == 0 ? 0 : 1) + 4;
            final StringBuilder sb = new StringBuilder(rows * 80);
            try {
                HexUtil.append(bytes, offset, length, sb);
            } catch (final IOException ignored) {
            }

            return sb.toString();
        }

        private static String from(final ByteBuffer byteBuffer, final int offset, final int length) {
            final int rows = (length >> 4) + ((length & 0xf) == 0 ? 0 : 1) + 4;
            final StringBuilder sb = new StringBuilder(rows * 80);
            try {
                HexUtil.append(byteBuffer, offset, length, sb);
            } catch (final IOException ignored) {
            }

            return sb.toString();
        }

        private static String from(final Buffer buffer, final int offset, final int length) {
            final int rows = (length >> 4) + ((length & 0xf) == 0 ? 0 : 1) + 4;
            final StringBuilder sb = new StringBuilder(rows * 80);
            try {
                HexUtil.append(buffer, offset, length, sb);
            } catch (final IOException ignored) {
            }

            return sb.toString();
        }

        private static void append(final byte[] bytes, final int offset, final int length, final Appendable out) throws IOException {
            if (length == 0) {
                return;
            }

            appendHeader(out);
            final int fullRows = length >>> 4;
            final int remainder = length & 0xf;

            // Dump the rows which have 16 bytes.
            for (int row = 0; row < fullRows; row++) {
                int rowStartIndex = (row << 4) + offset;

                // Per-row prefix.
                appendRowPrefix(row, rowStartIndex, out);

                // Hex dump.
                int rowEndIndex = rowStartIndex + 16;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[bytes[j] & 0xff]);
                }

                out.append(" |");

                // ASCII dump.
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[bytes[j] & 0xff]);
                }

                out.append('|');
            }

            // Dump the last row which has less than 16 bytes.
            if (remainder != 0) {
                int rowStartIndex = (fullRows << 4) + offset;
                appendRowPrefix(fullRows, rowStartIndex, out);

                // Hex dump
                final int rowEndIndex = rowStartIndex + remainder;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[bytes[j] & 0xff]);
                }
                out.append(HEX_PADDING[remainder]);
                out.append(" |");

                // Ascii dump
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[bytes[j] & 0xff]);
                }
                out.append(BYTE_PADDING[remainder]);
                out.append('|');
            }

            appendFooter(out);
        }

        private static void append(final Buffer bytes, final int offset, final int length, final Appendable out) throws IOException {
            if (length == 0) {
                return;
            }

            appendHeader(out);
            final int fullRows = length >>> 4;
            final int remainder = length & 0xf;

            // Dump the rows which have 16 bytes.
            for (int row = 0; row < fullRows; row++) {
                int rowStartIndex = (row << 4) + offset;

                // Per-row prefix.
                appendRowPrefix(row, rowStartIndex, out);

                // Hex dump.
                int rowEndIndex = rowStartIndex + 16;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[bytes.getByte(j) & 0xff]);
                }

                out.append(" |");

                // ASCII dump.
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[bytes.getByte(j) & 0xff]);
                }

                out.append('|');
            }

            // Dump the last row which has less than 16 bytes.
            if (remainder != 0) {
                int rowStartIndex = (fullRows << 4) + offset;
                appendRowPrefix(fullRows, rowStartIndex, out);

                // Hex dump
                final int rowEndIndex = rowStartIndex + remainder;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[bytes.getByte(j) & 0xff]);
                }
                out.append(HEX_PADDING[remainder]);
                out.append(" |");

                // Ascii dump
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[bytes.getByte(j) & 0xff]);
                }
                out.append(BYTE_PADDING[remainder]);
                out.append('|');
            }

            appendFooter(out);
        }

        private static void append(final ByteBuffer byteBuffer, final int offset, final int length, final Appendable out) throws IOException {
            if (length == 0) {
                return;
            }

            appendHeader(out);
            final int fullRows = length >>> 4;
            final int remainder = length & 0xf;

            // Dump the rows which have 16 bytes.
            for (int row = 0; row < fullRows; row++) {
                int rowStartIndex = (row << 4) + offset;

                // Per-row prefix.
                appendRowPrefix(row, rowStartIndex, out);

                // Hex dump.
                int rowEndIndex = rowStartIndex + 16;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[byteBuffer.get(j) & 0xff]);
                }

                out.append(" |");

                // ASCII dump.
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[byteBuffer.get(j) & 0xff]);
                }

                out.append('|');
            }

            // Dump the last row which has less than 16 bytes.
            if (remainder != 0) {
                int rowStartIndex = (fullRows << 4) + offset;
                appendRowPrefix(fullRows, rowStartIndex, out);

                // Hex dump
                final int rowEndIndex = rowStartIndex + remainder;
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2HEX[byteBuffer.get(j) & 0xff]);
                }
                out.append(HEX_PADDING[remainder]);
                out.append(" |");

                // Ascii dump
                for (int j = rowStartIndex; j < rowEndIndex; j++) {
                    out.append(BYTE2CHAR[byteBuffer.get(j) & 0xff]);
                }
                out.append(BYTE_PADDING[remainder]);
                out.append('|');
            }

            appendFooter(out);
        }

        private static void appendHeader(final Appendable out) throws IOException {
            out.append("         +-------------------------------------------------+").append(LF)
                    .append("         |  0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F |").append(LF)
                    .append("+--------+-------------------------------------------------+----------------+");
        }

        private static void appendFooter(final Appendable out) throws IOException {
            out.append(LF).append("+--------+-------------------------------------------------+----------------+");
        }

        private static void appendRowPrefix(final int row, final int rowStartIndex, final Appendable out) throws IOException {
            if (row < ROW_PREFIXES.length) {
                out.append(ROW_PREFIXES[row]);
            } else {
                out.append(LF);
                out.append('|');
                out.append(Long.toHexString(rowStartIndex & 0xFFFFFFFFL | 0x100000000L).substring(1));
                out.append('|');
            }
        }

        private HexUtil() {
        }
    }
}
