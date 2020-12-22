package de.ft.ledwall.animation.Red

import de.ft.ledwall.SystemInterface
import de.ft.ledwall.animation.AnimationFileParser
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL


class Red {


    init {

        SystemInterface.table.copyFrameToPixelBuffer(AnimationFileParser.parseFile("red.txt")?.get(0))
        SystemInterface.table.show()


    }

}
