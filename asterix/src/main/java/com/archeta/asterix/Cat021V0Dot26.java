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

public final class Cat021V0Dot26 {
    public static final long CAT021_ID = categoryId(21, 0, 26);
    public static final long I021_010;
    public static final long I021_010_SAC;
    public static final long I021_010_SIC;
    public static final long I021_020;
    public static final long I021_020_ECAT;
    public static final long I021_030;
    public static final long I021_030_TOD;
    public static final long I021_032;
    public static final long I021_032_TODA;
    public static final long I021_040;
    public static final long I021_040_DCR;
    public static final long I021_040_GBS;
    public static final long I021_040_SIM;
    public static final long I021_040_TST;
    public static final long I021_040_RAB;
    public static final long I021_040_SAA;
    public static final long I021_040_SPI;
    public static final long I021_040_SP9;
    public static final long I021_040_ATP;
    public static final long I021_040_ARC;
    public static final long I021_040_SP3;
    public static final long I021_040_SP2;
    public static final long I021_040_SP1;
    public static final long I021_070;
    public static final long I021_070_V;
    public static final long I021_070_G;
    public static final long I021_070_L;
    public static final long I021_070_SP13;
    public static final long I021_070_MODE3A;
    public static final long I021_080;
    public static final long I021_080_ADR;
    public static final long I021_090;
    public static final long I021_090_AC;
    public static final long I021_090_MN;
    public static final long I021_090_DC;
    public static final long I021_090_SP10;
    public static final long I021_090_SP9;
    public static final long I021_090_SP8;
    public static final long I021_090_SP7;
    public static final long I021_090_SP6;
    public static final long I021_090_SP5;
    public static final long I021_090_PA;
    public static final long I021_095;
    public static final long I021_095_VUN;
    public static final long I021_110;
    public static final long I021_110_TIS_NAV;
    public static final long I021_110_TIS_NVB;
    public static final long I021_110_TIS_SP6;
    public static final long I021_110_TIS_SP5;
    public static final long I021_110_TIS_SP4;
    public static final long I021_110_TIS_SP3;
    public static final long I021_110_TIS_SP2;
    public static final long I021_110_TIS_SP1;
    public static final long I021_110_TID_TCA;
    public static final long I021_110_TID_NC;
    public static final long I021_110_TID_TCP;
    public static final long I021_110_TID_ALT;
    public static final long I021_110_TID_LAT;
    public static final long I021_110_TID_LON;
    public static final long I021_110_TID_PT;
    public static final long I021_110_TID_TD;
    public static final long I021_110_TID_TRA;
    public static final long I021_110_TID_TOA;
    public static final long I021_110_TID_TOV;
    public static final long I021_110_TID_TTR;
    public static final long I021_130;
    public static final long I021_130_LAT;
    public static final long I021_130_LON;
    public static final long I021_131;
    public static final long I021_131_SAM;
    public static final long I021_140;
    public static final long I021_140_ALT;
    public static final long I021_145;
    public static final long I021_145_FL;
    public static final long I021_146;
    public static final long I021_146_SAS;
    public static final long I021_146_SRC;
    public static final long I021_146_ALT;
    public static final long I021_148;
    public static final long I021_148_MV;
    public static final long I021_148_AH;
    public static final long I021_148_AM;
    public static final long I021_148_ALT;
    public static final long I021_150;
    public static final long I021_150_IM;
    public static final long I021_150_IAS;
    public static final long I021_151;
    public static final long I021_151_TAS;
    public static final long I021_152;
    public static final long I021_152_MHG;
    public static final long I021_155;
    public static final long I021_155_BVR;
    public static final long I021_157;
    public static final long I021_157_GVR;
    public static final long I021_160;
    public static final long I021_160_GS;
    public static final long I021_160_TAN;
    public static final long I021_165;
    public static final long I021_165_TI;
    public static final long I021_165_SP6;
    public static final long I021_165_SP5;
    public static final long I021_165_SP4;
    public static final long I021_165_SP3;
    public static final long I021_165_SP2;
    public static final long I021_165_FX1;
    public static final long I021_165_ROT;
    public static final long I021_165_FX2;
    public static final long I021_170;
    public static final long I021_170_IDENT;
    public static final long I021_200;
    public static final long I021_200_STAT;
    public static final long I021_210;
    public static final long I021_210_SP8;
    public static final long I021_210_SP7;
    public static final long I021_210_SP6;
    public static final long I021_210_DTI;
    public static final long I021_210_MDS;
    public static final long I021_210_UAT;
    public static final long I021_210_VDL;
    public static final long I021_210_OTR;
    public static final long I021_220;
    public static final long I021_220_WS_WS;
    public static final long I021_220_WD_WD;
    public static final long I021_220_TMP_TMP;
    public static final long I021_220_TRB_TRB;
    public static final long I021_230;
    public static final long I021_230_RAN;
    public static final long I021_RE;
    public static final long I021_SP;

    private static final long I021_110_TIS;
    private static final long I021_110_TID;
    private static final long I021_110_SP6;
    private static final long I021_110_SP5;
    private static final long I021_110_SP4;
    private static final long I021_110_SP3;
    private static final long I021_110_SP2;
    private static final long I021_110_FX1;

    private static final long I021_165_FP;
    private static final long I021_165_X1;

    private static final long I021_220_WS;
    private static final long I021_220_WD;
    private static final long I021_220_TMP;
    private static final long I021_220_TRB;
    private static final long I021_220_SP4;
    private static final long I021_220_SP3;
    private static final long I021_220_SP2;
    private static final long I021_220_FX1;

