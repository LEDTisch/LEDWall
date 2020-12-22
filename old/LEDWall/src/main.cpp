#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Home/Home.h"
#include "utils//Device/LED-Tisch/LED-Tisch.h"
#include "Applications/Snake/Snake.h"
#include "Applications/Tetris/Tetris.h"
#include "Applications/RacingGame/RacingGame.h"
#include "Applications/FlappyBird/FlappyBird.h"
#include "Applications/Sokoban/Sokoban.h"


#include "utils/Log.h"


#include "utils/SystemInterface.h"
SystemInterface* systeminterface=new SystemInterface();




#define RunApps true


unsigned long secondTicker=0;
unsigned long timeSyncTicker=0;
int DataAvailablePin=8;
bool dataavailablechanche=false;

const int size=5;
int appanzahl=size;
Application* applications=new Home();

bool isReceiving=false;



void OnCreate_Application(){
  applications->onCreate(systeminterface);
  Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","create app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications->getName());
}
void OnRun_Application(){
  applications->onRun(systeminterface);
}
void OnDataReceive_Application(String m){
    applications->onDataReceive(m, systeminterface);
      Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","DataReceiv app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications->getName());
}
void OnStop_Application(){
applications->onStop(systeminterface);
  Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","stop app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications->getName());
}

/*void switchApp(int id) {
  OnStop_Application(currentApp);
  currentApp = id;
  OnCreate_Application(currentApp);
}*/
void switchApp(String s) {

 OnStop_Application();
delete applications;

if(s=="Tetris"){
  applications = new Tetris();

}else
if(s=="Licht"){
    applications = new Licht();
}else
if(s=="Home"){
    applications = new Home();
}else
if(s=="Snake"){
    applications = new Snake();
}else
if(s=="Racing Game"){
    applications = new RacingGame();
}else
if(s=="Flappy Bird") {
  applications = new FlappyBird();
}else
if(s=="Sokoban") {
  applications = new Sokoban();
}else{/////////////////////Falls keine dann Defaultapplication sonst Probleme
  applications = new Home();
}


OnCreate_Application();



}

//query
  #define transfere 0x01
  #define Time 0x02
  #define Weather 0x03
//subquery
  //transfere
    #define line 0x01
  //Time
    #define day 0x00
    #define month 0x01
    #define year 0x02
    #define houre 0x03
    #define minute 0x04
    #define second 0x05
  //Weather



void sentRequest(byte query, byte subquery){
  Log::println(Log::ESP_ARDUINO_CONNECTION_INFO,"info","sentRequest");
Serial2.write(query);
Serial2.write(byte(subquery | 0x80));
}


void setup(){
  pinMode(DataAvailablePin, INPUT);
  Serial2.begin(9600);
  Serial.begin(9600);

  systeminterface->init();

if(RunApps){
  OnCreate_Application();
}
//sentRequest(Time,houre);
systeminterface->ledtisch->setBrightness(0.5);
systeminterface->ledFeld->setBrightness(0.5);

}

int iindex=0;
char incommingbyte;
const int MaxLength=100;
char message[MaxLength];







void receiveAppData(){


        char vergleich[9]= {'s','w','i','t','c','h','T','o',':'};
        bool gleich=true;
        for(int i=0;i<9;i++){
          if(vergleich[i] != message[i]){
            gleich=false;
          }
        }
        if(gleich){
          int i=9;
          char mode[30];
          for(int i=0;i<30;i++){
            mode[i]=0x00;
          }
          while(message[i]!='#'){
              mode[i-9]=message[i];
              i++;

          }
          if(RunApps){
          switchApp(mode);
          }

          }else  {
        char vergleich_brightness[11]= {'b','r','i','g','h','t','n','e','s','s',':'};
        bool gleich_brightness=true;
        for(int i=0;i<11;i++){
          if(vergleich_brightness[i] != message[i]){
            gleich_brightness=false;
          }
        }

        if(gleich_brightness){
          int i_b=11;
          char mode[30];
          for(int i=0;i<30;i++){
            mode[i]=0x00;
          }
          while(message[i_b]!='#'){
              mode[i_b-11]=message[i_b];
              i_b++;

          }
          float brightness=atoi(mode);
          if(brightness>=0 && brightness<=100){
            systeminterface->ledtisch->setBrightness(brightness/100);
            systeminterface->ledFeld->setBrightness(brightness/100);
          }
          return;
          }
      

          }
        

          
          if(RunApps){
              OnDataReceive_Application(message);
          }
        

}
void ProcessTransfer(byte query, byte subquery){
switch(query){
  case transfere:{
    switch(subquery){
      case line:{
          receiveAppData();
        break;
      }
    }
    break;
  }
  case Time:{
    switch(subquery){
      case houre:{
        systeminterface->clocktime->setHour(atoi(message));
        break;
      }
      case minute:{
        systeminterface->clocktime->setMinute(atoi(message));
        break;
      }
      case second:{
        systeminterface->clocktime->setSecond(atoi(message));
        break;
      }
    }
    break;
  }
}
}

byte query;
byte subquery;
void serialreadupdate(){
  if(Serial2.available()) {
      incommingbyte=Serial2.read();
      Log::println(Log::ESP_ARDUINO_CONNECTION_DEBUG, "incommingbyte", incommingbyte);

      if(iindex < MaxLength-1){
        if(iindex>=2){
          message[iindex-2] = incommingbyte;
        }
        if(iindex==0){
          query=incommingbyte;
        }
        if(iindex==1){
            subquery=incommingbyte;         
        }
        iindex++;

      }else{
        Log::println(Log::ESP_ARDUINO_CONNECTION_ERROR, "ERROR:", "BufferOverflow");
      }
      if(incommingbyte == '\n'){
        for(int i=0;i<iindex;i++){
          if(message[i]=='\n'){
            message[i]='\0';
          }
        }
        iindex=0;
        
        //Verarbeitung///////////////////////////
        if(true){
          Log::println(Log::ESP_ARDUINO_CONNECTION_INFO,"message",message);
          Log::println(Log::ESP_ARDUINO_CONNECTION_INFO,"request",query);
          Log::print(Log::ESP_ARDUINO_CONNECTION_INFO,subquery);
          ProcessTransfer(query,subquery);
        
        }

        isReceiving=false;

        for (int j=0; j<MaxLength; ++j) {message[j] = 0; }////message clearen





        }

        

  }
}




void loop(){
      if(digitalRead(DataAvailablePin)){
      sentRequest(transfere, line);
      isReceiving=true;
      }
      while(isReceiving){
          serialreadupdate();
      }

      if(RunApps){
        OnRun_Application();
      }

      if(millis()>timeSyncTicker){
        sentRequest(Time, houre);
        sentRequest(Time, minute);
        sentRequest(Time, second);
        timeSyncTicker=millis()+10000;
      }
      if(millis()>secondTicker){
        Log::println(Log::TIME_AUSGABE,"Time",systeminterface->clocktime->getHour());
        Log::print(Log::TIME_AUSGABE, " : ");
        Log::print(Log::TIME_AUSGABE, systeminterface->clocktime->getMinute());
        Log::print(Log::TIME_AUSGABE, " : ");
        Log::print(Log::TIME_AUSGABE, systeminterface->clocktime->getSecond());
        systeminterface->clocktime->tick();
        secondTicker=millis()+1000;
      }


}
