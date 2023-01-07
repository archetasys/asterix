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

public final class Cat048V1Dot30 {
    public static final long CAT048_ID = categoryId(48, 1, 30);
    public static final long I048_010;
    public static final long I048_010_SAC;
    public static final long I048_010_SIC;
    public static final long I048_020;
    public static final long I048_020_TYP;
    public static final long I048_020_SIM;
    public static final long I048_020_RDP;
    public static final long I048_020_SPI;
    public static final long I048_020_RAB;
    public static final long I048_020_FX1;
    public static final long I048_020_TST;
    public static final long I048_020_ERR;
    public static final long I048_020_XPP;
    public static final long I048_020_ME;
    public static final long I048_020_MI;
    public static final long I048_020_FOEFRI;
    public static final long I048_020_FX2;
    public static final long I048_030;
    public static final long I048_030_CODE;
    public static final long I048_030_FX1;
    public static final long I048_040;
    public static final long I048_040_RHO;
    public static final long I048_040_THETA;
    public static final long I048_042;
    public static final long I048_042_X;
    public static final long I048_042_Y;
    public static final long I048_050;
    public static final long I048_050_V;
    public static final long I048_050_G;
    public static final long I048_050_L;
    public static final long I048_050_SP13;
    public static final long I048_050_MODE2;
    public static final long I048_055;
    public static final long I048_055_V;
    public static final long I048_055_G;
    public static final long I048_055_L;
    public static final long I048_055_MODE1;
    public static final long I048_060;
    public static final long I048_060_SP16;
    public static final long I048_060_SP15;
    public static final long I048_060_SP14;
    public static final long I048_060_SP13;
    public static final long I048_060_QA4;
    public static final long I048_060_QA2;
    public static final long I048_060_QA1;
    public static final long I048_060_QB4;
    public static final long I048_060_QB2;
    public static final long I048_060_QB1;
    public static final long I048_060_QC4;
    public static final long I048_060_QC2;
    public static final long I048_060_QC1;
    public static final long I048_060_QD4;
    public static final long I048_060_QD2;
    public static final long I048_060_QD1;
    public static final long I048_065;
    public static final long I048_065_SP8;
    public static final long I048_065_SP7;
    public static final long I048_065_SP6;
    public static final long I048_065_QA4;
    public static final long I048_065_QA2;
    public static final long I048_065_QA1;
    public static final long I048_065_QB2;
    public static final long I048_065_QB1;
    public static final long I048_070;
    public static final long I048_070_V;
    public static final long I048_070_G;
    public static final long I048_070_L;
    public static final long I048_070_SP13;
    public static final long I048_070_MODE3A;
    public static final long I048_080;
    public static final long I048_080_SP16;
    public static final long I048_080_SP15;
    public static final long I048_080_SP14;
    public static final long I048_080_SP13;
    public static final long I048_080_QA4;
    public static final long I048_080_QA2;
    public static final long I048_080_QA1;
    public static final long I048_080_QB4;
    public static final long I048_080_QB2;
    public static final long I048_080_QB1;
    public static final long I048_080_QC4;
    public static final long I048_080_QC2;
    public static final long I048_080_QC1;
    public static final long I048_080_QD4;
    public static final long I048_080_QD2;
    public static final long I048_080_QD1;
    public static final long I048_090;
    public static final long I048_090_V;
    public static final long I048_090_G;
    public static final long I048_090_FL;
    public static final long I048_100;
    public static final long I048_100_V;
    public static final long I048_100_G;
    public static final long I048_100_SP30;
    public static final long I048_100_SP29;
    public static final long I048_100_MODEC;
    public static final long I048_100_SP16;
    public static final long I048_100_SP15;
    public static final long I048_100_SP14;
    public static final long I048_100_SP13;
    public static final long I048_100_QC1;
    public static final long I048_100_QA1;
    public static final long I048_100_QC2;
    public static final long I048_100_QA2;
    public static final long I048_100_QC4;
    public static final long I048_100_QA4;
    public static final long I048_100_QB1;
    public static final long I048_100_QD1;
    public static final long I048_100_QB2;
    public static final long I048_100_QD2;
    public static final long I048_100_QB4;
    public static final long I048_100_QD4;
    public static final long I048_110;
    public static final long I048_110_SP16;
    public static final long I048_110_SP15;
    public static final long I048_110_3DH;
    public static final long I048_120;
    public static final long I048_120_CAL_D;
    public static final long I048_120_CAL_SP15;
    public static final long I048_120_CAL_SP14;
    public static final long I048_120_CAL_SP13;
    public static final long I048_120_CAL_SP12;
    public static final long I048_120_CAL_SP11;
    public static final long I048_120_CAL_CAL;
    public static final long I048_120_RDS_DOP;
    public static final long I048_120_RDS_AMB;
    public static final long I048_120_RDS_FRQ;
    public static final long I048_130;
    public static final long I048_130_SRL_SRL;
    public static final long I048_130_SRR_SRR;
    public static final long I048_130_SAM_SAM;
    public static final long I048_130_PRL_PRL;
    public static final long I048_130_PAM_PAM;
    public static final long I048_130_RPD_RPD;
    public static final long I048_130_APD_APD;
    public static final long I048_140;
    public static final long I048_140_TOD;
    public static final long I048_161;
    public static final long I048_161_SP16;
    public static final long I048_161_SP15;
    public static final long I048_161_SP14;
    public static final long I048_161_SP13;
    public static final long I048_161_TRN;
    public static final long I048_170;
    public static final long I048_170_CNF;
    public static final long I048_170_RAD;
    public static final long I048_170_DOU;
    public static final long I048_170_MAH;
    public static final long I048_170_CDM;
    public static final long I048_170_FX1;
    public static final long I048_170_TRE;
    public static final long I048_170_GHO;
    public static final long I048_170_SUP;
    public static final long I048_170_TCC;
    public static final long I048_170_SP4;
    public static final long I048_170_SP3;
    public static final long I048_170_SP2;
    public static final long I048_170_FX2;
    public static final long I048_200;
    public static final long I048_200_GSP;
    public static final long I048_200_HDG;
    public static final long I048_210;
    public static final long I048_210_SIGX;
    public static final long I048_210_SIGY;
    public static final long I048_210_SIGV;
    public static final long I048_210_SIGH;
    public static final long I048_220;
    public static final long I048_220_ADDR;
    public static final long I048_230;
    public static final long I048_230_COM;
    public static final long I048_230_STAT;
    public static final long I048_230_SI;
    public static final long I048_230_SP9;
    public static final long I048_230_MSSC;
    public static final long I048_230_ARC;
    public static final long I048_230_AIC;
    public static final long I048_230_B1A;
    public static final long I048_230_B1B;
    public static final long I048_240;
    public static final long I048_240_IDENT;
    public static final long I048_250;
    public static final long I048_250_MBDATA;
    public static final long I048_250_BDS1;
    public static final long I048_250_BDS2;
    public static final long I048_260;
    public static final long I048_260_BDS30;
    public static final long I048_260_ARA41;
    public static final long I048_260_ARA42;
    public static final long I048_260_ARA43;
    public static final long I048_260_ARA44;
    public static final long I048_260_ARA45;
    public static final long I048_260_ARA46;
    public static final long I048_260_ARA47;
    public static final long I048_260_ACAS3;
    public static final long I048_260_RAC55;
    public static final long I048_260_RAC56;
    public static final long I048_260_RAC57;
    public static final long I048_260_RAC58;
    public static final long I048_260_RAT;
    public static final long I048_260_MTE;
    public static final long I048_260_TTI;
    public static final long I048_260_TID;
    public static final long I048_RE;
    public static final long I048_RE_MD5_SUM_M5;
    public static final long I048_RE_MD5_SUM_ID;
    public static final long I048_RE_MD5_SUM_DA;
    public static final long I048_RE_MD5_SUM_M1;
    public static final long I048_RE_MD5_SUM_M2;
    public static final long I048_RE_MD5_SUM_M3;
    public static final long I048_RE_MD5_SUM_MC;
    public static final long I048_RE_MD5_SUM_SP1;
    public static final long I048_RE_MD5_PMN_SP32;
    public static final long I048_RE_MD5_PMN_SP31;
    public static final long I048_RE_MD5_PMN_PIN;
    public static final long I048_RE_MD5_PMN_SP16;
    public static final long I048_RE_MD5_PMN_SP15;
    public static final long I048_RE_MD5_PMN_NAV;
    public static final long I048_RE_MD5_PMN_NAT;
    public static final long I048_RE_MD5_PMN_SP8;
    public static final long I048_RE_MD5_PMN_SP7;
    public static final long I048_RE_MD5_PMN_MIS;
    public static final long I048_RE_MD5_POS_LAT;
    public static final long I048_RE_MD5_POS_LON;
    public static final long I048_RE_MD5_GA_SP16;
    public static final long I048_RE_MD5_GA_RES;
    public static final long I048_RE_MD5_GA_ALT;
    public static final long I048_RE_MD5_EM1_V;
    public static final long I048_RE_MD5_EM1_G;
    public static final long I048_RE_MD5_EM1_L;
    public static final long I048_RE_MD5_EM1_SP13;
    public static final long I048_RE_MD5_EM1_MODE1;
    public static final long I048_RE_MD5_TOS_TOS;
    public static final long I048_RE_MD5_XP_SP8;
    public static final long I048_RE_MD5_XP_SP7;
    public static final long I048_RE_MD5_XP_XP;
    public static final long I048_RE_MD5_XP_X5;
    public static final long I048_RE_MD5_XP_XC;
    public static final long I048_RE_MD5_XP_X3;
    public static final long I048_RE_MD5_XP_X2;
    public static final long I048_RE_MD5_XP_X1;
    public static final long I048_RE_M5N_SUM_M5;
    public static final long I048_RE_M5N_SUM_ID;
    public static final long I048_RE_M5N_SUM_DA;
    public static final long I048_RE_M5N_SUM_M1;
    public static final long I048_RE_M5N_SUM_M2;
    public static final long I048_RE_M5N_SUM_M3;
    public static final long I048_RE_M5N_SUM_MC;
    public static final long I048_RE_M5N_SUM_SP1;
    public static final long I048_RE_M5N_PMN_SP32;
    public static final long I048_RE_M5N_PMN_SP31;
    public static final long I048_RE_M5N_PMN_PIN;
    public static final long I048_RE_M5N_PMN_SP16;
    public static final long I048_RE_M5N_PMN_SP15;
    public static final long I048_RE_M5N_PMN_SP14;
    public static final long I048_RE_M5N_PMN_SP13;
    public static final long I048_RE_M5N_PMN_NOV;
    public static final long I048_RE_M5N_PMN_NO;
    public static final long I048_RE_M5N_POS_LAT;
    public static final long I048_RE_M5N_POS_LON;
    public static final long I048_RE_M5N_GA_SP16;
    public static final long I048_RE_M5N_GA_RES;
    public static final long I048_RE_M5N_GA_ALT;
    public static final long I048_RE_M5N_EM1_V;
    public static final long I048_RE_M5N_EM1_G;
    public static final long I048_RE_M5N_EM1_L;
    public static final long I048_RE_M5N_EM1_SP13;
    public static final long I048_RE_M5N_EM1_MODE1;
    public static final long I048_RE_M5N_TOS_TOS;
    public static final long I048_RE_M5N_XP_SP8;
    public static final long I048_RE_M5N_XP_SP7;
    public static final long I048_RE_M5N_XP_XP;
    public static final long I048_RE_M5N_XP_X5;
    public static final long I048_RE_M5N_XP_XC;
    public static final long I048_RE_M5N_XP_X3;
    public static final long I048_RE_M5N_XP_X2;
    public static final long I048_RE_M5N_XP_X1;
    public static final long I048_RE_M5N_FOM_SP8;
    public static final long I048_RE_M5N_FOM_SP7;
    public static final long I048_RE_M5N_FOM_SP6;
    public static final long I048_RE_M5N_FOM_FOM;
    public static final long I048_RE_M4E_SP8;
    public static final long I048_RE_M4E_SP7;
    public static final long I048_RE_M4E_SP6;
    public static final long I048_RE_M4E_SP5;
    public static final long I048_RE_M4E_SP4;
    public static final long I048_RE_M4E_FOEFRI;
    public static final long I048_RE_M4E_FX1;
    public static final long I048_RE_RPC_SCO_SCO;
    public static final long I048_RE_RPC_SCR_SCR;
    public static final long I048_RE_RPC_RW_RW;
    public static final long I048_RE_RPC_AR_AR;
    public static final long I048_SP;

    static final ASTERIXCategory CAT048;

    private static final long I048_020_FP;
    private static final long I048_020_X1;
    private static final long I048_030_FP;

    private static final long I048_120_CAL;
    private static final long I048_120_RDS;
    private static final long I048_120_SP6;
    private static final long I048_120_SP5;
    private static final long I048_120_SP4;
    private static final long I048_120_SP3;
    private static final long I048_120_SP2;
    private static final long I048_120_FX1;

    private static final long I048_130_SRL;
    private static final long I048_130_SRR;
    private static final long I048_130_SAM;
    private static final long I048_130_PRL;
    private static final long I048_130_PAM;
    private static final long I048_130_RPD;
    private static final long I048_130_APD;
    private static final long I048_130_FX1;

    private static final long I048_170_FP;
    private static final long I048_170_X1;

