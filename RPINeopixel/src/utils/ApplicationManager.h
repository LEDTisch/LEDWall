#include "Application.h"
#include "../Applications/Licht/Licht.cpp"
#include "../Applications/RacingGame/RacingGame.cpp"

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
    void setallowDrawing(bool allow){
        allowDrawing=allow;
    }

    unsigned long millis() {
        struct timeval tp;
        gettimeofday(&tp, NULL);
        return (long long) tp.tv_sec * 1000L + tp.tv_usec / 1000; //get current timestamp in milliseconds
    }

    void begin() {
        std::thread drawingThread(&ApplicationManager::drawingThread,*this);
        drawingThread.detach();
    }

private:
    bool allowDrawing=true;
    SystemInterface* systemInterface;
    Application *frontApplication = new Licht();

    void drawingThread() {
        unsigned long time;
        while (1){
            time=millis();
            if(!allowDrawing)continue;
            if(frontApplication!=NULL)
            frontApplication->onDraw(this->systemInterface);
            systemInterface->ledTisch->show();
            while(millis()-time<60);
        }

    }

};

#endif