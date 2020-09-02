#include <Arduino.h>
#include <Wire.h>


 void receiveEvent(int howMany) {
 while (0 <Wire.available()) {
    char c = Wire.read();      /* receive byte as a character */
    Serial.print(c);           /* print the character */
  }
 Serial.println("received");             /* to newline */

}

// function that executes whenever data is requested from master
void requestEvent() {
  Serial.println("request");
 Wire.write("Hello NodeMCU");  /*send string on request */
}


void setup() {
 Wire.begin(8); 
 Wire.pins(D1, D2);              
 Wire.onReceive(receiveEvent); 
 Wire.onRequest(requestEvent);
 Serial.begin(9600);          
 }



void loop() {
delay(100);
}