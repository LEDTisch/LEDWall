package de.ft.ledwall.apps.doodlejump;

class Player {
    int Xposition = 0;
    int Yposition = 14;

    private long jumpcounter=0;
    private float jumpspeed=0;
    private long movecounter=0;
    private float movespeed=0;


    void Bounce(){
        jumpspeed=20;
    }
    void jumpUpdate(){
        if(jumpcounter<System.currentTimeMillis()-(int)(1000f/Math.abs(jumpspeed))){
            if(jumpspeed!=0) {
                if(jumpspeed<0) {
                    Yposition--;
                }
                if(jumpspeed>0){
                    Yposition++;
                }
            }
            jumpcounter=System.currentTimeMillis();
        }
        jumpspeed-=0.15f;
    }
    void moveUpdate(float moveForce){
        movespeed=moveForce;
        if(movecounter<System.currentTimeMillis()-(int)(1000f/Math.abs(movespeed))){
            if(movespeed!=0) {
                if(movespeed<0) {
                    Xposition--;
                }
                if(movespeed>0){
                    Xposition++;
                }
            }
            if(Xposition <0)Xposition=9;
            if(Xposition >9)Xposition=0;

            //  movecounter= System.currentTimeMillis()+(int)(1000f/Math.abs(blub));
            movecounter=System.currentTimeMillis();
        }
    }

    public float getJumpspeed() {
        return jumpspeed;
    }
}