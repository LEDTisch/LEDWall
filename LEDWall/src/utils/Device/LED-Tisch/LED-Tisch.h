#include "Arduino.h"



#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
#include <avr/power.h>
#endif
#define PIN 9 // Hier wird angegeben, an welchem digitalen Pin die WS2812 LEDs bzw. NeoPixel angeschlossen sind
#define NUMPIXELS 150 // Hier wird die Anzahl der angeschlossenen WS2812 LEDs bzw. NeoPixel angegeben

#ifndef LED-Tisch_H
#define LED-Tisch_H

class LEDTisch{
   private:
      int xmax;
      int ymax;
      int drehung;
      int rot;
      int gruen;
      int blau;
      int pin;
 

Adafruit_NeoPixel pixels = Adafruit_NeoPixel(NUMPIXELS, PIN, NEO_GRB + NEO_KHZ800);
  
   public:
      LEDTisch(int _x,int _y,int _d);
      int koordinatensystem(int x,int y); 
      int drawkoordinatensystem(int x,int y); 
      int drawkoordinatensystem(int x,int y,uint32_t color);
      void zahl(int x_zahl, int y_zahl, int zahl);
      void show();
      void drehung_set(int d);
      void buchstaben(int x_wort, int y_wort, String wort, boolean s, int grenzl, int grenzr, long takt);
      void setcolor(int r,int g,int b);
      void clear();
      void kreis(int x,int y,int r,int u);
      void rect(int x,int y,int w,int h);
      void setRotation(int r);
      void init(int _pin);
      void gerade(float x1,float y1,float x2,float y2,float g);
      void drawImage(uint32_t image[],int w,int h);
      int brightness;
    
    
};
#endif