#include <Arduino.h>
#include "Application.h"

class Licht: public Application{
public:
Licht();
void onCreate(LEDTisch* ledtisch);
void onRun(LEDTisch* ledtisch);
void onDataReceive(String data,LEDTisch* ledtisch);
String getName();
void onStop(LEDTisch* ledtisch);
private:
bool lightstatus=false;

};