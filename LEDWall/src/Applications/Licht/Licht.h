#include <Arduino.h>
#include "Application.h"

class Licht: public Application{
public:
Licht();
void onCreate(LEDTisch* ledtisch);
void onRun(LEDTisch* ledtisch);
void onStop();


};