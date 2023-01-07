/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.archeta.asterix.ASTERIXParser.*;

/**
 * Parses PCAP file.
 * <p>
 * PCAP Global Header that wraps the specified {@code buffer} with content:
 * <pre>
 *                        1                   2
 *    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   | MAGIC |MJV|MNV|GMT2LCL| ACCRCY|SNAPLEN|LINKTYP|
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *
 *   typedef struct pcap_hdr_s {
 *           ggetUInt32 magic_number;  // magic number
 *           ggetUInt16 version_major; // major version number
 *           ggetUInt16 version_minor; // minor version number
 *           gint32  thiszone;      // GMT to local correction
 *           ggetUInt32 sigfigs;       // accuracy of timestamps
 *           ggetUInt32 snaplen;       // max length of captured packets, in octets
 *           ggetUInt32 network;       // data link type
 *   } pcap_hdr_t;
 * </pre>, where
 * <ul>
 *   <li>MAGIC   (4 bytes) = Magic number, unsigned</li>
 *   <li>MJV     (2 bytes) = Major version number, unsigned</li>
 *   <li>MNV     (2 bytes) = Minor version number, unsigned</li>
 *   <li>GMT2LCL (4 bytes) = GMT to local correction, signed</li>
 *   <li>ACCRCY  (4 bytes) = Accuracy of timestamps unsigned</li>
 *   <li>SNAPLEN (4 bytes) = Max length of captured packets, in octets, unsigned</li>
 *   <li>LINKTYP (4 bytes) = Data link type, unsigned</li>
 * </ul>
 * <p>
 * PCAP record header.
 * <pre>
 *   typedef struct pcaprec_hdr_s {
 *           ggetUInt32 ts_sec;   // timestamp seconds
 *           ggetUInt32 ts_usec;  // timestamp microseconds
 *           ggetUInt32 incl_len; // number of octets of packet saved in file
 *           ggetUInt32 orig_len; // actual length of packet
 *   } pcaprec_hdr_t;
 * </pre>
 *
 * <ul>
 *   <li>ts_sec: the date and time when this packet was captured. This value is in seconds since
 *   January 1, 1970 00:00:00 GMT; this is also known as a UN*X time_t. You can use the ANSI C
 *   time() function from time.h to get this value, but you might use a more optimized way to
 *   get this timestamp value. If this timestamp isn't based on GMT (UTC), use thiszone from the
 *   global header for adjustments.</li>
 *   <li>ts_usec: in regular pcap files, the microseconds when this packet was captured,
 *   as an offset to ts_sec. In nanosecond-resolution files, this is, instead, the nanoseconds
 *   when the packet was captured, as an offset to ts_sec /!\ Beware: this value shouldn't reach
 *   1 second (in regular pcap files 1 000 000; in nanosecond-resolution files, 1 000 000 000);
 *   in this case ts_sec must be increased instead!</li>
 *   <li>incl_len: the number of bytes of packet data actually captured and saved in the file.
 *   This value should never become larger than orig_len or the snaplen value of the global
 *   header.</li>
 *   <li>orig_len: the length of the packet as it appeared on the network when it was captured.
 *   If incl_len and orig_len differ, the actually saved packet size was limited by snaplen.</li>
 * </ul>
 */
final class PcapParser {
    private static final long PCAP_MAGIC_NUMBER = 0xA1B2C3D4L;
    private static final long PCAP_MAGIC_NUMBER_SWAPPED = 0xD4C3B2A1L;
    private static final int PCAP_GHDR_MAGIC_NUMBER_LENGTH = 4;
    private static final int PCAP_GHDR_MAJOR_VERSION_LENGTH = 2;
    private static final int PCAP_GHDR_MINOR_VERSION_LENGTH = 2;
    private static final int PCAP_GHDR_GMT2LOCAL_TIME_CORRECTION_LENGTH = 4;
    private static final int PCAP_GHDR_TIMESTAMP_ACCURACY_LENGTH = 4;
    private static final int PCAP_GHDR_SNAPLEN_LENGTH = 4;
    private static final int PCAP_GHDR_NETWORK_LENGTH = 4;
    private static final int PCAP_GHDR_LENGTH =
            PCAP_GHDR_MAGIC_NUMBER_LENGTH
                    + PCAP_GHDR_MAJOR_VERSION_LENGTH
                    + PCAP_GHDR_MINOR_VERSION_LENGTH
                    + PCAP_GHDR_GMT2LOCAL_TIME_CORRECTION_LENGTH
                    + PCAP_GHDR_TIMESTAMP_ACCURACY_LENGTH
                    + PCAP_GHDR_SNAPLEN_LENGTH
                    + PCAP_GHDR_NETWORK_LENGTH;

