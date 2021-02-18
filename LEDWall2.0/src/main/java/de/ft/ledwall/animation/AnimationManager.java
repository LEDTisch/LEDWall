package de.ft.ledwall.animation;

import de.ft.ledwall.Main;
import de.ft.ledwall.SystemInterface;

import java.util.ArrayList;

public class AnimationManager {

    //Static Animations
    public static Animation christmasTree = AnimationFileParser.Companion.parseFile("ChrismasTree.txt");
    public static Animation christmasCap = AnimationFileParser.Companion.parseFile("ChristmasCap.txt");
    public static Animation rainbowOut = RainbowOut.getAnimation();
    public static Animation rainbowInAndOut = RainbowInAndOut.getAnimation();
    public static Animation redGameOver = RedGameOver.getAnimation();
    public static Animation tetrisgameover= TetrisGameOver.getAnimation();

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
                if(!animationQueue.get(0).isLoop()) {
                    if(this== Main.applicationManager.getSysAnimation()) {

                        SystemInterface.table.clear();
                    }
                    animationQueue.remove(0);
                }
            }

        }

        return true;
    }

    public boolean animationsAvailable() {
        return animationQueue.size()>=1;
    }

    public void addToQueue(Animation animation) {
        animationQueue.add(animation);
    }

    public ArrayList<Animation> getAnimationQueue() {
        return animationQueue;
    }
}
