/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.ledwall.plugins;


import de.ft.ledwall.Application;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
public class PluginLoader {

    public static boolean loadPlugin(File filetest) {

        //Erzeugen des JAR-Objekts

        JarFile file = null;

        try {
            file = new JarFile(filetest);
        } catch (IOException e) {
            e.printStackTrace();

        }


//Laden der MANIFEST.MF
        Manifest manifest = null;
        try {
            assert file != null;
            manifest = file.getManifest();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (NullPointerException ignored) {

        }


// auslesen der Attribute aus der Manifest

        assert manifest != null;
        Attributes attrib = manifest.getMainAttributes();

// holen der Mainclass aus den Attributen
        String main = attrib.getValue(Attributes.Name.MAIN_CLASS);
// laden der Klasse mit dem File als URL und der Mainclass
        Class<Application> cl = null;
        try {
            cl = (Class<Application>) new PluginClassLoader(new File(filetest.getAbsolutePath()).toURI().toURL()).loadClass(main);


        } catch (ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }


// holen der Interfaces die die Klasse impementiert
        assert cl != null;
        Class[] interfaces = cl.getInterfaces();
        for (int y = 0; y < interfaces.length; y++)
            if (interfaces[y].getName().equals("de.ft.ledwall.Application")) {
                break;
            }
        Application plugin;
        try {
            try {
                plugin = cl.getDeclaredConstructor().newInstance();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("Loaded " + filetest.getName());
            PluginManager.apps.put(plugin.getUUID(),plugin);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;


    }
}
