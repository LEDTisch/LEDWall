#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
#include <queue>
#include "Log.h"

SoftwareSerial softwareserial(13,15,false);
 
 std::queue<String> ReceiveData;


#define SendKey 0  //Button to send data Flash BTN on NodeMCU
 
int port = 8888;  //Port number
WiFiServer server(port);
 
const char *ssid = "lap";  //Enter your wifi SSID
const char *password = "##Pilatus.b4##pi!?";  //Enter your wifi Password
 
int DataAvailiblePin=12;



// Set your Static IP address
IPAddress local_IP(192, 168, 137, 111);
// Set your Gateway IP address
IPAddress gateway(192, 168, 137, 1);

IPAddress subnet(255, 255, 0, 0);
IPAddress primaryDNS(8, 8, 8, 8);   //optional
IPAddress secondaryDNS(8, 8, 4, 4); //optional

void setup() 
{



  pinMode(DataAvailiblePin, OUTPUT);
  digitalWrite(DataAvailiblePin, LOW);

  softwareserial.begin(9600);


  Serial.begin(9600);
  Serial.println();


    // Configures static IP address
  if (!WiFi.config(local_IP, gateway, subnet, primaryDNS, secondaryDNS)) {
    Serial.println("STA Failed to configure");
  }
 
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password); //Connect to wifi
 
  // Wait for connection  
  Serial.println("Connecting to Wifi");
  while (WiFi.status() != WL_CONNECTED) {   
    delay(500);
    Serial.print(".");
    delay(500);
  }
 
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
 
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());  
  server.begin();
  Serial.print("Open Telnet and connect to IP:");
  Serial.print(WiFi.localIP());
  Serial.print(" on port ");
  Serial.println(port);






}
int iindex=2;
char incommingbyte;
const int MaxLength=100+2;
char message[MaxLength];

WiFiClient client;


void connectClient(){
  client = server.available();
  
  if (client) {
    if(client.connected())
    {
      Serial.println("Client Connected");
    }   
}
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

void sentAppData(){
                  Log::println(Log::ARDUINO_INFO,"Info","appdataanfrageerhalten incommingbuffersize: ");
                  Log::print(Log::ARDUINO_INFO,ReceiveData.size());
                  if(!ReceiveData.empty()){
                    String s=ReceiveData.front();
                     softwareserial.print(s);
                     Log::print(Log::ARDUINO_INFO," (beantwortet)");
                     Log::print(Log::ARDUINO_INFO," mit: ");
                     Log::print(Log::ARDUINO_INFO,s);
                     ReceiveData.pop();

                  }else{
                    softwareserial.println("empty");
                    Log::print(Log::ARDUINO_INFO," (empty arduino verschwendet Zeit)");
                  }
                       if(ReceiveData.empty()){
                        digitalWrite(DataAvailiblePin, LOW);
                        }else{
                          digitalWrite(DataAvailiblePin, HIGH);
                        }
}
        byte query=0x00;
        byte subquery=0x00;
void ProcessRequest(byte query, byte subquery){
  switch(query){
    case transfere:{
      switch(subquery){
        case line:{
            sentAppData();
          break;
        }
      }
      break;
    }
  }
}
void checkforRequest(){
      if(softwareserial.available()){
        byte ib=softwareserial.read();
        if((ib & 0x80)==0){
          query=ib;
        }else{
          subquery=ib & 0x7F;

          ProcessRequest(query, subquery);
        }
      }
}


void loop() 
{

    checkforRequest();


    if(!client.connected()){
    client.stop();
    Serial.println("Client disconnected");  
    connectClient();  
    }else{     
 
      if(client.available()>0){





//softwareserial.write(client.read());
      incommingbyte=client.read();
      //Serial.println(incommingbyte,BIN);
      if(iindex < MaxLength-1){
      message[iindex++] = incommingbyte;

      }else{
        Serial.println("Error: BufferOverflow");
      }
      if(incommingbyte == '\n'){

        iindex=2;
        //Serial.println(message);
        
        //Verarbeitung///////////////////////////
message[0]=0x01;
message[1]=0x01;
        ReceiveData.push(message);
                          if(ReceiveData.empty()){
                        digitalWrite(DataAvailiblePin, LOW);
                        }else{
                          digitalWrite(DataAvailiblePin, HIGH);
                        }
        for (int j=0; j<MaxLength; ++j) {message[j] = 0; }////message clearen

        }








        // read data from the connected client

                //Serial.write(t); 

      }

    }

    
  
  }
