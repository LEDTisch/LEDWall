import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class SocketController {

    private val socketServer = ServerSocket(8888)
    private val sockets = ArrayList<Socket>()

    private fun readSocket( socket: Socket) {
        try {
            println("Player ${sockets.indexOf(socket)} joined")
            while (true) {
                val text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
                if (text == null) {
                    lostConnection( socket)
                    return
                }
                println("Player ${sockets.indexOf(socket)}: $text")
            }
        }catch (e:Exception) {

        }
    }

    private fun lostConnection( socket: Socket) {
        sockets.remove(socket)
        println("Player ${sockets.indexOf(socket)} left")
        if (sockets.indexOf(socket) == 1) {
            println("Master Player left! Disconnecting all others...")
            for (socket in sockets) {
                socket.close()
            }
            sockets.clear()
        }
    }

    private fun acceptNewSockets() {
        while (true) {
            sockets.add(socketServer.accept())
            Thread { readSocket(sockets[sockets.size - 1]) }.start()
        }
    }

    fun initSocket() {
        Thread { acceptNewSockets() }.start()
    }

}
