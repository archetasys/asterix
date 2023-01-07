/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.io.IOException;

import static com.archeta.asterix.ASTERIXIds.getDataItemIdString;

public final class DefaultDataFieldValueConsumer implements DataFieldValueConsumer {
    private static final String LF = System.lineSeparator();
    private static final String FX0 = "0 (End of Data Item)";
    private static final String FX1 = "1 (Extension into next extent)";

    private final boolean line;
    private final boolean valueOnly;
    private final Appendable out;

    public DefaultDataFieldValueConsumer(final Appendable output, final boolean line, final boolean valueOnly) {
        if (null == output) {
            throw new NullPointerException("`output` must not null");
        }

        this.line = line;
        this.valueOnly = valueOnly;
        this.out = output;
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final boolean value) {
        final BitsFieldEncoding encoding = field.encoding;
        if (encoding == BitsFieldEncoding.FX) {
            if (!valueOnly) {
                append(item, subitem, subfield, field, value ? FX1 : FX0);
            }
        } else if (encoding == BitsFieldEncoding.SPARE) {
            if (!valueOnly) {
                append(item, subitem, subfield, field, value ? "1" : "0");
            }
        }
    }

    public void accept(final DataItem item,final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final byte value) {
        append(item, subitem, subfield, field, Byte.toString(value));
    }

    public void accept(final DataItem item,final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final long value) {
        final BitsFieldEncoding encoding = field.encoding;
        if (encoding == BitsFieldEncoding.OCTAL) {
            append(item, subitem, subfield, field, BitsField.getOctalString((int) value));
        } else if (encoding == BitsFieldEncoding.HEX) {
            append(item, subitem, subfield, field, "0x" + HexDump.from(value, field.width));
        } else {
            append(item, subitem, subfield, field, Long.toString(value));
        }
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final double value) {
        append(item, subitem, subfield, field, Double.toString(value) + ' ' + field.unit.symbol());
    }

    public void accept(final DataItem item,final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final String value) {
        append(item, subitem, subfield, field, value);
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final BitsValue value) {
        append(item, subitem, subfield, field, value.value() + " (" + value.description() + ')');
    }

    private void append(final DataItem item, final Subitem subitem, final Subfield subfield, final DataField field, final String value) {
        if (line) {
            try {
                out.append(getDataItemIdString(item.getId())).append('/');
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        String s = "";
        if (subitem != null) {
            s += subitem.name + '/';
        }

        if (subfield != null) {
            s += subfield.name + '/';
        }

        try {
            out.append(String.format(line ? "%-12s " : "%12s: ", s + field.name))
                    .append(value).append(' ').append('[').append(field.description).append(']')
                    .append(LF);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
