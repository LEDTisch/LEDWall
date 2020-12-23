package de.ft.ledwall.apps.felixtetris;

import de.ft.ledwall.Application;
import de.ft.ledwall.SystemInterface;
import de.ft.ledwall.Var;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Tetris implements Application {


    Block block= new Block();

    int v=0;
    int speed=1000;
    char blue;

    int helligkeit=10;

    long m_save= System.currentTimeMillis();
    int stop=1;
    int art=0;
    int artnext;

    int reihen_gesamt=0;

    int level=1;
    int punkte=0;
    boolean blockschneller=false;
    //festlegung
    int punkte_fur_block_setzen=5;
    int blockschnellerpunkte=1;
    int punkteeinereihe=100;
    int punktezewireihe=200;
    int punktedreireihe=400;
    int punkteevierreihe=600;

    boolean beistartausfuhren=true;

    int levelold=0;


    int sound_output=1;



    void ani_kreisaufl(){
        int i=0;
        while(i<20){
            SystemInterface.table.setColor(0,0,0);
            //TODO HARDWARE this.systeminterface.table.kreis(5,7,i,2);
            i=i+1;

            SystemInterface.table.show();
            //delay(0);

        }
    }

    void ani_gameover(){
        int i=0;
        while(i<20){
            SystemInterface.table.setColor(i*12,255-i*12,0);
            //TODO HARDWARE this.systeminterface.table.kreis(0,0,i,2);
            //TODO HARDWARE this.systeminterface.table.kreis(9,14,i,2);
            i=i+1;

            SystemInterface.table.show();
            //delay(0);

        }



        ani_kreisaufl();
    }






    void ani_start(){

        //block.l.drawImage(image,10,15);
//delay(50000);

        //irstreifen.on();
        //delay(20);
        //irstreifen.rot(4);

        //TODO HARDWARE this.systeminterface.ledFeld.setRotation(0);
        //TODO HARDWARE this.systeminterface.ledFeld.buchstaben(0,3,"hi",false,0,9,1000);
        //sound(sound_start);
        int i=0;
        while(i<10){
            block.setcolor(i*25,255-i*25,0);
            //TODO HARDWARE this.systeminterface.table.kreis(5,7,i,2);
            i=i+1;

            SystemInterface.table.show();
            //TODO HARDWARE this.systeminterface.ledFeld.show();
            //delay(0);

        }
        //delay(5000);
        ani_kreisaufl();
        //TODO HARDWARE this.systeminterface.ledFeld.clear();

    }
    
    
   
    void levelanzeigen(){
        //TODO HARDWARE  this.systeminterface.ledFeld.setRotation(0);
        //led.clear();
        if(level==levelold){
            //TODO HARDWARE this.systeminterface.tab.setcolor(255,0,0);
            //TODO HARDWARE this.systeminterface.ledFeld.zahl(0,2,level);
        }else{
            //TODO HARDWARE this.systeminterface.ledFeld.setcolor(0,0,0);
            //TODO HARDWARE this.systeminterface.ledFeld.zahl(0,2,levelold);
            //TODO HARDWARE this.systeminterface.ledFeld.setcolor(255,0,0);
            //TODO HARDWARE this.systeminterface.ledFeld.zahl(0,2,level);
            levelold=level;
        }
        //TODO HARDWARE this.systeminterface.ledFeld.show();
        //TODO HARDWARE this.systeminterface.ledFeld.setRotation(1);
    }


    void ani_peng(int x,int y){
        int i=0;
        while(i<10){
            block.setcolor(255,255,255);
            //TODO HARDWARE this.systeminterface.table.kreis(x,y,i,15);
            i=i+1;

            //SystemInterface.table.show();
            //delay(0);
            block.clearall();
            //levelanzeigen();
            block.drawall();


        }


    }




    void neuesspiel(){
        //sound(sound_gameover);
        ani_gameover();


        block.clearallarray();
        block.clearall();

        punkte=0;
        level=1;

        //matrix.lc.clearDisplay(0);
        //matrix.lc.clearDisplay(1);
        //matrix.lc.clearDisplay(2);
        //matrix.lc.clearDisplay(3);
        //matrix.zahl(punkte,0,1);
        stop=1;
    }


    void GameOver(){
        neuesspiel();
    }




    void level_up(){
        level=level+1;
        levelanzeigen();

    }

    void setblockcolor(int art,int d){
        int r=0;
        int g=0;
        int b=0;
        switch(art){
            case 1:
                r=255;
                g=0;
                b=0;

                break;
            case 2:
                r=0;
                g=255;
                b=0;
                break;
            case 3:
                r=255;
                g=255;
                b=0;
                break;
            case 4:
                r=255;
                g=0;
                b=255;
                break;
            case 5:
                r=0;
                g=255;
                b=255;
                break;
            case 6:

                r=0;
                g=0;
                b=255;
                break;
        }

        if(d==0){
            block.setcolor(r,g,b);
        }else if(d==1){
            //TODO HARDWARE this.systeminterface.ledFeld.setcolor(r,g,b);
        }
    }


    void show_nextblock(int art,int artalt){
        /*
        int xx=4;
        int yy=-1;
        int[][][] blocks_gros=
                {
                    {{0,0,1,1,1,1},
                     {0,0,1,1,1,1},
                    {1,1,1,1,0,0},
                     {1,1,1,1,0,0}},

                     {{1,1,1,1,0,0},
                     {1,1,1,1,0,0},
                    {0,0,1,1,1,1},
                     {0,0,1,1,1,1}},

        {{0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}},

        {{1,1,1,1,0,0},
        {1,1,1,1,0,0},
        {1,1,1,1,0,0},
        {1,1,1,1,0,0}},

        {{0,0,1,1,0,0},
        {0,0,1,1,0,0},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}},

        {{0,0,0,0,1,1},
        {0,0,0,0,1,1},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}}

        };

        //TODO HARDWARE this.systeminterface.ledFeld.drehung_set(1);

        //TODO HARDWARE this.systeminterface.ledFeld.setcolor(0,0,0);
        for(int x=0;x<6;x=x+1){
            for(int y=0;y<4;y=y+1){
                if(blocks_gros[artalt-1][y][x]==1){
                    //TODO HARDWARE this.systeminterface.ledFeld.drawkoordinatensystem(x+xx,(4-y)+yy);
                }
            }
        }

        setblockcolor(art,1);

        for(int x=0;x<6;x=x+1){
            for(int y=0;y<4;y=y+1){
                if(blocks_gros[art-1][y][x]==1){
                    //TODO HARDWARE this.systeminterface.ledFeld.drawkoordinatensystem(x+xx,(4-y)+yy);
                }
            }
        }

        //TODO HARDWARE this.systeminterface.ledFeld.show();
*/
    }



    int numpixels=60;

/*
    void regenbogen(){

        for(int r=0;r<numpixels;r=r+1){
            if(r<numpixels/3+1){
                //TODO HARDWARE this.systeminterface.lightring.light(r, 255/(numpixels/3)*r,255-255/(numpixels/3)*r,0);
            }
            if(r>numpixels/3 && r< numpixels/3*2+1){
                //TODO HARDWARE this.systeminterface.lightring.light(r, 255/(numpixels/3)*((numpixels/3)-r),0,255-255/(numpixels/3)*((numpixels/3)-r));
            }
            if(r>numpixels/3*2 ){
                //TODO HARDWARE this.systeminterface.lightring.light(r, 0,255-255/(numpixels/3)*((numpixels/3)*2-r),255/(numpixels/3)*((numpixels/3*2)-r));
            }
            // ring.setPixelColor(r, ring.Color(255/60*r,255-255/60*r,0)); // Pixel1 leuchtet in der Farbe Grün
            //TODO HARDWARE this.systeminterface.lightring.show();
        }


    }

*/





/*

    void wheelpos(int wheelpos){
        if(wheelpos < 85){
            this.systeminterface.lightring.light(map(wheelpos,0,255,0,60), wheelpos*3,255-wheelpos*3,0);

        }else if(wheelpos  < 170){
            this.systeminterface.lightring.light(map(wheelpos,0,255,0,60), 255-wheelpos*3,0,wheelpos*3);
        }else{
            this.systeminterface.lightring.light(map(wheelpos,0,255,0,60), 0,wheelpos*3,255-wheelpos*3);
        }
    }




    void regenbogen2(){

        for(int i=0;i<256;i=i+1){
            wheelpos(i);
        }
        this.systeminterface.lightring.show();


    }

*/



    @Override
    public void onCreate() {
        block.init();
        SystemInterface.table.clear();

        //TODO HARDWARE this.systeminterface.ledFeld.setcolor(100,0,0);

        block.clearallarray();

    }

    @Override
    public void onDraw() {
        if(beistartausfuhren==true){
            ani_start();
            beistartausfuhren=false;
            art=((int)(Math.random()*6)+1);
        }

        if(stop==1){
            speed=1000;
            int rk= block.reihenkontrolle();
            reihen_gesamt=reihen_gesamt+rk;
            if(rk > 0){
                block.clearall();
                block.setcolor(255,0,0);
                block.drawall();
            }
            switch(rk){
                case 1:
                    punkte=punkte+punkteeinereihe*level;
                    //sound(sound_einereihe);
                    break;
                case 2:
                    punkte=punkte+punktezewireihe*level;
                    //sound(sound_zweireihen);
                    break;
                case 3:
                    punkte=punkte+punktedreireihe*level;
                    //sound(sound_dreireihen);
                    break;
                case 4:
                    punkte=punkte+punkteevierreihe*level;
                    //sound(sound_vierreihen);
                    break;
            }

            if(reihen_gesamt>level*5){
                reihen_gesamt=0;
                level_up();
            }
            System.out.println(level);






            artnext=((int)(Math.random()*6)+1);


            setblockcolor(art,0);

            show_nextblock(artnext,art);

            block.setblockto(5,15,art);

            stop=0;

            //matrix.zahl(punkte,0,1);
            levelanzeigen();
        }
        if(stop==0){




//addonserial.print(9);

//levelanzeigen();
//block.setcolor(r,g,b);
            //y   x    in dem all array//
//Serial.println(all[1][9]);
//Serial.println(block.getdrehung());
            if(System.currentTimeMillis()>m_save+speed){

                if(blockschneller==true){
                    punkte=punkte+blockschnellerpunkte*level;
                }

                block.draw();
                if(block.kontrolle(2)==0 && block.kontrolle(5)==0){
                    System.out.println("blockdown ");
                    block.down();
                }else{
                    if(block.writeblocktoall() == 10){
                        GameOver();
                    }else{
                        stop=1;
                        if(blockschneller){
                            //sound(sound_harddrop);
                        }else{
                            // sound(sound_softdrop);
                        }
                        ani_peng(block.getblockx(),block.getblocky());
                        punkte=punkte+punkte_fur_block_setzen*level;
                    }

                    m_save=System.currentTimeMillis();
                }


                m_save=System.currentTimeMillis();
            }

        }
        if(stop==1){
            art=artnext;
        }
    }


    @Override
    public void onDataReceive(@NotNull String data, int playerID) {
        if(data.contentEquals("t")){
            if(block.drehen()){
//sound(sound_rotatefailed);
            }else{
                // sound(sound_drehen);
            }

        }

        if(data.contentEquals("r")&&block.kontrolle(1)==0&&block.kontrolle(6)==0){
            block.right();
//  sound(sound_button);
        }

        if(data.contentEquals("l")&&block.kontrolle(3)==0&&block.kontrolle(7)==0){
            block.left();
            // sound(sound_button);
        }

        if(data.contentEquals("d")){
            speed=50;
            blockschneller=true;
        }else{
            speed=1000;

            blockschneller=false;
        }


        if(data.contentEquals("n")){
            neuesspiel();
        }
    }


    @Override
    public void onRun() {

    }


    @NotNull
    @Override
    public String getName() {
        return "Tetris";
    }

    @Override
    public void onStop() {

    }
}
