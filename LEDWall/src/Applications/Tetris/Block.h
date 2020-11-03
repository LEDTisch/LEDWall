#include "Arduino.h"
#include "../../utils/ShowPort.h"


class Block
{
   private:
      int x;
      int y;
      int art;
      int drehung=0;
      int rot;
      int gruen;
      int blau;


 
  
   public:


      ShowPort* showport;
      Block(int _x,int _y, int _art);
      int getdrehung();
      void draw();
      int kontrolle(int k);
      void down();
      void right();
      void left();
      boolean drehen();
      void setcolor(int r,int g,int b);
      void setblockto(int _x,int _y,int _art);
      int writeblocktoall();
      int kw(int k,int s);
      void drawall();
      void clearall();
      void clearallarray();
      int kr(int x,int y,int k,int s,int koli);
      int reihenkontrolle();
      void show();
      int getblockx();
      int getblocky();
      void init(ShowPort* ShowPort);
    
     int all[15][10];
     int allfarbe[15][10][3];
      
 
       

};