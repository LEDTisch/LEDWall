#include <Arduino.h>
#include "Sokoban.h"



Sokoban::Sokoban(){

}

void Sokoban::onCreate(SystemInterface* systeminterface){
  systeminterface->lightring->light(5,100,100,100);
  systeminterface->lightring->show();
}
void Sokoban::onRun(SystemInterface* systeminterface){

}
void Sokoban::onDataReceive(String data,SystemInterface* systeminterface){
}
void Sokoban::onStop(SystemInterface* systeminterface){

}

 String Sokoban::getName(){
  return "Sokoban";
}