    private static final int PCAP_GHDR_MAGIC_NUMBER_OFFSET = 0;
    private static final int PCAP_GHDR_MAJOR_VERSION_OFFSET = PCAP_GHDR_MAGIC_NUMBER_OFFSET + PCAP_GHDR_MAGIC_NUMBER_LENGTH;
    private static final int PCAP_GHDR_MINOR_VERSION_OFFSET = PCAP_GHDR_MAJOR_VERSION_OFFSET + PCAP_GHDR_MAJOR_VERSION_LENGTH;
    private static final int PCAP_GHDR_GMT2LOCAL_TIME_CORRECTION_OFFSET = PCAP_GHDR_MINOR_VERSION_OFFSET + PCAP_GHDR_MINOR_VERSION_LENGTH;
    private static final int PCAP_GHDR_TIMESTAMP_ACCURACY_OFFSET = PCAP_GHDR_GMT2LOCAL_TIME_CORRECTION_OFFSET + PCAP_GHDR_GMT2LOCAL_TIME_CORRECTION_LENGTH;
    private static final int PCAP_GHDR_SNAPLEN_OFFSET = PCAP_GHDR_TIMESTAMP_ACCURACY_OFFSET + PCAP_GHDR_TIMESTAMP_ACCURACY_LENGTH;
    private static final int PCAP_GHDR_NETWORK_OFFSET = PCAP_GHDR_SNAPLEN_OFFSET + PCAP_GHDR_SNAPLEN_LENGTH;

    private static final int PCAP_RHDR_TIMESTAMP_SECONDS_LENGTH = 4;
    private static final int PCAP_RHDR_TIMESTAMP_MICROS_LENGTH = 4;
    private static final int PCAP_RHDR_NUM_CAPTURED_OCTETS_LENGTH = 4;
    private static final int PCAP_RHDR_ORIG_LENGTH = 4;
    private static final int PCAP_RHDR_TIMESTAMP_LENGTH = PCAP_RHDR_TIMESTAMP_SECONDS_LENGTH + PCAP_RHDR_TIMESTAMP_MICROS_LENGTH;
    private static final int PCAP_RHDR_LENGTH = PCAP_RHDR_TIMESTAMP_LENGTH + PCAP_RHDR_NUM_CAPTURED_OCTETS_LENGTH + PCAP_RHDR_ORIG_LENGTH;
    private static final int PCAP_RHDR_SECONDS_OFFSET = 0;
    private static final int PCAP_RHDR_MICROS_OFFSET = PCAP_RHDR_SECONDS_OFFSET + PCAP_RHDR_TIMESTAMP_SECONDS_LENGTH;
    private static final int PCAP_RHDR_NUM_CAPTURED_OCTETS_OFFSET = PCAP_RHDR_MICROS_OFFSET + PCAP_RHDR_TIMESTAMP_MICROS_LENGTH;
    private static final int PCAP_RHDR_FRAME_LENGTH_OFFSET = PCAP_RHDR_NUM_CAPTURED_OCTETS_OFFSET + PCAP_RHDR_NUM_CAPTURED_OCTETS_LENGTH;

    private static final int LINK_TYPE_ETH = 1;
    private static final int LINK_TYPE_LINUX_SLL = 113;
    private static final int LINK_LAYER_PROTOCOL_TYPE_IPV4 = 0x800;

    private static final int ETH_MAC_ADDRESS_LENGTH = 6;
    private static final int ETH_PROTOCOL_TYPE_LENGTH = 2;
    private static final int ETH_DST_MAC_ADDR_OFFSET = 0;
    private static final int ETH_SRC_MAC_ADDR_OFFSET = ETH_DST_MAC_ADDR_OFFSET + ETH_MAC_ADDRESS_LENGTH;
    private static final int ETH_PROTOCOL_TYPE_OFFSET = ETH_SRC_MAC_ADDR_OFFSET + ETH_MAC_ADDRESS_LENGTH;
    private static final int ETH_HDR_LENGTH = ETH_PROTOCOL_TYPE_OFFSET + ETH_PROTOCOL_TYPE_LENGTH;

