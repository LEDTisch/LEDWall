package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.SystemInterface;

import java.awt.*;

public class Ghost {
    Level myLevel;
    int x=0;
    int y=0;
    Color color;
    Direction nextDirection=Direction.NONE;
    private Direction acDirection=Direction.RIGHT;

    public Ghost(int x, int y, Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    public void move(){

        switch(this.nextDirection){
            case UP:{
                if(y<14)if((this.myLevel.level[this.y+1][this.x]&0x01)==0)this.acDirection=nextDirection;
                break;
            }
            case DOWN:{
                if(y>0)if((this.myLevel.level[this.y-1][this.x]&0x01)==0)this.acDirection=nextDirection;
                break;
            }
            case RIGHT:{
                if(x<9)if((this.myLevel.level[this.y][this.x+1]&0x01)==0)this.acDirection=nextDirection;
                break;
            }
            case LEFT:{
                if(x>0)if((this.myLevel.level[this.y][this.x-1]&0x01)==0)this.acDirection=nextDirection;
                break;
            }
        }
        boolean stuck=true;
        switch(this.acDirection){
            case UP:{
                if(y<14)if((myLevel.level[y+1][x]&0x01)==0)y++;stuck=false;
                break;
            }
            case DOWN:{
                if(y>0)if((myLevel.level[y-1][x]&0x01)==0)y--;stuck=false;
                break;
            }
            case RIGHT:{
                if(x<9)if((myLevel.level[y][x+1]&0x01)==0)x++;stuck=false;
                break;
            }
            case LEFT:{
                if(x>0)if((myLevel.level[y][x-1]&0x01)==0)x--;stuck=false;
                break;
            }
        }
        if(stuck){
            
        }
    }
    public void draw(){
        SystemInterface.table.setColor(this.color.getRed(),this.color.getGreen(),this.color.getBlue());
        SystemInterface.table.drawPixel(this.x,this.y);
    }

    public Ghost setMylevel(Level mylevel) {
        this.myLevel = mylevel;
        return this;
    }
}
