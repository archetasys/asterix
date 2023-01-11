/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;
import static com.archeta.asterix.BitsField.checkName;
import static com.archeta.asterix.DataFormat.*;

public final class Subfield {
    private static final String SPARE_DESCRIPTION = "Spare Subfield";

    static Subfield of(
            final DataFormat format,
            final int numOctets,
            final int number,
            final String name,
            final String description) {

        final long id = format.id;
        checkCompoundFormatId(id);
        checkPrimarySubfieldNumOctets(numOctets, id);
        checkName(name, id, true);
        checkSubfieldFormat(format);
        checkNumber(number, name, id);
        if (Strings.isBlank(description)) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s description must not null nor blank",
                    getDataFieldIdString(id)));
        }

        return new Subfield(number, name, description, format);
    }

    static Subfield sp(final long id, final int numOctets, final int number) {
        checkCompoundFormatId(id);
        checkPrimarySubfieldNumOctets(numOctets, id);
        checkNumber(number, SPARE, id);
        return new Subfield(number, SPARE, SPARE_DESCRIPTION, empty(id));
    }

    static Subfield fx(final long id, final int numOctets) {
        checkCompoundFormatId(id);
        checkPrimarySubfieldNumOctets(numOctets, id);
        return new Subfield(0, FX, FX, empty(id));
    }

    private static void checkNumber(final int number, final String name, final long id) {
        if (number < 0) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s number must not negative, number=%s",
                    getDataFieldIdString(id, name), number));
        }
    }

    public final int number;
    public final int bitPresence;
    public final String name;
    public final String description;
    final DataFormat format;

    private Subfield(final int number, final String name, final String description, final DataFormat format) {
        final long id = format.id;
        this.number = number;
        this.bitPresence = getDataItemFormat(id) == DATA_FORMAT_EXPLICIT ? getSubBBitPresence(id) : getSubABitPresence(id);
        this.name = name;
        this.description = description;
        this.format = format;
    }

    public long getId() {
        return format.id;
    }
}
