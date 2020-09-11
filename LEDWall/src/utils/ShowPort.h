#include <Arduino.h>
#include "Device/LED-Tisch/LED-Tisch.h"
#include "Device/Matrix/Matrix.h"
#include "Device/LightRing/LightRing.h"
#ifndef ShowPort_H
#define ShowPort_H

class ShowPort{

public:
~ShowPort() {}


LEDTisch* ledtisch=new LEDTisch(10,15,1);
LEDTisch* ledFeld=new LEDTisch(10,10,0);
//Matrix* matrix=new Matrix(45,47,49);
LightRing* lightring=new LightRing(12,60);



void init();


private:


};
#endif