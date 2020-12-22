package de.ft.ledwall.apps.felixtetris;

import de.ft.ledwall.SystemInterface;

public class Block {
    int x=0;
    int y=0;
    int art=0;
    int rot=0;
    int gruen=0;
    int blau=0;
    int drehung=0;
    byte[][] all=new byte[15][10];
    byte[][][] allfarbe=new byte[15][10][3];


    int f1[][] = {{1, 0},{1, 1},{0, 1},{0, 0}};
    int f2[][] = {{0, 1},{1, 1},{1, 0},{0, 0}};
    int f3[][] = {{1, 0},{1, 0},{1, 0},{1, 0}};
    int f4[][] = {{0, 0},{1, 1},{1, 1},{0, 0}};
    int f5[][] = {{1, 0},{1, 1},{1, 0},{0, 0}};
    int f6[][] = {{1, 0},{1, 0},{1, 1},{0, 0}};



    public Block(int x,int y,int art)
    {
        x=this.x;
        y=this.y;
        art=this.art;
        
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


    public void init(){
        // l.init(_pin);
    }

    public void show(){
        SystemInterface.table.show();
    }



    public int reihenkontrolle(){
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
                // Serial.println("reihe");
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


    public int getdrehung(){
        return drehung;
    }



    public void setblockto(int _x,int _y,int _art){
        x=_x;
        y=_y;
        art=_art;
    }

    public void setcolor(int r,int g,int b){
        rot=r;
        gruen=g;
        blau=b;
        SystemInterface.table.setColor(rot,gruen,blau);
    }


    public void drawall(){
        for(int x=0;x<10;x=x+1){
            for(int y=0;y<15;y=y+1){
                if(all[y][x]==1){
                    SystemInterface.table.setColor(allfarbe[y][x][0],allfarbe[y][x][1],allfarbe[y][x][2]);
                    SystemInterface.table.drawPixel(x,y);
                }
            }
        }
        SystemInterface.table.show();
    }

    public void clearall(){
        SystemInterface.table.clear();
    }

    public void clearallarray(){
        for(int x=0;x<10;x++){
            for(int y=0;y<15;y++){
                all[y][x]=0;
                for(int f=0;f<2;f++){
                    allfarbe[y][x][f]=0;
                }
            }
        }
    }


    public boolean drehen()
    {
        boolean failed=false;

        SystemInterface.table.setColor(0,0,0);
        draw();
        SystemInterface.table.setColor(rot,gruen,blau);
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

    public void down()
    {
        SystemInterface.table.setColor(0,0,0);
        draw();
        y=y-1;
        SystemInterface.table.setColor(rot,gruen,blau);
        draw();

    }

    public void right()
    {
        SystemInterface.table.setColor(0,0,0);
        draw();
        x=x+1;
        SystemInterface.table.setColor(rot,gruen,blau);
        draw();

    }

    public void left()
    {
        SystemInterface.table.setColor(0,0,0);
        draw();
        x=x-1;
        SystemInterface.table.setColor(rot,gruen,blau);
        draw();

    }

    public int getblockx(){
        return x;
    }
    public int getblocky(){
        return y;
    }

    public void draw()
    {


        switch(drehung){
            case 0:
                for(int xd=0;xd<2;xd=xd+1){
                    for(int yd=0;yd<4;yd=yd+1){
                        switch(art){
                            case 1:
                                if(f1[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    SystemInterface.table.drawPixel(xd+x,2-yd+y);
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
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);        }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);        }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);        }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);        }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-yd+x,2-xd+y);        }
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
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    SystemInterface.table.drawPixel(2-xd+x,yd+y);
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
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    SystemInterface.table.drawPixel(yd+x,xd+y);
                                }
                                break;
                        }

                    }
                }

                break;









        }

        SystemInterface.table.show();
    }

    public int kontrollpixel(int x,int y, int k){
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




    public int kontrolle(int k){
        return kw(k,0);  //wenn 0 dann kontrolle wenn 1 dann writetoall
    }
    public int writeblocktoall(){
        return kw(0,1);
    }




    public int kr(int x,int y,int k,int s,int koli){

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

            if(y-1<15 && y-1>=0 && x>=0 && x<10) {
                if (all[y - 1][x] == 1 && k == 5) {
                    koli = 1;

                }
            }
            if(x+1<10 && x+1>=0 && y>=0 && y<15) {
                if (all[y][x + 1] == 1 && k == 6) {
                    koli = 1;

                }
            }
            if(x-1>=0 && x-1<10 && y>=0 && y<15) {
                if (all[y][x - 1] == 1 && k == 7) {
                    koli = 1;

                }
            }
            if(y>=0 && y<15 && x>=0 && x<10) {
                if (all[y][x] == 1 && k == 8) {
                    koli = 1;
                }
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
                allfarbe[y][x][0]=(byte)rot;
                allfarbe[y][x][1]=(byte)gruen;
                allfarbe[y][x][2]=(byte)blau;
            }
            if(y>=15){
                koli=10;
            }
        }
        return koli;
    }


    public int kw(int k,int s){
        int koli=0;

        switch(drehung){
            case 0:
                for(int xd=0;xd<2;xd=xd+1){
                    for(int yd=0;yd<4;yd=yd+1){
                        switch(art){
                            case 1:
                                if(f1[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
                                    koli=kr(xd+x,2-yd+y,k,s,koli);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
                                    koli=kr(xd+x,2-yd+y,k,s,koli);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
                                    koli=kr(xd+x,2-yd+y,k,s,koli);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
                                    koli=kr(xd+x,2-yd+y,k,s,koli);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
                                    koli=kr(xd+x,2-yd+y,k,s,koli);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    //l.drawPixel(xd+x,2-yd+y);
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
                                    //l.drawPixel(2-yd+x,2-xd+y);
                                    koli=kr(2-yd+x,2-xd+y,k,s,koli);

                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    //l.drawPixel(2-yd+x,2-xd+y); 
                                    koli=kr(2-yd+x,2-xd+y,k,s,koli);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    //l.drawPixel(2-yd+x,2-xd+y);  
                                    koli=kr(2-yd+x,2-xd+y,k,s,koli);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    //l.drawPixel(2-yd+x,2-xd+y);    
                                    koli=kr(2-yd+x,2-xd+y,k,s,koli);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    //l.drawPixel(2-yd+x,2-xd+y);    
                                    koli=kr(2-yd+x,2-xd+y,k,s,koli);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    //l.drawPixel(2-yd+x,2-xd+y);   
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
                                    //l.drawPixel(2-xd+x,yd+y);
                                    koli=kr(2-xd+x,yd+y,k,s,koli);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    //l.drawPixel(2-xd+x,yd+y);
                                    koli=kr(2-xd+x,yd+y,k,s,koli);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    //l.drawPixel(2-xd+x,yd+y);
                                    koli=kr(2-xd+x,yd+y,k,s,koli);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    //l.drawPixel(2-xd+x,yd+y);
                                    koli=kr(2-xd+x,yd+y,k,s,koli);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    //l.drawPixel(2-xd+x,yd+y);
                                    koli=kr(2-xd+x,yd+y,k,s,koli);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    //l.drawPixel(2-xd+x,yd+y);
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
                                    //l.drawPixel(yd+x,xd+y);
                                    koli=kr(yd+x,xd+y,k,s,koli);
                                }
                                break;
                            case 2:
                                if(f2[yd][xd]==1){
                                    //l.drawPixel(yd+x,xd+y);
                                    koli=kr(yd+x,xd+y,k,s,koli);
                                }
                                break;
                            case 3:
                                if(f3[yd][xd]==1){
                                    //l.drawPixel(yd+x,xd+y);
                                    koli=kr(yd+x,xd+y,k,s,koli);
                                }
                                break;
                            case 4:
                                if(f4[yd][xd]==1){
                                    //l.drawPixel(yd+x,xd+y);
                                    koli=kr(yd+x,xd+y,k,s,koli);
                                }
                                break;
                            case 5:
                                if(f5[yd][xd]==1){
                                    //l.drawPixel(yd+x,xd+y);
                                    koli=kr(yd+x,xd+y,k,s,koli);
                                }
                                break;
                            case 6:
                                if(f6[yd][xd]==1){
                                    //l.drawPixel(yd+x,xd+y);
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

}
