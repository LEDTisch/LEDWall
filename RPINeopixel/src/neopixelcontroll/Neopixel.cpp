//
// Created by tim on 07.11.20.
//

#include <iostream>
#include <termio.h>
#include "stdio.h"
#include "Neopixel.h"

#define LED_USB_CONTROLLER_DEV "/dev/ttyACM0"

#define RED 0x00FF0000
#define GREEN 0x0000FF00
#define BLUE 0x000000FF
int neopixel_device;

Neopixel::Neopixel(int n) {
    this->numpixels = n;
    this->buffer = (uint32_t *) malloc(sizeof(uint32_t) * this->numpixels);


}

void Neopixel::show() {
    uint8_t *outputPtr = this->frame.dataPtr;
    for(int i=0;i<numpixels*3;i=i+3){
        outputPtr[i+0]=buffer[i/3]>>16;
        outputPtr[i+1]=(buffer[i/3] & 0xFF00)>>8;
        outputPtr[i+2]=(buffer[i/3] & 0xFFF0);

    }


    tcdrain(neopixel_device);
    write(neopixel_device,this->frame.bufferPtr.get(),this->frame.dataSize+5);

}

void Neopixel::clear() {
    for(int i=0;i<numpixels;i++){
        setPixelColor(i,0);
    }
}

void Neopixel::setPixelColor(uint16_t n, uint8_t r, uint8_t g, uint8_t b) {
    if (n > this->numpixels) return;
    if (r > 255 || r < 0) return;
    if (g > 255 || g < 0) return;
    if (b > 255 || b < 0) return;

    this->buffer[n] = (r << 16 | g << 8 | b);



}

void Neopixel::setPixelColor(uint16_t n, uint32_t c) {
    if (n > this->numpixels) return;
    this->buffer[n] = c;


}

void Neopixel::setBrightness(uint8_t) {

}

void Neopixel::end() {
    close(neopixel_device);

}

void Neopixel::begin(void) {
    neopixel_device = open(LED_USB_CONTROLLER_DEV, O_RDWR | O_NOCTTY | O_NDELAY);
    if (neopixel_device == -1) {
        printf("failed to open port ");
    } else {
        printf("Connected");
    }




    //
    // Check if the file descriptor is pointing to a TTY device or not.
    //
    if(!isatty(neopixel_device)) {
        std::cout << "File is not a TTY device" << std::endl;
        return;
    }

    //
    // Get the current configuration of the serial interface
    //
    if(tcgetattr(neopixel_device, &m_serialConfig) < 0) {
        std::cout << "Failed to get serial interface config" << std::endl;
        return;
    }

    //
    // Input flags - Turn off input processing
    //
    // convert break to null byte, no CR to NL translation,
    // no NL to CR translation, don't mark parity errors or breaks
    // no input parity check, don't strip high bit off,
    // no XON/XOFF software flow control
    //
    m_serialConfig.c_iflag &= ~(IGNBRK | BRKINT | ICRNL |
                                INLCR | PARMRK | INPCK | ISTRIP | IXON);

    //
    // Output flags - Turn off output processing
    //
    // no CR to NL translation, no NL to CR-NL translation,
    // no NL to CR translation, no column 0 CR suppression,
    // no Ctrl-D suppression, no fill characters, no case mapping,
    // no local output processing
    //
    // m_serialConfig.c_oflag &= ~(OCRNL | ONLCR | ONLRET |
    //                     ONOCR | ONOEOT| OFILL | OLCUC | OPOST);
    m_serialConfig.c_oflag = 0;

    //
    // No line processing
    //
    // echo off, echo newline off, canonical mode off,
    // extended input processing off, signal chars off
    //
    m_serialConfig.c_lflag &= ~(ECHO | ECHONL | ICANON | IEXTEN | ISIG);

    //
    // Turn off character processing
    //
    // clear current char size mask, no parity checking,
    // no output processing, force 8 bit input
    //
    m_serialConfig.c_cflag &= ~(CSIZE | PARENB);
    m_serialConfig.c_cflag |= CS8;

    //
    // One input byte is enough to return from read()
    // Inter-character timer off
    //
    m_serialConfig.c_cc[VMIN]  = 1;
    m_serialConfig.c_cc[VTIME] = 0;

    //
    // Communication speed (simple version, using the predefined
    // constants)
    //
    if(cfsetispeed(&m_serialConfig, B115200) < 0 || cfsetospeed(&m_serialConfig, B115200) < 0) {
        std::cout << "Failed to set serial speed" << std::endl;
        return;
    }

    //
    // Finally, apply the configuration
    //
    if(tcsetattr(neopixel_device, TCSAFLUSH, &m_serialConfig) < 0) {
        std::cout << "Failed to save configuration" << std::endl;
        return;
    }



    Packet p;
    size_t packetSize = 4 + this->numpixels*3 + 1;
    p.dataSize = this->numpixels*3;
    uint8_t* rawBuffer = new uint8_t[packetSize];
    std::shared_ptr<uint8_t> buffer( rawBuffer,
                                     std::default_delete<uint8_t[]>() );
    p.bufferPtr = buffer;
    p.dataPtr = rawBuffer + 4;
    p.type = BlockTypes::DATA;
    rawBuffer[0] = kStartByte;
    rawBuffer[1] = BlockTypes::DATA;
    uint8_t high = (this->numpixels*3 >> 8);
    rawBuffer[2] = high;
    uint8_t low = this->numpixels*3;
    rawBuffer[3] = low;
    rawBuffer[packetSize-1] = kEndByte;

    this->frame=p;

    sleep(5);

}
