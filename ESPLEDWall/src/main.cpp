#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
SoftwareSerial softwareserial(13,15,false);
 
#define SendKey 0  //Button to send data Flash BTN on NodeMCU
 
int port = 8888;  //Port number
WiFiServer server(port);
 
const char *ssid = "lap";  //Enter your wifi SSID
const char *password = "##Pilatus.b4##pi!?";  //Enter your wifi Password
 
int chanchepin=12;
bool chanchstatus=false;
void setup() 
{
  pinMode(chanchepin, OUTPUT);
  digitalWrite(chanchepin, LOW);
  softwareserial.begin(9600);


  Serial.begin(9600);
  Serial.println();
 
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
int iindex=0;
char incommingbyte;
const int MaxLength=100;
char message[MaxLength];


void loop() 
{
  
  WiFiClient client = server.available();
  
  if (client) {
    if(client.connected())
    {
      Serial.println("Client Connected");
    }                 
  

    while(client.connected()){     
 
      while(client.available()>0){





//softwareserial.write(client.read());
      incommingbyte=client.read();
      //Serial.println(incommingbyte,BIN);
      if(iindex < MaxLength-1){
      message[iindex++] = incommingbyte;

      }else{
        Serial.println("Error: BufferOverflow");
      }
      if(incommingbyte == '\n'){
        for(int i=0;i<iindex;i++){

        }
        iindex=0;
        //Serial.println(message);
        
        //Verarbeitung///////////////////////////
        Serial.println();
        Serial.println(message);
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

          if(chanchstatus){
          digitalWrite(chanchepin, HIGH);
          chanchstatus=false;
          }else{
          digitalWrite(chanchepin, LOW);
          chanchstatus=true;
          }
          softwareserial.println(message); 
          Serial.println(mode);
          }
        if(!gleich){
        softwareserial.println(message); 
                }
                          for (int j=0; j<MaxLength; ++j) {message[j] = 0; }

        }








        // read data from the connected client

                //Serial.write(t); 

      }

    }
    client.stop();
    Serial.println("Client disconnected");    
  }
}