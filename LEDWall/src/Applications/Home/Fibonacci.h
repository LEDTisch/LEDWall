#include "../../utils/Device/LED-Tisch/LED-Tisch.h"
#include "Arduino.h"
#include "../../utils/Color.h"
class Fibonacci{

public:
#define SET_BIT(byte, bit) ((byte) |= (0x01 << (bit)))

    Fibonacci();
    void init(LEDTisch* ledtisch);
    void draw();
    void setPosition(int x,int y);
    bool* zahl(int zahl);
    byte zahlasbyte(int zahl);
    void clear();
    void drawZahl(bool i1, bool i2, bool i3, bool i4, bool i5);
    void drawZahl(bool* zahl);
    void drawZahl(byte zahl1, byte zahl2);


private:
    LEDTisch* ledtisch;
    int x=0;
    int y=0;
    int fi[5]={5,3,2,1,1};
    int f[5]={1,1,2,3,5};
    Color color[5];
};