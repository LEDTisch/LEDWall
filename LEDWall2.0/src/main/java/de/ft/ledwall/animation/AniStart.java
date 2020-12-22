package de.ft.ledwall.animation;

import de.ft.ledwall.SystemInterface;

import java.awt.*;
import java.util.ArrayList;

public class AniStart {

   static ArrayList<int[]> frames = new ArrayList<>();
   static ArrayList<Integer> durations = new ArrayList<>();
    public static Animation aniStart () {


    int i=0;
    while(i<10) {

       frames.add(new int[150]);


       if(frames.size()>1)
       frames.set(frames.size()-1,frames.get(frames.size()-2).clone());

       durations.add(100);
        kreis(5,7,i,2,frames.get(frames.size()-1),new Color(i*25,255-i*25,0).getRGB());



        i++;
    }




        return new Animation("ani_start",frames,durations);

    }

   static void kreis(int x,int y,int r,int u,int[] frame,int rgb){
        for(int w=0;w<360;w=w+u){
            if(calculateStripPixel(x+Math.round(Math.cos(w*3.1415/180)*r),y+Math.round(Math.sin(w*3.1415/180)*r))>0)

            frame[calculateStripPixel(x+Math.round(Math.cos(w*3.1415/180)*r),y+Math.round(Math.sin(w*3.1415/180)*r))] = rgb;
        }
    }





    static  int height=15;
    static  int width=10;
    static int rotation=0;
    private static int calculateStripPixel(long x, long y){
        int PO = 0;
        if (x >= 0 && x < width && y >= 0 && y < height) {

            int led = 0;

            //y=9-y+1;
            //x = 0;
            //y = 5;
            // PO = ((height-1)-y) + 15 * ((width-1)-x);
            if (rotation == 0) {
                PO = (int) (x + height * (width - y - 1));
            }
            if (rotation == 1) {
                PO = (int) (((height - 1) - y) + height * ((width - 1) - x));
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


}
