
#include <stdint.h>
#include "RPI.h"
#include "RPI.cpp"

#define PIN 24 // Hier wird angegeben, an welchem digitalen Pin die WS2812 LEDs bzw. NeoPixel angeschlossen sind
#define NUMPIXELS 10 // Hier wird die Anzahl der angeschlossenen WS2812 LEDs bzw. NeoPixel angegeben
//Neopixel pixels =  Neopixel(PIN, NUMPIXELS);




int main() {
    //std::cout<<"Register PIN";

    if(map_peripheral(&gpio) == -1)
    {
        printf("Failed to map the physical GPIO registers into the virtual memory space.\n");
        return -1;
    }

    // Define pin PIN as output
    INP_GPIO(PIN);
    OUT_GPIO(PIN);

    while(1)
    {
        // Toggle pin 7 (blink a led!)
        GPIO_SET = 1 << PIN;
        sleep(1);

        GPIO_CLR = 1 << PIN;
        sleep(1);
    }

    return 0;

    return 0;
}

//    mapPin(PIN);
//    pixels.begin();
//    pixels.setPixelColor(1,150,10,20);
//    pixels.show();
//    pixels.end();
//    unmapPin(PIN);