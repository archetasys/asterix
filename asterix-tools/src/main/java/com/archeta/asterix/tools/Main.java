/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.*;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.format.Mat5File;
import us.hebi.matlab.mat.format.Mat5Writer;
import us.hebi.matlab.mat.types.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.Deflater;

import static com.archeta.asterix.ASTERIXConstants.DATA_FORMAT_FIXED;

public final class Main {
    private static final String ERR_MAJOR_VERSION = "invalid major version number: '%s'";
    private static final String ERR_MINOR_VERSION = "invalid minor version number: '%s'";
    private static final int CMD_LIST = 0;
    private static final int CMD_PARSE = 1;
    private static final int CMD_CONVERT = 2;
    private static final int MODE_TEXT = 0;
    private static final int MODE_LINE = 1;

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        if (run(args) > 0) {
            System.out.println();
        }
    }

    private static int run(final String[] args) throws IOException {
        final int nargs = args.length;
        if (nargs < 1) {
            return checkForHelp(args);
        }

        checkForHelp(args);

        boolean isShort = false;
        boolean withDataItems = false;
        boolean withDataFields = false;
        boolean withUAP = false;
        boolean valueOnly = false;
        int cmd = CMD_LIST;
        int version = parseVersion("1.30");
        int catno = 48;
        int item = -1;
        int mode = MODE_TEXT;
        String infile = null;
        String outfile = null;
        String filterFile = null;
        String categoriesFile = null;
        String op;

        for (int i = 0; i < nargs; i++) {
            final String arg = args[i];
            switch (arg) {
                case "-L": {
                    final ASTERIXParser parser = createParser();
                    parser.appendCategories(true, System.out);
                    System.exit(0);
                    return 0;
                }
                case "-l": {
                    final ASTERIXParser parser = createParser();
                    parser.appendCategories(false, System.out);
                    System.exit(0);
                    return 0;
                }
                case "-s":
                    isShort = true;
                    break;
                case "-I":
                    withDataItems = true;
                    break;
                case "-D":
                    withDataFields = true;
                    break;
                case "-U":
                    withUAP = true;
                    break;
                case "-y":
                    valueOnly = true;
                    break;
                case "-V":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    op = args[++i];
                    version = parseVersion(op);
                    if (version < 0) {
                        if (version == -2) {
                            System.err.printf(ERR_MINOR_VERSION, op);
                        } else {
                            System.err.printf(ERR_MAJOR_VERSION, op);
                        }
                        System.exit(1);
                    }
                    break;
                case "-n":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    catno = parseCategoryNumber(args[++i]);
                    break;
                case "-i":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    item = parseIntNonnegative(args[++i]);
                    break;
                case "-f":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    infile = args[++i];
                    break;
                case "-o":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    outfile = args[++i];
                    break;
                case "-t":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    filterFile = args[++i];
                    break;
                case "-c":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    categoriesFile = args[++i];
                    break;
                case "-m":
                    cmd = CMD_CONVERT;
                    break;
                case "-p":
                    if (i == nargs - 1) {
                        return printHelpAndExit(1);
                    }

                    op = args[++i];
                    cmd = CMD_PARSE;
                    mode = parseMode(op);
                    if (mode < MODE_TEXT || mode > MODE_LINE) {
                        System.err.printf("unknown mode `%s`%n", op);
                        return printHelpAndExit(1);
                    }

                    break;
                default:
                    System.err.printf("unrecognised argument: `%s`%n", arg);
                    return printHelpAndExit(1);
            }
        }

        try {
            switch (cmd) {
                case CMD_LIST:
                    return list(catno, version, item, withDataItems, withDataFields, withUAP, isShort, outfile);
                case CMD_CONVERT:
                    return convert(infile, outfile, categoriesFile, filterFile);
                case CMD_PARSE:
                    return parse(mode, valueOnly, categoriesFile, infile, outfile);
                default:
                    System.err.println("unknown command");
                    return printHelpAndExit(1);
            }
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 1;
        }
    }

    private static int list(
            final int catno,
            final int version,
            final int item,
            final boolean withDataItems,
            final boolean withDataFields,
            final boolean withUAP,
            final boolean isShort,
            final String outFile) throws IOException {

        final int major = (version >> 8) & 0xff;
        final int minor = version & 0xff;
        final long categoryId = ASTERIXIds.categoryId(catno, major, minor);
        final ASTERIXParser parser = createParser();
        if (!parser.hasCategory(categoryId)) {
            System.err.printf("unsupported Category %03d Version %d.%d%n", catno, major, minor);
            System.exit(1);
            return 1;
        }

        if (item < 0) {
            if (null == outFile) {
                parser.appendCategory(categoryId, withDataItems, withDataFields, withUAP, isShort, System.out);
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
                    parser.appendCategory(categoryId, withDataItems, withDataFields, withUAP, isShort, writer);
                }
            }
        } else {
            final long dataItemId = ASTERIXIds.dataItemId(categoryId, item, DATA_FORMAT_FIXED);
            parser.appendDataItem(dataItemId, isShort, withDataFields, System.out);
        }

        return 0;
    }

    private static int convert(
            final String infile,
            final String outfile,
            final String categoriesFile,
            final String filterFile) throws IOException {

        if (null == infile) {
            System.err.println("input file must be provided");
            return printHelpAndExit(1);
        }

        if (null == outfile) {
            System.err.println("output file must be provided");
            return printHelpAndExit(1);
        }

        if (null == categoriesFile) {
            System.err.println("categories file must be provided");
            return printHelpAndExit(1);
        }

        if (null == filterFile) {
            System.err.println("filter file must be provided");
            return printHelpAndExit(1);
        }

        return convert(new File(infile), new File(outfile), new File(categoriesFile), new File(filterFile));
    }

    private static int parse(
            final int mode,
            final boolean valueOnly,
            final String categoriesFile,
            final String infile,
            final String outfile) throws IOException {

        if (null == infile) {
            System.err.println("input file must be provided");
            return printHelpAndExit(1);
        }

        final CategoryLineConsumer categoryLineConsumer = new CategoryLineConsumer();
        final int lines = LineReader.readAll(new File(categoriesFile), categoryLineConsumer);
        final ImmutableLong2LongHashMap m = categoryLineConsumer.getCategoryIdsByNumbersMap();
        final int sz = m.size();
        if (lines < 1 || sz < 1) {
            System.err.println("categories file must not empty");
            return printHelpAndExit(1);
        }

        final AtomicInteger index = new AtomicInteger();
        final long[] categoryIds = new long[sz];
        m.forEach((k, v) -> categoryIds[index.getAndIncrement()] = v);

        final ASTERIXParser parser = ASTERIXParser.create(categoryIds);

        final boolean withHeader = mode == MODE_TEXT;
        final boolean line = mode == MODE_LINE;
        final FileWriter writer = null == outfile ? null : new FileWriter(outfile);
        final Appendable output = null == outfile ? System.out : new BufferedWriter(writer);
        final DataBlock block = new DataBlock();
        final DataRecordConsumer recordConsumer = (r, n, b, p, t) -> {
            try {
                parser.appendRecord(r, withHeader, line, valueOnly, output);
            } catch (final IOException ex) {
                System.err.println(ex.getMessage());
            }
        };

        final int ignored = parser.parsePCAP(new File(infile), null, recordConsumer);
        if (writer != null) {
            writer.close();
        }

        return 0;
    }

    private static int convert(
            final File infile,
            final File outfile,
            final File categoriesFile,
            final File filterFile) throws IOException {

        final FilteredDataFields filteredDataFields = FilteredDataFields.readFromConfigFiles(categoriesFile, filterFile);

        System.out.println("Parsing file " + infile);

        final DataRecords records = DataRecords.parse(infile, filteredDataFields.categoryIds);
        final LongList categoryIds = records.categoryIds;
        final int numRecords = (int) records.recordCount;
        final int numCategories = categoryIds.size();

        System.out.println("#1 Parse duration      : " + (records.parseDurationNs / 1000000) + " ms");
        System.out.println("Number of packets      : " + records.packetCount);
        System.out.println("Number of data blocks  : " + records.blockCount);
        System.out.println("Number of data records : " + numRecords);
        System.out.println("Number of categories   : " + numCategories);

        // Creates common fields:
        final Matrix[] pnos = new Matrix[numCategories];
        final Matrix[] rnos = new Matrix[numCategories];
        final Matrix[] cats = new Matrix[numCategories];
        for (int i = 0; i < numCategories; i++) {
            pnos[i] = Mat5.newMatrix(numRecords, 1, MatlabType.UInt32);
            rnos[i] = Mat5.newMatrix(numRecords, 1, MatlabType.UInt32);
            cats[i] = Mat5.newMatrix(numRecords, 1, MatlabType.UInt8);
        }

        final ASTERIXParser parser = ASTERIXParser.create(categoryIds.copyElements());
        final DataFields[] dataFieldsByCategoryNumber = DataFields.from(filteredDataFields, numRecords, parser);
        final DataRecordConsumer recordConsumer = (r, n, b, p, t) -> {
            final int catno = ASTERIXIds.getCategoryNumber(r.getCategoryId());
            final DataFields dataFields = dataFieldsByCategoryNumber[catno];
            if (dataFields != null) {
                final int rowIndex = n - 1;
                for (int i = 0; i < numCategories; i++) {
                    pnos[i].setInt(rowIndex, 0, p);
                    rnos[i].setInt(rowIndex, 0, n);
                    cats[i].setInt(rowIndex, 0, catno);
                }

                dataFields.set(rowIndex, r.getFSPEC(), r.getFSPECNumOctets());
                parser.getAllDataFields(r, dataFields);
            }
        };

        final long t0 = System.nanoTime();
        parser.parsePCAP(infile, null, recordConsumer);
        final long e0 = System.nanoTime() - t0;
        System.out.println("#2 Parse duration      : " + (e0 / 1000000) + " ms");

        System.out.println("Save Mat file...");

        try (Sink sink = Sinks.newStreamingFile(outfile, false);
             Mat5File matFile = Mat5.newMatFile()) {
            for (int i = 0; i < numCategories; i++) {
                final long categoryId = categoryIds.get(i);
                final int catno = ASTERIXIds.getCategoryNumber(categoryId);
                final DataFields dataFields = dataFieldsByCategoryNumber[catno];
                if (dataFields != null) {
                    final Struct s = Mat5.newStruct(1, 1);
                    s.set("PNO", pnos[i]);
                    s.set("RNO", rnos[i]);
                    s.set("CAT", cats[i]);

                    final NamedArray[] arrays = dataFields.build();
                    NamedArray array;
                    for (int j = 0, n = arrays.length; j < n; j++) {
                        array = arrays[j];
                        s.set(array.name, array.array);
                    }

                    matFile.addArray("CAT" + ASTERIXIds.getCategoryNumberString(catno) + "Records", s);
                }
            }
            final int numThreads = Runtime.getRuntime().availableProcessors();
            final ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            final Mat5Writer writer = Mat5.newWriter(sink)
                    .enableConcurrentCompression(executor)
                    .setDeflateLevel(Deflater.BEST_SPEED);

            writer.writeMat(matFile);
            executor.shutdown();
            System.out.println("Done!");
        }

        return 0;
    }

    private static ASTERIXParser createParser() {
        return ASTERIXParser.create(
                Cat034V1Dot29.CAT034_ID,
                Cat048V1Dot30.CAT048_ID);
    }

    private static int parseMode(final String s) {
        switch (s) {
            case "text":
                return MODE_TEXT;
            case "line":
                return MODE_LINE;
            default:
                return -1;
        }
    }

    private static int parseCategoryNumber(final String s) {
        try {
            final int no = Integer.parseInt(s, 10);
            if (no < 0 || no > 255) {
                return -1;
            }
            return no;
        } catch (final NumberFormatException ex) {
            return -2;
        }
    }

    private static int parseIntNonnegative(final String s) {
        try {
            final int no = Integer.parseInt(s, 10);
            if (no < 0) {
                return -1;
            }
            return no;
        } catch (final NumberFormatException ex) {
            return -2;
        }
    }

    private static int parseVersion(final String s) {
        final int indexOfDot = s.indexOf('.');
        if (indexOfDot == -1) {
            final int major = parseIntNonnegative(s);
            if (major < 0 || major > 255) {
                return -1;
            }

            return (major & 0xff) << 8;
        }

        final int major = parseIntNonnegative(s.substring(0, indexOfDot));
        if (major < 0 || major > 255) {
            return -1;
        }

        final int minor = parseIntNonnegative(s.substring(indexOfDot + 1));
        if (minor < 0 || minor > 255) {
            return -2;
        }

        return ((major & 0xff) << 8) | (minor & 0xff);
    }

    private static int checkForHelp(final String[] args) {
        for (final String arg : args) {
            if ("?".equals(arg) || "-h".equals(arg)) {
                printHelpAndExit(0);
            }
        }

        return 0;
    }

    private static int printHelpAndExit(final int code) {
        System.out.format("astool%n" +
                "%nUSAGE:%n" +
                "    astool [FLAGS] [OPTIONS]%n" +
                "%nFLAGS:%n" +
                "    -h Print help information%n" +
                "    -l List versions short%n" +
                "    -L List versions long%n" +
                "    -U Print User Application Profile%n" +
                "    -I Print Data Items%n" +
                "    -D Print Data Fields%n" +
                "    -s Print short%n" +
                "    -y Print value only%n" +
                "    -m Convert PCAP file to MAT-file%n" +
                "%nOPTIONS:%n" +
                "    -n <no>              Set Category number%n" +
                "    -V <major.minor>     Set Category Version number%n" +
                "    -p <mode>            Parse PCAP file%n" +
                "    -i <item>            Set Data Item number%n" +
                "    -f <infile>          Input file name%n" +
                "    -o <outfile>         Output file name%n" +
                "    -c <categories file> Set Categories file%n" +
                "    -t <filter file>     Set filter file%n");
        System.exit(code);
        return code;
    }
}
