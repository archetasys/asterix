/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * ASTERIX constants.
 */
public final class ASTERIXConstants {
    public static final int CAT_LEN_NUM_OCTETS = 3;

    /**
     * Maximum value for ASTERIX Category number.
     */
    public static final int CATEGORY_MAX_NUMBER = 255;

    /**
     * The number of category numbers.
     */
    public static final int NUM_CATEGORY_NUMBERS = CATEGORY_MAX_NUMBER + 1;

    /**
     * Maximum value for Data Item number.
     */
    public static final int DATA_ITEM_MAX_NUMBER = 1023;

    /**
     * The number of Data Item numbers.
     */
    public static final int NUM_DATA_ITEM_NUMBERS = DATA_ITEM_MAX_NUMBER + 1;

    /**
     * Reserved Data Item number for Special Purpose Field.
     */
    public static final int DATA_ITEM_SP = DATA_ITEM_MAX_NUMBER - 3;

    /**
     * Reserved Data Item number for Reserved Expansion Field.
     */
    public static final int DATA_ITEM_RE = DATA_ITEM_MAX_NUMBER - 2;

    /**
     * Reserved Data Item number for UAP bit in UAP.
     */
    public static final int DATA_ITEM_UAP_SP = DATA_ITEM_MAX_NUMBER - 1;

    /**
     * Reserved Data Item number for FX bit in UAP.
     */
    public static final int DATA_ITEM_UAP_FX = DATA_ITEM_MAX_NUMBER;

    /**
     * Minimum number of FSPEC octets.
     */
    public static final int FSPEC_MIN_NUM_OCTETS = 1;

    /**
     * Maximum number of FSPEC octets. This limit allows to parse the number of octets of the FSPEC
     * and concatenates each octet up to a long value. The FSPEC long value is used to parse each
     * UAP bit on it without accessing the buffer again.
     */
    public static final int FSPEC_MAX_NUM_OCTETS = 8;
    public static final int FSPEC_MIN_LENGTH = FSPEC_MIN_NUM_OCTETS << 3;
    public static final int FSPEC_MAX_LENGTH = FSPEC_MAX_NUM_OCTETS << 3;

    public static final int DATA_ITEMS_MIN_LENGTH = FSPEC_MIN_LENGTH;
    public static final int DATA_ITEMS_MAX_LENGTH = FSPEC_MAX_LENGTH;

    public static final int DATA_FORMAT_FIXED = 0;
    public static final int DATA_FORMAT_EXTENDED = 1;
    public static final int DATA_FORMAT_REPETITIVE = 2;
    public static final int DATA_FORMAT_COMPOUND = 3;
    public static final int DATA_FORMAT_EXPLICIT = 4;
    public static final int DATA_FORMAT_EMPTY = 5;

    /**
     * The maximum number of octets of a data field.
     */
    public static final int DATA_FIELD_MAX_NUM_OCTETS = ~(~0 << 16) - (CAT_LEN_NUM_OCTETS + FSPEC_MIN_NUM_OCTETS);

    public static final int BITS_FIELD_ENCODING_UNSIGNED = 0;
    public static final int BITS_FIELD_ENCODING_SIGNED = 1;
    public static final int BITS_FIELD_ENCODING_VALUE = 2;
    public static final int BITS_FIELD_ENCODING_VALUES = 3;
    public static final int BITS_FIELD_ENCODING_MEASURE_UNSIGNED = 4;
    public static final int BITS_FIELD_ENCODING_MEASURE_SIGNED = 5;
    public static final int BITS_FIELD_ENCODING_MEASURE_GRAYCODE = 6;
    public static final int BITS_FIELD_ENCODING_IA5 = 7;
    public static final int BITS_FIELD_ENCODING_HEX = 8;
    public static final int BITS_FIELD_ENCODING_OCTAL = 9;
    public static final int BITS_FIELD_ENCODING_ASCII = 10;
    public static final int BITS_FIELD_ENCODING_ASCII_CHAR = 11;
    public static final int BITS_FIELD_ENCODING_SPARE = 12;
    public static final int BITS_FIELD_ENCODING_FX = 13;

    public static final int BITS_FIELD_ENCODING_ASCII_CHAR_NUM_BITS = 8;
    public static final int BITS_FIELD_ENCODING_OCTAL_NUM_BITS = 12;
    public static final int BITS_FIELD_ENCODING_GRAYCODE_NUM_BITS = 12;
    public static final int BITS_FIELD_ENCODING_IA5_CHAR_GROUP = 6;
    public static final int BITS_FIELD_ENCODING_IA5_CHAR_GROUP_NUM_BITS = BITS_FIELD_ENCODING_IA5_CHAR_GROUP * Byte.SIZE;

    /**
     * Maximum number of bits that can fit in a {@code long} value.
     */
    public static final int BITS_FIELD_MAX_NUM_BITS = 64;

    /**
     * The minimum length of the bits field name.
     */
    public static final int BITS_FIELD_MIN_NAME_LENGTH = 1;

    /**
     * The maximum length of the bits field name.
     */
    public static final int BITS_FIELD_MAX_NAME_LENGTH = 12;

    /**
     * The minimum number of octets of a data block or record.
     */
    public static final int RECORD_MIN_NUM_OCTETS = CAT_LEN_NUM_OCTETS + FSPEC_MIN_NUM_OCTETS;

    public static final String SPARE = "SPARE";
    public static final String FX = "FX";

    public static final long NULL_CATEGORY_ID = 0L;

    public static final int PRIMARY_SUBFIELD_MAX_NUM_OCTETS = 8;
    public static final int ITEMS_INDICATOR_MAX_NUM_OCTETS = 8;

    /**
     * Private constructor.
     */
    private ASTERIXConstants() {
    }
}
