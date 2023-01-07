/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * Data field.
 */
public final class DataField {
    /**
     * The bits field identifier.
     */
    public final long id;

    /**
     * The name of this bits field.
     */
    public final String name;

    /**
     * The description of this bits field.
     */
    public final String description;

    /**
     * The number of octets of which bits part of.
     */
    public final int numOctets;

    /**
     * The bits from position within the octets.
     */
    public final int fromBitPosition;

    /**
     * The bits to position within the octets.
     */
    public final int toBitPosition;

    /**
     * The number of bits: from - to + 1, pre-calculated for reuse.
     */
    public final int numBits;

    /**
     * The minimum value of the bits.
     */
    public final long minValue;

    /**
     * The maximum value of the bits.
     */
    public final long maxValue;

    /**
     * Indicates that the bits value is unsigned.
     */
    public final boolean unsigned;

    /**
     * Unit of Measure used by BitsFieldEncoding.MEASURE_SIGNED, BitsFieldEncoding.MEASURE_UNSIGNED,
     * or BitsFieldEncoding.GRAYCODE.
     */
    public final UnitOfMeasure unit;

    /**
     * LSB used by BitsFieldEncoding.MEASURE_SIGNED, BitsFieldEncoding.MEASURE_UNSIGNED,
     * or BitsFieldEncoding.GRAYCODE.
     */
    public final double lsb;

    /**
     * The minimum value used by BitsFieldEncoding.MEASURE_SIGNED, BitsFieldEncoding.MEASURE_UNSIGNED,
     * or BitsFieldEncoding.GRAYCODE.
     */
    public final double min;

    /**
     * The maximum value used by BitsFieldEncoding.MEASURE_SIGNED, BitsFieldEncoding.MEASURE_UNSIGNED,
     * or BitsFieldEncoding.GRAYCODE.
     */
    public final double max;

    public final int width;
    public final int group;
    public final BitsFieldEncoding encoding;

    private final int numValues;
    private final BitsValue[] values;
    private final int valuesIndexMask;

    DataField(
            final long id,
            final String name,
            final String description,
            final int numOctets,
            final int fromBitPosition,
            final int toBitPosition,
            final int numBits,
            final long minValue,
            final long maxValue,
            final boolean unsigned,
            final int width,
            final int group,
            final BitsFieldEncoding encoding,
            final UnitOfMeasure unit,
            final double lsb,
            final double min,
            final double max,
            final int numValues,
            final BitsValue[] values) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.numOctets = numOctets;
        this.fromBitPosition = fromBitPosition;
        this.toBitPosition = toBitPosition;
        this.numBits = numBits;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unsigned = unsigned;
        this.width = width;
        this.group = group;
        this.encoding = encoding;
        this.unit = unit;
        this.lsb = lsb;
        this.min = min;
        this.max = max;
        this.numValues = numValues;
        this.values = values;
        this.valuesIndexMask = ~(~0 << numBits);
    }

    public int getNumBitsValue() {
        return encoding == BitsFieldEncoding.VALUE || encoding == BitsFieldEncoding.VALUES ? numValues : 0;
    }

    public BitsValue getBitsValue(final int index) {
        return encoding == BitsFieldEncoding.VALUE || encoding == BitsFieldEncoding.VALUES ?
                values[index & valuesIndexMask] : BitsValue.ZERO;
    }
}
