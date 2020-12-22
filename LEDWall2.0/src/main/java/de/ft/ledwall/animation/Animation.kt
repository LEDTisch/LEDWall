package de.ft.ledwall.animation

interface Animation {

    fun getFrame(frame:Int):LongArray
    fun getName():String
    fun getFrameCount():Int

}

