#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Dunkel/Dunkel.h"
#include "utils/LED-Tisch.h"
#include "Communication/i2c.h"

LEDTisch* ledtisch=new LEDTisch(10,15,1);

int currentApp=0;

Application* applications[10]={new Licht(), new Dunkel()};
i2c* connection = new i2c();


void switchApp(int id) {

  applications[currentApp]->onStop();
  currentApp = id;
  applications[currentApp]->onCreate(ledtisch);

}
void setup(){
  connection->init();
  Serial.begin(9600);
  ledtisch->init(10);
  ledtisch->setcolor(20,20,0);
  ledtisch->clear();


applications[currentApp]->onCreate(ledtisch);
}

void loop(){
  applications[currentApp]->onRun(ledtisch);

}

