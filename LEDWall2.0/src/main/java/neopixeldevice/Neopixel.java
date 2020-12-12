package neopixeldevice;

import java.io.*;

public class Neopixel {
    public int numpixels=0;
    private long buffer[];
    private OutputStream device;
    public Neopixel(int numpixels){
        this.numpixels=numpixels;
        buffer=new long[this.numpixels];
    }
    public void begin() throws FileNotFoundException {
        File device=new File("/dev/ttyACM0");
        if(device.exists()) {
            this.device = new FileOutputStream(device);
            System.out.println("Connected");
        }

    }
    public void show(){
        uint8_t *outputPtr = this->frame.dataPtr;
        for(int i=0;i<numpixels*3;i=i+3){
            outputPtr[i+0]=buffer[i/3]>>16;
            outputPtr[i+1]=(buffer[i/3] & 0xFF00)>>8;
            outputPtr[i+2]=(buffer[i/3] & 0xFFF0);

        }

        this.device.write();
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
