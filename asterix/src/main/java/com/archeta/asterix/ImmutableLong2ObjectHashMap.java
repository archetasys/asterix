/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.util.Objects;

public final class ImmutableLong2ObjectHashMap<V> {
    private final int size;
    private final long[] keys;
    private final V[] values;

    private ImmutableLong2ObjectHashMap(final long[] keys, final V[] values, final int size) {
        this.keys = keys;
        this.values = values;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(final long key) {
        final long[] keys = this.keys;
        final V[] values = this.values;
        final int mask = values.length - 1;
        int index = hash(key) & mask;

        while (null != values[index]) {
            if (key == keys[index]) {
                return true;
            }

            index = ++index & mask;
        }

        return false;
    }

    public boolean containsValue(final Object value) {
        if (null == value) {
            return false;
        }


        final V[] values = this.values;
        final int length = values.length;
        V existingValue;
        for (int i = 0, remaining = size; remaining > 0 && i < length; i++) {
            if (null != (existingValue = values[i])) {
                if (Objects.equals(existingValue, value)) {
                    return true;
                }

                --remaining;
            }
        }

        return false;
    }

    public V get(final long key) {
        final long[] keys = this.keys;
        final V[] values = this.values;
        final int mask = values.length - 1;
        int index = hash(key) & mask;
        V value;
        while (null != (value = values[index])) {
            if (key == keys[index]) {
                break;
            }

            index = ++index & mask;
        }

        return value;
    }

    public void forEach(final LongObjectConsumer<? super V> consumer) {
        final long[] keys = this.keys;
        final V[] values = this.values;
        final int length = values.length;
        V value;
        for (int index = 0, remaining = size; remaining > 0 && index < length; index++) {
            if (null != (value = values[index])) {
                consumer.accept(keys[index], value);
                --remaining;
            }
        }
    }

    public static final class Builder<V> {
        private static final int MIN_CAPACITY = 8;
        private static final float DEFAULT_LOAD_FACTOR = 0.65f;
        private static final String ERR_REUSED = "builder has been used to build a map, can not reuse the builder to build another map";

        private final float loadFactor;
        private int resizeThreshold;
        private int size;
        private long[] keys;
        private V[] values;

        public Builder() {
            this(MIN_CAPACITY);
        }

        public Builder(final int initialCapacity) {
            this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }

        @SuppressWarnings("unchecked")
        public Builder(final int initialCapacity, final float loadFactor) {
            validateLoadFactor(loadFactor);
            this.loadFactor = loadFactor;
            final int capacity = findNextPositivePowerOfTwo(Math.max(MIN_CAPACITY, initialCapacity));
            resizeThreshold = (int) (capacity * loadFactor);
            keys = new long[capacity];
            values = (V[]) new Object[capacity];
            size = 0;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return values.length;
        }

        public int resizeThreshold() {
            return resizeThreshold;
        }

        public float loadFactor() {
            return loadFactor;
        }

        public V put(final long key, final V value) {
            if (null == keys) {
                throw new IllegalStateException(ERR_REUSED);
            }

            if (null == value) {
                throw new NullPointerException("`value` must not null");
            }

            final long[] keys = this.keys;
            final V[] values = this.values;
            final int mask = values.length - 1;
            int index = hash(key) & mask;

            V oldValue;
            while (null != (oldValue = values[index])) {
                if (key == keys[index]) {
                    break;
                }

                index = ++index & mask;
            }

            if (null == oldValue) {
                ++size;
                keys[index] = key;
            }

            values[index] = value;

            if (size > resizeThreshold) {
                increaseCapacity();
            }

            return oldValue;
        }

        public ImmutableLong2ObjectHashMap<V> build() {
            if (null == keys) {
                throw new IllegalStateException(ERR_REUSED);
            }

            final ImmutableLong2ObjectHashMap<V> m = new ImmutableLong2ObjectHashMap<>(keys, values, size);

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

            final long[] tempKeys = new long[newCapacity];
            final V[] tempValues = (V[]) new Object[newCapacity];

            final long[] keys = this.keys;
            final V[] values = this.values;
            for (int i = 0, size = values.length; i < size; i++) {
                final V value = values[i];
                if (null != value) {
                    final long key = keys[i];
                    int index = hash(key) & mask;
                    while (null != tempValues[index]) {
                        index = ++index & mask;
                    }

                    tempKeys[index] = key;
                    tempValues[index] = value;
                }
            }

            this.keys = tempKeys;
            this.values = tempValues;
        }

        private static void validateLoadFactor(final float loadFactor) {
            if (loadFactor < 0.1f || loadFactor > 0.9f) {
                throw new IllegalArgumentException("load factor must be in the range of 0.1 to 0.9: " + loadFactor);
            }
        }

        /**
         * Fast method of finding the next power of 2 greater than or equal to the supplied value.
         * <p>
         * If the value is &lt;= 0 then 1 will be returned.
         * <p>
         * This method is not suitable for {@link Integer#MIN_VALUE} or numbers greater than 2^30.
         * When provided then {@link Integer#MIN_VALUE} will be returned.
         *
         * @param value the value from which to search for next power of 2
         * @return the next power of 2 or the value itself if it is a power of 2
         */
        private static int findNextPositivePowerOfTwo(final int value) {
            return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value - 1));
        }
    }

    /**
     * Generate a hash for a long value.
     *
     * @param value the value to be hashed
     * @return the hashed value
     */
    private static int hash(final long value) {
        long x = value;

        x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L;
        x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL;
        x = x ^ (x >>> 31);

        return (int) x ^ (int) (x >>> 32);
    }
}
