/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.archeta.asterix.Buffer.*;
import static org.junit.jupiter.api.Assertions.*;

final class BufferTest {
    private static final int BUFFER_CAPACITY = 256;
    private static final int OFFSET = 1;
    private static final int BUFFER_WITH_OFFSET_CAPACITY = BUFFER_CAPACITY - OFFSET - 1;

    private static final byte BYTE_VALUE = -125;
    private static final short SHORT_VALUE = (short) 0xf123;
    private static final int INT_VALUE = 0xf1234567;
    private static final long LONG_VALUE = 0xf123456789012345L;
    private static final int UINT24_VALUE = 0xf12345;
    private static final long UINT40_VALUE = 0xf123456789L;
    private static final long UINT48_VALUE = 0xf12345678901L;
    private static final long UINT56_VALUE = 0xf1234567890123L;

    private static final int BYTE_OFFSET = 3;
    private static final int SHORT_OFFSET = BYTE_OFFSET + SIZE_OF_BYTE;
    private static final int INT_OFFSET = SHORT_OFFSET + SIZE_OF_SHORT;
    private static final int LONG_OFFSET = INT_OFFSET + SIZE_OF_INT;
    private static final int UINT24_OFFSET = LONG_OFFSET + SIZE_OF_LONG;
    private static final int UINT40_OFFSET = UINT24_OFFSET + SIZE_OF_UINT24;
    private static final int UINT48_OFFSET = UINT40_OFFSET + SIZE_OF_UINT40;
    private static final int UINT56_OFFSET = UINT48_OFFSET + SIZE_OF_UINT48;
    private static final int SIZE = UINT56_OFFSET + SIZE_OF_UINT56;

    private static final int ADJUSTMENT_OFFSET = 3;

    private final byte[] wibbleBytes = "Wibble".getBytes(StandardCharsets.US_ASCII);


    @ParameterizedTest
    @MethodSource("createBuffersWithoutOffset")
    void shouldGetCapacityFromNonOffsetBuffers(final Buffer buf) {
        assertEquals(BUFFER_CAPACITY, buf.capacity());
    }

