import neopixeldevice.Neopixel;

import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;

public class Main {
    public static Neopixel neo = new Neopixel(150);
    static SocketController sc = new SocketController();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        neo.begin();
        //neo.clear();
        neo.setPixelColor(1,255,255,255);
        neo.show();
        sc.initSocket();
    }
}
