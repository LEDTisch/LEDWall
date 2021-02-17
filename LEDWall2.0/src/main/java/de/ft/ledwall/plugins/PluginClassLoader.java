/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.ledwall.plugins;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL fileURL) {
        super(new URL[]{fileURL});
    }
}
