#include "Arduino.h"
 #include "LED-Tisch.h"
#include "font.h"

LEDTisch::LEDTisch(int _x,int _y,int _d)
{
xmax=_x;
ymax=_y;
drehung=_d;


}

void LEDTisch::drawImage(uint32_t image[],int w,int h){
  int i=0;
  for(int wy=0;wy<h;wy++){
for(int wx=0;wx<w;wx++){
  drawkoordinatensystem(wx,wy,image[i]);
  i++;
}
}
pixels.show();
Serial.print("image gedrawec");
}



void LEDTisch::gerade(float x1,float y1,float x2,float y2,float g){
x2++;
y2++;
  
  float m=0;

  m=(y2-y1)/(x2-x1);
  Serial.println(m);

  float x=0;
while(x<x2-x1){
drawkoordinatensystem(x+x1,m*x+y1);
x=x+g;
}
pixels.show();

}


void LEDTisch::brightness(int brightness){
  pixels.setBrightness(brightness);
}

void LEDTisch::init(int _pin){
  pin=_pin;
  Serial.println("pin:");
pixels.setPin(_pin);
Serial.println(pixels.getPin());
}

void LEDTisch::drehung_set(int d){
drehung=d;
}

void LEDTisch::setcolor(int r,int g, int b){
  if(r<0){r=0;}
  if(g<0){g=0;}
  if(b<0){b=0;}
    rot=r;
    gruen=g;
    blau=b;
}



