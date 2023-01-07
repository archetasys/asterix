/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.ASTERIXIds;
import com.archeta.asterix.ImmutableLong2ObjectHashMap;

final class DataFieldLineConsumer implements LineReader.LineConsumer {
    private static final long MISSING_DATA_FIELD_ID = 0;
    private static final long MISSING_CATEGORY_ID = 0;
    private static final ByteProcessor NON_WHITESPACE_COMMENT = (b) -> b != 0x20 && b != 0x09 && b != 0x23 /* # */;

    private final ImmutableLong2ObjectHashMap.Builder<String> filteredDataFieldFullNamesByIdMap =
            new ImmutableLong2ObjectHashMap.Builder<>();
    private final ImmutableObject2LongHashMap.Builder<String> filteredDataFieldIdsByFullNamesMap =
            new ImmutableObject2LongHashMap.Builder<>(MISSING_DATA_FIELD_ID);
    private final ImmutableLong2LongHashMap.Builder filteredCategoryIdsMap =
            new ImmutableLong2LongHashMap.Builder(MISSING_CATEGORY_ID);
    private final ImmutableObject2LongHashMap<String> dataFieldIdsByFullNamesMap;
    private int lineno;

    DataFieldLineConsumer(final ImmutableObject2LongHashMap<String> dataFieldIdsByFullNamesMap) {
        this.dataFieldIdsByFullNamesMap = dataFieldIdsByFullNamesMap;
    }

    public boolean onNext(final DirectBuffer buffer, final int offset, final int length) {
        lineno++;

        final int end = buffer.forEachByte(offset, length, NON_WHITESPACE_COMMENT);
        final int len = end == -1 ? length : end - offset;
        final String name = buffer.getStringAsciiNoLength(offset, len);
        final long id = dataFieldIdsByFullNamesMap.get(name);
        if (id == MISSING_DATA_FIELD_ID) {
            System.err.printf("unknown data field name `%s` at line %d%n", name, lineno);
            return false;
        }

        if (filteredDataFieldIdsByFullNamesMap.put(name, id) != MISSING_DATA_FIELD_ID) {
            System.err.printf("duplicate data field name `%s` at line %d%n", name, lineno);
            return false;
        }

        if (filteredDataFieldFullNamesByIdMap.put(id, name) != null) {
            System.err.printf("duplicate data field identifier %d (`%s`) at line %d%n", id, name, lineno);
            return false;
        }

        final long categoryId = ASTERIXIds.getCategoryId(id);
        filteredCategoryIdsMap.put(categoryId,categoryId);

        return true;
    }

    ImmutableLong2ObjectHashMap<String> getFilteredDataFieldFullNamesByIdsMap() {
        return filteredDataFieldFullNamesByIdMap.build();
    }

    ImmutableObject2LongHashMap<String> getFilteredDataFieldIdsByFullNamesMap() {
        return filteredDataFieldIdsByFullNamesMap.build();
    }

    ImmutableLong2LongHashMap getFilteredCategoryIdsMap() {
        return filteredCategoryIdsMap.build();
    }
}
