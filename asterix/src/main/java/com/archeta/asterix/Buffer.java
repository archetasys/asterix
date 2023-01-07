/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * Supports big-endian byte access to an underlying buffer. The buffer can be a {@code byte} array,
 * one of the various {@link ByteBuffer} implementations, or an off Java heap memory address.
 * <p>
 * {@link ByteOrder ByteOrder} of a wrapped buffer is not applied to the {@code Buffer}.
 * {@code Buffer}s are effectively stateless and can be used concurrently, except wrapping.
 * <p>
 * <b>Note:</b>
 * <ul>
 *   <li>The wrap methods on this class are not thread safe. Concurrent access should only happen
 *   after a successful wrap.</li>
 * </ul>
 */
public final class Buffer {
    /**
     * Name of the system property that specify if the bounds checks should be disabled.
     * To disable bounds checks set this property to true.
     */
    public static final String DISABLE_BOUNDS_CHECK_PROP_NAME = "com.archeta.asterix.buffer.disable.bounds.check";

    /**
     * System property value whether the bounds check should be enabled. Controlled by the
     * {@link #DISABLE_BOUNDS_CHECK_PROP_NAME} system property. Default is true.
     */
    public static final boolean SHOULD_BOUNDS_CHECK = !Boolean.getBoolean(DISABLE_BOUNDS_CHECK_PROP_NAME);

    /**
     * Whether the native ByteOrder is LITTLE_ENDIAN.
     */
    public static final boolean IS_LITTLE_ENDIAN_NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;

    /**
     * Size of a {@code byte} in bytes.
     */
    public static final int SIZE_OF_BYTE = 1;

    /**
     * Size of a {@code short} in bytes.
     */
    public static final int SIZE_OF_SHORT = 2;

    /**
     * Size of a {@code int} in bytes.
     */
    public static final int SIZE_OF_INT = 4;

    /**
     * Size of a 24-bits unsigned integer in bytes.
     */
    public static final int SIZE_OF_UINT24 = 3;

    /**
     * Size of a 40-bits unsigned integer in bytes.
     */
    public static final int SIZE_OF_UINT40 = 5;

    /**
     * Size of a 48-bits unsigned integer in bytes.
     */
    public static final int SIZE_OF_UINT48 = 6;

    /**
     * Size of a 56-bits unsigned integer in bytes.
     */
    public static final int SIZE_OF_UINT56 = 7;

    /**
     * Size of a {@code long} in bytes.
     */
    public static final int SIZE_OF_LONG = 8;

    /**
     * Reference to the {@link Unsafe} instance.
     */
    private static final Unsafe UNSAFE;

    /**
     * Byte array base offset.
     */
    private static final long ARRAY_BYTE_BASE_OFFSET;

