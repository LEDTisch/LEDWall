package de.ft.ledwall.plugins;

import de.ft.ledwall.Application;
import de.ft.ledwall.ApplicationManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PluginManager {
    public static final HashMap<String, Application> apps = new HashMap<>();

    public static boolean loadplugins() {

        if (!new File(System.getProperty("user.home") + "\\" + ".LEDWall" + "\\apps").exists()) {
            new File(System.getProperty("user.home") + "\\" + ".LEDWall" + "\\apps").mkdir();
            if (!new File(System.getProperty("user.home") + "\\" + ".LEDWall" + "\\apps").exists()){
                System.out.println("Failed to create directory: "+ System.getProperty("user.home") + "\\" + ".LEDWall" + "\\apps");
                return false;
            }
        }

        File[] files = new File(System.getProperty("user.home") + "\\" + ".LEDWall" + "\\apps").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        assert files != null;
        System.out.println("Found " + files.length + " Plugins in Plugin Folder");
        for (File f : files) {
            if (f.getName().split("\\.")[1].contains("lwa") && f.getName().split("\\.")[1].endsWith("lwa")) {


                try {
                    PluginLoader.loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
                } catch (Exception e) {
                    System.out.println("Plugin lade Fehler");
                    e.printStackTrace();
                } catch (UnsupportedClassVersionError a) {
                    System.out.println("Das Plugin " + f.getName() + " wurde in der falschen Version geschrieben");
                    a.printStackTrace();

                }

            }
        }
        return true;
    }


}
