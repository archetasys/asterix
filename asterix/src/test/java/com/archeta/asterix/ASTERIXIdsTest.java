/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.Test;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class ASTERIXIdsTest {
    private static final int NUM_MAJOR_VERSIONS = 8;
    private static final int NUM_MINOR_VERSIONS = 256;

    @Test
    void shouldCreateCategoryId() {
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            for (int j = 0; j < NUM_MAJOR_VERSIONS; j++) {
                for (int k = 0; k < NUM_MINOR_VERSIONS; k++) {
                    final long categoryId = categoryId(i, j, k);
                    assertEquals(i, getCategoryNumber(categoryId));
                    assertEquals(j, getCategoryMajorVersionNumber(categoryId));
                    assertEquals(k, getCategoryMinorVersionNumber(categoryId));
                    assertDataFieldIdZeros(categoryId);
                }
            }
        }

        final long idOne = categoryId(256, 8, 256);

        assertEquals(0, getCategoryNumber(idOne));
        assertEquals(0, getCategoryMajorVersionNumber(idOne));
        assertEquals(0, getCategoryMinorVersionNumber(idOne));
        assertDataFieldIdZeros(idOne);

        final long idTwo = categoryId(-1, -1, -1);

        assertEquals(255, getCategoryNumber(idTwo));
        assertEquals(7, getCategoryMajorVersionNumber(idTwo));
        assertEquals(255, getCategoryMinorVersionNumber(idTwo));
        assertDataFieldIdZeros(idTwo);
    }

    @Test
    void shouldCreateDataItemId() {
        final int categoryMajorVersionNumber = 1;
        final int categoryMinorVersionNumber = 21;
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            final long categoryId = categoryId(i, categoryMajorVersionNumber, categoryMinorVersionNumber);
            for (int l = 0; l < NUM_DATA_ITEM_NUMBERS; l++) {
                final long dataItemId = dataItemId(categoryId, l, DATA_FORMAT_FIXED);
                assertEquals(i, getCategoryNumber(dataItemId));
                assertEquals(categoryMajorVersionNumber, getCategoryMajorVersionNumber(dataItemId));
                assertEquals(categoryMinorVersionNumber, getCategoryMinorVersionNumber(dataItemId));
                assertEquals(0, getSubABitPresence(dataItemId));
                assertEquals(0, getSubAFormat(dataItemId));
                assertEquals(0, getSubBBitPresence(dataItemId));
                assertEquals(0, getSubBFormat(dataItemId));
                assertEquals(0, getBitsFieldFromPosition(dataItemId));
                assertEquals(categoryId, getCategoryId(dataItemId));
                assertEquals(dataItemId, getDataItemId(dataItemId));
                assertEquals(dataItemId, getDataFieldId(dataItemId));
            }
        }
    }

    @Test
    void shouldCreateSubAId() {
        final long categoryId = categoryId(48, 1, 30);
        final long dataItemId = dataItemId(categoryId, 9, DATA_FORMAT_COMPOUND);

        long id = subAId(dataItemId, 8, DATA_FORMAT_FIXED);

        assertEquals(48, getCategoryNumber(id));
        assertEquals(1, getCategoryMajorVersionNumber(id));
        assertEquals(30, getCategoryMinorVersionNumber(id));
        assertEquals(9, getDataItemNumber(id));
        assertEquals(DATA_FORMAT_COMPOUND, getDataItemFormat(id));
        assertEquals(8, getSubABitPresence(id));
        assertEquals(DATA_FORMAT_FIXED, getSubAFormat(id));
        assertEquals(0, getSubBBitPresence(id));
        assertEquals(0, getSubBFormat(id));
        assertEquals(0, getBitsFieldFromPosition(id));
        assertEquals(0, getBitsFieldEncoding(id));
        assertEquals(categoryId, getCategoryId(id));
        assertEquals(dataItemId, getDataItemId(id));
        assertEquals(id, getDataFieldId(id));

        id = subAId(dataItemId, 127, DATA_FORMAT_EXTENDED);

        assertEquals(127, getSubABitPresence(id));
        assertEquals(DATA_FORMAT_EXTENDED, getSubAFormat(id));

        id = subAId(dataItemId, 128, DATA_FORMAT_REPETITIVE);

        assertEquals(0, getSubABitPresence(id));
        assertEquals(DATA_FORMAT_REPETITIVE, getSubAFormat(id));
    }

    @Test
    void shouldCreateSubBId() {
        final long categoryId = categoryId(48, 1, 30);
        final long dataItemId = dataItemId(categoryId, 9, DATA_FORMAT_EXPLICIT);
        final long subAId = subAId(dataItemId, 8, DATA_FORMAT_COMPOUND);

        long subBId = subBId(subAId, 6, DATA_FORMAT_FIXED);

        assertEquals(48, getCategoryNumber(subBId));
        assertEquals(1, getCategoryMajorVersionNumber(subBId));
        assertEquals(30, getCategoryMinorVersionNumber(subBId));
        assertEquals(9, getDataItemNumber(subBId));
        assertEquals(DATA_FORMAT_EXPLICIT, getDataItemFormat(subBId));
        assertEquals(8, getSubABitPresence(subBId));
        assertEquals(DATA_FORMAT_COMPOUND, getSubAFormat(subBId));
        assertEquals(6, getSubBBitPresence(subBId));
        assertEquals(DATA_FORMAT_FIXED, getSubBFormat(subBId));
        assertEquals(0, getBitsFieldFromPosition(subBId));
        assertEquals(0, getBitsFieldEncoding(subBId));
        assertEquals(categoryId, getCategoryId(subBId));
        assertEquals(dataItemId, getDataItemId(subBId));
        assertEquals(subBId, getDataFieldId(subBId));

        subBId = subBId(dataItemId, 127, DATA_FORMAT_EXTENDED);

        assertEquals(127, getSubBBitPresence(subBId));
        assertEquals(DATA_FORMAT_EXTENDED, getSubBFormat(subBId));

        subBId = subBId(dataItemId, 128, DATA_FORMAT_FIXED);

        assertEquals(0, getSubBBitPresence(subBId));
        assertEquals(DATA_FORMAT_FIXED, getSubBFormat(subBId));
    }

    @Test
    void shouldGetCategoryIdString() {
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            for (int j = 0; j < NUM_MAJOR_VERSIONS; j++) {
                for (int k = 0; k < NUM_MINOR_VERSIONS; k++) {
                    final long categoryId = categoryId(i, j, k);
                    assertEquals(String.format("ASTERIX Category %03d Version %d.%d", i, j, k),
                            getCategoryIdString(categoryId));
                }
            }
        }

        final long idOne = categoryId(256, 8, 256);
        assertEquals("ASTERIX Category 000 Version 0.0", getCategoryIdString(idOne));

        final long idTwo = categoryId(-1, -1, -1);
        assertEquals("ASTERIX Category 255 Version 7.255", getCategoryIdString(idTwo));
    }

    @Test
    void shouldGetDataItemIdString() {
        final int categoryMajorVersionNumber = 1;
        final int categoryMinorVersionNumber = 21;
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            final String expCategoryString = String.format("I%03d", i);
            final long categoryId = categoryId(i, categoryMajorVersionNumber, categoryMinorVersionNumber);
            final String expCategoryIdString = String.format("ASTERIX Category %03d Version %d.%d", i,
                    categoryMajorVersionNumber, categoryMinorVersionNumber);
            for (int l = 0; l < NUM_DATA_ITEM_NUMBERS; l++) {
                final long dataItemId = dataItemId(categoryId, l, DATA_FORMAT_FIXED);
                assertEquals(expCategoryIdString, getCategoryIdString(dataItemId));
                final String exp;
                if (DATA_ITEM_RE == l) {
                    exp = "RE";
                } else if (DATA_ITEM_SP == l) {
                    exp = "SP";
                } else {
                    exp = String.format("%03d", l);
                }
                assertEquals(expCategoryString + '/' + exp, getDataItemIdString(dataItemId));
                assertEquals(expCategoryString + '_' + exp, getDataItemCode(dataItemId));
            }
        }
    }

    @Test
    void shouldGetCategoryNumberString() {
        for (int i = 0; i < NUM_CATEGORY_NUMBERS; i++) {
            assertEquals(String.format("%03d", i), getCategoryNumberString(i));
        }
    }

    @Test
    void shouldGetDataItemNumberString() {
        for (int i = 0; i < NUM_DATA_ITEM_NUMBERS; i++) {
            final String exp;
            if (DATA_ITEM_RE == i) {
                exp = "RE";
            } else if (DATA_ITEM_SP == i) {
                exp = "SP";
            } else {
                exp = String.format("%03d", i);
            }

            assertEquals(exp, getDataItemNumberString(i));
        }
    }

    @Test
    void shouldGetDataFieldIdString() {
        final long categoryId = categoryId(48, 1, 30);
        final long dataItemId = dataItemId(categoryId, 9, DATA_FORMAT_EXPLICIT);
        final long subAId = subAId(dataItemId, 8, DATA_FORMAT_COMPOUND);
        final long subBId = subBId(subAId, 6, DATA_FORMAT_FIXED);

        assertEquals("I048/009(ECF/8/6)", getDataFieldIdString(subBId));
        assertEquals("I048/009/FOO(ECF/8/6)", getDataFieldIdString(subBId,"FOO"));
    }

    @Test
    void shouldGetDataFormatString() {
        assertEquals("FIXED", getDataFormatString(DATA_FORMAT_FIXED));
        assertEquals("EXTENDED", getDataFormatString(DATA_FORMAT_EXTENDED));
        assertEquals("REPETITIVE", getDataFormatString(DATA_FORMAT_REPETITIVE));
        assertEquals("COMPOUND", getDataFormatString(DATA_FORMAT_COMPOUND));
        assertEquals("EXPLICIT", getDataFormatString(DATA_FORMAT_EXPLICIT));
        assertEquals("EMPTY", getDataFormatString(DATA_FORMAT_EMPTY));
        assertEquals("UNKNOWN", getDataFormatString(6));
        assertEquals("UNKNOWN", getDataFormatString(7));
        assertEquals("FIXED", getDataFormatString(8));
        assertEquals("EXTENDED", getDataFormatString(9));
    }

    @Test
    void shouldGetBitsFieldEncodingString() {
        assertEquals("UNSIGNED", getBitsFieldEncodingString(BITS_FIELD_ENCODING_UNSIGNED));
        assertEquals("SIGNED", getBitsFieldEncodingString(BITS_FIELD_ENCODING_SIGNED));
        assertEquals("VALUE", getBitsFieldEncodingString(BITS_FIELD_ENCODING_VALUE));
        assertEquals("VALUES", getBitsFieldEncodingString(BITS_FIELD_ENCODING_VALUES));
        assertEquals("MEASURE_UNSIGNED", getBitsFieldEncodingString(BITS_FIELD_ENCODING_MEASURE_UNSIGNED));
        assertEquals("MEASURE_SIGNED", getBitsFieldEncodingString(BITS_FIELD_ENCODING_MEASURE_SIGNED));
        assertEquals("MEASURE_GRAYCODE", getBitsFieldEncodingString(BITS_FIELD_ENCODING_MEASURE_GRAYCODE));
        assertEquals("IA5", getBitsFieldEncodingString(BITS_FIELD_ENCODING_IA5));
        assertEquals("HEX", getBitsFieldEncodingString(BITS_FIELD_ENCODING_HEX));
        assertEquals("OCTAL", getBitsFieldEncodingString(BITS_FIELD_ENCODING_OCTAL));
        assertEquals("ASCII", getBitsFieldEncodingString(BITS_FIELD_ENCODING_ASCII));
        assertEquals("ASCII_CHAR", getBitsFieldEncodingString(BITS_FIELD_ENCODING_ASCII_CHAR));
        assertEquals("SPARE", getBitsFieldEncodingString(BITS_FIELD_ENCODING_SPARE));
        assertEquals("FX", getBitsFieldEncodingString(BITS_FIELD_ENCODING_FX));
        assertEquals("UNKNOWN", getBitsFieldEncodingString(14));
        assertEquals("UNKNOWN", getBitsFieldEncodingString(15));
        assertEquals("UNSIGNED", getBitsFieldEncodingString(16));
        assertEquals("SIGNED", getBitsFieldEncodingString(17));

        assertEquals("unsigned", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_UNSIGNED));
        assertEquals("signed", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_SIGNED));
        assertEquals("value", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_VALUE));
        assertEquals("values", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_VALUES));
        assertEquals("umeasure", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_MEASURE_UNSIGNED));
        assertEquals("smeasure", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_MEASURE_SIGNED));
        assertEquals("graycode", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_MEASURE_GRAYCODE));
        assertEquals("ia5", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_IA5));
        assertEquals("hex", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_HEX));
        assertEquals("octal", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_OCTAL));
        assertEquals("ascii", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_ASCII));
        assertEquals("ascii_char", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_ASCII_CHAR));
        assertEquals("spare", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_SPARE));
        assertEquals("fx", getBitsFieldEncodingDisplayName(BITS_FIELD_ENCODING_FX));
        assertEquals("unknown", getBitsFieldEncodingDisplayName(14));
        assertEquals("unknown", getBitsFieldEncodingDisplayName(15));
        assertEquals("unsigned", getBitsFieldEncodingDisplayName(16));
        assertEquals("signed", getBitsFieldEncodingDisplayName(17));
    }

    @Test
    void shouldGetBitsFieldString() {
        final long categoryId = categoryId(48, 1, 30);
        final long dataItemId = dataItemId(categoryId, 110, DATA_FORMAT_EXPLICIT);
        final long subAId = subAId(dataItemId, 8, DATA_FORMAT_COMPOUND);
        final long subBId = subBId(subAId, 127, DATA_FORMAT_FIXED);
        final long idOne = bitsFieldId(subBId, 32, BITS_FIELD_ENCODING_VALUE);

        assertEquals("I048/110(ECF/8/127/32/VALUE)", getBitsFieldIdString(idOne));
        assertEquals("I048/110/TYP(ECF/8/127/32/VALUE)", getBitsFieldIdString(idOne, "TYP"));
        assertEquals("I048/110/RHO(ECF/8/127/32/VALUE)", getBitsFieldIdString(idOne, "RHO"));

        final long idTwo = bitsFieldId(subBId, 12, BITS_FIELD_ENCODING_VALUES);

        assertEquals("I048/110(ECF/8/127/12/VALUES)", getBitsFieldIdString(idTwo));
        assertEquals("I048/110/TYP(ECF/8/127/12/VALUES)", getBitsFieldIdString(idTwo, "TYP"));
        assertEquals("I048/110/RHO(ECF/8/127/12/VALUES)", getBitsFieldIdString(idTwo, "RHO"));
    }

    private static void assertDataFieldIdZeros(final long id) {
        assertEquals(id, getCategoryId(id));
        assertEquals(id, getDataItemId(id));
        assertEquals(id, getDataFieldId(id));
        assertEquals(0, getDataItemNumber(id));
        assertEquals(0, getDataItemFormat(id));
        assertEquals(0, getSubABitPresence(id));
        assertEquals(0, getSubAFormat(id));
        assertEquals(0, getSubBBitPresence(id));
        assertEquals(0, getSubBFormat(id));
        assertEquals(0, getBitsFieldFromPosition(id));
        assertEquals(0, getBitsFieldEncoding(id));
    }
}
