/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;


import static com.archeta.asterix.ASTERIXConstants.*;
import static com.archeta.asterix.ASTERIXIds.bitsFieldId;
import static com.archeta.asterix.BitsField.position;

public final class ModeSMBData {
    /**
     * The number of octets of each element in Mode-S MB Data Field.
     */
    public static final int NUM_OCTETS = 8;

    private static final BitsValue ST0 = BitsValue.of(0, "Not available");
    private static final BitsValue ST1 = BitsValue.of(1, "Available");
    private static final BitsValue NOT_ACTIVE = BitsValue.of(0, "Not active");
    private static final BitsValue ACTIVE = BitsValue.of(1, "Active");
    private static final BitsValue INVALID = BitsValue.of(0, "Invalid");
    private static final BitsValue VALID = BitsValue.of(1, "Valid");
    private static final BitsValue NIL = BitsValue.of(0, "Nil");
    private static final BitsValue LIGHT = BitsValue.of(1, "Light");
    private static final BitsValue MODERATE = BitsValue.of(2, "Moderate");
    private static final BitsValue SEVERE = BitsValue.of(3, "Severe");
    private static final double ANGLE_LSB = 0.17578125;
    private static final double SPEED_LSB = 2.0;
    private static final double VERTICAL_RATE_LSB = 32.0;
    private static final double ALTITUDE_LSB = 16.0;

    private static final int BDS_40 = 0x40; // Selected Vertical Intention.
    private static final int BDS_44 = 0x44; // Meteorological Routine Air Report.
    private static final int BDS_45 = 0x45; // Meteorological Hazard Report.
    private static final int BDS_50 = 0x50; // Track and Turn Report.
    private static final int BDS_60 = 0x60; // Heading and Speed Report.

    private static BitsField bit(
            final long dataFieldId, final int bitPosition, final String name,
            final String description, final BitsValue value0, final BitsValue value1) {
        return BitsField.bit(bitsFieldId(dataFieldId, bitPosition, BITS_FIELD_ENCODING_VALUE),
                position(NUM_OCTETS, bitPosition, bitPosition), name, description, value0, value1);
    }

    private static BitsField bits(
            final long dataFieldId, final int from, final int to, final String name,
            final String description,
            final BitsValue value0, final BitsValue value1,
            final BitsValue value2, final BitsValue value3) {
        return BitsField.bits(bitsFieldId(dataFieldId, from, BITS_FIELD_ENCODING_VALUES),
                position(NUM_OCTETS, from, to), name, description, value0, value1, value2, value3);
    }

    private static BitsField umeasure(
            final long dataFieldId, final int from, final int to,
            final String name, final double lsb, final UnitOfMeasure unit, final String description) {
        return BitsField.umeasure(bitsFieldId(dataFieldId, from, BITS_FIELD_ENCODING_MEASURE_UNSIGNED),
                position(NUM_OCTETS, from, to), name, lsb, unit, description);
    }

    private static BitsField smeasure(
            final long dataFieldId, final int from, final int to,
            final String name, final double lsb, final UnitOfMeasure unit, final String description) {
        return BitsField.smeasure(bitsFieldId(dataFieldId, from, BITS_FIELD_ENCODING_MEASURE_SIGNED),
                position(NUM_OCTETS, from, to), name, lsb, unit, description);
    }

    private static BitsField spare(final long dataFieldId, final int bitPosition) {
        return BitsField.spare(bitsFieldId(dataFieldId, bitPosition, BITS_FIELD_ENCODING_SPARE),
                position(NUM_OCTETS, bitPosition, bitPosition));
    }

    private static BitsField hex(final long dataFieldId, final String name, final String description) {
        return BitsField.hex(bitsFieldId(dataFieldId, 8, BITS_FIELD_ENCODING_HEX),
                position(NUM_OCTETS, 8, 1), name, description);
    }

    private final BitsFieldList bds40; // Selected Vertical Intention.
    private final BitsFieldList bds44; // Meteorological Routine Air Report.
    private final BitsFieldList bds45; // Meteorological Hazard Report.
    private final BitsFieldList bds50; // Track and Turn Report
    private final BitsFieldList bds60; // Heading and Speed Report
    private final BitsFieldList deflt; // Default

