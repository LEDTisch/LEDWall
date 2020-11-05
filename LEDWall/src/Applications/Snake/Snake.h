#include <Arduino.h>
#include "Application.h"
#include "Snake_game.h"



class Snake: public Application{
public:
Snake();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);

void steuerung();
void GameOver(SystemInterface* systeminterface);

private:
Snake_game snake=Snake_game();
long schritt=0;
long takt=millis();
long verlauf=0;
int u=10;


};