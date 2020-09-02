#include <Arduino.h>
#include "Application.h"
#include "Applications/Licht/Licht.h"
#include "Applications/Dunkel/Dunkel.h"
#include "utils/LED-Tisch.h"

LEDTisch* ledtisch=new LEDTisch(10,15,1);

int currentApp=0;

Application* applications[10]={new Licht(), new Dunkel()};


void switchApp(int id) {

  applications[currentApp]->onStop();
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

