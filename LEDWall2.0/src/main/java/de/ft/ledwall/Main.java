package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.device.neopixeldevice.Neopixel;
import de.ft.ledwall.plugins.PluginManager;

import java.awt.*;

public class Main {
    public static final String LedWalluuid = "3c3e587d-65e9-11eb-ade7-b827ebba2f99";
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager applicationManager = new ApplicationManager();


    public static void main(String[] args) throws Exception {
        PluginManager.loadplugins();
        sc.initSocket();
        //SystemInterface.camera.start();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();

        System.out.println(new Color(255, 0, 0).getRGB());


        applicationManager.init();

        ServerConnection.registrate(LedWalluuid);

        applicationManager.setApplication(PluginManager.apps.get(0));
        //SystemInterface.camera.stop();

    }
}
