#include <Arduino.h>
#include "Application.h"

class RacingGame: public Application{
public:
RacingGame();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);

};