//
// Created by tim on 05.12.20.
//

#ifndef RPINEOPIXEL_LEDTISCH_H
#define RPINEOPIXEL_LEDTISCH_H


#include <cstdint>
#include <jmorecfg.h>
#include "../../neopixelcontroll/Neopixel.h"

class LEDTisch {
private:
    int xmax;
    int ymax;
    uint8_t drehung;
    uint8_t rot;
    uint8_t gruen;
    uint8_t blau;
    int pin;
    float brightness=1;

    Neopixel pixels =  Neopixel(150);

public:
    LEDTisch(int _x,int _y,int _d);
    int koordinatensystem(int x,int y);
    int drawkoordinatensystem(int x,int y);
    int drawkoordinatensystem(int x,int y,uint32_t color);
    void zahl(int x_zahl, int y_zahl, int zahl);
    void show();
    void drehung_set(int d);
  //  void buchstaben(int x_wort, int y_wort, char wort[], boolean s, int grenzl, int grenzr, long takt);
    void setcolor(int r,int g,int b);
    void clear();
    void kreis(int x,int y,int r,int u);
    void rect(int x,int y,int w,int h);
    void setRotation(int r);
    void init();
    void gerade(float x1,float y1,float x2,float y2,float g);
    void drawImage(uint32_t image[],int w,int h);
    void setBrightness(float brightness);

};


#endif //RPINEOPIXEL_LEDTISCH_H
