interface Application {

    fun onCreate()
    fun onDraw()
    fun onRun()
    fun onDataReceive(data:String,playerID:Int)
    fun getName():String
    fun onStop()


}