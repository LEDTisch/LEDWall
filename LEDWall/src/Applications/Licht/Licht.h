#include <Arduino.h>
#include "Application.h"

class Licht: public Application{
public:
Licht();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);
private:
bool lightstatus=false;

};