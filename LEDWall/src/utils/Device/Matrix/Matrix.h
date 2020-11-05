#include "Arduino.h"
#include "LedControl/src/LedControl.h"
//#include <LedControl.h>

#ifndef Matrix_H
#define Matrix_H


class Matrix
{
   private:
        int datain_pin;
        int clk_pin;
        int load_pin;


       LedControl* lc;

  
   public:



      Matrix(int _datain_pin,int _clk_pin,int _load_pin);
    void init(int brightness);
      void zahl(int zahl,int x,int a);
      void setkcolumn(int c,byte b);
       

};
#endif