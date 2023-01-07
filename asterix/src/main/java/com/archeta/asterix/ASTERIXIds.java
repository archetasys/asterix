/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;


import static com.archeta.asterix.ASTERIXConstants.*;

/**
 * Bits field identifier.
 * <pre>
 *    0                   1                   2                   3
 *    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   |CATEGORY NUMBER| MJR |      MNR      | DATA ITEM NUMBER  | FMT |
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   | SUB_A NMBR  |A_FMT| SUB_B NMBR  |B_FMT| BITS FROM POS |BIT_ENC|
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * </pre>
 * <p>
 * The maximum levels of the format hierarchy is 3:
 * <pre>
 *   Data Item Format -> Sub A format  -> Sub B format
 * </pre>
 *
 * <p>Supported structures:
 * <ol>
 *   <li>Fixed->BitsFieldList</li>
 *   <li>Repetitive->BitsFieldList</li>
 *   <li>Extended->Part->BitsFieldList</li>
 *   <li>Compound->Fixed->BitsFieldList</li>
 *   <li>Compound->Repetitive->BitsFieldList</li>
 *   <li>Compound->Extended->Part->BitsFieldList</li>
 *   <li>Explicit->Fixed->BitsFieldList</li>
 *   <li>Explicit->Repetitive->BitsFieldList</li>
 *   <li>Explicit->Extended->Fixed->BitsFieldList</li>
 *   <li>Explicit->Compound->Fixed->BitsFieldList</li>
 *   <li>Explicit->Compound->Repetitive->BitsFieldList</li>
 * </ol>
 */
public final class ASTERIXIds {
    private static final int CATEGORY_NUMBER_NBITS = 8;
    private static final int CATEGORY_VERSION_MAJOR_NBITS = 3;
    private static final int CATEGORY_VERSION_MINOR_NBITS = 8;
    private static final int CATEGORY_ID_NBITS = CATEGORY_NUMBER_NBITS +
            CATEGORY_VERSION_MAJOR_NBITS + CATEGORY_VERSION_MINOR_NBITS;

    private static final int DATA_ITEM_NUMBER_NBITS = 10;
    private static final int DATA_ITEM_FORMAT_NBITS = 3;
    private static final int DATA_ITEM_ID_NBITS = CATEGORY_ID_NBITS +
            DATA_ITEM_NUMBER_NBITS + DATA_ITEM_FORMAT_NBITS;

    private static final int SUB_A_BIT_PRESENCE_NBITS = 7;
    private static final int SUB_A_FORMAT_NBITS = 3;
    private static final int SUB_B_BIT_PRESENCE_NBITS = 7;
    private static final int SUB_B_FORMAT_NBITS = 3;
    private static final int DATA_FIELD_ID_NBITS = DATA_ITEM_ID_NBITS +
            SUB_A_BIT_PRESENCE_NBITS + SUB_A_FORMAT_NBITS +
            SUB_B_BIT_PRESENCE_NBITS + SUB_B_FORMAT_NBITS;

    private static final int BITS_FIELD_ID_NBITS = 64;
    private static final int BITS_FIELD_FROM_POS_NBITS = 8;
    private static final int BITS_FIELD_ENCODING_NBITS = BITS_FIELD_ID_NBITS -
            BITS_FIELD_FROM_POS_NBITS - DATA_FIELD_ID_NBITS;

    private static final long CATEGORY_ID_MASK = ~(~0L << CATEGORY_ID_NBITS);
    private static final long CATEGORY_NUMBER_MASK = ~(~0L << CATEGORY_NUMBER_NBITS);
    private static final long CATEGORY_VERSION_MAJOR_MASK = ~(~0L << CATEGORY_VERSION_MAJOR_NBITS);
    private static final long CATEGORY_VERSION_MINOR_MASK = ~(~0L << CATEGORY_VERSION_MINOR_NBITS);

    private static final long DATA_ITEM_NUMBER_MASK = ~(~0L << DATA_ITEM_NUMBER_NBITS);
    private static final long DATA_ITEM_FORMAT_MASK = ~(~0L << DATA_ITEM_FORMAT_NBITS);
    private static final long DATA_ITEM_ID_MASK = ~(~0L << DATA_ITEM_ID_NBITS);

