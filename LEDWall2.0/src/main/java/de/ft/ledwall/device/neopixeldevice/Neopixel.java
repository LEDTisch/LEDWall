package de.ft.ledwall.device.neopixeldevice;

import com.fazecast.jSerialComm.SerialPort;

import java.util.concurrent.TimeUnit;

public class Neopixel {
    public SerialPort device;
    public int numpixels=0;
    private byte buffer[];
    private byte sendbuffer[];
    public Neopixel(int numpixels){
        this.numpixels=numpixels;
        buffer=new byte[this.numpixels*3];
        sendbuffer=new byte[4 + this.numpixels*3 + 1];
        sendbuffer[0]=(byte)0xC9;
        sendbuffer[1]=(byte)0xDA;
        byte high = (byte)(this.numpixels*3 >> 8);
        byte low = (byte)(this.numpixels*3);

        sendbuffer[2] = high;
        sendbuffer[3] = low;
        sendbuffer[sendbuffer.length-1]=(byte)0x36;
    }
    public void begin() throws InterruptedException {
        device = SerialPort.getCommPort("COM3");
        device.setBaudRate(112500);
        device.openPort();
        TimeUnit.MILLISECONDS.sleep(2000);

        System.out.println("Connected");

    }
    public void show(){
        System.arraycopy(this.buffer,0,sendbuffer,4,this.numpixels*3);
        device.writeBytes(sendbuffer,sendbuffer.length);

    }

    public void setPixelColor(int n, int r, int g, int b){
        if (n > this.numpixels) return;
        if (r > 255 || r < 0) return;
        if (g > 255 || g < 0) return;
        if (b > 255 || b < 0) return;

        this.buffer[n*3] = (byte)r;
        this.buffer[n*3+1] = (byte)g;
        this.buffer[n*3+2] = (byte)b;

    }
    public void setBrightness(float brightness){

    }
    public void clear(){
        for(int i=0;i<numpixels;i++){
            setPixelColor(i,0,0,0);
        }
    }
}
