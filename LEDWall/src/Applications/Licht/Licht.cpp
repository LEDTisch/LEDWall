#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication1");
  ledtisch->clear();
  ledtisch->setcolor(255,255,255);
    ledtisch->zahl(3,3,1);
  ledtisch->show();
}
void Licht::onRun(LEDTisch* ledtisch){

}
void Licht::onDataReceive(String data,LEDTisch* ledtisch){
  if(data == "LichtSchalter"){
      Serial.println(data);

    if(lightstatus==false){
    ledtisch->setcolor(255,0,0);
    ledtisch->rect(0,6,10,7);
    lightstatus=true;
    }else{
      lightstatus=false;
          ledtisch->setcolor(0,0,0);
    ledtisch->rect(0,6,10,7);
    }
    ledtisch->show();
  }
}
void Licht::onStop(LEDTisch* ledtisch){

}

String Licht::getName(){
  return "Licht";
}