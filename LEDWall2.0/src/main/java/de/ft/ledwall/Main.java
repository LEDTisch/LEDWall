package de.ft.ledwall;

import de.ft.ledwall.animation.Red.Red;
import de.ft.ledwall.apps.felixtetris.Tetris;
import de.ft.ledwall.apps.racingGame.RacingGame;
import de.ft.ledwall.device.neopixeldevice.Neopixel;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        sc.initSocket();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.setColor(255,255,255);
        SystemInterface.table.show();
        new Red();

        am.init();
        am.setApplication(new Tetris());

    }
}
