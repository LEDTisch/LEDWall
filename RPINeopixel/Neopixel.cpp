//
// Created by tim on 07.11.20.
//

#include <iostream>
#include "Neopixel.h"
#define WS2811_TARGET_FREQ                       800000   // Can go as low as 400000
#define PWM_BYTE_COUNT(leds, freq)               (((((LED_BIT_COUNT(leds, freq) >> 3) & ~0x7) + 4) + 4) * \
                                                  RPI_PWM_CHANNELS)
#define red 0x00FF0000
#define green 0x0000FF00
#define blue 0x000000FF

Neopixel::Neopixel(int n, int pin) {
this->numpixels = n;
this->pin = pin;
this->buffer = (uint32_t *) malloc(sizeof(uint32_t)*this->numpixels);

std::cout<<this->buffer[this->numpixels-1];

}

void Neopixel::show() {




}

void Neopixel::begin(void) {
    printf("Begin!");

}
