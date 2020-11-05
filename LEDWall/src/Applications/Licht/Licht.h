#include <Arduino.h>
#include "Application.h"

class Licht: public Application{
public:
Licht();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);
private:
bool lightstatus=false;

};