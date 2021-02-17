package de.ft.ledwall.apps.doodlejump


import de.ft.ledwall.Application
import de.ft.ledwall.apps.doodlejump.Player
import de.ft.ledwall.apps.doodlejump.GameState
import de.ft.ledwall.SystemInterface
import java.util.ArrayList

class DoodleJump : Application {
    private val platforms = ArrayList<Platform>()
    private var counter = 0
    private val maxDistance = 4
    private val player = Player()
    private var bouncePlatform: Platform? = null
    private var gameState = GameState.RUNNING
    private val afterGameOverFall = 100

    /**
     * zwischen -1 und 1!!!!!!
     */
    private val moveForce = 0f
    override fun onCreate() {
        platforms.add(Platform(2))
        for (i in 0..4) {
            val blub = Platform(0)
            blub.Yposition = i * maxDistance
            platforms.add(blub)
        }
        gameState = GameState.RUNNING
    }

    fun drawPlatforms() {
        for (p in platforms) {
            for (i in 0 until p.size) {
                SystemInterface.table.setColor(255, 0, 0)
                SystemInterface.table.drawPixel(p.Xposition + i, p.Yposition)
            }
        }
    }

    fun setDown() {
        var p = 0
        while (p < platforms.size) {
            platforms[p].Yposition = platforms[p].Yposition - 1
            if (platforms[p].Yposition < -afterGameOverFall) {
                platforms.removeAt(p)
                p--
            }
            p++
        }
    }

    fun setUp() {
        for (p in platforms) {
            p.Yposition = p.Yposition + 1
        }
    }

    fun platformCreator() {
        val x = (Math.random() * 6).toInt()
        if (platforms[platforms.size - 1].Yposition < 15 - maxDistance) {
            platforms.add(Platform(x))
        } else {
        }
    }

    fun drawPlayer() {
        SystemInterface.table.setColor(0, 255, 0)
        SystemInterface.table.drawPixel(player.Xposition, player.Yposition)
    }

    fun bounceControll() {
        for (p in platforms) {
            if (player.Xposition >= p.Xposition && player.Xposition < p.Xposition + p.size) {
                if (player.jumpspeed < 0) {
                    if (player.Yposition == p.Yposition + 1) {
                        player.Bounce()
                        bouncePlatform = p
                    }
                }
            }
        }
    }

    fun GameOver() {
        if (player.Yposition <= 7) {
            setUp()
        }
    }

    override fun onDraw() {
        SystemInterface.table.clear()
        drawPlatforms()
        drawPlayer()
        if (bouncePlatform != null && counter > 2) {
            if (bouncePlatform!!.Yposition > 2) {
                setDown()
                player.Yposition--
                platformCreator()
            } else {
                bouncePlatform = null
            }
            counter = 0
        }
        counter++
    }

    fun cameraControll() {
        //moveForce=(float)SystemInterface.camera.getXpositionfromzero()/5f;
        player.Xposition = SystemInterface.camera.xposition
    }

    override fun onRun() {
        if (gameState == GameState.RUNNING) {
            bounceControll()
        }
        player.jumpUpdate()
        player.moveUpdate(moveForce)
        cameraControll()
        if (player.Yposition < 0) {
            gameState = GameState.GAMEOVER
            GameOver()
        }
    }

    override fun onDataReceive(data: String, playerID: Int) {}
    override fun getName(): String {
        return "DoodleJump"
    }

    override fun onStop() {}
    override fun getVersion(): Int {
        return 2;
    }

    override fun getUUID(): String {
        return "34a0136b-7056-11eb-8638-0242ac110002";
    }
}