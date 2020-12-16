package de.ft.ledwall.socket

import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class Socket {
    companion object {
        private val socketServer = ServerSocket(8888);
        private val sockets = ArrayList<Socket>()

        fun readSocket(player:String, socket:Socket) {
            val isr = InputStreamReader(socket.getInputStream());
            isr.read()


        }

        fun acceptNewSockets() {
            while(true) {
                sockets.add(socketServer.accept());
            }

        }

        @JvmStatic
        fun initSocket() {
            Thread { acceptNewSockets() }.start();


        }


    }

}
