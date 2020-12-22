#include <Arduino.h>
class Color{
    public:
        Color(int r, int g, int b){
            this->r=r;
            this->g=g;
            this->b=b;
        }
        Color(){};
        void setColor(int r, int g, int b){
            this->r=r;
            this->g=g;
            this->b=b;
        }  
        void setRed(int r){
            this->r=r;
        } 
        void setGreen(int g){
            this->g=g;
        }
        void setBlue(int b){
            this->b=b;
        }
        int getRed(){
            return r;
        }
        int getGreen(){
            return g;
        }
        int getBlue(){
            return b;
        }
        private:
        int r=0;
        int g=0;
        int b=0;

};