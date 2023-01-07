/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.util.logging.Level;
import java.util.logging.Logger;

final class Fmt {
    private static final String[] NULL_ARGS = new String[]{"(Object[])null"};

    /**
     * Returns the given {@code template} {@code String} with each occurrence of {@code "%s"} replaced
     * with the corresponding argument value from {@code args}; or, if the placeholder and argument
     * counts do not match, returns a best-effort form of that {@code String}. Will not throw an
     * exception under normal conditions.
     *
     * <p><b>Note:</b> For most string-formatting needs, use {@link String#format String.format},
     * {@link java.io.PrintWriter#format PrintWriter.format}, and related methods. These support the
     * full range of <a
     * href="https://docs.oracle.com/javase/9/docs/api/java/util/Formatter.html#syntax">format
     * specifiers</a>, and alert you to usage errors by throwing {@link
     * java.util.IllegalFormatException}.
     *
     * <p>In certain cases, such as outputting debugging information or constructing a message to be
     * used for another unchecked exception, an exception during string formatting would serve little
     * purpose except to supplant the real information you were trying to provide. These are the cases
     * this method is made for; it instead generates a best-effort string with all supplied argument
     * values present.
     *
     * <p><b>Warning:</b> Only the exact two-character placeholder sequence {@code "%s"} is
     * recognized.
     *
     * @param template a @{code String} containing zero or more {@code "%s"} placeholder sequences.
     *                 {@code null} is treated as the four-character {@code String} {@code "null"}
     * @param args     the arguments to be substituted into the message template. The first argument
     *                 specified is substituted for the first occurrence of {@code "%s"} in the
     *                 template, and so forth. A {@code null} argument is converted to the
     *                 four-character {@code String} {@code "null"}; non-null values are converted to
     *                 {@code String}s using {@link Object#toString()}.
     */
    static String sprintf(final String template, final Object... args) {
        final String tmpl = String.valueOf(template); // null -> "null"
        final String[] sargs;
        if (null == args) {
            sargs = NULL_ARGS;
        } else {
            final int nargs = args.length;
            sargs = new String[nargs];
            for (int i = 0; i < nargs; i++) {
                sargs[i] = lenientToString(args[i]);
            }
        }

        // Start substituting the arguments into the '%s' placeholders.
        final int nargs = sargs.length;
        final int templateLength = template.length();
        final StringBuilder sb = new StringBuilder(templateLength + 16 * nargs);
        int templateStart = 0, placeHolderStart;
        int i = 0;
        while (i < nargs) {
            placeHolderStart = tmpl.indexOf("%s", templateStart);
            if (placeHolderStart == -1) {
                break;
            }

            sb.append(template, templateStart, placeHolderStart);
            sb.append(sargs[i++]);
            templateStart = placeHolderStart + 2;
        }

        sb.append(template, templateStart, templateLength);

        // If we run out of placeholders, append the extra args in square braces
        if (i < nargs) {
            sb.append(" [");
            sb.append(sargs[i++]);
            while (i < nargs) {
                sb.append(", ");
                sb.append(sargs[i++]);
            }

            sb.append(']');
        }

        return sb.toString();
    }

    private static String lenientToString(final Object o) {
        if (null == o) {
            return "null";
        }

        try {
            return o.toString();
        } catch (final Throwable e) {
            // Default toString() behavior - see Object.toString()
            final String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
            // Logger is created inline with fixed name to avoid forcing Proguard to create another class.
            Logger.getLogger("Strings").log(Level.WARNING, "Exception during lenientFormat for " + objectToString, e);
            return "<" + objectToString + " threw " + e.getClass().getName() + ">";
        }
    }

    private Fmt() {
    }
}