void LEDTisch::zahl(int x_zahl, int y_zahl, int zahl){
  
int counter_x;
int counter_y;
int erste_stelle;
int zweite_stelle;
int PO;


  if (zahl >= 10) {
    erste_stelle = zahl / 10;
    zweite_stelle = zahl - erste_stelle * 10;
  }
  if (zahl < 10) {
    erste_stelle = zahl;
  }
  //Serial.println(zweite_stelle);

  counter_y = 0;
  while (counter_y < 3) {
    while (counter_x < 5) {
      //Serial.println(counter_x);

      PO=koordinatensystem(counter_x + x_zahl,counter_y+y_zahl);

      switch (erste_stelle) {
        case 0:
          if (nul[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 1:
          if (eins[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 2:
          if (zwei[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 3:
          if (drei[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 4:
          if (vier[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 5:
          if (funf[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 6:
          if (sechs[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 7:
          if (sieben[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 8:
          if (acht[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;
        case 9:
          if (neun[counter_x][counter_y] == 1) {
            pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

          }
          break;


      }

      counter_x++;
    }
    counter_x = 0;
    counter_y++;
  }//




  if (zahl >= 10) {
    counter_y = 0;
    while (counter_y < 3) {
      while (counter_x < 5) {
        //Serial.println(counter_x);

        PO=koordinatensystem(counter_x + x_zahl,counter_y + y_zahl + 4);

        switch (zweite_stelle) {
          case 0:
            if (nul[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 1:
            if (eins[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 2:
            if (zwei[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 3:
            if (drei[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 4:
            if (vier[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 5:
            if (funf[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 6:
            if (sechs[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 7:
            if (sieben[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 8:
            if (acht[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;
          case 9:
            if (neun[counter_x][counter_y] == 1) {
              pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

            }
            break;


        }

        counter_x++;
      }
      counter_x = 0;
      counter_y++;
    }//
  }



  //pixels.show();

}


void LEDTisch::show(){
  pixels.begin();
  pixels.show();
}





//Buchstaben start
int buchstaben[5][3][26];


//yuheeeeeeeeeeeeeeeeeeeee

int testt = 'a';




unsigned long millis_s = millis();
int scroll;
void LEDTisch::buchstaben(int x_wort, int y_wort, String wort, boolean s, int grenzl, int grenzr, long takt) {
  pixels.begin();
  //Serial.begin(9600);
  int PO;
  int counter_x;
int counter_y;
int x;
int y;
  //Serial.println(testt-97);
  //Serial.println(wort.length());
  int zeichenabstand = 0;
  int zeichen_counter = 0;
  int word_l = wort.length() * 4;
  if (s == true) {
    if (millis() > millis_s + takt) {


      int losch_1 = grenzl;
      int losch_2 = x_wort;
      while (losch_2   < 5 + x_wort) {
        losch_1 = grenzl;
        while (losch_1  < grenzr + grenzl) {
          y = losch_1;
          x = losch_2;
          PO=koordinatensystem(x,y);
          pixels.setPixelColor(PO, pixels.Color(0, 0, 0));
          losch_1++;
        }
        losch_2++;
      }



      scroll = scroll - 1 ;
      if (scroll < word_l * -1) {
        scroll = 0;
      }
      millis_s = millis();
    }
    
  }


  //int z_length = sizeof(s_gros)/5/2;
  while (zeichen_counter < (int)wort.length()) {
//Serial.println(y_wort + zeichenabstand + scroll);
    //if(zeichenabstand + scroll  < grenzl){
    
    counter_y = 0;
    while (counter_y < 3) {
      counter_x = 0;
      while (counter_x < 5) {
        x = counter_x + x_wort;
        y = counter_y + y_wort + zeichenabstand + scroll;
        if (y >= grenzl && y <= grenzr) {
                  //Serial.println(x);
        //Serial.println(y);
          PO=koordinatensystem(x,y);
          //Serial.println(wort[zeichen_counter]-97);
          switch (wort[zeichen_counter]-97) {
            case 0:
              if (a_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;

            case 1:
              if (b_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 2:
              if (c_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 3:
              if (d_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 4:
              if (e_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 5:
              if (f_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 6:
              if (g_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 7:
              if (h_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 8:
              if (i_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 9:
              if (j_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 10:
              if (k_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 11:
              if (l_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 12:
              if (m_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 13:
              if (n_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 14:
              if (o_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 15:
              if (p_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 16:
              if (q_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 17:
              if (r_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 18:
              if (s_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 19:
              if (t_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 20:
              if (u_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 21:
              if (v_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 22:
              if (w_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 23:
              if (x_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 24:
              if (y_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;
            case 25:
              if (z_gros[counter_x][counter_y] == 1) {
                pixels.setPixelColor(PO, pixels.Color(rot,gruen,blau));

              }
              break;



          }
        }


        counter_x++;
      }
      counter_y++;
    }//
    pixels.show();
    zeichen_counter = zeichen_counter + 1;
    zeichenabstand = zeichenabstand + 4;
  }
}


void LEDTisch::kreis(int x,int y,int r,int u){
for(int w=0;w<360;w=w+u){
   drawkoordinatensystem(x+round(cos(w*3.1415/180)*r),y+round(sin(w*3.1415/180)*r));
}
}

void LEDTisch::rect(int x,int y,int w,int h){
  for(int _w=0;_w<w;_w=_w+1){
    for(int _h=0;_h<h;_h=_h+1){
      drawkoordinatensystem(_w+x,_h+y);
    }
  }
}

void LEDTisch::setRotation(int r){
drehung=r;
}

int LEDTisch::koordinatensystem(int x,int y)
{
  long PO = -1;
if(x>=0 && x<xmax && y>=0 && y<ymax){
pixels.begin();

long led = 0;

  //y=9-y+1;
  //x = 0;
  //y = 5;
 // PO = ((ymax-1)-y) + 15 * ((xmax-1)-x);
 if(drehung==0){
PO= x + ymax * (xmax - y);
 }
 if(drehung==1){
   PO = ((ymax-1)-y) + ymax * ((xmax-1)-x);
 }
  led = PO;



  if (led >= ymax && led < ymax*2)
  {
    led = (ymax*2+1) - (led - ymax);
    led = led - 2;
  }

  if (led >= ymax*3 && led < ymax*4)
  {
    led = (ymax*6+1 - (led - ymax));
    led = led - 2;
  }

  if (led >= ymax*5 && led < ymax*6)
  {
    led = (ymax*10+1 - (led - ymax));
    led = led - 2;
  }


  if (led >= ymax*7 && led < ymax*8)
  {
    led = (ymax*14+1 - (led - ymax));
    led = led - 2;
  }

  if (led >= ymax*9 && led < ymax*10)
  {
    led = (ymax*18+1 - (led - ymax));
    led = led - 2;
  }
  PO = led;



   
}
   return PO;
   
}


int LEDTisch::drawkoordinatensystem(int x,int y){
  pixels.setPixelColor(koordinatensystem(x,y), pixels.Color(rot,gruen,blau));
}
int LEDTisch::drawkoordinatensystem(int x,int y,uint32_t color){
  pixels.setPixelColor(koordinatensystem(x,y), color);
}

void LEDTisch::clear(){
  pixels.clear();
}