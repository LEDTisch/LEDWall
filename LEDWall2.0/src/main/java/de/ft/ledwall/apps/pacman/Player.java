package de.ft.ledwall.apps.pacman;

public class Player {
    private int x=0;
    private int y=0;
    private Direction nextDirection=Direction.NONE;
    public Player(){

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
