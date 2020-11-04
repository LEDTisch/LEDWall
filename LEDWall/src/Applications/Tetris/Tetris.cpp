#include <Arduino.h>
#include "Tetris.h"



Tetris::Tetris(){

}






void Tetris::onCreate(ShowPort* showport){
this->showport=showport;





    block.init(this->showport);
  

  this->showport->ledtisch->clear();

    this->showport->ledFeld->setcolor(100,0,0);


 //addonserial.begin(9600);




  randomSeed(analogRead(0));
 // Serial.begin(115200);


 // Serial1.begin(9600);

//mySoftwareSerial.begin(9600);
/*
  Serial.println();
  Serial.println(F("DFRobot DFPlayer Mini Demo"));
  Serial.println(F("Initializing DFPlayer ... (May take 3~5 seconds)"));
  
  if (!myDFPlayer.begin(mySoftwareSerial)) {  //Use softwareSerial to communicate with mp3.
    Serial.println(F("Unable to begin:"));
    Serial.println(F("1.Please recheck the connection!"));
    Serial.println(F("2.Please insert the SD card!"));
    //while(true);
  }
  Serial.println(F("DFPlayer Mini online."));
  
  
  myDFPlayer.volume(5);  //Set volume value. From 0 to 30
  */
  //myDFPlayer.play(5);  //Play the first mp3 

  //myDFPlayer.loop(0);

  //myDFPlayer.play(2);






}
void Tetris::onRun(ShowPort* showport){

if(beistartausfuhren==true){
  ani_start();
  beistartausfuhren=false;
  art=random(1,7);
}

if(stop==1){

int rk= block.reihenkontrolle();
reihen_gesamt=reihen_gesamt+rk;
if(rk > 0){
    block.clearall();
    block.setcolor(255,0,0);
    block.drawall();
}
switch(rk){
  case 1:
  punkte=punkte+punkteeinereihe*level;
  //sound(sound_einereihe);
  break;
  case 2:
  punkte=punkte+punktezewireihe*level;
  //sound(sound_zweireihen);
  break;
  case 3:
  punkte=punkte+punktedreireihe*level;
  //sound(sound_dreireihen);
  break;
  case 4:
  punkte=punkte+punkteevierreihe*level;
  //sound(sound_vierreihen);
  break;
}
    
   if(reihen_gesamt>level*5){
     reihen_gesamt=0;
     level_up();
   }
   Serial.println(level);


      

    
//levelanzeigen();
block.show();

    artnext=random(1,7);
    //art=3;
    setblockcolor(art,0);

show_nextblock(artnext,art);
     
    block.setblockto(5,15,art);
    //block.setblockto(5,10,1);
    
    stop=0;

 //matrix.zahl(punkte,0,1);
 levelanzeigen();
}
if(stop==0){

  


//addonserial.print(9);

//levelanzeigen();
//block.setcolor(r,g,b);
                //y   x    in dem all array//
//Serial.println(all[1][9]);
//Serial.println(block.getdrehung());
if(millis()>m_save+speed){

if(blockschneller==true){
  punkte=punkte+blockschnellerpunkte*level;
}

  block.draw();
  if(block.kontrolle(2)==0 && block.kontrolle(5)==0){
    Serial.println("blockdown ");
  block.down();
  }else{
      if(block.writeblocktoall()){
        GameOver();
      }else{
      stop=1;
      if(blockschneller){
          //sound(sound_harddrop);
      }else{
          // sound(sound_softdrop);
      }
      ani_peng(block.getblockx(),block.getblocky());
      punkte=punkte+punkte_fur_block_setzen*level;
      }

       m_save=millis();
  }


  m_save=millis();
}


//steuerung

/*
if(drehen.ispushback()){
  block.drehen();
  
}

if(rechts.ispushback()&&block.kontrolle(1)==0&&block.kontrolle(6)==0){
  block.right();
  
}

if(links.ispushback()&&block.kontrolle(3)==0&&block.kontrolle(7)==0){
  block.left();
 
}

if(runter.ispressed()){
  speed=50;
 
}else{
speed=1000;
}
*/





/*
   if(Serial1.available()){
     
    
     //blue=Serial1.read();
     Serial1.setTimeout(50);
     String bb=Serial1.readString();


if(bb[0]=='h'){
  helligkeit=((int)bb[1]-48)*100    +  ((int)bb[2]-48)*10      +    ((int)bb[3]-48)*1;
  //Serial.println(  ((int)bb[1]-48)*100    +  ((int)bb[2]-48)*10      +    ((int)bb[3]-48)*1);
}


if(bb[0]=='s'){
blue=bb[1];
if(blue=='4'){
  if(block.drehen()){
//sound(sound_rotatefailed);
  }else{
   // sound(sound_drehen);
  }
  
}

if(blue=='2'&&block.kontrolle(1)==0&&block.kontrolle(6)==0){
  block.right();
//  sound(sound_button);
}

if(blue=='0'&&block.kontrolle(3)==0&&block.kontrolle(7)==0){
  block.left();
 // sound(sound_button);
}

if(blue=='7'){
  speed=50;
blockschneller=true;
}else{
speed=1000;

 blockschneller=false;
}


if(blue=='1'){
neuesspiel();
}
}



   }*/

   
//steuerung end

}
if(stop==1){
art=artnext;
}
}
void Tetris::onDataReceive(String data,ShowPort* showport){
if(data=="t"){
  if(block.drehen()){
//sound(sound_rotatefailed);
  }else{
   // sound(sound_drehen);
  }
  
}

if(data=="r"&&block.kontrolle(1)==0&&block.kontrolle(6)==0){
  block.right();
//  sound(sound_button);
}

if(data=="l"&&block.kontrolle(3)==0&&block.kontrolle(7)==0){
  block.left();
 // sound(sound_button);
}

if(data=="d"){
  speed=50;
blockschneller=true;
}else{
speed=1000;

 blockschneller=false;
}


if(data=="n"){
neuesspiel();
}
   
}
void Tetris::onStop(ShowPort* showport){

}

String Tetris::getName(){
  return "Tetris";
}