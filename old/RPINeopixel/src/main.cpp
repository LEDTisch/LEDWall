
#include <stdint.h>
#include "utils/GPIO/RPI.h"
#include "utils/GPIO/RPI.cpp"
#include "ControlSocket.cpp"
#include "utils/systeminterface/SystemInterface.cpp"

#include "utils/Device/neopixeldevice/LEDTisch/LEDTisch.cpp"
#include "utils/Application.h"
#include "utils/ApplicationManager.h"


SystemInterface* systeminterface=new SystemInterface();

ApplicationManager* applicationManager=new ApplicationManager(systeminterface);


ControlSocket controlSocket = ControlSocket(applicationManager,systeminterface);

void ani_kreisaufl(){
    int i=0;
    while(i<20){
        systeminterface->ledTisch->setcolor(0,0,0);
        systeminterface->ledTisch->kreis(5,7,i,2);
        i=i+1;

        systeminterface->ledTisch->show();
        usleep(60*1000);

    }
}


void ani_peng(int x,int y){
    int i=0;
    while(i<10){
        systeminterface->ledTisch->setcolor(255,255,255);
        systeminterface->ledTisch->kreis(x,y,i,15);
        i=i+1;

        systeminterface->ledTisch->show();
        usleep(60*1000);


    }


}

void ani() {

    int i = 0;
    while (i < 20) {
        systeminterface->ledTisch->setcolor(i * 12, 255 - i * 12, 0);
        systeminterface->ledTisch->kreis(0, 0, i, 2);
        systeminterface->ledTisch->kreis(9, 14, i, 2);
        i = i + 1;

        systeminterface->ledTisch->show();
        usleep(60 * 1000);

    }


    ani_kreisaufl();
    //std::cout<<"Register PIN";

}

int main() {

    systeminterface->init();
    controlSocket.begin();
    applicationManager->begin();



    while(1) {

if(applicationManager->getCurrentApplication()!=NULL)
    applicationManager->getCurrentApplication()->onRun(systeminterface);

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