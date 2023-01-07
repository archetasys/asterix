/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

final class ImmutableLong2LongHashMapTest {
    private static final long MISSING_VALUE = -1L;
    private ImmutableLong2LongHashMap.Builder builder;

    @BeforeEach
    void before() {
        builder = new ImmutableLong2LongHashMap.Builder(MISSING_VALUE);
    }

    @Test
    void shouldInitiallyEmpty() {
        final ImmutableLong2LongHashMap map = builder.build();

        assertEquals(0, map.size());
    }

    @Test
    void shouldReturnMissingValueWhenEmpty() {
        final ImmutableLong2LongHashMap map = builder.build();

        assertEquals(map.missingValue(), map.get(1L));
    }

    @Test
    void shouldGetReturnMissingValueWhenThereIsNoElement() {
        builder.put(1L, 1L);
        final ImmutableLong2LongHashMap map = builder.build();

        assertEquals(MISSING_VALUE, map.get(2L));
    }

    @Test
    void shouldFailReuseBuilder() {
        builder.build();

        assertThrows(IllegalStateException.class, () -> builder.put(1L, 1L));
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    void shouldGetReturnPutValue() {
        final long oldValue = builder.put(1L, 2L);

        assertEquals(MISSING_VALUE, oldValue);

        final ImmutableLong2LongHashMap map = builder.build();

        assertEquals(2L, map.get(1L));
    }

    @Test
    void shouldPutReturnOldValue() {
        final long oldValue = builder.put(1L, 2L);

        assertEquals(MISSING_VALUE, oldValue);
        assertEquals(2L, builder.put(1L, 3L));
    }

    @Test
    void shouldForEachLoopOverEveryElement() {
        builder.put(1L, 1L);
        builder.put(100L, 100L);

        final LongLongConsumer consumer = mock(LongLongConsumer.class);

        final ImmutableLong2LongHashMap map = builder.build();
        map.forEach(consumer);

        final InOrder inOrder = inOrder(consumer);
        inOrder.verify(consumer).accept(1L, 1L);
        inOrder.verify(consumer).accept(100L, 100L);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void shouldNotContainKeyOfAMissingKey() {
        final ImmutableLong2LongHashMap map = builder.build();

        assertFalse(map.containsKey(1L));
    }

    @Test
    void shouldContainKeyOfAPresentKey() {
        builder.put(1L, 1L);
        final ImmutableLong2LongHashMap map = builder.build();

        assertTrue(map.containsKey(1L));
    }

    @Test
    void shouldNotContainValueForAMissingEntry() {
        final ImmutableLong2LongHashMap map = builder.build();
        assertFalse(map.containsValue(1L));
    }

    @Test
    void shouldContainValueForAPresentEntry() {
        builder.put(1L, 1L);

        final ImmutableLong2LongHashMap map = builder.build();

        assertTrue(map.containsValue(1L));
    }

    @Test
    void shouldFailPutMissingValue() {
        assertThrows(IllegalArgumentException.class, () -> builder.put(1L, MISSING_VALUE));
    }

    @Test
    void shouldGrowWhenThresholdExceeded() {
        final float loadFactor = 0.5f;
        final int initialCapacity = 32;
        final int size = 16;
        final ImmutableLong2LongHashMap.Builder builder =
                new ImmutableLong2LongHashMap.Builder(MISSING_VALUE, initialCapacity, loadFactor);
        for (long i = 0; i < size; i++) {
            builder.put(i, i);
        }

        assertEquals(size, builder.resizeThreshold());
        assertEquals(initialCapacity, builder.capacity());
        assertEquals(size, builder.size());

        builder.put(16L, 16L);

        assertEquals(initialCapacity, builder.resizeThreshold());
        assertEquals(64, builder.capacity());
        assertEquals(17, builder.size());
        assertEquals(loadFactor, builder.loadFactor());

        final ImmutableLong2LongHashMap map = builder.build();
        assertEquals(16L, map.get(16L));
    }

    @Test
    void shouldHandleCollisionAndThenLinearProbe() {
        final float loadFactor = 0.5f;
        final int initialCapacity = 32;
        final ImmutableLong2LongHashMap.Builder builder =
                new ImmutableLong2LongHashMap.Builder(MISSING_VALUE, initialCapacity, loadFactor);
        final long key = 7L;
        final long value = 77L;
        builder.put(key, value);

        final long collisionKey = key + builder.capacity();
        final long collisionValue = 99999999L;
        builder.put(collisionKey, collisionValue);
        assertEquals(loadFactor, builder.loadFactor());

        final ImmutableLong2LongHashMap map = builder.build();
        assertEquals(value, map.get(key));
        assertEquals(collisionValue, map.get(collisionKey));
    }
}
