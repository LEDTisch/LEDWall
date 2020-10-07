#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Home/Home.h"
#include "utils//Device/LED-Tisch/LED-Tisch.h"
#include "Applications/Snake/Snake.h"
#include "utils/Log.h"


#include "utils/ShowPort.h"
ShowPort* showport=new ShowPort();




#define RunApps true



int DataAvailablePin=8;
bool dataavailablechanche=false;

int currentApp=0;
const int size=3;
int appanzahl=size;
Application* applications[size]={new Home(),new Licht(),  new Snake()};

bool isReceiving=false;



void OnCreate_Application(int id){
  applications[id]->onCreate(showport);
  Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","create app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications[id]->getName());
}
void OnRun_Application(int id){
  applications[id]->onRun(showport);
}
void OnDataReceive_Application(int id, String m){
    applications[id]->onDataReceive(m, showport);
      Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","DataReceiv app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications[id]->getName());
}
void OnStop_Application(int id){
applications[id]->onStop(showport);
  Log::println(Log::APPLICATION_MANAGE_INFO,"app_zyklus","stop app: ");
  Log::print(Log::APPLICATION_MANAGE_INFO, applications[id]->getName());
}

void switchApp(int id) {
  OnStop_Application(currentApp);
  currentApp = id;
  OnCreate_Application(currentApp);
}
void switchApp(String s) {
  int id=0;
  for(int i=0;i<appanzahl;i++){
    if(s==applications[i]->getName()){
      id=i;
    }
  }
  OnStop_Application(currentApp);
  currentApp = id;
  OnCreate_Application(currentApp);
}



void setup(){
  pinMode(DataAvailablePin, INPUT);
  Serial2.begin(9600);
  Serial.begin(9600);

  showport->init();

if(RunApps){
  OnCreate_Application(currentApp);
}

}

int iindex=0;
char incommingbyte;
const int MaxLength=100;
char message[MaxLength];


//query
  #define transfere 0x00
  #define Time 0x01
  #define Weather 0x02
//subquery
  //transfere
    #define line 0x00
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
Serial2.write(byte(query | 0x80));
Serial2.write(subquery);
}


void serialreadupdate(){
  if(Serial2.available()) {
      incommingbyte=Serial2.read();
      Log::println(Log::ESP_ARDUINO_CONNECTION_DEBUG, "incommingbyte", incommingbyte);
      if(iindex < MaxLength-1){
      message[iindex++] = incommingbyte;

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
          while(message[i]!='#'){
              mode[i-9]=message[i];
                        i++;
          }
          if(RunApps){
          switchApp(mode);
          }
          }
        if(!gleich){
          if(RunApps){
              OnDataReceive_Application(currentApp, message);
          }
        }
        isReceiving=false;
        }else{
          isReceiving=false;
        }
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
        OnRun_Application(currentApp);
      }


}

