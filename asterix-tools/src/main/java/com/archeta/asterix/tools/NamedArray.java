/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import us.hebi.matlab.mat.types.Array;

final class NamedArray {
    final int id;
    final String name;
    final Array array;

    NamedArray(final int id, final String name, final Array array) {
        this.id = id;
        this.name = name;
        this.array = array;
    }
}
