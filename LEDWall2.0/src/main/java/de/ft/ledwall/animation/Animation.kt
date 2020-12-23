package de.ft.ledwall.animation

class Animation {

    var ani_name: String =""
     var frames:ArrayList<IntArray>
     var durations: ArrayList<Int>
     var skipOff:Boolean = false;
    var loop:Boolean = false;

    constructor(name:String,frames:ArrayList<IntArray>,durations:ArrayList<Int>)  {
        this.ani_name=name
        this.frames =frames
        this.durations =durations
    }

    constructor(name:String,frames:ArrayList<IntArray>,durations:ArrayList<Int>,skipOff:Boolean)  {
        this.ani_name=name
        this.frames =frames
        this.durations =durations
        this.skipOff = skipOff
    }
    constructor(name:String,frames:ArrayList<IntArray>,durations:ArrayList<Int>,skipOff:Boolean,loop:Boolean)  {
        this.ani_name=name
        this.frames =frames
        this.durations =durations
        this.skipOff = skipOff
        this.loop = loop
    }

    fun getFrame(frame:Int):IntArray {
        return frames[frame];
    }

    fun getName():String {
        return ani_name
    }

    fun getFrameCount():Int {
        return frames.size
    }
    fun isLoop():Boolean {
        return loop
    }






}

