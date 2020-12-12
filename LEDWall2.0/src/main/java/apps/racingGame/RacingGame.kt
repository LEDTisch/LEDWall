package apps.racingGame

import Application


class RacingGame:Application{
    var counter:Int = 0
    var row_x = IntArray(10)
    var fasttickdelay:Float = 100f //75
    var ison = BooleanArray(15)
    var fasttickercounter:Int =0
    var gameend:Int = 0
    var referenzpoint:Int =4 //Point where the left roadside is
    var pixelposx:Int=referenzpoint+1
    var pixelposy:Int=5
    var roadpieces = Array(10) {BooleanArray(16)}
    var  lasttick:Long=0
    var lastfasttick:Long=0
    var firstone:Boolean = true
    var keepstate:Int = 0 //Keep State meens a natural street


    fun reset() {
        for(i in 0 until 10){
            row_x[i]=0
        }
        for(i in 0 until 15){
            ison[i]=false
        }

        for(x in 0 until 10){
            for(y in 0 until 6){
            roadpieces[x][y]=false
        }
        }
       // systeminterface->ledtisch->clear() todo
       // systeminterface->ledtisch->show()

        for(x in 0 until 10) {
            for(y in 0 until 15) {
            roadpieces[x][y] = false
        }

        }

        firstone = true

        pixelposy = 5
        pixelposx = 5
        keepstate = 0
        gameend = 0
        fasttickdelay = 100f
        fasttickercounter =1

    }

    private fun tick() {
        if (keepstate == 0) {
            if (Math.random() % 10 > 7) {
                if (Math.random() % 10 > 4) {
                    keepstate++
                    if (referenzpoint != 0) {
                        referenzpoint--
                    }
                } else {
                    keepstate++
                    if (referenzpoint < 5) {
                        referenzpoint++
                    }
                }
            }
        } else {
            keepstate++
            if (keepstate == 2) {
                keepstate = 0
            }
        }
        if (firstone) {
            firstone = false
            for (i in 0 until referenzpoint + 1) {
                roadpieces[i][15] = true
            }
            for (i in referenzpoint + 4..9) {
                roadpieces[i][15] = true
            }
        }
        for (y in 0..14) {
            for (x in 0..9) {
                roadpieces[x][y] = roadpieces[x][y + 1]
            }
        }
        for (i in 0..9) {
            roadpieces[i][15] = false
        }
        roadpieces[referenzpoint][15] = true
        roadpieces[referenzpoint + 4][15] = true
    }


    override fun onCreate() {
        lasttick = System.currentTimeMillis()
        lastfasttick = System.currentTimeMillis()

        counter =0
        reset()
    }

    override fun onDraw() {
        if (gameend == 0) {
            var counter = 0
            for (y in 0..14) {
                counter++
                for (x in 0..9) {
                    if (roadpieces[x][y]) {
                        if (ison[y]) {
                           // { systeminterface -> { ledTisch -> setcolor(0, 160, 0) } }
                          //  { systeminterface -> { ledTisch -> drawkoordinatensystem(x, y) } }
                        } else {
                           // { systeminterface -> { ledTisch -> setcolor(100, 100, 100) } }
                           // { systeminterface -> { ledTisch -> drawkoordinatensystem(x, y) } }
                        }
                    } else {
                      //  { systeminterface -> { ledTisch -> setcolor(0, 0, 0) } }
                       // { systeminterface -> { ledTisch -> drawkoordinatensystem(x, y) } }
                    }
                }
                if (counter > 2) {
                    counter = 0
                }
            }
            ////{ systeminterface -> { ledTisch -> setcolor(100, 0, 0) } }
           // { systeminterface -> { ledTisch -> drawkoordinatensystem(pixelposx, pixelposy) } }
            //{ systeminterface -> { ledTisch -> drawkoordinatensystem(pixelposx, pixelposy + 1) } }
            if (roadpieces[pixelposx][pixelposy] || roadpieces[pixelposx][pixelposy + 1]) {
                for (x in 0..9) {
                    for (y in 0..14) {
                      //  { systeminterface -> { ledTisch -> setcolor(100, 0, 0) } }
                      //  { systeminterface -> { ledTisch -> drawkoordinatensystem(x, y) } }
                    }
                }
                gameend = 1
            }
        }

    }

    override fun onRun() {
        if (gameend == 0) {
            if (System.currentTimeMillis() - lastfasttick >= fasttickdelay) {
                fasttickdelay -= 0.13f
                tick()
                if (fasttickercounter == 3) {
                    fasttickercounter = 0
                }
                for (i in 0..14) {
                    ison[i] = false
                    if ((i + fasttickercounter) % 3 == 0) {
                        ison[i] = true
                    }
                    /*
    if(i==12+fasttickercounter) {

        ison[i] = true

    }
*/
                }
                fasttickercounter++
                lastfasttick = System.currentTimeMillis()
            }
        }
    }

    override fun onDataReceive(data: String, playerID: Int) {
        if(data.contentEquals("h\n")) pixelposy++
        if(data.contentEquals("r\n")) pixelposx++
        if(data.contentEquals("l\n")) pixelposx--
        if(data.contentEquals("d\n")) pixelposy--

        if(data.contentEquals("n\n")) reset()
    }

    override fun getName(): String {
        return "RacingGame"
    }

    override fun onStop() {
       
    }


}
