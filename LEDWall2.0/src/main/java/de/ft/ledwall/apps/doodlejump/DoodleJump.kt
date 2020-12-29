package de.ft.ledwall.apps.doodlejump

import de.ft.ledwall.Application
import de.ft.ledwall.Main
import de.ft.ledwall.SystemInterface
import java.util.concurrent.TimeUnit

class DoodleJump : Application {
    private var platforms:ArrayList<Platform> = ArrayList();
    private var counter=0;
    private var jumpcounter=0;
    private var speed:Float=1f;//sekunden
    private var maxDistance=4;
    private var player:Player= Player();
    override fun onCreate() {
        platforms.add(Platform(2));
    }
    fun drawPlatforms(){
        for(p in platforms){
            for(i in 0 until p.size){
                SystemInterface.table.setColor(255,0,0);
                SystemInterface.table.drawPixel(p.Xposition+i,p.Yposition);
            }
        }
    }
    fun setDown(){
        var p=0;
        while(p<platforms.size){
            platforms[p].Yposition--;
            if(platforms[p].Yposition < 0){
                platforms.removeAt(p);
                p--;
            }
            p++;
        }
    }
    fun platformCreator(){
        var x:Int = (Math.random()*6).toInt();
        if(platforms[platforms.size-1].Yposition < 15-maxDistance){
            platforms.add(Platform(x));
        }else{

        }

    }
    fun drawPlayer(){
        SystemInterface.table.setColor(0,255,0);
        SystemInterface.table.drawPixel(player.Xposition,player.Yposition);
    }

    override fun onDraw() {
        SystemInterface.table.clear();
        drawPlatforms();
        drawPlayer();
        if(counter>=Main.am.fps*1f){
            counter=0;
            setDown();
            platformCreator();
        }
        if(jumpcounter>=Main.am.fps/20){
            player.update();
        }
        jumpcounter++;
        counter++;
    }
    override fun onRun() {}
    override fun onDataReceive(data: String, playerID: Int) {}
    override fun getName(): String {
        return "DoodleJump";
    }

    override fun onStop() {}
}