package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.apps.doodlejump.DoodleJump;
import de.ft.ledwall.apps.licht.Licht;
import de.ft.ledwall.apps.pacman.Pacman;
import de.ft.ledwall.device.neopixeldevice.Neopixel;
import de.ft.ledwall.plugins.PluginManager;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String LedWalluuid = "3c3e587d-65e9-11eb-ade7-b827ebba2f99";
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();


    public static void main(String[] args) throws Exception {
        PluginManager.loadplugins();
        ServerConnection.registrate(LedWalluuid);
        sc.initSocket();
        //SystemInterface.camera.start();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();
        System.out.println(new Color(255, 0, 0).getRGB());


        am.init();

        am.setApplication(PluginManager.apps.get(1));
        //SystemInterface.camera.stop();

    }
}
