#include "SystemInterface.h"



void SystemInterface::init(){

  ledtisch->init(10);
  ledtisch->setcolor(20,20,0);
  ledtisch->clear();

  ledFeld->init(11);
  ledFeld->clear();
  ledFeld->setcolor(20,20,0);
  ledFeld->show();

  lightring->clear();
  lightring->show();
}