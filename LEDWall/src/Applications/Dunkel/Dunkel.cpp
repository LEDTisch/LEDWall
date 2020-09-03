#include <Arduino.h>
#include "Dunkel.h"



Dunkel::Dunkel(){

}

void Dunkel::onCreate(LEDTisch* ledtisch){
Serial.println("TestApplication2");
}
void Dunkel::onRun(LEDTisch* ledtisch){

}
void Dunkel::onStop(){

}
void Dunkel::onDataReceive(String data){

}

String Dunkel::getName(){
    return "Home";
}