    @ParameterizedTest
    @MethodSource("createBuffersWithOffset")
    void shouldGetCapacityFromOffsetBuffers(final Buffer buf) {
        assertEquals(BUFFER_WITH_OFFSET_CAPACITY, buf.capacity());
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetByte(final Buffer buf) {
        assertEquals(BYTE_VALUE, buf.getByte(BYTE_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetShort(final Buffer buf) {
        assertEquals(SHORT_VALUE, buf.getShort(SHORT_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetInt(final Buffer buf) {
        assertEquals(INT_VALUE, buf.getInt(INT_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetLong(final Buffer buf) {
        assertEquals(LONG_VALUE, buf.getLong(LONG_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetUInt24(final Buffer buf) {
        assertEquals(UINT24_VALUE, buf.getUInt24(UINT24_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetUInt40(final Buffer buf) {
        assertEquals(UINT40_VALUE, buf.getUInt40(UINT40_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetUInt48(final Buffer buf) {
        assertEquals(UINT48_VALUE, buf.getUInt48(UINT48_OFFSET));
    }

    @ParameterizedTest
    @MethodSource("createBuffers")
    void shouldGetUInt56(final Buffer buf) {
        assertEquals(UINT56_VALUE, buf.getUInt56(UINT56_OFFSET));
    }

    @Test
    void shouldExposePositionAtWhichByteArrayGetsWrapped() {
        wibbleBytes[ADJUSTMENT_OFFSET] = 45;
        final Buffer wibbleBuffer = new Buffer(wibbleBytes, ADJUSTMENT_OFFSET, wibbleBytes.length - ADJUSTMENT_OFFSET);

        assertEquals(45, wibbleBytes[wibbleBuffer.wrapAdjustment()]);
    }

    @Test
    void shouldExposePositionAtWhichHeapByteBufferGetsWrapped() {
        final ByteBuffer wibbleByteBuffer = ByteBuffer.wrap(wibbleBytes);
        shouldExposePositionAtWhichByteBufferGetsWrapped(wibbleByteBuffer);
    }

    @Test
    void shouldExposePositionAtWhichDirectByteBufferGetsWrapped() {
        final ByteBuffer wibbleByteBuffer = ByteBuffer.allocateDirect(wibbleBytes.length);
        shouldExposePositionAtWhichByteBufferGetsWrapped(wibbleByteBuffer);
    }

    @Test
    void shouldWrapValidRange() {
        final Buffer buffer = new Buffer(new byte[8]);
        final Buffer slice = new Buffer();

        slice.wrap(buffer);
        slice.wrap(buffer, 0, 8);
        slice.wrap(buffer, 1, 7);
        slice.wrap(buffer, 2, 6);
        slice.wrap(buffer, 3, 5);
        slice.wrap(buffer, 4, 4);
        slice.wrap(buffer, 5, 3);
        slice.wrap(buffer, 6, 2);
        slice.wrap(buffer, 7, 1);
        slice.wrap(buffer, 8, 0);
    }

    @Test
    void shouldNotWrapInValidRange() {
        final Buffer buffer = new Buffer(new byte[8]);
        final Buffer slice = new Buffer();

        assertThrows(IllegalArgumentException.class, () -> slice.wrap(buffer, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> slice.wrap(buffer, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> slice.wrap(buffer, 8, 1));
        assertThrows(IllegalArgumentException.class, () -> slice.wrap(buffer, 7, 3));
    }

    @Test
    void wrapBuffer() {
        final Buffer src = new Buffer(ByteBuffer.allocate(64));
        final Buffer buffer = new Buffer(src);

        assertSame(src.byteArray(), buffer.byteArray());
        assertSame(src.byteBuffer(), buffer.byteBuffer());
        assertEquals(src.capacity(), buffer.capacity());
        assertEquals(src.addressOffset(), buffer.addressOffset());
        assertEquals(src.wrapAdjustment(), buffer.wrapAdjustment());
    }

    @Test
    void wrapBufferWithOffsetAndLength() {
        final Buffer src = new Buffer(ByteBuffer.allocate(64));
        final int offset = 3;
        final int length = 17;
        final Buffer buffer = new Buffer(src, offset, length);

        assertSame(src.byteArray(), buffer.byteArray());
        assertSame(src.byteBuffer(), buffer.byteBuffer());
        assertEquals(length, buffer.capacity());
        assertEquals(src.addressOffset() + offset, buffer.addressOffset());
        assertEquals(src.wrapAdjustment() + offset, buffer.wrapAdjustment());
    }

    @ParameterizedTest
    @MethodSource("nativeBuffers")
    void shouldGetBytesFromBuffer(final ByteBuffer bb) {
        final byte[] bytes = "Hello World".getBytes();

        final Buffer buffer = new Buffer(bb);
        bb.position(8);
        bb.put(bytes);

        final int len = bytes.length;
        final byte[] dst = new byte[len];
        buffer.getBytes(8, dst, 0, len);

        assertArrayEquals(bytes, dst);
    }

    @ParameterizedTest
    @MethodSource("nativeBuffers")
    void shouldGetBytesFromBufferToByteBuffer(final ByteBuffer bb) {
        final byte[] bytes = "Hello World".getBytes();

        final Buffer buffer = new Buffer(bb);
        bb.position(8);
        bb.put(bytes);

        final ByteBuffer dstBuffer = ByteBuffer.allocate(bytes.length);
        buffer.getBytes(8, dstBuffer, 0, bytes.length);

        assertArrayEquals(bytes, dstBuffer.array());
    }

    @ParameterizedTest
    @MethodSource("nativeBuffers")
    void shouldGetBytesFromBufferToBuffer(final ByteBuffer bb) {
        final byte[] bytes = "Hello World".getBytes();

        final Buffer buffer = new Buffer(bb);
        bb.position(8);
        bb.put(bytes);

        final Buffer dstBuffer = new Buffer(ByteBuffer.allocate(bytes.length));
        buffer.getBytes(8, dstBuffer, 0, bytes.length);

        assertArrayEquals(bytes, dstBuffer.byteArray());
    }

    @ParameterizedTest
    @MethodSource("nativeBuffers")
    void shouldGetBytesFromBufferToSlice(final ByteBuffer bb) {
        final byte[] bytes = "shouldGetBytesFromBufferToSlice".getBytes(StandardCharsets.US_ASCII);

        final Buffer buffer = new Buffer(bb);
        bb.position(8);
        bb.put(bytes);

        final ByteBuffer dstBuffer = sliceBuffer(ByteBuffer.allocate(bytes.length * 2).position(bytes.length));

        buffer.getBytes(8, dstBuffer, bytes.length);

        dstBuffer.flip();
        final byte[] result = new byte[bytes.length];
        dstBuffer.get(result);

        assertArrayEquals(result, bytes);
    }

    private static Stream<Buffer> createBuffersWithoutOffset() {
        return Stream.of(createBuffersWithOffset0(false));
    }

    private static Stream<Buffer> createBuffersWithOffset() {
        return Stream.of(createBuffersWithOffset0(true));
    }

    private static Stream<Buffer> createBuffers() {
        final Buffer[] b1 = createBuffersWithOffset0(false);
        final Buffer[] b2 = createBuffersWithOffset0(true);
        final Buffer[] buffs = new Buffer[b1.length + b2.length];
        System.arraycopy(b1, 0, buffs, 0, b1.length);
        System.arraycopy(b2, 0, buffs, b1.length, b2.length);
        return Stream.of(buffs);
    }

    private static Buffer[] createBuffersWithOffset0(final boolean withOffset) {
        final Buffer[] buffers;
        if (withOffset) {
            buffers = new Buffer[]{
                    new Buffer(new byte[BUFFER_CAPACITY], OFFSET, BUFFER_WITH_OFFSET_CAPACITY),
                    new Buffer(ByteBuffer.allocate(BUFFER_CAPACITY), OFFSET, BUFFER_WITH_OFFSET_CAPACITY),
                    new Buffer(ByteBuffer.allocateDirect(BUFFER_CAPACITY), OFFSET, BUFFER_WITH_OFFSET_CAPACITY),
                    new Buffer(ByteBuffer.allocate(BUFFER_CAPACITY).asReadOnlyBuffer(), OFFSET, BUFFER_WITH_OFFSET_CAPACITY),
                    new Buffer(ByteBuffer.allocateDirect(BUFFER_CAPACITY).asReadOnlyBuffer(), OFFSET, BUFFER_WITH_OFFSET_CAPACITY),
            };
        } else {
            buffers = new Buffer[]{
                    new Buffer(new byte[BUFFER_CAPACITY]),
                    new Buffer(ByteBuffer.allocate(BUFFER_CAPACITY)),
                    new Buffer(Buffer.address(ByteBuffer.allocateDirect(BUFFER_CAPACITY)), BUFFER_CAPACITY),
                    new Buffer(ByteBuffer.allocateDirect(BUFFER_CAPACITY)),
                    new Buffer(ByteBuffer.allocate(BUFFER_CAPACITY).asReadOnlyBuffer()),
                    new Buffer(ByteBuffer.allocateDirect(BUFFER_CAPACITY).asReadOnlyBuffer()),
            };
        }

        final ByteBuffer bb = fill(ByteBuffer.allocate(BUFFER_CAPACITY));
        final Buffer buf = new Buffer(bb);
        for (final Buffer buffer : buffers) {
            buf.getBytes(0, buffer, 0, SIZE);
        }

        return buffers;
    }

    private static ByteBuffer fill(final ByteBuffer bb) {
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(BYTE_OFFSET, BYTE_VALUE);
        bb.putShort(SHORT_OFFSET, SHORT_VALUE);
        bb.putInt(INT_OFFSET, INT_VALUE);
        bb.putLong(LONG_OFFSET, LONG_VALUE);
        bb.put(UINT24_OFFSET, (byte) ((UINT24_VALUE >> 16) & 0xff));
        bb.putShort(UINT24_OFFSET + 1, (short) (UINT24_VALUE & 0xffff));
        bb.put(UINT40_OFFSET, (byte) ((UINT40_VALUE >> 32) & 0xff));
        bb.putInt(UINT40_OFFSET + 1, (int) (UINT40_VALUE & 0xffffffffL));
        bb.putShort(UINT48_OFFSET, (short) ((UINT48_VALUE >> 32) & 0xffff));
        bb.putInt(UINT48_OFFSET + 2, (int) (UINT48_VALUE & 0xffffffffL));
        bb.put(UINT56_OFFSET, (byte) ((UINT56_VALUE >> 48) & 0xff));
        bb.putShort(UINT56_OFFSET + 1, (short) ((UINT56_VALUE >> 32) & 0xffff));
        bb.putInt(UINT56_OFFSET + 3, (int) (UINT56_VALUE & 0xffffffffL));
        return bb;
    }

    private static void shouldExposePositionAtWhichByteBufferGetsWrapped(final ByteBuffer bb) {
        bb.put(ADJUSTMENT_OFFSET, (byte) 43);
        final Buffer wibbleBuffer = new Buffer(bb, ADJUSTMENT_OFFSET, bb.capacity() - ADJUSTMENT_OFFSET);
        assertEquals(43, bb.get(wibbleBuffer.wrapAdjustment()));
    }

    private static List<ByteBuffer> nativeBuffers() {
        return Arrays.asList(
                ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN),
                ByteBuffer.allocateDirect(64).order(ByteOrder.BIG_ENDIAN),
                sliceBuffer(ByteBuffer.allocate(128).position(64)));
    }

    private static ByteBuffer sliceBuffer(final java.nio.Buffer buffer) {
        return ((ByteBuffer) buffer).slice();
    }
}
