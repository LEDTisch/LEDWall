#include "Arduino.h"

class Player{
    public:
        Player(byte XGridPosition, byte YGridPosition){
            this->XGridPosition=XGridPosition;
            this->YGridPosition=YGridPosition;
        }
    private:
        byte XGridPosition=0;
        byte YGridPosition=0;

};