    private static final long I048_RE_MD5;
    private static final long I048_RE_MD5_SUM;
    private static final long I048_RE_MD5_PMN;
    private static final long I048_RE_MD5_POS;
    private static final long I048_RE_MD5_GA;
    private static final long I048_RE_MD5_EM1;
    private static final long I048_RE_MD5_TOS;
    private static final long I048_RE_MD5_XP;
    private static final long I048_RE_M5N;
    private static final long I048_RE_MD5_FX1;
    private static final long I048_RE_M5N_SUM;
    private static final long I048_RE_M5N_PMN;
    private static final long I048_RE_M5N_POS;
    private static final long I048_RE_M5N_GA;
    private static final long I048_RE_M5N_EM1;
    private static final long I048_RE_M5N_TOS;
    private static final long I048_RE_M5N_XP;
    private static final long I048_RE_M5N_FX1;
    private static final long I048_RE_M5N_FOM;
    private static final long I048_RE_M5N_SP7;
    private static final long I048_RE_M5N_SP6;
    private static final long I048_RE_M5N_SP5;
    private static final long I048_RE_M5N_SP4;
    private static final long I048_RE_M5N_SP3;
    private static final long I048_RE_M5N_SP2;
    private static final long I048_RE_M5N_FX2;
    private static final long I048_RE_M4E;
    private static final long I048_RE_M4E_FP;
    private static final long I048_RE_RPC;
    private static final long I048_RE_RPC_SCO;
    private static final long I048_RE_RPC_SCR;
    private static final long I048_RE_RPC_RW;
    private static final long I048_RE_RPC_AR;
    private static final long I048_RE_RPC_SP4;
    private static final long I048_RE_RPC_SP3;
    private static final long I048_RE_RPC_SP2;
    private static final long I048_RE_RPC_FX1;
    private static final long I048_RE_ERR;
    private static final long I048_RE_ERR_RHO;
    private static final long I048_RE_SP3;
    private static final long I048_RE_SP2;
    private static final long I048_RE_SP1;

