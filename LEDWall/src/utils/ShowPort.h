#include <Arduino.h>
#include "Device/LED-Tisch/LED-Tisch.h"
#ifndef ShowPort_H
#define ShowPort_H
class ShowPort{

public:
~ShowPort() {}


LEDTisch* ledtisch=new LEDTisch(10,15,1);
LEDTisch* ledFeld=new LEDTisch(10,10,0);



void init();


private:


};
#endif