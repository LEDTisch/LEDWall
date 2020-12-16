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

}
void Home::onRun(SystemInterface* systeminterface){
 // clocktime.setTime(9,10,11);
      if(systeminterface->clocktime->getHour()<13){
        fibonacci.drawZahl(fibonacci.zahlasbyte(systeminterface->clocktime->getHour()),fibonacci.zahlasbyte(systeminterface->clocktime->getMinute()/5));
      }else{
        fibonacci.drawZahl(fibonacci.zahlasbyte(systeminterface->clocktime->getHour()-12),fibonacci.zahlasbyte(systeminterface->clocktime->getMinute()/5));
      }
  fibonacci.draw();


}
void Home::onStop(SystemInterface* systeminterface){

}
void Home::onDataReceive(String data,SystemInterface* systeminterface){

}

String Home::getName(){
    return "Home";
}