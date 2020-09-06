#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Home/Home.h"
#include "utils/LED-Tisch.h"
#include "Applications/Snake/Snake.h"

LEDTisch* ledtisch=new LEDTisch(10,15,1);

int currentApp=0;
const int size=3;
int appanzahl=size;
Application* applications[size]={new Licht(), new Home(), new Snake()};


void switchApp(int id) {
  applications[currentApp]->onStop(ledtisch);
  currentApp = id;
  applications[currentApp]->onCreate(ledtisch);
}
void switchApp(String s) {
  int id=0;
  for(int i=0;i<appanzahl;i++){
    if(s==applications[i]->getName()){
      id=i;
    }
  }
  applications[currentApp]->onStop(ledtisch);
  currentApp = id;
  applications[currentApp]->onCreate(ledtisch);
}



void setup(){
  Serial2.begin(9600);
  Serial.begin(9600);
  ledtisch->init(10);
  ledtisch->setcolor(20,20,0);
  ledtisch->clear();


applications[currentApp]->onCreate(ledtisch);


}

int iindex=0;
char incommingbyte;
const int MaxLength=100;
char message[MaxLength];



void sentRequest(){
Serial2.write(0x0F);
}


void serialreadupdate(){
  if(Serial2.available()) {
      incommingbyte=Serial2.read();
      //Serial.println(incommingbyte,BIN);
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
        Serial.println();
        Serial.println(message);
        char vergleich[9]= "switchTo:";
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
          switchApp(mode);
            sentRequest();

          Serial.println(mode);
          }
        if(!gleich){
        applications[currentApp]->onDataReceive(message, ledtisch);
        }


        }

  }
}




void loop(){
  // if(Serial2.available()) {
//Serial.write(Serial2.read());
 //  }
  serialreadupdate();
      applications[currentApp]->onRun(ledtisch);


}

