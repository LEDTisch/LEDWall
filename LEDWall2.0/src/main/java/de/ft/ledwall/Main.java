package de.ft.ledwall;

import de.ft.ledwall.apps.Standby;
import de.ft.ledwall.apps.doodlejump.DoodleJump;
import de.ft.ledwall.apps.licht.Licht;
import de.ft.ledwall.apps.pacman.Pacman;
import de.ft.ledwall.device.neopixeldevice.Neopixel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {


        sc.initSocket();
        SystemInterface.camera.start();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();
        System.out.println(new Color(255, 0, 0).getRGB());


        am.init();

        am.setApplication(new Pacman());
        SystemInterface.camera.stop();

    }
}
