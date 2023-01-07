/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.ASTERIXParser;
import com.archeta.asterix.DataBlockConsumer;
import com.archeta.asterix.RecordConsumer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

final class Records {
    private static final long MISSING_CATEGORY_ID = 0;

    static Records parse(final File infile, final LongList categoryIds) throws IOException {
        final AtomicLong packetCount = new AtomicLong();
        final AtomicLong blockCount = new AtomicLong();
        final AtomicLong recordCount = new AtomicLong();
        final ImmutableLong2LongHashMap.Builder categoryIdsMap = new ImmutableLong2LongHashMap.Builder(MISSING_CATEGORY_ID);
        final DataBlockConsumer blockConsumer = (b, n, p, t) -> {
            blockCount.set(n);
            packetCount.set(p);
            final long categoryId = b.getCategoryId();
            categoryIdsMap.put(categoryId, categoryId);
        };

        final RecordConsumer recordConsumer = (r, n, b, p, t) -> recordCount.set(n);
        final ASTERIXParser parser = ASTERIXParser.create(categoryIds.copyElements());
        final long t0 = System.nanoTime();
        parser.parsePCAP(infile, blockConsumer, recordConsumer);
        final long e0 = System.nanoTime() - t0;
        return new Records(packetCount.get(), blockCount.get(), recordCount.get(), LongList.fromKeys(categoryIdsMap.build()), e0);
    }

    final long packetCount;
    final long blockCount;
    final long recordCount;
    final long parseDurationNs;
    final LongList categoryIds;

    private Records(
            final long packetCount,
            final long blockCount,
            final long recordCount,
            final LongList categoryIds,
            final long parseDurationNs) {

        this.packetCount = packetCount;
        this.blockCount = blockCount;
        this.recordCount = recordCount;
        this.categoryIds = categoryIds;
        this.parseDurationNs = parseDurationNs;
    }
}
