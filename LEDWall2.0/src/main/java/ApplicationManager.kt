class ApplicationManager() {

    private var currentApp: Application? = null;
    private var allowDrawing:Boolean = false;

    init {
        Thread{drawingThread()}.start()
    }

    fun checkSystemCommand(command:String):Boolean {
        return false
    }

    fun getCurrentApplication(): Application? {
        return this.currentApp
    }

    fun setApplication(app: Application)  {
        this.currentApp = app
    }

    private fun drawingThread() {
        var lastDrawTime:Long;
        while(true) {
            lastDrawTime = System.currentTimeMillis()
            if(!allowDrawing) continue
            if(this.currentApp!=null)
                this.currentApp!!.onDraw();
            Main.neo.show();
            while(System.currentTimeMillis()-lastDrawTime<60);

        }

    }

}
