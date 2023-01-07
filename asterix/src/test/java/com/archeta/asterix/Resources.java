/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix;

import java.net.URL;

final class Resources {
    private Resources() {
    }

    static URL get(final String resourceName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (null == loader) {
            loader = Resources.class.getClassLoader();
        }

        URL url = loader.getResource(resourceName);
        if (null == url) {
            throw new RuntimeException("Resource " + resourceName + " not found");
        }

        return url;
    }
}
