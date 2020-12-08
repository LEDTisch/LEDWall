#include "Application.h"
#include "../Applications/Licht.cpp"

#ifndef APPLICATIONMANAGER_H
#define APPLICATIONMANAGER_H

class ApplicationManager {
public:

    ApplicationManager(SystemInterface* systemInterface) {
        this->systemInterface = systemInterface;
    }


    bool checkSystemCommand(char command[]) { //Check SystemCommand for Example Switch Application, LedTisch off... Can only be executed from Player 1

        return false;
    }

    Application *getCurrentApplication() {
        return frontApplication;
    }


    void setApplication(Application *app) {
        frontApplication = app;
    }

private:
    SystemInterface* systemInterface;
    Application *frontApplication = new Licht();

};

#endif