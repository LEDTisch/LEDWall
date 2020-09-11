#include <Arduino.h>
#include "Application.h"
#include "Fibonacci.h"

class Home: public Application{
public:
Home();
void onCreate(ShowPort* showport);
void onRun(ShowPort* showport);
void onDataReceive(String data,ShowPort* showport);
String getName();
void onStop(ShowPort* showport);
private:
Fibonacci fibonacci=Fibonacci();
uint32_t image[150]{
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,0x000000,
0x11ff00,0x11ff00,0x11ff00,0x11ff00,0x11ff00,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0xff0000,0xff0000,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0xff0000,0x11ff00,0x11ff00,0xff0000,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,
0x11ff00,0x11ff00,0x11ff00,0x11ff00,0x11ff00,0x11ff00,0xffc800,0xffc800,0xffc800,0xffc800,

};


};