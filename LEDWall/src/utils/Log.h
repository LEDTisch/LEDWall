#include <Arduino.h>
#ifndef Log_H
#define Log_H
class Log{
public:
enum LEVEL{DEBUG, INFO, ERROR};
static void println(String g, const String &s){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
};
static void println(String g, int s){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
};
static void println(String g, float s){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
};
static void println(String g, byte s){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
};
static void print(String g, long s){
  Serial.print("["+g+"]"+" ");
  Serial.println(s);
};

static void print(String g, char s){
  Serial.println("["+g+"]"+" ");
  Serial.print(s);
};






private:


};
#endif