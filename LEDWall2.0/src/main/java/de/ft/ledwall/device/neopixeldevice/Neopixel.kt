package de.ft.ledwall.device.neopixeldevice

import com.fazecast.jSerialComm.SerialPort
import java.awt.Color
import kotlin.Throws
import java.lang.InterruptedException
import java.util.concurrent.TimeUnit

class Neopixel(numpixels: Int) {
    var device: SerialPort? = null
    var numpixels = 0
    private val buffer: ByteArray
    private val sendbuffer: ByteArray
    @Throws(InterruptedException::class)
    fun begin() {
            device = SerialPort.getCommPort("ttyACM0")
        device!!.baudRate = 112500
        device!!.openPort()
        TimeUnit.MILLISECONDS.sleep(2500)
        println("Connected")
    }

    fun show() {
        System.arraycopy(buffer, 0, sendbuffer, 4, numpixels * 3)
        device!!.writeBytes(sendbuffer, sendbuffer.size.toLong())
    }

    fun setPixelColor(n: Int, r: Int, g: Int, b: Int) {
        if (n > numpixels) return
        if (r > 255 || r < 0) return
        if (g > 255 || g < 0) return
        if (b > 255 || b < 0) return
        buffer[n * 3] = r.toByte()
        buffer[n * 3 + 1] = g.toByte()
        buffer[n * 3 + 2] = b.toByte()
    }

    fun setPixelColor(n: Int, rgb: Int) {
        var r: Int
        var g: Int
        var b: Int
        val blueMask = 0xFF0000
        val greenMask = 0xFF00
        val redMask = 0xFF
        val tempcol =Color(rgb)
        this.setPixelColor(n, tempcol.red,tempcol.green,tempcol.blue)


    }

    fun setBrightness(brightness: Float) {}

    fun clear() {
        for (i in 0 until numpixels) {
            setPixelColor(i, 0, 0, 0)
        }
    }

    init {
        this.numpixels = numpixels
        buffer = ByteArray(this.numpixels * 3)
        sendbuffer = ByteArray(4 + this.numpixels * 3 + 1)
        sendbuffer[0] = 0xC9.toByte()
        sendbuffer[1] = 0xDA.toByte()
        val high = (this.numpixels * 3 shr 8).toByte()
        val low = (this.numpixels * 3).toByte()
        sendbuffer[2] = high
        sendbuffer[3] = low
        sendbuffer[sendbuffer.size - 1] = 0x36.toByte()
    }
}
