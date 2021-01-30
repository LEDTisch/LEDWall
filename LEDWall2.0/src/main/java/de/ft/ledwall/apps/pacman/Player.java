package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.Sound;
import de.ft.ledwall.SystemInterface;

public class Player {
    private String munch1="PacManSounds/munch_1.wav";
    private String munch2="PacManSounds/munch_2.wav";
    private int x=5;
    private int y=6;
    private Direction nextDirection=Direction.NONE;
    private Direction acDirection=Direction.NONE;
    private Level myLevel;
    boolean munch=false;
    public Player(){

    }
    public void setMyLevel(Level level){
        this.myLevel=level;
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
        switch(this.acDirection){
            case UP:{
                if(y<14)if((myLevel.level[y+1][x]&0x01)==0)y++;
                break;
            }
            case DOWN:{
                if(y>0)if((myLevel.level[y-1][x]&0x01)==0)y--;
                break;
            }
            case RIGHT:{
                if(x<9)if((myLevel.level[y][x+1]&0x01)==0)x++;
                break;
            }
            case LEFT:{
                if(x>0)if((myLevel.level[y][x-1]&0x01)==0)x--;
                break;
            }
        }
    }
    public void draw(){
        if((this.myLevel.level[y][x]&0x02)==0x02){
            this.myLevel.level[y][x]&=0xFD;
            if(munch){
                Sound.play(munch2);
                munch=false;
            }else{
                Sound.play(munch1);
                munch=true;
            }
        }
        SystemInterface.table.setColor(255,255,0);
        SystemInterface.table.drawPixel(this.x,this.y);
    }
    public void setNextDirection(Direction nextDirection){
        this.nextDirection=nextDirection;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
