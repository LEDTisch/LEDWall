#include "FlappyBird.h"

const int holesize = 4;
const float gravity = 0.01f;
const int tickdelay = 30;
const int obstacleTickDelay = 350;
const int startX = 5;
const int startY = 7;



boolean obstacles[11][15];
boolean gameover = false;
unsigned long lastTick = 0;
unsigned long lastObstacleTick = 0;
float fallingspeed = 0;
int refpoint =15;

int x=5;
int y=7;
int obstancleSpaceCounter =0;


FlappyBird::FlappyBird() {


}

 void reset() {

     gameover = false;
     y = startY;
     x = startX;
    fallingspeed = 0;
    for(int x=0;x<11;x++) {
        for(int y=0;y<16;y++) {

          obstacles[x][y] = false;

        }
     }
     obstancleSpaceCounter =0;
}


void FlappyBird::onCreate(ShowPort* showport){
  showport->ledtisch->clear();
  showport->ledtisch->show();
  lastTick = millis();
  lastObstacleTick = millis();
  reset();

}
void FlappyBird::onRun(ShowPort* showport){


if(!gameover) {
    if(millis()>=lastTick) {

      Serial.println(fallingspeed);
        y-=fallingspeed;
        if(fallingspeed<2)
            fallingspeed+=gravity;

        lastTick = millis()+tickdelay;
    }


    if(millis()-lastObstacleTick>=obstacleTickDelay) {

        //Serial.println("Ticking");

        //Move all Obstancles to the left
        for(int x = 0; x<11; x++) {
            for(int y = 0; y < 15; y++) {
                if(x==0) { 
                    obstacles[x][y] = false;
                    continue;   
                }
                
            
                if(obstacles[x][y]) {
                    obstacles[x-1][y] = true;
                }else{
                    obstacles[x-1][y] = false;
                }
                
                
            }
        }
            

        if(obstancleSpaceCounter%6==0) {


            for(int y=0;y<15;y++) {

                if(refpoint>y&&refpoint-5<y) {
                 obstacles[10][y] = false;

                }else{
                obstacles[10][y] = true;
                }

            }

            obstancleSpaceCounter=0;

         }else{
            for(int y=0;y<15;y++) {

                obstacles[10][y] = false;

            }
             
         }
        obstancleSpaceCounter++;
        lastObstacleTick = millis();
        refpoint = random(4,15);
        }
        





showport->ledtisch->setcolor(0,150,0);    
    showport->ledtisch->clear();
    
  for(int x=0;x<10;x++) {
            for(int y=0;y<15;y++) {
               if( obstacles[x][y]) {
              showport->ledtisch->drawkoordinatensystem(x,y);

               }
            }
        }

    showport->ledtisch->setcolor(150,100,0);    
    showport->ledtisch->drawkoordinatensystem(x,y);


    if(obstacles[x][y]||y>14||y<0) {
        gameover =true;

    }

}else{

    showport->ledtisch->setcolor(200,0,0);
    showport->ledtisch->rect(0,0,10,15);

}
    showport->ledtisch->show();



}

void FlappyBird::onDataReceive(String data,ShowPort* showport){

  if(data=="j") {

    fallingspeed=-0.02f;
    y++;
   

  }

  if(data=="n") {

      reset();


  }


  
}
void FlappyBird::onStop(ShowPort* showport){

}

 String FlappyBird::getName(){
  return "FlappyBird";
}