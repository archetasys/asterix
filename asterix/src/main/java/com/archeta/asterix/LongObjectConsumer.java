/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * An (int, Object) primitive specialisation of a {@code BiConsumer}.
 */
@FunctionalInterface
public interface LongObjectConsumer<V> {
    void accept(long k, V v);
}
