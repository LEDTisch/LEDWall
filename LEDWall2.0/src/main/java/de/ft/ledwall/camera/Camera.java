package de.ft.ledwall.camera;


import javax.swing.*;

public class Camera {
    private double merk=0;
    private int zulansamcounter=0;
    private int fps=20;
    int counter =0;
    int brighttestpointx =0;
    int brighttestpointy =0;
    int Xpositionfromzero=0;
    int Ypositionfromzero=0;
    int virtualLEDTischinPXwidth=150;
    int virtualLedTischinPXheight=(int)(virtualLEDTischinPXwidth*1.5f);
    int Xoffset=0;
    int Yoffset=-50;
    int xposition=0;
    int yposition=0;
    int z=0;
    private ImageProcess imageProcess=new ImageProcess();

    private Timer timer=new Timer(1000/fps, e -> process());
    public Camera(){
    }
    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    private void process(){
        merk=System.currentTimeMillis();
        imageProcess.readImagefromURL("http://192.168.137.175/stream/cam.jpg");
        counter =0;
        brighttestpointx =0;
        brighttestpointy =0;
        for(int x=0;x<imageProcess.input.getWidth();x=x+1){
            for(int y=0;y<imageProcess.input.getHeight();y=y+1){
                if(imageProcess.getBlue(x,y)>200) {
                    brighttestpointx+=x;
                    brighttestpointy+=y;
                    counter++;
                }
            }
        }
        if(counter >0) {
            brighttestpointx = brighttestpointx / counter;
            brighttestpointy = brighttestpointy / counter;
        }

        Xpositionfromzero=brighttestpointx-imageProcess.input.getWidth()/2+Xoffset;
        Ypositionfromzero=imageProcess.input.getHeight()/2-brighttestpointy+Yoffset;

        xposition=(Math.abs(-Math.round(Xpositionfromzero/(virtualLEDTischinPXwidth/10))-5));
        if(xposition<=0 || Xpositionfromzero<-virtualLEDTischinPXwidth/2){
            xposition=0;
        }
        if(xposition>=9 || Xpositionfromzero>virtualLEDTischinPXwidth/2){
            xposition=9;
        }
        yposition=(Math.abs(-Math.round(Ypositionfromzero/(virtualLedTischinPXheight/15))-7));
        if(yposition<=0 || Ypositionfromzero<-virtualLedTischinPXheight/2){
            yposition=0;
        }
        if(yposition>=14 || Ypositionfromzero>virtualLedTischinPXheight/2){
            yposition=14;
        }
      //  System.out.println("x: "+  xposition+" y: "+ yposition);


        if(Math.round(1000/(System.currentTimeMillis()-merk))<fps){
            System.out.println("zu langsam "+zulansamcounter++);
        }

    }

    public int getBrighttestpointx() {
        return brighttestpointx;
    }
    public int getBrighttestpointy() {
        return brighttestpointy;
    }

    public int getXposition() {
        return xposition;
    }

    public int getYposition() {
        return yposition;
    }

    public int getXpositionfromzero() {
        return Xpositionfromzero;
    }

    public int getYpositionfromzero() {
        return Ypositionfromzero;
    }
}
