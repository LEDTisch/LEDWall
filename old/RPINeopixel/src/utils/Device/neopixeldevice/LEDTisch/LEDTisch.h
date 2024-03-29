//
// Created by tim on 05.12.20.
//

#ifndef RPINEOPIXEL_LEDTISCH_H
#define RPINEOPIXEL_LEDTISCH_H


#include <cstdint>
#include <jmorecfg.h>
#include "../neopixelcontroll/Neopixel.cpp"

class LEDTisch {
private:
    int xmax;
    int ymax;
    uint8_t drehung;
    uint8_t rot;
    uint8_t gruen;
    uint8_t blau;
    float brightness=1;

    Neopixel pixels =  Neopixel(150);

public:
    LEDTisch(int _x,int _y,int _d);//da
    int koordinatensystem(int x,int y);//da
    int drawkoordinatensystem(int x,int y);//da
    int drawkoordinatensystem(int x,int y,uint32_t color);
    void zahl(int x_zahl, int y_zahl, int zahl);
    void show();//da
    void drehung_set(int d);//da
  //  void buchstaben(int x_wort, int y_wort, char wort[], boolean s, int grenzl, int grenzr, long takt);
    void setcolor(int r,int g,int b);//da
    void clear();//da
    void kreis(int x,int y,int r,int u);
    void rect(int x,int y,int w,int h);
    void setRotation(int r);
    void init();//da
    void gerade(float x1,float y1,float x2,float y2,float g);
    void drawImage(uint32_t image[],int w,int h);
    void setBrightness(float brightness);

};


#endif //RPINEOPIXEL_LEDTISCH_H
