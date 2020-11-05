#include <Arduino.h>
#include "Application.h"

#include "Block.h"


class Tetris: public Application{
public:
Tetris();
void onCreate(SystemInterface* systeminterface);
void onRun(SystemInterface* systeminterface);
void onDataReceive(String data,SystemInterface* systeminterface);
String getName();
void onStop(SystemInterface* systeminterface);


private:
SystemInterface* systeminterface;
Block block=Block(5,8,5);

int v=0;
int speed=1000;
char blue;

int helligkeit=10;

unsigned long m_save=millis();
int stop=1;
int art=0;
int artnext;

int reihen_gesamt=0;

int level=1;
int punkte=0;
boolean blockschneller=false;
//festlegung
int punkte_fur_block_setzen=5;
int blockschnellerpunkte=1;
int punkteeinereihe=100;
int punktezewireihe=200;
int punktedreireihe=400;
int punkteevierreihe=600;

bool beistartausfuhren=true;

int levelold=0;


int sound_output=1;

#define sound_start 5
#define sound_gameover 4
#define sound_einereihe 18
#define sound_zweireihen 17
#define sound_dreireihen 19
#define sound_vierreihen 20
#define sound_drehen 12
#define sound_button 11
#define sound_softdrop 13
#define sound_harddrop 7
#define sound_rotatefailed 1
#define sound_levelup 2

/*
void send(String s){
  addonserial.print("A");
  addonserial.print(s);
  addonserial.print("E");
  
}

void sendsound(int s){
  addonserial.print("A");
  addonserial.print(1);
  if(s<10){
    addonserial.print(0);
    addonserial.print(s);
  }else{
  addonserial.print(s);
  }
  addonserial.print("E");
  
}


void sound(int s){
if(sound_output==0){
myDFPlayer.play(s);
}
if(sound_output==1){
 
  sendsound(s);
  
  Serial.println("sound");
}

}


*/



void ani_kreisaufl(){
  int i=0;
    while(i<20){
    block.setcolor(0,0,0);
    this->systeminterface->ledtisch->kreis(5,7,i,2);
  i=i+1;
  
  block.show();
  delay(0);

  }
}

void ani_gameover(){
    int i=0;
  while(i<20){
    block.setcolor(i*12,255-i*12,0);
  this->systeminterface->ledtisch->kreis(0,0,i,2);
  this->systeminterface->ledtisch->kreis(9,14,i,2);
  i=i+1;
  
  block.show();
  delay(0);

  }



  ani_kreisaufl();
}



uint32_t image[150]{
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
65280,
65280,
65280,
65280,
65280,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
921328,
921328,
921328,
921328,
921328,
921328,
921328,
921328,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15740942,
15732291,
15732291,
15740942,
15740942,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
0,
0,
65280,
0,
0,
65280,
0,
0,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,
65280,











};




void ani_start(){

  //block.l.drawImage(image,10,15);
//delay(50000);

  //irstreifen.on();
  delay(20);
  //irstreifen.rot(4);

  this->systeminterface->ledFeld->setRotation(0);
  this->systeminterface->ledFeld->buchstaben(0,3,"hi",false,0,9,1000);
  //sound(sound_start);
    int i=0;
  while(i<10){
    block.setcolor(i*25,255-i*25,0);
  this->systeminterface->ledtisch->kreis(5,7,i,2);
  i=i+1;
  
  block.show();
  this->systeminterface->ledFeld->show();
  delay(0);

  }
  //delay(5000);
ani_kreisaufl();
this->systeminterface->ledFeld->clear();

}
void levelanzeigen(){
  this->systeminterface->ledFeld->setRotation(0);
  //led.clear();
  if(level==levelold){
     this->systeminterface->ledFeld->setcolor(255,0,0);
     this->systeminterface->ledFeld->zahl(0,2,level);
  }else{
  this->systeminterface->ledFeld->setcolor(0,0,0);
  this->systeminterface->ledFeld->zahl(0,2,levelold);
     this->systeminterface->ledFeld->setcolor(255,0,0);
     this->systeminterface->ledFeld->zahl(0,2,level);
     levelold=level;
  }
  this->systeminterface->ledFeld->show();
  this->systeminterface->ledFeld->setRotation(1);
}


void ani_peng(int x,int y){
    int i=0;
  while(i<10){
    block.setcolor(255,255,255);
  this->systeminterface->ledtisch->kreis(x,y,i,15);
  i=i+1;
  
  block.show();
  delay(0);
  block.clearall();
  //levelanzeigen();
  block.drawall();
  

  }


}




void neuesspiel(){
  //sound(sound_gameover);
ani_gameover();


  block.clearallarray();
  block.clearall();

        punkte=0;
        level=1;

        //matrix.lc.clearDisplay(0);
        //matrix.lc.clearDisplay(1);
        //matrix.lc.clearDisplay(2);
        //matrix.lc.clearDisplay(3);
      //matrix.zahl(punkte,0,1);
  stop=1;
}


void GameOver(){
  neuesspiel();
}




void level_up(){
  level=level+1;
  levelanzeigen();
  
}

void setblockcolor(int art,int d){
      int r;
    int g;
    int b;
    switch(art){
      case 1:
      r=255;
      g=0;
      b=0;
    
      break;
      case 2:
      r=0;
      g=255;
      b=0;
      break;
            case 3:
      r=255;
      g=255;
      b=0;
      break;
            case 4:
      r=255;
      g=0;
      b=255;
      break;
            case 5:
      r=0;
      g=255;
      b=255;
      break;
            case 6:
      
      r=0;
      g=0;
      b=255;
      break;
    }
 
if(d==0){
    block.setcolor(r,g,b);
}else if(d==1){
  this->systeminterface->ledFeld->setcolor(r,g,b);
}
}


void show_nextblock(int art,int artalt){
int xx=4;
int yy=-1;
int blocks_gros[6][4][6] {
0,0,1,1,1,1,    
0,0,1,1,1,1,    
1,1,1,1,0,0,
1,1,1,1,0,0,

1,1,1,1,0,0,    
1,1,1,1,0,0,    
0,0,1,1,1,1,
0,0,1,1,1,1,

0,0,0,0,0,0,  
0,0,0,0,0,0,   
1,1,1,1,1,1,
1,1,1,1,1,1,

1,1,1,1,0,0,    
1,1,1,1,0,0,    
1,1,1,1,0,0,
1,1,1,1,0,0,

0,0,1,1,0,0,    
0,0,1,1,0,0,    
1,1,1,1,1,1,
1,1,1,1,1,1,

0,0,0,0,1,1,    
0,0,0,0,1,1,    
1,1,1,1,1,1,
1,1,1,1,1,1

};

this->systeminterface->ledFeld->drehung_set(1);

this->systeminterface->ledFeld->setcolor(0,0,0);
for(int x=0;x<6;x=x+1){
  for(int y=0;y<4;y=y+1){
  if(blocks_gros[artalt-1][y][x]==1){
  this->systeminterface->ledFeld->drawkoordinatensystem(x+xx,(4-y)+yy);
  }
}
}

setblockcolor(art,1);

for(int x=0;x<6;x=x+1){
  for(int y=0;y<4;y=y+1){
  if(blocks_gros[art-1][y][x]==1){
  this->systeminterface->ledFeld->drawkoordinatensystem(x+xx,(4-y)+yy);
  }
}
}

this->systeminterface->ledFeld->show();

}



