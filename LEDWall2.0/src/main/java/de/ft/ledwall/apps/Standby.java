package de.ft.ledwall.apps;

import de.ft.ledwall.Application;
import de.ft.ledwall.Main;
import de.ft.ledwall.animation.AnimationManager;
import org.jetbrains.annotations.NotNull;

public class Standby implements Application {

AnimationManager ani_manager = new AnimationManager();

    @Override
    public void onCreate() {

    }
public Long lastdraw = 0L;

    @Override
    public void onDraw() {
        if(System.currentTimeMillis()-lastdraw>13500) { //TEMP
            ani_manager.addToQueue(AnimationManager.christmasCap);
            ani_manager.addToQueue(AnimationManager.christmasTree);
            ani_manager.addToQueue(AnimationManager.christmasTree);
            ani_manager.addToQueue(AnimationManager.christmasTree);
            ani_manager.addToQueue(AnimationManager.christmasTree);
            ani_manager.addToQueue(AnimationManager.christmasTree);
            lastdraw = System.currentTimeMillis();

        }

        ani_manager.update();

    }

    @Override
    public void onRun() {

    }

    @Override
    public void onDataReceive(@NotNull String data, int playerID) {

    }

    @NotNull
    @Override
    public String getName() {
        return "Standby";
    }

    @Override
    public void onStop() {

    }
}
