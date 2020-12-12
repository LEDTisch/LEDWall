//
// Created by tim on 08.12.20.
//

#include <cstring>
#include <sys/time.h>
#include "RacingGame.h"

#ifndef RACINGGAME_CPP
#define RACINGGAME_CPP
int counter = 0;
int row_x[10];
float fasttickdelay = 100; //75
boolean ison[15];
int fasttickercounter =0;
int gameend = 0;
int referenzpoint =4; //Point where the left roadside is
int pixelposx=referenzpoint+1;
int pixelposy=5;
boolean roadpieces[10][16];
unsigned long lasttick=0;
unsigned long lastfasttick=0;
boolean firstone = true;
int keepstate = 0; //Keep State meens a natural street


unsigned long millis() {
    struct timeval tp;
    gettimeofday(&tp, NULL);
    return (long long) tp.tv_sec * 1000L + tp.tv_usec / 1000; //get current timestamp in milliseconds
}

RacingGame::RacingGame() {

}


void reset(SystemInterface* systeminterface) {
    for(int i=0;i<10;i++){
        row_x[i]=0;
    }
    for(int i=0;i<15;i++){
        ison[i]=false;
    }

    for(int x=0;x<10;x++){
        for(int y=0;y<6;y++){
            roadpieces[x][y]=false;
        }
    }
    systeminterface->ledTisch->clear();
    systeminterface->ledTisch->show();

    for(int x=0;x<10;x++) {
        for(int y=0;y<15;y++) {
            roadpieces[x][y] = false;
        }

    }

    firstone = true;

    pixelposy = 5;
    pixelposx = 5;
    keepstate = 0;
    gameend = 0;
    fasttickdelay = 100;
    fasttickercounter =1;

}

void RacingGame::onCreate(SystemInterface* systeminterface){
    lasttick = millis();
    lastfasttick = millis();

    counter =0;


    reset(systeminterface);
}


void tick()
{



    if(keepstate==0) {
        if(random()%10>7) {
            if (random()%10 > 4)
            {
                keepstate++;

                if (referenzpoint != 0)
                {
                    referenzpoint--;
                }
            }
            else
            {
                keepstate++;

                if (referenzpoint < 5)
                {
                    referenzpoint++;
                }
            }

        }

    }else{
        keepstate++;
        if(keepstate==2) {
            keepstate = 0;
        }

    }




    if(firstone) {
        firstone = false;
        for(int i=0;i<referenzpoint+1;i++) {

            roadpieces[i][15] = true;

        }

        for(int i=referenzpoint+4;i<10;i++) {
            roadpieces[i][15] = true;
        }


    }



    for(int y=0;y<15;y++) {
        for(int x=0;x<10;x++) {

            roadpieces[x][y] = roadpieces[x][y+1];

        }


    }


    for(int i=0;i<10;i++) {
        roadpieces[i][15] = false;
    }


    roadpieces[referenzpoint][15] = true;
    roadpieces[referenzpoint+4][15] = true;

}

void RacingGame::onRun(SystemInterface* systemInterface) {

    if(gameend ==0) {


        if ((millis() - lastfasttick) >= fasttickdelay) {


            fasttickdelay -= 0.13f;

            tick();

            if (fasttickercounter == 3) {
                fasttickercounter = 0;
            }

            for (int i = 0; i < 15; i++) {
                ison[i] = false;

                if ((i + fasttickercounter) % 3 == 0) {
                    ison[i] = true;
                }
/*
    if(i==12+fasttickercounter) {

        ison[i] = true;

    }
*/

            }


            fasttickercounter++;
            lastfasttick = millis();

        }

    }


}

void RacingGame::onDraw(SystemInterface* systeminterface){

    if(gameend ==0) {


        if ((millis() - lastfasttick) >= fasttickdelay)
        {


            fasttickdelay-=0.13f;

            tick();

            if(fasttickercounter==3) {
                fasttickercounter=0;
            }

            for(int i=0;i<15;i++) {
                ison[i] = false;

                if((i+fasttickercounter)%3==0) {
                    ison[i] =true;
                }
/*
    if(i==12+fasttickercounter) {

        ison[i] = true;

    }
*/

            }



            fasttickercounter++;
            lastfasttick = millis();

        }


        int counter =0;
        for(int y=0;y<15;y++) {
            counter++;
            for(int x=0;x<10;x++) {



                if(roadpieces[x][y]) {

                    if(ison[y]) {



                        systeminterface->ledTisch->setcolor(0,160,0);
                        systeminterface->ledTisch->drawkoordinatensystem(x,y);

                    }else{


                        systeminterface->ledTisch->setcolor(100,100,100);
                        systeminterface->ledTisch->drawkoordinatensystem(x,y);



                    }

                }else{
                    systeminterface->ledTisch->setcolor(0,0,0);
                    systeminterface->ledTisch->drawkoordinatensystem(x,y);


                }

            }

            if(counter>2) {
                counter=0;
            }
        }





        systeminterface->ledTisch->setcolor(100,0,0);
        systeminterface->ledTisch->drawkoordinatensystem(pixelposx,pixelposy);
        systeminterface->ledTisch->drawkoordinatensystem(pixelposx,pixelposy+1);


        if(roadpieces[pixelposx][pixelposy]||roadpieces[pixelposx][pixelposy+1]) {
            for(int x=0;x<10;x++) {
                for(int y=0;y<15;y++) {
                    systeminterface->ledTisch->setcolor(100,0,0);
                    systeminterface->ledTisch->drawkoordinatensystem(x,y);

                }
            }

            gameend = 1;


        }
    }


}



void RacingGame::onDataReceive(char* data, SystemInterface* systeminterface,int ControllerID){

    if(strcmp(data,"h\n")==0){//hoch
        pixelposy++;
    }

    if(strcmp(data,"r\n")==0){//rechts
        pixelposx++;
    }

    if(strcmp(data,"l\n")==0 ){//links
        pixelposx--;
    }

    if(strcmp(data,"d\n")==0){//runter
        pixelposy--;
    }
    if(strcmp(data,"n\n")==0) {//new game
        reset(systeminterface);

    }

}
void RacingGame::onStop(SystemInterface* systeminterface){

}

char* RacingGame::getName(){
    return "RacingGame";
}

#endif