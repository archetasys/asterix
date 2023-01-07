/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.*;

public enum BitsFieldEncoding {
    UNSIGNED(BITS_FIELD_ENCODING_UNSIGNED),
    SIGNED(BITS_FIELD_ENCODING_SIGNED),
    VALUE(BITS_FIELD_ENCODING_VALUE),
    VALUES(BITS_FIELD_ENCODING_VALUES),
    MEASURE_UNSIGNED(BITS_FIELD_ENCODING_MEASURE_UNSIGNED),
    MEASURE_SIGNED(BITS_FIELD_ENCODING_MEASURE_SIGNED),
    MEASURE_GRAYCODE(BITS_FIELD_ENCODING_MEASURE_GRAYCODE),
    IA5(BITS_FIELD_ENCODING_IA5),
    HEX(BITS_FIELD_ENCODING_HEX),
    OCTAL(BITS_FIELD_ENCODING_OCTAL),
    ASCII(BITS_FIELD_ENCODING_ASCII),
    ASCII_CHAR(BITS_FIELD_ENCODING_ASCII_CHAR),
    SPARE(BITS_FIELD_ENCODING_SPARE),
    FX(BITS_FIELD_ENCODING_FX);

    private final int value;

    BitsFieldEncoding(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String getDisplayName() {
        return ASTERIXIds.getBitsFieldEncodingDisplayName(value);
    }
}
