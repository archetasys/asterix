/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.NULL_CATEGORY_ID;

public final class DataBlock {
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private DataRecord[] records;
    private int numRecords = 0;
    private long categoryId = NULL_CATEGORY_ID;
    private int length = 0;

    public DataBlock() {
        records = new DataRecord[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            records[i] = new DataRecord();
        }
    }

    public int capacity() {
        return records.length;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public int getCategoryNumber() {
        return ASTERIXIds.getCategoryNumber(categoryId);
    }

    public int getLength() {
        return length;
    }

    public int getNumRecords() {
        return numRecords;
    }

    public DataRecord getRecord(final int index) {
        return records[index];
    }

    void reset(final long categoryId, final int length) {
        this.categoryId = categoryId;
        this.length = length;
        numRecords = 0;
    }

    DataRecord addRecord() {
        ensureCapacity(numRecords + 1);
        return records[numRecords++];
    }

    private void ensureCapacity(final int requiredCapacity) {
        final int currentCapacity = records.length;
        if (requiredCapacity > currentCapacity) {
            if (requiredCapacity > MAX_CAPACITY) {
                throw new IllegalStateException("max capacity: " + MAX_CAPACITY);
            }

            int newCapacity = currentCapacity > INITIAL_CAPACITY ? currentCapacity : INITIAL_CAPACITY;
            while (newCapacity < requiredCapacity) {
                newCapacity = newCapacity + (newCapacity >> 1);
                if (newCapacity < 0 || newCapacity >= MAX_CAPACITY) {
                    newCapacity = MAX_CAPACITY;
                }
            }

            final DataRecord[] newRecords = new DataRecord[newCapacity];
            System.arraycopy(records, 0, newRecords, 0, currentCapacity);
            for (int i = currentCapacity; i < newCapacity; i++) {
                newRecords[i] = new DataRecord();
            }

            records = newRecords;
        }
    }
}