    private static final long SUB_A_BIT_PRESENCE_MASK = ~(~0L << SUB_A_BIT_PRESENCE_NBITS);
    private static final long SUB_A_FORMAT_MASK = ~(~0L << SUB_A_FORMAT_NBITS);
    private static final long SUB_B_BIT_PRESENCE_MASK = ~(~0L << SUB_B_BIT_PRESENCE_NBITS);
    private static final long SUB_B_FORMAT_MASK = ~(~0L << SUB_B_FORMAT_NBITS);
    private static final long DATA_FIELD_ID_MASK = ~(~0L << DATA_FIELD_ID_NBITS);

    private static final long BITS_FIELD_FROM_POS_MASK = ~(~0L << BITS_FIELD_FROM_POS_NBITS);
    private static final long BITS_FIELD_ENCODING_MASK = ~(~0L << BITS_FIELD_ENCODING_NBITS);

    private static final int CATEGORY_VERSION_MAJOR_SHIFT = CATEGORY_NUMBER_NBITS;
    private static final int CATEGORY_VERSION_MINOR_SHIFT = CATEGORY_VERSION_MAJOR_SHIFT + CATEGORY_VERSION_MAJOR_NBITS;
    private static final int DATA_ITEM_NUMBER_SHIFT = CATEGORY_VERSION_MINOR_SHIFT + CATEGORY_VERSION_MINOR_NBITS;
    private static final int DATA_ITEM_FORMAT_SHIFT = DATA_ITEM_NUMBER_SHIFT + DATA_ITEM_NUMBER_NBITS;
    private static final int SUB_A_BIT_PRESENCE_SHIFT = DATA_ITEM_FORMAT_SHIFT + DATA_ITEM_FORMAT_NBITS;
    private static final int SUB_A_FORMAT_SHIFT = SUB_A_BIT_PRESENCE_SHIFT + SUB_A_BIT_PRESENCE_NBITS;
    private static final int SUB_B_BIT_PRESENCE_SHIFT = SUB_A_FORMAT_SHIFT + SUB_A_FORMAT_NBITS;
    private static final int SUB_B_FORMAT_SHIFT = SUB_B_BIT_PRESENCE_SHIFT + SUB_B_BIT_PRESENCE_NBITS;
    private static final int BITS_FIELD_FROM_POS_SHIFT = SUB_B_FORMAT_SHIFT + SUB_B_FORMAT_NBITS;
    private static final int BITS_FIELD_ENCODING_SHIFT = BITS_FIELD_FROM_POS_SHIFT + BITS_FIELD_FROM_POS_NBITS;

    private static final String[] CATEGORY_NUMBERS = new String[NUM_CATEGORY_NUMBERS];
    private static final String[] CATEGORY_V_NUMBERS = new String[NUM_CATEGORY_NUMBERS];
    private static final String[] CATEGORY_I_NUMBERS = new String[NUM_CATEGORY_NUMBERS];
    private static final String[] CATEGORY_C_NUMBERS = new String[NUM_CATEGORY_NUMBERS];
    private static final String[] DATA_ITEM_NUMBERS = new String[NUM_DATA_ITEM_NUMBERS];

    private static final String[] DATA_FORMATS = {
            "FIXED",
            "EXTENDED",
            "REPETITIVE",
            "COMPOUND",
            "EXPLICIT",
            "EMPTY",
            "UNKNOWN",
            "UNKNOWN",
    };

    private static final String[] BITS_FIELD_ENCODINGS = {
            "UNSIGNED",
            "SIGNED",
            "VALUE",
            "VALUES",
            "MEASURE_UNSIGNED",
            "MEASURE_SIGNED",
            "MEASURE_GRAYCODE",
            "IA5",
            "HEX",
            "OCTAL",
            "ASCII",
            "ASCII_CHAR",
            "SPARE",
            "FX",
            "UNKNOWN",
            "UNKNOWN",
    };

    private static final String[] BITS_FIELD_ENCODING_NAMES = {
            "unsigned",
            "signed",
            "value",
            "values",
            "umeasure",
            "smeasure",
            "graycode",
            "ia5",
            "hex",
            "octal",
            "ascii",
            "ascii_char",
            "spare",
            "fx",
            "unknown",
            "unknown",
    };

    private static final char[] SHORT_DATA_FORMATS = {'F', 'X', 'R', 'C', 'E', 'T', '?', '?',};

