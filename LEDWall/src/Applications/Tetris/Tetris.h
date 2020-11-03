#include <Arduino.h>
#include "Application.h"



class Tetris: public Application{
public:
Tetris();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);


private:



};