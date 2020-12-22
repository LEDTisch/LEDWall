package de.ft.ledwall.animation

import org.json.JSONObject
import java.io.InputStream
import java.io.InputStreamReader

class AnimationFileParser {
    companion object {

        fun parse(input:String): ArrayList<IntArray>? {
            var animation = JSONObject(input)



            var frames = ArrayList<IntArray>()

            var frameCounter = 1;

            while (animation.has(frameCounter.toString())) {

                frames.add(IntArray(150))

                var counter = 0;
                for (pixel in 0 until animation.getJSONArray(frameCounter.toString()).length()) {

                    frames[frames.size - 1][counter] = animation.getJSONArray(frameCounter.toString()).getInt(pixel)
                    counter++
                }

                frameCounter++
            }


            if (frameCounter == 1) return null

            return frames


        }

        fun parseFile(path:String): ArrayList<IntArray>?  {

            var ins:InputStream = this.javaClass.classLoader.getResourceAsStream(path)
            var isr: InputStreamReader = InputStreamReader(ins)
            return AnimationFileParser.parse(isr.readText())


        }
    }


}