    static {
        for (int catno = 0; catno < NUM_CATEGORY_NUMBERS; catno++) {
            final String s;
            if (catno < 10) {
                s = "00" + catno;
            } else if (catno < 100) {
                s = "0" + catno;
            } else {
                s = "" + catno;
            }

            CATEGORY_NUMBERS[catno] = s;
            CATEGORY_I_NUMBERS[catno] = "I" + s + '/';
            CATEGORY_C_NUMBERS[catno] = "I" + s + '_';
            CATEGORY_V_NUMBERS[catno] = "ASTERIX Category " + s + " Version ";
        }

        for (int no = 0; no < NUM_DATA_ITEM_NUMBERS; no++) {
            final String s;
            if (no == DATA_ITEM_SP) {
                s = "SP";
            } else if (no == DATA_ITEM_RE) {
                s = "RE";
            } else {
                if (no < 10) {
                    s = "00" + no;
                } else if (no < 100) {
                    s = "0" + no;
                } else {
                    s = Integer.toString(no);
                }
            }

            DATA_ITEM_NUMBERS[no] = s;
        }
    }

    public static long categoryId(final int number, final int majorVersion, final int minorVersion) {
        return CATEGORY_ID_MASK & ((number & CATEGORY_NUMBER_MASK) |
                ((majorVersion & CATEGORY_VERSION_MAJOR_MASK) << CATEGORY_VERSION_MAJOR_SHIFT) |
                ((minorVersion & CATEGORY_VERSION_MINOR_MASK) << CATEGORY_VERSION_MINOR_SHIFT));
    }

    public static long dataItemId(final long categoryId, final int number, final int format) {
        return DATA_ITEM_ID_MASK & (categoryId | ((number & DATA_ITEM_NUMBER_MASK) << DATA_ITEM_NUMBER_SHIFT) |
                ((format & DATA_ITEM_FORMAT_MASK) << DATA_ITEM_FORMAT_SHIFT));
    }

    public static long subAId(final long dataItemId, final int bitPresence, final int format) {
        return DATA_FIELD_ID_MASK & (dataItemId |
                ((bitPresence & SUB_A_BIT_PRESENCE_MASK) << SUB_A_BIT_PRESENCE_SHIFT) |
                ((format & SUB_A_FORMAT_MASK) << SUB_A_FORMAT_SHIFT));
    }

    public static long subBId(final long subAId, final int bitPresence, final int format) {
        return DATA_FIELD_ID_MASK & (subAId |
                ((bitPresence & SUB_B_BIT_PRESENCE_MASK) << SUB_B_BIT_PRESENCE_SHIFT) |
                ((format & SUB_B_FORMAT_MASK) << SUB_B_FORMAT_SHIFT));
    }

    public static long bitsFieldId(final long dataFieldId, final int from, final int encoding) {
        return dataFieldId | ((from & BITS_FIELD_FROM_POS_MASK) << BITS_FIELD_FROM_POS_SHIFT) |
                ((encoding & BITS_FIELD_ENCODING_MASK) << BITS_FIELD_ENCODING_SHIFT);
    }

    public static int getCategoryNumber(final long categoryId) {
        return (int) (categoryId & CATEGORY_NUMBER_MASK);
    }

    public static int getCategoryMajorVersionNumber(final long categoryId) {
        return (int) ((categoryId >> CATEGORY_VERSION_MAJOR_SHIFT) & CATEGORY_VERSION_MAJOR_MASK);
    }

    public static int getCategoryMinorVersionNumber(final long categoryId) {
        return (int) ((categoryId >> CATEGORY_VERSION_MINOR_SHIFT) & CATEGORY_VERSION_MINOR_MASK);
    }

    public static int getDataItemNumber(final long dataItemId) {
        return (int) ((dataItemId >> DATA_ITEM_NUMBER_SHIFT) & DATA_ITEM_NUMBER_MASK);
    }

    public static int getDataItemFormat(final long dataItemId) {
        return (int) ((dataItemId >> DATA_ITEM_FORMAT_SHIFT) & DATA_ITEM_FORMAT_MASK);
    }

    public static int getSubABitPresence(final long dataFieldId) {
        return (int) ((dataFieldId >> SUB_A_BIT_PRESENCE_SHIFT) & SUB_A_BIT_PRESENCE_MASK);
    }

    public static int getSubAFormat(final long dataFieldId) {
        return (int) ((dataFieldId >> SUB_A_FORMAT_SHIFT) & SUB_A_FORMAT_MASK);
    }

    public static int getSubBBitPresence(final long dataFieldId) {
        return (int) ((dataFieldId >> SUB_B_BIT_PRESENCE_SHIFT) & SUB_B_BIT_PRESENCE_MASK);
    }

