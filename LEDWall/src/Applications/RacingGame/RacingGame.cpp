#include <Arduino.h>
#include "RacingGame.h"

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

RacingGame::RacingGame() {

}


void reset(ShowPort* showport) {
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
    showport->ledtisch->clear();
    showport->ledtisch->show();

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

}

void RacingGame::onCreate(ShowPort* showport){
    lasttick = millis();
    lastfasttick = millis();

    counter =0;


  reset(showport);
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


void RacingGame::onRun(ShowPort* showport){ 

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

    if(i==0+fasttickercounter) {

        ison[i] =true;

    }

    if(i==3+fasttickercounter) {
        ison[i] = true;
    }

    if(i==6+fasttickercounter) {

        ison[i] = true;

    }

  if(i==9+fasttickercounter) {

        ison[i] = true;

    }

    if(i==12+fasttickercounter) {

        ison[i] = true;

    }


    }

    Serial.println(fasttickdelay);

    fasttickercounter++;
    lastfasttick = millis();

  }


int counter =0;
for(int y=0;y<15;y++) {
  counter++;
  for(int x=0;x<10;x++) {



    if(roadpieces[x][y]) {

      if(ison[y]) {
        

        
        showport->ledtisch->setcolor(0,160,0);
        showport->ledtisch->drawkoordinatensystem(x,y);

      }else{

        
        showport->ledtisch->setcolor(100,100,100);
        showport->ledtisch->drawkoordinatensystem(x,y);

      

      }

    }else{
       showport->ledtisch->setcolor(0,0,0);
        showport->ledtisch->drawkoordinatensystem(x,y);

     
    }

  }

if(counter>2) {
  counter=0;
}
}

  



showport->ledtisch->setcolor(100,0,0);
showport->ledtisch->drawkoordinatensystem(pixelposx,pixelposy);
showport->ledtisch->drawkoordinatensystem(pixelposx,pixelposy+1);
showport->ledtisch->show();

if(roadpieces[pixelposx][pixelposy]||roadpieces[pixelposx][pixelposy+1]) {
  for(int x=0;x<10;x++) {
    for(int y=0;y<15;y++) {
        showport->ledtisch->setcolor(100,0,0);
        showport->ledtisch->drawkoordinatensystem(x,y);
     
    }
  }
  showport->ledtisch->show();

   gameend = 1;


 }

}


}



void RacingGame::onDataReceive(String data,ShowPort* showport){

if(data=="h"){//hoch
pixelposy++;
}

if(data=="r"){//rechts
pixelposx++;
}

if(data=="l" ){//links
 pixelposx--;
}

if(data=="d"){//runter
pixelposy--;
}
if(data=="n") {//new game
    reset(showport);
    
}
 
}
void RacingGame::onStop(ShowPort* showport){

}

String RacingGame::getName(){
  return "RacingGame";
}