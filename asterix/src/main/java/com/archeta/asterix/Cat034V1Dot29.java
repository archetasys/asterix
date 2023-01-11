/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.*;
import static com.archeta.asterix.BitsField.*;
import static com.archeta.asterix.DataFormat.*;

public final class Cat034V1Dot29 {
    public static final long CAT034_ID = categoryId(34, 1, 29);
    public static final long I034_000;
    public static final long I034_000_TYP;
    public static final long I034_010;
    public static final long I034_010_SAC;
    public static final long I034_010_SIC;
    public static final long I034_020;
    public static final long I034_020_SNB;
    public static final long I034_030;
    public static final long I034_030_TOD;
    public static final long I034_041;
    public static final long I034_041_ARS;
    public static final long I034_050;
    public static final long I034_050_COM_NOGO;
    public static final long I034_050_COM_RDPC;
    public static final long I034_050_COM_RDPR;
    public static final long I034_050_COM_OVLRDP;
    public static final long I034_050_COM_OVLXMT;
    public static final long I034_050_COM_MSC;
    public static final long I034_050_COM_TSV;
    public static final long I034_050_COM_SP1;
    public static final long I034_050_PSR_ANT;
    public static final long I034_050_PSR_CHAB;
    public static final long I034_050_PSR_OVL;
    public static final long I034_050_PSR_MSC;
    public static final long I034_050_PSR_SP3;
    public static final long I034_050_PSR_SP2;
    public static final long I034_050_PSR_SP1;
    public static final long I034_050_SSR_ANT;
    public static final long I034_050_SSR_CHAB;
    public static final long I034_050_SSR_OVL;
    public static final long I034_050_SSR_MSC;
    public static final long I034_050_SSR_SP3;
    public static final long I034_050_SSR_SP2;
    public static final long I034_050_SSR_SP1;
    public static final long I034_050_MDS_ANT;
    public static final long I034_050_MDS_CHAB;
    public static final long I034_050_MDS_OVLSUR;
    public static final long I034_050_MDS_MSC;
    public static final long I034_050_MDS_SCF;
    public static final long I034_050_MDS_DLF;
    public static final long I034_050_MDS_OVLSCF;
    public static final long I034_050_MDS_OVLDLF;
    public static final long I034_050_MDS_SP7;
    public static final long I034_050_MDS_SP6;
    public static final long I034_050_MDS_SP5;
    public static final long I034_050_MDS_SP4;
    public static final long I034_050_MDS_SP3;
    public static final long I034_050_MDS_SP2;
    public static final long I034_050_MDS_SP1;
    public static final long I034_060;
    public static final long I034_060_COM_SP8;
    public static final long I034_060_COM_REDRDP;
    public static final long I034_060_COM_REDXMT;
    public static final long I034_060_COM_SP1;
    public static final long I034_060_PSR_POL;
    public static final long I034_060_PSR_REDRAD;
    public static final long I034_060_PSR_STC;
    public static final long I034_060_PSR_SP2;
    public static final long I034_060_PSR_SP1;
    public static final long I034_060_SSR_REDRAD;
    public static final long I034_060_SSR_SP5;
    public static final long I034_060_SSR_SP4;
    public static final long I034_060_SSR_SP3;
    public static final long I034_060_SSR_SP2;
    public static final long I034_060_SSR_SP1;
    public static final long I034_060_MDS_REDRAD;
    public static final long I034_060_MDS_CLU;
    public static final long I034_060_MDS_SP4;
    public static final long I034_060_MDS_SP3;
    public static final long I034_060_MDS_SP2;
    public static final long I034_060_MDS_SP1;
    public static final long I034_070;
    public static final long I034_070_TYP;
    public static final long I034_070_COUNT;
    public static final long I034_090;
    public static final long I034_090_RNG;
    public static final long I034_090_AZM;
    public static final long I034_100;
    public static final long I034_100_RHOST;
    public static final long I034_100_RHOEND;
    public static final long I034_100_THETAST;
    public static final long I034_100_THETAEND;
    public static final long I034_110;
    public static final long I034_110_FILTER;
    public static final long I034_120;
    public static final long I034_120_HGT;
    public static final long I034_120_LAT;
    public static final long I034_120_LON;
    public static final long I034_RE;
    public static final long I034_SP;

    private static final long I034_050_COM;
    private static final long I034_050_SP7;
    private static final long I034_050_SP6;
    private static final long I034_050_PSR;
    private static final long I034_050_SSR;
    private static final long I034_050_SP2;
    private static final long I034_050_FX1;
    private static final long I034_050_MDS;
    private static final long I034_060_COM;
    private static final long I034_060_SP7;
    private static final long I034_060_SP6;
    private static final long I034_060_PSR;
    private static final long I034_060_SSR;
    private static final long I034_060_MDS;
    private static final long I034_060_SP2;
    private static final long I034_060_FX1;