    private static final int LINUX_SLL_PACKET_TYPE_LENGTH = 2;
    private static final int LINUX_SLL_ADDRESS_TYPE_LENGTH = 2;
    private static final int LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH_LENGTH = 2;
    private static final int LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH = 8;
    private static final int LINUX_SLL_PROTOCOL_TYPE_LENGTH = 2;
    private static final int LINUX_SLL_PACKET_TYPE_OFFSET = 0;
    private static final int LINUX_SLL_ADDRESS_TYPE_OFFSET = LINUX_SLL_PACKET_TYPE_OFFSET + LINUX_SLL_PACKET_TYPE_LENGTH;
    private static final int LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH_OFFSET = LINUX_SLL_ADDRESS_TYPE_OFFSET + LINUX_SLL_ADDRESS_TYPE_LENGTH;
    private static final int LINUX_SLL_LINK_LAYER_ADDRESS_OFFSET = LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH_OFFSET + LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH_LENGTH;
    private static final int LINUX_SLL_PROTOCOL_TYPE_OFFSET = LINUX_SLL_LINK_LAYER_ADDRESS_OFFSET + LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH;
    private static final int LINUX_SLL_HDR_LENGTH = LINUX_SLL_PROTOCOL_TYPE_OFFSET + LINUX_SLL_PROTOCOL_TYPE_LENGTH;

    private static final int IPV4 = 4;
    private static final int IPV4_ADDR_LENGTH = 4;
    private static final int IPV4_MIN_HDR_LEN = 5;
    private static final int IPV4_PROTO_UDP = 0x11;
    private static final int IPV4_FLAGS_RSVD = 4;
    private static final int IPV4_FLAGS_DONT_FRAGMENT = 2;
    private static final int IPV4_FLAGS_MORE_FRAGMENT = 1;
    private static final int IPV4_VER_OFFSET = 0;
    private static final int IPV4_IHL_OFFSET = IPV4_VER_OFFSET;
    private static final int IPV4_TOS_OFFSET = IPV4_IHL_OFFSET + 1;
    private static final int IPV4_TOTLEN_OFFSET = IPV4_TOS_OFFSET + 1;
    private static final int IPV4_IDENT_OFFSET = IPV4_TOTLEN_OFFSET + 2;
    private static final int IPV4_FLAGS_OFFSET = IPV4_IDENT_OFFSET + 2;
    private static final int IPV4_FOFFSET_OFFSET = IPV4_FLAGS_OFFSET;
    private static final int IPV4_TTL_OFFSET = IPV4_FOFFSET_OFFSET + 2;
    private static final int IPV4_PROTO_OFFSET = IPV4_TTL_OFFSET + 1;
    private static final int IPV4_HDR_CHECKSUM_OFFSET = IPV4_PROTO_OFFSET + 1;
    private static final int IPV4_SRC_ADDR_OFFSET = IPV4_HDR_CHECKSUM_OFFSET + 2;
    private static final int IPV4_DST_ADDR_OFFSET = IPV4_SRC_ADDR_OFFSET + IPV4_ADDR_LENGTH;
    private static final int IPV4_HDR_LENGTH = IPV4_DST_ADDR_OFFSET + IPV4_ADDR_LENGTH;

    private static final int UDP_SRC_PORT_OFFSET = 0;
    private static final int UDP_DST_PORT_OFFSET = UDP_SRC_PORT_OFFSET + 2;
    private static final int UDP_LENGTH_OFFSET = UDP_DST_PORT_OFFSET + 2;
    private static final int UDP_CHECKSUM_OFFSET = UDP_LENGTH_OFFSET + 2;
    private static final int UDP_PAYLOAD_OFFSET = UDP_CHECKSUM_OFFSET + 2;
    private static final int UDP_HDR_LENGTH = UDP_PAYLOAD_OFFSET;

