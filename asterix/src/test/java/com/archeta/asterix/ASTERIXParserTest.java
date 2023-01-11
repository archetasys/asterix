/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.archeta.asterix.ASTERIXIds.categoryId;
import static com.archeta.asterix.Cat034V1Dot29.*;
import static com.archeta.asterix.Cat034V1Dot29.I034_050_PSR_CHAB;
import static com.archeta.asterix.Cat048V1Dot30.*;
import static com.archeta.asterix.Cat048V1Dot30.I048_240_IDENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ASTERIXParserTest {
    private static final Buffer CAT048_RECORD = new Buffer(HexDump.decode("300048ffff42321e1dce9ca81a1f24b5091f00c9e00a04c88a036b057478d35d200310010080e500001089c80030900000409c1987146004006000090a3d081c04163b60462020f5"));

    @Test
    void shouldAppendCategory034V1Dot29() throws Exception {
        final long cat034 = categoryId(34, 1, 29);
        final ASTERIXParser parser = ASTERIXParser.create(cat034);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat034, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat034_v1.29.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory048V1Dot30() throws Exception {
        final long cat048 = categoryId(48, 1, 30);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat048, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat048_v1.30.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory048V1Dot31() throws Exception {
        final long cat048 = categoryId(48, 1, 31);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat048, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat048_v1.31.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V0Dot23() throws Exception {
        final long cat021 = categoryId(21, 0, 23);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v0.23.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V0Dot231() throws Exception {
        final long cat021 = categoryId(21, 0, 231);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v0.231.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V0Dot24() throws Exception {
        final long cat021 = categoryId(21, 0, 24);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v0.24.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V0Dot26() throws Exception {
        final long cat021 = categoryId(21, 0, 26);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v0.26.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V1Dot0() throws Exception {
        final long cat021 = categoryId(21, 1, 0);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v1.0.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V1Dot7() throws Exception {
        final long cat021 = categoryId(21, 1, 7);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v1.7.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V2Dot1() throws Exception {
        final long cat021 = categoryId(21, 2, 1);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v2.1.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V2Dot2() throws Exception {
        final long cat021 = categoryId(21, 2, 2);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v2.2.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendCategory021V2Dot6() throws Exception {
        final long cat021 = categoryId(21, 2, 6);
        final ASTERIXParser parser = ASTERIXParser.create(cat021);
        final StringBuilder sb = new StringBuilder(8192);

        parser.appendCategory(cat021, true, true, true, false, sb);

        assertEquals(Files.readString(Paths.get(Resources.get("cat021_v2.6.txt").toURI())), sb.toString());
    }

    @Test
    void shouldAppendDataItemShort() throws IOException {
        final long cat048 = categoryId(48, 1, 30);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final StringBuilder sb = new StringBuilder(8192);
        parser.appendDataItem(I048_010, true, true, sb);
        final String expected = String.format("%-24s # %s%n%-24s # %s%n",
                "I048/010/SAC", "System Area Code",
                "I048/010/SIC", "System Identification Code");
        assertEquals(expected, sb.toString());
    }

    @Test
    void shouldGetAllDataItemFields() throws Exception {
        final long cat048 = categoryId(48, 1, 30);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final DataBlock block = new DataBlock();
        final int res = parser.parse(CAT048_RECORD, 0, CAT048_RECORD.capacity(), block, null, null);

        assertEquals(0, res);

        final Buffer buffer = new Buffer(new byte[1024]);
        final Appendable output = new StringBuilder(1024);
        final DataFieldValueConsumer consumer = new DefaultDataFieldValueConsumer(output, false, false);
        final int numRecords = block.getNumRecords();
        Record record;
        int length;
        for (int i = 0; i < numRecords; i++) {
            record = block.getRecord(i);
            length = parser.getAllDataItemFields(I048_010, record, consumer);
            assertEquals(2, length);
            length = parser.getAllDataFields(record, consumer);
            assertEquals(66, length);
            record = record.copy();
            length = record.getFSPECNumOctets() + record.getDataItemsNumOctets();
            assertTrue(Buffers.equals(CAT048_RECORD, 0, record.getData(buffer), 0, length + 3));
        }

        final String expected = Files.readString(Paths.get(Resources.get("cat048_record.txt").toURI()));
        assertEquals(expected, output.toString());
    }

    @Test
    void shouldParseDataBlockWithOneRecord() {
        final long cat048 = categoryId(48, 1, 30);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final DataBlock block = new DataBlock();
        final int res = parser.parse(CAT048_RECORD, 0, CAT048_RECORD.capacity(), block, null, null);
        assertEquals(0, res);
        assertEquals(cat048, block.getCategoryId());
        assertEquals(72, block.getLength());
        assertEquals(1, block.getNumRecords());
    }

    @Test
    void shouldGetDataField() throws IOException {
        final long cat048 = categoryId(48, 1, 30);
        final ASTERIXParser parser = ASTERIXParser.create(cat048);
        final DataBlock block = new DataBlock();
        final Buffer buffer = new Buffer(new byte[1024]);
        final int res = parser.parse(CAT048_RECORD, 0, CAT048_RECORD.capacity(), block, null, null);
        assertEquals(0, res);
        assertEquals(cat048, block.getCategoryId());
        assertEquals(72, block.getLength());
        assertEquals(1, block.getNumRecords());

        final Record record = block.getRecord(0);
        assertEquals(cat048, record.getCategoryId());
        assertEquals(69, record.getLength());
        assertEquals(3, record.getFSPECOffset());
        assertEquals(3, record.getFSPECNumOctets());
        assertEquals(66, record.getDataItemsNumOctets());

        record.getData(buffer);
        assertTrue(Buffers.equals(CAT048_RECORD, 0, buffer, 0, 72));

        final Record copy = record.copy();
        assertNotSame(record.getData(), copy.getData());
        assertEquals(cat048, copy.getCategoryId());
        assertEquals(69, copy.getLength());
        assertEquals(3, copy.getFSPECOffset());
        assertEquals(3, copy.getFSPECNumOctets());
        assertEquals(66, copy.getDataItemsNumOctets());
        copy.getData(buffer);
        assertTrue(Buffers.equals(CAT048_RECORD, 0, buffer, 0, 72));

        assertTrue(parser.hasDataItem(I048_010, record));
        assertTrue(parser.hasDataItem(I048_010_SAC, record));
        assertTrue(parser.hasDataItem(I048_010_SIC, record));
        assertTrue(parser.hasDataField(I048_010_SAC, record));
        assertTrue(parser.hasDataField(I048_010_SIC, record));
        assertEquals(6, parser.findDataItemOffset(I048_010, record));
        assertEquals(6, parser.findDataItemOffset(I048_010_SAC, record));
        assertEquals(6, parser.findDataItemOffset(I048_010_SIC, record));
        assertEquals(6, parser.findDataFieldOffset(I048_010_SAC, record));
        assertEquals(6, parser.findDataFieldOffset(I048_010_SIC, record));
        assertEquals(50, parser.getLong(I048_010_SAC, record));
        assertEquals(30, parser.getLong(I048_010_SIC, record));
        assertEquals(50, parser.getLong(I048_010_SAC, 6, record));
        assertEquals(30, parser.getLong(I048_010_SIC, 6, record));

        assertTrue(parser.hasDataItem(I048_140, record));
        assertTrue(parser.hasDataItem(I048_140_TOD, record));
        assertTrue(parser.hasDataField(I048_140_TOD, record));
        assertEquals(8, parser.findDataItemOffset(I048_140, record));
        assertEquals(8, parser.findDataItemOffset(I048_140_TOD, record));
        assertEquals(8, parser.findDataFieldOffset(I048_140_TOD, record));
        assertEquals(15261.21875, parser.getDouble(I048_140_TOD, record));
        assertEquals(15261.21875, parser.getDouble(I048_140_TOD, 8, record));
        assertEquals(0x1dce9c, parser.getLong(I048_140_TOD, record));
        assertEquals(0x1dce9c, parser.getLong(I048_140_TOD, 8, record));

        assertTrue(parser.hasDataItem(I048_020, record));
        assertTrue(parser.hasDataItem(I048_020_TYP, record));
        assertTrue(parser.hasDataField(I048_020_TYP, record));
        assertEquals(11, parser.findDataItemOffset(I048_020, record));
        assertEquals(11, parser.findDataItemOffset(I048_020_TYP, record));
        assertEquals(11, parser.findDataFieldOffset(I048_020_TYP, record));
        assertFalse(parser.getBoolean(I048_020_SIM, record));
        assertFalse(parser.getBoolean(I048_020_SIM, 11, record));
        assertEquals(0, parser.getLong(I048_020_SIM, record));
        assertEquals(0, parser.getLong(I048_020_SIM, 11, record));
        assertEquals(5, parser.getBitsValue(I048_020_TYP, record).value());
        assertEquals(5, parser.getBitsValue(I048_020_TYP, 11, record).value());
        assertEquals(5, parser.getLong(I048_020_TYP, record));
        assertEquals(5, parser.getLong(I048_020_TYP, 11, record));

        assertTrue(parser.hasDataItem(I048_040, record));
        assertTrue(parser.hasDataItem(I048_040_RHO, record));
        assertTrue(parser.hasDataItem(I048_040_THETA, record));
        assertTrue(parser.hasDataField(I048_040_RHO, record));
        assertTrue(parser.hasDataField(I048_040_THETA, record));
        assertEquals(12, parser.findDataItemOffset(I048_040, record));
        assertEquals(12, parser.findDataItemOffset(I048_040_RHO, record));
        assertEquals(12, parser.findDataItemOffset(I048_040_THETA, record));
        assertEquals(12, parser.findDataFieldOffset(I048_040_RHO, record));
        assertEquals(12, parser.findDataFieldOffset(I048_040_THETA, record));
        assertEquals(0x1a1f, parser.getLong(I048_040_RHO, record));
        assertEquals(0x24b5, parser.getLong(I048_040_THETA, record));
        assertEquals(0x1a1f, parser.getLong(I048_040_RHO, 12, record));
        assertEquals(0x24b5, parser.getLong(I048_040_THETA, 12, record));
        assertEquals(26.12109375, parser.getDouble(I048_040_RHO, record));
        assertEquals(51.6192626953125, parser.getDouble(I048_040_THETA, record));
        assertEquals(26.12109375, parser.getDouble(I048_040_RHO, 12, record));
        assertEquals(51.6192626953125, parser.getDouble(I048_040_THETA, 12, record));

        assertTrue(parser.hasDataItem(I048_240, record));
        assertTrue(parser.hasDataItem(I048_240_IDENT, record));
        assertTrue(parser.hasDataField(I048_240_IDENT, record));
        assertEquals(27, parser.findDataItemOffset(I048_240, record));
        assertEquals(27, parser.findDataItemOffset(I048_240_IDENT, record));
        assertEquals(27, parser.findDataFieldOffset(I048_240_IDENT, record));
        assertEquals("AWQ8454 ", parser.getString(I048_240_IDENT, record));
        assertEquals("AWQ8454 ", parser.getString(I048_240_IDENT, 27, record));

        final StringBuilder sb = new StringBuilder(8);
        assertEquals("AWQ8454 ", parser.getString(I048_240_IDENT, record, sb).toString());
        sb.setLength(0);
        assertEquals("AWQ8454 ", parser.getString(I048_240_IDENT, 27, record, sb).toString());
    }

    @Test
    void shouldGetDataFieldCompound() {
        final Buffer buf = new Buffer(HexDump.decode("22000ef432080217cfd548900020"));
        final long cat034 = categoryId(34, 1, 29);
        final ASTERIXParser parser = ASTERIXParser.create(cat034);
        final DataBlock block = new DataBlock();
        final Buffer buffer = new Buffer(new byte[1024]);
        final int res = parser.parse(buf, 0, buf.capacity(), block, null, null);

        assertEquals(0, res);
        assertEquals(cat034, block.getCategoryId());
        assertEquals(14, block.getLength());
        assertEquals(1, block.getNumRecords());

        final Record record = block.getRecord(0);
        assertEquals(cat034, record.getCategoryId());
        assertEquals(11, record.getLength());
        assertEquals(3, record.getFSPECOffset());
        assertEquals(1, record.getFSPECNumOctets());
        assertEquals(10, record.getDataItemsNumOctets());

        record.getData(buffer);
        assertTrue(Buffers.equals(buf, 0, buffer, 0, 14));

        assertTrue(parser.hasDataItem(I034_050, record));
        assertTrue(parser.hasDataItem(I034_050_COM_OVLRDP, record));
        assertTrue(parser.hasDataField(I034_050_COM_OVLRDP, record));
        assertEquals(11, parser.findDataItemOffset(I034_050, record));
        assertEquals(11, parser.findDataItemOffset(I034_050_COM_OVLRDP, record));
        assertEquals(12, parser.findDataFieldOffset(I034_050_COM_OVLRDP, record));
        assertFalse(parser.getBoolean(I034_050_COM_OVLRDP, record));
        assertFalse(parser.getBoolean(I034_050_COM_OVLRDP, 12, record));
        assertEquals(0, parser.getLong(I034_050_COM_OVLRDP, record));
        assertEquals(0, parser.getLong(I034_050_COM_OVLRDP, 12, record));

        assertTrue(parser.hasDataItem(I034_050_PSR_CHAB, record));
        assertTrue(parser.hasDataField(I034_050_PSR_CHAB, record));
        assertEquals(11, parser.findDataItemOffset(I034_050_PSR_CHAB, record));
        assertEquals(13, parser.findDataFieldOffset(I034_050_PSR_CHAB, record));
        assertEquals(1, parser.getBitsValue(I034_050_PSR_CHAB, record).value());
        assertEquals(1, parser.getBitsValue(I034_050_PSR_CHAB, 13, record).value());
        assertEquals(1, parser.getLong(I034_050_PSR_CHAB, record));
        assertEquals(1, parser.getLong(I034_050_PSR_CHAB, 13, record));
    }
}
