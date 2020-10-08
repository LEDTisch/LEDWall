#include <Arduino.h>
#ifndef Log_H
#define Log_H
class Log{
public:

enum DEBUG_LEVEL{APPLICATION_MANAGE_INFO=true, ESP_ARDUINO_CONNECTION_INFO=true, ESP_ARDUINO_CONNECTION_DEBUG=true, ESP_ARDUINO_CONNECTION_ERROR=true, SNAKE_GAME_DEBUG=false};

static void println(bool active, String g, const String &s){
    if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s);
    }
};
static void println(bool active, String g, int s){
        if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s);
  }
};
static void println(bool active, String g, float s){
        if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s);
  }
};
static void println(bool active, String g, byte s){
        if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s,BIN);
  }
};
static void println(bool active, String g, long s){
        if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s);
  }
};

static void println(bool active, String g, char s){
        if(active){
      Serial.println();
  Serial.print("["+g+"]"+" ");
  Serial.print(s,BIN);
        }
};

static void print(bool active, String s){
  if(active){
    Serial.print(s);
  }
}






private:


};
#endif