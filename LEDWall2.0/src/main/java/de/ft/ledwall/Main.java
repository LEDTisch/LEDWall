package de.ft.ledwall;

import de.ft.ledwall.animation.AnimationFileParser;
import de.ft.ledwall.animation.AnimationManager;
import de.ft.ledwall.apps.Standby;
import de.ft.ledwall.apps.felixtetris.Tetris;
import de.ft.ledwall.apps.racingGame.RacingGame;
import de.ft.ledwall.apps.snake.Snake;
import de.ft.ledwall.apps.snake.SnakeGame;
import de.ft.ledwall.device.neopixeldevice.Neopixel;

import javax.sound.sampled.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {



        sc.initSocket();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();
        System.out.println(new Color(255, 0, 0).getRGB());


        am.init();
        am.setApplication(new Standby());



    }
}
