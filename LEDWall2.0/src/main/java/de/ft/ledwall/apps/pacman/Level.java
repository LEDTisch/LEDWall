package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.SystemInterface;

public class Level {
    public int level[][];
    public Level(){

    }
    public void draw(){
        for(int x=0;x<10;x++){
            for(int y=0;y<15;y++){
                if((this.level[y][x] & 0x01)==0x01){
                    SystemInterface.table.setColor(255,0,0);
                }else if((this.level[y][x] & 0x02)==0x02){
                    SystemInterface.table.setColor(0,30,0);
                }else{
                    SystemInterface.table.setColor(10,10,4);
                }
                SystemInterface.table.drawPixel(x,y);
            }
        }
    }
    public void setLevel(int[][] level){
        this.level=level;
    }

    /**
     * soll irgendwann mal dazu da sein level aus strings zu laden
     */
    public void loadlevelfromString(String level){

    }
}
