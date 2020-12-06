//
// Created by tim on 06.12.20.
//

#ifndef RPINEOPIXEL_CONTROLSOCKET_H
#define RPINEOPIXEL_CONTROLSOCKET_H


#include <netinet/in.h>
#include <vector>
#include <atomic>
#include "utils/ApplicationManager.h"


class ControlSocket {
public:
ControlSocket(ApplicationManager* applicationManager,SystemInterface* systemInterface);
void begin();

private:
    int server_fd, valread;
    std::vector<int> sockets;
    struct sockaddr_in address;
    int opt = 1;
    ApplicationManager* applicationManager;
    SystemInterface* systemInterface;

    int addrlen = sizeof(address);
    char buffer[1024] = {0};
     void acceptNewSockets();
     void readFromSocket(long controllerNumber,int Socket);
};


#endif //RPINEOPIXEL_CONTROLSOCKET_H
