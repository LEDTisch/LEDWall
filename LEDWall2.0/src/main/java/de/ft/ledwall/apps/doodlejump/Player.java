package de.ft.ledwall.apps.doodlejump;

class Player {
    int Xposition = 0;
    int Yposition = 14;
    float speed = 0f;
    int counter=0;

    //speed -100 - +100 stillstand 0

    void update(){
        counter++;

        if(counter>=2-Math.abs(speed) && speed != 0) {
            System.out.println(counter);

            boolean direction=false;
            if(speed < 0){
                direction=false;
            }
            if(speed > 0){
                direction=true;
            }
            if(speed != 0){
                if(direction){
                    Yposition+=1;
                }else{
                    Yposition-=1;
                }
            }
            counter=0;
        }
        speed-=0.18f;
        if(Yposition <=0)speed=2;
      //  if(Yposition >=14)speed=-10;

    }
}