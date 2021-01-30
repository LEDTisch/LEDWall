package de.ft.ledwall.apps.pacman;

import de.ft.ledwall.SystemInterface;

public class Player {
    private int x=5;
    private int y=6;
    private Direction nextDirection=Direction.NONE;
    private Direction acDirection=Direction.NONE;
    private Level myLevel;
    public Player(){

    }
    public void setMyLevel(Level level){
        this.myLevel=level;
    }
    public void move(){
        switch(this.nextDirection){
            case UP:{
                if((this.myLevel.level[this.y+1][this.x] & 0x01)==1)this.acDirection=nextDirection;
                break;
            }
            case DOWN:{
                if((this.myLevel.level[this.y-1][this.x] & 0x01)==1)this.acDirection=nextDirection;
                break;
            }
            case RIGHT:{
                if((this.myLevel.level[this.y][this.x+1] & 0x01)==1)this.acDirection=nextDirection;
                break;
            }
            case LEFT:{
                if((this.myLevel.level[this.y][this.x-1] & 0x01)==1)this.acDirection=nextDirection;
                break;
            }
        }
        switch(this.acDirection){
            case UP:{
                if(y<14)if((myLevel.level[y+1][x] & 0x01)==1)y++;
                break;
            }
            case DOWN:{
                if(y>0)if((myLevel.level[y-1][x] & 0x01)==1)y--;
                break;
            }
            case RIGHT:{
                if(x<9)if((myLevel.level[y][x+1] & 0x01)==1)x++;
                break;
            }
            case LEFT:{
                if(x>0)if((myLevel.level[y][x-1] & 0x01)==1)x--;
                break;
            }
        }
    }
    public void draw(){
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
