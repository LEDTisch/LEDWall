#include <Arduino.h>
#include "Snake.h"



Snake::Snake(){

}

void Snake::onCreate(LEDTisch* ledtisch){
Serial.println("Snake");
  ledtisch->clear();
  ledtisch->setcolor(255,255,255);
    ledtisch->zahl(3,3,3);
  ledtisch->show();
}
void Snake::onRun(LEDTisch* ledtisch){

}
void Snake::onDataReceive(String data,LEDTisch* ledtisch){

}
void Snake::onStop(LEDTisch* ledtisch){

}

String Snake::getName(){
  return "snake";
}