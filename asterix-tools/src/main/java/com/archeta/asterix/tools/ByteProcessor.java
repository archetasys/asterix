/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

/**
 * Provides a mechanism to iterate over a collection of {@code byte}s.
 */
@FunctionalInterface
interface ByteProcessor {
    /**
     * Returns true if the processor wants to continue the loop and handle the next {@code byte}
     * {@code value} in the buffer. Returns false if the processor wants to stop handling
     * {@code byte}s and abort the loop.
     *
     * @param value the {@code byte} value to process
     * @return whether the processor wants to continue to process next {@code byte} {@code value}
     */
    boolean process(byte value);
}
