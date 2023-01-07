/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.ASTERIXIds;

final class CategoryLineConsumer implements LineReader.LineConsumer {
    private static final String ERR_CONFIG = "ASTERIX configuration file at line %d is not valid";
    private static final ByteProcessor NON_WHITESPACE_COMMENT = (b) -> b != 0x20 && b != 0x09 && b != 0x23 /* # */;
    private static final ByteProcessor FIND_COLON = (b) -> b != 0x3a;
    private static final ByteProcessor FIND_DOT = (b) -> b != 0x2e;
    private static final long MISSING_VALUE = 0;
    private static final int CATEGORY_NUMBER_MAX = 255;
    private static final int MAJOR_VERSION_MAX = 7;
    private static final int MINOR_VERSION_MAX = 255;

    private final ImmutableLong2LongHashMap.Builder categoryIdsByNumbersMap = new ImmutableLong2LongHashMap.Builder(MISSING_VALUE);
    private int lineno = 0;

    CategoryLineConsumer() {
    }

    @Override
    public boolean onNext(final DirectBuffer buffer, final int offset, final int length) {
        lineno++;

        final int indexOfColon = buffer.forEachByte(offset, length, FIND_COLON);
        if (indexOfColon == -1) {
            System.err.printf(ERR_CONFIG, lineno);
            return false;
        }

        final int catno = buffer.parseNaturalIntAscii(offset, indexOfColon - offset);
        if (catno < 0 || catno > CATEGORY_NUMBER_MAX) {
            System.err.printf("invalid ASTERIX Category number `%d` at line %d", catno, lineno);
            return false;
        }

        final int indexOfDot = buffer.forEachByte(indexOfColon + 1, length - indexOfColon + offset, FIND_DOT);
        if (indexOfDot == -1) {
            System.err.printf(ERR_CONFIG, lineno);
            return false;
        }

        final int majorVersion = buffer.parseNaturalIntAscii(indexOfColon + 1, indexOfDot - indexOfColon - 1);
        if (majorVersion < 0 || majorVersion > MAJOR_VERSION_MAX) {
            System.err.printf("invalid ASTERIX Category major version number '%d' at line %d", majorVersion, lineno);
            return false;
        }

        final int end = buffer.forEachByte(indexOfDot + 1, length - (indexOfDot - offset) - 1, NON_WHITESPACE_COMMENT);
        final int n = end == -1 ? length - (indexOfDot - offset) - 1 : end - indexOfDot - 1;
        final int minorVersion = buffer.parseNaturalIntAscii(indexOfDot + 1, n);
        if (minorVersion < 0 || minorVersion > MINOR_VERSION_MAX) {
            System.err.printf("invalid ASTERIX Category minor version number at line %d", lineno);
            return false;
        }

        final long categoryId = ASTERIXIds.categoryId(catno, majorVersion, minorVersion);
        if (categoryIdsByNumbersMap.put(catno, categoryId) != MISSING_VALUE) {
            System.err.printf("duplicate ASTERIX Category %03d at line %d", catno, lineno);
            return false;
        }

        return true;
    }

    ImmutableLong2LongHashMap getCategoryIdsByNumbersMap() {
        return categoryIdsByNumbersMap.build();
    }
}
