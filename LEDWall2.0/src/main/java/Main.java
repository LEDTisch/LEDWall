
import apps.racingGame.RacingGame;
import device.neopixeldevice.Neopixel;

import java.io.FileNotFoundException;

public class Main {
    private static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        sc.initSocket();
        SystemInterface.table.init(neo);
        SystemInterface.table.clear();
        SystemInterface.table.show();

        am.init();
        am.setApplication(new RacingGame());

    }
}
