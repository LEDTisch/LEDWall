#include <Arduino.h>
#include "Home.h"



Home::Home(){

}

void Home::onCreate(ShowPort* showport){
  fibonacci.init(showport->ledtisch);

Serial.println("HOME-Application");
  showport->ledtisch->clear();
  showport->ledtisch->setcolor(255,255,255);
  showport->ledtisch->show();
    //showport->ledtisch->drawImage(image,10,15);
  //showport->ledtisch->show();
}
void Home::onRun(ShowPort* showport){
  fibonacci.setPosition(1,9);
  fibonacci.drawZahl(fibonacci.zahlasbyte(7),fibonacci.zahlasbyte(3));
  fibonacci.draw();
}
void Home::onStop(ShowPort* showport){

}
void Home::onDataReceive(String data,ShowPort* showport){

}

String Home::getName(){
    return "Home";
}