    static {
        I048_010 = dataItemId(CAT048_ID, 10, DATA_FORMAT_FIXED);
        I048_010_SAC = bitsFieldId(I048_010, 16, BITS_FIELD_ENCODING_UNSIGNED);
        I048_010_SIC = bitsFieldId(I048_010, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I048_020 = dataItemId(CAT048_ID, 20, DATA_FORMAT_EXTENDED);
        I048_020_FP = subAId(I048_020, 0, DATA_FORMAT_FIXED);
        I048_020_TYP = bitsFieldId(I048_020_FP, 8, BITS_FIELD_ENCODING_VALUES);
        I048_020_SIM = bitsFieldId(I048_020_FP, 5, BITS_FIELD_ENCODING_VALUE);
        I048_020_RDP = bitsFieldId(I048_020_FP, 4, BITS_FIELD_ENCODING_VALUE);
        I048_020_SPI = bitsFieldId(I048_020_FP, 3, BITS_FIELD_ENCODING_VALUE);
        I048_020_RAB = bitsFieldId(I048_020_FP, 2, BITS_FIELD_ENCODING_VALUE);
        I048_020_FX1 = bitsFieldId(I048_020_FP, 1, BITS_FIELD_ENCODING_FX);
        I048_020_X1 = subAId(I048_020, 1, DATA_FORMAT_FIXED);
        I048_020_TST = bitsFieldId(I048_020_X1, 8, BITS_FIELD_ENCODING_VALUE);
        I048_020_ERR = bitsFieldId(I048_020_X1, 7, BITS_FIELD_ENCODING_VALUE);
        I048_020_XPP = bitsFieldId(I048_020_X1, 6, BITS_FIELD_ENCODING_VALUE);
        I048_020_ME = bitsFieldId(I048_020_X1, 5, BITS_FIELD_ENCODING_VALUE);
        I048_020_MI = bitsFieldId(I048_020_X1, 4, BITS_FIELD_ENCODING_VALUE);
        I048_020_FOEFRI = bitsFieldId(I048_020_X1, 3, BITS_FIELD_ENCODING_VALUES);
        I048_020_FX2 = bitsFieldId(I048_020_X1, 1, BITS_FIELD_ENCODING_FX);
        I048_030 = dataItemId(CAT048_ID, 30, DATA_FORMAT_EXTENDED);
        I048_030_FP = subAId(I048_030, 0, DATA_FORMAT_FIXED);
        I048_030_CODE = bitsFieldId(I048_030_FP, 8, BITS_FIELD_ENCODING_VALUES);
        I048_030_FX1 = bitsFieldId(I048_030_FP, 1, BITS_FIELD_ENCODING_FX);
        I048_040 = dataItemId(CAT048_ID, 40, DATA_FORMAT_FIXED);
        I048_040_RHO = bitsFieldId(I048_040, 32, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_040_THETA = bitsFieldId(I048_040, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_042 = dataItemId(CAT048_ID, 42, DATA_FORMAT_FIXED);
        I048_042_X = bitsFieldId(I048_042, 32, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_042_Y = bitsFieldId(I048_042, 16, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_050 = dataItemId(CAT048_ID, 50, DATA_FORMAT_FIXED);
        I048_050_V = bitsFieldId(I048_050, 16, BITS_FIELD_ENCODING_VALUE);
        I048_050_G = bitsFieldId(I048_050, 15, BITS_FIELD_ENCODING_VALUE);
        I048_050_L = bitsFieldId(I048_050, 14, BITS_FIELD_ENCODING_VALUE);
        I048_050_MODE2 = bitsFieldId(I048_050, 12, BITS_FIELD_ENCODING_OCTAL);
        I048_050_SP13 = bitsFieldId(I048_050, 13, BITS_FIELD_ENCODING_SPARE);
        I048_055 = dataItemId(CAT048_ID, 55, DATA_FORMAT_FIXED);
        I048_055_V = bitsFieldId(I048_055, 8, BITS_FIELD_ENCODING_VALUE);
        I048_055_G = bitsFieldId(I048_055, 7, BITS_FIELD_ENCODING_VALUE);
        I048_055_L = bitsFieldId(I048_055, 6, BITS_FIELD_ENCODING_VALUE);
        I048_055_MODE1 = bitsFieldId(I048_055, 5, BITS_FIELD_ENCODING_OCTAL);
        I048_060 = dataItemId(CAT048_ID, 60, DATA_FORMAT_FIXED);
        I048_060_SP16 = bitsFieldId(I048_060, 16, BITS_FIELD_ENCODING_SPARE);
        I048_060_SP15 = bitsFieldId(I048_060, 15, BITS_FIELD_ENCODING_SPARE);
        I048_060_SP14 = bitsFieldId(I048_060, 14, BITS_FIELD_ENCODING_SPARE);
        I048_060_SP13 = bitsFieldId(I048_060, 13, BITS_FIELD_ENCODING_SPARE);
        I048_060_QA4 = bitsFieldId(I048_060, 12, BITS_FIELD_ENCODING_VALUE);
        I048_060_QA2 = bitsFieldId(I048_060, 11, BITS_FIELD_ENCODING_VALUE);
        I048_060_QA1 = bitsFieldId(I048_060, 10, BITS_FIELD_ENCODING_VALUE);
        I048_060_QB4 = bitsFieldId(I048_060, 9, BITS_FIELD_ENCODING_VALUE);
        I048_060_QB2 = bitsFieldId(I048_060, 8, BITS_FIELD_ENCODING_VALUE);
        I048_060_QB1 = bitsFieldId(I048_060, 7, BITS_FIELD_ENCODING_VALUE);
        I048_060_QC4 = bitsFieldId(I048_060, 6, BITS_FIELD_ENCODING_VALUE);
        I048_060_QC2 = bitsFieldId(I048_060, 5, BITS_FIELD_ENCODING_VALUE);
        I048_060_QC1 = bitsFieldId(I048_060, 4, BITS_FIELD_ENCODING_VALUE);
        I048_060_QD4 = bitsFieldId(I048_060, 3, BITS_FIELD_ENCODING_VALUE);
        I048_060_QD2 = bitsFieldId(I048_060, 2, BITS_FIELD_ENCODING_VALUE);
        I048_060_QD1 = bitsFieldId(I048_060, 1, BITS_FIELD_ENCODING_VALUE);
        I048_065 = dataItemId(CAT048_ID, 65, DATA_FORMAT_FIXED);
        I048_065_SP8 = bitsFieldId(I048_065, 8, BITS_FIELD_ENCODING_SPARE);
        I048_065_SP7 = bitsFieldId(I048_065, 7, BITS_FIELD_ENCODING_SPARE);
        I048_065_SP6 = bitsFieldId(I048_065, 6, BITS_FIELD_ENCODING_SPARE);
        I048_065_QA4 = bitsFieldId(I048_065, 5, BITS_FIELD_ENCODING_VALUE);
        I048_065_QA2 = bitsFieldId(I048_065, 4, BITS_FIELD_ENCODING_VALUE);
        I048_065_QA1 = bitsFieldId(I048_065, 3, BITS_FIELD_ENCODING_VALUE);
        I048_065_QB2 = bitsFieldId(I048_065, 2, BITS_FIELD_ENCODING_VALUE);
        I048_065_QB1 = bitsFieldId(I048_065, 1, BITS_FIELD_ENCODING_VALUE);
        I048_070 = dataItemId(CAT048_ID, 70, DATA_FORMAT_FIXED);
        I048_070_V = bitsFieldId(I048_070, 16, BITS_FIELD_ENCODING_VALUE);
        I048_070_G = bitsFieldId(I048_070, 15, BITS_FIELD_ENCODING_VALUE);
        I048_070_L = bitsFieldId(I048_070, 14, BITS_FIELD_ENCODING_VALUE);
        I048_070_SP13 = bitsFieldId(I048_070, 13, BITS_FIELD_ENCODING_SPARE);
        I048_070_MODE3A = bitsFieldId(I048_070, 12, BITS_FIELD_ENCODING_OCTAL);
        I048_080 = dataItemId(CAT048_ID, 80, DATA_FORMAT_FIXED);
        I048_080_SP16 = bitsFieldId(I048_080, 16, BITS_FIELD_ENCODING_SPARE);
        I048_080_SP15 = bitsFieldId(I048_080, 15, BITS_FIELD_ENCODING_SPARE);
        I048_080_SP14 = bitsFieldId(I048_080, 14, BITS_FIELD_ENCODING_SPARE);
        I048_080_SP13 = bitsFieldId(I048_080, 13, BITS_FIELD_ENCODING_SPARE);
        I048_080_QA4 = bitsFieldId(I048_080, 12, BITS_FIELD_ENCODING_VALUE);
        I048_080_QA2 = bitsFieldId(I048_080, 11, BITS_FIELD_ENCODING_VALUE);
        I048_080_QA1 = bitsFieldId(I048_080, 10, BITS_FIELD_ENCODING_VALUE);
        I048_080_QB4 = bitsFieldId(I048_080, 9, BITS_FIELD_ENCODING_VALUE);
        I048_080_QB2 = bitsFieldId(I048_080, 8, BITS_FIELD_ENCODING_VALUE);
        I048_080_QB1 = bitsFieldId(I048_080, 7, BITS_FIELD_ENCODING_VALUE);
        I048_080_QC4 = bitsFieldId(I048_080, 6, BITS_FIELD_ENCODING_VALUE);
        I048_080_QC2 = bitsFieldId(I048_080, 5, BITS_FIELD_ENCODING_VALUE);
        I048_080_QC1 = bitsFieldId(I048_080, 4, BITS_FIELD_ENCODING_VALUE);
        I048_080_QD4 = bitsFieldId(I048_080, 3, BITS_FIELD_ENCODING_VALUE);
        I048_080_QD2 = bitsFieldId(I048_080, 2, BITS_FIELD_ENCODING_VALUE);
        I048_080_QD1 = bitsFieldId(I048_080, 1, BITS_FIELD_ENCODING_VALUE);
        I048_090 = dataItemId(CAT048_ID, 90, DATA_FORMAT_FIXED);
        I048_090_V = bitsFieldId(I048_090, 16, BITS_FIELD_ENCODING_VALUE);
        I048_090_G = bitsFieldId(I048_090, 15, BITS_FIELD_ENCODING_VALUE);
        I048_090_FL = bitsFieldId(I048_090, 14, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_100 = dataItemId(CAT048_ID, 100, DATA_FORMAT_FIXED);
        I048_100_V = bitsFieldId(I048_100, 32, BITS_FIELD_ENCODING_VALUE);
        I048_100_G = bitsFieldId(I048_100, 31, BITS_FIELD_ENCODING_VALUE);
        I048_100_SP30 = bitsFieldId(I048_100, 30, BITS_FIELD_ENCODING_SPARE);
        I048_100_SP29 = bitsFieldId(I048_100, 29, BITS_FIELD_ENCODING_SPARE);
        I048_100_MODEC = bitsFieldId(I048_100, 28, BITS_FIELD_ENCODING_MEASURE_GRAYCODE);
        I048_100_SP16 = bitsFieldId(I048_100, 16, BITS_FIELD_ENCODING_SPARE);
        I048_100_SP15 = bitsFieldId(I048_100, 15, BITS_FIELD_ENCODING_SPARE);
        I048_100_SP14 = bitsFieldId(I048_100, 14, BITS_FIELD_ENCODING_SPARE);
        I048_100_SP13 = bitsFieldId(I048_100, 13, BITS_FIELD_ENCODING_SPARE);
        I048_100_QC1 = bitsFieldId(I048_100, 12, BITS_FIELD_ENCODING_VALUE);
        I048_100_QA1 = bitsFieldId(I048_100, 11, BITS_FIELD_ENCODING_VALUE);
        I048_100_QC2 = bitsFieldId(I048_100, 10, BITS_FIELD_ENCODING_VALUE);
        I048_100_QA2 = bitsFieldId(I048_100, 9, BITS_FIELD_ENCODING_VALUE);
        I048_100_QC4 = bitsFieldId(I048_100, 8, BITS_FIELD_ENCODING_VALUE);
        I048_100_QA4 = bitsFieldId(I048_100, 7, BITS_FIELD_ENCODING_VALUE);
        I048_100_QB1 = bitsFieldId(I048_100, 6, BITS_FIELD_ENCODING_VALUE);
        I048_100_QD1 = bitsFieldId(I048_100, 5, BITS_FIELD_ENCODING_VALUE);
        I048_100_QB2 = bitsFieldId(I048_100, 4, BITS_FIELD_ENCODING_VALUE);
        I048_100_QD2 = bitsFieldId(I048_100, 3, BITS_FIELD_ENCODING_VALUE);
        I048_100_QB4 = bitsFieldId(I048_100, 2, BITS_FIELD_ENCODING_VALUE);
        I048_100_QD4 = bitsFieldId(I048_100, 1, BITS_FIELD_ENCODING_VALUE);
        I048_110 = dataItemId(CAT048_ID, 110, DATA_FORMAT_FIXED);
        I048_110_SP16 = bitsFieldId(I048_110, 16, BITS_FIELD_ENCODING_SPARE);
        I048_110_SP15 = bitsFieldId(I048_110, 15, BITS_FIELD_ENCODING_SPARE);
        I048_110_3DH = bitsFieldId(I048_110, 14, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_120 = dataItemId(CAT048_ID, 120, DATA_FORMAT_COMPOUND);
        I048_120_CAL = subAId(I048_120, 8, DATA_FORMAT_FIXED);
        I048_120_RDS = subAId(I048_120, 7, DATA_FORMAT_REPETITIVE);
        I048_120_SP6 = subAId(I048_120, 6, DATA_FORMAT_EMPTY);
        I048_120_SP5 = subAId(I048_120, 5, DATA_FORMAT_EMPTY);
        I048_120_SP4 = subAId(I048_120, 4, DATA_FORMAT_EMPTY);
        I048_120_SP3 = subAId(I048_120, 3, DATA_FORMAT_EMPTY);
        I048_120_SP2 = subAId(I048_120, 2, DATA_FORMAT_EMPTY);
        I048_120_FX1 = subAId(I048_120, 1, DATA_FORMAT_EMPTY);
        I048_120_CAL_D = bitsFieldId(I048_120_CAL, 16, BITS_FIELD_ENCODING_VALUE);
        I048_120_CAL_SP15 = bitsFieldId(I048_120_CAL, 15, BITS_FIELD_ENCODING_SPARE);
        I048_120_CAL_SP14 = bitsFieldId(I048_120_CAL, 14, BITS_FIELD_ENCODING_SPARE);
        I048_120_CAL_SP13 = bitsFieldId(I048_120_CAL, 13, BITS_FIELD_ENCODING_SPARE);
        I048_120_CAL_SP12 = bitsFieldId(I048_120_CAL, 12, BITS_FIELD_ENCODING_SPARE);
        I048_120_CAL_SP11 = bitsFieldId(I048_120_CAL, 11, BITS_FIELD_ENCODING_SPARE);
        I048_120_CAL_CAL = bitsFieldId(I048_120_CAL, 10, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_120_RDS_DOP = bitsFieldId(I048_120_RDS, 48, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_120_RDS_AMB = bitsFieldId(I048_120_RDS, 32, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_120_RDS_FRQ = bitsFieldId(I048_120_RDS, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_130 = dataItemId(CAT048_ID, 130, DATA_FORMAT_COMPOUND);
        I048_130_SRL = subAId(I048_130, 8, DATA_FORMAT_FIXED);
        I048_130_SRR = subAId(I048_130, 7, DATA_FORMAT_FIXED);
        I048_130_SAM = subAId(I048_130, 6, DATA_FORMAT_FIXED);
        I048_130_PRL = subAId(I048_130, 5, DATA_FORMAT_FIXED);
        I048_130_PAM = subAId(I048_130, 4, DATA_FORMAT_FIXED);
        I048_130_RPD = subAId(I048_130, 3, DATA_FORMAT_FIXED);
        I048_130_APD = subAId(I048_130, 2, DATA_FORMAT_FIXED);
        I048_130_FX1 = subAId(I048_130, 1, DATA_FORMAT_EMPTY);
        I048_130_SRL_SRL = bitsFieldId(I048_130_SRL, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_130_SRR_SRR = bitsFieldId(I048_130_SRR, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I048_130_SAM_SAM = bitsFieldId(I048_130_SAM, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_130_PRL_PRL = bitsFieldId(I048_130_PRL, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_130_PAM_PAM = bitsFieldId(I048_130_PAM, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_130_RPD_RPD = bitsFieldId(I048_130_RPD, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_130_APD_APD = bitsFieldId(I048_130_APD, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_140 = dataItemId(CAT048_ID, 140, DATA_FORMAT_FIXED);
        I048_140_TOD = bitsFieldId(I048_140, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_161 = dataItemId(CAT048_ID, 161, DATA_FORMAT_FIXED);
        I048_161_SP16 = bitsFieldId(I048_161, 16, BITS_FIELD_ENCODING_SPARE);
        I048_161_SP15 = bitsFieldId(I048_161, 15, BITS_FIELD_ENCODING_SPARE);
        I048_161_SP14 = bitsFieldId(I048_161, 14, BITS_FIELD_ENCODING_SPARE);
        I048_161_SP13 = bitsFieldId(I048_161, 13, BITS_FIELD_ENCODING_SPARE);
        I048_161_TRN = bitsFieldId(I048_161, 12, BITS_FIELD_ENCODING_UNSIGNED);
        I048_170 = dataItemId(CAT048_ID, 170, DATA_FORMAT_EXTENDED);
        I048_170_FP = subAId(I048_170, 0, DATA_FORMAT_FIXED);
        I048_170_CNF = bitsFieldId(I048_170_FP, 8, BITS_FIELD_ENCODING_VALUE);
        I048_170_RAD = bitsFieldId(I048_170_FP, 7, BITS_FIELD_ENCODING_VALUES);
        I048_170_DOU = bitsFieldId(I048_170_FP, 5, BITS_FIELD_ENCODING_VALUE);
        I048_170_MAH = bitsFieldId(I048_170_FP, 4, BITS_FIELD_ENCODING_VALUE);
        I048_170_CDM = bitsFieldId(I048_170_FP, 3, BITS_FIELD_ENCODING_VALUES);
        I048_170_FX1 = bitsFieldId(I048_170_FP, 1, BITS_FIELD_ENCODING_FX);
        I048_170_X1 = subAId(I048_170, 1, DATA_FORMAT_FIXED);
        I048_170_TRE = bitsFieldId(I048_170_X1, 8, BITS_FIELD_ENCODING_VALUE);
        I048_170_GHO = bitsFieldId(I048_170_X1, 7, BITS_FIELD_ENCODING_VALUE);
        I048_170_SUP = bitsFieldId(I048_170_X1, 6, BITS_FIELD_ENCODING_VALUE);
        I048_170_TCC = bitsFieldId(I048_170_X1, 5, BITS_FIELD_ENCODING_VALUE);
        I048_170_SP4 = bitsFieldId(I048_170_X1, 4, BITS_FIELD_ENCODING_SPARE);
        I048_170_SP3 = bitsFieldId(I048_170_X1, 3, BITS_FIELD_ENCODING_SPARE);
        I048_170_SP2 = bitsFieldId(I048_170_X1, 2, BITS_FIELD_ENCODING_SPARE);
        I048_170_FX2 = bitsFieldId(I048_170_X1, 1, BITS_FIELD_ENCODING_FX);
        I048_200 = dataItemId(CAT048_ID, 200, DATA_FORMAT_FIXED);
        I048_200_GSP = bitsFieldId(I048_200, 32, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_200_HDG = bitsFieldId(I048_200, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_210 = dataItemId(CAT048_ID, 210, DATA_FORMAT_FIXED);
        I048_210_SIGX = bitsFieldId(I048_210, 32, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_210_SIGY = bitsFieldId(I048_210, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_210_SIGV = bitsFieldId(I048_210, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_210_SIGH = bitsFieldId(I048_210, 8, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_220 = dataItemId(CAT048_ID, 220, DATA_FORMAT_FIXED);
        I048_220_ADDR = bitsFieldId(I048_220, 24, BITS_FIELD_ENCODING_HEX);
        I048_230 = dataItemId(CAT048_ID, 230, DATA_FORMAT_FIXED);
        I048_230_COM = bitsFieldId(I048_230, 16, BITS_FIELD_ENCODING_VALUES);
        I048_230_STAT = bitsFieldId(I048_230, 13, BITS_FIELD_ENCODING_VALUES);
        I048_230_SI = bitsFieldId(I048_230, 10, BITS_FIELD_ENCODING_VALUE);
        I048_230_SP9 = bitsFieldId(I048_230, 9, BITS_FIELD_ENCODING_SPARE);
        I048_230_MSSC = bitsFieldId(I048_230, 8, BITS_FIELD_ENCODING_VALUE);
        I048_230_ARC = bitsFieldId(I048_230, 7, BITS_FIELD_ENCODING_VALUE);
        I048_230_AIC = bitsFieldId(I048_230, 6, BITS_FIELD_ENCODING_VALUE);
        I048_230_B1A = bitsFieldId(I048_230, 5, BITS_FIELD_ENCODING_VALUE);
        I048_230_B1B = bitsFieldId(I048_230, 4, BITS_FIELD_ENCODING_UNSIGNED);
        I048_240 = dataItemId(CAT048_ID, 240, DATA_FORMAT_FIXED);
        I048_240_IDENT = bitsFieldId(I048_240, 48, BITS_FIELD_ENCODING_IA5);
        I048_250 = dataItemId(CAT048_ID, 250, DATA_FORMAT_REPETITIVE);
        I048_250_MBDATA = bitsFieldId(I048_250, 64, BITS_FIELD_ENCODING_HEX);
        I048_250_BDS1 = bitsFieldId(I048_250, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I048_250_BDS2 = bitsFieldId(I048_250, 4, BITS_FIELD_ENCODING_UNSIGNED);
        I048_260 = dataItemId(CAT048_ID, 260, DATA_FORMAT_FIXED);
        I048_260_BDS30 = bitsFieldId(I048_260, 56, BITS_FIELD_ENCODING_HEX);
        I048_260_ARA41 = bitsFieldId(I048_260, 48, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA42 = bitsFieldId(I048_260, 47, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA43 = bitsFieldId(I048_260, 46, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA44 = bitsFieldId(I048_260, 45, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA45 = bitsFieldId(I048_260, 44, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA46 = bitsFieldId(I048_260, 43, BITS_FIELD_ENCODING_VALUE);
        I048_260_ARA47 = bitsFieldId(I048_260, 42, BITS_FIELD_ENCODING_VALUE);
        I048_260_ACAS3 = bitsFieldId(I048_260, 41, BITS_FIELD_ENCODING_UNSIGNED);
        I048_260_RAC55 = bitsFieldId(I048_260, 34, BITS_FIELD_ENCODING_VALUE);
        I048_260_RAC56 = bitsFieldId(I048_260, 33, BITS_FIELD_ENCODING_VALUE);
        I048_260_RAC57 = bitsFieldId(I048_260, 32, BITS_FIELD_ENCODING_VALUE);
        I048_260_RAC58 = bitsFieldId(I048_260, 31, BITS_FIELD_ENCODING_VALUE);
        I048_260_RAT = bitsFieldId(I048_260, 30, BITS_FIELD_ENCODING_VALUE);
        I048_260_MTE = bitsFieldId(I048_260, 29, BITS_FIELD_ENCODING_VALUE);
        I048_260_TTI = bitsFieldId(I048_260, 28, BITS_FIELD_ENCODING_VALUES);
        I048_260_TID = bitsFieldId(I048_260, 26, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE = dataItemId(CAT048_ID, DATA_ITEM_RE, DATA_FORMAT_EXPLICIT);
        I048_RE_MD5 = subAId(I048_RE, 8, DATA_FORMAT_COMPOUND);
        I048_RE_MD5_SUM = subBId(I048_RE_MD5, 8, DATA_FORMAT_FIXED);
        I048_RE_MD5_SUM_M5 = bitsFieldId(I048_RE_MD5_SUM, 8, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_ID = bitsFieldId(I048_RE_MD5_SUM, 7, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_DA = bitsFieldId(I048_RE_MD5_SUM, 6, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_M1 = bitsFieldId(I048_RE_MD5_SUM, 5, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_M2 = bitsFieldId(I048_RE_MD5_SUM, 4, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_M3 = bitsFieldId(I048_RE_MD5_SUM, 3, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_MC = bitsFieldId(I048_RE_MD5_SUM, 2, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_SUM_SP1 = bitsFieldId(I048_RE_MD5_SUM, 1, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN = subBId(I048_RE_MD5, 7, DATA_FORMAT_FIXED);
        I048_RE_MD5_PMN_SP32 = bitsFieldId(I048_RE_MD5_PMN, 32, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_SP31 = bitsFieldId(I048_RE_MD5_PMN, 31, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_PIN = bitsFieldId(I048_RE_MD5_PMN, 30, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_MD5_PMN_SP16 = bitsFieldId(I048_RE_MD5_PMN, 16, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_SP15 = bitsFieldId(I048_RE_MD5_PMN, 15, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_NAV = bitsFieldId(I048_RE_MD5_PMN, 14, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_PMN_NAT = bitsFieldId(I048_RE_MD5_PMN, 13, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_MD5_PMN_SP8 = bitsFieldId(I048_RE_MD5_PMN, 8, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_SP7 = bitsFieldId(I048_RE_MD5_PMN, 7, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_PMN_MIS = bitsFieldId(I048_RE_MD5_PMN, 6, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_MD5_POS = subBId(I048_RE_MD5, 6, DATA_FORMAT_FIXED);
        I048_RE_MD5_POS_LAT = bitsFieldId(I048_RE_MD5_POS, 48, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_MD5_POS_LON = bitsFieldId(I048_RE_MD5_POS, 24, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_MD5_GA = subBId(I048_RE_MD5, 5, DATA_FORMAT_FIXED);
        I048_RE_MD5_GA_SP16 = bitsFieldId(I048_RE_MD5_GA, 16, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_GA_RES = bitsFieldId(I048_RE_MD5_GA, 15, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_GA_ALT = bitsFieldId(I048_RE_MD5_GA, 14, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_MD5_EM1 = subBId(I048_RE_MD5, 4, DATA_FORMAT_FIXED);
        I048_RE_MD5_EM1_V = bitsFieldId(I048_RE_MD5_EM1, 16, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_EM1_G = bitsFieldId(I048_RE_MD5_EM1, 15, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_EM1_L = bitsFieldId(I048_RE_MD5_EM1, 14, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_EM1_SP13 = bitsFieldId(I048_RE_MD5_EM1, 13, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_EM1_MODE1 = bitsFieldId(I048_RE_MD5_EM1, 12, BITS_FIELD_ENCODING_OCTAL);
        I048_RE_MD5_TOS = subBId(I048_RE_MD5, 3, DATA_FORMAT_FIXED);
        I048_RE_MD5_TOS_TOS = bitsFieldId(I048_RE_MD5_TOS, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_MD5_XP = subBId(I048_RE_MD5, 2, DATA_FORMAT_FIXED);
        I048_RE_MD5_XP_SP8 = bitsFieldId(I048_RE_MD5_XP, 8, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_XP_SP7 = bitsFieldId(I048_RE_MD5_XP, 7, BITS_FIELD_ENCODING_SPARE);
        I048_RE_MD5_XP_XP = bitsFieldId(I048_RE_MD5_XP, 6, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_XP_X5 = bitsFieldId(I048_RE_MD5_XP, 5, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_XP_XC = bitsFieldId(I048_RE_MD5_XP, 4, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_XP_X3 = bitsFieldId(I048_RE_MD5_XP, 3, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_XP_X2 = bitsFieldId(I048_RE_MD5_XP, 2, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_XP_X1 = bitsFieldId(I048_RE_MD5_XP, 1, BITS_FIELD_ENCODING_VALUE);
        I048_RE_MD5_FX1 = subBId(I048_RE_MD5, 1, DATA_FORMAT_EMPTY);
        I048_RE_M5N = subAId(I048_RE, 7, DATA_FORMAT_COMPOUND);
        I048_RE_M5N_SUM = subBId(I048_RE_M5N, 16, DATA_FORMAT_FIXED);
        I048_RE_M5N_SUM_M5 = bitsFieldId(I048_RE_M5N_SUM, 8, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_ID = bitsFieldId(I048_RE_M5N_SUM, 7, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_DA = bitsFieldId(I048_RE_M5N_SUM, 6, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_M1 = bitsFieldId(I048_RE_M5N_SUM, 5, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_M2 = bitsFieldId(I048_RE_M5N_SUM, 4, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_M3 = bitsFieldId(I048_RE_M5N_SUM, 3, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_MC = bitsFieldId(I048_RE_M5N_SUM, 2, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_SUM_SP1 = bitsFieldId(I048_RE_M5N_SUM, 1, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN = subBId(I048_RE_M5N, 15, DATA_FORMAT_FIXED);
        I048_RE_M5N_PMN_SP32 = bitsFieldId(I048_RE_M5N_PMN, 32, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_SP31 = bitsFieldId(I048_RE_M5N_PMN, 31, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_PIN = bitsFieldId(I048_RE_M5N_PMN, 30, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_M5N_PMN_SP16 = bitsFieldId(I048_RE_M5N_PMN, 16, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_SP15 = bitsFieldId(I048_RE_M5N_PMN, 15, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_SP14 = bitsFieldId(I048_RE_M5N_PMN, 14, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_SP13 = bitsFieldId(I048_RE_M5N_PMN, 13, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_PMN_NOV = bitsFieldId(I048_RE_M5N_PMN, 12, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_PMN_NO = bitsFieldId(I048_RE_M5N_PMN, 11, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_M5N_POS = subBId(I048_RE_M5N, 14, DATA_FORMAT_FIXED);
        I048_RE_M5N_POS_LAT = bitsFieldId(I048_RE_M5N_POS, 48, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_M5N_POS_LON = bitsFieldId(I048_RE_M5N_POS, 24, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_M5N_GA = subBId(I048_RE_M5N, 13, DATA_FORMAT_FIXED);
        I048_RE_M5N_GA_SP16 = bitsFieldId(I048_RE_M5N_GA, 16, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_GA_RES = bitsFieldId(I048_RE_M5N_GA, 15, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_GA_ALT = bitsFieldId(I048_RE_M5N_GA, 14, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_M5N_EM1 = subBId(I048_RE_M5N, 12, DATA_FORMAT_FIXED);
        I048_RE_M5N_EM1_V = bitsFieldId(I048_RE_M5N_EM1, 16, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_EM1_G = bitsFieldId(I048_RE_M5N_EM1, 15, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_EM1_L = bitsFieldId(I048_RE_M5N_EM1, 14, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_EM1_SP13 = bitsFieldId(I048_RE_M5N_EM1, 13, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_EM1_MODE1 = bitsFieldId(I048_RE_M5N_EM1, 12, BITS_FIELD_ENCODING_OCTAL);
        I048_RE_M5N_TOS = subBId(I048_RE_M5N, 11, DATA_FORMAT_FIXED);
        I048_RE_M5N_TOS_TOS = bitsFieldId(I048_RE_M5N_TOS, 8, BITS_FIELD_ENCODING_MEASURE_SIGNED);
        I048_RE_M5N_XP = subBId(I048_RE_M5N, 10, DATA_FORMAT_FIXED);
        I048_RE_M5N_XP_SP8 = bitsFieldId(I048_RE_M5N_XP, 8, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_XP_SP7 = bitsFieldId(I048_RE_M5N_XP, 7, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_XP_XP = bitsFieldId(I048_RE_M5N_XP, 6, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_XP_X5 = bitsFieldId(I048_RE_M5N_XP, 5, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_XP_XC = bitsFieldId(I048_RE_M5N_XP, 4, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_XP_X3 = bitsFieldId(I048_RE_M5N_XP, 3, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_XP_X2 = bitsFieldId(I048_RE_M5N_XP, 2, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_XP_X1 = bitsFieldId(I048_RE_M5N_XP, 1, BITS_FIELD_ENCODING_VALUE);
        I048_RE_M5N_FX1 = subBId(I048_RE_M5N, 9, DATA_FORMAT_EMPTY);
        I048_RE_M5N_FOM = subBId(I048_RE_M5N, 8, DATA_FORMAT_FIXED);
        I048_RE_M5N_FOM_SP8 = bitsFieldId(I048_RE_M5N_FOM, 8, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_FOM_SP7 = bitsFieldId(I048_RE_M5N_FOM, 7, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_FOM_SP6 = bitsFieldId(I048_RE_M5N_FOM, 6, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M5N_FOM_FOM = bitsFieldId(I048_RE_M5N_FOM, 5, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_M5N_SP7 = subBId(I048_RE_M5N, 7, DATA_FORMAT_EMPTY);
        I048_RE_M5N_SP6 = subBId(I048_RE_M5N, 6, DATA_FORMAT_EMPTY);
        I048_RE_M5N_SP5 = subBId(I048_RE_M5N, 5, DATA_FORMAT_EMPTY);
        I048_RE_M5N_SP4 = subBId(I048_RE_M5N, 4, DATA_FORMAT_EMPTY);
        I048_RE_M5N_SP3 = subBId(I048_RE_M5N, 3, DATA_FORMAT_EMPTY);
        I048_RE_M5N_SP2 = subBId(I048_RE_M5N, 2, DATA_FORMAT_EMPTY);
        I048_RE_M5N_FX2 = subBId(I048_RE_M5N, 1, DATA_FORMAT_EMPTY);
        I048_RE_M4E = subAId(I048_RE, 6, DATA_FORMAT_EXTENDED);
        I048_RE_M4E_FP = subBId(I048_RE_M4E, 0, DATA_FORMAT_FIXED);
        I048_RE_M4E_SP8 = bitsFieldId(I048_RE_M4E_FP, 8, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M4E_SP7 = bitsFieldId(I048_RE_M4E_FP, 7, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M4E_SP6 = bitsFieldId(I048_RE_M4E_FP, 6, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M4E_SP5 = bitsFieldId(I048_RE_M4E_FP, 5, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M4E_SP4 = bitsFieldId(I048_RE_M4E_FP, 4, BITS_FIELD_ENCODING_SPARE);
        I048_RE_M4E_FOEFRI = bitsFieldId(I048_RE_M4E_FP, 3, BITS_FIELD_ENCODING_VALUES);
        I048_RE_M4E_FX1 = bitsFieldId(I048_RE_M4E_FP, 1, BITS_FIELD_ENCODING_FX);
        I048_RE_RPC = subAId(I048_RE, 5, DATA_FORMAT_COMPOUND);
        I048_RE_RPC_SCO = subBId(I048_RE_RPC, 8, DATA_FORMAT_FIXED);
        I048_RE_RPC_SCO_SCO = bitsFieldId(I048_RE_RPC_SCO, 8, BITS_FIELD_ENCODING_UNSIGNED);
        I048_RE_RPC_SCR = subBId(I048_RE_RPC, 7, DATA_FORMAT_FIXED);
        I048_RE_RPC_SCR_SCR = bitsFieldId(I048_RE_RPC_SCR, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_RE_RPC_RW = subBId(I048_RE_RPC, 6, DATA_FORMAT_FIXED);
        I048_RE_RPC_RW_RW = bitsFieldId(I048_RE_RPC_RW, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_RE_RPC_AR = subBId(I048_RE_RPC, 5, DATA_FORMAT_FIXED);
        I048_RE_RPC_AR_AR = bitsFieldId(I048_RE_RPC_AR, 16, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_RE_RPC_SP4 = subBId(I048_RE_RPC, 4, DATA_FORMAT_EMPTY);
        I048_RE_RPC_SP3 = subBId(I048_RE_RPC, 3, DATA_FORMAT_EMPTY);
        I048_RE_RPC_SP2 = subBId(I048_RE_RPC, 2, DATA_FORMAT_EMPTY);
        I048_RE_RPC_FX1 = subBId(I048_RE_RPC, 1, DATA_FORMAT_EMPTY);
        I048_RE_ERR = subAId(I048_RE, 4, DATA_FORMAT_FIXED);
        I048_RE_ERR_RHO = bitsFieldId(I048_RE_ERR, 24, BITS_FIELD_ENCODING_MEASURE_UNSIGNED);
        I048_RE_SP3 = subAId(I048_RE, 3, DATA_FORMAT_EMPTY);
        I048_RE_SP2 = subAId(I048_RE, 2, DATA_FORMAT_EMPTY);
        I048_RE_SP1 = subAId(I048_RE, 1, DATA_FORMAT_EMPTY);
        I048_SP = dataItemId(CAT048_ID, DATA_ITEM_SP, DATA_FORMAT_EXPLICIT);

        final DataFormat f048_010 = fixed(I048_010, 2,
                unsigned(I048_010_SAC, position(2, 16, 9), "SAC", "System Area Code"),
                unsigned(I048_010_SIC, position(2, 8, 1), "SIC", "System Identification Code"));

        final DataItem i048_010 = DataItem.from(f048_010, "Data Source Identifier", true,
                "Identification of the radar station from which the data are received");

        final DataFormat f048_020 = extended(I048_020,
                Part.of(I048_020_FP, 1,
                        bits(I048_020_TYP, position(1, 8, 6), "TYP", "Type of detection",
                                BitsValue.of(0, "No detection"),
                                BitsValue.of(1, "Single PSR detection"),
                                BitsValue.of(2, "Single SSR detection"),
                                BitsValue.of(3, "SSR + PSR detection"),
                                BitsValue.of(4, "Single Mode-S All-Call"),
                                BitsValue.of(5, "Single Mode-S Roll-Call"),
                                BitsValue.of(6, "Mode-S All-Call + PSR"),
                                BitsValue.of(7, "Mode-S Roll-Call + PSR")),
                        bit(I048_020_SIM, position(1, 5, 5), "SIM", "Actual vs. Simulated target report",
                                BitsValue.of(0, "Actual target report"),
                                BitsValue.of(1, "Simulated target report")),
                        bit(I048_020_RDP, position(1, 4, 4), "RDP", "Report from RDP Chain",
                                BitsValue.of(0, "Report from RDP Chain 1"),
                                BitsValue.of(1, "Report from RDP Chain 2")),
                        bit(I048_020_SPI, position(1, 3, 3), "SPI", "Special Position Identification",
                                BitsValue.of(0, "Absence of SPI"),
                                BitsValue.of(1, "Special Position Identification")),
                        bit(I048_020_RAB, position(1, 2, 2), "RAB", "Target Report Source",
                                BitsValue.of(0, "Report from aircraft transponder"),
                                BitsValue.of(1, "Report from field monitor (fixed transponder)")),
                        fx(I048_020_FX1, position(1, 1, 1))),
                Part.of(I048_020_X1, 1,
                        bit(I048_020_TST, position(1, 8, 8), "TST", "Real vs. Test target report",
                                BitsValue.of(0, "Real target report"),
                                BitsValue.of(1, "Test target report")),
                        bit(I048_020_ERR, position(1, 7, 7), "ERR", "Extended Range",
                                BitsValue.of(0, "No Extended Range"),
                                BitsValue.of(1, "Extended Range present")),
                        bit(I048_020_XPP, position(1, 6, 6), "XPP", "X-Pulse",
                                BitsValue.of(0, "No X-Pulse present"),
                                BitsValue.of(1, "X-Pulse present")),
                        bit(I048_020_ME, position(1, 5, 5), "ME", "Military Emergency",
                                BitsValue.of(0, "No military emergency"),
                                BitsValue.of(1, "Military emergency")),
                        bit(I048_020_MI, position(1, 4, 4), "MI", "Military Identification",
                                BitsValue.of(0, "No military identification"),
                                BitsValue.of(1, "Military identification")),
                        bits(I048_020_FOEFRI, position(1, 3, 2), "FOEFRI", "Mode-4 interrogation",
                                BitsValue.of(0, "No Mode-4 interrogation"),
                                BitsValue.of(1, "Friendly target"),
                                BitsValue.of(2, "Unknown target"),
                                BitsValue.of(3, "No reply")),
                        fx(I048_020_FX2, position(1, 1, 1))));

        final DataItem i048_020 = DataItem.from(f048_020, "Target Report Descriptor", true,
                "Type and properties of the target report");

        final DataFormat f048_030 = extended(I048_030,
                Part.of(I048_030_FP, 1,
                        bits(I048_030_CODE, position(1, 8, 2), "CODE", "Warning/error condition value",
                                BitsValue.of(0, "Not defined; never used"),
                                BitsValue.of(1, "Multipath Reply (Reflection)"),
                                BitsValue.of(2, "Reply due to sidelobe interrogation/reception"),
                                BitsValue.of(3, "Split plot"),
                                BitsValue.of(4, "Second time around reply"),
                                BitsValue.of(5, "Angel"),
                                BitsValue.of(6, "Slow moving target correlated with road infrastructure (terrestrial vehicle)"),
                                BitsValue.of(7, "Fixed PSR plot"),
                                BitsValue.of(8, "Slow PSR plot"),
                                BitsValue.of(9, "Low quality PSR plot"),
                                BitsValue.of(10, "Phantom SSR plot"),
                                BitsValue.of(11, "Non-Matching Mode-3/A Code"),
                                BitsValue.of(12, "Mode-C code/Mode-S altitude code abnormal value compared to the track"),
                                BitsValue.of(13, "Target in Clutter Area"),
                                BitsValue.of(14, "Maximum doppler Response in Zero Filter"),
                                BitsValue.of(15, "Transponder anomaly detected"),
                                BitsValue.of(16, "Duplicated or Illegal Mode-S Aircraft Address"),
                                BitsValue.of(17, "Mode-S error correction applied"),
                                BitsValue.of(18, "Undecodable Mode-C code / Mode-S altitude code"),
                                BitsValue.of(19, "Birds"),
                                BitsValue.of(20, "Flock of Birds"),
                                BitsValue.of(21, "Mode-1 was present in original reply"),
                                BitsValue.of(22, "Mode-2 was present in original reply"),
                                BitsValue.of(23, "Plot potentially caused by Wind Turbine"),
                                BitsValue.of(24, "Helicopter"),
                                BitsValue.of(25, "Maximum number of re-interrogations reached (surveillance information)"),
                                BitsValue.of(26, "Maximum number of re-interrogations reached (BDS Extractions)"),
                                BitsValue.of(27, "BDS Overlay Incoherence"),
                                BitsValue.of(28, "Potential BDS Swap Detected"),
                                BitsValue.of(29, "Track Update in the Zenithal Gap"),
                                BitsValue.of(30, "Mode-S Track re-acquired"),
                                BitsValue.of(31, "Duplicated Mode-5 Pair NO/PIN detected"),
                                BitsValue.of(32, "Wrong DF reply format detected"),
                                BitsValue.of(33, "Transponder anomaly (MS XPD replies with Mode-A/C to Mode-A/C-only all-call"),
                                BitsValue.of(34, "Transponder anomaly (SI capability report wrong)")),
                        fx(I048_030_FX1, position(1, 1, 1))));

        final DataItem i048_030 = DataItem.from(f048_030, "Warning/Error Conditions and Target Classification", false,
                "Warning/error conditions detected by a radar station for the target report involved. Target Classification information for the target involved");

        final DataFormat f048_040 = fixed(I048_040, 4,
                umeasure(I048_040_RHO, position(4, 32, 17), "RHO", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Rho"),
                umeasure(I048_040_THETA, position(4, 16, 1), "THETA", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Theta"));

        final DataItem i048_040 = DataItem.from(f048_040, "Measured Position in Polar Co-ordinates", false,
                "Measured position of an aircraft in local polar co-ordinates");

        final DataFormat f048_042 = fixed(I048_042, 4,
                smeasure(I048_042_X, position(4, 32, 17), "X", 1.0 / 128.0, UnitOfMeasure.DISTANCE_NM, "X"),
                smeasure(I048_042_Y, position(4, 16, 1), "Y", 1.0 / 128.0, UnitOfMeasure.DISTANCE_NM, "Y"));

        final DataItem i048_042 = DataItem.from(f048_042, "Calculated Position in Cartesian Co-ordinates", false,
                "Calculated position of an aircraft in Cartesian co-ordinates");

        final DataFormat f048_050 = fixed(I048_050, 2,
                bit(I048_050_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I048_050_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I048_050_L, position(2, 14, 14), "L", "Reply",
                        BitsValue.of(0, "Mode-2 code as derived from the reply of the transponder"),
                        BitsValue.of(1, "Smoothed Mode-2 code as provided by a local tracker")),
                spare(I048_050_SP13, position(2, 13, 13)),
                octal(I048_050_MODE2, position(2, 12, 1), "MODE2", "Mode-2 Code"));

        final DataItem i048_050 = DataItem.from(f048_050, "Mode-2 Code in Octal Representation", false,
                "Reply to Mode-2 interrogation");

        final DataFormat f048_055 = fixed(I048_055, 1,
                bit(I048_055_V, position(1, 8, 8), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I048_055_G, position(1, 7, 7), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I048_055_L, position(1, 6, 6), "L", "Reply",
                        BitsValue.of(0, "Mode-1 code as derived from the reply of the transponder"),
                        BitsValue.of(1, "Smoothed Mode-1 code as provided by a local tracker")),
                octal(I048_055_MODE1, position(1, 5, 1), "MODE1", "Mode-1 Code"));

        final DataItem i048_055 = DataItem.from(f048_055, "Mode-1 Code in Octal Representation", false,
                "Reply to Mode-1 interrogation");

        final DataFormat f048_060 = fixed(I048_060, 2,
                spare(I048_060_SP16, position(2, 16, 16)),
                spare(I048_060_SP15, position(2, 15, 15)),
                spare(I048_060_SP14, position(2, 14, 14)),
                spare(I048_060_SP13, position(2, 13, 13)),
                bit(I048_060_QA4, position(2, 12, 12), "QA4", "Quality pulse A4",
                        BitsValue.of(0, "High quality pulse A4"),
                        BitsValue.of(1, "Low quality pulse A4")),
                bit(I048_060_QA2, position(2, 11, 11), "QA2", "Quality pulse A2",
                        BitsValue.of(0, "High quality pulse A2"),
                        BitsValue.of(1, "Low quality pulse A2")),
                bit(I048_060_QA1, position(2, 10, 10), "QA1", "Quality pulse A1",
                        BitsValue.of(0, "High quality pulse A1"),
                        BitsValue.of(1, "Low quality pulse A1")),
                bit(I048_060_QB4, position(2, 9, 9), "QB4", "Quality pulse B4",
                        BitsValue.of(0, "High quality pulse B4"),
                        BitsValue.of(1, "Low quality pulse B4")),
                bit(I048_060_QB2, position(2, 8, 8), "QB2", "Quality pulse B2",
                        BitsValue.of(0, "High quality pulse B2"),
                        BitsValue.of(1, "Low quality pulse B2")),
                bit(I048_060_QB1, position(2, 7, 7), "QB1", "Quality pulse B1",
                        BitsValue.of(0, "High quality pulse B1"),
                        BitsValue.of(1, "Low quality pulse B1")),
                bit(I048_060_QC4, position(2, 6, 6), "QC4", "Quality pulse C4",
                        BitsValue.of(0, "High quality pulse C4"),
                        BitsValue.of(1, "Low quality pulse C4")),
                bit(I048_060_QC2, position(2, 5, 5), "QC2", "Quality pulse C2",
                        BitsValue.of(0, "High quality pulse C2"),
                        BitsValue.of(1, "Low quality pulse C2")),
                bit(I048_060_QC1, position(2, 4, 4), "QC1", "Quality pulse C1",
                        BitsValue.of(0, "High quality pulse C1"),
                        BitsValue.of(1, "Low quality pulse C1")),
                bit(I048_060_QD4, position(2, 3, 3), "QD4", "Quality pulse D4",
                        BitsValue.of(0, "High quality pulse D4"),
                        BitsValue.of(1, "Low quality pulse D4")),
                bit(I048_060_QD2, position(2, 2, 2), "QD2", "Quality pulse D2",
                        BitsValue.of(0, "High quality pulse D2"),
                        BitsValue.of(1, "Low quality pulse D2")),
                bit(I048_060_QD1, position(2, 1, 1), "QD1", "Quality pulse D1",
                        BitsValue.of(0, "High quality pulse D1"),
                        BitsValue.of(1, "Low quality pulse D1")));

        final DataItem i048_060 = DataItem.from(f048_060, "Mode-2 Code Confidence Indicator", false,
                "Confidence level for each bit of a Mode-2 reply as provided by a monopulse SSR station");

        final DataFormat f048_065 = fixed(I048_065, 1,
                spare(I048_065_SP8, position(1, 8, 8)),
                spare(I048_065_SP7, position(1, 7, 7)),
                spare(I048_065_SP6, position(1, 6, 6)),
                bit(I048_065_QA4, position(1, 5, 5), "QA4", "Quality pulse A4",
                        BitsValue.of(0, "High quality pulse A4"),
                        BitsValue.of(1, "Low quality pulse A4")),
                bit(I048_065_QA2, position(1, 4, 4), "QA2", "Quality pulse A2",
                        BitsValue.of(0, "High quality pulse A2"),
                        BitsValue.of(1, "Low quality pulse A2")),
                bit(I048_065_QA1, position(1, 3, 3), "QA1", "Quality pulse A1",
                        BitsValue.of(0, "High quality pulse A1"),
                        BitsValue.of(1, "Low quality pulse A1")),
                bit(I048_065_QB2, position(1, 2, 2), "QB2", "Quality pulse B2",
                        BitsValue.of(0, "High quality pulse B2"),
                        BitsValue.of(1, "Low quality pulse B2")),
                bit(I048_065_QB1, position(1, 1, 1), "QB1", "Quality pulse B1",
                        BitsValue.of(0, "High quality pulse B1"),
                        BitsValue.of(1, "Low quality pulse B1")));

        final DataItem i048_065 = DataItem.from(f048_065, "Mode-1 Code Confidence Indicator", false,
                "Confidence level for each bit of a Mode-1 reply as provided by a monopulse SSR station");

        final DataFormat f048_070 = fixed(I048_070, 2,
                bit(I048_070_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I048_070_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I048_070_L, position(2, 14, 14), "L", "Reply",
                        BitsValue.of(0, "Mode-3/A code as derived from the reply of the transponder"),
                        BitsValue.of(1, "Mode-3/A code not extracted during the last scan")),
                spare(I048_070_SP13, position(2, 13, 13)),
                octal(I048_070_MODE3A, position(2, 12, 1), "MODE3A", "Mode-3/A Code"));

        final DataItem i048_070 = DataItem.from(f048_070, "Mode-3/A Code in Octal Representation", false,
                "Mode-3/A code converted into octal representation");

        final DataFormat f048_080 = fixed(I048_080, 2,
                spare(I048_080_SP16, position(2, 16, 16)),
                spare(I048_080_SP15, position(2, 15, 15)),
                spare(I048_080_SP14, position(2, 14, 14)),
                spare(I048_080_SP13, position(2, 13, 13)),
                bit(I048_080_QA4, position(2, 12, 12), "QA4", "Quality pulse A4",
                        BitsValue.of(0, "High quality pulse A4"),
                        BitsValue.of(1, "Low quality pulse A4")),
                bit(I048_080_QA2, position(2, 11, 11), "QA2", "Quality pulse A2",
                        BitsValue.of(0, "High quality pulse A2"),
                        BitsValue.of(1, "Low quality pulse A2")),
                bit(I048_080_QA1, position(2, 10, 10), "QA1", "Quality pulse A1",
                        BitsValue.of(0, "High quality pulse A1"),
                        BitsValue.of(1, "Low quality pulse A1")),
                bit(I048_080_QB4, position(2, 9, 9), "QB4", "Quality pulse B4",
                        BitsValue.of(0, "High quality pulse B4"),
                        BitsValue.of(1, "Low quality pulse B4")),
                bit(I048_080_QB2, position(2, 8, 8), "QB2", "Quality pulse B2",
                        BitsValue.of(0, "High quality pulse B2"),
                        BitsValue.of(1, "Low quality pulse B2")),
                bit(I048_080_QB1, position(2, 7, 7), "QB1", "Quality pulse B1",
                        BitsValue.of(0, "High quality pulse B1"),
                        BitsValue.of(1, "Low quality pulse B1")),
                bit(I048_080_QC4, position(2, 6, 6), "QC4", "Quality pulse C4",
                        BitsValue.of(0, "High quality pulse C4"),
                        BitsValue.of(1, "Low quality pulse C4")),
                bit(I048_080_QC2, position(2, 5, 5), "QC2", "Quality pulse C2",
                        BitsValue.of(0, "High quality pulse C2"),
                        BitsValue.of(1, "Low quality pulse C2")),
                bit(I048_080_QC1, position(2, 4, 4), "QC1", "Quality pulse C1",
                        BitsValue.of(0, "High quality pulse C1"),
                        BitsValue.of(1, "Low quality pulse C1")),
                bit(I048_080_QD4, position(2, 3, 3), "QD4", "Quality pulse D4",
                        BitsValue.of(0, "High quality pulse D4"),
                        BitsValue.of(1, "Low quality pulse D4")),
                bit(I048_080_QD2, position(2, 2, 2), "QD2", "Quality pulse D2",
                        BitsValue.of(0, "High quality pulse D2"),
                        BitsValue.of(1, "Low quality pulse D2")),
                bit(I048_080_QD1, position(2, 1, 1), "QD1", "Quality pulse D1",
                        BitsValue.of(0, "High quality pulse D1"),
                        BitsValue.of(1, "Low quality pulse D1")));

        final DataItem i048_080 = DataItem.from(f048_080, "Mode-3/A Code Confidence Indicator", false,
                "Confidence level for each bit of a Mode-3/A reply as provided by a monopulse SSR station");

        final DataFormat f048_090 = fixed(I048_090, 2,
                bit(I048_090_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I048_090_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                umeasure(I048_090_FL, position(2, 14, 1), "FL", 0.25, UnitOfMeasure.ALTITUDE_FL, "Flight Level"));

        final DataItem i048_090 = DataItem.from(f048_090, "Flight Level in Binary Representation", false,
                "Flight Level converted into binary representation");

        final DataFormat f048_100 = fixed(I048_100, 4,
                bit(I048_100_V, position(4, 32, 32), "V", "Validated",
                        BitsValue.of(0, "Code validated"),
                        BitsValue.of(1, "Code not validated")),
                bit(I048_100_G, position(4, 31, 31), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                spare(I048_100_SP30, position(4, 30, 30)),
                spare(I048_100_SP29, position(4, 29, 29)),
                graycode(I048_100_MODEC, position(4, 28, 17), "MODEC", "Mode-C reply in Gray notation"),
                spare(I048_100_SP16, position(4, 16, 16)),
                spare(I048_100_SP15, position(4, 15, 15)),
                spare(I048_100_SP14, position(4, 14, 14)),
                spare(I048_100_SP13, position(4, 13, 13)),
                bit(I048_100_QC1, position(4, 12, 12), "QC1", "Quality pulse C1",
                        BitsValue.of(0, "High quality pulse C1"),
                        BitsValue.of(1, "Low quality pulse C1")),
                bit(I048_100_QA1, position(4, 11, 11), "QA1", "Quality pulse A1",
                        BitsValue.of(0, "High quality pulse A1"),
                        BitsValue.of(1, "Low quality pulse A1")),
                bit(I048_100_QC2, position(4, 10, 10), "QC2", "Quality pulse C2",
                        BitsValue.of(0, "High quality pulse C2"),
                        BitsValue.of(1, "Low quality pulse C2")),
                bit(I048_100_QA2, position(4, 9, 9), "QA2", "Quality pulse A2",
                        BitsValue.of(0, "High quality pulse A2"),
                        BitsValue.of(1, "Low quality pulse A2")),
                bit(I048_100_QC4, position(4, 8, 8), "QC4", "Quality pulse C4",
                        BitsValue.of(0, "High quality pulse C4"),
                        BitsValue.of(1, "Low quality pulse C4")),
                bit(I048_100_QA4, position(4, 7, 7), "QA4", "Quality pulse A4",
                        BitsValue.of(0, "High quality pulse A4"),
                        BitsValue.of(1, "Low quality pulse A4")),
                bit(I048_100_QB1, position(4, 6, 6), "QB1", "Quality pulse B1",
                        BitsValue.of(0, "High quality pulse B1"),
                        BitsValue.of(1, "Low quality pulse B1")),
                bit(I048_100_QD1, position(4, 5, 5), "QD1", "Quality pulse D1",
                        BitsValue.of(0, "High quality pulse D1"),
                        BitsValue.of(1, "Low quality pulse D1")),
                bit(I048_100_QB2, position(4, 4, 4), "QB2", "Quality pulse B2",
                        BitsValue.of(0, "High quality pulse B2"),
                        BitsValue.of(1, "Low quality pulse B2")),
                bit(I048_100_QD2, position(4, 3, 3), "QD2", "Quality pulse D2",
                        BitsValue.of(0, "High quality pulse D2"),
                        BitsValue.of(1, "Low quality pulse D2")),
                bit(I048_100_QB4, position(4, 2, 2), "QB4", "Quality pulse B4",
                        BitsValue.of(0, "High quality pulse B4"),
                        BitsValue.of(1, "Low quality pulse B4")),
                bit(I048_100_QD4, position(4, 1, 1), "QD4", "Quality pulse D4",
                        BitsValue.of(0, "High quality pulse D4"),
                        BitsValue.of(1, "Low quality pulse D4")));

        final DataItem i048_100 = DataItem.from(f048_100, "Mode-C Code and Code Confidence Indicator", false,
                "Mode-C height in Gray notation as received from the transponder together with the confidence level for each reply bit as provided by a MSSR/Mode-S station");

        final DataFormat f048_110 = fixed(I048_110, 2,
                spare(I048_110_SP16, position(2, 16, 16)),
                spare(I048_110_SP15, position(2, 15, 15)),
                smeasure(I048_110_3DH, position(2, 14, 1), "3DH", 25.0, UnitOfMeasure.ALTITUDE_FT, "Height measured by 3D radar"));

        final DataItem i048_110 = DataItem.from(f048_110, "Height Measured by a 3D Radar", false,
                "Height of a target as measured by a 3D radar. The height shall use mean sea level as the zero reference level");

        final DataFormat f048_120_CAL = fixed(I048_120_CAL, 2,
                bit(I048_120_CAL_D, position(2, 16, 16), "D", "Doppler speed valid vs. doubtful",
                        BitsValue.of(0, "Doppler speed is valid"),
                        BitsValue.of(1, "Doppler speed is doubtful")),
                spare(I048_120_CAL_SP15, position(2, 15, 15)),
                spare(I048_120_CAL_SP14, position(2, 14, 14)),
                spare(I048_120_CAL_SP13, position(2, 13, 13)),
                spare(I048_120_CAL_SP12, position(2, 12, 12)),
                spare(I048_120_CAL_SP11, position(2, 11, 11)),
                smeasure(I048_120_CAL_CAL, position(2, 10, 1), "CAL", 1.0, UnitOfMeasure.SPEED_M_S, "Calculated Doppler Speed"));

        final DataFormat f048_120_RDS = repetitive(I048_120_RDS, 6, "Raw Doppler Speed",
                umeasure(I048_120_RDS_DOP, position(6, 48, 33), "DOP", 1.0, UnitOfMeasure.SPEED_M_S, "Doppler Speed"),
                umeasure(I048_120_RDS_AMB, position(6, 32, 17), "AMB", 1.0, UnitOfMeasure.SPEED_M_S, "Ambiguity Range"),
                umeasure(I048_120_RDS_FRQ, position(6, 16, 1), "FRQ", 1.0, UnitOfMeasure.FREQUENCY_MHZ, "Transmitter Frequency"));

        final DataFormat f048_120 = compound(I048_120,
                Subfield.of(f048_120_CAL, 1, 1, "CAL", "Calculated Doppler Speed"),
                Subfield.of(f048_120_RDS, 1, 2, "RDS", "Raw Doppler Speed"),
                Subfield.sp(I048_120_SP6, 1, 3),
                Subfield.sp(I048_120_SP5, 1, 4),
                Subfield.sp(I048_120_SP4, 1, 5),
                Subfield.sp(I048_120_SP3, 1, 6),
                Subfield.sp(I048_120_SP2, 1, 7),
                Subfield.fx(I048_120_FX1, 1, 8));

        final DataItem i048_120 = DataItem.from(f048_120, "Radial Doppler Speed", false,
                "Information on the Doppler Speed of the target report");

        final DataFormat f048_130_SRL = fixed(I048_130_SRL, 1,
                umeasure(I048_130_SRL_SRL, position(1, 8, 1), "SRL", 360.0 / (1 << 13), UnitOfMeasure.ANGLE_DEGREE, "SSR Plot Runlength"));
        final DataFormat f048_130_SRR = fixed(I048_130_SRR, 1,
                unsigned(I048_130_SRR_SRR, position(1, 8, 1), "SRR", "Number of received replies for (M)SSR"));
        final DataFormat f048_130_SAM = fixed(I048_130_SAM, 1,
                smeasure(I048_130_SAM_SAM, position(1, 8, 1), "SAM", 1.0, UnitOfMeasure.POWER_DBM, "Amplitude of (M)SSR reply"));
        final DataFormat f048_130_PRL = fixed(I048_130_PRL, 1,
                umeasure(I048_130_PRL_PRL, position(1, 8, 1), "PRL", 360.0 / (1 << 13), UnitOfMeasure.ANGLE_DEGREE, "Primary Plot Runlength"));
        final DataFormat f048_130_PAM = fixed(I048_130_PAM, 1,
                smeasure(I048_130_PAM_PAM, position(1, 8, 1), "PAM", 1.0, UnitOfMeasure.POWER_DBM, "Amplitude of Primary Plot"));
        final DataFormat f048_130_RPD = fixed(I048_130_RPD, 1,
                smeasure(I048_130_RPD_RPD, position(1, 8, 1), "RPD", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Difference in Range between PSR and SSR plot"));
        final DataFormat f048_130_APD = fixed(I048_130_APD, 1,
                smeasure(I048_130_APD_APD, position(1, 8, 1), "APD", 360.0 / (1 << 14), UnitOfMeasure.ANGLE_DEGREE, "Difference in Azimuth between PSR and SSR plot"));

        final DataFormat f048_130 = compound(I048_130,
                Subfield.of(f048_130_SRL, 1, 1, "SRL", "SSR Plot Runlength"),
                Subfield.of(f048_130_SRR, 1, 2, "SRR", "Number of received replies for M(SSR)"),
                Subfield.of(f048_130_SAM, 1, 3, "SAM", "Amplitude of received replies for M(SSR)"),
                Subfield.of(f048_130_PRL, 1, 4, "PRL", "PSR Plot Runlength"),
                Subfield.of(f048_130_PAM, 1, 5, "PAM", "PSR amplitude"),
                Subfield.of(f048_130_RPD, 1, 6, "RPD", "Difference in Range"),
                Subfield.of(f048_130_APD, 1, 7, "APD", "Difference in Azimuth"),
                Subfield.fx(I048_130_FX1, 1, 8));

        final DataItem i048_130 = DataItem.from(f048_130, "Radar Plot Characteristics", false,
                "Additional information on the quality of the target report");

        final DataFormat f048_140 = fixed(I048_140, 3,
                umeasure(I048_140_TOD, position(3, 24, 1), "TOD", 1.0 / 128.0, UnitOfMeasure.TIME_SECONDS, "Time of Day"));

        final DataItem i048_140 = DataItem.from(f048_140, "Time of Day", true,
                "Absolute time stamping expressed as Co-ordinated Universal Time (UTC)");

        final DataFormat f048_161 = fixed(I048_161, 2,
                spare(I048_161_SP16, position(2, 16, 16)),
                spare(I048_161_SP15, position(2, 15, 15)),
                spare(I048_161_SP14, position(2, 14, 14)),
                spare(I048_161_SP13, position(2, 13, 13)),
                unsigned(I048_161_TRN, position(2, 12, 1), "TRN", "Track Number"));

        final DataItem i048_161 = DataItem.from(f048_161, "Track Number", false,
                "An integer value representing a unique reference to a track record within a particular track file");

        final DataFormat f048_170 = extended(I048_170,
                Part.of(I048_170_FP, 1,
                        bit(I048_170_CNF, position(1, 8, 8), "CNF", "Confirmed vs. Tentative Track",
                                BitsValue.of(0, "Confirmed Track"),
                                BitsValue.of(1, "Tentative Track")),
                        bits(I048_170_RAD, position(1, 7, 6), "RAD", "Type of Sensor(s) maintaining Track",
                                BitsValue.of(0, "Combined Track"),
                                BitsValue.of(1, "PSR Track"),
                                BitsValue.of(2, "SSR/Mode-S Track"),
                                BitsValue.of(3, "Invalid")),
                        bit(I048_170_DOU, position(1, 5, 5), "DOU", "Signals level of confidence in plot to track association process",
                                BitsValue.of(0, "Normal confidence"),
                                BitsValue.of(1, "Low confidence in plot to track association")),
                        bit(I048_170_MAH, position(1, 4, 4), "MAH", "Manoeuvre detection in Horizontal Sense",
                                BitsValue.of(0, "No horizontal manoeuvre sensed"),
                                BitsValue.of(1, "Horizontal manoeuvre sensed")),
                        bits(I048_170_CDM, position(1, 3, 2), "CDM", "Climbing/Descending Mode",
                                BitsValue.of(0, "Maintaining"),
                                BitsValue.of(1, "Climbing"),
                                BitsValue.of(2, "Descending"),
                                BitsValue.of(3, "Unknown")),
                        fx(I048_170_FX1, position(1, 1, 1))),
                Part.of(I048_170_X1, 1,
                        bit(I048_170_TRE, position(1, 8, 8), "TRE", "Signal for End of Track",
                                BitsValue.of(0, "Track still alive"),
                                BitsValue.of(1, "End of track lifetime (last report for this track)")),
                        bit(I048_170_GHO, position(1, 7, 7), "GHO", "Ghost vs. true target",
                                BitsValue.of(0, "True target track"),
                                BitsValue.of(1, "Ghost target track")),
                        bit(I048_170_SUP, position(1, 6, 6), "SUP", "Track maintained with track information from neighbouring Node B on the cluster or network",
                                BitsValue.of(0, "No"),
                                BitsValue.of(1, "Yes")),
                        bit(I048_170_TCC, position(1, 5, 5), "TCC", "Type of plot coordinate transformation mechanism",
                                BitsValue.of(0, "Tracking performed in so-called 'Radar Plane', i.e.neither slant range correction nor stereographical projection was applied"),
                                BitsValue.of(1, "Slant range correction and a suitable projection technique are  used to track in a 2D.reference plane, tangential to the earth model at the Radar Site co-ordinates")),
                        spare(I048_170_SP4, position(1, 4, 4)),
                        spare(I048_170_SP3, position(1, 3, 3)),
                        spare(I048_170_SP2, position(1, 2, 2)),
                        fx(I048_170_FX2, position(1, 1, 1))));

        final DataItem i048_170 = DataItem.from(f048_170, "Track Status", false,
                "Status of monoradar track (PSR and/or SSR updated)");

        final DataFormat f048_200 = fixed(I048_200, 4,
                umeasure(I048_200_GSP, position(4, 32, 17), "GSP", 1.0 / (1 << 14), UnitOfMeasure.SPEED_NM_S, 2.0, "Calculated Groundspeed"),
                umeasure(I048_200_HDG, position(4, 16, 1), "HDG", 360.0 / (1 << 16), UnitOfMeasure.ANGLE_DEGREE, "Calculated Heading"));

        final DataItem i048_200 = DataItem.from(f048_200, "Calculated Track Velocity in Polar Co-ordinates", false,
                "Calculated track velocity expressed in polar co-ordinates");

        final DataFormat f048_210 = fixed(I048_210, 4,
                umeasure(I048_210_SIGX, position(4, 32, 25), "SIGX", 1.0 / 128.0, UnitOfMeasure.DISTANCE_NM, "Standard Deviation on the horizontal axis of the local grid system"),
                umeasure(I048_210_SIGY, position(4, 24, 17), "SIGY", 1.0 / 128.0, UnitOfMeasure.DISTANCE_NM, "Standard Deviation on the vertical axis of the local grid system"),
                umeasure(I048_210_SIGV, position(4, 16, 9), "SIGV", 1.0 / (1 << 14), UnitOfMeasure.SPEED_NM_S, "Standard Deviation on the groundspeed within the local grid system"),
                umeasure(I048_210_SIGH, position(4, 8, 1), "SIGH", 360.0 / (1 << 12), UnitOfMeasure.ANGLE_DEGREE, "Standard Deviation on the heading within the local grid system"));

        final DataItem i048_210 = DataItem.from(f048_210, "Track Quality", false,
                "Track quality in the form of a vector of standard deviations");

        final DataFormat f048_220 = fixed(I048_220, 3,
                hex(I048_220_ADDR, position(3, 24, 1), "ADDR", "Aircraft Address"));

        final DataItem i048_220 = DataItem.from(f048_220, "Aircraft Address", false,
                "Aircraft address (24-bits Mode-S address) assigned uniquely to each aircraft");

        final DataFormat f048_230 = fixed(I048_230, 2,
                bits(I048_230_COM, position(2, 16, 14), "COM", "Communications capability of the transponder",
                        BitsValue.of(0, "No communications capability (surveillance only)"),
                        BitsValue.of(1, "Comm. A and Comm. B capability"),
                        BitsValue.of(2, "Comm. A, Comm. B and Uplink ELM"),
                        BitsValue.of(3, "Comm. A, Comm. B, Uplink ELM and Downlink ELM"),
                        BitsValue.of(4, "Level 5 Transponder capability")),
                bits(I048_230_STAT, position(2, 13, 11), "STAT", "Flight Status",
                        BitsValue.of(0, "No alert, no SPI, aircraft airborne"),
                        BitsValue.of(1, "No alert, no SPI, aircraft on ground"),
                        BitsValue.of(2, "Alert, no SPI, aircraft airborne"),
                        BitsValue.of(3, "Alert, no SPI, aircraft on ground"),
                        BitsValue.of(4, "Alert, SPI, aircraft airborne or on ground"),
                        BitsValue.of(5, "No alert, SPI, aircraft airborne or on ground"),
                        BitsValue.of(6, "Not assigned"),
                        BitsValue.of(7, "Unknown")),
                bit(I048_230_SI, position(2, 10, 10), "SI", "SI/II Transponder Capability",
                        BitsValue.of(0, "SI-Code Capable"),
                        BitsValue.of(1, "II-Code Capable")),
                spare(I048_230_SP9, position(2, 9, 9)),
                bit(I048_230_MSSC, position(2, 8, 8), "MSSC", "Mode-S Specific Service Capability",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bit(I048_230_ARC, position(2, 7, 7), "ARC", "Altitude reporting capability",
                        BitsValue.of(0, "100 ft resolution"),
                        BitsValue.of(1, "25 ft resolution")),
                bit(I048_230_AIC, position(2, 6, 6), "AIC", "Aircraft identification capability",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bit(I048_230_B1A, position(2, 5, 5), "B1A", "BDS 1,0 bit 16",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active")),
                unsigned(I048_230_B1B, position(2, 4, 1), "B1B", "BDS 1,0 bits 37/40"));

        final DataItem i048_230 = DataItem.from(f048_230, "Communications/ACAS Capability and Flight Status", false,
                "Communications capability of the transponder, capability of the on-board ACAS equipment and flight status");

        final DataFormat f048_240 = fixed(I048_240, 6,
                ia5(I048_240_IDENT, position(6, 48, 1), "IDENT", "Aircraft Identification"));

        final DataItem i048_240 = DataItem.from(f048_240, "Aircraft Identification", false,
                "Aircraft identification (in 8 characters) obtained from an aircraft equipped with a Mode-S transponder");

        final DataFormat f048_250 = mbdata(I048_250,
                hex(I048_250_MBDATA, position(8, 64, 9), "MBDATA", "Mode S Comm B Message Data"),
                unsigned(I048_250_BDS1, position(8, 8, 5), "BDS1", "Comm B Data Buffer Store 1 Address"),
                unsigned(I048_250_BDS2, position(8, 4, 1), "BDS2", "Comm B Data Buffer Store 2 Address"));

        final DataItem i048_250 = DataItem.from(f048_250, "Mode-S MB Data", false,
                "Mode-S Comm B Data as extracted from the aircraft transponder");

        final DataFormat f048_260 = fixed(I048_260, 7,
                hex(I048_260_BDS30, position(7, 56, 49), "BDS30", "BDS Code 3,0"),
                bit(I048_260_ARA41, position(7, 48, 48), "ARA41", "Active Resolution Advisories bit 41",
                        BitsValue.of(0, "There is more than one threat and the RA is intended to provide separation below some threat(s) and above some other threat(s) or no RA has been generated (when MTE = 0)"),
                        BitsValue.of(1, "Either there is only one threat or the RA is intended to provide separation in the same direction for all threats")),
                bit(I048_260_ARA42, position(7, 47, 47), "ARA42", "Active Resolution Advisories bit 42",
                        BitsValue.of(0, "RA is preventive or RA does not require a correction in the upward sense"),
                        BitsValue.of(1, "RA is corrective or RA requires a correction in the upward sense")),
                bit(I048_260_ARA43, position(7, 46, 46), "ARA43", "Active Resolution Advisories bit 43",
                        BitsValue.of(0, "Upward sense RA has been generated or RA does not require a positive climb"),
                        BitsValue.of(1, "Downward sense RA has been generated or RA requires a positive climb")),
                bit(I048_260_ARA44, position(7, 45, 45), "ARA44", "Active Resolution Advisories bit 44",
                        BitsValue.of(0, "RA is not increased rate or RA does not require a correction in the downward sense"),
                        BitsValue.of(1, "RA is increased rate or RA requires a correction in the downward sense")),
                bit(I048_260_ARA45, position(7, 44, 44), "ARA45", "Active Resolution Advisories bit 45",
                        BitsValue.of(0, "RA is not a sense reversal or RA does not require a positive descend"),
                        BitsValue.of(1, "RA is a sense reversal or RA requires a positive descend")),
                bit(I048_260_ARA46, position(7, 43, 43), "ARA46", "Active Resolution Advisories bit 46",
                        BitsValue.of(0, "RA is not altitude crossing or RA does not require a crossing"),
                        BitsValue.of(1, "RA is altitude crossing or RA requires a crossing")),
                bit(I048_260_ARA47, position(7, 42, 42), "ARA47", "Active Resolution Advisories bit 47",
                        BitsValue.of(0, "RA is vertical speed limit or RA is not a sense reversal"),
                        BitsValue.of(1, "RA is positive or RA is a sense reversal")),
                unsigned(I048_260_ACAS3, position(7, 41, 35), "ACAS3", "Reserved for ACAS III"),
                bit(I048_260_RAC55, position(7, 34, 34), "RAC55", "Resolution Advisory Complement bit 55",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not pass below")),
                bit(I048_260_RAC56, position(7, 33, 33), "RAC56", "Resolution Advisory Complement bit 56",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not pass above")),
                bit(I048_260_RAC57, position(7, 32, 32), "RAC57", "Resolution Advisory Complement bit 57",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not turn left")),
                bit(I048_260_RAC58, position(7, 31, 31), "RAC58", "Resolution Advisory Complement bit 58",
                        BitsValue.of(0, "Not active"),
                        BitsValue.of(1, "Active Do not turn right")),
                bit(I048_260_RAT, position(7, 30, 30), "RAT", "RA Terminated",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bit(I048_260_MTE, position(7, 29, 29), "MTE", "Multiple Threat Encounter",
                        BitsValue.of(0, "No"),
                        BitsValue.of(1, "Yes")),
                bits(I048_260_TTI, position(7, 28, 27), "TTI", "Threat Type Indicator",
                        BitsValue.of(0, "No identity data in TID"),
                        BitsValue.of(1, "TID contains a Mode-S transponder address"),
                        BitsValue.of(2, "TID contains altitude, range and bearing data"),
                        BitsValue.of(3, "Not assigned")),
                unsigned(I048_260_TID, position(7, 26, 1), "TID", "Threat Identity Data"));

        final DataItem i048_260 = DataItem.from(f048_260, "ACAS Resolution Advisory Report", false,
                "Currently active Resolution Advisory (RA), if any, generated by the ACAS associated with the transponder transmitting the report and threat identity data");

        final DataFormat f048_RE_MD5_SUM = fixed(I048_RE_MD5_SUM, 1,
                bit(I048_RE_MD5_SUM_M5, position(1, 8, 8), "M5", "Mode-5 interrogation",
                        BitsValue.of(0, "No Mode-5 interrogation"),
                        BitsValue.of(1, "Mode-5 interrogation")),
                bit(I048_RE_MD5_SUM_ID, position(1, 7, 7), "ID", "Authenticated Mode-5 ID reply",
                        BitsValue.of(0, "No authenticated Mode-5 ID reply"),
                        BitsValue.of(1, "Authenticated Mode-5 ID reply")),
                bit(I048_RE_MD5_SUM_DA, position(1, 6, 6), "DA", "Authenticated Mode-5 Data reply or report (i.e any valid Mode-5 reply type other than ID",
                        BitsValue.of(0, "No authenticated Mode-5 Data reply or report"),
                        BitsValue.of(1, "Authenticated Mode-5 Data reply or report (i.e any valid Mode-5 reply type other than ID")),
                bit(I048_RE_MD5_SUM_M1, position(1, 5, 5), "M1", "Mode-1 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-1 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-1 code from Mode-5 reply")),
                bit(I048_RE_MD5_SUM_M2, position(1, 4, 4), "M2", "Mode-2 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-2 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-2 code from Mode-5 reply")),
                bit(I048_RE_MD5_SUM_M3, position(1, 3, 3), "M3", "Mode-3 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-3 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-3 code from Mode-5 reply")),
                bit(I048_RE_MD5_SUM_MC, position(1, 2, 2), "MC", "Mode-C altitude from Mode-5 reply",
                        BitsValue.of(0, "Mode-C altitude not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-C altitude from Mode-5 reply")),
                spare(I048_RE_MD5_SUM_SP1, position(1, 1, 1)));

        final DataFormat f048_RE_MD5_PMN = fixed(I048_RE_MD5_PMN, 4,
                spare(I048_RE_MD5_PMN_SP32, position(4, 32, 32)),
                spare(I048_RE_MD5_PMN_SP31, position(4, 31, 31)),
                unsigned(I048_RE_MD5_PMN_PIN, position(4, 30, 17), "PIN", "PIN Code"),
                spare(I048_RE_MD5_PMN_SP16, position(4, 16, 16)),
                spare(I048_RE_MD5_PMN_SP15, position(4, 15, 15)),
                bit(I048_RE_MD5_PMN_NAV, position(4, 14, 14), "NAV", "Validity of National Origin",
                        BitsValue.of(0, "National Origin is valid"),
                        BitsValue.of(1, "National Origin is invalid")),
                unsigned(I048_RE_MD5_PMN_NAT, position(4, 13, 9), "NAT", "National Origin"),
                spare(I048_RE_MD5_PMN_SP8, position(4, 8, 8)),
                spare(I048_RE_MD5_PMN_SP7, position(4, 7, 7)),
                unsigned(I048_RE_MD5_PMN_MIS, position(4, 6, 1), "MIS", "Mission Code"));

        final DataFormat f048_RE_MD5_POS = fixed(I048_RE_MD5_POS, 6,
                smeasure(I048_RE_MD5_POS_LAT, position(6, 48, 25), "LAT", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I048_RE_MD5_POS_LON, position(6, 24, 1), "LON", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataFormat f048_RE_MD5_GA = fixed(I048_RE_MD5_GA, 2,
                spare(I048_RE_MD5_GA_SP16, position(2, 16, 16)),
                bit(I048_RE_MD5_GA_RES, position(2, 15, 15), "RES", "Resolution with which the GNSS-derived Altitude (GA) is reported",
                        BitsValue.of(0, "GA reported in 100 ft increments"),
                        BitsValue.of(1, "GA reported in 25 ft increments")),
                smeasure(I048_RE_MD5_GA_ALT, position(2, 14, 1), "ALT", 25.0, UnitOfMeasure.ALTITUDE_FT, -1000, 150000, "GNSS-derived Altitude of target"));

        final DataFormat f048_RE_MD5_EM1 = fixed(I048_RE_MD5_EM1, 2,
                bit(I048_RE_MD5_EM1_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code not validated"),
                        BitsValue.of(1, "Code validated")),
                bit(I048_RE_MD5_EM1_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I048_RE_MD5_EM1_L, position(2, 14, 14), "L", "Reply",
                        BitsValue.of(0, "Mode-1 code derived from the reply of the transponder"),
                        BitsValue.of(1, "Mode-1 code not extracted during the last scan")),
                spare(I048_RE_MD5_EM1_SP13, position(2, 13, 13)),
                octal(I048_RE_MD5_EM1_MODE1, position(2, 12, 1), "MODE1", "Extended Mode-1 Code in octal representation"));

        final DataFormat f048_RE_MD5_TOS = fixed(I048_RE_MD5_TOS, 1,
                smeasure(I048_RE_MD5_TOS_TOS, position(1, 8, 1), "TOS", 1.0 / 128, UnitOfMeasure.TIME_SECONDS, "Time Offset"));

        final DataFormat f048_RE_MD5_XP = fixed(I048_RE_MD5_XP, 1,
                spare(I048_RE_MD5_XP_SP8, position(1, 8, 8)),
                spare(I048_RE_MD5_XP_SP7, position(1, 7, 7)),
                bit(I048_RE_MD5_XP_XP, position(1, 6, 6), "XP", "X-pulse from Mode-5 PIN reply/report",
                        BitsValue.of(0, "X-pulse not present"),
                        BitsValue.of(1, "X-pulse present")),
                bit(I048_RE_MD5_XP_X5, position(1, 5, 5), "X5", "X-pulse from Mode-5 Data reply or report",
                        BitsValue.of(0, "X-pulse set to zero or no authenticated Data reply or report received"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_MD5_XP_XC, position(1, 4, 4), "XC", "X-pulse from Mode-C reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-C reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_MD5_XP_X3, position(1, 3, 3), "X3", "X-pulse from Mode-3/A reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-3/A reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_MD5_XP_X2, position(1, 2, 2), "X2", "X-pulse from Mode-2 reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-2 reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_MD5_XP_X1, position(1, 1, 1), "X1", "X-pulse from Mode-1 reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-1 reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")));

        final DataFormat f048_RE_MD5 = compound(I048_RE_MD5,
                Subfield.of(f048_RE_MD5_SUM, 1, 1, "SUM", "Mode-5 Summary"),
                Subfield.of(f048_RE_MD5_PMN, 1, 2, "PMN", "Mode-5 PIN/ National"),
                Subfield.of(f048_RE_MD5_POS, 1, 3, "POS", "Mode-5 Reported Position"),
                Subfield.of(f048_RE_MD5_GA, 1, 4, "GA", "Mode-5 GNSS-derived Altitude"),
                Subfield.of(f048_RE_MD5_EM1, 1, 5, "EM1", "Extended Mode-1 Code in Octal Representation"),
                Subfield.of(f048_RE_MD5_TOS, 1, 6, "TOS", "Time Offset for POS and GA"),
                Subfield.of(f048_RE_MD5_XP, 1, 7, "XP", " Pulse Presence"),
                Subfield.fx(I048_RE_MD5_FX1, 1, 8));

        final DataFormat f048_RE_M5N_SUM = fixed(I048_RE_M5N_SUM, 1,
                bit(I048_RE_M5N_SUM_M5, position(1, 8, 8), "M5", "Mode-5 interrogation",
                        BitsValue.of(0, "No Mode-5 interrogation"),
                        BitsValue.of(1, "Mode-5 interrogation")),
                bit(I048_RE_M5N_SUM_ID, position(1, 7, 7), "ID", "Authenticated Mode-5 ID reply",
                        BitsValue.of(0, "No authenticated Mode-5 ID reply"),
                        BitsValue.of(1, "Authenticated Mode-5 ID reply")),
                bit(I048_RE_M5N_SUM_DA, position(1, 6, 6), "DA", "Authenticated Mode-5 Data reply or report (i.e any valid Mode-5 reply type other than ID",
                        BitsValue.of(0, "No authenticated Mode-5 Data reply or report"),
                        BitsValue.of(1, "Authenticated Mode-5 Data reply or report (i.e any valid Mode-5 reply type other than ID")),
                bit(I048_RE_M5N_SUM_M1, position(1, 5, 5), "M1", "Mode-1 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-1 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-1 code from Mode-5 reply")),
                bit(I048_RE_M5N_SUM_M2, position(1, 4, 4), "M2", "Mode-2 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-2 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-2 code from Mode-5 reply")),
                bit(I048_RE_M5N_SUM_M3, position(1, 3, 3), "M3", "Mode-3 code from Mode-5 reply",
                        BitsValue.of(0, "Mode-3 code not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-3 code from Mode-5 reply")),
                bit(I048_RE_M5N_SUM_MC, position(1, 2, 2), "MC", "Mode-C altitude from Mode-5 reply",
                        BitsValue.of(0, "Mode-C altitude not present or not from Mode-5 reply"),
                        BitsValue.of(1, "Mode-C altitude from Mode-5 reply")),
                spare(I048_RE_M5N_SUM_SP1, position(1, 1, 1)));

        final DataFormat f048_RE_M5N_PMN = fixed(I048_RE_M5N_PMN, 4,
                spare(I048_RE_M5N_PMN_SP32, position(4, 32, 32)),
                spare(I048_RE_M5N_PMN_SP31, position(4, 31, 31)),
                unsigned(I048_RE_M5N_PMN_PIN, position(4, 30, 17), "PIN", "PIN Code"),
                spare(I048_RE_M5N_PMN_SP16, position(4, 16, 16)),
                spare(I048_RE_M5N_PMN_SP15, position(4, 15, 15)),
                spare(I048_RE_M5N_PMN_SP14, position(4, 14, 14)),
                spare(I048_RE_M5N_PMN_SP13, position(4, 13, 13)),
                bit(I048_RE_M5N_PMN_NOV, position(4, 12, 12), "NOV", "Validity of National Origin",
                        BitsValue.of(0, "National Origin is valid"),
                        BitsValue.of(1, "National Origin is invalid")),
                unsigned(I048_RE_M5N_PMN_NO, position(4, 11, 1), "NO", "National Origin"));

        final DataFormat f048_RE_M5N_POS = fixed(I048_RE_M5N_POS, 6,
                smeasure(I048_RE_M5N_POS_LAT, position(6, 48, 25), "LAT", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, -90.0, 90.0, "Latitude"),
                smeasure(I048_RE_M5N_POS_LON, position(6, 24, 1), "LON", 180.0 / (1 << 23), UnitOfMeasure.ANGLE_DEGREE, "Longitude"));

        final DataFormat f048_RE_M5N_GA = fixed(I048_RE_M5N_GA, 2,
                spare(I048_RE_M5N_GA_SP16, position(2, 16, 16)),
                bit(I048_RE_M5N_GA_RES, position(2, 15, 15), "RES", "Resolution with which the GNSS-derived Altitude (GA) is reported",
                        BitsValue.of(0, "GA reported in 100 ft increments"),
                        BitsValue.of(1, "GA reported in 25 ft increments")),
                smeasure(I048_RE_M5N_GA_ALT, position(2, 14, 1), "ALT", 25.0, UnitOfMeasure.ALTITUDE_FT, -1000, 150000, "GNSS-derived Altitude of target"));

        final DataFormat f048_RE_M5N_EM1 = fixed(I048_RE_M5N_EM1, 2,
                bit(I048_RE_M5N_EM1_V, position(2, 16, 16), "V", "Validated",
                        BitsValue.of(0, "Code not validated"),
                        BitsValue.of(1, "Code validated")),
                bit(I048_RE_M5N_EM1_G, position(2, 15, 15), "G", "Garbled",
                        BitsValue.of(0, "Default"),
                        BitsValue.of(1, "Garbled code")),
                bit(I048_RE_M5N_EM1_L, position(2, 14, 14), "L", "Reply",
                        BitsValue.of(0, "Mode-1 code derived from the reply of the transponder"),
                        BitsValue.of(1, "Mode-1 code not extracted during the last scan")),
                spare(I048_RE_M5N_EM1_SP13, position(2, 13, 13)),
                octal(I048_RE_M5N_EM1_MODE1, position(2, 12, 1), "MODE1", "Extended Mode-1 Code in octal representation"));

        final DataFormat f048_RE_M5N_TOS = fixed(I048_RE_M5N_TOS, 1,
                smeasure(I048_RE_M5N_TOS_TOS, position(1, 8, 1), "TOS", 1.0 / 128, UnitOfMeasure.TIME_SECONDS, "Time Offset"));

        final DataFormat f048_RE_M5N_XP = fixed(I048_RE_M5N_XP, 1,
                spare(I048_RE_M5N_XP_SP8, position(1, 8, 8)),
                spare(I048_RE_M5N_XP_SP7, position(1, 7, 7)),
                bit(I048_RE_M5N_XP_XP, position(1, 6, 6), "XP", "X-pulse from Mode-5 PIN reply/report",
                        BitsValue.of(0, "X-pulse not present"),
                        BitsValue.of(1, "X-pulse present")),
                bit(I048_RE_M5N_XP_X5, position(1, 5, 5), "X5", "X-pulse from Mode-5 Data reply or report",
                        BitsValue.of(0, "X-pulse set to zero or no authenticated Data reply or report received"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_M5N_XP_XC, position(1, 4, 4), "XC", "X-pulse from Mode-C reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-C reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_M5N_XP_X3, position(1, 3, 3), "X3", "X-pulse from Mode-3/A reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-3/A reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_M5N_XP_X2, position(1, 2, 2), "X2", "X-pulse from Mode-2 reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-2 reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")),
                bit(I048_RE_M5N_XP_X1, position(1, 1, 1), "X1", "X-pulse from Mode-1 reply",
                        BitsValue.of(0, "X-pulse set to zero or no Mode-1 reply"),
                        BitsValue.of(1, "X-pulse set to one (present)")));

        final DataFormat f048_RE_M5N_FOM = fixed(I048_RE_M5N_FOM, 1,
                spare(I048_RE_M5N_FOM_SP8, position(1, 8, 8)),
                spare(I048_RE_M5N_FOM_SP7, position(1, 7, 7)),
                spare(I048_RE_M5N_FOM_SP6, position(1, 6, 6)),
                unsigned(I048_RE_M5N_FOM_FOM, position(1, 5, 1), "FOM", "Figure of Merit Position Accuracy as extracted and provided by a Mode-5 transponder"));
        final DataFormat f048_RE_M5N = compound(I048_RE_M5N,
                Subfield.of(f048_RE_M5N_SUM, 2, 1, "SUM", "Mode-5 Summary"),
                Subfield.of(f048_RE_M5N_PMN, 2, 2, "PMN", "Mode-5 PIN/ National"),
                Subfield.of(f048_RE_M5N_POS, 2, 3, "POS", "Mode-5 Reported Position"),
                Subfield.of(f048_RE_M5N_GA, 2, 4, "GA", "Mode-5 GNSS-derived Altitude"),
                Subfield.of(f048_RE_M5N_EM1, 2, 5, "EM1", "Extended Mode-1 Code in Octal Representation"),
                Subfield.of(f048_RE_M5N_TOS, 2, 6, "TOS", "Time Offset for POS and GA"),
                Subfield.of(f048_RE_M5N_XP, 2, 7, "XP", " Pulse Presence"),
                Subfield.fx(I048_RE_M5N_FX1, 2, 8),
                Subfield.of(f048_RE_M5N_FOM, 2, 9, "FOM", "Figure of Merit"),
                Subfield.sp(I048_RE_M5N_SP7, 2, 10),
                Subfield.sp(I048_RE_M5N_SP6, 2, 11),
                Subfield.sp(I048_RE_M5N_SP5, 2, 12),
                Subfield.sp(I048_RE_M5N_SP4, 2, 13),
                Subfield.sp(I048_RE_M5N_SP3, 2, 14),
                Subfield.sp(I048_RE_M5N_SP2, 2, 15),
                Subfield.fx(I048_RE_M5N_FX2, 2, 16));

        final DataFormat f048_RE_M4E = extended(I048_RE_M4E,
                Part.of(I048_RE_M4E_FP, 1,
                        spare(I048_RE_M4E_SP8, position(1, 8, 8)),
                        spare(I048_RE_M4E_SP7, position(1, 7, 7)),
                        spare(I048_RE_M4E_SP6, position(1, 6, 6)),
                        spare(I048_RE_M4E_SP5, position(1, 5, 5)),
                        spare(I048_RE_M4E_SP4, position(1, 4, 4)),
                        bits(I048_RE_M4E_FOEFRI, position(1, 3, 2), "FOEFRI", "Mode-4 interrogation",
                                BitsValue.of(0, "No Mode-4 interrogation"),
                                BitsValue.of(1, "Friendly target"),
                                BitsValue.of(2, "Unknown target"),
                                BitsValue.of(3, "No reply")),
                        fx(I048_RE_M4E_FX1, position(1, 1, 1))));

        final DataFormat f048_RE_RPC_SCO = fixed(I048_RE_RPC_SCO, 1,
                unsigned(I048_RE_RPC_SCO_SCO, position(1, 8, 1), "SCO", "Score"));

        final DataFormat f048_RE_RPC_SCR = fixed(I048_RE_RPC_SCR, 2,
                umeasure(I048_RE_RPC_SCR_SCR, position(2, 16, 1), "SCR", 0.1, UnitOfMeasure.POWER_DB, 2550.0, "Signal to Clutter Ratio"));

        final DataFormat f048_RE_RPC_RW = fixed(I048_RE_RPC_RW, 2,
                umeasure(I048_RE_RPC_RW_RW, position(2, 16, 1), "RW", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Range Width"));

        final DataFormat f048_RE_RPC_AR = fixed(I048_RE_RPC_AR, 2,
                umeasure(I048_RE_RPC_AR_AR, position(2, 16, 1), "AR", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Ambiguous Range"));

        final DataFormat f048_RE_RPC = compound(I048_RE_RPC,
                Subfield.of(f048_RE_RPC_SCO, 1, 1, "SCO", "Score"),
                Subfield.of(f048_RE_RPC_SCR, 1, 2, "SCR", "Signal/Clutter Ratio"),
                Subfield.of(f048_RE_RPC_RW, 1, 3, "RW", "Range Width"),
                Subfield.of(f048_RE_RPC_AR, 1, 4, "AR", "Ambiguous Range"),
                Subfield.sp(I048_RE_RPC_SP4, 1, 5),
                Subfield.sp(I048_RE_RPC_SP3, 1, 6),
                Subfield.sp(I048_RE_RPC_SP2, 1, 7),
                Subfield.fx(I048_RE_RPC_FX1, 1, 8));

        final DataFormat f048_RE_ERR = fixed(I048_RE_ERR, 3,
                umeasure(I048_RE_ERR_RHO, position(3, 24, 1), "RHO", 1.0 / 256.0, UnitOfMeasure.DISTANCE_NM, "Rho"));

        final DataFormat f048_RE = explicit(I048_RE, 1, 10,
                Subitem.of(f048_RE_MD5, 1, 1, "MD5", "Mode-5 Reports"),
                Subitem.of(f048_RE_M5N, 1, 2, "M5N", "Mode-5 Reports New Format"),
                Subitem.of(f048_RE_M4E, 1, 3, "M4E", "Extended Mode-4 Report"),
                Subitem.of(f048_RE_RPC, 1, 4, "RPC", "Radar Plot Characteristics"),
                Subitem.of(f048_RE_ERR, 1, 5, "ERR", "Extended Range Report"),
                Subitem.sp(I048_RE_SP3, 1, 6),
                Subitem.sp(I048_RE_SP2, 1, 7),
                Subitem.sp(I048_RE_SP1, 1, 8));

        final DataItem i048_RE_ = DataItem.re(f048_RE);
        final DataItem i048_SP_ = DataItem.sp(I048_SP);
        final DataItem i048_FX1 = DataItem.fx(CAT048_ID, 4, 25);
        final DataItem i048_FX2 = DataItem.fx(CAT048_ID, 4, 17);
        final DataItem i048_FX3 = DataItem.fx(CAT048_ID, 4, 9);
        final DataItem i048_FX4 = DataItem.fx(CAT048_ID, 4, 1);

        final UserApplicationProfile uap = new UserApplicationProfile(
                CAT048_ID,
                i048_010, //32
                i048_140, //31
                i048_020, //30
                i048_040, //29
                i048_070, //28
                i048_090, //27
                i048_130, //26
                i048_FX1, //25
                i048_220, //24
                i048_240, //23
                i048_250, //22
                i048_161, //21
                i048_042, //20
                i048_200, //19
                i048_170, //18
                i048_FX2, //17
                i048_210, //16
                i048_030, //15
                i048_080, //14
                i048_100, //13
                i048_110, //12
                i048_120, //11
                i048_230, //10
                i048_FX3, // 9
                i048_260, // 8
                i048_055, // 7
                i048_050, // 6
                i048_065, // 5
                i048_060, // 4
                i048_SP_, // 3
                i048_RE_, // 2
                i048_FX4);// 1

        CAT048 = new ASTERIXCategory("Transmission of Monoroadar Target Reports", uap);
    }
}
