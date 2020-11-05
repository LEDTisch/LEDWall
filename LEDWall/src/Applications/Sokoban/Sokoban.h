#include <Arduino.h>
#include "Application.h"

class Sokoban: public Application{
public:
Sokoban();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);
private:

};