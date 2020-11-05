#ifndef Application_H
#define Application_H
#include "utils/SystemInterface.h"
#include "utils/Log.h"

class Application{
public:
  virtual ~Application() {}

  virtual void onCreate(SystemInterface* systeminterface)=0;
  virtual void onRun(SystemInterface* systeminterface)=0;
  virtual void onDataReceive(String data, SystemInterface* systeminterface)=0;
  virtual String getName()=0;
  virtual void onStop(SystemInterface* systeminterface)=0;


};
#endif