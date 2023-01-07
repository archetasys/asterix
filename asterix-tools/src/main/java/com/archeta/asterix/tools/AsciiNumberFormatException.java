/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

/**
 * Thrown when parsing an ASCII string and finding characters that are not digits.
 */
public class AsciiNumberFormatException extends NumberFormatException {
    private static final long serialVersionUID = -6849486257505869666L;

    /**
     * Create exception with a given {@code message}.
     *
     * @param message the message to use as cause
     */
    public AsciiNumberFormatException(final String message) {
        super(message);
    }
}