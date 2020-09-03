#ifndef Application_H
#define Application_H
#include "utils/LED-Tisch.h"

class Application{
public:
  virtual ~Application() {}

  virtual void onCreate(LEDTisch* ledtisch)=0;
  virtual void onRun(LEDTisch* ledtisch)=0;
  virtual void onDataReceive(String data)=0;
  virtual String getName()=0;
  virtual void onStop()=0;


};
#endif