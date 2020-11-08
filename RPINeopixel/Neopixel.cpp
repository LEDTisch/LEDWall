//
// Created by tim on 07.11.20.
//

#include <iostream>
#include "gpio.h"
#include "Neopixel.h"
#define WS2811_TARGET_FREQ                       800000   // Can go as low as 400000
#define PWM_BYTE_COUNT(leds, freq)               (((((LED_BIT_COUNT(leds, freq) >> 3) & ~0x7) + 4) + 4) * \
                                                  RPI_PWM_CHANNELS)
#define red 0x00FF0000
#define green 0x0000FF00
#define blue 0x000000FF

Neopixel::Neopixel(int n, uint8_t pin) {
this->numpixels = n;
this->pin = pin;
this->buffer = (uint32_t *) malloc(sizeof(uint32_t)*this->numpixels);



}

void Neopixel::show() {

    test(this->pin);

    std::cout<<"\n";

    for(int i=0;i<this->numpixels;i++) {

        std::cout<<"Pixel "<<std::to_string(i)<<": ";
        std::cout<<std::hex<<this->buffer[i];
        std::cout<<"\n";

    }

}


void Neopixel::setPixelColor(uint16_t n, uint8_t r, uint8_t g, uint8_t b) {
    if(n>this->numpixels) return;
    if(r>255||r<0) return;
    if(g>255||g<0) return;
    if(b>255||b<0) return;

    this->buffer[n] = (r << 16 | g << 8 | b );

    std::cout<<std::hex<<this->buffer[n];



}

void Neopixel::setPixelColor(uint16_t n, uint32_t c) {
    if(n>this->numpixels) return;
    this->buffer[n] = c;

    std::cout<<std::hex<<this->buffer[n];

}
void Neopixel::setBrightness(uint8_t) {

}

void Neopixel::setPin(uint8_t p) {
    this->pin=p;

}

void Neopixel::end() {


}

void Neopixel::begin(void) {
std::cout<<"Init Pixels: ";
std::cout<<this->numpixels;
std::cout<<"\n";
}
