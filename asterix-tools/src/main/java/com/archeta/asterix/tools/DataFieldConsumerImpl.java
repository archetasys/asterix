/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.*;

final class DataFieldConsumerImpl implements DataFieldConsumer {
    private static final long MISSING_DATA_FIELD_ID = 0;

    private final ImmutableObject2LongHashMap.Builder<String> dataFieldIdsByFullNamesMap =
            new ImmutableObject2LongHashMap.Builder<>(MISSING_DATA_FIELD_ID);
    private final StringBuilder sb = new StringBuilder(128);

    DataFieldConsumerImpl() {
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part, final DataField field) {
        final String fieldName = field.name;
        if ("FX".equals(fieldName) || "SPARE".equals(fieldName)) {
            return;
        }

        sb.setLength(0);
        sb.append(item.code2).append('/');

        if (subitem != null) {
            sb.append(subitem.name).append('/');
        }

        if (subfield != null) {
            sb.append(subfield.name).append('/');
        }

        sb.append(fieldName);

        final String name = sb.toString();
        if (dataFieldIdsByFullNamesMap.put(name, field.id) != MISSING_DATA_FIELD_ID) {
            throw new IllegalStateException("duplicate data field `" + name + '`');
        }
    }

    ImmutableObject2LongHashMap<String> getDataFieldIdsByFullNamesMap() {
        return dataFieldIdsByFullNamesMap.build();
    }
}
