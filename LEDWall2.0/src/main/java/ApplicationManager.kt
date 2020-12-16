import apps.racingGame.RacingGame
import java.util.concurrent.TimeUnit

class ApplicationManager() {

    private var currentApp: Application? = null
    private var allowDrawing:Boolean = true

    fun init() {
        Thread { drawingThread() }.start()
        Thread { runningThread() }.start()
    }

    fun checkSystemCommand(command:String):Boolean {
        return false
    }

    fun getCurrentApplication(): Application? {
        return this.currentApp
    }

    fun setApplication(app: Application)  {
        if(this.currentApp!=null) this.currentApp!!.onStop()
        this.currentApp = app
        if(this.currentApp!=null) app.onCreate()
    }


    private fun runningThread() {
        while(true) {
            if (this.currentApp != null) this.currentApp!!.onRun()
            TimeUnit.MILLISECONDS.sleep(10)
        }
    }

    private fun drawingThread() {
        while(true) {
            if(!allowDrawing) continue
            if(this.currentApp!=null)
                this.currentApp!!.onDraw();
            SystemInterface.table.show();
            TimeUnit.MILLISECONDS.sleep(80)

        }

    }

}
