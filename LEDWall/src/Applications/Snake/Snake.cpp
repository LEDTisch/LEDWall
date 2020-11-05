#include <Arduino.h>
#include "Snake.h"



Snake::Snake(){

}

void Snake::GameOver(SystemInterface* systeminterface){
        snake.clearFood();
        systeminterface->ledtisch->clear();
        snake.direction=0;
        snake.createRandomFood(1);
        snake.setLength(2);
snake.createSnake(5,7);

}




void Snake::onCreate(SystemInterface* systeminterface){
Serial.println("Snake");

        randomSeed(analogRead(0));
        snake.createSnake(5,7);
        snake.createRandomFood(1);
        systeminterface->ledtisch->clear();
       systeminterface->ledtisch->setcolor(0,255,0);
}
void Snake::onRun(SystemInterface* systeminterface){
if(millis()>takt){
        if(schritt>255/u){
    snake.move(systeminterface->ledtisch);
    if(snake.WandKontrolle()!=-1 || snake.SnakeKontrolle()!=-1){
            GameOver(systeminterface);
    }
    schritt=0;
}
            snake.draw(schritt,u,systeminterface->ledtisch);
            snake.drawFood(systeminterface->ledtisch);


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
void Snake::onDataReceive(String data,SystemInterface* systeminterface){
  
if(data=="h" && snake.direction!=3){//drehen
snake.direction=2;
}

if(data=="r" && snake.direction!=1){//rechts
snake.direction=0;
}

if(data=="l" && snake.direction!=0){//links
 snake.direction=1;
}

if(data=="d" && snake.direction!=2){//runter
snake.direction=3;



}

   
}
void Snake::onStop(SystemInterface* systeminterface){

}

String Snake::getName(){
  return "Snake";
}