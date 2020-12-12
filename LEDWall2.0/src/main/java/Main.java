
import device.neopixeldevice.Neopixel;

import java.io.FileNotFoundException;

public class Main {
    public static Neopixel neo = new Neopixel(150);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        neo.begin();
        neo.clear();
        neo.setPixelColor(1,255,255,255);
        neo.show();
        sc.initSocket();
    }
}
