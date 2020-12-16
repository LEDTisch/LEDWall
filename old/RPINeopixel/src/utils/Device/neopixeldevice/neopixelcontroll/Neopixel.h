//
// Created by tim on 07.11.20.
//


/**
 * Default Neopixel String RGB order is GBR this Library only support them
 */

#ifndef NEOPIXEL_H
#define NEOPIXEL_H

#include <stdint.h>
#include <memory>


class Neopixel {
public:



    Neopixel(int n);
    void begin(void);
    void show(void);
    void setPixelColor(uint16_t n, uint8_t r, uint8_t g, uint8_t b);
    void setPixelColor(uint16_t n, uint32_t c);
    void setBrightness(uint8_t);
    void clear(void);
    void end(void);


    enum BlockTypes {
        DATA    = 0xDA,
    };

    static const uint8_t kStartByte = 0xC9;
    static const uint8_t kEndByte   = 0x36;
    struct termios m_serialConfig;

    struct Packet {
        BlockTypes type;
        std::shared_ptr<uint8_t>bufferPtr;
        uint8_t                *dataPtr;
        size_t dataSize;
    };

    Packet frame;
private:

    int numpixels;
    uint32_t* buffer;
};


#endif
