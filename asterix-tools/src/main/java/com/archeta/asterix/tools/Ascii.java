/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

/**
 * Helper for dealing with ASCII encoding of numbers.
 */
final class Ascii {
    /**
     * Maximum number of digits in a US-ASCII-encoded int.
     */
    static final int INT_MAX_DIGITS = 10;

    /**
     * An absolute value of the {@link Integer#MIN_VALUE} as long.
     */
    static final long INTEGER_ABSOLUTE_MIN_VALUE = Math.abs((long) Integer.MIN_VALUE);

    /**
     * Check if the {@code value} is an ASCII-encoded digit.
     *
     * @param value the value be checked
     * @return {@code true} if the {@code value} is an ASCII-encoded digit.
     */
    static boolean isDigit(final byte value) {
        return value >= 0x30 && value <= 0x39;
    }

    /**
     * Parses a four-digit number out of an ASCII-encoded value assuming little-endian byte order.
     *
     * @param bytes the ASCII-encoded value in little-endian byte order
     * @return {@code int} value with four digits.
     */
    static int parseFourDigitsLittleEndian(final int bytes) {
        int val = bytes & 0x0F0F0F0F;
        val = (val * 10) + (val >> 8);
        return ((val & 0x00FF00FF) * 6553601) >> 16;
    }

    /**
     * Parses an eight-digit number out of an ASCII-encoded value assuming little-endian byte order.
     *
     * @param bytes the ASCII-encoded value in little-endian byte order
     * @return {@code int} value with eight digits
     */
    static int parseEightDigitsLittleEndian(final long bytes) {
        long val = bytes - 0x3030303030303030L;
        val = (val * 10) + (val >> 8);
        val = (((val & 0x000000FF000000FFL) * 0x000F424000000064L) +
                (((val >> 16) & 0x000000FF000000FFL) * 0x0000271000000001L)) >> 32;
        return (int) val;
    }

    /**
     * Checks if the provided {@code value} represents an ASCII-encoded number which contains exactly four digits.
     *
     * @param value the four ASCII-encoded bytes value to check
     * @return {@code true} if the {@code value} is an ASCII-encoded number with four digits in it
     */
    static boolean isFourDigitsAsciiEncodedNumber(final int value) {
        return 0 == ((((value + 0x46464646) | (value - 0x30303030)) & 0x80808080));
    }

    /**
     * Checks if the provided {@code value} represents an ASCII-encoded number which contains exactly eight digits.
     *
     * @param value the eight ASCII-encoded bytes value to check
     * @return {@code true} if the {@code value} is an ASCII-encoded number with eight digits in it.
     */
    static boolean isEightDigitAsciiEncodedNumber(final long value) {
        return 0L == ((((value + 0x4646464646464646L) | (value - 0x3030303030303030L)) & 0x8080808080808080L));
    }

    /**
     * Private constructor.
     */
    private Ascii() {
    }
}
