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
    bool e[5]={false,false,false,false,false};
    int a=0;
    Serial.println();
    for(int i=0;i<5;i++){
        if(zahl>=fi[i]+a){
            a+=fi[i];
            Serial.print(4-i);
            Serial.print(" ");
            e[4-i]=true;
        }
    }
    return e;
}