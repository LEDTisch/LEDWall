import neopixeldevice.Neopixel;

import java.io.FileNotFoundException;



public class Main {
    public static Neopixel neo = new Neopixel(150);
    static SocketController sc = new SocketController();

    public static void main(String[] args) throws FileNotFoundException {
        neo.begin();

        sc.initSocket();

    }

}
