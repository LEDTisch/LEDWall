#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
#include <queue>

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
int iindex=0;
char incommingbyte;
const int MaxLength=100;
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


void sentAppData(){
                  Serial.print("appdataanfrageerhalten incommingbuffersize: ");
                  Serial.print(ReceiveData.size());
                  if(!ReceiveData.empty()){
                    String s=ReceiveData.front();
                     softwareserial.print(s);
                     Serial.print(" (beantwortet)");
                     Serial.print(" mit: ");
                     Serial.print(s);
                     Serial.println();
                     ReceiveData.pop();

                  }else{
                    softwareserial.println("empty");
                    Serial.print(" (empty arduino verschwendet Zeit)");
                    Serial.println();
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
    case 0x00:{
      switch(subquery){
        case 0x00:{
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
        if((ib & 0x80)==1){
          query=ib & 0x7F;
        }else{
          subquery=ib;
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
        for(int i=0;i<iindex;i++){

        }
        iindex=0;
        //Serial.println(message);
        
        //Verarbeitung///////////////////////////

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
