package de.ft.ledwall.animation;

import java.awt.*;
import java.util.ArrayList;

public class RainbowInAndOut {

   static ArrayList<int[]> frames = new ArrayList<>();
   static ArrayList<Integer> durations = new ArrayList<>();
    protected static Animation getAnimation() {


    int i=0;
    while(i<10) {

       frames.add(new int[150]);


        if(frames.size()>1)
            frames.set(frames.size()-1,frames.get(frames.size()-2).clone());

       durations.add(50);
        kreis(5,7,i,2,frames.get(frames.size()-1),new Color(i*25,255-i*25,0).getRGB());

        i++;
    }
    i=0;
    while(i<20) {

        frames.add(new int[150]);
        frames.set(frames.size()-1,frames.get(frames.size()-2).clone());
        durations.add(50);
        kreis(5,7,i,2,frames.get(frames.size()-1),new Color(0,0,0).getRGB());
        i++;
    }




        return new Animation("RainbowOut",frames,durations);

    }

   static void kreis(int x,int y,int r,int u,int[] frame,int rgb){
        for(int w=0;w<360;w=w+u){
            try {

                if((int)(x + Math.round(Math.cos(w * 3.1415 / 180) * r))>9||(int)(x + Math.round(Math.cos(w * 3.1415 / 180) * r))<0) continue;


                frame[(int)(x + Math.round(Math.cos(w * 3.1415 / 180) * r))+ (int)(y + Math.round(Math.sin(w * 3.1415 / 180) * r))*10] = rgb;
            }catch (Exception e) {

            }
        }
    }




}
