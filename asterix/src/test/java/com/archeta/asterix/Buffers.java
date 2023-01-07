/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

final class Buffers {
    /**
     * Returns true if and only if the two specified buffers are identical to each other for
     * {@code length} bytes starting at {@code aidx} index for the {@code a} buffer and {@code bidx}
     * index for the {@code b} buffer. A more compact way to express this is:
     * <p>
     * {@code a[aidx : aidx + length] == b[bidx : bidx + length]}
     *
     * @param a      the first buffer to test for equality
     * @param aidx   the start index of the {@code a}
     * @param b      the second buffer to test for equality
     * @param bidx   the start index of the {@code b}
     * @param length the number of bytes to test for equality
     * @return true if and only if {@code a} are identical with {@code b}, false otherwise
     */
    static boolean equals(final Buffer a, final int aidx, final Buffer b, final int bidx, final int length) {
        if (aidx < 0 || bidx < 0 || length < 0) {
            throw new IllegalArgumentException("all indexes and lengths must be non-negative");
        }

        final int longCount = length >>> 3;
        final int byteCount = length & 7;
        int _aidx = aidx;
        int _bidx = bidx;
        for (int i = longCount; i > 0; i--) {
            if (a.getLong(_aidx) != b.getLong(_bidx)) {
                return false;
            }

            _aidx += 8;
            _bidx += 8;
        }

        for (int i = byteCount; i > 0; i--, _aidx++, _bidx++) {
            if (a.getByte(_aidx) != b.getByte(_bidx)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Private constructor.
     */
    private Buffers() {
    }
}
