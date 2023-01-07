/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.ASTERIXParser;
import com.archeta.asterix.ImmutableLong2ObjectHashMap;

import java.io.File;
import java.io.IOException;

final class FilteredDataFields {
    static FilteredDataFields readFromConfigFiles(final File categoriesFile, final File filterFile) throws IOException {
        // Read categories version from config file.
        final CategoryLineConsumer categoryLineConsumer = new CategoryLineConsumer();
        LineReader.readAll(categoriesFile, categoryLineConsumer);
        final LongList categoryIds = LongList.fromValues(categoryLineConsumer.getCategoryIdsByNumbersMap());

        // Create parser and get all data fields for each category found from the config file.
        final ASTERIXParser parser = ASTERIXParser.create(categoryIds.copyElements());
        final DataFieldConsumerImpl dataFieldConsumer = new DataFieldConsumerImpl();
        parser.forEachDataField(dataFieldConsumer);

        // Read data fields definition from filter config file, each data field definition must
        // be valid for any category set in the parser.
        final DataFieldLineConsumer c = new DataFieldLineConsumer(dataFieldConsumer.getDataFieldIdsByFullNamesMap());
        LineReader.readAll(filterFile, c);
        return new FilteredDataFields(
                LongList.fromKeys(c.getFilteredCategoryIdsMap()),
                c.getFilteredDataFieldFullNamesByIdsMap(),
                c.getFilteredDataFieldIdsByFullNamesMap());
    }

    final LongList categoryIds;
    final ImmutableLong2ObjectHashMap<String> dataFieldFullNamesByIdsMap;
    final ImmutableObject2LongHashMap<String> dataFieldIdsByFullNamesMap;

    private FilteredDataFields(
            final LongList categoryIds,
            final ImmutableLong2ObjectHashMap<String> dataFieldFullNamesByIdsMap,
            final ImmutableObject2LongHashMap<String> dataFieldIdsByFullNamesMap) {
        this.categoryIds = categoryIds;
        this.dataFieldFullNamesByIdsMap = dataFieldFullNamesByIdsMap;
        this.dataFieldIdsByFullNamesMap = dataFieldIdsByFullNamesMap;
    }
}
