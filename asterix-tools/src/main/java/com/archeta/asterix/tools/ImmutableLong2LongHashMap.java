/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import java.util.Arrays;

import static com.archeta.asterix.tools.ImmutableMaps.*;

final class ImmutableLong2LongHashMap {
    private final long missingValue;
    private final int size;
    private final long[] entries;

    private ImmutableLong2LongHashMap(final long[] entries, final int size, final long missingValue) {
        this.entries = entries;
        this.size = size;
        this.missingValue = missingValue;
    }

    int size() {
        return size;
    }

    long missingValue() {
        return missingValue;
    }

    boolean containsKey(final long key) {
        return get(key) != missingValue;
    }

    boolean containsValue(final long value) {
        final long missingValue = this.missingValue;
        if (value == missingValue) {
            return false;
        }

        final long[] entries = this.entries;
        final int length = entries.length;
        int remaining = size;
        long existingValue;
        for (int valueIndex = 1; remaining > 0 && valueIndex < length; valueIndex += 2) {
            if (missingValue != (existingValue = entries[valueIndex])) {
                if (existingValue == value) {
                    return true;
                }

                --remaining;
            }
        }

        return false;
    }

    long get(final long key) {
        final long missingValue = this.missingValue;
        final long[] entries = this.entries;
        final int mask = entries.length - 1;
        int index = Hashing.evenHash(key, mask);

        long value;
        while (missingValue != (value = entries[index + 1])) {
            if (key == entries[index]) {
                break;
            }

            index = next(index, mask);
        }

        return value;
    }

    void forEach(final LongLongConsumer consumer) {
        final long missingValue = this.missingValue;
        final long[] entries = this.entries;
        final int length = entries.length;
        long value;
        for (int valueIndex = 1, remaining = size; remaining > 0 && valueIndex < length; valueIndex += 2) {
            if (missingValue != (value = entries[valueIndex])) {
                consumer.accept(entries[valueIndex - 1], value);
                --remaining;
            }
        }
    }

    private static int next(final int index, final int mask) {
        return (index + 2) & mask;
    }

    static final class Builder {
        private final float loadFactor;
        private final long missingValue;
        private int resizeThreshold;
        private int size;
        private long[] entries;

        Builder(final long missingValue) {
            this(missingValue, MIN_CAPACITY);
        }

        Builder(final long missingValue, final int initialCapacity) {
            this(missingValue, initialCapacity, DEFAULT_LOAD_FACTOR);
        }

        Builder(final long missingValue, final int initialCapacity, final float loadFactor) {
            validateLoadFactor(loadFactor);
            this.loadFactor = loadFactor;
            this.missingValue = missingValue;
            final int capacity = Ints.findNextPositivePowerOfTwo(Math.max(MIN_CAPACITY, initialCapacity));
            capacity(capacity);
            size = 0;
        }

        int size() {
            return size;
        }

        int capacity() {
            return entries.length >> 1;
        }

        int resizeThreshold() {
            return resizeThreshold;
        }

        float loadFactor() {
            return loadFactor;
        }

        long put(final long key, final long value) {
            if (null == entries) {
                throw new IllegalStateException(ERR_REUSED);
            }

            final long missingValue = this.missingValue;
            if (missingValue == value) {
                throw new IllegalArgumentException("`value` must not equals missing value");
            }

            final long[] entries = this.entries;
            final int mask = entries.length - 1;
            int index = Hashing.evenHash(key, mask);

            long oldValue;
            while (missingValue != (oldValue = entries[index + 1])) {
                if (key == entries[index]) {
                    break;
                }

                index = next(index, mask);
            }

            if (missingValue == oldValue) {
                ++size;
                entries[index] = key;
            }

            entries[index + 1] = value;

            increaseCapacity();

            return oldValue;
        }

        ImmutableLong2LongHashMap build() {
            if (null == entries) {
                throw new IllegalStateException(ERR_REUSED);
            }

            final ImmutableLong2LongHashMap m = new ImmutableLong2LongHashMap(entries, size, missingValue);

            // Make sure builder is not reused.
            entries = null;
            size = 0;

            return m;
        }

        private void increaseCapacity() {
            if (size > resizeThreshold) {
                // entries.length = 2 * capacity
                final int newCapacity = entries.length;
                rehash(newCapacity);
            }
        }

        private void rehash(final int newCapacity) {
            final long missingValue = this.missingValue;
            final long[] oldEntries = entries;
            final int length = oldEntries.length;

            capacity(newCapacity);

            final long[] newEntries = entries;
            final int mask = newEntries.length - 1;

            for (int keyIndex = 0; keyIndex < length; keyIndex += 2) {
                final long value = oldEntries[keyIndex + 1];
                if (missingValue != value) {
                    final long key = oldEntries[keyIndex];
                    int index = Hashing.evenHash(key, mask);

                    while (missingValue != newEntries[index + 1]) {
                        index = next(index, mask);
                    }

                    newEntries[index] = key;
                    newEntries[index + 1] = value;
                }
            }
        }

        private void capacity(final int newCapacity) {
            final int entriesLength = newCapacity << 1;
            if (entriesLength < 0) {
                throw new IllegalStateException("max capacity reached at size=" + size);
            }

            resizeThreshold = (int) (newCapacity * loadFactor);
            entries = new long[entriesLength];
            Arrays.fill(entries, missingValue);
        }
    }
}
