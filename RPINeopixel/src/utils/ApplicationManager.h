#include "Application.h"

#ifndef APPLICATIONMANAGER_H
#define APPLICATIONMANAGER_H

class ApplicationManager{
public:

bool checkSystemCommand(char command[]) { //Check SystemCommand for Example Switch Application, LedTisch off... Can only be executed from Player 1

}

Application* getCurrentApplication() {
    return frontApplication;
}
Application* frontApplication;
void setfrontApp(Application* app){
    frontApplication=app;
}
private:
};

#endif