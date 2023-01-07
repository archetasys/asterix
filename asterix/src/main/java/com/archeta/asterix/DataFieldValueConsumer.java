/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

public interface DataFieldValueConsumer {
    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, boolean value);

    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, byte value);

    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, long value);

    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, double value);

    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, String value);

    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, int repIndex, DataField field, BitsValue value);
}
