/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;

import static com.archeta.asterix.ASTERIXIds.*;

final class ASTERIXCategory {
    private static final String LF = System.lineSeparator();
    
    final long id;
    final String name;
    final UserApplicationProfile uap;

    ASTERIXCategory(final String name, final UserApplicationProfile uap) {
        if (Strings.isBlank(name)) {
            throw new ASTERIXFormatException("`name` must not null nor blank");
        }

        this.id = uap.categoryId;
        this.name = name;
        this.uap = uap;
    }
    
    void append(final boolean isShort, final Appendable out) throws IOException {
        if (isShort) {
            out.append(getCategoryNumberString(getCategoryNumber(id))).append(':')
                    .append(Integer.toString(getCategoryMajorVersionNumber(id))).append('.')
                    .append(Integer.toString(getCategoryMinorVersionNumber(id))).append(LF);
        } else {
            out.append(getCategoryIdString(id)).append(' ').append(name).append(LF);
        }
    }

    void append(
            final boolean withDataItems,
            final boolean withDataFields,
            final boolean withUAP,
            final boolean isShort,
            final Appendable out) throws IOException {

        out.append(getCategoryIdString(id)).append(' ').append(name).append(LF).append(LF);
        uap.append(withDataItems, withDataFields, withUAP, isShort, out);
    }
}
