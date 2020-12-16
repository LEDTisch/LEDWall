package de.ft.ledwall

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
            try {
                if (this.currentApp != null) this.currentApp!!.onRun()
            }catch (e:Exception) {
                error(e);
            }
            TimeUnit.MILLISECONDS.sleep(7)
        }
    }

    private fun drawingThread() {
        while(true) {
            if(!allowDrawing) continue
            if(this.currentApp!=null)
                try {
                    this.currentApp!!.onDraw();
                }catch (e:Exception) {
                    error(e);
                }
            SystemInterface.table.show();
            TimeUnit.MILLISECONDS.sleep(60)

        }

    }

    private fun error(e:Exception) {
        try {
            this.currentApp!!.onStop()
        }catch (e:Exception){}
        this.currentApp = null;

        SystemInterface.table.clear()
        SystemInterface.table.show()
        println("Fehler!")
        e.printStackTrace()



    }

}
