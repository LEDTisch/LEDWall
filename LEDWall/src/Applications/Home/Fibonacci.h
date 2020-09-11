#include "../../utils/Device/LED-Tisch/LED-Tisch.h"
#include "Arduino.h"
#include "../../utils/Color.h"
class Fibonacci{

public:
    Fibonacci();
    void init(LEDTisch* ledtisch);
    void draw();
    void setPosition(int x,int y);
    void zahl();
private:
    LEDTisch* ledtisch;
    int x=0;
    int y=0;
    int fi[5]={5,3,2,1,1};
    int f[5]={1,1,2,3,5};
    Color color[5];
};