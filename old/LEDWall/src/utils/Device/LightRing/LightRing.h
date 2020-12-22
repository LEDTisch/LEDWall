
#ifndef LightRing_H
#define LightRing_H

#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
#include <avr/power.h>
#endif

class LightRing{
    public:
        LightRing(int pin, int numpixels);
        void show();
        void clear();
        void light(int pixelnumper, int rot, int gruen, int blau);
    private:
            Adafruit_NeoPixel     ring = Adafruit_NeoPixel(60, 12, NEO_GRB + NEO_KHZ800);

};
#endif