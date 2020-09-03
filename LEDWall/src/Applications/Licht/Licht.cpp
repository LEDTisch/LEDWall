#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication1");
  ledtisch->clear();
  ledtisch->show();
  ledtisch->setcolor(255,255,255);
    ledtisch->zahl(3,3,1);
  ledtisch->show();
}
void Licht::onRun(LEDTisch* ledtisch){

}
void Licht::onDataReceive(String data){
  Serial.println(data);
}
void Licht::onStop(){

}

String Licht::getName(){
  return "Licht";
}