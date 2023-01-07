/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

/**
 * Hashing functions for applying to integers.
 */
final class Hashing {
    /**
     * Generate a hash for a long value.
     *
     * @param value the value to be hashed
     * @return the hashed value
     */
    static int hash(final long value) {
        long x = value;

        x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L;
        x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL;
        x = x ^ (x >>> 31);

        return (int) x ^ (int) (x >>> 32);
    }

    /**
     * Generate an even hash for an int value and apply mask to get a remainder that will be even.
     *
     * @param value the value to be hashed
     * @param mask  the mask to be applied that must be a power of 2 - 1
     * @return the hash of the value which is always even
     */
    static int evenHash(final long value, final int mask) {
        final int hash = hash(value);
        final int evenHash = (hash << 1) - (hash << 8);
        return evenHash & mask;
    }

    /**
     * Private constructor.
     */
    private Hashing() {
    }
}
