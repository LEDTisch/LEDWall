//
// Created by Felix on 06.12.2020.
//

#ifndef RPINEOPIXEL_SYSTEMINTERFACE_H
#define RPINEOPIXEL_SYSTEMINTERFACE_H
#include "../Device/neopixeldevice/LEDTisch/LEDTisch.h"

class SystemInterface {
public:
    LEDTisch* ledTisch=new LEDTisch(10,15,1);

    void init();
};


#endif //RPINEOPIXEL_SYSTEMINTERFACE_H
