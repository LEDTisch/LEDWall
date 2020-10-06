#include <Arduino.h>
#include "Home.h"



Home::Home(){

}

void Home::onCreate(ShowPort* showport){
  fibonacci.init(showport->ledtisch);

  showport->ledtisch->clear();
  showport->ledtisch->setcolor(255,255,255);
  showport->ledtisch->show();
    //showport->ledtisch->drawImage(image,10,15);
  //showport->ledtisch->show();
    fibonacci.setPosition(1,9);
  showport->ledtisch->setRotation(1);
    clocktime.setTime(10,35,0);

}
void Home::onRun(ShowPort* showport){
 // clocktime.setTime(9,10,11);
  if(millis()>time){
    clocktime.count();
      fibonacci.drawZahl(fibonacci.zahlasbyte(clocktime.getHour()),fibonacci.zahlasbyte(clocktime.getMinute()/5));
  fibonacci.draw();
    time=millis()+1000;
  }

}
void Home::onStop(ShowPort* showport){

}
void Home::onDataReceive(String data,ShowPort* showport){

}

String Home::getName(){
    return "Home";
}