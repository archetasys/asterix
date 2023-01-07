/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

@FunctionalInterface
public interface DataFieldConsumer {
    void accept(DataItem item, Subitem subitem, Subfield subfield, Part part, DataField field);
}