    private static final boolean IS_BIG_ENDIAN_NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    private static final long SECONDS2MICROS = 1000000;
    private static final Unsafe UNSAFE;

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
    }

    interface PacketHandler {
        int onNext(Buffer buffer, int offset, int length, int packetNumber, long timestamp);
    }

    static int parse(final File pcapFile, final PacketHandler packetHandler) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(pcapFile, "r");
             FileChannel channel = raf.getChannel()) {

            final long size = Math.min(channel.size(), Integer.MAX_VALUE);
            final MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            return parse(buffer, packetHandler);
        }
    }

    private static int parse(final ByteBuffer buffer, final PacketHandler packetHandler) {
        final int capacity = buffer.capacity();
        final Buffer buf = new Buffer(buffer);
        final byte[] byteArray = buf.byteArray();
        final long addressOffset = buf.addressOffset();

        // Parse the Global Header.
        // The magic number is used to detect the file format itself and the byte ordering.
        // The writing application writes 0xa1b2c3d4 with its native byte ordering format into this
        // field. The reading application will read either 0xa1b2c3d4 (identical) or 0xd4c3b2a1
        // (swapped). If the reading application reads the swapped 0xd4c3b2a1 value, it knows that
        // all the following fields will have to be swapped too.
        final long magicNumber = getUInt32BE(byteArray, addressOffset + PCAP_GHDR_MAGIC_NUMBER_OFFSET);
        final boolean bigEndian;
        if (magicNumber == PCAP_MAGIC_NUMBER) {
            bigEndian = true;
        } else {
            if (magicNumber != PCAP_MAGIC_NUMBER_SWAPPED) {
                return ERR_PCAP_MAGIC_NUMBER;
            }

            bigEndian = false;
        }

        final long network = getUInt32(byteArray, addressOffset + PCAP_GHDR_NETWORK_OFFSET, bigEndian);
        final int linkLayerHdrLength;
        final boolean linkLayerEthernet;
        if (network == LINK_TYPE_ETH) {
            linkLayerHdrLength = ETH_HDR_LENGTH;
            linkLayerEthernet = true;
        } else if (network == LINK_TYPE_LINUX_SLL) {
            linkLayerHdrLength = LINUX_SLL_HDR_LENGTH;
            linkLayerEthernet = false;
        } else {
            // Only support ETH and LINUX_SLL.
            return ERR_PCAP_UNSUPPORTED_NETWORK_TYPE;
        }

        final long snaplen = getUInt32(byteArray, addressOffset + PCAP_GHDR_SNAPLEN_OFFSET, bigEndian);
        final long baseOffset = addressOffset + PCAP_GHDR_LENGTH;
        final int rcapacity = capacity - PCAP_GHDR_LENGTH; // Record capacity, without global header.
        int pos = 0;
        int packetNumber = 0;
        while ((rcapacity - pos) > PCAP_RHDR_LENGTH) {
            // == Parse the record header.
            // The offset of a record.
            final long roffset = baseOffset + pos;

            // The date and time when this packet was capture. This value is in seconds since
            // January 1, 1970 00:00:00 GMT, this is also known as a UNIX time_t.
            final long ts_sec = getUInt32(byteArray, roffset + PCAP_RHDR_SECONDS_OFFSET, bigEndian);

            // In regular pcap files, the microseconds when this packet was captured, as an offset
            // to ts_sec. This value shouldn't reach 1 second.
            final long ts_usec = getUInt32(byteArray, roffset + PCAP_RHDR_MICROS_OFFSET, bigEndian);
            if (ts_usec > SECONDS2MICROS) {
                return ERR_PCAP_TS_USEC_GREATER_THAN_ONE_SECONDS;
            }

            // Number of octets of packet data actually captured and saved in the file.
            final long incl_len = getUInt32(byteArray, roffset + PCAP_RHDR_NUM_CAPTURED_OCTETS_OFFSET, bigEndian);

            // The length of the packet as it appeared on the network.
            final long orig_len = getUInt32(byteArray, roffset + PCAP_RHDR_FRAME_LENGTH_OFFSET, bigEndian);

            if (incl_len > snaplen) {
                return ERR_PCAP_NUM_CAPTURED_PACKETS_EXCEEDS_SNAPLEN;
            }

            if (incl_len > orig_len) {
                return ERR_PCAP_NUM_CAPTURED_PACKETS_EXCEEDS_ORIGLEN;
            }

            final int remaining = capacity - pos - PCAP_RHDR_LENGTH;
            if (remaining < incl_len) {
                return ERR_PCAP_NUM_BYTES_REMAINING;
            }

            // == Parse Link Layer.
            final long loffset = roffset + PCAP_RHDR_LENGTH; // Link layer offset.
            if (linkLayerEthernet) {
                final int proto = getUInt16BE(byteArray, loffset + ETH_PROTOCOL_TYPE_OFFSET);
                if (proto != LINK_LAYER_PROTOCOL_TYPE_IPV4) {
                    return ERR_PCAP_LINK_LAYER_PROTOCOL_TYPE;
                }
            } else { // LINK_TYPE_LINUX_SLL
                final int addressLength = getUInt16BE(byteArray, loffset + LINUX_SLL_LINK_LAYER_ADDRESS_LENGTH_OFFSET);
                if (addressLength != ETH_MAC_ADDRESS_LENGTH) {
                    return ERR_PCAP_LINUX_SLL_LINK_LAYER_ADDR_LENGTH;
                }

                final int protocol = getUInt16BE(byteArray, loffset + LINUX_SLL_PROTOCOL_TYPE_OFFSET);
                if (protocol != LINK_LAYER_PROTOCOL_TYPE_IPV4) {
                    return ERR_PCAP_LINUX_SLL_PROTOCOL_TYPE;
                }
            }

            // == Parse the Network Layer.
            final long noffset = loffset + linkLayerHdrLength; // Network layer offset.
            final short ipVersIHL = getUInt8(byteArray, noffset + IPV4_IHL_OFFSET);
            final byte ipVers = (byte) ((ipVersIHL & 0xf0) >> 4);
            final byte ipIHL = (byte) ((ipVersIHL & 0x0f));
            if (ipVers != IPV4) {
                return ERR_PCAP_IP_VERSION;
            }

            final int ipHdrLength = ipIHL << 2;
            if (ipIHL < IPV4_MIN_HDR_LEN) {
                return ERR_PCAP_IPV4_IHL;
            }

            final int ipLength = getUInt16BE(byteArray, noffset + IPV4_TOTLEN_OFFSET);
            final int nlen = linkLayerHdrLength + ipLength;
            if (nlen > incl_len) {
                return ERR_PCAP_IPV4_LENGTH;
            }

            // Flags & Fragment Offset
            final int flagsFragOff = getUInt16BE(byteArray, noffset + IPV4_FOFFSET_OFFSET);
            final byte flags = (byte) ((flagsFragOff & 0xe000) >> 13);
            if (!(flags == 0 ||
                    flags == IPV4_FLAGS_RSVD ||
                    flags == IPV4_FLAGS_DONT_FRAGMENT ||
                    flags == IPV4_FLAGS_MORE_FRAGMENT)) {
                return ERR_PCAP_IPV4_INVALID_FLAGS;
            }

            final short protocol = getUInt8(byteArray, noffset + IPV4_PROTO_OFFSET);
            if (protocol != IPV4_PROTO_UDP) {
                return ERR_PCAP_IPV4_PROTO;
            }

            // == Parse the Transport Layer UDP.
            final long toffset = noffset + IPV4_HDR_LENGTH;
            final int udpLength = getUInt16BE(byteArray, toffset + UDP_LENGTH_OFFSET);
            final int totalLength = linkLayerHdrLength + ipHdrLength + udpLength;
            if (udpLength < UDP_HDR_LENGTH || totalLength > incl_len) {
                return ERR_PCAP_UDP_LENGTH;
            }

            final long len = PCAP_RHDR_LENGTH + incl_len;
            final int recordOffset = pos + PCAP_GHDR_LENGTH;
            final int linkLayerOffset = recordOffset + PCAP_RHDR_LENGTH;
            final int ipOffset = linkLayerOffset + linkLayerHdrLength;
            final int udpOffset = ipOffset + ipHdrLength;
            final int udpPayloadOffset = udpOffset + UDP_HDR_LENGTH;
            final int udpPayloadLength = udpLength - UDP_HDR_LENGTH;
            packetNumber++;
            if (null != packetHandler) {
                final long ts = ts_sec * SECONDS2MICROS + ts_usec;
                final int res = packetHandler.onNext(buf, udpPayloadOffset, udpPayloadLength, packetNumber, ts);
                if (res != RES_OK) {
                    return res;
                }
            }

            pos += len;
        }

        return RES_OK;
    }

    private static short getUInt8(final byte[] byteArray, final long offset) {
        return (short) (UNSAFE.getByte(byteArray, offset) & 0xff);
    }

    private static long getUInt32(final byte[] byteArray, final long offset, final boolean bigEndian) {
        final int v = UNSAFE.getInt(byteArray, offset);
        return (bigEndian == IS_BIG_ENDIAN_NATIVE_ORDER ? v : Integer.reverseBytes(v)) & 0xffffffffL;
    }

    private static int getUInt16BE(final byte[] byteArray, final long offset) {
        final short v = UNSAFE.getShort(byteArray, offset);
        return (IS_BIG_ENDIAN_NATIVE_ORDER ? v : Short.reverseBytes(v)) & 0xffff;
    }

    private static long getUInt32BE(final byte[] byteArray, final long offset) {
        final int v = UNSAFE.getInt(byteArray, offset);
        return (IS_BIG_ENDIAN_NATIVE_ORDER ? v : Integer.reverseBytes(v)) & 0xffffffffL;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void rethrow(final Throwable t) throws T {
        throw (T) t;
    }
}
