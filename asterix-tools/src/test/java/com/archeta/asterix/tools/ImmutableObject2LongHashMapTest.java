/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class ImmutableObject2LongHashMapTest {
    private static final long MISSING_VALUE = -1L;

    private ImmutableObject2LongHashMap.Builder<String> builder;

    @BeforeEach
    void before() {
        builder = new ImmutableObject2LongHashMap.Builder<>(MISSING_VALUE);
    }

    @Test
    void shouldPutAndThenGet() {
        final String key = "seven";
        final long value = 7L;
        builder.put(key, value);

        final ImmutableObject2LongHashMap<String> map = builder.build();

        assertEquals(value, map.get(key));
        assertEquals(map.missingValue(), map.get("not-exist"));
    }

    @Test
    void shouldContainsKey() {
        final String key = "seven";
        final long value = 7L;

        builder.put(key, value);

        final ImmutableObject2LongHashMap<String> map = builder.build();

        assertTrue(map.containsKey(key));
        assertFalse(map.containsKey(0L));
    }

    @Test
    void shouldContainsValue() {
        final String key = "seven";
        final long value = 7L;

        builder.put(key, value);

        final ImmutableObject2LongHashMap<String> map = builder.build();

        assertTrue(map.containsValue(value));
        assertFalse(map.containsValue(999L));
    }

    @Test
    void shouldForEach() {
        final long count = 11L;
        for (long i = 0L; i < count; i++) {
            builder.put(Long.toString(i), i);
        }

        final ImmutableObject2LongHashMap<String> map = builder.build();
        map.forEach((key, value) -> assertEquals(value, Long.parseLong(key)));
    }

    @Test
    void shouldFailReuseBuilder() {
        builder.build();

        assertThrows(IllegalStateException.class, () -> builder.put("foo", 1L));
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    void shouldReplaceExistingValueForTheSameKey() {
        final String key = "seven";
        final long value = 7L;
        builder.put(key, value);

        final long newValue = 8L;
        final long oldValue = builder.put(key, newValue);

        final ImmutableObject2LongHashMap<String> map = builder.build();

        assertEquals(value, oldValue);
        assertEquals(newValue, map.get(key));
        assertEquals(1, map.size());
    }

    @Test
    void shouldGrowWhenThresholdExceeded() {
        final float loadFactor = 0.5f;
        final int initialCapacity = 32;
        final int size = 16;
        final ImmutableObject2LongHashMap.Builder<String> builder =
                new ImmutableObject2LongHashMap.Builder<>(MISSING_VALUE, initialCapacity, loadFactor);
        for (long i = 0; i < size; i++) {
            builder.put(Long.toString(i), i);
        }

        assertEquals(size, builder.resizeThreshold());
        assertEquals(initialCapacity, builder.capacity());
        assertEquals(size, builder.size());

        builder.put("16", 16L);

        assertEquals(initialCapacity, builder.resizeThreshold());
        assertEquals(64, builder.capacity());
        assertEquals(17, builder.size());
        assertEquals(loadFactor, builder.loadFactor());

        final ImmutableObject2LongHashMap<String> map = builder.build();
        assertEquals(16L, map.get("16"));
    }
}
