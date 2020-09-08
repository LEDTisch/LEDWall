#include <Arduino.h>
#include "Home.h"



Home::Home(){

}

void Home::onCreate(ShowPort* showport){
Serial.println("TestApplication2");
  showport->ledtisch->clear();
  showport->ledtisch->setcolor(255,255,255);
    showport->ledtisch->drawImage(image,10,15);
  showport->ledtisch->show();
}
void Home::onRun(ShowPort* showport){

}
void Home::onStop(ShowPort* showport){

}
void Home::onDataReceive(String data,ShowPort* showport){

}

String Home::getName(){
    return "Home";
}