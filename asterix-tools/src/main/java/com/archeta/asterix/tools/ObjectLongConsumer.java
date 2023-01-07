/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

@FunctionalInterface
interface ObjectLongConsumer<K> {
    void accept(K k, long v);
}
