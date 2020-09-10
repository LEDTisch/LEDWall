#include <Arduino.h>
#include "Matrix.h"
#include "matrixsfont.h"

Matrix::Matrix(int _datain_pin,int _clk_pin,int _load_pin)
{


datain_pin=_datain_pin;
clk_pin=_clk_pin;
load_pin=_load_pin;

//lc=LedControl(datain_pin,clk_pin,load_pin,4);
}

void Matrix::init(int brightness){
    lc.shutdown(0,false);
  lc.shutdown(1,false);
  lc.shutdown(2,false);
  lc.shutdown(3,false);
  /* Set the brightness to a medium values */
 
  lc.setIntensity(0,brightness);
  lc.setIntensity(1,brightness);
  lc.setIntensity(2,brightness);
  lc.setIntensity(3,brightness);
  /* and clear the display */
  lc.clearDisplay(0);
  lc.clearDisplay(1);
  lc.clearDisplay(2);
  lc.clearDisplay(3);
}


void Matrix::setkcolumn(int c,byte b){
  if(c < 8){
    lc.setColumn(0,c,b);
  }
  if(c >7 && c < 16){
    Serial.println("löjkö");
    lc.setColumn(1,c-8,b);
  }
    if(c >15 && c < 24){
    Serial.println("löjkö");
    lc.setColumn(2,c-16,b);
  }
      if(c >23 && c < 32){
    Serial.println("löjkö");
    lc.setColumn(3,c-24,b);
  }
}


void Matrix::zahl(int zahl,int x,int a){
  
  if(zahl < 10){

   setkcolumn(0+x,zahlen[zahl][3]);
   setkcolumn(1+x,zahlen[zahl][2]);
   setkcolumn(2+x,zahlen[zahl][1]);
   setkcolumn(3+x,zahlen[zahl][0]);

  }
  if(zahl > 9  && zahl < 100){
    
    int z1=zahl-(zahl/10)*10;
    int z2=(zahl/10);

   setkcolumn(0+x,zahlen[z1][3]);
   setkcolumn(1+x,zahlen[z1][2]);
   setkcolumn(2+x,zahlen[z1][1]);
   setkcolumn(3+x,zahlen[z1][0]);


   setkcolumn(0+x+4+a,zahlen[z2][3]);
   setkcolumn(1+x+4+a,zahlen[z2][2]);
   setkcolumn(2+x+4+a,zahlen[z2][1]);
   setkcolumn(3+x+4+a,zahlen[z2][0]);
  }



  if(zahl > 99 && zahl < 1000){
    
    int z1=zahl-(zahl/10)*10;
    int z2=(((zahl-(zahl/100)*100)-z1)/10);
    int z3= zahl/100;
  
  
   setkcolumn(0+x,zahlen[z1][3]);
   setkcolumn(1+x,zahlen[z1][2]);
   setkcolumn(2+x,zahlen[z1][1]);
   setkcolumn(3+x,zahlen[z1][0]);


   setkcolumn(0+x+4+a,zahlen[z2][3]);
   setkcolumn(1+x+4+a,zahlen[z2][2]);
   setkcolumn(2+x+4+a,zahlen[z2][1]);
   setkcolumn(3+x+4+a,zahlen[z2][0]);

   
   setkcolumn(0+x+8+a*2,zahlen[z3][3]);
   setkcolumn(1+x+8+a*2,zahlen[z3][2]);
   setkcolumn(2+x+8+a*2,zahlen[z3][1]);
   setkcolumn(3+x+8+a*2,zahlen[z3][0]);
  }

  if(zahl > 999){
       int z4=zahl / 1000;
       int z3=(zahl - z4*1000) / 100 ;
       int z2=(zahl - z4*1000 - z3*100) / 10;
      int z1=(zahl - z4*1000 -z3*100 - z2*10);
      
      
     

Serial.println(z1);
   setkcolumn(0+x,zahlen[z1][3]);
   setkcolumn(1+x,zahlen[z1][2]);
   setkcolumn(2+x,zahlen[z1][1]);
   setkcolumn(3+x,zahlen[z1][0]);


   setkcolumn(0+x+4+a,zahlen[z2][3]);
   setkcolumn(1+x+4+a,zahlen[z2][2]);
   setkcolumn(2+x+4+a,zahlen[z2][1]);
   setkcolumn(3+x+4+a,zahlen[z2][0]);

   
   setkcolumn(0+x+8+a*2,zahlen[z3][3]);
   setkcolumn(1+x+8+a*2,zahlen[z3][2]);
   setkcolumn(2+x+8+a*2,zahlen[z3][1]);
   setkcolumn(3+x+8+a*2,zahlen[z3][0]);

   setkcolumn(0+x+12+a*3,zahlen[z4][3]);
   setkcolumn(1+x+12+a*3,zahlen[z4][2]);
   setkcolumn(2+x+12+a*3,zahlen[z4][1]);
   setkcolumn(3+x+12+a*3,zahlen[z4][0]);


  }

  
  
}
