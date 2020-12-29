package de.ft.ledwall

import de.ft.ledwall.animation.AnimationManager
import de.ft.ledwall.animation.system.ErrorAnimation
import de.ft.ledwall.apps.licht.Licht
import de.ft.ledwall.apps.racingGame.RacingGame
import de.ft.ledwall.apps.snake.SnakeGame
import de.ft.ledwall.apps.tetris.Tetris
import java.util.concurrent.TimeUnit

class ApplicationManager() {
    private var timer:Long=0;
    private var currentApp: Application? = null
    private var allowDrawing:Boolean = true
    private var enableDraw:Boolean=true;
    internal final var systemAnimation:AnimationManager = AnimationManager()
    var fps:Int=20;

    fun init() {
        Thread { drawingThread() }.start()
        Thread { runningThread() }.start()

    }

    fun checkSystemCommand(command:String):Boolean {

        if(command.contains("switchTo:")) {


            if(command.contentEquals("switchTo:Tetris#")) {
                Main.am.setApplication(de.ft.ledwall.apps.felixtetris.Tetris())
                return true
            }
            if(command.contentEquals("switchTo:Racing Game#")) {
                Main.am.setApplication(RacingGame())
                return true
            }

            if(command.contentEquals("switchTo:Snake#")) {
                Main.am.setApplication(SnakeGame())
                return true
            }
            if(command.contentEquals("switchTo:Licht#")) {
                Main.am.setApplication(Licht())
                return true
            }





        }
        return false
    }

    fun getCurrentApplication(): Application? {
        return this.currentApp
    }

    fun setApplication(app: Application)  {
        println(app.getName())
        enableDraw=false;
        if(this.currentApp!=null) this.currentApp!!.onStop()
        this.currentApp = app
        if(this.currentApp!=null) app.onCreate()
        enableDraw=true;
    }


    private fun runningThread() {
        while(true) {
                if (systemAnimation.animationsAvailable() && systemAnimation.animationQueue[0].getName().contains("error")) {
                    TimeUnit.MILLISECONDS.sleep(7)
                    continue
                }
                try {
                    if (this.currentApp != null && enableDraw) this.currentApp!!.onRun()
                } catch (e: Exception) {
                    error(e);
                }
                TimeUnit.MILLISECONDS.sleep(7)

        }
    }

    private fun drawingThread() {
        while(true) {
                timer = System.currentTimeMillis()
                if (!allowDrawing) continue
                if (this.currentApp != null && enableDraw)
                    try {
                        this.currentApp!!.onDraw();
                    } catch (e: Exception) {
                        error(e);
                    }

                systemAnimation.update()
                SystemInterface.table.show()

                TimeUnit.MILLISECONDS.sleep(1000/fps - (System.currentTimeMillis() - timer))
        }

    }

    private fun error(e:Exception) {
  //      try {
    //        this.currentApp!!.onStop()
      //  }catch (e:Exception){}
        //this.currentApp = null;

        SystemInterface.table.clear()
        SystemInterface.table.show()
        systemAnimation.addToQueue(ErrorAnimation.getAnimation())
        println("Fehler!")
        e.printStackTrace()



    }

    fun getSysAnimation():AnimationManager {
        return systemAnimation
    }



}
