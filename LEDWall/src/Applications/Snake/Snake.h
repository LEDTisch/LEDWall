#include <Arduino.h>
#include "Application.h"
#include "Snake_game.h"



class Snake: public Application{
public:
Snake();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);

void steuerung();
void GameOver(ShowPort* showport);

private:
Snake_game snake=Snake_game();
char blue;
long schritt=0;
long takt=millis();
long verlauf=0;
int u=10;


};