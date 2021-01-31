package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.SystemInterface;

import java.awt.*;
import java.util.ArrayList;

public class Level {
    ArrayList<Ghost> ghosts=new ArrayList<>();
    public int level[][];
    public Level(){

    }
    public void draw(){
        for(int x=0;x<10;x++){
            for(int y=0;y<15;y++){

                SystemInterface.table.setColor(10,10,4);
                if((this.level[y][x] & 0x01)==0x01){
                    SystemInterface.table.setColor(255,0,0);
                }
                if((this.level[y][x] & 0x02)==0x02){
                    SystemInterface.table.setColor(0,100,0);
                }
                SystemInterface.table.drawPixel(x,y);
            }
        }
    }
    public void setLevel(int[][] level){
        this.ghosts.clear();
        this.level=level;
        for(int x=0;x<10;x++){
            for(int y=0;y<15;y++) {
                if((this.level[y][x] & 0x04)==0x04){
                    double r=Math.random();
                    this.ghosts.add(new Ghost(x,y,new Color(255,255,255)).setMylevel(this));
                }
            }
       }
    }

    /**
     * soll irgendwann mal dazu da sein level aus strings zu laden
     */
    public void loadlevelfromString(String level){

    }
}
