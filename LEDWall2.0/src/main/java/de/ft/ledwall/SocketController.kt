package de.ft.ledwall

import de.ft.ledwall.animation.system.ConnectAnimation
import de.ft.ledwall.apps.Standby
import de.ft.ledwall.apps.racingGame.RacingGame
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class SocketController {

    private val socketServer = ServerSocket(8888)
    internal val sockets = ArrayList<Socket>()

    private fun readSocket( socket: Socket) {
        try {
            println("Player ${sockets.indexOf(socket)+1} joined")
            while (true) {
                val text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
                if (text == null) {
                    lostConnection( socket)
                    return
                }
                println("Player ${sockets.indexOf(socket)+1}: $text")
                Main.am.getCurrentApplication()!!.onDataReceive(text,sockets.indexOf(socket)+1) //TODO Debug

            }
        }catch (e:Exception) {

        }
    }

    private fun lostConnection( socket: Socket) {
        println("Player ${sockets.indexOf(socket)+1} left")
        if (sockets.indexOf(socket)+1 == 1) {
            println("Master Player left! Disconnecting all others...")
            for (socket in sockets) {
                socket.close()
            }
            sockets.clear()
        }
        sockets.remove(socket)

        //TEMP
        Main.am.setApplication(Standby())
        (Main.am.getCurrentApplication() as Standby).lastdraw = System.currentTimeMillis()-13500


    }

    private fun acceptNewSockets() {
        while (true) {
            sockets.add(socketServer.accept())
            Thread { readSocket(sockets[sockets.size - 1]) }.start()
            Main.am.systemAnimation.addToQueue(ConnectAnimation.animation)

            Main.am.setApplication(RacingGame())

        }
    }

    fun initSocket() {
        Thread { acceptNewSockets() }.start()
    }

}
