#include "LightRing.h"

LightRing::LightRing(int pin,int numpixels){
    ring.setPin(pin);
    ring.begin();
}
void LightRing::show(){
    ring.show();
}
void LightRing::clear(){
    ring.clear();
}
void LightRing::light(int pixelnumber, int rot, int gruen, int blau){
    ring.setPixelColor(pixelnumber, ring.Color(rot,gruen,blau)); 
}