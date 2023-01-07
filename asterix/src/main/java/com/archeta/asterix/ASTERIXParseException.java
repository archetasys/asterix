/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

/**
 * ASTERIX parse exception.
 */
public final class ASTERIXParseException extends RuntimeException {
    private static final long serialVersionUID = 2833083857613459110L;

    private final int errorCode;

    public ASTERIXParseException(final int errorCode, final String message) {
        super(message, null, false, false);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
