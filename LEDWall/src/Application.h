#ifndef Application_H
#define Application_H
#include "utils/ShowPort.h"
#include "utils/Log.h"

class Application{
public:
  virtual ~Application() {}

  virtual void onCreate(ShowPort* showport)=0;
  virtual void onRun(ShowPort* showport)=0;
  virtual void onDataReceive(String data, ShowPort* showport)=0;
  virtual String getName()=0;
  virtual void onStop(ShowPort* showport)=0;


};
#endif