    public ModeSMBData(final long dataFieldId) {
        bds40 = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                bit(dataFieldId, 64, "MCP_ALT_ST", "MCP Altitude Status", ST0, ST1),
                umeasure(dataFieldId, 63, 52, "MCP_ALT", ALTITUDE_LSB, UnitOfMeasure.ALTITUDE_FT, "MCP/FCU Selected Altitude"),
                bit(dataFieldId, 51, "FMS_SALT_ST", "FMS Altitude Status", ST0, ST1),
                umeasure(dataFieldId, 50, 39, "FMS_SALT", ALTITUDE_LSB, UnitOfMeasure.ALTITUDE_FT, "FMS Selected Altitude"),
                bit(dataFieldId, 38, "BPS_ST", "Barometric Pressure Status", ST0, ST1),
                umeasure(dataFieldId, 37, 26, "BPS", 0.1, UnitOfMeasure.PRESSURE_MBAR, "Barometric Pressure"),
                spare(dataFieldId, 25),
                spare(dataFieldId, 24),
                spare(dataFieldId, 23),
                spare(dataFieldId, 22),
                spare(dataFieldId, 21),
                spare(dataFieldId, 20),
                spare(dataFieldId, 19),
                spare(dataFieldId, 18),
                bit(dataFieldId, 17, "MODE_ST", "Status of MCP/FCU Mode Bit", NOT_ACTIVE, ACTIVE),
                bit(dataFieldId, 16, "VNAV", "VNAV Mode", NOT_ACTIVE, ACTIVE),
                bit(dataFieldId, 15, "AHM", "Altitude Hold Mode", NOT_ACTIVE, ACTIVE),
                bit(dataFieldId, 14, "APP", "Approach Mode", NOT_ACTIVE, ACTIVE),
                spare(dataFieldId, 13),
                spare(dataFieldId, 12),
                bit(dataFieldId, 11, "TGT_ALT_ST", "Status of Target Altitude Source Bit",
                        BitsValue.of(0, "No source information provided"),
                        BitsValue.of(1, "Source information deliberately provided")),
                bits(dataFieldId, 10, 9, "TGT_ALT_SRC", "Target Altitude Source",
                        BitsValue.of(0, "Unknown"),
                        BitsValue.of(1, "Aircraft Altitude"),
                        BitsValue.of(2, "FCU/MCP Selected Altitude"),
                        BitsValue.of(3, "FMS Selected Altitude")),
                hex(dataFieldId, "REG_SVI", "BDS Register Selected Vertical Intention"));

