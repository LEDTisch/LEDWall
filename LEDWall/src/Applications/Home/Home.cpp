#include <Arduino.h>
#include "Home.h"



Home::Home(){

}

void Home::onCreate(SystemInterface* systeminterface){
  fibonacci.init(systeminterface->ledtisch);

  systeminterface->ledtisch->clear();
  systeminterface->ledtisch->setcolor(255,255,255);
  systeminterface->ledtisch->show();
    //systeminterface->ledtisch->drawImage(image,10,15);
  //systeminterface->ledtisch->show();
    fibonacci.setPosition(1,9);
  systeminterface->ledtisch->setRotation(1);
    clocktime.setTime(10,35,0);

}
void Home::onRun(SystemInterface* systeminterface){
 // clocktime.setTime(9,10,11);
  if(millis()>time){
    clocktime.count();
      fibonacci.drawZahl(fibonacci.zahlasbyte(clocktime.getHour()),fibonacci.zahlasbyte(clocktime.getMinute()/5));
  fibonacci.draw();
    time=millis()+1000;
  }

}
void Home::onStop(SystemInterface* systeminterface){

}
void Home::onDataReceive(String data,SystemInterface* systeminterface){

}

String Home::getName(){
    return "Home";
}