package de.ft.ledwall

interface Application {

    fun onCreate()
    fun onDraw()
    fun onRun()
    fun onDataReceive(data:String,playerID:Int)
    fun getName():String
    fun onStop()
    fun getVersion():String
    fun getUUID():String


}
