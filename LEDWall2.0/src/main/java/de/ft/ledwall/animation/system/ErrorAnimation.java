package de.ft.ledwall.animation.system;

import de.ft.ledwall.animation.Animation;

import java.awt.*;
import java.util.ArrayList;

public class ErrorAnimation {
    static ArrayList<int[]> frames = new ArrayList<>();
    static ArrayList<Integer> durations = new ArrayList<>();
    public static Animation getAnimation() {

        int red = new Color(255, 0, 0).getRGB();

        for(int z=0;z<3*2;z+=2) {

            durations.add(100);
            durations.add(100);


            frames.add(new int[150]);
            frames.add(new int[150]);



            for (int i = 0; i < 150; i++) {
                frames.get(z + 1)[i] = 0;

            }
            for (int i = 0; i < 150; i++) {
                frames.get(z)[i] = red;


            }

        }


        return new Animation("error",frames,durations);




    }

}
