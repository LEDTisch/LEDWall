#include "i2c.h"
#include <Wire.h>
#include <Arduino.h>
 i2c::i2c() {

}

void i2c::init() {


Wire.begin();

}