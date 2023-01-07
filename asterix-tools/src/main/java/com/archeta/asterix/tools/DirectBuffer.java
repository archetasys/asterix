/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.Buffer;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.archeta.asterix.tools.Ascii.*;
import static java.nio.charset.StandardCharsets.US_ASCII;

final class DirectBuffer {
    private static final Unsafe UNSAFE;
    private static final long ARRAY_BYTE_BASE_OFFSET;
    private static final boolean IS_BIG_ENDIAN_NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;

    static {
        Unsafe unsafe = null;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (final Exception ex) {
            try {
                final Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (final Exception ex2) {
                rethrow(ex);
            }
        }

        UNSAFE = unsafe;
        ARRAY_BYTE_BASE_OFFSET = Unsafe.ARRAY_BYTE_BASE_OFFSET;
    }

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final ByteBuffer byteBuffer;
    private final long addressOffset;
    private final byte[] byteArray;
    private final int capacity;

    DirectBuffer(final ByteBuffer byteBuffer) {
        capacity = byteBuffer.capacity();
        this.byteBuffer = byteBuffer;
        if (byteBuffer.isDirect()) {
            byteArray = null;
            addressOffset = Buffer.address(byteBuffer);
        } else {
            byteArray = Buffer.array(byteBuffer);
            final int arrayOffset = Buffer.arrayOffset(byteBuffer);
            addressOffset = ARRAY_BYTE_BASE_OFFSET + arrayOffset;
        }
    }

    int capacity() {
        return capacity;
    }

    byte getByte(final int offset) {
        return UNSAFE.getByte(byteArray, addressOffset + offset);
    }

    String getStringAsciiNoLength(final int offset, final int length) {
        final byte[] dst = new byte[length];
        UNSAFE.copyMemory(byteArray, addressOffset + offset, dst, ARRAY_BYTE_BASE_OFFSET, length);
        return new String(dst, US_ASCII);
    }

    int parseNaturalIntAscii(final int offset, final int length) {
        if (length < INT_MAX_DIGITS) {
            return parsePositiveIntAscii(offset, length, offset, offset + length);
        } else {
            final long tally = parsePositiveIntAsciiOverflowCheck(offset, length, offset, offset + length);
            if (tally >= INTEGER_ABSOLUTE_MIN_VALUE) {
                throwParseIntOverflowError(offset, length);
            }

            return (int) tally;
        }
    }

    int forEachByte(final int offset, final int length, final ByteProcessor processor) {
        final int end = offset + length;
        if ((offset | end | (capacity - end)) < 0) {
            return -1;
        }

        if (null == processor) {
            throw new NullPointerException("`processor` must not null");
        }

        for (int i = offset; i < end; i++) {
            if (!processor.process(UNSAFE.getByte(byteArray, addressOffset + i))) {
                return i;
            }
        }

        return -1;
    }

    private int parsePositiveIntAscii(final int offset, final int length, final int start, final int end) {
        final long off = addressOffset;
        final byte[] array = byteArray;
        int i = start;
        int tally = 0, quartet;
        while ((end - i) >= 4 && isFourDigitsAsciiEncodedNumber(quartet = UNSAFE.getInt(array, off + i))) {
            if (IS_BIG_ENDIAN_NATIVE_ORDER) {
                quartet = Integer.reverseBytes(quartet);
            }

            tally = (tally * 10_000) + parseFourDigitsLittleEndian(quartet);
            i += 4;
        }

        byte digit;
        while (i < end && isDigit(digit = UNSAFE.getByte(array, off + i))) {
            tally = (tally * 10) + (digit - 0x30);
            i++;
        }

        if (i != end) {
            throwParseIntError(offset, length);
        }

        return tally;
    }

    private long parsePositiveIntAsciiOverflowCheck(final int offset, final int length, final int start, final int end) {
        if ((end - start) > INT_MAX_DIGITS) {
            throwParseIntOverflowError(offset, length);
        }

        final long off = addressOffset;
        final byte[] array = byteArray;
        int i = start;
        long tally = 0;
        long octet = UNSAFE.getLong(array, off + i);
        if (isEightDigitAsciiEncodedNumber(octet)) {
            if (IS_BIG_ENDIAN_NATIVE_ORDER) {
                octet = Long.reverseBytes(octet);
            }

            tally = parseEightDigitsLittleEndian(octet);
            i += 8;

            byte digit;
            while (i < end && isDigit(digit = UNSAFE.getByte(array, off + i))) {
                tally = (tally * 10L) + (digit - 0x30);
                i++;
            }
        }

        if (i != end) {
            throwParseIntError(offset, length);
        }

        return tally;
    }

    private void throwParseIntError(final int offset, final int length) {
        throw new AsciiNumberFormatException("error parsing int: " + getStringAsciiNoLength(offset, length));
    }

    private void throwParseIntOverflowError(final int offset, final int length) {
        throw new AsciiNumberFormatException("int overflow parsing: " + getStringAsciiNoLength(offset, length));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void rethrow(final Throwable t) throws T {
        throw (T) t;
    }
}
