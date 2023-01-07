/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * Represent an unsigned bits value with description.
 */
public final class BitsValue {
    public static final BitsValue ZERO = new BitsValue(0, "0");

    /**
     * Returns a new bits value
     *
     * @param value       the value, must not negative
     * @param description the description of the value
     * @return the newly created bits value
     */
    public static BitsValue of(final int value, final String description) {
        if (value < 0) {
            throw new IllegalArgumentException("`value` must be nonnegative, found: " + value);
        }

        if (Strings.isBlank(description)) {
            throw new IllegalArgumentException("`description` must not null nor blank");
        }

        return new BitsValue(value, description);
    }

    private final int value;
    private final String description;

    /**
     * Private constructor.
     */
    private BitsValue(final int value, final String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Returns the value.
     *
     * @return the value, never negative
     */
    public int value() {
        return value;
    }

    /**
     * Returns the description of the value.
     *
     * @return the description of the value
     */
    public String description() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BitsValue)) return false;
        final BitsValue o = (BitsValue) obj;
        return value == o.value && description.equals(o.description);
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        int result = 1;
        result = 31 * result + value;
        result = 31 * result + description.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return description + " (" + value + ')';
    }
}
