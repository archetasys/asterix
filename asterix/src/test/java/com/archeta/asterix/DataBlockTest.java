/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.archeta.asterix.ASTERIXConstants.FSPEC_MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

final class DataBlockTest {
    @Test
    void shouldCreateDefault() {
        final DataBlock block = new DataBlock();

        assertEmptyDataBlock(block);
    }

    @Test
    void shouldExpandWhenAddRecord() {
        final DataBlock block = new DataBlock();
        final List<Record> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            records.add(block.addRecord());
            assertEmptyRecord(records.get(i));
        }

        assertEquals(10, block.capacity());
        assertEquals(10, block.getNumRecords());
        assertEquals(10, records.size());

        for (int i = 0; i < 10; i++) {
            assertSame(records.get(i), block.getRecord(i));
        }

        final Record record = block.addRecord();
        records.add(record);

        assertEmptyRecord(record);
        assertEquals(15, block.capacity());
        assertEquals(11, block.getNumRecords());

        for (int i = 0; i < 11; i++) {
            assertSame(records.get(i), block.getRecord(i));
        }
    }

    @Test
    void shouldReset() {
        final DataBlock block = new DataBlock();
        for (int i = 0; i < 5; i++) {
            assertEmptyRecord(block.addRecord());
        }

        assertEquals(10, block.capacity());
        assertEquals(5, block.getNumRecords());

        block.reset(0L, 0);

        assertEmptyDataBlock(block);

        block.reset(2L, 2);

        assertEquals(0, block.getNumRecords());
        assertEquals(2, block.getLength());
        assertEquals(2L, block.getCategoryId());
        assertEquals(2, block.getCategoryNumber());
    }

    private static void assertEmptyRecord(final Record record) {
        assertEquals(0L, record.getCategoryId());
        assertEquals(0, record.getCategoryNumber());
        assertEquals(0, record.getLength());
        assertEquals(0L, record.getFSPEC());
        assertEquals(0, record.getFSPECOffset());
        assertEquals(0, record.getFSPECNumOctets());
        assertEquals(0, record.getDataItemsNumOctets());
        for (int i = 0; i < FSPEC_MAX_LENGTH; i++) {
            assertEquals(-1, record.getDataItemOffset(i));
        }
    }

    private static void assertEmptyDataBlock(final DataBlock block) {
        assertEquals(0, block.getNumRecords());
        assertEquals(0, block.getLength());
        assertEquals(0L, block.getCategoryId());
        assertEquals(0, block.getCategoryNumber());
        assertEquals(10, block.capacity());
        for (int i = 0; i < 10; i++) {
            assertEmptyRecord(block.getRecord(i));
        }
    }
}