    static {
        I021_010 = dataItemId(CAT021_ID, 10, DATA_FORMAT_FIXED);
        I021_010_SAC = bitsFieldId(I021_010, 16, BITS_FIELD_ENCODING_UNSIGNED);
        I021_010_SIC = bitsFieldId(I021_010, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_020 = dataItemId(CAT021_ID, 20, DATA_FORMAT_FIXED);
        I021_020_ECAT = bitsFieldId(I021_020, 8, BITS_FIELD_ENCODING_VALUES);
        I021_030 = dataItemId(CAT021_ID, 30, DATA_FORMAT_FIXED);
        I021_030_TOD = bitsFieldId(I021_030, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_032 = dataItemId(CAT021_ID, 32, DATA_FORMAT_FIXED);
        I021_032_TODA = bitsFieldId(I021_032, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_040 = dataItemId(CAT021_ID, 40, DATA_FORMAT_FIXED);
        I021_040_DCR = bitsFieldId(I021_040, 16, BITS_FIELD_ENCODING_VALUE);
        I021_040_GBS = bitsFieldId(I021_040, 15, BITS_FIELD_ENCODING_VALUE);
        I021_040_SIM = bitsFieldId(I021_040, 14, BITS_FIELD_ENCODING_VALUE);
        I021_040_TST = bitsFieldId(I021_040, 13, BITS_FIELD_ENCODING_VALUE);
        I021_040_RAB = bitsFieldId(I021_040, 12, BITS_FIELD_ENCODING_VALUE);
        I021_040_SAA = bitsFieldId(I021_040, 11, BITS_FIELD_ENCODING_VALUE);
        I021_040_SP9 = bitsFieldId(I021_040, 9, BITS_FIELD_ENCODING_SPARE);
        I021_040_SPI = bitsFieldId(I021_040, 10, BITS_FIELD_ENCODING_VALUE);
        I021_040_ATP = bitsFieldId(I021_040, 8, BITS_FIELD_ENCODING_VALUES);
        I021_040_ARC = bitsFieldId(I021_040, 5, BITS_FIELD_ENCODING_VALUES);
        I021_040_SP3 = bitsFieldId(I021_040, 3, BITS_FIELD_ENCODING_SPARE);
        I021_040_SP2 = bitsFieldId(I021_040, 2, BITS_FIELD_ENCODING_SPARE);
        I021_040_SP1 = bitsFieldId(I021_040, 1, BITS_FIELD_ENCODING_SPARE);
        I021_070 = dataItemId(CAT021_ID, 70, DATA_FORMAT_FIXED);
        I021_070_V = bitsFieldId(I021_070, 16, BITS_FIELD_ENCODING_VALUE);
        I021_070_G = bitsFieldId(I021_070, 15, BITS_FIELD_ENCODING_VALUE);
        I021_070_L = bitsFieldId(I021_070, 14, BITS_FIELD_ENCODING_VALUE);
        I021_070_SP13 = bitsFieldId(I021_070, 13, BITS_FIELD_ENCODING_SPARE);
        I021_070_MODE3A = bitsFieldId(I021_070, 12, BITS_FIELD_ENCODING_OCTAL);
        I021_080 = dataItemId(CAT021_ID, 80, DATA_FORMAT_FIXED);
        I021_080_ADR = bitsFieldId(I021_080, 24, BITS_FIELD_ENCODING_HEX);
        I021_090 = dataItemId(CAT021_ID, 90, DATA_FORMAT_FIXED);
        I021_090_AC = bitsFieldId(I021_090, 16, BITS_FIELD_ENCODING_VALUES);
        I021_090_MN = bitsFieldId(I021_090, 14, BITS_FIELD_ENCODING_VALUES);
        I021_090_DC = bitsFieldId(I021_090, 12, BITS_FIELD_ENCODING_VALUES);
        I021_090_SP10 = bitsFieldId(I021_090, 10, BITS_FIELD_ENCODING_SPARE);
        I021_090_SP9 = bitsFieldId(I021_090, 9, BITS_FIELD_ENCODING_SPARE);
        I021_090_SP8 = bitsFieldId(I021_090, 8, BITS_FIELD_ENCODING_SPARE);
        I021_090_SP7 = bitsFieldId(I021_090, 7, BITS_FIELD_ENCODING_SPARE);
        I021_090_SP6 = bitsFieldId(I021_090, 6, BITS_FIELD_ENCODING_SPARE);
        I021_090_SP5 = bitsFieldId(I021_090, 5, BITS_FIELD_ENCODING_SPARE);
        I021_090_PA = bitsFieldId(I021_090, 4, BITS_FIELD_ENCODING_UNSIGNED);
        I021_095 = dataItemId(CAT021_ID, 95, DATA_FORMAT_FIXED);
        I021_095_VUN = bitsFieldId(I021_095, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_110 = dataItemId(CAT021_ID, 110, DATA_FORMAT_COMPOUND);
        I021_110_TIS = subAId(I021_110, 8, DATA_FORMAT_FIXED);
        I021_110_TID = subAId(I021_110, 7, DATA_FORMAT_REPETITIVE);
        I021_110_SP6 = subAId(I021_110, 6, DATA_FORMAT_EMPTY);
        I021_110_SP5 = subAId(I021_110, 5, DATA_FORMAT_EMPTY);
        I021_110_SP4 = subAId(I021_110, 4, DATA_FORMAT_EMPTY);
        I021_110_SP3 = subAId(I021_110, 3, DATA_FORMAT_EMPTY);
        I021_110_SP2 = subAId(I021_110, 2, DATA_FORMAT_EMPTY);
        I021_110_FX1 = subAId(I021_110, 1, DATA_FORMAT_EMPTY);
        I021_110_TIS_NAV = bitsFieldId(I021_110_TIS, 8, BITS_FIELD_ENCODING_VALUE);
        I021_110_TIS_NVB = bitsFieldId(I021_110_TIS, 7, BITS_FIELD_ENCODING_VALUE);
        I021_110_TIS_SP6 = bitsFieldId(I021_110_TIS, 6, BITS_FIELD_ENCODING_SPARE);
        I021_110_TIS_SP5 = bitsFieldId(I021_110_TIS, 5, BITS_FIELD_ENCODING_SPARE);
        I021_110_TIS_SP4 = bitsFieldId(I021_110_TIS, 4, BITS_FIELD_ENCODING_SPARE);
        I021_110_TIS_SP3 = bitsFieldId(I021_110_TIS, 3, BITS_FIELD_ENCODING_SPARE);
        I021_110_TIS_SP2 = bitsFieldId(I021_110_TIS, 2, BITS_FIELD_ENCODING_SPARE);
        I021_110_TIS_SP1 = bitsFieldId(I021_110_TIS, 1, BITS_FIELD_ENCODING_SPARE);
        I021_110_TID_TCA = bitsFieldId(I021_110_TID, 120, BITS_FIELD_ENCODING_VALUE);
        I021_110_TID_NC = bitsFieldId(I021_110_TID, 119, BITS_FIELD_ENCODING_VALUE);
        I021_110_TID_TCP = bitsFieldId(I021_110_TID, 118, BITS_FIELD_ENCODING_UNSIGNED);
        I021_110_TID_ALT = bitsFieldId(I021_110_TID, 112, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_110_TID_LAT = bitsFieldId(I021_110_TID, 96, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_110_TID_LON = bitsFieldId(I021_110_TID, 72, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_110_TID_PT = bitsFieldId(I021_110_TID, 48, BITS_FIELD_ENCODING_VALUES);
        I021_110_TID_TD = bitsFieldId(I021_110_TID, 44, BITS_FIELD_ENCODING_VALUES);
        I021_110_TID_TRA = bitsFieldId(I021_110_TID, 42, BITS_FIELD_ENCODING_VALUE);
        I021_110_TID_TOA = bitsFieldId(I021_110_TID, 41, BITS_FIELD_ENCODING_VALUE);
        I021_110_TID_TOV = bitsFieldId(I021_110_TID, 40, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_110_TID_TTR = bitsFieldId(I021_110_TID, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_130 = dataItemId(CAT021_ID, 130, DATA_FORMAT_FIXED);
        I021_130_LAT = bitsFieldId(I021_130, 64, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_130_LON = bitsFieldId(I021_130, 32, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_131 = dataItemId(CAT021_ID, 131, DATA_FORMAT_FIXED);
        I021_131_SAM = bitsFieldId(I021_131, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_140 = dataItemId(CAT021_ID, 140, DATA_FORMAT_FIXED);
        I021_140_ALT = bitsFieldId(I021_140, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_145 = dataItemId(CAT021_ID, 145, DATA_FORMAT_FIXED);
        I021_145_FL = bitsFieldId(I021_145, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_146 = dataItemId(CAT021_ID, 146, DATA_FORMAT_FIXED);
        I021_146_SAS = bitsFieldId(I021_146, 16, BITS_FIELD_ENCODING_VALUE);
        I021_146_SRC = bitsFieldId(I021_146, 15, BITS_FIELD_ENCODING_VALUES);
        I021_146_ALT = bitsFieldId(I021_146, 13, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_148 = dataItemId(CAT021_ID, 148, DATA_FORMAT_FIXED);
        I021_148_MV = bitsFieldId(I021_148, 16, BITS_FIELD_ENCODING_VALUE);
        I021_148_AH = bitsFieldId(I021_148, 15, BITS_FIELD_ENCODING_VALUE);
        I021_148_AM = bitsFieldId(I021_148, 14, BITS_FIELD_ENCODING_VALUE);
        I021_148_ALT = bitsFieldId(I021_148, 13, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_150 = dataItemId(CAT021_ID, 150, DATA_FORMAT_FIXED);
        I021_150_IM = bitsFieldId(I021_150, 16, BITS_FIELD_ENCODING_VALUE);
        I021_150_IAS = bitsFieldId(I021_150, 15, BITS_FIELD_ENCODING_UNSIGNED);
        I021_151 = dataItemId(CAT021_ID, 151, DATA_FORMAT_FIXED);
        I021_151_TAS = bitsFieldId(I021_151, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_152 = dataItemId(CAT021_ID, 152, DATA_FORMAT_FIXED);
        I021_152_MHG = bitsFieldId(I021_152, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_155 = dataItemId(CAT021_ID, 155, DATA_FORMAT_FIXED);
        I021_155_BVR = bitsFieldId(I021_155, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_157 = dataItemId(CAT021_ID, 157, DATA_FORMAT_FIXED);
        I021_157_GVR = bitsFieldId(I021_157, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_160 = dataItemId(CAT021_ID, 160, DATA_FORMAT_FIXED);
        I021_160_GS = bitsFieldId(I021_160, 32, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_160_TAN = bitsFieldId(I021_160, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_165 = dataItemId(CAT021_ID, 165, DATA_FORMAT_EXTENDED);
        I021_165_FP = subAId(I021_165, 0, DATA_FORMAT_FIXED);
        I021_165_X1 = subAId(I021_165, 1, DATA_FORMAT_FIXED);
        I021_165_TI = bitsFieldId(I021_165_FP, 8, BITS_FIELD_ENCODING_VALUES);
        I021_165_SP6 = bitsFieldId(I021_165_FP, 6, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP5 = bitsFieldId(I021_165_FP, 5, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP4 = bitsFieldId(I021_165_FP, 4, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP3 = bitsFieldId(I021_165_FP, 3, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP2 = bitsFieldId(I021_165_FP, 2, BITS_FIELD_ENCODING_SPARE);
        I021_165_FX1 = bitsFieldId(I021_165_FP, 1, BITS_FIELD_ENCODING_FX);
        I021_165_ROT = bitsFieldId(I021_165_X1, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_165_FX2 = bitsFieldId(I021_165_X1, 1, BITS_FIELD_ENCODING_FX);
        I021_170 = dataItemId(CAT021_ID, 170, DATA_FORMAT_FIXED);
        I021_170_IDENT = bitsFieldId(I021_170, 48, BITS_FIELD_ENCODING_IA5);
        I021_200 = dataItemId(CAT021_ID, 200, DATA_FORMAT_FIXED);
        I021_200_STAT = bitsFieldId(I021_200, 8, BITS_FIELD_ENCODING_VALUES);
        I021_210 = dataItemId(CAT021_ID, 210, DATA_FORMAT_FIXED);
        I021_210_SP8 = bitsFieldId(I021_210, 8, BITS_FIELD_ENCODING_SPARE);
        I021_210_SP7 = bitsFieldId(I021_210, 7, BITS_FIELD_ENCODING_SPARE);
        I021_210_SP6 = bitsFieldId(I021_210, 6, BITS_FIELD_ENCODING_SPARE);
        I021_210_DTI = bitsFieldId(I021_210, 5, BITS_FIELD_ENCODING_VALUE);
        I021_210_MDS = bitsFieldId(I021_210, 4, BITS_FIELD_ENCODING_VALUE);
        I021_210_UAT = bitsFieldId(I021_210, 3, BITS_FIELD_ENCODING_VALUE);
        I021_210_VDL = bitsFieldId(I021_210, 2, BITS_FIELD_ENCODING_VALUE);
        I021_210_OTR = bitsFieldId(I021_210, 1, BITS_FIELD_ENCODING_VALUE);
        I021_220 = dataItemId(CAT021_ID, 220, DATA_FORMAT_COMPOUND);
        I021_220_WS = subAId(I021_220, 8, DATA_FORMAT_FIXED);
        I021_220_WD = subAId(I021_220, 7, DATA_FORMAT_FIXED);
        I021_220_TMP = subAId(I021_220, 6, DATA_FORMAT_FIXED);
        I021_220_TRB = subAId(I021_220, 5, DATA_FORMAT_FIXED);
        I021_220_SP4 = subAId(I021_220, 4, DATA_FORMAT_EMPTY);
        I021_220_SP3 = subAId(I021_220, 3, DATA_FORMAT_EMPTY);
        I021_220_SP2 = subAId(I021_220, 2, DATA_FORMAT_EMPTY);
        I021_220_FX1 = subAId(I021_220, 1, DATA_FORMAT_EMPTY);
        I021_220_WS_WS = bitsFieldId(I021_220_WS, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_220_WD_WD = bitsFieldId(I021_220_WD, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_220_TMP_TMP = bitsFieldId(I021_220_TMP, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_220_TRB_TRB = bitsFieldId(I021_220_TRB, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_230 = dataItemId(CAT021_ID, 230, DATA_FORMAT_FIXED);
        I021_230_RAN = bitsFieldId(I021_230, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_RE = dataItemId(CAT021_ID, DATA_ITEM_RE, DATA_FORMAT_EXPLICIT);
        I021_SP = dataItemId(CAT021_ID, DATA_ITEM_SP, DATA_FORMAT_EXPLICIT);
    }

    static ASTERIXCategory createCategory() {
        final DataFormat f021_010 = fixed(I021_010, 2,
                unsigned(I021_010_SAC, position(2, 16, 9), "SAC", "System Area Code"),
                unsigned(I021_010_SIC, position(2, 8, 1), "SIC", "System Identification Code"));

        final DataItem i021_010 = DataItem.from(f021_010, "Data Source Identification", true,
                "Identification of the ADS-B station providing information");

        final DataFormat f021_020 = fixed(I021_020, 1,
                bits(I021_020_ECAT, position(1, 8, 1), "ECAT", "Emitter Category",
                        BitsValue.of(0, "Not used"),
                        BitsValue.of(1, "Light aircraft <= 7000 kg"),
                        BitsValue.of(2, "Reserved"),
                        BitsValue.of(3, "7000 kg < medium aircraft < 136000 kg"),
                        BitsValue.of(4, "Reserved"),
                        BitsValue.of(5, "136000 kg <= heavy aircraft"),
                        BitsValue.of(6, "Highly manoeuvrable (5g acceleration capability) and high speed (>400 knots cruise)"),
                        BitsValue.of(7, "Reserved"),
                        BitsValue.of(8, "Reserved"),
                        BitsValue.of(9, "Reserved"),
                        BitsValue.of(10, "Rotocraft"),
                        BitsValue.of(11, "Glider/sailplane"),
                        BitsValue.of(12, "Lighter-than-air"),
                        BitsValue.of(13, "Unmanned aerial vehicle"),
                        BitsValue.of(14, "Space/transatmospheric vehicle"),
                        BitsValue.of(15, "Ultralight/handglider/paraglider"),
                        BitsValue.of(16, "Parachutist/skydiver"),
                        BitsValue.of(17, "Reserved"),
                        BitsValue.of(18, "Reserved"),
                        BitsValue.of(19, "Reserved"),
                        BitsValue.of(20, "Surface emergency vehicle"),
                        BitsValue.of(21, "Surface service vehicle"),
                        BitsValue.of(22, "Fixed ground or tethered obstruction"),
                        BitsValue.of(23, "Reserved"),
                        BitsValue.of(24, "Reserved")));

        final DataItem i021_020 = DataItem.from(f021_020, "Emitter Category", false,
                "Characteristics of the originating ADS-B unit");

        final DataFormat f021_030 = fixed(I021_030, 3,
                umeasure(I021_030_TOD, position(3, 24, 1), "TOD", 1.0 / 128.0, UnitOfMeasure.TIME_SECONDS, "Time of Day"));

        final DataItem i021_030 = DataItem.from(f021_030, "Time of Day", true,
                "Time of applicability (measurement) of the reported position, in the form of elapsed time since last midnight, expressed as UTC");


        final DataFormat f021_032 = fixed(I021_032, 1,
                umeasure(I021_032_TODA, position(1, 8, 1), "TODA", 1.0 / 256, UnitOfMeasure.TIME_SECONDS, "Time of Day Accuracy"));

        final DataItem i021_032 = DataItem.from(f021_032, "Time of Day Accuracy", false,
                "The maximum difference between the actual time of applicability of the reported position and the time reported in the Time of Day item (I021/030)");

        final DataFormat f021_040 = fixed(I021_040, 2,
                bit(I021_040_DCR, position(2, 16, 16), "DCR", "Differential Correction",
                        BitsValue.of(0, "No differential correction (ADS-B)"),
                        BitsValue.of(1, "Differential correction (ADS-B)")),
                bit(I021_040_GBS, position(2, 15, 15), "GBS", "Ground Bit Setting",
                        BitsValue.of(0, "Ground Bit not set"),
                        BitsValue.of(1, "Ground Bit set")),
                bit(I021_040_SIM, position(2, 14, 14), "SIM", "Simulated Target",
                        BitsValue.of(0, "Actual target report"),
                        BitsValue.of(1, "Simulated target report")),
                bit(I021_040_TST, position(2, 13, 13), "TST", "Test Target",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Test Target")),
                bit(I021_040_RAB, position(2, 12, 12), "RAB", "Target Report Source",
                        BitsValue.of(0, "Report from target transponder"),
                        BitsValue.of(1, "Report from field monitor (fixed transponder)")),
                bit(I021_040_SAA, position(2, 11, 11), "SAA", "Equipment Selected Altitude Capability",
                        BitsValue.of(0, "Equipment not capable to provide Selected Altitude"),
                        BitsValue.of(1, "Equipment capable to provide Selected Altitude")),
                bit(I021_040_SPI, position(2, 10, 10), "SPI", "Special Position Identification",
                        BitsValue.of(0, "Absence of SPI"),
                        BitsValue.of(1, "Special Position Identification")),
                spare(I021_040_SP9, position(2, 9, 9)),
                bits(I021_040_ATP, position(2, 8, 6), "ATP", "Address Type",
                        BitsValue.of(0, "Non-unique address"),
                        BitsValue.of(1, "24-Bit ICAO address"),
                        BitsValue.of(2, "Surface vehicle address"),
                        BitsValue.of(3, "Anonymous address"),
                        BitsValue.of(4, "Reserved for future use"),
                        BitsValue.of(5, "Reserved for future use"),
                        BitsValue.of(6, "Reserved for future use"),
                        BitsValue.of(7, "Reserved for future use")),
                bits(I021_040_ARC, position(2, 5, 4), "ARC", "Altitude Reporting Capability",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "25 ft"),
                        BitsValue.of(2, "100 ft"),
                        BitsValue.of(3, "Invalid")),
                spare(I021_040_SP3, position(2, 3, 3)),
                spare(I021_040_SP2, position(2, 2, 2)),
                spare(I021_040_SP1, position(2, 1, 1)));

        final DataItem i021_040 = DataItem.from(f021_040, "Target Report Descriptor", true,
                "Type and characteristics of the data as transmitted by a system");

        final DataFormat f021_070 = fixed(I021_070, 2,
                bit(I021_070_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I021_070_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I021_070_L, position(2, 14, 14), "L", "Reply",
                        BitsValue.of(0, "Mode-3/A code as derived from the reply of the transponder"),
                        BitsValue.of(1, "Mode-3/A code not extracted during the last scan")),
                spare(I021_070_SP13, position(2, 13, 13)),
                octal(I021_070_MODE3A, position(2, 12, 1), "MODE3A", "Mode-3/A Code"));

        final DataItem i021_070 = DataItem.from(f021_070, "Mode-3/A Code in Octal Representation", false,
                "Mode-3/A code converted into octal representation");

        final DataFormat f021_080 = fixed(I021_080, 3,
                hex(I021_080_ADR, position(3, 24, 1), "ADR", "Target Address"));

        final DataItem i021_080 = DataItem.from(f021_080, "Target Address", true,
                "Target address (emitter identifier) assigned uniquely to each target");

        final DataFormat f021_090 = fixed(I021_090, 2,
                bits(I021_090_AC, position(2, 16, 15), "AC", "ACAS operational status",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "ACAS not operational"),
                        BitsValue.of(2, "ACAS operational"),
                        BitsValue.of(3, "Invalid")),
                bits(I021_090_MN, position(2, 14, 13), "MN", "Multiple navigational aids operating status",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Multiple navigational aids not operating"),
                        BitsValue.of(2, "Multiple navigational aids operating"),
                        BitsValue.of(3, "Invalid")),
                bits(I021_090_DC, position(2, 12, 11), "DC", "Differential Correction status",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Differential Correction"),
                        BitsValue.of(2, "No Differential Correction"),
                        BitsValue.of(3, "Invalid")),
                spare(I021_090_SP10, position(2, 10, 10)),
                spare(I021_090_SP9, position(2, 9, 9)),
                spare(I021_090_SP8, position(2, 8, 8)),
                spare(I021_090_SP7, position(2, 7, 7)),
                spare(I021_090_SP6, position(2, 6, 6)),
                spare(I021_090_SP5, position(2, 5, 5)),
                unsigned(I021_090_PA, position(2, 4, 1), "PA", "Position Accuracy"));

        final DataItem i021_090 = DataItem.from(f021_090, "Figure of Merit", false,
                "ADS figure of merit (FOM) provided by the aircraft avionics");

        final DataFormat f021_095 = fixed(I021_095, 1,
                unsigned(I021_095_VUN, position(1, 8, 1), "VUN", "Velocity Uncertainty Category"));

        final DataItem i021_095 = DataItem.from(f021_095, "Velocity Accuracy", false,
                "Velocity uncertainty category of the least accurate velocity component");

        final DataFormat f021_110_TIS = fixed(I021_110_TIS, 1,
                bit(I021_110_TIS_NAV, position(1, 8, 8), "NAV", "Trajectory Intent Data availability",
                        BitsValue.of(0, "Trajectory Intent Data is available for this aircraft"),
                        BitsValue.of(1, "Trajectory Intent Data is not available for this aircraft")),
                bit(I021_110_TIS_NVB, position(1, 7, 7), "NVB", "Trajectory Intent Data validity",
                        BitsValue.of(0, "Trajectory Intent Data is valid"),
                        BitsValue.of(1, "Trajectory Intent Data is not valid")),
                spare(I021_110_TIS_SP6, position(1, 6, 6)),
                spare(I021_110_TIS_SP5, position(1, 5, 5)),
                spare(I021_110_TIS_SP4, position(1, 4, 4)),
                spare(I021_110_TIS_SP3, position(1, 3, 3)),
                spare(I021_110_TIS_SP2, position(1, 2, 2)),
                spare(I021_110_TIS_SP1, position(1, 1, 1)));

        final DataFormat f021_110_TID = repetitive(I021_110_TID, 15, "Trajectory Intent",
                bit(I021_110_TID_TCA, position(15, 120, 120), "TCA", "TCP number availability",
                        BitsValue.of(0, "TCP number available"),
                        BitsValue.of(1, "TCP number not available")),
                bit(I021_110_TID_NC, position(15, 119, 119), "NC", "TCP compliance",
                        BitsValue.of(0, "TCP compliance"),
                        BitsValue.of(1, "TCP non-compliance")),
                unsigned(I021_110_TID_TCP, position(15, 118, 113), "TCP", "Trajectory Change Point Number"),
                smeasure(I021_110_TID_ALT, position(15, 112, 97), "ALT", 10.0, UnitOfMeasure.ALTITUDE_FT, -1500, 150000, "Altitude"),
                smeasure(I021_110_TID_LAT, position(15, 96, 73), "LAT", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I021_110_TID_LON, position(15, 72, 49), "LON", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, "Longitude"),
                bits(I021_110_TID_PT, position(15, 48, 45), "TID", "Point Type",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Fly by waypoint (LT)"),
                        BitsValue.of(2, "Fly over waypoint (LT)"),
                        BitsValue.of(3, "Hold pattern (LT)"),
                        BitsValue.of(4, "Procedure hold (LT)"),
                        BitsValue.of(5, "Procedure turn (LT)"),
                        BitsValue.of(6, "RF leg (LT)"),
                        BitsValue.of(7, "Top of climb (VT)"),
                        BitsValue.of(8, "Top of descent (VT)"),
                        BitsValue.of(9, "Start of level (VT)"),
                        BitsValue.of(10, "Cross-over altitude (VT)"),
                        BitsValue.of(11, "Transition altitude (VT)"),
                        BitsValue.of(12, "Not assigned"),
                        BitsValue.of(13, "Not assigned"),
                        BitsValue.of(14, "Not assigned"),
                        BitsValue.of(15, "Not assigned")),
                bits(I021_110_TID_TD, position(15, 44, 43), "TD", "Turn Direction",
                        BitsValue.of(0, "N/A"),
                        BitsValue.of(1, "Turn right"),
                        BitsValue.of(2, "Turn left"),
                        BitsValue.of(3, "No turn")),
                bit(I021_110_TID_TRA, position(15, 42, 42), "TRA", "Turn Radius Availability",
                        BitsValue.of(0, "TTR not available"),
                        BitsValue.of(1, "TTR available")),
                bit(I021_110_TID_TOA, position(15, 41, 41), "TOA", "Time over Point Availability",
                        BitsValue.of(0, "TOV available"),
                        BitsValue.of(1, "TOV not available")),
                umeasure(I021_110_TID_TOV, position(15, 40, 17), "TOV", 1.0, UnitOfMeasure.TIME_SECONDS, "Time over Point"),
                umeasure(I021_110_TID_TTR, position(15, 16, 1), "TTR", 0.01, UnitOfMeasure.DISTANCE_NM, 655.35, "TCP Turn Radius"));

        final DataFormat f021_110 = compound(I021_110,
                Subfield.of(f021_110_TIS, 1, "TIS", "Trajectory Intent Status"),
                Subfield.of(f021_110_TID, 2, "TID", "Trajectory Intent Data validity"),
                Subfield.sp(I021_110_SP6, 3),
                Subfield.sp(I021_110_SP5, 4),
                Subfield.sp(I021_110_SP4, 5),
                Subfield.sp(I021_110_SP3, 6),
                Subfield.sp(I021_110_SP2, 7),
                Subfield.fx(I021_110_FX1));

        final DataItem i021_110 = DataItem.from(f021_110, "Trajectory Intent", false,
                "Reports indicating the 4D intended trajectory of the aircraft");

        final DataFormat f021_130 = fixed(I021_130, 8,
                smeasure(I021_130_LAT, position(8, 64, 33), "LAT", 180.0 / (1 << 30), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I021_130_LON, position(8, 32, 1), "LON", 180.0 / (1 << 30), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataItem i021_130 = DataItem.from(f021_130, "Position in WGS84 Co-ordinates", false,
                "Position in WGS84 Co-ordinates");

        final DataFormat f021_131 = fixed(I021_131, 1,
                unsigned(I021_131_SAM, position(1, 8, 1), "SAM", "Signal Amplitude"));

        final DataItem i021_131 = DataItem.from(f021_131, "Signal Amplitude", false,
                "Relative strength of received signal");

        final DataFormat f021_140 = fixed(I021_140, 2,
                smeasure(I021_140_ALT, position(2, 16, 1), "ALT", 6.25, UnitOfMeasure.ALTITUDE_FT, -1500.0, 150000.0, "Geometric Altitude"));

        final DataItem i021_140 = DataItem.from(f021_140, "Geometric Altitude", false,
                "Vertical distance between the target and the projection of its position on the earth’s ellipsoid, as defined by WGS84, in two’s complement form");

        final DataFormat f021_145 = fixed(I021_145, 2,
                smeasure(I021_145_FL, position(2, 16, 1), "FL", 0.25, UnitOfMeasure.ALTITUDE_FL, -15.0, 1500.0, "Flight Level"));

        final DataItem i021_145 = DataItem.from(f021_145, "Flight Level", false,
                "Flight Level from barometric measurements, not QNH corrected, in two's complement form");

        final DataFormat f021_146 = fixed(I021_146, 2,
                bit(I021_146_SAS, position(2, 16, 16), "SAS", "Source Information provided",
                        BitsValue.of(0, "No Source Information provided"),
                        BitsValue.of(1, "Source Information provided")),
                bits(I021_146_SRC, position(2, 15, 14), "SRC", "Source Information",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Aircraft Altitude"),
                        BitsValue.of(2, "FCU/MSP Selected Altitude"),
                        BitsValue.of(3, "FMS Selected Altitude")),
                smeasure(I021_146_ALT, position(2, 13, 1), "ALT", 25.0, UnitOfMeasure.ALTITUDE_FT, -1300.0, 100000.0, "Altitude"));

        final DataItem i021_146 = DataItem.from(f021_146, "Intermediate State Selected Altitude", false,
                "The short-term vertical intent as described by either the FMS selected altitude, the Altitude Control Panel Selected Altitude, or the current aircraft altitude according to the aircraft's mode of flight");

        final DataFormat f021_148 = fixed(I021_148, 2,
                bit(I021_148_MV, position(2, 16, 16), "MV", "Manage Vertical Mode",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active")),
                bit(I021_148_AH, position(2, 15, 15), "AH", "Altitude Hold Mode",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active")),
                bit(I021_148_AM, position(2, 14, 14), "AM", "Approach Mode",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active")),
                smeasure(I021_148_ALT, position(2, 13, 1), "ALT", 25.0, UnitOfMeasure.ALTITUDE_FT, -1300.0, 100000.0, "Altitude"));

        final DataItem i021_148 = DataItem.from(f021_148, "Final State Selected Altitude", false,
                "The vertical intent value that corresponds with the ATC cleared altitude, as derived from the Altitude Control Panel");

        final DataFormat f021_150 = fixed(I021_150, 2,
                bit(I021_150_IM, position(2, 16, 16), "IM", "IAS or Mach",
                        BitsValue.of(0, "Air Speed = IAS, LSB (bit-1) = 2^14 NM/s"),
                        BitsValue.of(1, "Air Speed = Mach, LSB (bit-1) = 0.001 Mach")),
                unsigned(I021_150_IAS, position(2, 15, 1), "IAS", "Air Speed (IAS or Mach)"));

        final DataItem i021_150 = DataItem.from(f021_150, "Air Speed", false,
                "Calculated Air Speed (Element of Air Vector)");

        final DataFormat f021_151 = fixed(I021_151, 2,
                umeasure(I021_151_TAS, position(2, 16, 1), "TAS", 1.0, UnitOfMeasure.SPEED_KNOT, "True Air Speed"));

        final DataItem i021_151 = DataItem.from(f021_151, "True Airspeed", false, "True Airspeed");

        final DataFormat f021_152 = fixed(I021_152, 2,
                umeasure(I021_152_MHG, position(2, 16, 1), "MHG", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Magnetic Heading"));

        final DataItem i021_152 = DataItem.from(f021_152, "Magnetic Heading", false,
                "Magnetic Heading (Element of Air Vector)");

        final DataFormat f021_155 = fixed(I021_155, 2,
                smeasure(I021_155_BVR, position(2, 16, 1), "BVR", 6.25, UnitOfMeasure.SPEED_FT_MIN, "Barometric Vertical Rate"));

        final DataItem i021_155 = DataItem.from(f021_155, "Barometric Vertical Rate", false,
                "Barometric Vertical Rate, in two's complement form");

        final DataFormat f021_157 = fixed(I021_157, 2,
                smeasure(I021_157_GVR, position(2, 16, 1), "GVR", 6.25, UnitOfMeasure.SPEED_FT_MIN, "Geometric Vertical Rate"));

        final DataItem i021_157 = DataItem.from(f021_157, "Geometric Vertical Rate", false,
                "Geometric Vertical Rate, in two's complement form, with reference to WGS84");

        final DataFormat f021_160 = fixed(I021_160, 4,
                smeasure(I021_160_GS, position(4, 32, 17), "GS", 1.0 / (1 << 14), UnitOfMeasure.SPEED_NM_S, "Ground Speed"),
                umeasure(I021_160_TAN, position(4, 16, 1), "TAN", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Track Angle"));

        final DataItem i021_160 = DataItem.from(f021_160, "Ground Vector", false,
                "Ground Speed and Track Angle elements of Ground Vector");

        final DataFormat f021_165 = extended(I021_165,
                Part.of(I021_165_FP, 1,
                        bits(I021_165_TI, position(1, 8, 7), "TI", "Turn Indicator",
                                BitsValue.of(0, "Not available"),
                                BitsValue.of(1, "Left"),
                                BitsValue.of(2, "Right"),
                                BitsValue.of(3, "Straight")),
                        spare(I021_165_SP6, position(1, 6, 6)),
                        spare(I021_165_SP5, position(1, 5, 5)),
                        spare(I021_165_SP4, position(1, 4, 4)),
                        spare(I021_165_SP3, position(1, 3, 3)),
                        spare(I021_165_SP2, position(1, 2, 2)),
                        fx(I021_165_FX1, position(1, 1, 1))),
                Part.of(I021_165_X1, 1,
                        smeasure(I021_165_ROT, position(1, 8, 2), "ROT", 0.25, UnitOfMeasure.ANGLE_DEGREE, -15.0, 15.0, "Rate of Turn"),
                        fx(I021_165_FX2, position(1, 1, 1))));

        final DataItem i021_165 = DataItem.from(f021_165, "Rate of Turn", false,
                "Rate of Turn, in two's complement form");

        final DataFormat f021_170 = fixed(I021_170, 6,
                ia5(I021_170_IDENT, position(6, 48, 1), "IDENT", "Target Identification"));

        final DataItem i021_170 = DataItem.from(f021_170, "Target Identification", false,
                "Target (aircraft or vehicle) identification in 8 characters, as reported by the target");

        final DataFormat f021_200 = fixed(I021_200, 1,
                bits(I021_200_STAT, position(1, 8, 1), "STAT", "Status of the target",
                        BitsValue.of(0, "No emergency/not reported"),
                        BitsValue.of(1, "General emergency"),
                        BitsValue.of(2, "Lifeguard/medical"),
                        BitsValue.of(3, "Minimum fuel"),
                        BitsValue.of(4, "No communications"),
                        BitsValue.of(5, "Unlawful interference")));

        final DataItem i021_200 = DataItem.from(f021_200, "Target Status", false, "Status of the target");

        final DataFormat f021_210 = fixed(I021_210, 1,
                spare(I021_210_SP8, position(1, 8, 8)),
                spare(I021_210_SP7, position(1, 7, 7)),
                spare(I021_210_SP6, position(1, 6, 6)),
                bit(I021_210_DTI, position(1, 5, 5), "DTI", "Cockpit Display of Traffic Information",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Aircraft equipped with CDTI")),
                bit(I021_210_MDS, position(1, 4, 4), "MDS", "Mode-S Extended Squitter",
                        BitsValue.of(0, "Not used"),
                        BitsValue.of(1, "Used")),
                bit(I021_210_UAT, position(1, 3, 3), "UAT", "UAT",
                        BitsValue.of(0, "Not used"),
                        BitsValue.of(1, "Used")),
                bit(I021_210_VDL, position(1, 2, 2), "VDL", "VDL Mode 4",
                        BitsValue.of(0, "Not used"),
                        BitsValue.of(1, "Used")),
                bit(I021_210_OTR, position(1, 1, 1), "OTR", "Other Technology",
                        BitsValue.of(0, "Not used"),
                        BitsValue.of(1, "Used")));

        final DataItem i021_210 = DataItem.from(f021_210, "Link Technology Indicator", true,
                "Indication of which ADS link technology has been used to send the target report");

        final DataFormat f021_220_WS = fixed(I021_220_WS, 2,
                umeasure(I021_220_WS_WS, position(2, 16, 1), "WS", 1.0, UnitOfMeasure.SPEED_KNOT, 300.0, "Wind Speed"));

        final DataFormat f021_220_WD = fixed(I021_220_WD, 2,
                umeasure(I021_220_WD_WD, position(2, 16, 1), "WD", 1.0, UnitOfMeasure.ANGLE_DEGREE, 360.0, "Wind Direction"));

        final DataFormat f021_220_TMP = fixed(I021_220_TMP, 2,
                smeasure(I021_220_TMP_TMP, position(2, 16, 1), "TMP", 0.25, UnitOfMeasure.TEMPERATURE_CELSIUS, -100.0, 100.0, "Temperature"));

        final DataFormat f021_220_TRB = fixed(I021_220_TRB, 1,
                unsigned(I021_220_TRB_TRB, position(1, 8, 1), "TRB", "Turbulence"));

        final DataFormat f021_220 = compound(I021_220,
                Subfield.of(f021_220_WS, 1, "WS", "Wind Speed"),
                Subfield.of(f021_220_WD, 2, "WD", "Wind Direction"),
                Subfield.of(f021_220_TMP, 3, "TMP", "Temperature"),
                Subfield.of(f021_220_TRB, 4, "TRB", "Turbulence"),
                Subfield.sp(I021_220_SP4, 5),
                Subfield.sp(I021_220_SP3, 6),
                Subfield.sp(I021_220_SP2, 7),
                Subfield.fx(I021_220_FX1));

        final DataItem i021_220 = DataItem.from(f021_220, "Met Information", false,
                "Meteorological information");

        final DataFormat f021_230 = fixed(I021_230, 2,
                smeasure(I021_230_RAN, position(2, 16, 1), "RAN", 0.01, UnitOfMeasure.ANGLE_DEGREE, -180.0, 180.0, "Roll Angle"));

        final DataItem i021_230 = DataItem.from(f021_230, "Roll Angle", false,
                "The roll angle, in two's complement form, of an aircraft executing a turn");

        final DataItem i021_FX1 = DataItem.fx(CAT021_ID, 5, 33);
        final DataItem i021_FX2 = DataItem.fx(CAT021_ID, 5, 25);
        final DataItem i021_FX3 = DataItem.fx(CAT021_ID, 5, 17);
        final DataItem i021_FX4 = DataItem.fx(CAT021_ID, 5, 9);
        final DataItem i021_SP3 = DataItem.sp(CAT021_ID, 5, 8);
        final DataItem i021_SP4 = DataItem.sp(CAT021_ID, 5, 7);
        final DataItem i021_SP5 = DataItem.sp(CAT021_ID, 5, 6);
        final DataItem i021_SP6 = DataItem.sp(CAT021_ID, 5, 5);
        final DataItem i021_SP7 = DataItem.sp(CAT021_ID, 5, 4);
        final DataItem i021_RE_ = DataItem.re(I021_RE);
        final DataItem i021_SP_ = DataItem.sp(I021_SP);
        final DataItem i021_FX5 = DataItem.fx(CAT021_ID, 5, 1);

        final UserApplicationProfile uap = new UserApplicationProfile(CAT021_ID,
                i021_010, // 40
                i021_040, // 39
                i021_030, // 38
                i021_130, // 37
                i021_080, // 36
                i021_140, // 35
                i021_090, // 34
                i021_FX1, // 33
                i021_210, // 32
                i021_230, // 31
                i021_145, // 30
                i021_150, // 29
                i021_151, // 28
                i021_152, // 27
                i021_155, // 26
                i021_FX2, // 25
                i021_157, // 24
                i021_160, // 23
                i021_165, // 22
                i021_170, // 21
                i021_095, // 20
                i021_032, // 19
                i021_200, // 18
                i021_FX3, // 17
                i021_020, // 16
                i021_220, // 15
                i021_146, // 14
                i021_148, // 13
                i021_110, // 12
                i021_070, // 11
                i021_131, // 10
                i021_FX4, //  9
                i021_SP3, //  8
                i021_SP4, //  7
                i021_SP5, //  6
                i021_SP6, //  5
                i021_SP7, //  4
                i021_RE_, //  3
                i021_SP_, //  2
                i021_FX5);//  1

        return new ASTERIXCategory("ADS-B Messages", uap);
    }

    private Cat021V0Dot26() {
    }
}
