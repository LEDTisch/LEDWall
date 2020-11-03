#include "Block.h"
//#include "LED-Tisch.h"
//#include "LED-Tisch.h"
//LEDTisch l=LEDTisch(10,15,1);


Block::Block(int _x,int _y,int _art)
{
x=_x;
y=_y;
art=_art;

//all[1][7]=1;
   



}
/*
int Block::reihenkontrolle(){
   int k=0;
   for(int x=0;x<10;x=x+1){
   if(all[0][x]==0){
      k=1;
   }
   }

   if(k==0){
      for(int x=0;x<10;x=x+1){
         all[0][x]=0;
      }
   }

   return k;
}
*/


void Block::init(ShowPort* ShowPort){
  // l.init(_pin);
  this->showport=ShowPort;
}

void Block::show(){
   this->showport->ledtisch->show();
   
}



int Block::reihenkontrolle(){
   int k=0;
   int kk=0;
   int m=0;

   int reihenanzahl=0;
   
for(int y=0;y<15;y=y+1){
   k=0;
   if(m==1){
      y=y-1;
      m=0;
   }
   for(int x=0;x<10;x=x+1){
      if(all[y][x]==0){
         k=1;
      }
   }
//Serial.print(y);
//Serial.print(" :  ");
  // Serial.println(k);
  // Serial.println();
   if(k==0){
      Serial.println("reihe");
      reihenanzahl=reihenanzahl+1;
      for(int yy=y;yy<14;yy=yy+1){
         for(int xx=0;xx<10;xx=xx+1){
               all[yy][xx]=all[yy+1][xx];
               allfarbe[yy][xx][0]=allfarbe[yy+1][xx][0];
               allfarbe[yy][xx][1]=allfarbe[yy+1][xx][1];
               allfarbe[yy][xx][2]=allfarbe[yy+1][xx][2];

            
               
         }
      
    
   }
   
      m=1;
   kk++;

   }
   //Serial.println(y);

}

//Serial.println(kk);
//Serial.println();
//Serial.println();
/*if(k==0){
   for(int x=0;x<10;x=x+1){

   }
}*/

   return reihenanzahl;
}


int Block::getdrehung(){
   return drehung;
}



void Block::setblockto(int _x,int _y,int _art){
   x=_x;
   y=_y;
   art=_art;
}

void Block::setcolor(int r,int g,int b){
   rot=r;
   gruen=g;
   blau=b;
   this->showport->ledtisch->setcolor(rot,gruen,blau);
}


void Block::drawall(){
   for(int x=0;x<10;x=x+1){
      for(int y=0;y<15;y=y+1){
         if(all[y][x]==1){
            this->showport->ledtisch->setcolor(allfarbe[y][x][0],allfarbe[y][x][1],allfarbe[y][x][2]);
            this->showport->ledtisch->drawkoordinatensystem(x,y);
         }
      }
   }
   this->showport->ledtisch->show();
}

void Block::clearall(){
   this->showport->ledtisch->clear();
}

void Block::clearallarray(){
   for(int x=0;x<10;x++){
      for(int y=0;y<15;y++){
         all[y][x]=0;
      }
   }
}


boolean Block::drehen()
{
boolean failed=false;

   this->showport->ledtisch->setcolor(0,0,0);
   draw();
   this->showport->ledtisch->setcolor(rot,gruen,blau);
   drehung=drehung+1;
if(drehung>3){
   drehung=0;
}
if(kontrolle(8)==1){
   drehung=drehung-1;
   if(drehung < 0){
      drehung =3;
   }
   failed=true;
}
draw();

return failed;
}

void Block::down()
{
   this->showport->ledtisch->setcolor(0,0,0);
   draw();
   y=y-1;
   this->showport->ledtisch->setcolor(rot,gruen,blau);
   draw();

}

void Block::right()
{
   this->showport->ledtisch->setcolor(0,0,0);
   draw();
   x=x+1;
   this->showport->ledtisch->setcolor(rot,gruen,blau);
   draw();

}

void Block::left()
{
   this->showport->ledtisch->setcolor(0,0,0);
   draw();
   x=x-1;
   this->showport->ledtisch->setcolor(rot,gruen,blau);
   draw();

}

int Block::getblockx(){
   return x;
}
int Block::getblocky(){
   return y;
}

void Block::draw()
{
   int f1[4][2] {
  1, 0,
  1, 1,
  0, 1,
  0, 0

};
  int f2[4][2] {
  0, 1,
  1, 1,
  1, 0,
  0, 0

};
  int f3[4][2] {
  1, 0,
  1, 0,
  1, 0,
  1, 0

};
  int f4[4][2] {
  0, 0,
  1, 1,
  1, 1,
  0, 0

};
  int f5[4][2] {
  1, 0,
  1, 1,
  1, 0,
  0, 0

};
  int f6[4][2] {
  1, 0,
  1, 0,
  1, 1,
  0, 0

};

switch(drehung){
   case 0:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(xd+x,2-yd+y);
        }
        break;
      }

      }
    }

break;



   case 1:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
            this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
            this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
            this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
            this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
            this->showport->ledtisch->drawkoordinatensystem(2-yd+x,2-xd+y);        }
        break;
      }

      }
    }

