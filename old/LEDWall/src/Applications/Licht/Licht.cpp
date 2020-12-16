#include <Arduino.h>
#include "Licht.h"



Licht::Licht(){

}

void Licht::onCreate(SystemInterface* systeminterface){
  systeminterface->ledtisch->clear();
  systeminterface->ledtisch->setcolor(255,255,255);
  systeminterface->ledtisch->show();
}
void Licht::onRun(SystemInterface* systeminterface){

}
void Licht::onDataReceive(String data,SystemInterface* systeminterface){

  if(data == "LichtSchalter"){

    if(lightstatus==false){
    systeminterface->ledtisch->setcolor(255,0,0);
    systeminterface->ledtisch->rect(0,6,10,7);

    systeminterface->ledFeld->setcolor(0,255,0);
    systeminterface->ledFeld->rect(1,1,3,3);

    systeminterface->lightring->light(6,255,255,0);
    
    lightstatus=true;
    }else{
      lightstatus=false;
          systeminterface->ledtisch->setcolor(0,0,0);
    systeminterface->ledtisch->rect(0,6,10,7);

        systeminterface->ledFeld->setcolor(0,0,0);
    systeminterface->ledFeld->rect(1,1,3,3);

        systeminterface->lightring->light(6,0,0,0);

    }
    systeminterface->ledtisch->show();
    
    systeminterface->ledFeld->show();

    systeminterface->lightring->show();
  }
}
void Licht::onStop(SystemInterface* systeminterface){

}

 String Licht::getName(){
  return "Licht";
}