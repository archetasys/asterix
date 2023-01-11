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

public final class Cat021V2Dot2 {
    public static final long CAT021_ID = categoryId(21, 2, 2);

    public static final long I021_008;
    public static final long I021_008_RA;
    public static final long I021_008_TC;
    public static final long I021_008_TS;
    public static final long I021_008_ARV;
    public static final long I021_008_CDTIA;
    public static final long I021_008_NTCAS;
    public static final long I021_008_SA;
    public static final long I021_010;
    public static final long I021_010_SAC;
    public static final long I021_010_SIC;
    public static final long I021_015;
    public static final long I021_015_SID;
    public static final long I021_016;
    public static final long I021_016_RP;
    public static final long I021_020;
    public static final long I021_020_ECAT;
    public static final long I021_040;
    public static final long I021_040_ATP;
    public static final long I021_040_ARC;
    public static final long I021_040_RC;
    public static final long I021_040_RAB;
    public static final long I021_040_FX1;
    public static final long I021_040_DCR;
    public static final long I021_040_GBS;
    public static final long I021_040_SIM;
    public static final long I021_040_TST;
    public static final long I021_040_SAA;
    public static final long I021_040_CL;
    public static final long I021_040_FX2;
    public static final long I021_040_SP8;
    public static final long I021_040_LLC;
    public static final long I021_040_IPC;
    public static final long I021_040_NOGO;
    public static final long I021_040_CPR;
    public static final long I021_040_LDPJ;
    public static final long I021_040_RCF;
    public static final long I021_040_FX3;
    public static final long I021_070;
    public static final long I021_070_SP16;
    public static final long I021_070_SP15;
    public static final long I021_070_SP14;
    public static final long I021_070_SP13;
    public static final long I021_070_MODE3A;
    public static final long I021_071;
    public static final long I021_071_TAP;
    public static final long I021_072;
    public static final long I021_072_TAV;
    public static final long I021_073;
    public static final long I021_073_TOMRP;
    public static final long I021_074;
    public static final long I021_074_FSI;
    public static final long I021_074_TOMRPH;
    public static final long I021_075;
    public static final long I021_075_TOMRV;
    public static final long I021_076;
    public static final long I021_076_FSI;
    public static final long I021_076_TOMRVH;
    public static final long I021_077;
    public static final long I021_077_TRT;
    public static final long I021_080;
    public static final long I021_080_ADR;
    public static final long I021_090;
    public static final long I021_090_NUCRV;
    public static final long I021_090_NUCPN;
    public static final long I021_090_FX1;
    public static final long I021_090_NICB;
    public static final long I021_090_SIL;
    public static final long I021_090_NACP;
    public static final long I021_090_FX2;
    public static final long I021_090_X2_SP8;
    public static final long I021_090_X2_SP7;
    public static final long I021_090_SILS;
    public static final long I021_090_SDA;
    public static final long I021_090_GVA;
    public static final long I021_090_FX3;
    public static final long I021_090_PIC;
    public static final long I021_090_X3_SP4;
    public static final long I021_090_X3_SP3;
    public static final long I021_090_X3_SP2;
    public static final long I021_090_FX4;
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
    public static final long I021_131_LAT;
    public static final long I021_131_LON;
    public static final long I021_132;
    public static final long I021_132_MAM;
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
    public static final long I021_155_RE;
    public static final long I021_155_BVR;
    public static final long I021_157;
    public static final long I021_157_RE;
    public static final long I021_157_GVR;
    public static final long I021_160;
    public static final long I021_160_RE;
    public static final long I021_160_GS;
    public static final long I021_160_TAN;
    public static final long I021_161;
    public static final long I021_161_SP16;
    public static final long I021_161_SP15;
    public static final long I021_161_SP14;
    public static final long I021_161_SP13;
    public static final long I021_161_TRN;
    public static final long I021_165;
    public static final long I021_165_SP16;
    public static final long I021_165_SP15;
    public static final long I021_165_SP14;
    public static final long I021_165_SP13;
    public static final long I021_165_SP12;
    public static final long I021_165_SP11;
    public static final long I021_165_TAR;
    public static final long I021_170;
    public static final long I021_170_IDENT;
    public static final long I021_200;
    public static final long I021_200_ICF;
    public static final long I021_200_LNAV;
    public static final long I021_200_ME;
    public static final long I021_200_PS;
    public static final long I021_200_SS;
    public static final long I021_210;
    public static final long I021_210_SP8;
    public static final long I021_210_VNS;
    public static final long I021_210_VN;
    public static final long I021_210_LTT;
    public static final long I021_220;
    public static final long I021_220_WS_WS;
    public static final long I021_220_WD_WD;
    public static final long I021_220_TMP_TMP;
    public static final long I021_220_TRB_TRB;
    public static final long I021_230;
    public static final long I021_230_RAN;
    public static final long I021_250;
    public static final long I021_250_MBDATA;
    public static final long I021_250_BDS1;
    public static final long I021_250_BDS2;
    public static final long I021_260;
    public static final long I021_260_BDS30;
    public static final long I021_260_ARA41;
    public static final long I021_260_ARA42;
    public static final long I021_260_ARA43;
    public static final long I021_260_ARA44;
    public static final long I021_260_ARA45;
    public static final long I021_260_ARA46;
    public static final long I021_260_ARA47;
    public static final long I021_260_ACAS3;
    public static final long I021_260_RAC55;
    public static final long I021_260_RAC56;
    public static final long I021_260_RAC57;
    public static final long I021_260_RAC58;
    public static final long I021_260_RAT;
    public static final long I021_260_MTE;
    public static final long I021_260_TTI;
    public static final long I021_260_TID;
    public static final long I021_271;
    public static final long I021_271_FP_SP8;
    public static final long I021_271_FP_SP7;
    public static final long I021_271_POA;
    public static final long I021_271_CDTIS;
    public static final long I021_271_B2LOW;
    public static final long I021_271_RAS;
    public static final long I021_271_IDENT;
    public static final long I021_271_FX1;
    public static final long I021_271_LW;
    public static final long I021_271_X1_SP4;
    public static final long I021_271_X1_SP3;
    public static final long I021_271_X1_SP2;
    public static final long I021_271_FX2;
    public static final long I021_295;
    public static final long I021_295_AOS_AOS;
    public static final long I021_295_TRD_TRD;
    public static final long I021_295_M3A_M3A;
    public static final long I021_295_QI_QI;
    public static final long I021_295_TI_TI;
    public static final long I021_295_MAM_MAM;
    public static final long I021_295_GH_GH;
    public static final long I021_295_FL_FL;
    public static final long I021_295_ISA_ISA;
    public static final long I021_295_FSA_FSA;
    public static final long I021_295_AS_AS;
    public static final long I021_295_TAS_TAS;
    public static final long I021_295_MH_MH;
    public static final long I021_295_BVR_BVR;
    public static final long I021_295_GVR_GVR;
    public static final long I021_295_GV_GV;
    public static final long I021_295_TAR_TAR;
    public static final long I021_295_TID_TID;
    public static final long I021_295_TS_TS;
    public static final long I021_295_MET_MET;
    public static final long I021_295_ROA_ROA;
    public static final long I021_295_ARA_ARA;
    public static final long I021_295_SCC_SCC;
    public static final long I021_400;
    public static final long I021_400_RID;
    public static final long I021_RE;
    public static final long I021_SP;

    private static final long I021_040_FP;
    private static final long I021_040_X1;
    private static final long I021_040_X2;

    private static final long I021_090_FP;
    private static final long I021_090_X1;
    private static final long I021_090_X2;
    private static final long I021_090_X3;

    private static final long I021_110_TIS;
    private static final long I021_110_TID;
    private static final long I021_110_SP6;
    private static final long I021_110_SP5;
    private static final long I021_110_SP4;
    private static final long I021_110_SP3;
    private static final long I021_110_SP2;
    private static final long I021_110_FX1;

    private static final long I021_220_WS;
    private static final long I021_220_WD;
    private static final long I021_220_TMP;
    private static final long I021_220_TRB;
    private static final long I021_220_SP4;
    private static final long I021_220_SP3;
    private static final long I021_220_SP2;
    private static final long I021_220_FX1;

    private static final long I021_271_FP;
    private static final long I021_271_X1;

    private static final long I021_295_AOS;
    private static final long I021_295_TRD;
    private static final long I021_295_M3A;
    private static final long I021_295_QI;
    private static final long I021_295_TI;
    private static final long I021_295_MAM;
    private static final long I021_295_GH;
    private static final long I021_295_FX1;
    private static final long I021_295_FL;
    private static final long I021_295_ISA;
    private static final long I021_295_FSA;
    private static final long I021_295_AS;
    private static final long I021_295_TAS;
    private static final long I021_295_MH;
    private static final long I021_295_BVR;
    private static final long I021_295_FX2;
    private static final long I021_295_GVR;
    private static final long I021_295_GV;
    private static final long I021_295_TAR;
    private static final long I021_295_TID;
    private static final long I021_295_TS;
    private static final long I021_295_MET;
    private static final long I021_295_ROA;
    private static final long I021_295_FX3;
    private static final long I021_295_ARA;
    private static final long I021_295_SCC;
    private static final long I021_295_SP1;
    private static final long I021_295_SP2;
    private static final long I021_295_SP3;
    private static final long I021_295_SP4;
    private static final long I021_295_SP5;
    private static final long I021_295_FX4;

