
#include <stdint.h>
#include "RPI.h"
#include "RPI.cpp"
#include "Neopixel.cpp"

#define PIN 24 // Hier wird angegeben, an welchem digitalen Pin die WS2812 LEDs bzw. NeoPixel angeschlossen sind
#define NUMPIXELS 20 // Hier wird die Anzahl der angeschlossenen WS2812 LEDs bzw. NeoPixel angegeben

Neopixel pixels =  Neopixel(NUMPIXELS);




int main() {
    //std::cout<<"Register PIN";
    pixels.begin();
    pixels.setPixelColor(1,30,255,255);
    pixels.show();
    pixels.end();
    return 0;

    return 0;
}

//    mapPin(PIN);
//    pixels.begin();
//    pixels.setPixelColor(1,150,10,20);
//    pixels.show();
//    pixels.end();
//    unmapPin(PIN);