    private static final long BYTE_BUFFER_HB_FIELD_OFFSET;
    private static final long BYTE_BUFFER_OFFSET_FIELD_OFFSET;
    private static final long BYTE_BUFFER_ADDRESS_FIELD_OFFSET;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    static {
        Unsafe unsafe = null;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (final Exception ex) {
            try {
                final Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (final Exception ex2) {
                rethrow(ex);
            }
        }

        UNSAFE = unsafe;
        ARRAY_BYTE_BASE_OFFSET = Unsafe.ARRAY_BYTE_BASE_OFFSET;

        try {
            BYTE_BUFFER_HB_FIELD_OFFSET = UNSAFE.objectFieldOffset(ByteBuffer.class.getDeclaredField("hb"));
            BYTE_BUFFER_OFFSET_FIELD_OFFSET = UNSAFE.objectFieldOffset(ByteBuffer.class.getDeclaredField("offset"));
            BYTE_BUFFER_ADDRESS_FIELD_OFFSET = UNSAFE.objectFieldOffset(java.nio.Buffer.class.getDeclaredField("address"));
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void rethrow(final Throwable t) throws T {
        throw (T) t;
    }

    /**
     * Returns the memory address at which the underlying direct buffer storage begins.
     *
     * @param buffer the direct buffer that wraps the underlying storage
     * @return the memory address at which the buffer storage begins
     * @throws IllegalArgumentException if the {@code buffer} is not a direct {@code ByteBuffer}
     */
    public static long address(final ByteBuffer buffer) {
        if (!buffer.isDirect()) {
            throw new IllegalArgumentException("`buffer` must be a direct ByteBuffer");
        }

        return UNSAFE.getLong(buffer, BYTE_BUFFER_ADDRESS_FIELD_OFFSET);
    }

    /**
     * Returns  the array offset from a read-only {@link ByteBuffer} similar to
     * {@link ByteBuffer#arrayOffset()}.
     *
     * @param buffer the buffer that wraps the underlying array
     * @return the underlying array offset at which this {@code ByteBuffer} starts
     */
    public static int arrayOffset(final ByteBuffer buffer) {
        return UNSAFE.getInt(buffer, BYTE_BUFFER_OFFSET_FIELD_OFFSET);
    }

    /**
     * Returns the {@code byte} array from a read-only {@link ByteBuffer} similar to
     * {@link ByteBuffer#array()}.
     *
     * @param buffer the buffer that wraps the underlying {@code byte} array
     * @return the underlying {@code byte} array
     * @throws IllegalArgumentException if the {@code buffer} is a direct {@code ByteBuffer}
     */
    public static byte[] array(final ByteBuffer buffer) {
        if (buffer.isDirect()) {
            throw new IllegalArgumentException("`buffer` must not a direct ByteBuffer");
        }

        return (byte[]) UNSAFE.getObject(buffer, BYTE_BUFFER_HB_FIELD_OFFSET);
    }

    /**
     * The address offset of either the byte array or the ByteBuffer that backed this buffer.
     */
    private long addressOffset;

    /**
     * The capacity of this buffer.
     */
    private int capacity;

    /**
     * The byte array that backed this buffer, null if this buffer is backed by a direct ByteBuffer.
     */
    private byte[] byteArray;

    /**
     * The ByteBuffer that backed this buffer, null if this buffer is backed by a byte array.
     */
    private ByteBuffer byteBuffer;

    /**
     * The wrap adjustment.
     */
    private int wrapAdjustment;

    /**
     * Empty constructor for a reusable wrapper buffer.
     */
    public Buffer() {
        wrap(EMPTY_BYTE_ARRAY);
    }

    /**
     * Attach a view to a {@code byte} array for providing direct access.
     *
     * @param bytes the {@code byte} array to which the view is attached
     */
    public Buffer(final byte[] bytes) {
        wrap(bytes);
    }

    /**
     * Attach a view to a {@code byte} array for providing direct access.
     *
     * @param bytes  the {@code byte} array to which the view is attached
     * @param offset the offset within the buffer to begin
     * @param length the length of the buffer to be included
     */
    public Buffer(final byte[] bytes, final int offset, final int length) {
        wrap(bytes, offset, length);
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access, the {@link ByteBuffer} can
     * be heap based or direct.
     *
     * @param buffer the buffer to which the view is attached
     */
    public Buffer(final ByteBuffer buffer) {
        wrap(buffer);
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access, the {@link ByteBuffer} can
     * be heap based or direct.
     *
     * @param buffer the buffer to which the view is attached
     * @param offset the offset within the buffer to begin
     * @param length the length of the buffer to be included
     */
    public Buffer(final ByteBuffer buffer, final int offset, final int length) {
        wrap(buffer, offset, length);
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access, the {@link ByteBuffer} can
     * be heap based or direct.
     *
     * @param buffer the buffer to which the view is attached
     */
    public Buffer(final Buffer buffer) {
        wrap(buffer);
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access, the {@link ByteBuffer} can
     * be heap based or direct.
     *
     * @param buffer the buffer to which the view is attached
     * @param offset the offset within the buffer to begin
     * @param length the length of the buffer to be included
     */
    public Buffer(final Buffer buffer, final int offset, final int length) {
        wrap(buffer, offset, length);
    }

    /**
     * Attach a view to an off-heap memory region by address. This is useful for interacting with native libraries.
     *
     * @param address the address where the memory begins off-heap
     * @param length  the length of the buffer from the given address
     */
    public Buffer(final long address, final int length) {
        wrap(address, length);
    }

    /**
     * Attach a view to a {@code byte} array for providing direct access.
     *
     * @param byteArray the {@code byte} array to which the view is attached
     */
    public void wrap(final byte[] byteArray) {
        capacity = byteArray.length;
        addressOffset = ARRAY_BYTE_BASE_OFFSET;
        byteBuffer = null;
        wrapAdjustment = 0;
        if (byteArray != this.byteArray) {
            this.byteArray = byteArray;
        }
    }

    /**
     * Attach a view to a {@code byte} array for providing direct access.
     *
     * @param byteArray the {@code byte} array to which the view is attached
     * @param offset    the offset within the array at which the view begins
     * @param length    the length of the array to be included
     */
    public void wrap(final byte[] byteArray, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckWrap(offset, length, byteArray.length);
        }

        capacity = length;
        addressOffset = ARRAY_BYTE_BASE_OFFSET + offset;
        byteBuffer = null;
        wrapAdjustment = offset;
        if (byteArray != this.byteArray) {
            this.byteArray = byteArray;
        }
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access, the {@link ByteBuffer} can
     * be heap based or direct. The {@link ByteBuffer#order()} is not relevant for accessing the
     * wrapped buffer.
     * <p>
     * When using this method to wrap the view of the {@code ByteBuffer} the entire {@code ByteBuffer}
     * gets wrapped between offset 0 and capacity. If you want to just wrap the {@code ByteBuffer}
     * between the position and the limit then you should use the {@link #wrap(ByteBuffer, int, int)}
     * method, eg:
     * <pre>
     * {@code
     * unsafeBuffer.wrap(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
     * }</pre>
     *
     * @param byteBuffer the buffer to which the view is attached
     */
    public void wrap(final ByteBuffer byteBuffer) {
        capacity = byteBuffer.capacity();
        if (byteBuffer != this.byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        if (byteBuffer.isDirect()) {
            byteArray = null;
            addressOffset = address(byteBuffer);
            wrapAdjustment = 0;
        } else {
            byteArray = array(byteBuffer);
            final int arrayOffset = arrayOffset(byteBuffer);
            addressOffset = ARRAY_BYTE_BASE_OFFSET + arrayOffset;
            wrapAdjustment = arrayOffset;
        }
    }

    /**
     * Attach a view to a {@link ByteBuffer} for providing direct access.
     * <p>
     * The {@link ByteBuffer#order()} is not relevant for accessing the wrapped buffer.
     *
     * @param buffer the buffer to which the view is attached
     * @param offset the offset at which the view begins
     * @param length the length of the buffer included in the view
     */
    public void wrap(final ByteBuffer buffer, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckWrap(offset, length, buffer.capacity());
        }

        capacity = length;

        if (buffer != byteBuffer) {
            byteBuffer = buffer;
        }

        if (buffer.isDirect()) {
            byteArray = null;
            addressOffset = address(buffer) + offset;
            wrapAdjustment = offset;
        } else {
            byteArray = array(buffer);
            final int totalOffset = arrayOffset(buffer) + offset;
            addressOffset = ARRAY_BYTE_BASE_OFFSET + totalOffset;
            wrapAdjustment = totalOffset;
        }
    }

    /**
     * Attach a view to the given {@code buffer} for providing direct access.
     *
     * @param buffer the buffer to which the view is attached
     */
    public void wrap(final Buffer buffer) {
        capacity = buffer.capacity();
        addressOffset = buffer.addressOffset;
        wrapAdjustment = buffer.wrapAdjustment;

        final byte[] byteArray = buffer.byteArray;
        if (byteArray != this.byteArray) {
            this.byteArray = byteArray;
        }

        final ByteBuffer byteBuffer = buffer.byteBuffer;
        if (byteBuffer != this.byteBuffer) {
            this.byteBuffer = byteBuffer;
        }
    }

    /**
     * Attach a view to the given {@code buffer} for providing direct access.
     *
     * @param buffer the buffer to which the view is attached
     * @param offset the offset at which the view begins
     * @param length the length of the buffer included in the view
     */
    public void wrap(final Buffer buffer, final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckWrap(offset, length, buffer.capacity());
        }

        capacity = length;
        addressOffset = buffer.addressOffset + offset;
        wrapAdjustment = buffer.wrapAdjustment + offset;

        final byte[] byteArray = buffer.byteArray;
        if (byteArray != this.byteArray) {
            this.byteArray = byteArray;
        }

        final ByteBuffer byteBuffer = buffer.byteBuffer;
        if (byteBuffer != this.byteBuffer) {
            this.byteBuffer = byteBuffer;
        }
    }

    /**
     * Attach a view to an off-heap memory region by {@code address}.
     *
     * @param address the address where the memory begins off-heap
     * @param length  the length of the buffer from the given {@code address}
     */
    public void wrap(final long address, final int length) {
        capacity = length;
        addressOffset = address;
        byteArray = null;
        byteBuffer = null;
    }

    /**
     * Returns the capacity of the underlying buffer.
     *
     * @return the capacity of the underlying buffer in bytes
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the underlying {@code byte} array if one exists.
     * <p>
     * NB: there may not be a one-to-one mapping between indices on this buffer
     * and the underlying {@code byte[]}, see {@link #wrapAdjustment()}.
     *
     * @return the underlying {@code byte} array if one exists, may be {@code null}
     * @see #wrap(byte[])
     * @see #wrap(byte[], int, int)
     * @see #wrap(ByteBuffer)
     * @see #wrap(ByteBuffer, int, int)
     */
    public byte[] byteArray() {
        return byteArray;
    }

    /**
     * Returns the underlying {@link ByteBuffer} if one exists.
     * <p>
     * NB: there may not be a one-to-one mapping between indices on this buffer
     * and the underlying byte[], see {@link #wrapAdjustment()}.
     *
     * @return the underlying {@code ByteBuffer} if one exists, may be {@code null}
     */
    public ByteBuffer byteBuffer() {
        return byteBuffer;
    }

    /**
     * Returns the underlying offset to the memory address.
     *
     * @return the underlying offset to the memory address
     */
    public long addressOffset() {
        return addressOffset;
    }

    /**
     * Returns the adjustment in indices between an index in this buffer and the wrapped object.
     * The wrapped object might be a {@link ByteBuffer} or a {@code byte[]}.
     * <p>
     * You only need to use this adjustment if you plan to perform operations on the underlying
     * byte array or byte buffer that rely on their indices.
     *
     * @return the adjustment in indices between an index in this buffer and the wrapped object
     * @see #byteArray()
     * @see #byteBuffer()
     */
    public int wrapAdjustment() {
        return wrapAdjustment;
    }

    /**
     * Returns the {@code byte} value read at the given {@code offset}.
     *
     * @param offset the offset to read the {@code byte} value
     * @return the {@code byte} value at the given {@code offset}
     */
    public byte getByte(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_BYTE);
        }

        return UNSAFE.getByte(byteArray, addressOffset + offset);
    }

    /**
     * Returns the {@code short} value read at the given {@code offset} in big endian byte order.
     *
     * @param offset the start offset to read the {@code short} value
     * @return the {@code short} value at the given {@code offset}
     */
    public short getShort(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_SHORT);
        }

        final short s = UNSAFE.getShort(byteArray, addressOffset + offset);
        return IS_LITTLE_ENDIAN_NATIVE_ORDER ? Short.reverseBytes(s) : s;
    }

    /**
     * Returns the {@code int} value read at the given {@code offset} in big endian byte order.
     *
     * @param offset the start offset to read the {@code int} value
     * @return the {@code int} value at the given {@code offset}
     */
    public int getInt(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_INT);
        }

        final int i = UNSAFE.getInt(byteArray, addressOffset + offset);
        return IS_LITTLE_ENDIAN_NATIVE_ORDER ? Integer.reverseBytes(i) : i;
    }

    /**
     * Returns the 24-bit unsigned integer value at a given {@code offset} in big endian byte order.
     *
     * @param offset the offset in bytes from which to get
     * @return the 24-bit unsigned integer value at a given {@code offset}
     */
    public int getUInt24(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_UINT24);
        }

        final byte ub = UNSAFE.getByte(byteArray, addressOffset + offset);
        final short s = UNSAFE.getShort(byteArray, addressOffset + offset + 1);
        final short ls = IS_LITTLE_ENDIAN_NATIVE_ORDER ? Short.reverseBytes(s) : s;
        return ((ub & 0xff) << 16) | (ls & 0xffff);
    }

    /**
     * Returns the 40-bit unsigned integer value at a given {@code offset} in little endian byte order.
     *
     * @param offset the offset in bytes from which to get
     * @return the 40-bit unsigned integer value at a given {@code offset}
     */
    public long getUInt40(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_UINT40);
        }

