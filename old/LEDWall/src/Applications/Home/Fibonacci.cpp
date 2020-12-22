#include <Arduino.h>
#include "Fibonacci.h"


Fibonacci::Fibonacci(){
    this->ledtisch=ledtisch;
}
void Fibonacci::init(LEDTisch* ledtisch){
    this->ledtisch=ledtisch;
    color[0].setColor(255,0,0);
    color[1].setColor(255,255,0);
    color[2].setColor(0,255,0);
    color[3].setColor(0,0,255);
    color[4].setColor(255,0,255);
    color[5].setColor(0,255,255);

}
void Fibonacci::setPosition(int x, int y){
this->x=x;
this->y=y;
}
void Fibonacci::draw(){
    ledtisch->setcolor(color[0].getRed(),color[0].getGreen(),color[0].getBlue());
    ledtisch->rect(this->x+2,this->y+4,1,1);//Field fib1 index1
    ledtisch->setcolor(color[1].getRed(),color[1].getGreen(),color[1].getBlue());
    ledtisch->rect(this->x+2,this->y+3,1,1);//Field fib1 index2
    ledtisch->setcolor(color[2].getRed(),color[2].getGreen(),color[2].getBlue());
    ledtisch->rect(this->x,this->y+3,2,2);//Field fib2   index3
    ledtisch->setcolor(color[3].getRed(),color[3].getGreen(),color[3].getBlue());
    ledtisch->rect(this->x,this->y,3,3);//Field fib3     index4
    ledtisch->setcolor(color[4].getRed(),color[4].getGreen(),color[4].getBlue());
    ledtisch->rect(this->x+3,this->y,5,5);//Field fib5   index5

    ledtisch->show();
}

bool* Fibonacci::zahl(int zahl){
    static bool e[5]={false,false,false,false,false};
    int a=0;
    for(int i=0;i<5;i++){
        if(zahl>=fi[i]+a){
            a+=fi[i];
            e[4-i]=true;
        }
    }
    return e;
}
byte Fibonacci::zahlasbyte(int zahl){
    byte e=0b00000000;
    int a=0;
    for(int i=0;i<5;i++){
        if(zahl>=fi[i]+a){
            a+=fi[i];
            SET_BIT(e,4-i);
        }
    }
return e;
}
void Fibonacci::clear(){
    for(int i=0;i<5;i++){
        color[i].setColor(0, 68, 100);
    }
}
void Fibonacci::drawZahl(bool i1, bool i2, bool i3, bool i4, bool i5){
 clear();
 if(i1){
     color[0].setColor(255,0,0);
 }
  if(i2){
     color[1].setColor(255,0,0);
 }
  if(i3){
     color[2].setColor(255,0,0);
 }
  if(i4){
     color[3].setColor(255,0,0);
 }
  if(i5){
     color[4].setColor(255,0,0);
 }

}
void Fibonacci::drawZahl(bool* zahl){
 clear();
 if(*zahl){
     color[0].setColor(255,0,0);
 }
  if(*(zahl+1)){
     color[1].setColor(255,0,0);
 }
  if(*(zahl+2)){
     color[2].setColor(255,0,0);
 }
  if(*(zahl+3)){
     color[3].setColor(255,0,0);
 }
  if(*(zahl+4)){
     color[4].setColor(255,0,0);
 }
}
void Fibonacci::drawZahl(byte zahl1, byte zahl2){
    //Serial.println(zahlasbyte(8),BIN);
    clear();
    for(int i=0;i<=4;i++){
        //Serial.println(0x01<<i,BIN);
        if((zahl1&(0x01<<i))==(0x01<<i) && (zahl2&(0x01<<i))!=(0x01<<i)){
            color[i].setColor(255,0,0);
        }else if((zahl2&(0x01<<i))==(0x01<<i) && (zahl1&(0x01<<i))!=(0x01<<i)){
            color[i].setColor(0,255,0);
        }else if((zahl1&(0x01<<i))==(0x01<<i) && (zahl2&(0x01<<i))==(0x01<<i)){
            color[i].setColor(255,255,0);
        }
        /*
        if(*(zahl1+i)){      
            color[i].setColor(255,0,0);
        }if(*(zahl2+i) ){
            color[i].setColor(0,255,0);
        }else if(*(zahl1+i) && *(zahl2+i)){
            color[i].setColor(255,255,0);
        }*/
}
}