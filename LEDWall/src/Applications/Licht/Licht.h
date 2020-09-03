#include <Arduino.h>
#include "Application.h"

class Licht: public Application{
public:
Licht();
void onCreate(LEDTisch* ledtisch);
void onRun(LEDTisch* ledtisch);
void onDataReceive(String data);
String getName();
void onStop();


};