        final byte ub = UNSAFE.getByte(byteArray, addressOffset + offset);
        final int i = UNSAFE.getInt(byteArray, addressOffset + offset + 1);
        final int li = IS_LITTLE_ENDIAN_NATIVE_ORDER ? Integer.reverseBytes(i) : i;
        return ((ub & 0xffL) << 32) | (li & 0xffffffffL);
    }

    /**
     * Returns the 48-bit unsigned integer value at a given {@code offset} in big endian byte order.
     *
     * @param offset the offset in bytes from which to get
     * @return the 48-bit unsigned integer value at a given {@code offset}
     */
    public long getUInt48(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_UINT48);
        }

        final short s = UNSAFE.getShort(byteArray, addressOffset + offset);
        final int i = UNSAFE.getInt(byteArray, addressOffset + offset + 2);
        final short us;
        final int li;
        if (IS_LITTLE_ENDIAN_NATIVE_ORDER) {
            us = Short.reverseBytes(s);
            li = Integer.reverseBytes(i);
        } else {
            us = s;
            li = i;
        }

        return ((us & 0xffffL) << 32) | (li & 0xffffffffL);
    }

    /**
     * Returns the 56-bit unsigned integer value at a given {@code offset} in big endian byte order.
     *
     * @param offset the offset in bytes from which to get
     * @return the 56-bit unsigned integer value at a given {@code offset}
     */
    public long getUInt56(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_UINT56);
        }

        final byte ub = UNSAFE.getByte(byteArray, addressOffset + offset);
        final short s = UNSAFE.getShort(byteArray, addressOffset + offset + 1);
        final int i = UNSAFE.getInt(byteArray, addressOffset + offset + 3);
        final short ms;
        final int li;
        if (IS_LITTLE_ENDIAN_NATIVE_ORDER) {
            ms = Short.reverseBytes(s);
            li = Integer.reverseBytes(i);
        } else {
            ms = s;
            li = i;
        }

        return ((ub & 0xffL) << 48) | ((ms & 0xffffL) << 32) | (li & 0xffffffffL);
    }

    /**
     * Returns the {@code long} value read at the given {@code offset} in big endian byte order.
     *
     * @param offset the start offset to read the {@code long} value
     * @return the {@code long} value at the given {@code offset}
     */
    public long getLong(final int offset) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, SIZE_OF_LONG);
        }

        final long l = UNSAFE.getLong(byteArray, addressOffset + offset);
        return IS_LITTLE_ENDIAN_NATIVE_ORDER ? Long.reverseBytes(l) : l;
    }

    /**
     * Reads the underlying buffer into the given {@code dstByteArray}.
     *
     * @param offset       the offset in the underlying buffer to start read from
     * @param dstByteArray the destination byte array into which the bytes will be copied
     * @param dstOffset    the offset in the destination byte array to start the copy
     * @param length       the length of the bytes to be written
     */
    public void getBytes(final int offset, final byte[] dstByteArray, final int dstOffset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckDst(offset, length, dstOffset, dstByteArray.length);
        }

        UNSAFE.copyMemory(byteArray, addressOffset + offset, dstByteArray, ARRAY_BYTE_BASE_OFFSET + dstOffset, length);
    }

    /**
     * Reads from the underlying buffer into a supplied {@link ByteBuffer} at an offset.
     * <p>
     * The destination buffer will not have its {@link ByteBuffer#position()} advanced as a result.
     *
     * @param offset    the offset in the underlying buffer to start read from
     * @param dstBuffer the destination buffer into which the bytes will be copied
     * @param dstOffset the offset in the destination buffer to start write bytes
     * @param length    the length of the supplied destination buffer to use
     */
    public void getBytes(final int offset, final ByteBuffer dstBuffer, final int dstOffset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckDst(offset, length, dstOffset, dstBuffer.capacity());
        }

        final byte[] dstByteArray;
        final long dstBaseOffset;
        if (dstBuffer.isDirect()) {
            dstByteArray = null;
            dstBaseOffset = address(dstBuffer);
        } else {
            dstByteArray = array(dstBuffer);
            dstBaseOffset = ARRAY_BYTE_BASE_OFFSET + arrayOffset(dstBuffer);
        }

        UNSAFE.copyMemory(byteArray, addressOffset + offset, dstByteArray, dstBaseOffset + dstOffset, length);
    }

    /**
     * Reads from the underlying buffer into a supplied {@link ByteBuffer} current {@link ByteBuffer#position()}.
     * <p>
     * The destination buffer will have its {@link ByteBuffer#position()} advanced as a result.
     *
     * @param offset    the offset in the underlying buffer to start read from
     * @param dstBuffer the destination buffer into which the bytes will be copied
     * @param length    the length of the bytes to be copied
     */
    public void getBytes(int offset, ByteBuffer dstBuffer, int length) {
        final int dstOffset = dstBuffer.position();
        getBytes(offset, dstBuffer, dstOffset, length);
        dstBuffer.position(dstOffset + length);
    }

    /**
     * Reads the underlying buffer into the given {@code dstBuffer}.
     *
     * @param offset    the offset in the underlying buffer to start read from
     * @param dstBuffer the destination buffer into which the bytes will be copied
     * @param dstOffset the offset in the destination buffer to start write bytes
     * @param length    the length of the bytes to be copied
     */
    public void getBytes(final int offset, final Buffer dstBuffer, final int dstOffset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheckDst(offset, length, dstOffset, dstBuffer.capacity());
        }

        UNSAFE.copyMemory(byteArray, addressOffset + offset, dstBuffer.byteArray, dstBuffer.addressOffset + dstOffset, length);
    }

    /**
     * Returns an encoded ASCII {@code String} from the buffer that does not have a length prefix.
     *
     * @param offset the offset at which the {@code String} begins
     * @param length the length of the {@code String} in bytes to decode
     * @return the {@code String} as represented by the Ascii encoded bytes
     */
    public String getStringAsciiNoLength(final int offset, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length);
        }

        final byte[] bytes = new byte[length];
        UNSAFE.copyMemory(byteArray, addressOffset + offset, bytes, ARRAY_BYTE_BASE_OFFSET, length);
        return new String(bytes, StandardCharsets.US_ASCII);
    }

    /**
     * Reads an encoded ASCII String from the buffer that does not have a length prefix and append
     * to an {@link Appendable} {@code output}.
     *
     * @param offset the offset at which the {@code String} begins
     * @param length the length of the {@code String} in bytes to decode
     * @param output the  {@link Appendable} to append the chars to
     * @return the given {@code output}
     * @throws IOException on I/O error
     */
    public Appendable getStringAsciiNoLength(final int offset, final int length, final Appendable output) throws IOException {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length);
        }

        final byte[] array = byteArray;
        final long off = addressOffset;
        for (int i = offset, limit = offset + length; i < limit; i++) {
            final char c = (char) UNSAFE.getByte(array, off + i);
            output.append(c > 127 ? '?' : c);
        }

        return output;
    }

    /**
     * Returns the unsigned value read from the underlying bytes store start from the specified
     * {@code offset}. The bits value is read with Bit Endian byte order.
     * <p>
     * Note that if {@code numBits} is either 8, 16, 24, 32, 40, 48, or 56, then the returned value
     * is guaranteed to be unsigned (positive {@code long} value). As an exception, if {@code numBits}
     * is 64 then the returned value might be negative because no such unsigned {@code long} value.
     *
     * @param offset the offset to start read the buf from
     * @param numBits  the number of bits, must be 8, 16, 24, 32, 40, 48, 56, or 64
     * @return the unsigned value read from the underlying bytes store start from the specified {@code offset}
     */
    public long getBits(final int offset, final int numBits) {
        switch (numBits) {
            case 8:
                return getByte(offset) & 0xffL;
            case 16:
                return getShort(offset) & 0xffffL;
            case 24:
                return getUInt24(offset);
            case 32:
                return getInt(offset) & 0xffffffffL;
            case 40:
                return getUInt40(offset);
            case 48:
                return getUInt48(offset);
            case 56:
                return getUInt56(offset);
            case 64:
                return getLong(offset);
            default:
                throw new IllegalArgumentException("illegal number of bits: " + numBits);
        }
    }

    /**
     * Returns the bits value from the given {@code byte} starting {@code from} up
     * {@code to}.
     * <p>
     * Bits are numbered from right to left from 1 to 8. Below is an example of a bits with from
     * bit position 7 and to bit position 3.
     * <pre>
     *   +-+-+-+-+-+-+-+-+
     *   |8|7|6|5|4|3|2|1| <-- Bit Position (right to left)
     *   +-+-+-+-+-+-+-+-+
     *   |0|1|0|1|1|0|1|0|
     *   +-+-+-+-+-+-+-+-+
     *      |       |
     *    from=7  to=3
     * </pre>
     * The returned value is {@code 0x58} ({@code 01011000}). Note that the returned value is not
     * shifted to the right and return  {@code ({@code 010110}.
     * <p>
     * <b>Note:</b> the {@code from} and {@code to} are not checked.
     * </p>
     *
     * @param byte the {@code byte}
     * @param from the from bit position, must be in {@code [1, 8]}
     * @param to   the to bit position, must be in {@code [1, from]}
     * @return the bits value, always positive
     */
    public int getBits(final int offset, final int from, final int to) {
        final byte b = getByte(offset);

        // Single bit:
        if (from == to) {
            return b & (1 << (from - 1));
        }

        // Whole byte:
        if (from == 8 && to == 1) {
            return b & 0xff;
        }

        final int mask = ~(~0 << (from - to + 1));
        return b & (mask << (to - 1));
    }

    void copyRecordData(final int catno, final int offset, final Buffer buffer, final int length) {
        if (SHOULD_BOUNDS_CHECK) {
            boundsCheck(offset, length);
        }

        if (buffer.capacity() < length + 3) {
            throw new IllegalArgumentException("`buffer` has insufficient capacity");
        }

        final byte[] byteArray = buffer.byteArray;
        final long addressOffset = buffer.addressOffset;
        final int len = length + 3;
        final short lenBits = IS_LITTLE_ENDIAN_NATIVE_ORDER ? Short.reverseBytes((short) len) : (short) len;
        UNSAFE.putByte(byteArray, addressOffset, (byte) catno);
        UNSAFE.putShort(byteArray, addressOffset + 1, lenBits);
        UNSAFE.copyMemory(this.byteArray, this.addressOffset + offset, byteArray, addressOffset + 3, length);
    }

    private void boundsCheck(final int offset, final int length) {
        final long resultingPosition = offset + (long) length;
        if (offset < 0 || length < 0 || resultingPosition > capacity) {
            throw new IndexOutOfBoundsException("offset=" + offset + " length=" + length + " capacity=" + capacity);
        }
    }

    private void boundsCheckDst(final int offset, final int length, final int dstOffset, final int dstCapacity) {
        if ((offset | length | dstOffset | (offset + length) | (capacity - (offset + length))
                | (dstOffset + length) | (dstCapacity - (dstOffset + length))) < 0) {
            throw new IndexOutOfBoundsException("offset=" + offset + " length=" + length + " capacity="
                    + capacity + " dstOffset=" + dstOffset + " dstCapacity=" + dstCapacity);
        }
    }

    private static void boundsCheckWrap(final int offset, final int length, final int capacity) {
        if (offset < 0) {
            throw new IllegalArgumentException("`offset` must be nonnegative, found: " + offset);
        }

        if (length < 0) {
            throw new IllegalArgumentException("`length` must be nonnegative, found: " + length);
        }

        if ((offset > capacity - length) || (length > capacity - offset)) {
            throw new IllegalArgumentException("offset=" + offset + " length=" + length
                    + " not valid for capacity=" + capacity);
        }
    }
}
