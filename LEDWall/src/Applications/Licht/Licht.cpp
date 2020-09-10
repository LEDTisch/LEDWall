#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(ShowPort* showport){
Serial.println("TestApplication1");
  showport->ledtisch->clear();
  showport->ledtisch->setcolor(255,255,255);
    showport->ledtisch->zahl(3,3,1);
  showport->ledtisch->show();
}
void Licht::onRun(ShowPort* showport){

}
void Licht::onDataReceive(String data,ShowPort* showport){
        Serial.println(data);

  if(data == "LichtSchalter"){

    if(lightstatus==false){
    showport->ledtisch->setcolor(255,0,0);
    showport->ledtisch->rect(0,6,10,7);

    showport->ledFeld->setcolor(0,255,0);
    showport->ledFeld->rect(1,1,3,3);

    showport->lightring->light(6,255,255,0);
    
    lightstatus=true;
    }else{
      lightstatus=false;
          showport->ledtisch->setcolor(0,0,0);
    showport->ledtisch->rect(0,6,10,7);

        showport->ledFeld->setcolor(0,0,0);
    showport->ledFeld->rect(1,1,3,3);

        showport->lightring->light(6,0,0,0);

    }
    showport->ledtisch->show();
    
    showport->ledFeld->show();

    showport->lightring->show();
  }
}
void Licht::onStop(ShowPort* showport){

}

String Licht::getName(){
  return "Licht";
}