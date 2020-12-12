package device.neopixeldevice;

import java.io.FileNotFoundException;

public class LEDTisch {
    private int width;
    private int height;
    private int rotation;
    private int red;
    private int green;
    private int blue;

    Neopixel strip;

    public LEDTisch(int width, int height, int rotation){
        this.width=width;
        this.height=height;
        this.rotation=rotation;

    }

    public void init(Neopixel strip) throws FileNotFoundException, InterruptedException {
        this.strip=strip;
        strip.begin();
    }
    public void setColor(int r,int g,int b){
        this.red=r;
        this.green=g;
        this.blue=b;
    }
    public void clear(){
        strip.clear();
    }
    public void show(){
        strip.show();
    }
    public void drawPixel(int x, int y){
        System.out.println(calculateStripPixel(x,y));
        this.strip.setPixelColor(calculateStripPixel(x, y), this.red, this.green, this.blue);
    }
    public int calculateStripPixel(int x,int y){
        int PO = -1;
        if (x >= 0 && x < width && y >= 0 && y < height) {

            int led = 0;

            //y=9-y+1;
            //x = 0;
            //y = 5;
            // PO = ((height-1)-y) + 15 * ((width-1)-x);
            if (rotation == 0) {
                PO = x + height * (width - y - 1);
            }
            if (rotation == 1) {
                PO = ((height - 1) - y) + height * ((width - 1) - x);
            }

            led = PO;


            if (led >= height && led < height * 2) {
                led = (height * 2 + 1) - (led - height);
                led = led - 2;
            }

            if (led >= height * 3 && led < height * 4) {
                led = (height * 6 + 1 - (led - height));
                led = led - 2;
            }

            if (led >= height * 5 && led < height * 6) {
                led = (height * 10 + 1 - (led - height));
                led = led - 2;
            }


            if (led >= height * 7 && led < height * 8) {
                led = (height * 14 + 1 - (led - height));
                led = led - 2;
            }

            if (led >= height * 9 && led < height * 10) {
                led = (height * 18 + 1 - (led - height));
                led = led - 2;
            }
            PO = led;


        }
        return PO;
    }
    public void drawdigit(int x, int y, int digit){

    }









    ////////////////////////////SETTER///GETTER//////////
    public void setRotation(byte rotation) {
        this.rotation = rotation;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getRed() {
        return red;
    }

    public int getRotation() {
        return rotation;
    }

    public Neopixel getStrip() {
        return strip;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBlue(byte blue) {
        this.blue = blue;
    }

    public void setGreen(byte green) {
        this.green = green;
    }

    public void setRed(byte red) {
        this.red = red;
    }

    public void setStrip(Neopixel strip) {
        this.strip = strip;
    }
}