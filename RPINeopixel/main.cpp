#include <iostream>
#include "Neopixel.cpp"
#include <stdint.h>
#define PIN 24 // Hier wird angegeben, an welchem digitalen Pin die WS2812 LEDs bzw. NeoPixel angeschlossen sind
#define NUMPIXELS 10 // Hier wird die Anzahl der angeschlossenen WS2812 LEDs bzw. NeoPixel angegeben
Neopixel pixels =  Neopixel(PIN, NUMPIXELS);


int main() {
    std::cout<<"Register PIN";
    mapPin(PIN);
    pixels.begin();
    pixels.setPixelColor(1,150,10,20);
    pixels.show();
    pixels.end();
    unmapPin(PIN);
    return 0;
}
