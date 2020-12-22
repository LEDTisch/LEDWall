package de.ft.ledwall.animation

class Animation {

    var ani_name: String =""
     var frames:ArrayList<IntArray>
     var durations: ArrayList<Int>

    constructor(name:String,frames:ArrayList<IntArray>,durations:ArrayList<Int>)  {
        this.ani_name=name
        this.frames =frames
        this.durations =durations
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

}

