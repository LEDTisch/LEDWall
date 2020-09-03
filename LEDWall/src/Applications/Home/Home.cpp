#include <Arduino.h>
#include "Home.h"



Home::Home(){

}

void Home::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication2");
  ledtisch->clear();
  ledtisch->setcolor(255,255,255);
    ledtisch->drawImage(image,10,15);
  ledtisch->show();
}
void Home::onRun(LEDTisch* ledtisch){

}
void Home::onStop(LEDTisch* ledtisch){

}
void Home::onDataReceive(String data,LEDTisch* ledtisch){

}

String Home::getName(){
    return "Home";
}