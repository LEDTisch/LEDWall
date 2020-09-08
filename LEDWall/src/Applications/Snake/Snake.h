#include <Arduino.h>
#include "Application.h"
#include "Snake_game.h"
#include "../../utils/Device/LED-Tisch/LED-Tisch.h"


class Snake: public Application{
public:
Snake();
void onCreate(LEDTisch* ledtisch);
void onRun(LEDTisch* ledtisch);
void onDataReceive(String data,LEDTisch* ledtisch);
String getName();
void onStop(LEDTisch* ledtisch);

void steuerung();
void GameOver(LEDTisch* ledtisch);

private:
Snake_game snake=Snake_game();
char blue;
long schritt=0;
long takt=millis();
long verlauf=0;
int u=10;


};