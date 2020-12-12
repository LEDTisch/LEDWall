package neopixeldevice;

import java.io.*;

public class Neopixel {
    public int numpixels=0;
    private long buffer[];
    public Neopixel(int numpixels){
        this.numpixels=numpixels;
        buffer=new long[this.numpixels];
    }
    public void begin() throws FileNotFoundException {
        File device=new File("/dev/ttyACM0");
        OutputStream os=new FileOutputStream(device);


    }
    public void show(){

    }

    public void setPixelColor(int n, byte r, byte g, byte b){
        if (n > this.numpixels) return;
        if (r > 255 || r < 0) return;
        if (g > 255 || g < 0) return;
        if (b > 255 || b < 0) return;

        this.buffer[n] = (r << 16 | g << 8 | b);
    }
    public void setBrightness(float brightness){

    }
    public void clear(){
        for(int i=0;i<numpixels;i++){
            setPixelColor(i,(byte)0,(byte)0,(byte)0);
        }
    }
}
