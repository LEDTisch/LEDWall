#include <Arduino.h>
#include "Application.h"

class Dunkel: public Application{
public:
Dunkel();
void onCreate(LEDTisch* ledtisch);
void onRun(LEDTisch* ledtisch);
void onDataReceive(String data);
String getName();
void onStop();


};