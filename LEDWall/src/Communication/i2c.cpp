#include "i2c.h"
#include <Wire.h>
#include <Arduino.h>
 i2c::i2c() {

}

void i2c::init() {


Wire.begin();

}

void i2c::send(char s[]){
Wire.beginTransmission(8); /* begin with device address 8 */
 Wire.write(0);  /* sends hello string */
 Wire.endTransmission();    /* stop transmitting */
Wire.requestFrom(8, 13); /* request & read data of size 13 from slave */
 while(Wire.available()){
    char c = Wire.read();
  Serial.print(c);
 }
 Serial.println();}