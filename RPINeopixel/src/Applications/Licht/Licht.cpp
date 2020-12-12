//
// Created by tim on 08.12.20.
//

#include "Licht.h"
#ifndef LICHT_CPP
#define LICHT_CPP

Licht::Licht(){

}

void Licht::onCreate(SystemInterface* systeminterface){
    systeminterface->ledTisch->clear();
    systeminterface->ledTisch->setcolor(255,255,255);
    systeminterface->ledTisch->show();
}
void Licht::onDraw(SystemInterface* systeminterface){

}
void Licht::onDataReceive(char* data, SystemInterface* systeminterface,int ControllerID){



    if(strcmp ("LichtSchalter\n", data) == 0){

        if(lightstatus==false){
            systeminterface->ledTisch->setcolor(255,0,0);
            systeminterface->ledTisch->rect(0,6,10,7);



            lightstatus=true;
        }else{
            lightstatus=false;
            systeminterface->ledTisch->setcolor(0,0,0);
            systeminterface->ledTisch->rect(0,6,10,7);



        }


    }
}
void Licht::onRun(SystemInterface *systemInterface) {

}
void Licht::onStop(SystemInterface* systeminterface){

}

char* Licht::getName(){
    return "Licht";
}

#endif