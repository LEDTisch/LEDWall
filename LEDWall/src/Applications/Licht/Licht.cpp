#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication1");
  ledtisch->setcolor(255,0,0);
  ledtisch->gerade(0,0,10,15,1);
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