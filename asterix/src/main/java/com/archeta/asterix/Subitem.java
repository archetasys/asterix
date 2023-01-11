/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.SPARE;
import static com.archeta.asterix.ASTERIXIds.getDataFieldIdString;
import static com.archeta.asterix.ASTERIXIds.getSubABitPresence;
import static com.archeta.asterix.BitsField.checkName;
import static com.archeta.asterix.DataFormat.*;

public final class Subitem {
    private static final String SPARE_DESCRIPTION = "Spare Subitem";

    static Subitem of(final DataFormat format, final int number, final String name, final String description) {
        final long id = format.id;
        checkExplicitFormatId(id);
        checkName(name, id, true);
        checkSubitemFormat(format);
        if (Strings.isBlank(description)) {
            throw new ASTERIXFormatException(Fmt.sprintf(
                    "%s description must not null nor blank",
                    getDataFieldIdString(id)));
        }

        return new Subitem(number, name, description, format);
    }

    static Subitem sp(final long id, final int number) {
        checkExplicitFormatId(id);
        return new Subitem(number, SPARE, SPARE_DESCRIPTION, DataFormat.empty(id));
    }

    public final int number;
    public final int bitPresence;
    public final String name;
    public final String description;
    final DataFormat format;

    private Subitem(final int number, final String name, final String description, final DataFormat format) {
        this.number = number;
        this.bitPresence = getSubABitPresence(format.id);
        this.name = name;
        this.description = description;
        this.format = format;
    }

    public long getId() {
        return format.id;
    }
}
