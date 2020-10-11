#include <Arduino.h>
#include "Snake.h"



Snake::Snake(){

}

void Snake::GameOver(ShowPort* showport){
        snake.clearFood();
        showport->ledtisch->clear();
        snake.direction=0;
        snake.createRandomFood(1);
        snake.setLength(2);
snake.createSnake(5,7);

}




void Snake::onCreate(ShowPort* showport){
Serial.println("Snake");

        randomSeed(analogRead(0));
        snake.createSnake(5,7);
        snake.createRandomFood(1);
        showport->ledtisch->clear();
       showport->ledtisch->setcolor(0,255,0);
}
void Snake::onRun(ShowPort* showport){
if(millis()>takt){
        if(schritt>255/u){
    snake.move(showport->ledtisch);
    if(snake.WandKontrolle()!=-1 || snake.SnakeKontrolle()!=-1){
            GameOver(showport);
    }
    schritt=0;
}
            snake.draw(schritt,u,showport->ledtisch);
            snake.drawFood(showport->ledtisch);


        if(verlauf>=254){
                verlauf=0;
        }
        schritt++;
        verlauf++;
    takt=millis()+10;
}



if(snake.foodCheck()!=-1){
        //Serial.println("fooddelete: "+snake.foodCheck());
        Serial.println("fooddelete");
        snake.deleteFood(snake.foodCheck());
        snake.createRandomFood(1);
        snake.addPixel();
        
}
}
void Snake::onDataReceive(byte data[],ShowPort* showport){
  
if(data[0]==0x00 && snake.direction!=3){//drehen
snake.direction=2;
}

if(data[0]==0x01 && snake.direction!=1){//rechts
snake.direction=0;
}

if(data[0]==0x02 && snake.direction!=0){//links
 snake.direction=1;
}

if(data[0]==0x03 && snake.direction!=2){//runter
snake.direction=3;



}

   
}
void Snake::onStop(ShowPort* showport){

}

String Snake::getName(){
  return "Snake";
}