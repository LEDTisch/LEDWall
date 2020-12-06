
#include <stdint.h>
#include "RPI.h"
#include "RPI.cpp"
#include "neopixelcontroll/Neopixel.cpp"
#include "ControlSocket.cpp"

#include "Device/LED-Tisch/LEDTisch.cpp"

LEDTisch ledTisch=LEDTisch(10,15,1);
ControlSocket controlSocket = ControlSocket();

void ani_kreisaufl(){
    int i=0;
    while(i<20){
        ledTisch.setcolor(0,0,0);
        ledTisch.kreis(5,7,i,2);
        i=i+1;

        ledTisch.show();
        usleep(48*1000);

    }
}


void ani_peng(int x,int y){
    int i=0;
    while(i<10){
        ledTisch.setcolor(255,255,255);
       ledTisch.kreis(x,y,i,15);
        i=i+1;

        ledTisch.show();
        usleep(60*1000);


    }


}

void ani() {

    int i = 0;
    while (i < 20) {
        ledTisch.setcolor(i * 12, 255 - i * 12, 0);
        ledTisch.kreis(0, 0, i, 2);
        ledTisch.kreis(9, 14, i, 2);
        i = i + 1;

        ledTisch.show();
        usleep(48 * 1000);

    }


    ani_kreisaufl();
    //std::cout<<"Register PIN";

}

int main() {

    controlSocket.begin();
    ledTisch.init();
    ledTisch.setcolor(255,255,0);

    while(1) {

ani();
    }
 //   return 0;

    return 0;
}




//    mapPin(PIN);
//    pixels.begin();
//    pixels.setPixelColor(1,150,10,20);
//    pixels.show();
//    pixels.end();
//    unmapPin(PIN);