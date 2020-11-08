//
// Created by tim on 07.11.20.
//


/**
 * Default Neopixel String RGB order is GBR this Library only support them
 */

#ifndef RPINEOPIXEL_NEOPIXEL_H
#define RPINEOPIXEL_NEOPIXEL_H


#include <stdint.h>



class Neopixel {
public:

    void              begin(void);
    void              show(void);
    void              setPin(uint8_t p);
    void              setPixelColor(uint16_t n, uint8_t r, uint8_t g, uint8_t b);
    void              setPixelColor(uint16_t n, uint8_t r, uint8_t g, uint8_t b,
                                    uint8_t w);
    void              setPixelColor(uint16_t n, uint32_t c);
    void              fill(uint32_t c=0, uint16_t first=0, uint16_t count=0);
    void              setBrightness(uint8_t);
    void              clear(void);


// Constructor: number of LEDs, pin number, LED type
    Neopixel(int n, uint8_t pin);

private:
    uint8_t pin;
    int numpixels;
    uint32_t* buffer;
};


#endif //RPINEOPIXEL_NEOPIXEL_H
