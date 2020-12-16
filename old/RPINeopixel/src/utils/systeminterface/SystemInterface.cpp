//
// Created by Felix on 06.12.2020.
//

#include "SystemInterface.h"

void SystemInterface::init() {
    //LED-Tisch init
    ledTisch->init();
    ledTisch->setcolor(255,255,0);
    ledTisch->clear();
}