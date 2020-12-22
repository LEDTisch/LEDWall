package de.ft.ledwall.animation

import org.json.JSONObject
import java.io.InputStream
import java.io.InputStreamReader

class AnimationFileParser {
    companion object {

        fun parse(input:String):Animation? {
            var animation = JSONObject(input)



            var frames = ArrayList<IntArray>()
            var durations = ArrayList<Int>()

            var frameCounter = 1;

            while (animation.has(frameCounter.toString())) {

                durations.add(animation.getJSONObject(frameCounter.toString()).getInt("duration"))

                frames.add(IntArray(150))

                var counter = 0;
                for (pixel in 0 until animation.getJSONObject(frameCounter.toString()).getJSONArray("frame").length()) {

                    frames[frames.size - 1][counter] = animation.getJSONObject(frameCounter.toString()).getJSONArray("frame").getInt(pixel)
                    counter++
                }

                frameCounter++
            }


            if (frameCounter == 1) return null

            return Animation(animation.getString("name"),frames,durations)


        }

        fun parseFile(path:String): Animation?  {

            var ins:InputStream = this.javaClass.classLoader.getResourceAsStream(path)
            var isr: InputStreamReader = InputStreamReader(ins)
            return AnimationFileParser.parse(isr.readText())


        }
    }


}
