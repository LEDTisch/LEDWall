package de.ft.ledwall;

import de.ft.ledwall.camera.Camera;
import de.ft.ledwall.device.neopixeldevice.LEDTisch;

public class SystemInterface {
    public static LEDTisch table=new LEDTisch(10, 15,1);
    public static Camera camera=new Camera();
}