    public static int getSubBFormat(final long dataFieldId) {
        return (int) ((dataFieldId >> SUB_B_FORMAT_SHIFT) & SUB_B_FORMAT_MASK);
    }

    public static int getBitsFieldFromPosition(final long bitsFieldId) {
        return (int) ((bitsFieldId >> BITS_FIELD_FROM_POS_SHIFT) & BITS_FIELD_FROM_POS_MASK);
    }

    public static int getBitsFieldEncoding(final long bitsFieldId) {
        return (int) ((bitsFieldId >> BITS_FIELD_ENCODING_SHIFT) & BITS_FIELD_ENCODING_MASK);
    }

    public static long getCategoryId(final long id) {
        return id & CATEGORY_ID_MASK;
    }

    public static long getDataItemId(final long id) {
        return id & DATA_ITEM_ID_MASK;
    }

    public static long getDataFieldId(final long id) {
        return id & DATA_FIELD_ID_MASK;
    }

    public static String getCategoryNumberString(final int categoryNumber) {
        return CATEGORY_NUMBERS[(int) (categoryNumber & CATEGORY_NUMBER_MASK)];
    }

    public static String getCategoryIdString(final long categoryId) {
        return CATEGORY_V_NUMBERS[getCategoryNumber(categoryId)] +
                getCategoryMajorVersionNumber(categoryId) +
                '.' + getCategoryMinorVersionNumber(categoryId);
    }

    public static String getDataItemNumberString(final int number) {
        return DATA_ITEM_NUMBERS[(int) (number & DATA_ITEM_NUMBER_MASK)];
    }

    public static String getDataFormatString(final int format) {
        return DATA_FORMATS[(int) (format & DATA_ITEM_FORMAT_MASK)];
    }

    public static String getDataItemIdString(final long dataItemId) {
        return CATEGORY_I_NUMBERS[getCategoryNumber(dataItemId)] +
                DATA_ITEM_NUMBERS[getDataItemNumber(dataItemId)];
    }

    public static String getDataItemIdString(final long bitsFieldId, final String name) {
        final String s = getDataItemIdString(bitsFieldId);
        return null == name ? s : s + '/' + name;
    }

    public static String getDataItemCode(final long dataItemId) {
        return CATEGORY_C_NUMBERS[getCategoryNumber(dataItemId)] +
                DATA_ITEM_NUMBERS[getDataItemNumber(dataItemId)];
    }

    public static String getDataFieldIdString(final long dataFieldId) {
        return getDataFieldIdString(dataFieldId, null);
    }

    public static String getDataFieldIdString(final long dataFieldId, final String name) {
        return getDataItemIdString(dataFieldId, name) + '(' +
                SHORT_DATA_FORMATS[getDataItemFormat(dataFieldId)] +
                SHORT_DATA_FORMATS[getSubAFormat(dataFieldId)] +
                SHORT_DATA_FORMATS[getSubBFormat(dataFieldId)] + '/' +
                getSubABitPresence(dataFieldId) + '/' +
                getSubBBitPresence(dataFieldId) + ')';
    }

    public static String getBitsFieldEncodingString(final int encoding) {
        return BITS_FIELD_ENCODINGS[(int) (encoding & BITS_FIELD_ENCODING_MASK)];
    }

    public static String getBitsFieldEncodingDisplayName(final int encoding) {
        return BITS_FIELD_ENCODING_NAMES[(int) (encoding & BITS_FIELD_ENCODING_MASK)];
    }

    public static String getBitsFieldIdString(final long bitsFieldId) {
        return getBitsFieldIdString(bitsFieldId, null);
    }

    public static String getBitsFieldIdString(final long bitsFieldId, final String name) {
        return getDataItemIdString(bitsFieldId, name) + '(' +
                SHORT_DATA_FORMATS[getDataItemFormat(bitsFieldId)] +
                SHORT_DATA_FORMATS[getSubAFormat(bitsFieldId)] +
                SHORT_DATA_FORMATS[getSubBFormat(bitsFieldId)] + '/' +
                getSubABitPresence(bitsFieldId) + '/' +
                getSubBBitPresence(bitsFieldId) + '/' +
                getBitsFieldFromPosition(bitsFieldId) + '/' +
                BITS_FIELD_ENCODINGS[getBitsFieldEncoding(bitsFieldId)] + ')';
    }

    /**
     * Private constructor.
     */
    private ASTERIXIds() {
    }
}
