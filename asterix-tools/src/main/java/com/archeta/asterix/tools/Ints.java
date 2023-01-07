/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

final class Ints {
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
    static int findNextPositivePowerOfTwo(final int value) {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * Private constructor.
     */
    private Ints() {
    }
}
