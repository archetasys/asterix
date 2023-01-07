/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.archeta.asterix.Cat048V1Dot30.*;

// Benchmark                                  Mode  Cnt    Score   Error  Units
// ASTERIXCategory048DecoderBenchmark.decode  avgt   10  160.960 ± 3.305  ns/op
// Benchmark                                  Mode  Cnt    Score   Error  Units
// ASTERIXCategory048DecoderBenchmark.decode  avgt   10  163.585 ± 9.233  ns/op
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 10, time = 5)
@Measurement(iterations = 10, time = 5)
@State(Scope.Benchmark)
public class ASTERIXCategory048ParserBenchmark {
    private DataBlock block;
    private ASTERIXParser parser;
    private Buffer buf;
    private Record record;
    private int offset;
    private int length;
    private int dataFieldOffset;

    @Setup
    public void setUp() {
        block = new DataBlock();
        parser = ASTERIXParser.create(Cat048V1Dot30.CAT048_ID);
        buf = new Buffer(HexDump.decode("300058ffff42321d312af2a06f039e8609d901cde02c09b78a038130e271cf18200510010680fd000010fa8101000000001789c800307c000040ffd77b2020048050bc09a718beb7d360001cda33d75b03ea7c4dc62020fd"));
        offset = 0;
        length = buf.capacity();
        parser.parse(buf, offset, length, block, null, null);
        record = block.getRecord(0).copy();
        dataFieldOffset = parser.findDataItemOffset(I048_010, record);
    }

    @Benchmark
    public int parse() {
        return parser.parse(buf, offset, length, block, null, null);
    }

    @Benchmark
    public byte getSAC() {
        return parser.getByte(I048_010_SAC, dataFieldOffset, record);
    }

    @Benchmark
    public byte getSIC() {
        return parser.getByte(I048_010_SIC, dataFieldOffset, record);
    }
}
