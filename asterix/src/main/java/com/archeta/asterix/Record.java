/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;
import java.util.Arrays;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.getCategoryNumberString;

public final class Record {
    private static final String BORDER = "-----------------------------------------------------------------------------";
    private static final String LF = System.lineSeparator();

    private final int[] fspecIndex2Offsets = new int[FSPEC_MAX_LENGTH];
    private long categoryId = NULL_CATEGORY_ID;
    private Buffer data = null;
    private int fspecOffset = 0;
    private int fspecNumOctets = 0;
    private int dataItemsNumOctets = 0;
    private long fspec = 0L;

    Record() {
        Arrays.fill(fspecIndex2Offsets, -1);
    }

    private Record(final Record other) {
        System.arraycopy(other.fspecIndex2Offsets, 0, fspecIndex2Offsets, 0, FSPEC_MAX_LENGTH);
        categoryId = other.categoryId;
        fspecOffset = CAT_LEN_NUM_OCTETS;
        fspecNumOctets = other.fspecNumOctets;
        fspec = other.fspec;
        dataItemsNumOctets = other.dataItemsNumOctets;
        final int len = CAT_LEN_NUM_OCTETS + fspecNumOctets + dataItemsNumOctets;
        data = other.getData(new Buffer(new byte[len]));
    }

    public long getCategoryId() {
        return categoryId;
    }

    public int getCategoryNumber() {
        return ASTERIXIds.getCategoryNumber(categoryId);
    }

    public int getLength() {
        return fspecNumOctets + dataItemsNumOctets;
    }

    public int getFSPECOffset() {
        return fspecOffset;
    }

    public int getFSPECNumOctets() {
        return fspecNumOctets;
    }

    public long getFSPEC() {
        return fspec;
    }

    public int getDataItemsNumOctets() {
        return dataItemsNumOctets;
    }

    public int getDataItemOffset(final int fspecIndex) {
        return fspecIndex2Offsets[fspecIndex];
    }

    public Record copy() {
        return new Record(this);
    }

    public Buffer getData(final Buffer buffer) {
        data.copyRecordData(getCategoryNumber(), getFSPECOffset(), buffer, getLength());
        return buffer;
    }

    void reset(
            final long categoryId,
            final Buffer buf,
            final int fspecOffset,
            final int fspecNumOctets,
            final int fspecMaxNumOctets,
            final long fspec) {

        final int toIndex = fspecMaxNumOctets << CAT_LEN_NUM_OCTETS;
        Arrays.fill(fspecIndex2Offsets, 0, toIndex, -1);
        this.categoryId = categoryId;
        this.data = buf;
        this.fspecOffset = fspecOffset;
        this.fspecNumOctets = fspecNumOctets;
        this.fspec = fspec;
        dataItemsNumOctets = 0;
    }

    void setDataItemOffset(final int dataItemIndex, final int offset) {
        fspecIndex2Offsets[dataItemIndex] = offset;
    }

    void setDataItemsNumOctets(final int dataItemsNumOctets) {
        this.dataItemsNumOctets = dataItemsNumOctets;
    }

    Buffer getData() {
        return data;
    }

    void append(
            final boolean withHeader,
            final boolean line,
            final boolean valueOnly,
            final UserApplicationProfile uap,
            final Appendable out) throws IOException {

        final int fspecOffset = this.fspecOffset;
        final int fspecNumOctets = this.fspecNumOctets;
        if (withHeader && !line) {
            out.append(LF)
                    .append(BORDER).append(LF)
                    .append("Category: ").append(getCategoryNumberString(ASTERIXIds.getCategoryNumber(uap.categoryId))).append(LF)
                    .append("Length  : ").append(Integer.toString(getLength())).append(LF)
                    .append("FSPEC   : ");

            final int last = fspecOffset + fspecNumOctets - 1;
            for (int j = fspecOffset; j < last; j++) {
                out.append(HexDump.i8(data.getByte(j))).append(' ');
            }

            out.append(HexDump.i8(data.getByte(last))).append(LF).append(BORDER).append(LF);
        }

        final DataFieldValueConsumer consumer = new DefaultDataFieldValueConsumer(out, line, valueOnly);
        final int fspecLength = uap.getFSPECLength();
        final int[] fspecIndex2Offsets = this.fspecIndex2Offsets;
        for (int i = 0; i < fspecLength; i++) {
            final DataItem item = uap.getDataItemByFSPECIndex(i);
            final int dataItemOffset = fspecIndex2Offsets[i];
            if (dataItemOffset < 0) {
                continue;
            }

            final int dataItemNumOctets = item.format.parseLength(data, dataItemOffset);
            if (dataItemNumOctets < 1) {
                continue;
            }

            if (!line) {
                out.append(LF).append(item.code2).append(' ').append(item.name).append(LF);
            }

            if (!line) {
                out.append('[');
                final int last = dataItemNumOctets - 1;
                for (int j = 0; j < last; j++) {
                    out.append(HexDump.i8(data.getByte(dataItemOffset + j))).append(' ');
                }

                out.append(HexDump.i8(data.getByte(dataItemOffset + last))).append(']').append(LF);
            }

            item.format.getAllDataFields(data, dataItemOffset, item, null, null, 0, consumer);
        }
    }
}
