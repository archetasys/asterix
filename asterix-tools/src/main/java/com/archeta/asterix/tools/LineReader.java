/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Read in a "logical line" from a FileChannel, skip all comment and blank lines and filter out
 * those leading whitespace characters (\u0020, \u0009 and \u000c) from the beginning of a
 * "natural line".
 */
final class LineReader {
    interface LineConsumer {
        boolean onNext(DirectBuffer buffer, int offset, int length);
    }

    static int readAll(final File file, final LineConsumer consumer) throws IOException {
        try (final RandomAccessFile raf = new RandomAccessFile(file, "r");
             final FileChannel channel = raf.getChannel()) {

            final ByteBuffer bb = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            final LineReader lineReader = new LineReader(bb, consumer);
            int line = 0;
            while (lineReader.readLine() >= 0) {
                line++;
            }

            return line;
        }
    }

    private final DirectBuffer buf;
    private final LineConsumer consumer;
    private final int limit;
    private int offset = 0;
    private int begin = 0;

    private LineReader(final ByteBuffer byteBuffer, final LineConsumer consumer) {
        this.buf = new DirectBuffer(byteBuffer);
        this.consumer = consumer;
        this.limit = buf.capacity();
    }

    private int readLine() {
        // Use locals to optimize for interpreted performance.
        int len = 0;

        boolean skipWhiteSpace = true;
        boolean appendedLineBegin = false;
        boolean precedingBackslash = false;
        char c;

        while (true) {
            if (offset >= limit) {
                if (len > 0) {
                    final int res = precedingBackslash ? len - 1 : len;
                    if (consumer.onNext(buf, begin, len)) {
                        return res;
                    }
                }
                return -1;
            }

            // (char)(byte & 0xff) is equivalent to calling a ISO8859-1 decoder.
            c = (char) (buf.getByte(offset++) & 0xff);

            if (skipWhiteSpace) {
                if (c == ' ' || c == '\t' || c == '\f') {
                    continue;
                }

                if (!appendedLineBegin && (c == '\r' || c == '\n')) {
                    continue;
                }

                skipWhiteSpace = false;
                appendedLineBegin = false;
            }

            if (len == 0) { // Still on a new logical line.
                if (c == '#' || c == '!') {
                    // Comment, quickly consume the rest of the line.

                    // When checking for new line characters a range check,
                    // starting with the higher bound ('\r') means one less
                    // branch in the common case.
                    commentLoop:
                    while (true) {
                        byte b;
                        while (offset < limit) {
                            b = buf.getByte(offset++);
                            if (b == '\r' || b == '\n') {
                                break commentLoop;
                            }
                        }

                        if (offset == limit) {
                            return -1;
                        }
                    }

                    skipWhiteSpace = true;
                    continue;
                }
            }

            if (c != '\n' && c != '\r') {
                if (len == 0) {
                    begin = offset - 1;
                }

                len++;

                // Flip the preceding backslash flag.
                precedingBackslash = (c == '\\') && !precedingBackslash;
            } else {
                // Reached EOL.
                if (len == 0) {
                    skipWhiteSpace = true;
                    continue;
                }

                if (offset >= limit) {
                    final int res = precedingBackslash ? len - 1 : len;
                    if (consumer.onNext(buf, begin, len)) {
                        return res;
                    }
                    return -1;
                }

                if (precedingBackslash) {
                    // Backslash at EOL is not part of the line.
                    len -= 1;
                    // Skip leading whitespace characters in the following line.
                    skipWhiteSpace = true;
                    appendedLineBegin = true;
                    precedingBackslash = false;
                    // Take care not to include any subsequent \n.
                    if (c == '\r') {
                        if (buf.getByte(offset) == '\n') {
                            offset++;
                        }
                    }
                } else {
                    if (consumer.onNext(buf, begin, len)) {
                        return len;
                    }
                    return -1;
                }
            }
        }
    }
}
