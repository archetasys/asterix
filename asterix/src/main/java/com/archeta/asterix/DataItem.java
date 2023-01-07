/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;

import static com.archeta.asterix.ASTERIXConstants.DATA_FORMAT_EXPLICIT;
import static com.archeta.asterix.ASTERIXConstants.FX;
import static com.archeta.asterix.ASTERIXIds.*;

public final class DataItem {
    private static final String RE = "Reserved Expansion Field";
    private static final String SP = "Special Purpose Field";
    private static final String LF = System.lineSeparator();

    static DataItem from(
            final DataFormat format,
            final String name,
            final boolean mandatory,
            final String definition) {

        if (null == format) {
            throw new ASTERIXFormatException("`format` must not null");
        }

        final long id = format.id;
        if (Strings.isBlank(name)) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `name` must not null nor blank",
                    getDataItemIdString(id)));
        }

        if (Strings.isBlank(definition)) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `definition` must not null nor blank",
                    getDataItemIdString(id)));
        }

        return new DataItem(format, name, mandatory, definition);
    }

    static DataItem re(final long id) {
        checkFormatExplicit(id);
        return new DataItem(DataFormat.explicit(id), RE, false, RE);
    }

    static DataItem re(final DataFormat format) {
        checkFormatExplicit(format.id);
        return new DataItem(format, RE, false, format.description);
    }

    static DataItem sp(final long id) {
        checkFormatExplicit(id);
        return new DataItem(DataFormat.explicit(id), SP, false, SP);
    }

    static DataItem sp(final long categoryId, final int numOctets, final int bitPosition) {
        return new DataItem(DataFormat.empty(UserApplicationProfile.sp(categoryId, numOctets, bitPosition)), SP, false, SP);
    }

    static DataItem fx(final long categoryId, final int numOctets, final int bitPosition) {
        return new DataItem(DataFormat.empty(UserApplicationProfile.fx(categoryId, numOctets, bitPosition)), FX, false, FX);
    }

    private static void checkFormatExplicit(final long id) {
        final int fmt = getDataItemFormat(id);
        if (fmt != DATA_FORMAT_EXPLICIT) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s `format` must be %s not %s",
                    getDataItemIdString(id),
                    getDataFormatString(DATA_FORMAT_EXPLICIT),
                    getDataFormatString(fmt)));
        }
    }

    public final String name;
    public final boolean mandatory;
    public final String definition;
    public final String code;
    public final String code2;
    final DataFormat format;

    private DataItem(final DataFormat format, final String name, final boolean mandatory, final String definition) {
        this.name = name;
        this.mandatory = mandatory;
        this.definition = definition;
        this.format = format;
        final long id = format.id;
        code = getDataItemCode(id);
        code2 = getDataItemIdString(id);
    }

    public long getId() {
        return format.id;
    }

    public int getNumber() {
        return getDataItemNumber(format.id);
    }

    public void append(final boolean isShort, final boolean withDataFields, final Appendable out) throws IOException {
        if (withDataFields) {
            format.append(code2, isShort, out);
        } else {
            out.append(code2).append(' ').append(name).append(LF);
        }
    }
}
