package de.ft.ledwall.animation.system

import de.ft.ledwall.animation.Animation
import de.ft.ledwall.animation.system.ConnectAnimation
import java.awt.Color
import java.util.ArrayList

object ConnectAnimation {
    private var frames = ArrayList<IntArray>()
    private var durations = ArrayList<Int>()
    val animation: Animation
        get() {
            for (z in 0..9) {
                durations.add(25)
                frames.add(IntArray(150))
                val blue: Int = Color(0, (145f / (10f - z)).toInt(), (255 / (10 - z).toInt())).rgb
                for (i in 0..9) {
                    frames[z][i+140] = blue
                }
            }
            return Animation("connect", frames, durations,true)
        }
}
