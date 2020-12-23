package de.ft.ledwall.animation;

import java.awt.*;
import java.util.ArrayList;

public class RedGameOver {
    static ArrayList<int[]> frames = new ArrayList<>();
    static ArrayList<Integer> durations = new ArrayList<>();
    protected static Animation getAnimation() {

        frames.add(new int[150]);

        durations.add(500);

int red = new Color(255,0,0).getRGB();

        for(int i=0;i<150;i++) {
            frames.get(0)[i] = red;

        }


        return new Animation("gameoverRed",frames,durations);




    }

}
