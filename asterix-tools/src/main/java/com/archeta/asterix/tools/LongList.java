/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

final class LongList {
    private static final LongList EMPTY = new LongList(new long[0]);

    static LongList fromKeys(final ImmutableLong2LongHashMap map) {
        final int sz = map.size();
        if (sz < 1) {
            return EMPTY;
        }

        final LongLongConsumerImpl c = new LongLongConsumerImpl(sz, true);
        map.forEach(c);
        return new LongList(c.elements);
    }

    static LongList fromValues(final ImmutableLong2LongHashMap map) {
        final int sz = map.size();
        if (sz < 1) {
            return EMPTY;
        }

        final LongLongConsumerImpl c = new LongLongConsumerImpl(sz, false);
        map.forEach(c);
        return new LongList(c.elements);
    }

    private final long[] elements;

    private LongList(final long[] elements) {
        this.elements = elements;
    }

    int size() {
        return elements.length;
    }

    long get(final int index) {
        return elements[index];
    }

    long[] copyElements() {
        final int n = elements.length;
        final long[] copy = new long[n];
        System.arraycopy(elements, 0, copy, 0, n);
        return copy;
    }

    private static final class LongLongConsumerImpl implements LongLongConsumer {
        private final long[] elements;
        private final boolean keys;
        private int index;

        private LongLongConsumerImpl(final int size, final boolean keys) {
            this.keys = keys;
            elements = new long[size];
        }

        public void accept(final long k, final long v) {
            elements[index++] = keys ? k : v;
        }
    }
}