        // To allow meteorological data to be collected by ground system.
        bds44 = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                BitsField.bits(bitsFieldId(dataFieldId, 64, BITS_FIELD_ENCODING_VALUES),
                        position(NUM_OCTETS, 64, 61), "FOM_SRC", "Figure of Merit",
                        BitsValue.of(0, "Invalid"),
                        BitsValue.of(1, "INS"),
                        BitsValue.of(2, "GNSS"),
                        BitsValue.of(3, "DME/DME"),
                        BitsValue.of(4, "VOR/DME")),
                bit(dataFieldId, 60, "WIND_ST", "Status Wind Speed and Direction", INVALID, VALID),
                umeasure(dataFieldId, 59, 51, "WIND_SPD", 1.0, UnitOfMeasure.SPEED_KNOT, "Wind Speed"),
                umeasure(dataFieldId, 50, 42, "WIND_DIR", 0.703125, UnitOfMeasure.ANGLE_DEGREE, "Wind Direction (True)"),
                bit(dataFieldId, 41, "TEMP_ST", "Status Static Air Temperature", INVALID, VALID),
                smeasure(dataFieldId, 40, 31, "TEMP", 0.25, UnitOfMeasure.TEMPERATURE_CELSIUS, "Static Air Temperature"),
                bit(dataFieldId, 30, "PRES_ST", "Status Average Static Pressure", INVALID, VALID),
                umeasure(dataFieldId, 29, 19, "PRES_AVG", 1, UnitOfMeasure.PRESSURE_HPA, "Average Static Pressure"),
                bit(dataFieldId, 18, "TURB_ST", "Status Turbulence", INVALID, VALID),
                bits(dataFieldId, 17, 16, "TURB", "Turbulence", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 15, "HUM_ST", "Status Humidity", INVALID, VALID),
                umeasure(dataFieldId, 14, 9, "HUM", 1.5625, UnitOfMeasure.RATIO_PERCENTAGE, "Humidity"),
                hex(dataFieldId, "REG_METAR", "BDS Register Meteorological Routine Air Report"));

        // To provide reports on the severity of meteorological hazards, in particular for low flight.
        bds45 = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                bit(dataFieldId, 64, "TURB_ST", "Status Turbulence", INVALID, VALID),
                bits(dataFieldId, 63, 62, "TURB", "Turbulence", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 61, "WINDS_ST", "Status Wind Shear", INVALID, VALID),
                bits(dataFieldId, 60, 59, "WINDS", "Wind Shear", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 58, "BURST_ST", "Status Microburst", INVALID, VALID),
                bits(dataFieldId, 57, 56, "BURST", "Microburst", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 55, "ICING_ST", "Status Icing", INVALID, VALID),
                bits(dataFieldId, 54, 53, "ICING", "Icing", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 52, "VORTEX_ST", "Status Wake Vortex", INVALID, VALID),
                bits(dataFieldId, 51, 50, "VORTEX", "Wake Vortex", NIL, LIGHT, MODERATE, SEVERE),
                bit(dataFieldId, 49, "TEMP_ST", "Status Static Air Temperature", INVALID, VALID),
                smeasure(dataFieldId, 48, 39, "TEMP", 0.25, UnitOfMeasure.TEMPERATURE_CELSIUS, "Static Air Temperature"),
                bit(dataFieldId, 38, "PRES_ST", "Status Average Static Pressure", INVALID, VALID),
                umeasure(dataFieldId, 37, 27, "PRES", 1.0, UnitOfMeasure.PRESSURE_HPA, "Average Static Pressure"),
                bit(dataFieldId, 26, "RHGT_ST", "Status Radio Height", INVALID, VALID),
                umeasure(dataFieldId, 25, 14, "RHGT", 16, UnitOfMeasure.ALTITUDE_FT, "Radio Height"),
                spare(dataFieldId, 13),
                spare(dataFieldId, 12),
                spare(dataFieldId, 11),
                spare(dataFieldId, 10),
                spare(dataFieldId, 9),
                hex(dataFieldId, "REG_HAZARD", "BDS Register Meteorological Hazard Report"));

        bds50 = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                bit(dataFieldId, 64, "RAN_ST", "Roll Angle Status", ST0, ST1),
                smeasure(dataFieldId, 63, 54, "RAN", ANGLE_LSB, UnitOfMeasure.ANGLE_DEGREE, "Roll Angle"),
                bit(dataFieldId, 53, "TTA_ST", "True Track Angle Status", ST0, ST1),
                smeasure(dataFieldId, 52, 42, "TTA", ANGLE_LSB, UnitOfMeasure.ANGLE_DEGREE, "True Track Angle"),
                bit(dataFieldId, 41, "GSPD_ST", "Ground Speed Status", ST0, ST1),
                umeasure(dataFieldId, 40, 31, "GSPD", SPEED_LSB, UnitOfMeasure.SPEED_KNOT, "Ground Speed"),
                bit(dataFieldId, 30, "TAR_ST", "Track Angle Rate Status", ST0, ST1),
                smeasure(dataFieldId, 29, 20, "TAR", 0.03125, UnitOfMeasure.ANGULAR_VELOCITY_DEGREES_S, "Track Angle Rate"),
                bit(dataFieldId, 19, "TAS_ST", "True Airspeed Status", ST0, ST1),
                smeasure(dataFieldId, 18, 9, "TAS", SPEED_LSB, UnitOfMeasure.SPEED_KNOT, "True Airspeed"),
                hex(dataFieldId, "REG_TTR", "BDS Register Track and Turn Report"));

        bds60 = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                bit(dataFieldId, 64, "HDG_ST", "Magnetic Heading Status", ST0, ST1),
                smeasure(dataFieldId, 63, 53, "HDG", ANGLE_LSB, UnitOfMeasure.ANGLE_DEGREE, "Magnetic Heading"),
                bit(dataFieldId, 52, "IAS_ST", "Indicated Airspeed Status", ST0, ST1),
                umeasure(dataFieldId, 51, 42, "IAS", 1.0, UnitOfMeasure.SPEED_KNOT, "Indicated Airspeed"),
                bit(dataFieldId, 41, "MACH_ST", "Mach Speed Status", ST0, ST1),
                umeasure(dataFieldId, 40, 31, "MACH", 0.004, UnitOfMeasure.SPEED_MACH, "Mach Speed"),
                bit(dataFieldId, 30, "BAR_ST", "Barometric Altitude Rate Status", ST0, ST1),
                smeasure(dataFieldId, 29, 20, "BAR", VERTICAL_RATE_LSB, UnitOfMeasure.SPEED_FT_MIN, "Barometric Altitude Rate"),
                bit(dataFieldId, 19, "IVVS", "Inertial Vertical Velocity Status", ST0, ST1),
                smeasure(dataFieldId, 18, 9, "IVV",  VERTICAL_RATE_LSB, UnitOfMeasure.SPEED_FT_MIN, "Inertial Vertical Velocity"),
                hex(dataFieldId, "REG_HSR", "BDS Register Heading and Speed Report"));

        deflt = BitsFieldList.create(dataFieldId, NUM_OCTETS,
                BitsField.hex(bitsFieldId(dataFieldId, 64, BITS_FIELD_ENCODING_HEX), position(NUM_OCTETS, 64, 9), "VAL", "BDS Value"),
                hex(dataFieldId, "REG", "BDS Register"));
    }

    int getAllDataFields(
            final Buffer buf,
            final int offset,
            final DataItem item,
            final Subitem subitem,
            final Subfield subfield,
            final int repIndex,
            final DataFieldValueConsumer consumer) {

        if (repIndex < 0) {
            return 0;
        }

        final int off = offset + repIndex * NUM_OCTETS;
        final byte reg = (byte) buf.getLong(off);
        final BitsFieldList bfs;
        switch (reg) {
            case BDS_40:
                bfs = bds40;
                break;
            case BDS_44:
                bfs = bds44;
                break;
            case BDS_45:
                bfs = bds45;
                break;
            case BDS_50:
                bfs = bds50;
                break;
            case BDS_60:
                bfs = bds60;
                break;
            default:
                bfs = deflt;
                break;
        }

        bfs.getAllDataFields(buf, off, item, subitem, subfield, null, repIndex, consumer);
        return NUM_OCTETS;
    }
}
