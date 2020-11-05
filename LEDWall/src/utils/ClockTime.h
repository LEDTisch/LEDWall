#ifndef ClockTime_H
#define ClockTime_H

class ClockTime{

public:
    ClockTime(){}
    void setTime(int hour, int minute, int second){
        this->hour=hour;
        this->minute=minute;
        this->second=second;

    }
    void tick(){
        second++;
        if(second>59){
            second=0;
            minute+=1;
        }
        if(minute>59){
            minute=0;
            hour+=1;
        }
        if(hour>12){
            hour=1;
        }
    }
    void setHour(int hour){
        this->hour=hour;
    }
    void setMinute(int minute){
        this->minute=minute;
    }
    void setSecond(int second){
        this->second=second;
    }
    int getHour(){
        return hour;
    }
    int getMinute(){
        return minute;
    }
    int getSecond(){
        return second;
    }
private:
    int hour;
    int minute;
    int second;
};
#endif