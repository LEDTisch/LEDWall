#include <Arduino.h>
#include "Application.h"

class FlappyBird: public Application{
public:
FlappyBird();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);

};