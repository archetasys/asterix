/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import java.util.Arrays;
import java.util.Objects;

import static com.archeta.asterix.tools.ImmutableMaps.*;

final class ImmutableObject2LongHashMap<K> {
    private final long missingValue;
    private final K[] keys;
    private final long[] values;
    private final int size;

    private ImmutableObject2LongHashMap(final K[] keys, final long[] values, final int size, final long missingValue) {
        this.keys = keys;
        this.values = values;
        this.size = size;
        this.missingValue = missingValue;
    }

    int size() {
        return size;
    }

    long missingValue() {
        return missingValue;
    }

    boolean containsKey(final Object key) {
        return get(key) != missingValue;
    }

    boolean containsValue(final long value) {
        if (value == missingValue) {
            return false;
        }

        final long[] values = this.values;
        final int length = values.length;
        for (int i = 0; i < length; i++) {
            if (value == values[i]) {
                return true;
            }
        }

        return false;
    }

    long get(final Object key) {
        final long missingValue = this.missingValue;
        final K[] keys = this.keys;
        final long[] values = this.values;
        final int mask = values.length - 1;
        int index = key.hashCode() & mask;
        long value;
        while (missingValue != (value = values[index])) {
            if (Objects.equals(keys[index], key)) {
                break;
            }

            index = ++index & mask;
        }

        return value;
    }

    void forEach(final ObjectLongConsumer<? super K> consumer) {
        final long missingValue = this.missingValue;
        final K[] keys = this.keys;
        final long[] values = this.values;
        final int length = values.length;
        long value;
        for (int index = 0, remaining = size; remaining > 0 && index < length; index++) {
            if (missingValue != (value = values[index])) {
                consumer.accept(keys[index], value);
                --remaining;
            }
        }
    }

    static final class Builder<K> {
        private final float loadFactor;
        private final long missingValue;
        private int resizeThreshold;
        private int size;
        private K[] keys;
        private long[] values;

        Builder(final long missingValue) {
            this(missingValue, MIN_CAPACITY);
        }

        Builder(final long missingValue, final int initialCapacity) {
            this(missingValue, initialCapacity, DEFAULT_LOAD_FACTOR);
        }

        @SuppressWarnings("unchecked")
        Builder(final long missingValue, final int initialCapacity, final float loadFactor) {
            validateLoadFactor(loadFactor);
            this.loadFactor = loadFactor;
            this.missingValue = missingValue;
            final int capacity = Ints.findNextPositivePowerOfTwo(Math.max(MIN_CAPACITY, initialCapacity));
            resizeThreshold = (int) (capacity * loadFactor);
            keys = (K[]) new Object[capacity];
            values = new long[capacity];
            size = 0;
            Arrays.fill(values, missingValue);
        }

        int size() {
            return size;
        }

        int capacity() {
            return values.length;
        }

        int resizeThreshold() {
            return resizeThreshold;
        }

        float loadFactor() {
            return loadFactor;
        }

        long put(final K key, final long value) {
            if (null == keys) {
                throw new IllegalStateException(ERR_REUSED);
            }

            if (null == key) {
                throw new IllegalArgumentException("`key` must not null");
            }

            final long missingValue = this.missingValue;
            if (missingValue == value) {
                throw new IllegalArgumentException("`value` must not equals missing value");
            }

            final K[] keys = this.keys;
            final long[] values = this.values;
            final int mask = values.length - 1;
            int index = key.hashCode() & mask;

            long oldValue;
            while (missingValue != (oldValue = values[index])) {
                if (Objects.equals(keys[index], key)) {
                    break;
                }

                index = ++index & mask;
            }

            if (missingValue == oldValue) {
                ++size;
                keys[index] = key;
            }

            values[index] = value;

            if (size > resizeThreshold) {
                increaseCapacity();
            }

            return oldValue;
        }

        ImmutableObject2LongHashMap<K> build() {
            if (null == keys) {
                throw new IllegalStateException(ERR_REUSED);
            }

            final ImmutableObject2LongHashMap<K> m = new ImmutableObject2LongHashMap<>(keys, values, size, missingValue);

            // Make sure builder is not reused.
            keys = null;
            values = null;
            size = 0;

            return m;
        }

        private void increaseCapacity() {
            final int newCapacity = values.length << 1;
            if (newCapacity < 0) {
                throw new IllegalStateException("max capacity reached at size=" + size);
            }

            rehash(newCapacity);
        }

        @SuppressWarnings("unchecked")
        private void rehash(final int newCapacity) {
            final int mask = newCapacity - 1;
            resizeThreshold = (int) (newCapacity * loadFactor);

            final K[] tempKeys = (K[]) new Object[newCapacity];
            final long[] tempValues = new long[newCapacity];
            Arrays.fill(tempValues, missingValue);

            final K[] keys = this.keys;
            final long[] values = this.values;
            for (int i = 0, size = values.length; i < size; i++) {
                final long value = values[i];
                if (missingValue != value) {
                    final K key = keys[i];
                    int index = key.hashCode() & mask;
                    while (missingValue != tempValues[index]) {
                        index = ++index & mask;
                    }

                    tempKeys[index] = key;
                    tempValues[index] = value;
                }
            }

            this.keys = tempKeys;
            this.values = tempValues;
        }
    }
}
