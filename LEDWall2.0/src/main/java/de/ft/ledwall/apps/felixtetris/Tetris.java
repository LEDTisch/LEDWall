package de.ft.ledwall.apps.felixtetris;

import de.ft.ledwall.Application;
import de.ft.ledwall.Sound;
import de.ft.ledwall.SystemInterface;
import de.ft.ledwall.animation.AnimationManager;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Tetris implements Application {

    String Sound_splash="TetrisSounds/1/SFX_Splash.wav";
    String Sound_startani="TetrisSounds/1/SFX_GameStart.wav";
    String Sound_start="TetrisSounds/start.wav";
    String Sound_rotate="TetrisSounds/block-rotate.wav";
    String Sound_rotatefailed="TetrisSounds/1/SFX_PieceRotateFail.wav";
    String Sound_slowhit="TetrisSounds/slow-hit.wav";
    String Sound_forcehit="TetrisSounds/force-hit.wav";
    String Sound_gameover="TetrisSounds/gameover.wav";
    String Sound_lineclear="TetrisSounds/line-remove.wav";
    String Sound_lineclearspecial="TetrisSounds/line-removal4.wav";

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


    int levelold=0;


    AnimationManager ani_manager = new AnimationManager();

    public Tetris(){
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






    void neuesspiel(){
        ani_manager.addToQueue(AnimationManager.tetrisgameover);


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
        Sound.play(Sound_start);

    }


    void GameOver(){
        Sound.play(Sound_gameover);
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
        Sound.play(Sound_startani);


        block.init();
        SystemInterface.table.clear();

        //TODO HARDWARE this.systeminterface.ledFeld.setcolor(100,0,0);

        block.clearallarray();

        ani_manager.addToQueue(AnimationManager.rainbowInAndOut);
        double random=Math.random();
        art=(int)((Math.random()) * 6 + 1);
        System.out.println("artc: "+art+"  random: "+random);

        Sound.TetrisTheme.loop(Clip.LOOP_CONTINUOUSLY);

    }
    private void rowcheck(){

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
                Sound.play(Sound_lineclear);
                break;
            case 2:
                punkte=punkte+punktezewireihe*level;
                Sound.play(Sound_lineclear);
                break;
            case 3:
                punkte=punkte+punktedreireihe*level;
                Sound.play(Sound_lineclearspecial);
                break;
            case 4:
                punkte=punkte+punkteevierreihe*level;
                Sound.play(Sound_lineclearspecial);
                break;
        }

        if(reihen_gesamt>level*5){
            reihen_gesamt=0;
            level_up();
        }
        System.out.println(level);
    }

    @Override
    public void onDraw() {
        if(ani_manager.update()) return;


        if(stop==1){
            speed=1000;

            rowcheck();


            double random=Math.random();
            artnext=(int)((Math.random()) * 6 + 1);
            System.out.println("artd: "+artnext+"  random: "+random);


            setblockcolor(art,0);

            show_nextblock(artnext,art);

            block.setblockto(5,15,art);

            stop=0;

            //matrix.zahl(punkte,0,1);
            levelanzeigen();
        }
        if(stop==0){



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
                    block.down();
                }else{
                    if(block.writeblocktoall() == 10){
                        GameOver();
                    }else{
                        stop=1;
                        if(blockschneller){
                            Sound.play(Sound_forcehit);
                        }else{
                            Sound.play(Sound_slowhit);
                        }
                       // ani_manager.addToQueue(PengAnimation.getAnimation(block.getblockx(),block.getblocky()));
                        punkte=punkte+punkte_fur_block_setzen*level;
                    }
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
                Sound.play(Sound_rotatefailed);
            }else{
                Sound.play(Sound_rotate);
            }

        }

        if(data.contentEquals("r")&&block.kontrolle(1)==0&&block.kontrolle(6)==0){
            block.right();
        }

        if(data.contentEquals("l")&&block.kontrolle(3)==0&&block.kontrolle(7)==0){
            block.left();
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
        if(ani_manager.animationsAvailable()) return;

    }


    @NotNull
    @Override
    public String getName() {
        return "Tetris";
    }

    @Override
    public void onStop() {
        Sound.TetrisTheme.stop();
    }

    @Override
    public int getVersion() {
        return 9999;
    }

    @NotNull
    @Override
    public String getUUID() {
        return "8f8188a7-6691-11eb-a9b2-b827ebba2f99";
    }
}
