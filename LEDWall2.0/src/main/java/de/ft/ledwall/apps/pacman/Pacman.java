package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.Application;
import de.ft.ledwall.Sound;
import de.ft.ledwall.SystemInterface;
import org.jetbrains.annotations.NotNull;

public class Pacman implements Application {
    Level level=new Level();
    Player player=new Player();
    int drawcounter=0;

    /*
    1-wall
    2-futter

    87654321
    11111111
     */
    int level1[][]={
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00},
            {0x00,0x03,0x03,0x03,0x03,0x00,0x03,0x03,0x03,0x00},
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00},
            {0x03,0x03,0x00,0x03,0x03,0x03,0x03,0x00,0x03,0x03},
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00},
            {0x03,0x00,0x03,0x00,0x03,0x03,0x00,0x03,0x00,0x03},
            {0x00,0x00,0x03,0x00,0x03,0x00,0x00,0x03,0x00,0x00},
            {0x00,0x03,0x03,0x00,0x03,0x03,0x00,0x03,0x03,0x00},
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00},
            {0x03,0x03,0x00,0x03,0x03,0x00,0x03,0x03,0x03,0x03},
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00},
            {0x00,0x03,0x03,0x03,0x03,0x00,0x03,0x03,0x03,0x00},
            {0x00,0x00,0x00,0x00,0x03,0x00,0x03,0x00,0x00,0x00},
            {0x03,0x03,0x03,0x00,0x03,0x00,0x03,0x00,0x03,0x00},
            {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00}
    };
    @Override
    public void onCreate() {
        level.setLevel(level1);
        this.player.setMyLevel(level);
        player.setNextDirection(Direction.RIGHT);

    }

    @Override
    public void onDraw() {
        SystemInterface.table.clear();
        level.draw();
        player.draw();
        if(drawcounter>10){
            player.move();
            drawcounter=0;
        }
        drawcounter++;
    }

    @Override
    public void onRun() {

    }

    @Override
    public void onDataReceive(@NotNull String data, int playerID) {

        if(data.contentEquals("d")){
            this.player.setNextDirection(Direction.DOWN);
        }
        if(data.contentEquals("u")){
            this.player.setNextDirection(Direction.UP);
        }
        if(data.contentEquals("r")){
            this.player.setNextDirection(Direction.RIGHT);
        }
        if(data.contentEquals("l")){
            this.player.setNextDirection(Direction.LEFT);
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "PacMan";
    }

    @Override
    public void onStop() {

    }
}