break;










   case 2:
      for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(2-xd+x,yd+y);
        }
        break;
      }

      }
    }

break;














 case 3:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
          this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           this->showport->ledtisch->drawkoordinatensystem(yd+x,xd+y);
        }
        break;
      }

      }
    }

break;









}

    this->showport->ledtisch->show();
}

int kontrollpixel(int x,int y, int k){
   int koli=0;
                      if(x<=0 && k==3){
              koli=1;
           }
           if(x>=9 && k==1){
              koli=1;
           }
           if(y<=0 && k==2){
              koli=1;
           }
           return koli;
}




int Block::kontrolle(int k){
   return kw(k,0);  //wenn 0 dann kontrolle wenn 1 dann writetoall
}
int Block::writeblocktoall(){
      return kw(0,1);
}




int Block::kr(int x,int y,int k,int s,int koli){
   
              if(s==0){
            if(x<=0 && k==3){
               koli=1;
            }
            if(x>=9 && k==1){
               koli=1;
            }
            if(y<=0 && k==2){
               koli=1;
            }
            if(y>=15 && k==4){
               koli=1;
            }
            
            if(all[y-1][x]==1 && k==5){
               koli=1;
                                         
            }
            if(all[y][x+1]==1 && k==6){
               koli=1;
                                         
            }
            if(all[y][x-1]==1 && k==7){
               koli=1;
                                         
            }

            if(all[y][x]==1 && k==8){
               koli=1;
            }
            if(x<0 && k==8){
               koli=1;
            }
            if(x>9 && k==8){
               koli=1;
            }



           }
           if(s==1){
              if(x>=0 && x<10 && y>=0 && y<15){
              all[y][x]=1;
              allfarbe[y][x][0]=rot;
              allfarbe[y][x][1]=gruen;
              allfarbe[y][x][2]=blau;
              }
              if(y>=15){
                 koli=10;
              }
           }
           return koli;
}


int Block::kw(int k,int s){
   int koli=0;
   int f1[4][2] {
  1, 0,
  1, 1,
  0, 1,
  0, 0

};
  int f2[4][2] {
  0, 1,
  1, 1,
  1, 0,
  0, 0

};
  int f3[4][2] {
  1, 0,
  1, 0,
  1, 0,
  1, 0

};
  int f4[4][2] {
  0, 0,
  1, 1,
  1, 1,
  0, 0

};
  int f5[4][2] {
  1, 0,
  1, 1,
  1, 0,
  0, 0

};
  int f6[4][2] {
  1, 0,
  1, 0,
  1, 1,
  0, 0

};

switch(drehung){
   case 0:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           //l.drawkoordinatensystem(xd+x,2-yd+y);
            koli=kr(xd+x,2-yd+y,k,s,koli);
        }
        break;
      }

      }
    }

break;



   case 1:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           //l.drawkoordinatensystem(2-yd+x,2-xd+y);
           koli=kr(2-yd+x,2-xd+y,k,s,koli);

        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
            //l.drawkoordinatensystem(2-yd+x,2-xd+y); 
            koli=kr(2-yd+x,2-xd+y,k,s,koli);     
            }
        break;
                  case 3:
        if(f3[yd][xd]==1){
            //l.drawkoordinatensystem(2-yd+x,2-xd+y);  
            koli=kr(2-yd+x,2-xd+y,k,s,koli);
           }    
        break;
                  case 4:
        if(f4[yd][xd]==1){
            //l.drawkoordinatensystem(2-yd+x,2-xd+y);    
             koli=kr(2-yd+x,2-xd+y,k,s,koli);  
            }
        break;
                  case 5:
        if(f5[yd][xd]==1){
            //l.drawkoordinatensystem(2-yd+x,2-xd+y);    
            koli=kr(2-yd+x,2-xd+y,k,s,koli);    
            }
        break;
                  case 6:
        if(f6[yd][xd]==1){
            //l.drawkoordinatensystem(2-yd+x,2-xd+y);   
            koli=kr(2-yd+x,2-xd+y,k,s,koli);    
            }
        break;
      }

      }
    }

break;










   case 2:
      for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);  
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           //l.drawkoordinatensystem(2-xd+x,yd+y);
            koli=kr(2-xd+x,yd+y,k,s,koli);
        }
        break;
      }

      }
    }

break;














 case 3:
       for(int xd=0;xd<2;xd=xd+1){
      for(int yd=0;yd<4;yd=yd+1){
        switch(art){
           case 1:
        if(f1[yd][xd]==1){
           //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
                  case 2:
        if(f2[yd][xd]==1){
           //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
                  case 3:
        if(f3[yd][xd]==1){
           //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
                  case 4:
        if(f4[yd][xd]==1){
          //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
                  case 5:
        if(f5[yd][xd]==1){
           //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
                  case 6:
        if(f6[yd][xd]==1){
           //l.drawkoordinatensystem(yd+x,xd+y);
            koli=kr(yd+x,xd+y,k,s,koli);
        }
        break;
      }

      }
    }

break;









}

 return koli;   
}
