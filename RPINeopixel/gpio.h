//
// Created by tim on 08.11.20.
//


#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include <fcntl.h>
#include <cstring>
#include <stdint-gcc.h>
#include <string>

#ifndef RPINEOPIXEL_GPIO_H
#define RPINEOPIXEL_GPIO_H


volatile uint8_t *pxl_raw;

static inline void mapPin(uint8_t pin) {

    int fd = open("/sys/class/gpio/export", O_WRONLY);

    if (fd == -1) {
        perror("Unable to open: /sys/class/gpio/export No Permission?");
        exit(1);
    }

    if (write(fd, std::to_string(pin).c_str(), 2) != 2) {
        perror("Error writing to /sys/class/gpio/export");
        exit(1);
    }

    close(fd);

    // Set the pin to be an output by writing "out" to /sys/class/gpio/gpio24/direction

    fd = open(std::string ("/sys/class/gpio/gpio"+std::to_string(pin)+"/direction").c_str(), O_WRONLY);
    if (fd == -1) {
        perror("Unable to open /sys/class/gpio/gpio24/direction");
        exit(1);
    }

    if (write(fd, "out", 3) != 3) {
        perror("Error writing to /sys/class/gpio/gpio/direction");
        exit(1);
    }
    close(fd);

}

static inline void test(uint8_t pin) {

  int fd = open("/sys/class/gpio/gpio24/value", O_WRONLY);
    if (fd == -1) {
        perror("Unable to open /sys/class/gpio/gpio24/value");
        exit(1);
    }

    // Toggle LED 50 ms on, 50ms off, 100 times (10 seconds)

    for (int i = 0; i < 100; i++) {
        if (write(fd, "1", 1) != 1) {
            perror("Error writing to /sys/class/gpio/gpio24/value");
            exit(1);
        }
        usleep(50000);

        if (write(fd, "0", 1) != 1) {
            perror("Error writing to /sys/class/gpio/gpio24/value");
            exit(1);
        }
        usleep(50000);
    }

    close(fd);


}


static inline void unmapPin(uint8_t pin) {

    // Unexport the pin by writing to /sys/class/gpio/unexport

    int fd = open("/sys/class/gpio/unexport", O_WRONLY);
    if (fd == -1) {
        perror("Unable to open /sys/class/gpio/unexport");
        exit(1);
    }

    if (write(fd, std::to_string(pin).c_str(), 2) != 2) {
        perror("Error writing to /sys/class/gpio/unexport");
        exit(1);
    }

    close(fd);
}

#endif //RPINEOPIXEL_GPIO_H
