#include <Arduino.h>
#include "Application.h"

class FlappyBird: public Application{
public:
FlappyBird();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);

};