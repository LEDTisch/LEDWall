#include <Arduino.h>
#include "Application.h"
#include "Player.h"
#include "Level.h"

class Sokoban: public Application{
public:
Sokoban();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);
private:
Player* player;
Level* level;
byte boxsize=0x02;//die Kisten können verschieden groß sein 2*2 oder 1*1 oder vieleicht auch 3*3 boxsize ist meistens auch die stepsize
byte stepsize=boxsize;
byte actGame[10*15];

};