/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class PcapParserTest {
    @Test
    void shouldParse() throws Exception {
        final File pcapFile = new File(Resources.get("sample.pcap").toURI());
        final AtomicInteger pktNumber = new AtomicInteger();
        final AtomicLong firstTimestamp = new AtomicLong();
        final AtomicLong lastTimestamp = new AtomicLong();
        final AtomicInteger firstLength = new AtomicInteger();
        final AtomicInteger lastLength = new AtomicInteger();
        final PcapParser.PacketHandler packetHandler = (buffer, offset, length, packetNumber, timestamp) -> {
            pktNumber.set(packetNumber);
            if (packetNumber == 1) {
                firstLength.set(length);
                firstTimestamp.set(timestamp);
            }

            lastLength.set(length);
            lastTimestamp.set(timestamp);
            return 0;
        };

        final int res = PcapParser.parse(pcapFile, packetHandler);

        assertEquals(0, res);
        assertEquals(32343, pktNumber.get());
        assertEquals(141, firstLength.get());
        assertEquals(1656127388L * 1_000_000 + 900306, firstTimestamp.get());
        assertEquals(14, lastLength.get());
        assertEquals(1656129188L * 1_000_000 + 762572, lastTimestamp.get());
    }
}
