#include <Arduino.h>
#include "Application.h"

class RacingGame: public Application{
public:
RacingGame();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);

};