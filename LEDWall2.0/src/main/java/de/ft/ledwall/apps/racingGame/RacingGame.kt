package de.ft.ledwall.apps.racingGame

import de.ft.ledwall.Application
import de.ft.ledwall.SystemInterface
import de.ft.ledwall.animation.AnimationManager
import de.ft.ledwall.animation.dynamic.PengAnimation


class RacingGame: Application {
    var fasttickdelay:Float = 100f //75
    var ison = BooleanArray(15)
    var fasttickercounter:Int =0
    var gameend:Int = 0
    var referenzpoint:Int =4 //Point where the left roadside is
    var carposx:Int=referenzpoint+1
    var carposy:Int=5
    var roadpieces = Array(10) {BooleanArray(16)}
    var  lasttick:Long=0
    var lastfasttick:Long=0
    var firstone:Boolean = true
    var keepstate:Int = 2 //Keep State meens a natural street


    var ani_manager:AnimationManager = AnimationManager()

    fun reset() {

        for(i in 0 until 15){
            ison[i]=false
        }

        for(x in 0 until 10){
            for(y in 0 until 6){
            roadpieces[x][y]=false
        }
        }
        SystemInterface.table.clear()

        for(x in 0 until 10) {
            for(y in 0 until 15) {
            roadpieces[x][y] = false
        }

        }

        firstone = true

        carposy = 5
        carposx = 5
        keepstate = 2
        gameend = 0
        fasttickdelay = 100f
        fasttickercounter =1

    }

    private fun tick() {
        if (keepstate == 0) {
            if (Math.random()*Int.MAX_VALUE % 10 > 7) {
                if (Math.random()*Int.MAX_VALUE % 10 > 4) {
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
            if (keepstate >= 2) {
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

        reset()
    }

    override fun onDraw() {
        try {

            if (ani_manager.update() && ani_manager.animationQueue.size > 0 && !ani_manager.animationQueue[0].getName()
                    .contentEquals("peng")
            ) return
            var counter = 0
            for (y in 0..14) {
                counter++
                for (x in 0..9) {
                    if (roadpieces[x][y]) {
                        if (ison[y]) {

                            SystemInterface.table.setColor(0, 160, 0)
                            SystemInterface.table.drawPixel(x, y)
                        } else {
                            SystemInterface.table.setColor(100, 100, 100)
                            SystemInterface.table.drawPixel(x, y)
                        }
                    } else {
                        SystemInterface.table.setColor(0, 0, 0)
                        SystemInterface.table.drawPixel(x, y)
                    }
                }
                if (counter > 2) {
                    counter = 0
                }
            }
            SystemInterface.table.setColor(100, 0, 0)
            SystemInterface.table.drawPixel(carposx, carposy)
            SystemInterface.table.drawPixel(carposx, carposy + 1)


            if (ani_manager.update() && ani_manager.animationQueue.size > 0 && ani_manager.animationQueue[0].getName()
                    .contentEquals("peng")
            ) return

            if (gameend == 0) {
                if (ani_manager.animationsAvailable()) return
                if (roadpieces[carposx][carposy] || roadpieces[carposx][carposy + 1]) {

                    ani_manager.addToQueue(PengAnimation.getAnimation(carposx, carposy));


                    gameend = 1
                }
            } else {
                //for (x in 0..9) {
                //for (y in 0..14) {
                //SystemInterface.table.setColor(100,0,0)
                // SystemInterface.table.drawPixel(x,y)
                // }
                // }
                ani_manager.addToQueue(AnimationManager.redGameOver)

                reset()
                gameend = 0
            }
        }catch (e:Exception) {
            ani_manager.addToQueue(PengAnimation.getAnimation(carposx, carposy));

            gameend = 1
        }

    }

    override fun onRun() {
        if(ani_manager.animationsAvailable()) return
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
        if(data.contentEquals("n")) reset()
        if(gameend==1) return
        if(ani_manager.animationsAvailable()) return
        if(data.contentEquals("h")) carposy++
        if(data.contentEquals("r")) carposx++
        if(data.contentEquals("l")) carposx--
        if(data.contentEquals("d")) carposy--


    }

    override fun getName(): String {
        return "RacingGame"
    }

    override fun onStop() {
       
    }


}
