/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * Callback interface for handling a decoded {@link DataBlock} when decoding from a file.
 */
@FunctionalInterface
public interface DataBlockConsumer {
    /**
     * Callback to notify of a decoded {@link DataBlock} when decoding from a file
     *
     * @param block        the decoded {@link DataBlock}
     * @param blockNumber  the data block number
     * @param packetNumber the packet number
     * @param timestamp    the timestamp when the packet was captured in microseconds since
     *                     January 1, 1970 00:00:00 GMT
     */
    void accept(DataBlock block, int blockNumber, int packetNumber, long timestamp);
}
