/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

final class ImmutableMaps {
    static final int MIN_CAPACITY = 8;
    static final float DEFAULT_LOAD_FACTOR = 0.65f;
    static final String ERR_REUSED = "builder has been used to build a map, can not reuse the builder to build another map";

    static void validateLoadFactor(final float loadFactor) {
        if (loadFactor < 0.1f || loadFactor > 0.9f) {
            throw new IllegalArgumentException("load factor must be in the range of 0.1 to 0.9: " + loadFactor);
        }
    }

    private ImmutableMaps() {
    }
}
