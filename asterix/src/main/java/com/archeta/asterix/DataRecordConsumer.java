/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * Callback interface for handling a decoded {@link DataRecord} when decoding from a file.
 */
@FunctionalInterface
public interface DataRecordConsumer {
    /**
     * Callback to notify of a decoded {@link DataRecord} when decoding from a file
     *
     * @param record       the decoded {@link DataRecord}
     * @param recordNumber the record number
     * @param blockNumber  the data block number
     * @param packetNumber the packet number
     * @param timestamp    the timestamp when the packet was captured in microseconds since
     *                     January 1, 1970 00:00:00 GMT
     */
    void accept(DataRecord record, int recordNumber, int blockNumber, int packetNumber, long timestamp);
}
