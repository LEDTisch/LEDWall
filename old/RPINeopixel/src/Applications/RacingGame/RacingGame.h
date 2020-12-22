//
// Created by tim on 08.12.20.
//

#ifndef RPINEOPIXEL_RACINGGAME_H
#define RPINEOPIXEL_RACINGGAME_H


#include "../../utils/Application.h"

class RacingGame: public Application{
public:
    RacingGame();
    void onCreate(SystemInterface* systeminterface);
    void onDraw(SystemInterface* systeminterface);
    void onDataReceive(char* data, SystemInterface* systeminterface,int ControllerID);
    char* getName();
    void onStop(SystemInterface* systeminterface);
    void onRun(SystemInterface* systemInterface);
};


#endif //RPINEOPIXEL_RACINGGAME_H
