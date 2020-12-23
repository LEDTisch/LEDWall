package de.ft.ledwall.animation.dynamic;


import de.ft.ledwall.animation.Animation;

import java.awt.*;
import java.util.ArrayList;

public class PengAnimation {
    static ArrayList<int[]> frames = new ArrayList<>();
    static ArrayList<Integer> durations = new ArrayList<>();
    public static Animation getAnimation(int x, int y) {
        frames.clear();
        durations.clear();
        int i=0;
        while(i<10) {
            frames.add(new int[150]);

            durations.add(25);

            kreis(x,y,i,15,frames.get(frames.size()-1),new Color(255,255,255).getRGB());
            i++;
        }


        return new Animation("peng",frames,durations,true);
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
