//
// Created by tim on 08.12.20.
//

#ifndef RPINEOPIXEL_LICHT_H
#define RPINEOPIXEL_LICHT_H


#include "../utils/Application.h"

class Licht: public Application {
public:
    Licht();
    void onCreate(SystemInterface* systeminterface);
    void onRun(SystemInterface* systeminterface);
    void onDataReceive(char* data, SystemInterface* systeminterface,int ControllerID);
    char* getName();
    void onStop(SystemInterface* systeminterface);
private:
    bool lightstatus=false;


};


#endif //RPINEOPIXEL_LICHT_H
