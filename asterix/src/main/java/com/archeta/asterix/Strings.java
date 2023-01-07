/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

final class Strings {
    private static final String ABBREV_MARKER = "...";
    private static final int MIN_ABBREVIATION_WIDTH = 4;

    static boolean isBlank(final CharSequence cs) {
        int len;
        if (null == cs || (len = cs.length()) == 0) {
            return true;
        }

        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    static String abbreviate(final String s, final int maxWidth) {
        final int length = s.length();
        if (maxWidth < MIN_ABBREVIATION_WIDTH) {
            throw new IllegalArgumentException("minimum abbreviation width is " + MIN_ABBREVIATION_WIDTH);
        }

        if (length <= maxWidth) {
            return s;
        }

        return s.substring(0, maxWidth - 3) + ABBREV_MARKER;
    }

    private Strings() {
    }
}
