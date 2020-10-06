#include <Arduino.h>
#ifndef Log_H
#define Log_H
class Log{
public:

static void println(bool active, String g, const String &s){
    if(active){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
    }
};
static void println(bool active, String g, int s){
        if(active){

  Serial.print("["+g+"]"+" ");
  Serial.println(s);
  }
};
static void println(bool active, String g, float s){
        if(active){

  Serial.print("["+g+"]"+" ");
  Serial.println(s);
  }
};
static void println(bool active, String g, byte s){
        if(active){

  Serial.print("["+g+"]"+" ");
  Serial.println(s);
  }
};
static void println(bool active, String g, long s){
        if(active){

  Serial.print("["+g+"]"+" ");
  Serial.println(s);
  }
};

static void println(bool active, String g, char s){
        if(active){

  Serial.println("["+g+"]"+" ");
  Serial.print(s);
        }
};






private:


};
#endif