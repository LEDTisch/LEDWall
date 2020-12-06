#ifndef Application_H
#define Application_H

#include "systeminterface/SystemInterface.h"

class Application{
public:
    virtual ~Application() {}

    virtual void onCreate(SystemInterface* systeminterface)=0;
    virtual void onRun(SystemInterface* systeminterface)=0;
    virtual void onDataReceive(char data[], SystemInterface* systeminterface,int ControllerID)=0;
    virtual char* getName()=0;
    virtual void onStop(SystemInterface* systeminterface)=0;


};
#endif