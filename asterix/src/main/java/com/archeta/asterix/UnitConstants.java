/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

public final class UnitConstants {
    public static final double RAD2DEG = 180.0 / Math.PI;
    public static final double DEG2RAD = Math.PI / 180.0;
    public static final double KM2MTR = 1000;
    public static final double FL2MTR = 30.48;
    public static final double KM2DM = 0.5468066491688539;
    public static final double DM2KM = 1.8288;
    public static final double DM2NM = 0.9874730021598271;
    public static final double FT2MTR_STD = 0.3048;
    public static final double MTR2FT_STD = 3.280839895013123;
    public static final double FT2MTR_US = 0.30480060960121924;
    public static final double MTR2FT_US = 3.2808333333333333;
    public static final double MI2MTR_US = 1609.3472186944375;
    public static final double MTR2MI_US = 6.213699494949494e-4;
    public static final double FTH2MTR = 1.8288;
    public static final double MTR2FTH = 0.5468066491688539;
    public static final double NM2MTR = 1852;
    public static final double MTR2NM = 5.399568034557236e-4;
    public static final double GLM2MTR = 1.000014;
    public static final double MTR2GLM = 0.9999860001959973;
    public static final double EARTH_RADIUS = 6371100.0;
    public static final double WGS84_A = 6378137.0;
    public static final double WGS84_1_OVER_F = 298.257223563;
    public static final double INCH2CM = 2.54;
    public static final double MACH2MS = 340.3;
    public static final double MPS2KNOT = 1.943844492440605;
    public static final double MPS2MACH = 0.00291545;
    public static final double MPS2KMPH = 3.6;
    public static final double KNOT2MPS = 0.51444444444;
    public static final double MACH2MPS = 343;
    public static final double KMPH2MPS = 1 / MPS2KMPH;
    public static final double MTR2KM = 1.0 / KM2MTR;
    public static final double NM2KM = NM2MTR * MTR2KM;
    public static final double KM2NM = 1.0 / NM2KM;

    /**
     * Private constructor.
     */
    private UnitConstants() {
    }
}
