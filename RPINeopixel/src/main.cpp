
#include <stdint.h>
#include "RPI.h"
#include "RPI.cpp"
#include "neopixelcontroll/Neopixel.cpp"

#include "Device/LED-Tisch/LEDTisch.cpp"

LEDTisch ledTisch=LEDTisch(10,15,1);


int main() {
    ledTisch.init();
    ledTisch.setcolor(255,255,0);
    ledTisch.kreis(5,5,4,5);
    ledTisch.show();
    //std::cout<<"Register PIN";

    return 0;

    return 0;
}

//    mapPin(PIN);
//    pixels.begin();
//    pixels.setPixelColor(1,150,10,20);
//    pixels.show();
//    pixels.end();
//    unmapPin(PIN);