#include <iostream>
#include "Neopixel.cpp"
#include <stdint.h>
#define PIN 18 // Hier wird angegeben, an welchem digitalen Pin die WS2812 LEDs bzw. NeoPixel angeschlossen sind
#define NUMPIXELS 10 // Hier wird die Anzahl der angeschlossenen WS2812 LEDs bzw. NeoPixel angegeben
Neopixel pixels =  Neopixel(PIN, NUMPIXELS);


int main() {

    pixels.begin();

    return 0;
}