    static {
        I021_008 = dataItemId(CAT021_ID, 8, DATA_FORMAT_FIXED);
        I021_008_RA = bitsFieldId(I021_008, 8, BITS_FIELD_ENCODING_VALUE);
        I021_008_TC = bitsFieldId(I021_008, 7, BITS_FIELD_ENCODING_VALUES);
        I021_008_TS = bitsFieldId(I021_008, 5, BITS_FIELD_ENCODING_VALUE);
        I021_008_ARV = bitsFieldId(I021_008, 4, BITS_FIELD_ENCODING_VALUE);
        I021_008_CDTIA = bitsFieldId(I021_008, 3, BITS_FIELD_ENCODING_VALUE);
        I021_008_NTCAS = bitsFieldId(I021_008, 2, BITS_FIELD_ENCODING_VALUE);
        I021_008_SA = bitsFieldId(I021_008, 1, BITS_FIELD_ENCODING_VALUE);
        I021_010 = dataItemId(CAT021_ID, 10, DATA_FORMAT_FIXED);
        I021_010_SAC = bitsFieldId(I021_010, 16, BITS_FIELD_ENCODING_UNSIGNED);
        I021_010_SIC = bitsFieldId(I021_010, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_015 = dataItemId(CAT021_ID, 15, DATA_FORMAT_FIXED);
        I021_015_SID = bitsFieldId(I021_015, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_016 = dataItemId(CAT021_ID, 16, DATA_FORMAT_FIXED);
        I021_016_RP = bitsFieldId(I021_016, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_020 = dataItemId(CAT021_ID, 20, DATA_FORMAT_FIXED);
        I021_020_ECAT = bitsFieldId(I021_020, 8, BITS_FIELD_ENCODING_VALUES);
        I021_040 = dataItemId(CAT021_ID, 40, DATA_FORMAT_EXTENDED);
        I021_040_FP = subAId(I021_040, 0, DATA_FORMAT_FIXED);
        I021_040_ATP = bitsFieldId(I021_040_FP, 8, BITS_FIELD_ENCODING_VALUES);
        I021_040_ARC = bitsFieldId(I021_040_FP, 5, BITS_FIELD_ENCODING_VALUES);
        I021_040_RC = bitsFieldId(I021_040_FP, 3, BITS_FIELD_ENCODING_VALUE);
        I021_040_RAB = bitsFieldId(I021_040_FP, 2, BITS_FIELD_ENCODING_VALUE);
        I021_040_FX1 = bitsFieldId(I021_040_FP, 1, BITS_FIELD_ENCODING_FX);
        I021_040_X1 = subAId(I021_040, 1, DATA_FORMAT_FIXED);
        I021_040_DCR = bitsFieldId(I021_040_X1, 8, BITS_FIELD_ENCODING_VALUE);
        I021_040_GBS = bitsFieldId(I021_040_X1, 7, BITS_FIELD_ENCODING_VALUE);
        I021_040_SIM = bitsFieldId(I021_040_X1, 6, BITS_FIELD_ENCODING_VALUE);
        I021_040_TST = bitsFieldId(I021_040_X1, 5, BITS_FIELD_ENCODING_VALUE);
        I021_040_SAA = bitsFieldId(I021_040_X1, 4, BITS_FIELD_ENCODING_VALUE);
        I021_040_CL = bitsFieldId(I021_040_X1, 3, BITS_FIELD_ENCODING_VALUES);
        I021_040_FX2 = bitsFieldId(I021_040_X1, 1, BITS_FIELD_ENCODING_FX);
        I021_040_X2 = subAId(I021_040, 2, DATA_FORMAT_FIXED);
        I021_040_SP8 = bitsFieldId(I021_040_X2, 8, BITS_FIELD_ENCODING_SPARE);
        I021_040_LLC = bitsFieldId(I021_040_X2, 7, BITS_FIELD_ENCODING_VALUE);
        I021_040_IPC = bitsFieldId(I021_040_X2, 6, BITS_FIELD_ENCODING_VALUE);
        I021_040_NOGO = bitsFieldId(I021_040_X2, 5, BITS_FIELD_ENCODING_VALUE);
        I021_040_CPR = bitsFieldId(I021_040_X2, 4, BITS_FIELD_ENCODING_VALUE);
        I021_040_LDPJ = bitsFieldId(I021_040_X2, 3, BITS_FIELD_ENCODING_VALUE);
        I021_040_RCF = bitsFieldId(I021_040_X2, 2, BITS_FIELD_ENCODING_VALUE);
        I021_040_FX3 = bitsFieldId(I021_040_X2, 1, BITS_FIELD_ENCODING_FX);
        I021_070 = dataItemId(CAT021_ID, 70, DATA_FORMAT_FIXED);
        I021_070_SP16 = bitsFieldId(I021_070, 16, BITS_FIELD_ENCODING_SPARE);
        I021_070_SP15 = bitsFieldId(I021_070, 15, BITS_FIELD_ENCODING_SPARE);
        I021_070_SP14 = bitsFieldId(I021_070, 14, BITS_FIELD_ENCODING_SPARE);
        I021_070_SP13 = bitsFieldId(I021_070, 13, BITS_FIELD_ENCODING_SPARE);
        I021_070_MODE3A = bitsFieldId(I021_070, 12, BITS_FIELD_ENCODING_OCTAL);
        I021_071 = dataItemId(CAT021_ID, 71, DATA_FORMAT_FIXED);
        I021_071_TAP = bitsFieldId(I021_071, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_072 = dataItemId(CAT021_ID, 72, DATA_FORMAT_FIXED);
        I021_072_TAV = bitsFieldId(I021_072, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_073 = dataItemId(CAT021_ID, 73, DATA_FORMAT_FIXED);
        I021_073_TOMRP = bitsFieldId(I021_073, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_074 = dataItemId(CAT021_ID, 74, DATA_FORMAT_FIXED);
        I021_074_FSI = bitsFieldId(I021_074, 32, BITS_FIELD_ENCODING_VALUES);
        I021_074_TOMRPH = bitsFieldId(I021_074, 30, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_075 = dataItemId(CAT021_ID, 75, DATA_FORMAT_FIXED);
        I021_075_TOMRV = bitsFieldId(I021_075, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_076 = dataItemId(CAT021_ID, 76, DATA_FORMAT_FIXED);
        I021_076_FSI = bitsFieldId(I021_076, 32, BITS_FIELD_ENCODING_VALUES);
        I021_076_TOMRVH = bitsFieldId(I021_076, 30, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_077 = dataItemId(CAT021_ID, 77, DATA_FORMAT_FIXED);
        I021_077_TRT = bitsFieldId(I021_077, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_080 = dataItemId(CAT021_ID, 80, DATA_FORMAT_FIXED);
        I021_080_ADR = bitsFieldId(I021_080, 24, BITS_FIELD_ENCODING_HEX);
        I021_090 = dataItemId(CAT021_ID, 90, DATA_FORMAT_EXTENDED);
        I021_090_FP = subAId(I021_090, 0, DATA_FORMAT_FIXED);
        I021_090_NUCRV = bitsFieldId(I021_090_FP, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_NUCPN = bitsFieldId(I021_090_FP, 5, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_FX1 = bitsFieldId(I021_090_FP, 1, BITS_FIELD_ENCODING_FX);
        I021_090_X1 = subAId(I021_090, 1, DATA_FORMAT_FIXED);
        I021_090_NICB = bitsFieldId(I021_090_X1, 8, BITS_FIELD_ENCODING_VALUE);
        I021_090_SIL = bitsFieldId(I021_090_X1, 7, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_NACP = bitsFieldId(I021_090_X1, 5, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_FX2 = bitsFieldId(I021_090_X1, 1, BITS_FIELD_ENCODING_FX);
        I021_090_X2 = subAId(I021_090, 2, DATA_FORMAT_FIXED);
        I021_090_X2_SP8 = bitsFieldId(I021_090_X2, 8, BITS_FIELD_ENCODING_SPARE);
        I021_090_X2_SP7 = bitsFieldId(I021_090_X2, 7, BITS_FIELD_ENCODING_SPARE);
        I021_090_SILS = bitsFieldId(I021_090_X2, 6, BITS_FIELD_ENCODING_VALUE);
        I021_090_SDA = bitsFieldId(I021_090_X2, 5, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_GVA = bitsFieldId(I021_090_X2, 3, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_FX3 = bitsFieldId(I021_090_X2, 1, BITS_FIELD_ENCODING_FX);
        I021_090_X3 = subAId(I021_090, 3, DATA_FORMAT_FIXED);
        I021_090_PIC = bitsFieldId(I021_090_X3, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_090_X3_SP4 = bitsFieldId(I021_090_X3, 4, BITS_FIELD_ENCODING_SPARE);
        I021_090_X3_SP3 = bitsFieldId(I021_090_X3, 3, BITS_FIELD_ENCODING_SPARE);
        I021_090_X3_SP2 = bitsFieldId(I021_090_X3, 2, BITS_FIELD_ENCODING_SPARE);
        I021_090_FX4 = bitsFieldId(I021_090_X3, 1, BITS_FIELD_ENCODING_FX);
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
        I021_130_LAT = bitsFieldId(I021_130, 48, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_130_LON = bitsFieldId(I021_130, 24, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_131 = dataItemId(CAT021_ID, 131, DATA_FORMAT_FIXED);
        I021_131_LAT = bitsFieldId(I021_131, 64, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_131_LON = bitsFieldId(I021_131, 32, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_132 = dataItemId(CAT021_ID, 132, DATA_FORMAT_FIXED);
        I021_132_MAM = bitsFieldId(I021_132, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
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
        I021_155_RE = bitsFieldId(I021_155, 16, BITS_FIELD_ENCODING_VALUE);
        I021_155_BVR = bitsFieldId(I021_155, 15, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_157 = dataItemId(CAT021_ID, 157, DATA_FORMAT_FIXED);
        I021_157_RE = bitsFieldId(I021_157, 16, BITS_FIELD_ENCODING_VALUE);
        I021_157_GVR = bitsFieldId(I021_157, 15, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_160 = dataItemId(CAT021_ID, 160, DATA_FORMAT_FIXED);
        I021_160_RE = bitsFieldId(I021_160, 32, BITS_FIELD_ENCODING_VALUE);
        I021_160_GS = bitsFieldId(I021_160, 31, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_160_TAN = bitsFieldId(I021_160, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_161 = dataItemId(CAT021_ID, 161, DATA_FORMAT_FIXED);
        I021_161_SP16 = bitsFieldId(I021_161, 16, BITS_FIELD_ENCODING_SPARE);
        I021_161_SP15 = bitsFieldId(I021_161, 15, BITS_FIELD_ENCODING_SPARE);
        I021_161_SP14 = bitsFieldId(I021_161, 14, BITS_FIELD_ENCODING_SPARE);
        I021_161_SP13 = bitsFieldId(I021_161, 13, BITS_FIELD_ENCODING_SPARE);
        I021_161_TRN = bitsFieldId(I021_161, 12, BITS_FIELD_ENCODING_UNSIGNED);
        I021_165 = dataItemId(CAT021_ID, 165, DATA_FORMAT_FIXED);
        I021_165_SP16 = bitsFieldId(I021_165, 16, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP15 = bitsFieldId(I021_165, 15, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP14 = bitsFieldId(I021_165, 14, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP13 = bitsFieldId(I021_165, 13, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP12 = bitsFieldId(I021_165, 12, BITS_FIELD_ENCODING_SPARE);
        I021_165_SP11 = bitsFieldId(I021_165, 11, BITS_FIELD_ENCODING_SPARE);
        I021_165_TAR = bitsFieldId(I021_165, 10, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I021_170 = dataItemId(CAT021_ID, 170, DATA_FORMAT_FIXED);
        I021_170_IDENT = bitsFieldId(I021_170, 48, BITS_FIELD_ENCODING_IA5);
        I021_200 = dataItemId(CAT021_ID, 200, DATA_FORMAT_FIXED);
        I021_200_ICF = bitsFieldId(I021_200, 8, BITS_FIELD_ENCODING_VALUE);
        I021_200_LNAV = bitsFieldId(I021_200, 7, BITS_FIELD_ENCODING_VALUE);
        I021_200_ME = bitsFieldId(I021_200, 6, BITS_FIELD_ENCODING_VALUE);
        I021_200_PS = bitsFieldId(I021_200, 5, BITS_FIELD_ENCODING_VALUES);
        I021_200_SS = bitsFieldId(I021_200, 2, BITS_FIELD_ENCODING_VALUES);
        I021_210 = dataItemId(CAT021_ID, 210, DATA_FORMAT_FIXED);
        I021_210_SP8 = bitsFieldId(I021_210, 8, BITS_FIELD_ENCODING_SPARE);
        I021_210_VNS = bitsFieldId(I021_210, 7, BITS_FIELD_ENCODING_VALUE);
        I021_210_VN = bitsFieldId(I021_210, 6, BITS_FIELD_ENCODING_VALUES);
        I021_210_LTT = bitsFieldId(I021_210, 3, BITS_FIELD_ENCODING_VALUES);
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
        I021_250 = dataItemId(CAT021_ID, 250, DATA_FORMAT_REPETITIVE);
        I021_250_MBDATA = bitsFieldId(I021_250, 64, BITS_FIELD_ENCODING_HEX);
        I021_250_BDS1 = bitsFieldId(I021_250, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_250_BDS2 = bitsFieldId(I021_250, 4, BITS_FIELD_ENCODING_UNSIGNED);
        I021_260 = dataItemId(CAT021_ID, 260, DATA_FORMAT_FIXED);
        I021_260_BDS30 = bitsFieldId(I021_260, 56, BITS_FIELD_ENCODING_HEX);
        I021_260_ARA41 = bitsFieldId(I021_260, 48, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA42 = bitsFieldId(I021_260, 47, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA43 = bitsFieldId(I021_260, 46, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA44 = bitsFieldId(I021_260, 45, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA45 = bitsFieldId(I021_260, 44, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA46 = bitsFieldId(I021_260, 43, BITS_FIELD_ENCODING_VALUE);
        I021_260_ARA47 = bitsFieldId(I021_260, 42, BITS_FIELD_ENCODING_VALUE);
        I021_260_ACAS3 = bitsFieldId(I021_260, 41, BITS_FIELD_ENCODING_UNSIGNED);
        I021_260_RAC55 = bitsFieldId(I021_260, 34, BITS_FIELD_ENCODING_VALUE);
        I021_260_RAC56 = bitsFieldId(I021_260, 33, BITS_FIELD_ENCODING_VALUE);
        I021_260_RAC57 = bitsFieldId(I021_260, 32, BITS_FIELD_ENCODING_VALUE);
        I021_260_RAC58 = bitsFieldId(I021_260, 31, BITS_FIELD_ENCODING_VALUE);
        I021_260_RAT = bitsFieldId(I021_260, 30, BITS_FIELD_ENCODING_VALUE);
        I021_260_MTE = bitsFieldId(I021_260, 29, BITS_FIELD_ENCODING_VALUE);
        I021_260_TTI = bitsFieldId(I021_260, 28, BITS_FIELD_ENCODING_VALUES);
        I021_260_TID = bitsFieldId(I021_260, 26, BITS_FIELD_ENCODING_UNSIGNED);
        I021_271 = dataItemId(CAT021_ID, 271, DATA_FORMAT_EXTENDED);
        I021_271_FP = subAId(I021_271, 0, DATA_FORMAT_FIXED);
        I021_271_FP_SP8 = bitsFieldId(I021_271_FP, 8, BITS_FIELD_ENCODING_SPARE);
        I021_271_FP_SP7 = bitsFieldId(I021_271_FP, 7, BITS_FIELD_ENCODING_SPARE);
        I021_271_POA = bitsFieldId(I021_271_FP, 6, BITS_FIELD_ENCODING_VALUE);
        I021_271_CDTIS = bitsFieldId(I021_271_FP, 5, BITS_FIELD_ENCODING_VALUE);
        I021_271_B2LOW = bitsFieldId(I021_271_FP, 4, BITS_FIELD_ENCODING_VALUE);
        I021_271_RAS = bitsFieldId(I021_271_FP, 3, BITS_FIELD_ENCODING_VALUE);
        I021_271_IDENT = bitsFieldId(I021_271_FP, 2, BITS_FIELD_ENCODING_VALUE);
        I021_271_FX1 = bitsFieldId(I021_271_FP, 1, BITS_FIELD_ENCODING_FX);
        I021_271_X1 = subAId(I021_271, 1, DATA_FORMAT_FIXED);
        I021_271_LW = bitsFieldId(I021_271_X1, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_271_X1_SP4 = bitsFieldId(I021_271_X1, 4, BITS_FIELD_ENCODING_SPARE);
        I021_271_X1_SP3 = bitsFieldId(I021_271_X1, 3, BITS_FIELD_ENCODING_SPARE);
        I021_271_X1_SP2 = bitsFieldId(I021_271_X1, 2, BITS_FIELD_ENCODING_SPARE);
        I021_271_FX2 = bitsFieldId(I021_271_X1, 1, BITS_FIELD_ENCODING_FX);
        I021_295 = dataItemId(CAT021_ID, 295, DATA_FORMAT_COMPOUND);
        I021_295_AOS = subAId(I021_295, 32, DATA_FORMAT_FIXED);
        I021_295_TRD = subAId(I021_295, 31, DATA_FORMAT_FIXED);
        I021_295_M3A = subAId(I021_295, 30, DATA_FORMAT_FIXED);
        I021_295_QI = subAId(I021_295, 29, DATA_FORMAT_FIXED);
        I021_295_TI = subAId(I021_295, 28, DATA_FORMAT_FIXED);
        I021_295_MAM = subAId(I021_295, 27, DATA_FORMAT_FIXED);
        I021_295_GH = subAId(I021_295, 26, DATA_FORMAT_FIXED);
        I021_295_FX1 = subAId(I021_295, 25, DATA_FORMAT_FIXED);
        I021_295_FL = subAId(I021_295, 24, DATA_FORMAT_FIXED);
        I021_295_ISA = subAId(I021_295, 23, DATA_FORMAT_FIXED);
        I021_295_FSA = subAId(I021_295, 22, DATA_FORMAT_FIXED);
        I021_295_AS = subAId(I021_295, 21, DATA_FORMAT_FIXED);
        I021_295_TAS = subAId(I021_295, 20, DATA_FORMAT_FIXED);
        I021_295_MH = subAId(I021_295, 19, DATA_FORMAT_FIXED);
        I021_295_BVR = subAId(I021_295, 18, DATA_FORMAT_FIXED);
        I021_295_FX2 = subAId(I021_295, 17, DATA_FORMAT_FIXED);
        I021_295_GVR = subAId(I021_295, 16, DATA_FORMAT_FIXED);
        I021_295_GV = subAId(I021_295, 15, DATA_FORMAT_FIXED);
        I021_295_TAR = subAId(I021_295, 14, DATA_FORMAT_FIXED);
        I021_295_TID = subAId(I021_295, 13, DATA_FORMAT_FIXED);
        I021_295_TS = subAId(I021_295, 12, DATA_FORMAT_FIXED);
        I021_295_MET = subAId(I021_295, 11, DATA_FORMAT_FIXED);
        I021_295_ROA = subAId(I021_295, 10, DATA_FORMAT_FIXED);
        I021_295_FX3 = subAId(I021_295, 9, DATA_FORMAT_FIXED);
        I021_295_ARA = subAId(I021_295, 8, DATA_FORMAT_FIXED);
        I021_295_SCC = subAId(I021_295, 7, DATA_FORMAT_FIXED);
        I021_295_SP1 = subAId(I021_295, 6, DATA_FORMAT_FIXED);
        I021_295_SP2 = subAId(I021_295, 5, DATA_FORMAT_FIXED);
        I021_295_SP3 = subAId(I021_295, 4, DATA_FORMAT_FIXED);
        I021_295_SP4 = subAId(I021_295, 3, DATA_FORMAT_FIXED);
        I021_295_SP5 = subAId(I021_295, 2, DATA_FORMAT_FIXED);
        I021_295_FX4 = subAId(I021_295, 1, DATA_FORMAT_FIXED);
        I021_295_AOS_AOS = bitsFieldId(I021_295_AOS, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TRD_TRD = bitsFieldId(I021_295_TRD, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_M3A_M3A = bitsFieldId(I021_295_M3A, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_QI_QI = bitsFieldId(I021_295_QI, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TI_TI = bitsFieldId(I021_295_TI, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_MAM_MAM = bitsFieldId(I021_295_MAM, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_GH_GH = bitsFieldId(I021_295_GH, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_FL_FL = bitsFieldId(I021_295_FL, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_ISA_ISA = bitsFieldId(I021_295_ISA, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_FSA_FSA = bitsFieldId(I021_295_FSA, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_AS_AS = bitsFieldId(I021_295_AS, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TAS_TAS = bitsFieldId(I021_295_TAS, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_MH_MH = bitsFieldId(I021_295_MH, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_BVR_BVR = bitsFieldId(I021_295_BVR, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_GVR_GVR = bitsFieldId(I021_295_GVR, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_GV_GV = bitsFieldId(I021_295_GV, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TAR_TAR = bitsFieldId(I021_295_TAR, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TID_TID = bitsFieldId(I021_295_TID, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_TS_TS = bitsFieldId(I021_295_TS, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_MET_MET = bitsFieldId(I021_295_MET, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_ROA_ROA = bitsFieldId(I021_295_ROA, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_ARA_ARA = bitsFieldId(I021_295_ARA, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_295_SCC_SCC = bitsFieldId(I021_295_SCC, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I021_400 = dataItemId(CAT021_ID, 400, DATA_FORMAT_FIXED);
        I021_400_RID = bitsFieldId(I021_400, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I021_RE = dataItemId(CAT021_ID, DATA_ITEM_RE, DATA_FORMAT_EXPLICIT);
        I021_SP = dataItemId(CAT021_ID, DATA_ITEM_SP, DATA_FORMAT_EXPLICIT);
    }

    static ASTERIXCategory createCategory() {
        final DataFormat f021_008 = fixed(I021_008, 1,
                bit(I021_008_RA, position(1, 8, 8), "RA", "TCAS Resolution Advisory active",
                        BitsValue.of(0, "TCAS II or ACAS RA not active"),
                        BitsValue.of(1, "TCAS RA active")),
                bits(I021_008_TC, position(1, 7, 6), "TC", "Target Trajectory Change Report Capability",
                        BitsValue.of(0, "No capability for Trajectory Change Reports"),
                        BitsValue.of(1, "Support for TC+0 reports only"),
                        BitsValue.of(2, "Support for multiple TC reports"),
                        BitsValue.of(3, "Reserved")),
                bit(I021_008_TS, position(1, 5, 5), "TS", "Target Status Report Capability",
                        BitsValue.of(0, "No capability to support Target State Reports"),
                        BitsValue.of(1, "Capable of supporting Target State Reports")),
                bit(I021_008_ARV, position(1, 4, 4), "ARV", "Air-Referenced Velocity Report Capability",
                        BitsValue.of(0, "No capability to generate ARV-reports"),
                        BitsValue.of(1, "Capable of generate ARV-reports")),
                bit(I021_008_CDTIA, position(1, 3, 3), "CDTIA", "Cockpit Display of Traffic Information Airborne",
                        BitsValue.of(0, "CDTI not operational"),
                        BitsValue.of(1, "CDTI operational")),
                bit(I021_008_NTCAS, position(1, 2, 2), "NTCAS", "TCAS System Status",
                        BitsValue.of(0, "TCAS operational or unknown"),
                        BitsValue.of(1, "TCAS not installed or not operational")),
                bit(I021_008_SA, position(1, 1, 1), "SA", "Single Antenna",
                        BitsValue.of(0, "Antenna Diversity"),
                        BitsValue.of(1, "Single Antenna only")));

        final DataItem i021_008 = DataItem.from(f021_008, "Aircraft Operational Status", false,
                "Identification of the operational services available in the aircraft while airborne");

        final DataFormat f021_010 = fixed(I021_010, 2,
                unsigned(I021_010_SAC, position(2, 16, 9), "SAC", "System Area Code"),
                unsigned(I021_010_SIC, position(2, 8, 1), "SIC", "System Identification Code"));

        final DataItem i021_010 = DataItem.from(f021_010, "Data Source Identification", true,
                "Identification of the ADS-B station providing information");

        final DataFormat f021_015 = fixed(I021_015, 1,
                unsigned(I021_015_SID, position(1, 8, 1), "SID", "Service Identification"));

        final DataItem i021_015 = DataItem.from(f021_015, "Service Identification", false,
                "Identification of the service provided to one or more users");

        final DataFormat f021_016 = fixed(I021_016, 1,
                umeasure(I021_016_RP, position(1, 8, 1), "RP", 0.5, UnitOfMeasure.TIME_SECONDS, 127.5, "Report Period"));

        final DataItem i021_016 = DataItem.from(f021_016, "Service Management", false,
                "Identification of services offered by a ground station (identified by a SIC code)");

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
                        BitsValue.of(23, "Cluster obstacle"),
                        BitsValue.of(24, "Line obstacle")));

        final DataItem i021_020 = DataItem.from(f021_020, "Emitter Category", false,
                "Characteristics of the originating ADS-B unit.");

        final DataFormat f021_040 = extended(I021_040,
                Part.of(I021_040_FP, 1,
                        bits(I021_040_ATP, position(1, 8, 6), "ATP", "Address Type",
                                BitsValue.of(0, "Non unique address"),
                                BitsValue.of(1, "24-Bit ICAO address"),
                                BitsValue.of(2, "Surface vehicle address"),
                                BitsValue.of(3, "Anonymous address"),
                                BitsValue.of(4, "Reserved for future use"),
                                BitsValue.of(5, "Reserved for future use"),
                                BitsValue.of(6, "Reserved for future use"),
                                BitsValue.of(7, "Reserved for future use")),
                        bits(I021_040_ARC, position(1, 5, 4), "ARC", "Altitude Reporting Capability",
                                BitsValue.of(0, "25 ft"),
                                BitsValue.of(1, "100 ft"),
                                BitsValue.of(2, "Unknown"),
                                BitsValue.of(3, "Invalid")),
                        bit(I021_040_RC, position(1, 3, 3), "RC", "Range Check",
                                BitsValue.of(0, "Default"),
                                BitsValue.of(1, "Range Check passed, CPR Validation pending")),
                        bit(I021_040_RAB, position(1, 2, 2), "RAB", "Report Type",
                                BitsValue.of(0, "Report from target transponder"),
                                BitsValue.of(1, "Report from field monitor (fixed transponder")),
                        fx(I021_040_FX1, position(1, 1, 1))),
                Part.of(I021_040_X1, 1,
                        bit(I021_040_DCR, position(1, 8, 8), "DCR", "Differential Correction",
                                BitsValue.of(0, "No differential correction (ADS-B)"),
                                BitsValue.of(1, "Differential correction (ADS-B)")),
                        bit(I021_040_GBS, position(1, 7, 7), "GBS", "Ground Bit Setting",
                                BitsValue.of(0, "Ground Bit not set"),
                                BitsValue.of(1, "Ground Bit set")),
                        bit(I021_040_SIM, position(1, 6, 6), "SIM", "Simulated Target",
                                BitsValue.of(0, "Actual target report"),
                                BitsValue.of(1, "Simulated target report")),
                        bit(I021_040_TST, position(1, 5, 5), "TST", "Test Target",
                                BitsValue.of(0, "Default"),
                                BitsValue.of(1, "Test Target")),
                        bit(I021_040_SAA, position(1, 4, 4), "SAA", "Selected Altitude Available",
                                BitsValue.of(0, "Equipment not capable to provide Selected Altitude"),
                                BitsValue.of(1, "Equipment capable to provide Selected Altitude")),
                        bits(I021_040_CL, position(1, 3, 2), "CL", "Confidence Level",
                                BitsValue.of(0, "Report valid"),
                                BitsValue.of(1, "Report suspect"),
                                BitsValue.of(2, "No information"),
                                BitsValue.of(3, "Reserved")),
                        fx(I021_040_FX2, position(1, 1, 1))),
                Part.of(I021_040_X2, 1,
                        spare(I021_040_SP8, position(1, 8, 8)),
                        bit(I021_040_LLC, position(1, 7, 7), "LLC", "List Lookup Check",
                                BitsValue.of(0, "Default"),
                                BitsValue.of(1, "List Lookup failed")),
                        bit(I021_040_IPC, position(1, 6, 6), "IPC", "Independent Position Check",
                                BitsValue.of(0, "Default"),
                                BitsValue.of(1, "Failed")),
                        bit(I021_040_NOGO, position(1, 5, 5), "NOGO", "No-go Bits Status",
                                BitsValue.of(0, "NOGO-bit not set"),
                                BitsValue.of(1, "NOGO-bit set")),
                        bit(I021_040_CPR, position(1, 4, 4), "CPR", "Compact Position Reporting",
                                BitsValue.of(0, "CPR Validation correct"),
                                BitsValue.of(1, "CPR Validation failed")),
                        bit(I021_040_LDPJ, position(1, 3, 3), "LDPJ", "Local Decoding Position Jump",
                                BitsValue.of(0, "LDPJ not detected"),
                                BitsValue.of(1, "LDPJ detected")),
                        bit(I021_040_RCF, position(1, 2, 2), "RCF", "Range Check",
                                BitsValue.of(0, "Default"),
                                BitsValue.of(1, "Range Check failed")),
                        fx(I021_040_FX3, position(1, 1, 1))));

        final DataItem i021_040 = DataItem.from(f021_040, "Target Report Descriptor", true,
                "Type and characteristics of the data as transmitted by a system");

        final DataFormat f021_070 = fixed(I021_070, 2,
                spare(I021_070_SP16, position(2, 16, 16)),
                spare(I021_070_SP15, position(2, 15, 15)),
                spare(I021_070_SP14, position(2, 14, 14)),
                spare(I021_070_SP13, position(2, 13, 13)),
                octal(I021_070_MODE3A, position(2, 12, 1), "MODE3A", "Mode-3/A Code"));

        final DataItem i021_070 = DataItem.from(f021_070, "Mode-3/A Code", false,
                "Mode-3/A code converted into octal representation");

        final DataFormat f021_071 = fixed(I021_071, 3,
                umeasure(I021_071_TAP, position(3, 24, 1), "TAP", 1.0 / (1 << 7), UnitOfMeasure.TIME_SECONDS, "Time of Applicability Position"));

        final DataItem i021_071 = DataItem.from(f021_071, "Time of Applicability for Position", false,
                "Time of applicability of the reported position, in the form of elapsed time since last midnight, expressed as UTC");

        final DataFormat f021_072 = fixed(I021_072, 3,
                umeasure(I021_072_TAV, position(3, 24, 1), "TAV", 1.0 / (1 << 7), UnitOfMeasure.TIME_SECONDS, "Time of Applicability for Velocity"));

        final DataItem i021_072 = DataItem.from(f021_072, "Time of Applicability for Velocity", false,
                "Time of applicability (measurement) of the reported velocity, in the form of elapsed time since last midnight, expressed as UTC");

        final DataFormat f021_073 = fixed(I021_073, 3,
                umeasure(I021_073_TOMRP, position(3, 24, 1), "TOMRP", 1.0 / (1 << 7), UnitOfMeasure.TIME_SECONDS, "Time of Message Reception for Position"));

        final DataItem i021_073 = DataItem.from(f021_073, "Time of Message Reception for Position", false,
                "Time of reception of the latest position squitter in the Ground Station, in the form of elapsed time since last midnight, expressed as UTC");

        final DataFormat f021_074 = fixed(I021_074, 4,
                bits(I021_074_FSI, position(4, 32, 31), "FSI", "Full Second Indication",
                        BitsValue.of(0, "TOMRp whole seconds =(I021/073) Whole seconds"),
                        BitsValue.of(1, "TOMRp whole seconds =(I021/073) Whole seconds + 1"),
                        BitsValue.of(2, "TOMRp whole seconds =(I021/073) Whole seconds - 1"),
                        BitsValue.of(3, "Reserved")),
                umeasure(I021_074_TOMRPH, position(4, 30, 1), "TOMRPH", 1.0 / (1 << 30), UnitOfMeasure.TIME_SECONDS, "Time of Message Reception of Position–High Precision"));

        final DataItem i021_074 = DataItem.from(f021_074, "Time of Message Reception for Position - High Precision", false,
                "Time at which the latest ADS-B position information was received by the Ground Station, expressed as fraction of the second of the UTC Time");

        final DataFormat f021_075 = fixed(I021_075, 3,
                umeasure(I021_075_TOMRV, position(3, 24, 1), "TOMRV", 1.0 / (1 << 7), UnitOfMeasure.TIME_SECONDS, "Time of Message Reception for Velocity"));

        final DataItem i021_075 = DataItem.from(f021_075, "Time of Message Reception for Velocity", false,
                "Time of reception of the latest velocity squitter in the Ground Station, in the form of elapsed time since last midnight, expressed as UTC");

        final DataFormat f021_076 = fixed(I021_076, 4,
                bits(I021_076_FSI, position(4, 32, 31), "FSI", "Full Second Indication",
                        BitsValue.of(0, "TOMRv whole seconds =(I021/073) Whole seconds"),
                        BitsValue.of(1, "TOMRv whole seconds =(I021/073) Whole seconds + 1"),
                        BitsValue.of(2, "TOMRv whole seconds =(I021/073) Whole seconds - 1"),
                        BitsValue.of(3, "Reserved")),
                umeasure(I021_076_TOMRVH, position(4, 30, 1), "TOMRVH", 1.0 / (1 << 30), UnitOfMeasure.TIME_SECONDS, "Time of Message Reception of Velocity–High Precision"));

        final DataItem i021_076 = DataItem.from(f021_076, "Time of Message Reception for Velocity - High Precision", false,
                "Time at which the latest ADS-B velocity information was received by the Ground Station, expressed as fraction of the second of the UTC Time");

        final DataFormat f021_077 = fixed(I021_077, 3,
                umeasure(I021_077_TRT, position(3, 24, 1), "TRT", 1.0 / 128.0, UnitOfMeasure.TIME_SECONDS, "Time of Report Transmission"));

        final DataItem i021_077 = DataItem.from(f021_077, "Time of Message Transmission", false,
                "Time of the transmission of the ASTERIX Category 021 report in the form of elapsed time since last midnight, expressed as UTC");

        final DataFormat f021_080 = fixed(I021_080, 3,
                hex(I021_080_ADR, position(3, 24, 1), "ADR", "Target Address"));

        final DataItem i021_080 = DataItem.from(f021_080, "Target Address", true,
                "Target address (emitter identifier) assigned uniquely to each target");

        final DataFormat f021_090 = extended(I021_090,
                Part.of(I021_090_FP, 1,
                        unsigned(I021_090_NUCRV, position(1, 8, 6), "NUCRV", "\"Navigation Uncertainty Category for Velocity\" NUCr or the \"Navigation Accuracy Category for Velocity\" NACv"),
                        unsigned(I021_090_NUCPN, position(1, 5, 2), "NUCPN", "\"Navigation Uncertainty Category for Position\" NUCp or \"Navigation Integrity Category\" \"NIC\""),
                        fx(I021_090_FX1, position(1, 1, 1))),
                Part.of(I021_090_X1, 1,
                        bit(I021_090_NICB, position(1, 8, 8), "NICB", "Navigation Integrity Category for Barometric Altitude",
                                BitsValue.of(0, "Uncertainty Category"),
                                BitsValue.of(1, "Accuracy Category")),
                        unsigned(I021_090_SIL, position(1, 7, 6), "SIL", "Surveillance (version 1) or Source (version 2) Integrity Level"),
                        unsigned(I021_090_NACP, position(1, 5, 2), "NACP", "Navigation Accuracy Category for Position"),
                        fx(I021_090_FX2, position(1, 1, 1))),
                Part.of(I021_090_X2, 1,
                        spare(I021_090_X2_SP8, position(1, 8, 8)),
                        spare(I021_090_X2_SP7, position(1, 7, 7)),
                        bit(I021_090_SILS, position(1, 6, 6), "SILS", "SIL-Supplement",
                                BitsValue.of(0, "Measured per flight-hour"),
                                BitsValue.of(1, "Measured per sample")),
                        unsigned(I021_090_SDA, position(1, 5, 4), "SDA", "Horizontal Position System Design Assurance Level (as defined in version 2)"),
                        unsigned(I021_090_GVA, position(1, 3, 2), "GVA", "Geometric Altitude Accuracy"),
                        fx(I021_090_FX3, position(1, 1, 1))),
                Part.of(I021_090_X3, 1,
                        unsigned(I021_090_PIC, position(1, 8, 5), "PIC", "Position Integrity Category"),
                        spare(I021_090_X3_SP4, position(1, 4, 4)),
                        spare(I021_090_X3_SP3, position(1, 3, 3)),
                        spare(I021_090_X3_SP2, position(1, 2, 2)),
                        fx(I021_090_FX4, position(1, 1, 1)))
        );

        final DataItem i021_090 = DataItem.from(f021_090, "Quality Indicators", true,
                "ADS-B quality indicators transmitted by a/c according to MOPS version");

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
                Subfield.of(f021_110_TIS, 1, 1, "TIS", "Trajectory Intent Status"),
                Subfield.of(f021_110_TID, 1, 2, "TID", "Trajectory Intent Data validity"),
                Subfield.sp(I021_110_SP6, 1, 3),
                Subfield.sp(I021_110_SP5, 1, 4),
                Subfield.sp(I021_110_SP4, 1, 5),
                Subfield.sp(I021_110_SP3, 1, 6),
                Subfield.sp(I021_110_SP2, 1, 7),
                Subfield.fx(I021_110_FX1, 1));

        final DataItem i021_110 = DataItem.from(f021_110, "Trajectory Intent", false,
                "Reports indicating the 4D intended trajectory of the aircraft");

        final DataFormat f021_130 = fixed(I021_130, 6,
                smeasure(I021_130_LAT, position(6, 48, 25), "LAT", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I021_130_LON, position(6, 24, 1), "LON", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataItem i021_130 = DataItem.from(f021_130, "Position in WGS84 Co-ordinates", false,
                "Position in WGS84 Co-ordinates");

        final DataFormat f021_131 = fixed(I021_131, 8,
                smeasure(I021_131_LAT, position(8, 64, 33), "LAT", 180.0 / (1 << 30), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I021_131_LON, position(8, 32, 1), "LON", 180.0 / (1 << 30), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataItem i021_131 = DataItem.from(f021_131, "Position in WGS-84 co-ordinates - High Resolution", false,
                "Position in WGS-84 Co-ordinates in high resolution");

        final DataFormat f021_132 = fixed(I021_132, 1,
                smeasure(I021_132_MAM, position(1, 8, 1), "MAM", 1.0, UnitOfMeasure.POWER_DBM, "Message Amplitude"));

        final DataItem i021_132 = DataItem.from(f021_132, "Message Amplitude", false,
                "Amplitude, in dBm, of ADS-B messages received by the ground  station, coded in two's complement");

        final DataFormat f021_140 = fixed(I021_140, 2,
                smeasure(I021_140_ALT, position(2, 16, 1), "ALT", 6.25, UnitOfMeasure.ALTITUDE_FT, -1500.0, 150000.0, "Geometric Altitude"));

        final DataItem i021_140 = DataItem.from(f021_140, "Geometric Height", false,
                "Minimum height from a plane tangent to the earth’s ellipsoid, defined by WGS-84, in two’s complement form");

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
                bit(I021_155_RE, position(2, 16, 16), "RE", "Range Exceeded Indicator",
                        BitsValue.of(0, "Value in defined range"),
                        BitsValue.of(1, "Value exceeds defined range")),
                smeasure(I021_155_BVR, position(2, 15, 1), "BVR", 6.25, UnitOfMeasure.SPEED_FT_MIN, "Barometric Vertical Rate"));

        final DataItem i021_155 = DataItem.from(f021_155, "Barometric Vertical Rate", false,
                "Barometric Vertical Rate, in two's complement form");

        final DataFormat f021_157 = fixed(I021_157, 2,
                bit(I021_157_RE, position(2, 16, 16), "RE", "Range Exceeded Indicator",
                        BitsValue.of(0, "Value in defined range"),
                        BitsValue.of(1, "Value exceeds defined range")),
                smeasure(I021_157_GVR, position(2, 15, 1), "GVR", 6.25, UnitOfMeasure.SPEED_FT_MIN, "Geometric Vertical Rate"));

        final DataItem i021_157 = DataItem.from(f021_157, "Geometric Vertical Rate", false,
                "Geometric Vertical Rate, in two's complement form, with reference to WGS84");

        final DataFormat f021_160 = fixed(I021_160, 4,
                bit(I021_160_RE, position(4, 32, 32), "RE", "Range Exceeded Indicator",
                        BitsValue.of(0, "Value in defined range"),
                        BitsValue.of(1, "Value exceeds defined range")),
                smeasure(I021_160_GS, position(4, 31, 17), "GS", 1.0 / (1 << 14), UnitOfMeasure.SPEED_NM_S, "Ground Speed"),
                umeasure(I021_160_TAN, position(4, 16, 1), "TAN", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Track Angle"));

        final DataItem i021_160 = DataItem.from(f021_160, "Ground Vector", false,
                "Ground Speed and Track Angle elements of Ground Vector");

        final DataFormat f021_161 = fixed(I021_161, 2,
                spare(I021_161_SP16, position(2, 16, 16)),
                spare(I021_161_SP15, position(2, 15, 15)),
                spare(I021_161_SP14, position(2, 14, 14)),
                spare(I021_161_SP13, position(2, 13, 13)),
                unsigned(I021_161_TRN, position(2, 12, 1), "TRN", "Track number"));

        final DataItem i021_161 = DataItem.from(f021_161, "Track ID", false,
                "An integer value representing a unique reference to a track record within a particular track file");

        final DataFormat f021_165 = fixed(I021_165, 2,
                spare(I021_165_SP16, position(2, 16, 16)),
                spare(I021_165_SP15, position(2, 15, 15)),
                spare(I021_165_SP14, position(2, 14, 14)),
                spare(I021_165_SP13, position(2, 13, 13)),
                spare(I021_165_SP12, position(2, 12, 12)),
                spare(I021_165_SP11, position(2, 11, 11)),
                smeasure(I021_165_TAR, position(2, 10, 1), "TAR", 1.0 / 32.0, UnitOfMeasure.ANGULAR_VELOCITY_DEGREES_S, "Track Angle Rate"));

        final DataItem i021_165 = DataItem.from(f021_165, "Track Angle Rate", false,
                "Rate of Turn, in two's complement form");

        final DataFormat f021_170 = fixed(I021_170, 6,
                ia5(I021_170_IDENT, position(6, 48, 1), "IDENT", "Target Identification"));

        final DataItem i021_170 = DataItem.from(f021_170, "Target Identification", false,
                "Target (aircraft or vehicle) identification in 8 characters, as reported by the target");

        final DataFormat f021_200 = fixed(I021_200, 1,
                bit(I021_200_ICF, position(1, 8, 8), "ICF", "Intent Change Flag",
                        BitsValue.of(0, "No intent change active"),
                        BitsValue.of(1, "Intent change flag raised")),
                bit(I021_200_LNAV, position(1, 7, 7), "LNAV", "LNAV Mode",
                        BitsValue.of(0, "LNAV Mode engaged"),
                        BitsValue.of(1, "LNAV Mode not engaged")),
                bit(I021_200_ME, position(1, 6, 6), "ME", "Military Emergency",
                        BitsValue.of(0, "No military emergency"),
                        BitsValue.of(1, "Military emergency")),
                bits(I021_200_PS, position(1, 5, 3), "PS", "Priority Status",
                        BitsValue.of(0, "No emergency/not reported"),
                        BitsValue.of(1, "General emergency"),
                        BitsValue.of(2, "Lifeguard/medical emergency"),
                        BitsValue.of(3, "Minimum fuel"),
                        BitsValue.of(4, "No communications"),
                        BitsValue.of(5, "Unlawful interference"),
                        BitsValue.of(6, "\"Downed\" Aircraft"),
                        BitsValue.of(7, "Reserved")),
                bits(I021_200_SS, position(1, 2, 1), "SS", "Surveillance Status",
                        BitsValue.of(0, "No condition reported"),
                        BitsValue.of(1, "Permanent Alert (Emergency condition)"),
                        BitsValue.of(2, "Temporary Alert (change in Mode-3/A Code other than emergency)"),
                        BitsValue.of(3, "SPI set")));

        final DataItem i021_200 = DataItem.from(f021_200, "Target Status", false,
                "Status of the target");

        final DataFormat f021_210 = fixed(I021_210, 1,
                spare(I021_210_SP8, position(1, 8, 8)),
                bit(I021_210_VNS, position(1, 7, 7), "VNS", "Version Not Supported",
                        BitsValue.of(0, "The MOPS Version is supported by the GS"),
                        BitsValue.of(1, "The MOPS Version is not supported by the GS")),
                bits(I021_210_VN, position(1, 6, 4), "VN", "Version Number This sub-field shall contain a value describing the MOPS used by each aircraft",
                        BitsValue.of(0, "ED102/DO-260 [Ref. 8]"),
                        BitsValue.of(1, "DO-260A [Ref. 9]"),
                        BitsValue.of(2, "ED102A/DO-260B [Ref. 11]"),
                        BitsValue.of(3, "ED-102B/DO-260C [Ref. 11]"),
                        BitsValue.of(4, "Not assigned"),
                        BitsValue.of(5, "Not assigned"),
                        BitsValue.of(6, "Not assigned"),
                        BitsValue.of(7, "Not assigned")),
                bits(I021_210_LTT, position(1, 3, 1), "LTT", "Link Technology Type",
                        BitsValue.of(0, "Other"),
                        BitsValue.of(1, "UAT"),
                        BitsValue.of(2, "1090 ES"),
                        BitsValue.of(3, "VDL 4"),
                        BitsValue.of(4, "Not assigned"),
                        BitsValue.of(5, "Not assigned"),
                        BitsValue.of(6, "Not assigned"),
                        BitsValue.of(7, "Not assigned")));

        final DataItem i021_210 = DataItem.from(f021_210, "MOPS Version", false,
                "Identification of the MOPS version used by a/c to supply ADS-B information");

        final DataFormat f021_220_WS = fixed(I021_220_WS, 2,
                umeasure(I021_220_WS_WS, position(2, 16, 1), "WS", 1.0, UnitOfMeasure.SPEED_KNOT, 300.0, "Wind Speed"));

        final DataFormat f021_220_WD = fixed(I021_220_WD, 2,
                umeasure(I021_220_WD_WD, position(2, 16, 1), "WD", 1.0, UnitOfMeasure.ANGLE_DEGREE, 360.0, "Wind Direction"));

        final DataFormat f021_220_TMP = fixed(I021_220_TMP, 2,
                smeasure(I021_220_TMP_TMP, position(2, 16, 1), "TMP", 0.25, UnitOfMeasure.TEMPERATURE_CELSIUS, -100.0, 100.0, "Temperature"));

        final DataFormat f021_220_TRB = fixed(I021_220_TRB, 1,
                unsigned(I021_220_TRB_TRB, position(1, 8, 1), "TRB", "Turbulence"));

        final DataFormat f021_220 = compound(I021_220,
                Subfield.of(f021_220_WS, 1, 1, "WS", "Wind Speed"),
                Subfield.of(f021_220_WD, 1, 2, "WD", "Wind Direction"),
                Subfield.of(f021_220_TMP, 1, 3, "TMP", "Temperature"),
                Subfield.of(f021_220_TRB, 1, 4, "TRB", "Turbulence"),
                Subfield.sp(I021_220_SP4, 1, 5),
                Subfield.sp(I021_220_SP3, 1, 6),
                Subfield.sp(I021_220_SP2, 1, 7),
                Subfield.fx(I021_220_FX1, 1));

        final DataItem i021_220 = DataItem.from(f021_220, "Met Information", false,
                "Meteorological information");

        final DataFormat f021_230 = fixed(I021_230, 2,
                smeasure(I021_230_RAN, position(2, 16, 1), "RAN", 0.01, UnitOfMeasure.ANGLE_DEGREE, -180.0, 180.0, "Roll Angle"));

        final DataItem i021_230 = DataItem.from(f021_230, "Roll Angle", false,
                "The roll angle, in two's complement form, of an aircraft executing a turn");

        final DataFormat f021_250 = mbdata(I021_250,
                hex(I021_250_MBDATA, position(8, 64, 9), "MBDATA", "Mode S Comm B Message Data"),
                unsigned(I021_250_BDS1, position(8, 8, 5), "BDS1", "Comm B Data Buffer Store 1 Address"),
                unsigned(I021_250_BDS2, position(8, 4, 1), "BDS2", "Comm B Data Buffer Store 2 Address"));

        final DataItem i021_250 = DataItem.from(f021_250, "Mode-S MB Data", false,
                "Mode-S Comm B Data as extracted from the aircraft transponder");

        final DataFormat f021_260 = fixed(I021_260, 7,
                hex(I021_260_BDS30, position(7, 56, 49), "BDS30", "BDS Code 3,0"),
                bit(I021_260_ARA41, position(7, 48, 48), "ARA41", "Active Resolution Advisories bit 41",
                        BitsValue.of(0, "There is more than one threat and the RA is intended to provide separation below some threat(s) and above some other threat(s) or no RA has been generated (when MTE = 0)"),
                        BitsValue.of(1, "Either there is only one threat or the RA is intended to provide separation in the same direction for all threats")),
                bit(I021_260_ARA42, position(7, 47, 47), "ARA42", "Active Resolution Advisories bit 42",
                        BitsValue.of(0, "RA is preventive or RA does not require a correction in the upward sense"),
                        BitsValue.of(1, "RA is corrective or RA requires a correction in the upward sense")),
                bit(I021_260_ARA43, position(7, 46, 46), "ARA43", "Active Resolution Advisories bit 43",
                        BitsValue.of(0, "Upward sense RA has been generated or RA does not require a positive climb"),
                        BitsValue.of(1, "Downward sense RA has been generated or RA requires a positive climb")),
                bit(I021_260_ARA44, position(7, 45, 45), "ARA44", "Active Resolution Advisories bit 44",
                        BitsValue.of(0, "RA is not increased rate or RA does not require a correction in the downward sense"),
                        BitsValue.of(1, "RA is increased rate or RA requires a correction in the downward sense")),
                bit(I021_260_ARA45, position(7, 44, 44), "ARA45", "Active Resolution Advisories bit 45",
                        BitsValue.of(0, "RA is not a sense reversal or RA does not require a positive descend"),
                        BitsValue.of(1, "RA is a sense reversal or RA requires a positive descend")),
                bit(I021_260_ARA46, position(7, 43, 43), "ARA46", "Active Resolution Advisories bit 46",
                        BitsValue.of(0, "RA is not altitude crossing or RA does not require a crossing"),
                        BitsValue.of(1, "RA is altitude crossing or RA requires a crossing")),
                bit(I021_260_ARA47, position(7, 42, 42), "ARA47", "Active Resolution Advisories bit 47",
                        BitsValue.of(0, "RA is vertical speed limit or RA is not a sense reversal"),
                        BitsValue.of(1, "RA is positive or RA is a sense reversal")),
                unsigned(I021_260_ACAS3, position(7, 41, 35), "ACAS3", "Reserved for ACAS III"),
                bit(I021_260_RAC55, position(7, 34, 34), "RAC55", "Resolution Advisory Complement bit 55",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not pass below")),
                bit(I021_260_RAC56, position(7, 33, 33), "RAC56", "Resolution Advisory Complement bit 56",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not pass above")),
                bit(I021_260_RAC57, position(7, 32, 32), "RAC57", "Resolution Advisory Complement bit 57",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not turn left")),
                bit(I021_260_RAC58, position(7, 31, 31), "RAC58", "Resolution Advisory Complement bit 58",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not turn right")),
                bit(I021_260_RAT, position(7, 30, 30), "RAT", "RA Terminated",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bit(I021_260_MTE, position(7, 29, 29), "MTE", "Multiple Threat Encounter",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bits(I021_260_TTI, position(7, 28, 27), "TTI", "Threat Type Indicator",
                        BitsValue.of(0, "No identity data in TID"),
                        BitsValue.of(1, "TID contains a Mode-S transponder address"),
                        BitsValue.of(2, "TID contains altitude, range and bearing data"),
                        BitsValue.of(3, "Not assigned")),
                unsigned(I021_260_TID, position(7, 26, 1), "TID", "Threat Identity Data"));

        final DataItem i021_260 = DataItem.from(f021_260, "ACAS Resolution Advisory Report", false,
                "Currently active Resolution Advisory (RA), if any, generated by the ACAS associated with the transponder transmitting the report and threat identity data");

        final DataFormat f021_271 = extended(I021_271,
                Part.of(I021_271_FP, 1,
                        spare(I021_271_FP_SP8, position(1, 8, 8)),
                        spare(I021_271_FP_SP7, position(1, 7, 7)),
                        bit(I021_271_POA, position(1, 6, 6), "POA", "Position Offset Applied",
                                BitsValue.of(0, "Position transmitted is not ADS-B position reference point"),
                                BitsValue.of(1, "Position transmitted is the ADS-B position reference point")),
                        bit(I021_271_CDTIS, position(1, 5, 5), "CDTIS", "Cockpit Display of Traffic Information Surface",
                                BitsValue.of(0, "CDTI not operational"),
                                BitsValue.of(1, "CDTI operational")),
                        bit(I021_271_B2LOW, position(1, 4, 4), "B2LOW", "Class B2 transmit power less than 70 Watts",
                                BitsValue.of(0, ">= 70 Watts"),
                                BitsValue.of(1, "< 70 Watts")),
                        bit(I021_271_RAS, position(1, 3, 3), "RAS", "Receiving ATC Services",
                                BitsValue.of(0, "Aircraft not receiving ATC-services"),
                                BitsValue.of(1, "Aircraft receiving ATC services")),
                        bit(I021_271_IDENT, position(1, 2, 2), "IDENT", "Setting of \"IDENT\"-switch",
                                BitsValue.of(0, "IDENT switch not active"),
                                BitsValue.of(1, "IDENT switch active")),
                        fx(I021_271_FX1, position(1, 1, 1))),
                Part.of(I021_271_X1, 1,
                        unsigned(I021_271_LW, position(1, 8, 5), "LW", "Length and width of aircraft"),
                        spare(I021_271_X1_SP4, position(1, 4, 4)),
                        spare(I021_271_X1_SP3, position(1, 3, 3)),
                        spare(I021_271_X1_SP2, position(1, 2, 2)),
                        fx(I021_271_FX2, position(1, 1, 1))));

        final DataItem i021_271 = DataItem.from(f021_271, "Surface Capabilities and Characteristics", false,
                "Operational capabilities of the aircraft while on the ground");

        final DataFormat f021_295_AOS = fixed(I021_295_AOS, 1,
                umeasure(I021_295_AOS_AOS, position(1, 8, 1), "AOS", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the latest received information transmitted in Data Item I021/008"));

        final DataFormat f021_295_TRD = fixed(I021_295_TRD, 1,
                umeasure(I021_295_TRD_TRD, position(1, 8, 1), "TRD", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the last update of the Target Report Descriptor in Data Item I021/040"));

        final DataFormat f021_295_M3A = fixed(I021_295_M3A, 1,
                umeasure(I021_295_M3A_M3A, position(1, 8, 1), "M3A", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the last update of the Mode-3/A Code in Data Item I021/070"));

        final DataFormat f021_295_QI = fixed(I021_295_QI, 1,
                umeasure(I021_295_QI_QI, position(1, 8, 1), "QI", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the latest information received to update the Quality Indicators in Data Item I021/090"));

        final DataFormat f021_295_TI = fixed(I021_295_TI, 1,
                umeasure(I021_295_TI_TI, position(1, 8, 1), "TI", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the last update of the Trajectory Intent information updating Data Item I021/110"));

        final DataFormat f021_295_MAM = fixed(I021_295_MAM, 1,
                umeasure(I021_295_MAM_MAM, position(1, 8, 1), "MAM", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the latest measurement of the Message Amplitude in Data Item I021/132"));

        final DataFormat f021_295_GH = fixed(I021_295_GH, 1,
                umeasure(I021_295_GH_GH, position(1, 8, 1), "GH", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the information contained in Data Item I021/140"));

        final DataFormat f021_295_FL = fixed(I021_295_FL, 1,
                umeasure(I021_295_FL_FL, position(1, 8, 1), "FL", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Flight Level information in Data Item I021/145"));

        final DataFormat f021_295_ISA = fixed(I021_295_ISA, 1,
                umeasure(I021_295_ISA_ISA, position(1, 8, 1), "ISA", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Intermediate State Selected Altitude in Data Item I021/146"));

        final DataFormat f021_295_FSA = fixed(I021_295_FSA, 1,
                umeasure(I021_295_FSA_FSA, position(1, 8, 1), "FSA", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Final State Selected Altitude in item I021/148"));

        final DataFormat f021_295_AS = fixed(I021_295_AS, 1,
                umeasure(I021_295_AS_AS, position(1, 8, 1), "AS", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Air Speed contained in Data Item I021/150"));

        final DataFormat f021_295_TAS = fixed(I021_295_TAS, 1,
                umeasure(I021_295_TAS_TAS, position(1, 8, 1), "TAS", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the value for the True Air Speed in Data Item I021/151"));

        final DataFormat f021_295_MH = fixed(I021_295_MH, 1,
                umeasure(I021_295_MH_MH, position(1, 8, 1), "MH", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the value for the Magnetic Heading in Data Item I021/152"));

        final DataFormat f021_295_BVR = fixed(I021_295_BVR, 1,
                umeasure(I021_295_BVR_BVR, position(1, 8, 1), "BVR", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Barometric Vertical Rate contained in Data Item I021/155"));

        final DataFormat f021_295_GVR = fixed(I021_295_GVR, 1,
                umeasure(I021_295_GVR_GVR, position(1, 8, 1), "GVR", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Geometric Vertical Rate in Data Item I021/157"));

        final DataFormat f021_295_GV = fixed(I021_295_GV, 1,
                umeasure(I021_295_GV_GV, position(1, 8, 1), "GV", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Ground Vector in Data Item I021/160"));

        final DataFormat f021_295_TAR = fixed(I021_295_TAR, 1,
                umeasure(I021_295_TAR_TAR, position(1, 8, 1), "TAR", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of Data Item I021/165 Track Angle Rate"));

        final DataFormat f021_295_TID = fixed(I021_295_TID, 1,
                umeasure(I021_295_TID_TID, position(1, 8, 1), "TID", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Target Identification in Data Item I021/170"));

        final DataFormat f021_295_TS = fixed(I021_295_TS, 1,
                umeasure(I021_295_TS_TS, position(1, 8, 1), "TS", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Target Status as contained in Data Item I021/200"));

        final DataFormat f021_295_MET = fixed(I021_295_MET, 1,
                umeasure(I021_295_MET_MET, position(1, 8, 1), "MET", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Meteorological data contained in Data Item I021/220"));

        final DataFormat f021_295_ROA = fixed(I021_295_ROA, 1,
                umeasure(I021_295_ROA_ROA, position(1, 8, 1), "ROA", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the Roll Angle value as in Data Item I021/230"));

        final DataFormat f021_295_ARA = fixed(I021_295_ARA, 1,
                umeasure(I021_295_ARA_ARA, position(1, 8, 1), "ARA", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the latest update of an active ACAS Resolution Advisory"));

        final DataFormat f021_295_SCC = fixed(I021_295_SCC, 1,
                umeasure(I021_295_SCC_SCC, position(1, 8, 1), "SCC", 0.1, UnitOfMeasure.TIME_SECONDS, 25.5,
                        "Age of the latest information received on the surface capabilities and characteristics of the respective target"));

        final DataFormat f021_295 = compound(I021_295,
                Subfield.of(f021_295_AOS, 4, 1, "AOS", "Aircraft Operational Status age"),
                Subfield.of(f021_295_TRD, 4, 2, "TRD", "Target Report Descriptor age"),
                Subfield.of(f021_295_M3A, 4, 3, "M3A", "Mode-3/A Code age"),
                Subfield.of(f021_295_QI, 4, 4, "QI", "Quality Indicators age"),
                Subfield.of(f021_295_TI, 4, 5, "TI", "Trajectory Intent age"),
                Subfield.of(f021_295_MAM, 4, 6, "MAM", "Message Amplitude age"),
                Subfield.of(f021_295_GH, 4, 7, "GH", "Geometric Height age"),
                Subfield.fx(I021_295_FX1, 4),
                Subfield.of(f021_295_FL, 4, 8, "FL", "Flight Level age"),
                Subfield.of(f021_295_ISA, 4, 9, "ISA", "Intermediate State Selected Altitude age"),
                Subfield.of(f021_295_FSA, 4, 10, "FSA", "Final State Selected Altitude age"),
                Subfield.of(f021_295_AS, 4, 11, "AS", "Air Speed age"),
                Subfield.of(f021_295_TAS, 4, 12, "TAS", "True Air Speed age"),
                Subfield.of(f021_295_MH, 4, 13, "MH", "Magnetic Heading age"),
                Subfield.of(f021_295_BVR, 4, 14, "BVR", "Barometric Vertical Rate age"),
                Subfield.fx(I021_295_FX2, 4),
                Subfield.of(f021_295_GVR, 4, 15, "GVR", "Geometric Vertical Rate age"),
                Subfield.of(f021_295_GV, 4, 16, "GV", "Ground Vector age"),
                Subfield.of(f021_295_TAR, 4, 17, "TAR", "Track Angle Rate age"),
                Subfield.of(f021_295_TID, 4, 18, "TID", "Target Identification age"),
                Subfield.of(f021_295_TS, 4, 19, "TS", "Target Status age"),
                Subfield.of(f021_295_MET, 4, 20, "MET", "Met. Information age"),
                Subfield.of(f021_295_ROA, 4, 21, "ROA", "Roll Angle age"),
                Subfield.fx(I021_295_FX3, 4),
                Subfield.of(f021_295_ARA, 1, 22, "ARA", "ACAS Resolution Advisory age"),
                Subfield.of(f021_295_SCC, 1, 23, "SCC", "Surface Capabilities and Characteristics age"),
                Subfield.sp(I021_295_SP1, 4, 24),
                Subfield.sp(I021_295_SP2, 4, 25),
                Subfield.sp(I021_295_SP3, 4, 26),
                Subfield.sp(I021_295_SP4, 4, 27),
                Subfield.sp(I021_295_SP5, 4, 28),
                Subfield.fx(I021_295_FX4, 4));

        final DataItem i021_295 = DataItem.from(f021_295, "Data Ages", false, "Ages of the data provided");

        final DataFormat f021_400 = fixed(I021_400, 1,
                unsigned(I021_400_RID, position(1, 8, 1), "RID", "Receiver ID"));

        final DataItem i021_400 = DataItem.from(f021_400, "Receiver ID", false,
                "Designator of Ground Station in Distributed System");

        final DataItem i021_FX1 = DataItem.fx(CAT021_ID, 7, 49);
        final DataItem i021_FX2 = DataItem.fx(CAT021_ID, 7, 41);
        final DataItem i021_FX3 = DataItem.fx(CAT021_ID, 7, 33);
        final DataItem i021_FX4 = DataItem.fx(CAT021_ID, 7, 25);
        final DataItem i021_FX5 = DataItem.fx(CAT021_ID, 7, 17);
        final DataItem i021_FX6 = DataItem.fx(CAT021_ID, 7, 9);
        final DataItem i021_SP1 = DataItem.sp(CAT021_ID, 7, 8);
        final DataItem i021_SP2 = DataItem.sp(CAT021_ID, 7, 7);
        final DataItem i021_SP3 = DataItem.sp(CAT021_ID, 7, 6);
        final DataItem i021_SP4 = DataItem.sp(CAT021_ID, 7, 5);
        final DataItem i021_SP5 = DataItem.sp(CAT021_ID, 7, 4);
        final DataItem i021_RE_ = DataItem.re(I021_RE);
        final DataItem i021_SP_ = DataItem.sp(I021_SP);
        final DataItem i021_FX7 = DataItem.fx(CAT021_ID, 7, 1);

        final UserApplicationProfile uap = new UserApplicationProfile(CAT021_ID,
                i021_010, // 56
                i021_040, // 55
                i021_161, // 54
                i021_015, // 53
                i021_071, // 52
                i021_130, // 51
                i021_131, // 50
                i021_FX1, // 49
                i021_072, // 48
                i021_150, // 47
                i021_151, // 46
                i021_080, // 45
                i021_073, // 44
                i021_074, // 43
                i021_075, // 42
                i021_FX2, // 41
                i021_076, // 40
                i021_140, // 39
                i021_090, // 38
                i021_210, // 37
                i021_070, // 36
                i021_230, // 35
                i021_145, // 34
                i021_FX3, // 33
                i021_152, // 32
                i021_200, // 31
                i021_155, // 30
                i021_157, // 29
                i021_160, // 28
                i021_165, // 27
                i021_077, // 26
                i021_FX4, // 25
                i021_170, // 24
                i021_020, // 23
                i021_220, // 22
                i021_146, // 21
                i021_148, // 20
                i021_110, // 19
                i021_016, // 18
                i021_FX5, // 17
                i021_008, // 16
                i021_271, // 15
                i021_132, // 14
                i021_250, // 13
                i021_260, // 12
                i021_400, // 11
                i021_295, // 10
                i021_FX6, //  9
                i021_SP1, //  8
                i021_SP2, //  7
                i021_SP3, //  6
                i021_SP4, //  5
                i021_SP5, //  4
                i021_RE_, //  3
                i021_SP_, //  2
                i021_FX7);//  1

        return new ASTERIXCategory("ADS-B Messages", uap);
    }
}
