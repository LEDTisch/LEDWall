package de.ft.ledwall.animation;

import de.ft.ledwall.SystemInterface;

import java.util.ArrayList;

public class AnimationManager {

    //Static Animations
    public static Animation test = AnimationFileParser.Companion.parseFile("rgb.txt");
    public static Animation rainbowOut = RainbowOut.getAnimation();
    public static Animation rainbowInAndOut = RainbowInAndOut.getAnimation();
    public static Animation redGameOver = RedGameOver.getAnimation();

    int currentStep = 0;
    long frameStart =-1;

    ArrayList<Animation> animationQueue = new ArrayList<>();
    public boolean update() {
        if(animationQueue.size()==0) return false;
        SystemInterface.table.copyFrameToPixelBuffer(animationQueue.get(0).getFrame(currentStep),animationQueue.get(0).getSkipOff());
        if(frameStart==-1) frameStart=System.currentTimeMillis();
        if(System.currentTimeMillis()-frameStart>animationQueue.get(0).getDurations().get(currentStep))  {
            frameStart=-1;
            if(currentStep!=animationQueue.get(0).getFrameCount()-1) {
                currentStep++;
            }else{
                currentStep=0;
                animationQueue.remove(0);
            }

        }

        return true;
    }

    public boolean animationsAvailable() {
        return animationQueue.size()==1;
    }

    public void addToQueue(Animation animation) {
        animationQueue.add(animation);
    }

    public ArrayList<Animation> getAnimationQueue() {
        return animationQueue;
    }
}
