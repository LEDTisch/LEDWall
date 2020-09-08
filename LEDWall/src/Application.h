#ifndef Application_H
#define Application_H
#include "utils//Device/LED-Tisch/LED-Tisch.h"

class Application{
public:
  virtual ~Application() {}

  virtual void onCreate(LEDTisch* ledtisch)=0;
  virtual void onRun(LEDTisch* ledtisch)=0;
  virtual void onDataReceive(String data, LEDTisch* ledtisch)=0;
  virtual String getName()=0;
  virtual void onStop(LEDTisch* ledtisch)=0;


};
#endif