 int numpixels=60;


void regenbogen(){

for(int r=0;r<numpixels;r=r+1){
  if(r<numpixels/3+1){
  this->systeminterface->lightring->light(r, 255/(numpixels/3)*r,255-255/(numpixels/3)*r,0);
  }
    if(r>numpixels/3 && r< numpixels/3*2+1){
  this->systeminterface->lightring->light(r, 255/(numpixels/3)*((numpixels/3)-r),0,255-255/(numpixels/3)*((numpixels/3)-r));
  }
      if(r>numpixels/3*2 ){
  this->systeminterface->lightring->light(r, 0,255-255/(numpixels/3)*((numpixels/3)*2-r),255/(numpixels/3)*((numpixels/3*2)-r));
  }
 // ring.setPixelColor(r, ring.Color(255/60*r,255-255/60*r,0)); // Pixel1 leuchtet in der Farbe Grün
this->systeminterface->lightring->show(); 
}


}









void wheelpos(int wheelpos){
  if(wheelpos < 85){
    this->systeminterface->lightring->light(map(wheelpos,0,255,0,60), wheelpos*3,255-wheelpos*3,0);

  }else if(wheelpos  < 170){
    this->systeminterface->lightring->light(map(wheelpos,0,255,0,60), 255-wheelpos*3,0,wheelpos*3);
  }else{
    this->systeminterface->lightring->light(map(wheelpos,0,255,0,60), 0,wheelpos*3,255-wheelpos*3);
  }
}




void regenbogen2(){

for(int i=0;i<256;i=i+1){
  wheelpos(i);
}
this->systeminterface->lightring->show();


  }



};