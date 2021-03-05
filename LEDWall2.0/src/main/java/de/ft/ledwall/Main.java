package de.ft.ledwall;

import de.ft.ledwall.api.ServerConnection;
import de.ft.ledwall.data.DataManager;
import de.ft.ledwall.device.neopixeldevice.Neopixel;
import de.ft.ledwall.nativeapps.licht.Licht;
import de.ft.ledwall.plugins.PluginManager;

import java.awt.*;

public class Main {
    public static final String LedWalluuid = "3c3e587d-65e9-11eb-ade7-b827ebba2f99";
    public static String deviceuuid = "";
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager applicationManager = new ApplicationManager();

    public static ServerConnection serverConnection=new ServerConnection();


    public static void main(String[] args) throws Exception {
        DataManager.init();
        sc.initSocket();
        //SystemInterface.camera.start();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();


        applicationManager.init();


       /* if(!Main.serverConnection.checkAPIKey()) {
            Main.serverConnection.registrate(LedWalluuid);
        }*/
        Main.serverConnection.startpollconnection();

        //serverConnection.getWebSocketifconnected().send("Hallo Server");

      //  applicationManager.setApplication(new Licht());
        //SystemInterface.camera.stop();

    }
}
