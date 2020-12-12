import neopixeldevice.Neopixel;

import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;

public class Main {
    public static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();
    public static void main(String[] args) throws FileNotFoundException {
        neo.begin();
        sc.initSocket();
    }
}
