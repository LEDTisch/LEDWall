package de.ft.ledwall;

import de.ft.ledwall.camera.Camera;
import de.ft.ledwall.device.neopixeldevice.LEDTisch;
import de.ft.ledwall.utils.Time;
import de.ft.ledwall.utils.Weather;

public class SystemInterface {
    public static LEDTisch table=new LEDTisch(10, 15,1);
    public static Camera camera=new Camera();
    public static Time systemTime=new Time();
    public static Weather weather=new Weather();
}
