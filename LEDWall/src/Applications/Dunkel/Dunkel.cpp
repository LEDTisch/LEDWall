#include <Arduino.h>
#include "Dunkel.h"



Dunkel::Dunkel(){

}

void Dunkel::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication2");
  ledtisch->clear();
  ledtisch->show();
  ledtisch->setcolor(255,255,255);
    ledtisch->zahl(3,3,2);
  ledtisch->show();
}
void Dunkel::onRun(LEDTisch* ledtisch){

}
void Dunkel::onStop(){

}
void Dunkel::onDataReceive(String data){

}

String Dunkel::getName(){
    return "Home";
}