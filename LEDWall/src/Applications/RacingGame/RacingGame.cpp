#include <Arduino.h>
#include "RacingGame.h"

int counter = 0;
int row_x[10];
const int tickdelay = 75;
const int fasttickdelay = 75;
boolean ison[15];
int fasttickercounter =0;

int referenzpoint =4;
int pixelposx=referenzpoint+1;
int pixelposy=5;
int ledtofade[10][15];
boolean roadpieces[10][16];
unsigned long lasttick;
unsigned long lastfasttick;
int gameend = 0;

RacingGame::RacingGame() {

}


void reset(ShowPort* showport) {
    showport->ledtisch->clear();
    showport->ledtisch->show();

   for(int x=0;x<10;x++) {
     for(int y=0;y<15;y++) {
       roadpieces[x][y] = false;
     }

   }

   pixelposy = 5;
   pixelposx = 5;

gameend = 0;

}

void RacingGame::onCreate(ShowPort* showport){
    lasttick = millis();
    lastfasttick = millis();

    counter =0;


  reset(showport);
}


void tick()
{


if(random()%10>7) {
  if (random()%10 > 4)
  {
    if (referenzpoint != 0)
    {
      referenzpoint--;
    }
  }
  else
  {
    if (referenzpoint < 5)
    {
      referenzpoint++;
    }
  }

}else{

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


 if(true) {
     Serial.println("playing");

if ((millis() - lastfasttick) >= fasttickdelay)
  {
      tick();
    Serial.println("ticking");
    if(fasttickercounter==14) {
      fasttickercounter=0;
    }

    for(int i=0;i<15;i++) {
      ison[i] = false;
    }

   
    
      ison[14-fasttickercounter] = true;

      
    ison[14-fasttickercounter+4] = true;
    ison[14-fasttickercounter+8] = true;


    fasttickercounter++;
    lastfasttick = millis();

  }


int counter =0;
for(int y=0;y<15;y++) {
  counter++;
  for(int x=0;x<10;x++) {



    if(roadpieces[x][y]) {

      if(ison[y]) {
        

        
        showport->ledtisch->setcolor(0,50,0);
        showport->ledtisch->drawkoordinatensystem(x,y);

      }else{

        
        showport->ledtisch->setcolor(20,20,20);
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

   // gameover = true;


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