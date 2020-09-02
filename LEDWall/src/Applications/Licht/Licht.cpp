#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication1");
  ledtisch->setcolor(255,0,0);
  ledtisch->kreis(5,5,3,10);
  ledtisch->show();
}
void Licht::onRun(LEDTisch* ledtisch){

}
void Licht::onStop(){

}