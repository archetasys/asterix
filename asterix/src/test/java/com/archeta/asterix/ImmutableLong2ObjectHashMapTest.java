/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class ImmutableLong2ObjectHashMapTest {
    private ImmutableLong2ObjectHashMap.Builder<String> builder;

    @BeforeEach
    void before() {
        builder = new ImmutableLong2ObjectHashMap.Builder<>();
    }

    @Test
    void shouldPutAndThenGet() {
        final long key = 7L;
        final String value = "seven";
        builder.put(key, value);

        final ImmutableLong2ObjectHashMap<String> map = builder.build();

        assertEquals(value, map.get(key));
    }

    @Test
    void shouldContainsKey() {
        final long key = 7L;
        final String value = "seven";

        builder.put(key, value);

        final ImmutableLong2ObjectHashMap<String> map = builder.build();

        assertTrue(map.containsKey(key));
        assertFalse(map.containsKey(0L));
    }

    @Test
    void shouldContainsValue() {
        final long key = 7L;
        final String value = "seven";

        builder.put(key, value);

        final ImmutableLong2ObjectHashMap<String> map = builder.build();

        assertTrue(map.containsValue(value));
        assertFalse(map.containsValue("not-exist"));
    }

    @Test
    void shouldForEach() {
        final long count = 11L;
        for (long i = 0L; i < count; i++) {
            builder.put(i, Long.toString(i));
        }

        final ImmutableLong2ObjectHashMap<String> map = builder.build();
        map.forEach((key, value) -> assertEquals(value, String.valueOf(key)));
    }

    @Test
    void shouldFailReuseBuilder() {
        builder.build();

        assertThrows(IllegalStateException.class, () -> builder.put(1L, "foo"));
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    void shouldReplaceExistingValueForTheSameKey() {
        final long key = 7L;
        final String value = "seven";
        builder.put(key, value);

        final String newValue = "new seven";
        final String oldValue = builder.put(key, newValue);

        final ImmutableLong2ObjectHashMap<String> map = builder.build();

        assertEquals(value, oldValue);
        assertEquals(newValue, map.get(key));
        assertEquals(1, map.size());
    }

    @Test
    void shouldGrowWhenThresholdExceeded() {
        final float loadFactor = 0.5f;
        final int initialCapacity = 32;
        final int size = 16;
        final ImmutableLong2ObjectHashMap.Builder<String> builder =
                new ImmutableLong2ObjectHashMap.Builder<>(initialCapacity, loadFactor);
        for (long i = 0; i < size; i++) {
            builder.put(i, Long.toString(i));
        }

        assertEquals(size, builder.resizeThreshold());
        assertEquals(initialCapacity, builder.capacity());
        assertEquals(size, builder.size());

        builder.put(16L, "16");

        assertEquals(initialCapacity, builder.resizeThreshold());
        assertEquals(64, builder.capacity());
        assertEquals(17, builder.size());
        assertEquals(loadFactor, builder.loadFactor());

        final ImmutableLong2ObjectHashMap<String> map = builder.build();
        assertEquals("16", map.get(16L));
    }

    @Test
    void shouldHandleCollisionAndThenLinearProbe() {
        final float loadFactor = 0.5f;
        final int initialCapacity = 32;
        final ImmutableLong2ObjectHashMap.Builder<String> builder =
                new ImmutableLong2ObjectHashMap.Builder<>(initialCapacity, loadFactor);
        final long key = 7L;
        final String value = "seven";
        builder.put(key, value);

        final long collisionKey = key + builder.capacity();
        final String collisionValue = Long.toString(collisionKey);
        builder.put(collisionKey, collisionValue);
        assertEquals(loadFactor, builder.loadFactor());

        final ImmutableLong2ObjectHashMap<String> map = builder.build();
        assertEquals(value, map.get(key));
        assertEquals(collisionValue, map.get(collisionKey));
    }
}
