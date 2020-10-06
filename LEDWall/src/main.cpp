#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Home/Home.h"
#include "utils//Device/LED-Tisch/LED-Tisch.h"
#include "Applications/Snake/Snake.h"
#include "utils/Log.h"


#include "utils/ShowPort.h"
ShowPort* showport=new ShowPort();




#define RunApps false



int DataAvailablePin=8;
bool dataavailablechanche=false;

int currentApp=0;
const int size=3;
int appanzahl=size;
Application* applications[size]={new Home(),new Licht(),  new Snake()};

bool isReceiving=false;





void switchApp(int id) {
  applications[currentApp]->onStop(showport);
  currentApp = id;
  applications[currentApp]->onCreate(showport);
}
void switchApp(String s) {
  int id=0;
  for(int i=0;i<appanzahl;i++){
    if(s==applications[i]->getName()){
      id=i;
    }
  }
  applications[currentApp]->onStop(showport);
  currentApp = id;
  applications[currentApp]->onCreate(showport);
}



void setup(){
  pinMode(DataAvailablePin, INPUT);
  Serial2.begin(9600);
  Serial.begin(9600);

  showport->init();

if(RunApps){
applications[currentApp]->onCreate(showport);
}

}

int iindex=0;
char incommingbyte;
const int MaxLength=100;
char message[MaxLength];



void sentRequest(){
  Log::println("info","sentRequest");
Serial2.write(0x10);
}


void serialreadupdate(){
  if(Serial2.available()) {
      incommingbyte=Serial2.read();
      //Serial.println(incommingbyte);
      if(iindex < MaxLength-1){
      message[iindex++] = incommingbyte;

      }else{
        Serial.println("Error: BufferOverflow");
      }
      if(incommingbyte == '\n'){
        for(int i=0;i<iindex;i++){
          if(message[i]=='\n'){
            message[i]='\0';
          }
        }
        iindex=0;
        //Serial.println(message);
        
        //Verarbeitung///////////////////////////
        //Serial.println("-------------------------------------------------------------");
        //Serial.print("empfangen: ");
        //Serial.println(message);
        if(true){
        //Serial.println();
        //Serial.print("verarbeiten: ");
       // Serial.println(message);
       Log::println("message",message);
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
          for (int j=0; j<30; ++j) {mode[j] = 0; }
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
             applications[currentApp]->onDataReceive(message, showport);
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
  // if(Serial2.available()) {
//Serial.write(Serial2.read());
 //  }
      if(digitalRead(DataAvailablePin)){
      sentRequest();
      isReceiving=true;
      }
      while(isReceiving || Serial.available()){
          serialreadupdate();
      }

      if(RunApps){
        applications[currentApp]->onRun(showport);
      }


}

