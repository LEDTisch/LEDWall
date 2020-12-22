package de.ft.ledwall.animation.Red

import de.ft.ledwall.SystemInterface
import de.ft.ledwall.animation.AnimationFileParser


class Red {


    init {

        SystemInterface.table.copyFrameToPixelBuffer(AnimationFileParser.parseFile("rgb.txt")?.get(0))
        SystemInterface.table.show()


    }

}
