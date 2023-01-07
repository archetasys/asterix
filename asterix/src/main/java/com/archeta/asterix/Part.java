/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.DATA_FORMAT_EXTENDED;
import static com.archeta.asterix.ASTERIXIds.*;

public final class Part {
    static Part of(final long id, final int numOctets, final BitsField ...fields) {
        return new Part(BitsFieldList.create(id, numOctets, fields));
    }

    final BitsFieldList fields;

    private Part(final BitsFieldList fields) {
        this.fields = fields;
    }

    public long getId() {
        return fields.id;
    }

    public int getNumber() {
        final long id = fields.id;
        return getDataItemFormat(id) == DATA_FORMAT_EXTENDED ? getSubABitPresence(id) : getSubBBitPresence(id);
    }
}
