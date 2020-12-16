
import device.neopixeldevice.LEDTisch;
import device.neopixeldevice.Neopixel;

import java.io.FileNotFoundException;

public class Main {
    public static Neopixel neo = new Neopixel(150);
    public static LEDTisch table=new LEDTisch(10, 15,1);
    public static SocketController sc = new SocketController();
    public static ApplicationManager am = new ApplicationManager();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        table.init(neo);
        table.clear();
        table.setColor(255,0,0);
        table.drawPixel(0,14);
        table.show();
    }
}