    static {
        I034_000 = dataItemId(CAT034_ID, 0, DATA_FORMAT_FIXED);
        I034_000_TYP = bitsFieldId(I034_000, 8, BITS_FIELD_ENCODING_VALUES);
        I034_010 = dataItemId(CAT034_ID, 10, DATA_FORMAT_FIXED);
        I034_010_SAC = bitsFieldId(I034_010, 16, BITS_FIELD_ENCODING_UNSIGNED);
        I034_010_SIC = bitsFieldId(I034_010, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I034_020 = dataItemId(CAT034_ID, 20, DATA_FORMAT_FIXED);
        I034_020_SNB = bitsFieldId(I034_020, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_030 = dataItemId(CAT034_ID, 30, DATA_FORMAT_FIXED);
        I034_030_TOD = bitsFieldId(I034_030, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_041 = dataItemId(CAT034_ID, 41, DATA_FORMAT_FIXED);
        I034_041_ARS = bitsFieldId(I034_041, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_050 = dataItemId(CAT034_ID, 50, DATA_FORMAT_COMPOUND);
        I034_050_COM = subAId(I034_050, 8, DATA_FORMAT_FIXED);
        I034_050_SP7 = subAId(I034_050, 7, DATA_FORMAT_EMPTY);
        I034_050_SP6 = subAId(I034_050, 6, DATA_FORMAT_EMPTY);
        I034_050_PSR = subAId(I034_050, 5, DATA_FORMAT_FIXED);
        I034_050_SSR = subAId(I034_050, 4, DATA_FORMAT_FIXED);
        I034_050_MDS = subAId(I034_050, 3, DATA_FORMAT_FIXED);
        I034_050_SP2 = subAId(I034_050, 2, DATA_FORMAT_EMPTY);
        I034_050_FX1 = subAId(I034_050, 1, DATA_FORMAT_EMPTY);
        I034_050_COM_NOGO = bitsFieldId(I034_050_COM, 8, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_RDPC = bitsFieldId(I034_050_COM, 7, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_RDPR = bitsFieldId(I034_050_COM, 6, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_OVLRDP = bitsFieldId(I034_050_COM, 5, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_OVLXMT = bitsFieldId(I034_050_COM, 4, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_MSC = bitsFieldId(I034_050_COM, 3, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_TSV = bitsFieldId(I034_050_COM, 2, BITS_FIELD_ENCODING_VALUE);
        I034_050_COM_SP1 = bitsFieldId(I034_050_COM, 1, BITS_FIELD_ENCODING_SPARE);
        I034_050_PSR_ANT = bitsFieldId(I034_050_PSR, 8, BITS_FIELD_ENCODING_VALUE);
        I034_050_PSR_CHAB = bitsFieldId(I034_050_PSR, 7, BITS_FIELD_ENCODING_VALUES);
        I034_050_PSR_OVL = bitsFieldId(I034_050_PSR, 5, BITS_FIELD_ENCODING_VALUE);
        I034_050_PSR_MSC = bitsFieldId(I034_050_PSR, 4, BITS_FIELD_ENCODING_VALUE);
        I034_050_PSR_SP3 = bitsFieldId(I034_050_PSR, 3, BITS_FIELD_ENCODING_SPARE);
        I034_050_PSR_SP2 = bitsFieldId(I034_050_PSR, 2, BITS_FIELD_ENCODING_SPARE);
        I034_050_PSR_SP1 = bitsFieldId(I034_050_PSR, 1, BITS_FIELD_ENCODING_SPARE);
        I034_050_SSR_ANT = bitsFieldId(I034_050_SSR, 8, BITS_FIELD_ENCODING_VALUE);
        I034_050_SSR_CHAB = bitsFieldId(I034_050_SSR, 7, BITS_FIELD_ENCODING_VALUES);
        I034_050_SSR_OVL = bitsFieldId(I034_050_SSR, 5, BITS_FIELD_ENCODING_VALUE);
        I034_050_SSR_MSC = bitsFieldId(I034_050_SSR, 4, BITS_FIELD_ENCODING_VALUE);
        I034_050_SSR_SP3 = bitsFieldId(I034_050_SSR, 3, BITS_FIELD_ENCODING_SPARE);
        I034_050_SSR_SP2 = bitsFieldId(I034_050_SSR, 2, BITS_FIELD_ENCODING_SPARE);
        I034_050_SSR_SP1 = bitsFieldId(I034_050_SSR, 1, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_ANT = bitsFieldId(I034_050_MDS, 16, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_CHAB = bitsFieldId(I034_050_MDS, 15, BITS_FIELD_ENCODING_VALUES);
        I034_050_MDS_OVLSUR = bitsFieldId(I034_050_MDS, 13, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_MSC = bitsFieldId(I034_050_MDS, 12, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_SCF = bitsFieldId(I034_050_MDS, 11, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_DLF = bitsFieldId(I034_050_MDS, 10, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_OVLSCF = bitsFieldId(I034_050_MDS, 9, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_OVLDLF = bitsFieldId(I034_050_MDS, 8, BITS_FIELD_ENCODING_VALUE);
        I034_050_MDS_SP7 = bitsFieldId(I034_050_MDS, 7, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP6 = bitsFieldId(I034_050_MDS, 6, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP5 = bitsFieldId(I034_050_MDS, 5, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP4 = bitsFieldId(I034_050_MDS, 4, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP3 = bitsFieldId(I034_050_MDS, 3, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP2 = bitsFieldId(I034_050_MDS, 2, BITS_FIELD_ENCODING_SPARE);
        I034_050_MDS_SP1 = bitsFieldId(I034_050_MDS, 1, BITS_FIELD_ENCODING_SPARE);
        I034_060 = dataItemId(CAT034_ID, 60, DATA_FORMAT_COMPOUND);
        I034_060_COM = subAId(I034_060, 8, DATA_FORMAT_FIXED);
        I034_060_SP7 = subAId(I034_060, 7, DATA_FORMAT_EMPTY);
        I034_060_SP6 = subAId(I034_060, 6, DATA_FORMAT_EMPTY);
        I034_060_PSR = subAId(I034_060, 5, DATA_FORMAT_FIXED);
        I034_060_SSR = subAId(I034_060, 4, DATA_FORMAT_FIXED);
        I034_060_MDS = subAId(I034_060, 3, DATA_FORMAT_FIXED);
        I034_060_SP2 = subAId(I034_060, 2, DATA_FORMAT_EMPTY);
        I034_060_FX1 = subAId(I034_060, 1, DATA_FORMAT_EMPTY);
        I034_060_COM_SP8 = bitsFieldId(I034_060_COM, 8, BITS_FIELD_ENCODING_SPARE);
        I034_060_COM_REDRDP = bitsFieldId(I034_060_COM, 7, BITS_FIELD_ENCODING_VALUES);
        I034_060_COM_REDXMT = bitsFieldId(I034_060_COM, 4, BITS_FIELD_ENCODING_VALUES);
        I034_060_COM_SP1 = bitsFieldId(I034_060_COM, 1, BITS_FIELD_ENCODING_SPARE);
        I034_060_PSR_POL = bitsFieldId(I034_060_PSR, 8, BITS_FIELD_ENCODING_VALUE);
        I034_060_PSR_REDRAD = bitsFieldId(I034_060_PSR, 7, BITS_FIELD_ENCODING_VALUES);
        I034_060_PSR_STC = bitsFieldId(I034_060_PSR, 4, BITS_FIELD_ENCODING_VALUES);
        I034_060_PSR_SP2 = bitsFieldId(I034_060_PSR, 2, BITS_FIELD_ENCODING_SPARE);
        I034_060_PSR_SP1 = bitsFieldId(I034_060_PSR, 1, BITS_FIELD_ENCODING_SPARE);
        I034_060_SSR_REDRAD = bitsFieldId(I034_060_SSR, 8, BITS_FIELD_ENCODING_VALUES);
        I034_060_SSR_SP5 = bitsFieldId(I034_060_SSR, 5, BITS_FIELD_ENCODING_SPARE);
        I034_060_SSR_SP4 = bitsFieldId(I034_060_SSR, 4, BITS_FIELD_ENCODING_SPARE);
        I034_060_SSR_SP3 = bitsFieldId(I034_060_SSR, 3, BITS_FIELD_ENCODING_SPARE);
        I034_060_SSR_SP2 = bitsFieldId(I034_060_SSR, 2, BITS_FIELD_ENCODING_SPARE);
        I034_060_SSR_SP1 = bitsFieldId(I034_060_SSR, 1, BITS_FIELD_ENCODING_SPARE);
        I034_060_MDS_REDRAD = bitsFieldId(I034_060_MDS, 8, BITS_FIELD_ENCODING_VALUES);
        I034_060_MDS_CLU = bitsFieldId(I034_060_MDS, 5, BITS_FIELD_ENCODING_VALUE);
        I034_060_MDS_SP4 = bitsFieldId(I034_060_MDS, 4, BITS_FIELD_ENCODING_SPARE);
        I034_060_MDS_SP3 = bitsFieldId(I034_060_MDS, 3, BITS_FIELD_ENCODING_SPARE);
        I034_060_MDS_SP2 = bitsFieldId(I034_060_MDS, 2, BITS_FIELD_ENCODING_SPARE);
        I034_060_MDS_SP1 = bitsFieldId(I034_060_MDS, 1, BITS_FIELD_ENCODING_SPARE);
        I034_070 = dataItemId(CAT034_ID, 70, DATA_FORMAT_REPETITIVE);
        I034_070_TYP = bitsFieldId(I034_070, 16, BITS_FIELD_ENCODING_VALUES);
        I034_070_COUNT = bitsFieldId(I034_070, 11, BITS_FIELD_ENCODING_UNSIGNED);
        I034_090 = dataItemId(CAT034_ID, 90, DATA_FORMAT_FIXED);
        I034_090_RNG = bitsFieldId(I034_090, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I034_090_AZM = bitsFieldId(I034_090, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I034_100 = dataItemId(CAT034_ID, 100, DATA_FORMAT_FIXED);
        I034_100_RHOST = bitsFieldId(I034_100, 64, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_100_RHOEND = bitsFieldId(I034_100, 48, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_100_THETAST = bitsFieldId(I034_100, 32, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_100_THETAEND = bitsFieldId(I034_100, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_110 = dataItemId(CAT034_ID, 110, DATA_FORMAT_FIXED);
        I034_110_FILTER = bitsFieldId(I034_110, 8, BITS_FIELD_ENCODING_VALUES);
        I034_120 = dataItemId(CAT034_ID, 120, DATA_FORMAT_FIXED);
        I034_120_HGT = bitsFieldId(I034_120, 64, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I034_120_LAT = bitsFieldId(I034_120, 48, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I034_120_LON = bitsFieldId(I034_120, 24, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I034_RE = dataItemId(CAT034_ID, DATA_ITEM_RE, DATA_FORMAT_EXPLICIT);
        I034_SP = dataItemId(CAT034_ID, DATA_ITEM_SP, DATA_FORMAT_EXPLICIT);
    }

    static ASTERIXCategory createCategory() {
        final DataFormat f034_000 = fixed(I034_000, 1,
                bits(I034_000_TYP, position(1, 8, 1), "TYP", "Message Type",
                        BitsValue.of(0, "Not defined; never used"),
                        BitsValue.of(1, "North Marker Message"),
                        BitsValue.of(2, "Sector Crossing Message"),
                        BitsValue.of(3, "Geographical Filtering Message"),
                        BitsValue.of(4, "Jamming Strobe Message"),
                        BitsValue.of(5, "Solar Storm Message"),
                        BitsValue.of(6, "SSR Jamming Strobe Message"),
                        BitsValue.of(7, "Mode S Jamming Strobe Message")));

        final DataItem i034_000 = DataItem.from(f034_000, "Message Type", true,
                "This Data Item allows for a more convenient handling of the messages at the receiver side by further defining the type of transaction");

        final DataFormat f034_010 = fixed(I034_010, 2,
                unsigned(I034_010_SAC, position(2, 16, 9), "SAC", "System Area Code"),
                unsigned(I034_010_SIC, position(2, 8, 1), "SIC", "System Identification Code"));

        final DataItem i034_010 = DataItem.from(f034_010, "Data Source Identifier", true,
                "Identification of the radar station from which the data are received");

        final DataFormat f034_020 = fixed(I034_020, 1,
                umeasure(I034_020_SNB, position(1, 8, 1), "SNB", 360.0 / (1 << 8), UnitOfMeasure.ANGLE_DEGREE, "Sector Number"));

        final DataItem i034_020 = DataItem.from(f034_020, "Sector Number", false,
                "Eight most significant bits of the antenna azimuth defining a particular azimuth sector");

        final DataFormat f034_030 = fixed(I034_030, 3,
                umeasure(I034_030_TOD, position(3, 24, 1), "TOD", 1.0 / 128.0, UnitOfMeasure.TIME_SECONDS, "Time of Day"));

        final DataItem i034_030 = DataItem.from(f034_030, "Time of Day", true,
                "Absolute time stamping expressed as UTC time");

        final DataFormat f034_041 = fixed(I034_041, 2,
                umeasure(I034_041_ARS, position(2, 16, 1), "ARS", 1.0 / 128.0, UnitOfMeasure.TIME_SECONDS, "Antenna Rotation Speed"));

        final DataItem i034_041 = DataItem.from(f034_041, "Antenna Rotation Speed", false,
                "Antenna rotation period as measured between two consecutive North crossings or as averaged during a period of time");

        final DataFormat f034_050_COM = fixed(I034_050_COM, 1,
                bit(I034_050_COM_NOGO, position(1, 8, 8), "NOGO", "Operational Release Status of the System",
                        BitsValue.of(0, "System is released for operational use"),
                        BitsValue.of(1, "Operational use of System is inhibited, i.e. the data shall be discarded by an operational SDPS")),
                bit(I034_050_COM_RDPC, position(1, 7, 7), "RDPC", "Radar Data Processor Chain Selection Status",
                        BitsValue.of(0, "RDPC-1 selected"),
                        BitsValue.of(1, "RDPC-2 selected")),
                bit(I034_050_COM_RDPR, position(1, 6, 6), "RDPR", "Event to signal a reset/restart of the selected Radar Data Processor Chain",
                        BitsValue.of(0, "Default Situation"),
                        BitsValue.of(1, "Reset of RDPC")),
                bit(I034_050_COM_OVLRDP, position(1, 5, 5), "OVLRDP", "Radar Data Processor Overload Indicator",
                        BitsValue.of(0, "Default, no overload"),
                        BitsValue.of(1, "Overload in RDP")),
                bit(I034_050_COM_OVLXMT, position(1, 4, 4), "OVLXMT", "Transmission Subsystem Overload Status",
                        BitsValue.of(0, "Default, no overload"),
                        BitsValue.of(1, "Overload in Transmission Subsystem")),
                bit(I034_050_COM_MSC, position(1, 3, 3), "MSC", "Monitoring System Connected Status",
                        BitsValue.of(0, "Monitoring System connected"),
                        BitsValue.of(1, "Monitoring System disconnected")),
                bit(I034_050_COM_TSV, position(1, 2, 2), "TSV", "Time Source Validity",
                        BitsValue.of(0, "Valid"),
                        BitsValue.of(1, "Invalid")),
                spare(I034_050_COM_SP1, position(1, 1, 1)));

        final DataFormat f034_050_PSR = fixed(I034_050_PSR, 1,
                bit(I034_050_PSR_ANT, position(1, 8, 8), "ANT", "Selected antenna",
                        BitsValue.of(0, "Antenna 1"),
                        BitsValue.of(1, "Antenna 2")),
                bits(I034_050_PSR_CHAB, position(1, 7, 6), "CHAB", "Channel A/B selection status",
                        BitsValue.of(0, "No channel selected"),
                        BitsValue.of(1, "Channel A only selected"),
                        BitsValue.of(2, "Channel B only selected"),
                        BitsValue.of(3, "Diversity mode; Channel A and B selected")),
                bit(I034_050_PSR_OVL, position(1, 5, 5), "OVL", "Overload condition",
                        BitsValue.of(0, "No overload"),
                        BitsValue.of(1, "Overload")),
                bit(I034_050_PSR_MSC, position(1, 4, 4), "MSC", "Monitoring System Connected Status",
                        BitsValue.of(0, "Monitoring System connected"),
                        BitsValue.of(1, "Monitoring System disconnected")),
                spare(I034_050_PSR_SP3, position(1, 3, 3)),
                spare(I034_050_PSR_SP2, position(1, 2, 2)),
                spare(I034_050_PSR_SP1, position(1, 1, 1)));

        final DataFormat f034_050_SSR = fixed(I034_050_SSR, 1,
                bit(I034_050_SSR_ANT, position(1, 8, 8), "ANT", "Selected antenna",
                        BitsValue.of(0, "Antenna 1"),
                        BitsValue.of(1, "Antenna 2")),
                bits(I034_050_SSR_CHAB, position(1, 7, 6), "CHAB", "Channel A/B selection status",
                        BitsValue.of(0, "No channel selected"),
                        BitsValue.of(1, "Channel A only selected"),
                        BitsValue.of(2, "Channel B only selected"),
                        BitsValue.of(3, "Invalid combination")),
                bit(I034_050_SSR_OVL, position(1, 5, 5), "OVL", "Overload condition",
                        BitsValue.of(0, "No overload"),
                        BitsValue.of(1, "Overload")),
                bit(I034_050_SSR_MSC, position(1, 4, 4), "MSC", "Monitoring System Connected Status",
                        BitsValue.of(0, "Monitoring System connected"),
                        BitsValue.of(1, "Monitoring System disconnected")),
                spare(I034_050_SSR_SP3, position(1, 3, 3)),
                spare(I034_050_SSR_SP2, position(1, 2, 2)),
                spare(I034_050_SSR_SP1, position(1, 1, 1)));

        final DataFormat f034_050_MDS = fixed(I034_050_MDS, 2,
                bit(I034_050_MDS_ANT, position(2, 16, 16), "ANT", "Selected antenna",
                        BitsValue.of(0, "Antenna 1"),
                        BitsValue.of(1, "Antenna 2")),
                bits(I034_050_MDS_CHAB, position(2, 15, 14), "CHAB", "Channel A/B selection status",
                        BitsValue.of(0, "No channel selected"),
                        BitsValue.of(1, "Channel A only selected"),
                        BitsValue.of(2, "Channel B only selected"),
                        BitsValue.of(3, "Invalid combination")),
                bit(I034_050_MDS_OVLSUR, position(2, 13, 13), "OVLSUR", "Overload condition",
                        BitsValue.of(0, "No overload"),
                        BitsValue.of(1, "Overload")),
                bit(I034_050_MDS_MSC, position(2, 12, 12), "MSC", "Monitoring System Connected Status",
                        BitsValue.of(0, "Monitoring System connected"),
                        BitsValue.of(1, "Monitoring System disconnected")),
                bit(I034_050_MDS_SCF, position(2, 11, 11), "SCF", "Channel A/B selection status for Surveillance Co-ordination Function",
                        BitsValue.of(0, "Channel A in use"),
                        BitsValue.of(1, "Channel B in use")),
                bit(I034_050_MDS_DLF, position(2, 10, 10), "DLF", "Channel A/B selection status for Data Link Function",
                        BitsValue.of(0, "Channel A in use"),
                        BitsValue.of(1, "Channel B in use")),
                bit(I034_050_MDS_OVLSCF, position(2, 9, 9), "OVLSCF", "Overload in Surveillance Co-ordination Function",
                        BitsValue.of(0, "No overload"),
                        BitsValue.of(1, "Overload")),
                bit(I034_050_MDS_OVLDLF, position(2, 8, 8), "OVLDLF", "Overload in Data Link Function",
                        BitsValue.of(0, "No overload"),
                        BitsValue.of(1, "Overload")),
                spare(I034_050_MDS_SP7, position(2, 7, 7)),
                spare(I034_050_MDS_SP6, position(2, 6, 6)),
                spare(I034_050_MDS_SP5, position(2, 5, 5)),
                spare(I034_050_MDS_SP4, position(2, 4, 4)),
                spare(I034_050_MDS_SP3, position(2, 3, 3)),
                spare(I034_050_MDS_SP2, position(2, 2, 2)),
                spare(I034_050_MDS_SP1, position(2, 1, 1)));

        final DataFormat f034_050 = compound(I034_050,
                Subfield.of(f034_050_COM, 1, "COM", "Common Part"),
                Subfield.sp(I034_050_SP7, 2),
                Subfield.sp(I034_050_SP6, 3),
                Subfield.of(f034_050_PSR, 4, "PSR", "Specific Status Information for PSR Sensor"),
                Subfield.of(f034_050_SSR, 5, "SSR", "Specific Status Information for SSR Sensor"),
                Subfield.of(f034_050_MDS, 6, "MDS", "Specific Status Information for Mode-S Sensor"),
                Subfield.sp(I034_050_SP2, 7),
                Subfield.fx(I034_050_FX1));

        final DataItem i034_050 = DataItem.from(f034_050, "System Configuration and Status", false,
                "Information concerning the configuration and status of a System");

        final DataFormat f034_060_COM = fixed(I034_060_COM, 1,
                spare(I034_060_COM_SP8, position(1, 8, 8)),
                bits(I034_060_COM_REDRDP, position(1, 7, 5), "REDRDP", "Reduction Steps in use for an overload of the RDP",
                        BitsValue.of(0, "No reduction active"),
                        BitsValue.of(1, "Reduction step 1 active"),
                        BitsValue.of(2, "Reduction step 2 active"),
                        BitsValue.of(3, "Reduction step 3 active"),
                        BitsValue.of(4, "Reduction step 4 active"),
                        BitsValue.of(5, "Reduction step 5 active"),
                        BitsValue.of(6, "Reduction step 6 active"),
                        BitsValue.of(7, "Reduction step 7 active")),
                bits(I034_060_COM_REDXMT, position(1, 4, 2), "REDXMT", "Reduction Steps in use for an overload of the Transmission subsystem",
                        BitsValue.of(0, "No reduction active"),
                        BitsValue.of(1, "Reduction step 1 active"),
                        BitsValue.of(2, "Reduction step 2 active"),
                        BitsValue.of(3, "Reduction step 3 active"),
                        BitsValue.of(4, "Reduction step 4 active"),
                        BitsValue.of(5, "Reduction step 5 active"),
                        BitsValue.of(6, "Reduction step 6 active"),
                        BitsValue.of(7, "Reduction step 7 active")),
                spare(I034_060_COM_SP1, position(1, 1, 1)));

        final DataFormat f034_060_PSR = fixed(I034_060_PSR, 1,
                bit(I034_060_PSR_POL, position(1, 8, 8), "POL", "Polarization in use by PSR",
                        BitsValue.of(0, "Linear Polarization"),
                        BitsValue.of(1, "Circular Polarization")),
                bits(I034_060_PSR_REDRAD, position(1, 7, 5), "REDRAD", "Reduction Steps in use as result of an overload within the PSR subsystem",
                        BitsValue.of(0, "No reduction active"),
                        BitsValue.of(1, "Reduction step 1 active"),
                        BitsValue.of(2, "Reduction step 2 active"),
                        BitsValue.of(3, "Reduction step 3 active"),
                        BitsValue.of(4, "Reduction step 4 active"),
                        BitsValue.of(5, "Reduction step 5 active"),
                        BitsValue.of(6, "Reduction step 6 active"),
                        BitsValue.of(7, "Reduction step 7 active")),
                bits(I034_060_PSR_STC, position(1, 4, 3), "STC", "Sensitivity Time Control Map in use",
                        BitsValue.of(0, "STC Map-1"),
                        BitsValue.of(1, "STC Map-2"),
                        BitsValue.of(2, "STC Map-3"),
                        BitsValue.of(3, "STC Map-4")),
                spare(I034_060_PSR_SP2, position(1, 2, 2)),
                spare(I034_060_PSR_SP1, position(1, 1, 1)));

        final DataFormat f034_060_SSR = fixed(I034_060_SSR, 1,
                bits(I034_060_SSR_REDRAD, position(1, 8, 6), "REDRAD", "Reduction Steps in use as result of an overload within the SSR subsystem",
                        BitsValue.of(0, "No reduction active"),
                        BitsValue.of(1, "Reduction step 1 active"),
                        BitsValue.of(2, "Reduction step 2 active"),
                        BitsValue.of(3, "Reduction step 3 active"),
                        BitsValue.of(4, "Reduction step 4 active"),
                        BitsValue.of(5, "Reduction step 5 active"),
                        BitsValue.of(6, "Reduction step 6 active"),
                        BitsValue.of(7, "Reduction step 7 active")),
                spare(I034_060_SSR_SP5, position(1, 5, 5)),
                spare(I034_060_SSR_SP4, position(1, 4, 4)),
                spare(I034_060_SSR_SP3, position(1, 3, 3)),
                spare(I034_060_SSR_SP2, position(1, 2, 2)),
                spare(I034_060_SSR_SP1, position(1, 1, 1)));

        final DataFormat f034_060_MDS = fixed(I034_060_MDS, 1,
                bits(I034_060_MDS_REDRAD, position(1, 8, 6), "REDRAD", "Reduction Steps in use as result of an overload within the Mode-S subsystem",
                        BitsValue.of(0, "No reduction active"),
                        BitsValue.of(1, "Reduction step 1 active"),
                        BitsValue.of(2, "Reduction step 2 active"),
                        BitsValue.of(3, "Reduction step 3 active"),
                        BitsValue.of(4, "Reduction step 4 active"),
                        BitsValue.of(5, "Reduction step 5 active"),
                        BitsValue.of(6, "Reduction step 6 active"),
                        BitsValue.of(7, "Reduction step 7 active")),
                bit(I034_060_MDS_CLU, position(1, 5, 5), "CLU", "Cluster State",
                        BitsValue.of(0, "Autonomous"),
                        BitsValue.of(1, "Not autonomous")),
                spare(I034_060_MDS_SP4, position(1, 4, 4)),
                spare(I034_060_MDS_SP3, position(1, 3, 3)),
                spare(I034_060_MDS_SP2, position(1, 2, 2)),
                spare(I034_060_MDS_SP1, position(1, 1, 1)));

        final DataFormat f034_060 = compound(I034_060,
                Subfield.of(f034_060_COM, 1, "COM", "Common Part"),
                Subfield.sp(I034_060_SP7, 2),
                Subfield.sp(I034_060_SP6, 3),
                Subfield.of(f034_060_PSR, 4, "PSR", "Specific Processing Mode Information for PSR Sensor"),
                Subfield.of(f034_060_SSR, 5, "SSR", "Specific Processing Mode Information for SSR Sensor"),
                Subfield.of(f034_060_MDS, 6, "MDS", "Specific Processing Mode Information for Mode-S Sensor"),
                Subfield.sp(I034_060_SP2, 7),
                Subfield.fx(I034_060_FX1));

        final DataItem i034_060 = DataItem.from(f034_060, "System Processing Mode", false,
                "Status concerning the processing options, in use during the last antenna revolution, for the various Sensors, composing the System");

        final DataFormat f034_070 = repetitive(I034_070, 2, null,
                bits(I034_070_TYP, position(2, 16, 12), "TYP", "Type of message counter",
                        BitsValue.of(0, "No detection (number of misses)"),
                        BitsValue.of(1, "Single PSR target reports"),
                        BitsValue.of(2, "Single SSR target reports (Non-Mode-S)"),
                        BitsValue.of(3, "SSR+PSR target reports (Non-Mode-S)"),
                        BitsValue.of(4, "Single All-Call target reports (Mode-S)"),
                        BitsValue.of(5, "Single Roll-Call target reports (Mode-S)"),
                        BitsValue.of(6, "All-Call + PSR (Mode-S) target reports"),
                        BitsValue.of(7, "Roll-Call + PSR (Mode-S) target reports"),
                        BitsValue.of(8, "Filter for Weather data"),
                        BitsValue.of(9, "Filter for Jamming Strobe"),
                        BitsValue.of(10, "Filter for PSR data"),
                        BitsValue.of(11, "Filter for SSR/Mode-S data"),
                        BitsValue.of(12, "Filter for SSR/Mode-S+PSR data"),
                        BitsValue.of(13, "Filter for Enhanced Surveillance data"),
                        BitsValue.of(14, "Filter for PSR+Enhanced Surveillance"),
                        BitsValue.of(15, "Filter for PSR+Enhanced Surveillance SSR/Mode-S data not in Area of Prime Interest"),
                        BitsValue.of(16, "Filter for PSR+Enhanced Surveillance all SSR/Mode-S data"),
                        BitsValue.of(17, "Re-Interrogations (per sector)"),
                        BitsValue.of(18, "BDS Swap and wrong DF replies (per sector)"),
                        BitsValue.of(19, "Mode-A/C FRUIT (per sector"),
                        BitsValue.of(20, "Mode-S FRUIT (per sector)")),
                unsigned(I034_070_COUNT, position(2, 11, 1), "COUNT", "11-bit counter value"));

        final DataItem i034_070 = DataItem.from(f034_070, "Message Count Values", false,
                "Message Count values, according the various types of messages, for the last completed antenna revolution, counted between two North crossings");

        final DataFormat f034_090 = fixed(I034_090, 2,
                smeasure(I034_090_RNG, position(2, 16, 9), "RNG", 1.0 / 128.0, UnitOfMeasure.DISTANCE_NM, "Range Error"),
                smeasure(I034_090_AZM, position(2, 8, 1), "AZM", 360.0 / (1 << 14), UnitOfMeasure.ANGLE_DEGREE, "Azimuth Error"));

        final DataItem i034_090 = DataItem.from(f034_090, "Collimation Error", false,
                "Averaged difference in range and in azimuth for the primary target position with respect to the SSR target position as calculated by the radar station");

        final DataFormat f034_100 = fixed(I034_100, 8,
                umeasure(I034_100_RHOST, position(8, 64, 49), "RHOST", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Rho start"),
                umeasure(I034_100_RHOEND, position(8, 48, 33), "RHOEND", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Rho end"),
                umeasure(I034_100_THETAST, position(8, 32, 17), "THETAST", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Theta start"),
                umeasure(I034_100_THETAEND, position(8, 16, 1), "THETAEND", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Theta end"));

        final DataItem i034_100 = DataItem.from(f034_100, "Generic Polar Window", false,
                "Geographical window defined in polar co-ordinates");

        final DataFormat f034_110 = fixed(I034_110, 1,
                bits(I034_110_FILTER, position(1, 8, 1), "FILTER", "Filter Type",
                        BitsValue.of(0, "Invalid value"),
                        BitsValue.of(1, "Filter for Weather data"),
                        BitsValue.of(2, "Filter for Jamming Strobe"),
                        BitsValue.of(3, "Filter for PSR data"),
                        BitsValue.of(4, "Filter for SSR/Mode-S data"),
                        BitsValue.of(5, "Filter for SSR/Mode-S + PSR data"),
                        BitsValue.of(6, "Enhanced Surveillance data"),
                        BitsValue.of(7, "Filter for PSR+Enhanced Surveillance data"),
                        BitsValue.of(8, "Filter for PSR+Enhanced Surveillance SSR/Mode-S data not in Area of Prime Interest"),
                        BitsValue.of(9, "Filter for PSR+Enhanced Surveillance all SSR/Mode-S data")));

        final DataItem i034_110 = DataItem.from(f034_110, "Data Filter", false,
                "Data Filter, which allows suppression of individual data types");

        final DataFormat f034_120 = fixed(I034_120, 8,
                umeasure(I034_120_HGT, position(8, 64, 49), "HGT", 1.0, UnitOfMeasure.ALTITUDE_METER, "Height"),
                smeasure(I034_120_LAT, position(8, 48, 25), "LAT", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I034_120_LON, position(8, 24, 1), "LON", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataItem i034_120 = DataItem.from(f034_120, "3D-Position of Data Source", false,
                "3D-Position of Data Source in WGS84 Co-ordinates");

        final DataItem i034_FX1 = DataItem.fx(CAT034_ID, 2, 9);
        final DataItem i034_RE_ = DataItem.re(I034_RE);
        final DataItem i034_SP_ = DataItem.sp(I034_SP);
        final DataItem i034_FX2 = DataItem.fx(CAT034_ID, 2, 1);

        final UserApplicationProfile uap = new UserApplicationProfile(CAT034_ID,
                i034_010, //16
                i034_000, //15
                i034_030, //14
                i034_020, //13
                i034_041, //12
                i034_050, //11
                i034_060, //10
                i034_FX1, // 9
                i034_070, // 8
                i034_100, // 7
                i034_110, // 6
                i034_120, // 5
                i034_090, // 4
                i034_RE_, // 3
                i034_SP_, // 2
                i034_FX2);// 1

        return new ASTERIXCategory("Transmission of Monoradar Service Messages", uap);
    }

    private Cat034V1Dot29() {
    }
}

