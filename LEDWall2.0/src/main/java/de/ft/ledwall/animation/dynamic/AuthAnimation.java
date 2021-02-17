package de.ft.ledwall.animation.dynamic;


import de.ft.ledwall.animation.Animation;

import java.awt.*;
import java.util.ArrayList;

public class AuthAnimation {
    static ArrayList<int[]> frames = new ArrayList<>();
    static ArrayList<Integer> durations = new ArrayList<>();
    public static Animation getAnimation(int x, int y, int code, int length) {
        frames.clear();
        durations.clear();

        frames.add(new int[150]);
        int i=0;

        rect(x,y,2,3,new Color(255,0,0).getRGB(),frames.get(i));
        durations.add(500);
        i++;
        while(i<length+1) {
            frames.add(new int[150]);
            rect(x,y,2,3,new Color(0,0,0).getRGB(),frames.get(i));
            if((code & (0x01<<i)) == (0x01<<i)){
                System.out.println("1");
                rect(x,y,2,3,new Color(255,255,255).getRGB(),frames.get(i));

            }else{
                System.out.println("0");
                rect(x,y,2,3,new Color(0,0,0).getRGB(),frames.get(i));

            }
            durations.add(500);



            i++;
        }


        return new Animation("auth",frames,durations,true);
    }

    static void rect(int x,int y,int w,int h,int rgb, int[] frame){
        for(int xx=x;xx<x+w;xx++){
            for(int yy=y;yy<y+h;yy++){
                frame[xx+yy*10]=rgb;
            }
        }
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
