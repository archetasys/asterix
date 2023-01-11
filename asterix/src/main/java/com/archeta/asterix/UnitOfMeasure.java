/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

public enum UnitOfMeasure {
    ALTITUDE_METER("meter", "m", 1),
    ALTITUDE_KM("kilometer", "km", UnitConstants.KM2MTR),
    ALTITUDE_FT("feet", "ft", UnitConstants.FT2MTR_STD),
    ALTITUDE_FL("flight level", "FL", UnitConstants.FL2MTR),

    ANGLE_RADIAN("radians", "rad", 1),
    ANGLE_GRADIAN("gradians", "gon", 0.015707963267948967),
    ANGLE_DEGREE("degrees", "°", UnitConstants.DEG2RAD),
    ANGLE_TENTH_DEGREE("10 degrees", "10 °", 10 * UnitConstants.DEG2RAD),
    ANGLE_MILS("mils", "mil", 9.817477042468104e-4),

    ANGULAR_VELOCITY_RADIANS_S("radians/second", "rad/s", 1),
    ANGULAR_VELOCITY_DEGREES_S("degrees/second", "°/s", UnitConstants.DEG2RAD),

    DISTANCE_METER("meter", "m", 1),
    DISTANCE_KM("kilometer", "km", UnitConstants.KM2MTR),
    DISTANCE_NM("Nautical Mile", "NM", UnitConstants.NM2MTR),
    DISTANCE_MILE("mile", "mi", UnitConstants.MI2MTR_US),
    DISTANCE_FT("feet", "ft", UnitConstants.FT2MTR_STD),

    FREQUENCY_HZ("hertz", "Hz", 1),
    FREQUENCY_KHZ("kilohertz", "kHz", 1e3),
    FREQUENCY_MHZ("megahertz", "MHz", 1e6),
    FREQUENCY_GHZ("gigahertz", "GHz", 1e9),

    POWER_WATT("watt", "W", 1, false, 0),
    POWER_DBM("decibel-milliwatt", "dBm", 1, false, 0),
    POWER_DB("decibel", "dB", 1, false, 0),

    PRESSURE_PASCAL("pascal", "Pa", 1),
    PRESSURE_BAR("bar", "bar", 100000),
    PRESSURE_MBAR("millibar", "mbar", 100),
    PRESSURE_ATM("standard atmosphere", "atm", 101325.0),
    PRESSURE_PSI("pound per square inch", "psi", 6895.0),
    PRESSURE_MPA("milliPascal", "mPa", 0.001),
    PRESSURE_HPA("hectoPascal", "hPa", 100.0),
    PRESSURE_TORR("torr", "torr", 133.3),

    RATIO("ratio", "", 1),
    RATIO_PERCENTAGE("percentage", "%", 0.01),
    RATIO_PERMILLE("permille", "‰", 0.001),

    SPEED_M_S("meter/second", "m/s", 1),
    SPEED_NM_S("NM/second", "NM/s", UnitConstants.NM2MTR),
    SPEED_NM_H("NM/hour", "NM/h", UnitConstants.NM2MTR / 3600),
    SPEED_FT_S("feet/second", "ft/s", UnitConstants.FT2MTR_STD),
    SPEED_FT_MIN("feet/minute", "ft/min", UnitConstants.FT2MTR_STD / 60),
    SPEED_FL_S("FL/second", "fl/s", UnitConstants.FL2MTR),
    SPEED_KM_H("kilometer/hour", "km/h", UnitConstants.KM2MTR / 3600),
    SPEED_MACH("Mach", "Mach", UnitConstants.MACH2MS),
    SPEED_MPH("miles/hour", "mph", UnitConstants.MI2MTR_US / 3600),
    SPEED_KNOT("knot", "kt", UnitConstants.NM2MTR / 3600),

    TEMPERATURE_CELSIUS("Celsius", "°C", 1, true, 0),
    TEMPERATURE_KELVIN("Kelvin", "K", 1, true, 273.15),

    TIME_NANOSECONDS("nanoseconds", "ns", 1e-9),
    TIME_MICROSECONDS("microseconds", "us", 1e-6),
    TIME_MILLISECONDS("milliseconds", "ms", 1e-3),
    TIME_SECONDS("seconds", "s", 1),
    TIME_MINUTES("minutes", "min", 60),
    TIME_HOURS("hours", "hr", 3600),
    TIME_DAYS("days", "days", 24 * 3600),

    NONE("none", "none", 0);

    private final String name;
    private final String symbol;
    private final double factor;
    private final boolean linear;
    private final double offset;

    UnitOfMeasure(final String name, final String symbol, final double factor) {
        this(name, symbol, factor, true, 0);
    }

    UnitOfMeasure(final String name, final String symbol, final double factor, final boolean linear, final double offset) {
        this.name = name;
        this.symbol = symbol;
        this.factor = factor;
        this.linear = linear;
        this.offset = offset;
    }

    public String uname() {
        return name;
    }

    public String symbol() {
        return symbol;
    }

    public double factor() {
        return factor;
    }

    public boolean isLinear() {
        return linear;
    }

    public double offset() {
        return offset;
    }
}
