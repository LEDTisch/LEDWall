//
// Created by tim on 06.12.20.
//

#include <cstring>
#include <thread>
#include <iostream>
#include <algorithm>
#include "ControlSocket.h"

std::atomic<long> controllerCount(0);

ControlSocket::ControlSocket(ApplicationManager *applicationManager,SystemInterface* systemInterface) {
    this->applicationManager = applicationManager;
    this->systemInterface = systemInterface;

}

void ControlSocket::readFromSocket(long controllerNumber, int Socket) {

    char buffer[255];
    while (1) {
        bzero(buffer, 256);
        int status = read(Socket, buffer, 255);
        if (status < 0) printf("Error while Reading from Socket\n");
        if (strlen(buffer) == 0) {
            sockets.erase(sockets.begin() + controllerNumber - 1);
            controllerCount--;
            printf("Removed %ld\n", controllerCount.operator long());
            return;
        }

        printf("Player %u: %s", controllerNumber, buffer);

        if (controllerNumber == 1) {
            if(this->applicationManager->checkSystemCommand(buffer)) return; //Request was handled
        }

        if(this->applicationManager->getCurrentApplication()!=NULL)
            this->applicationManager->getCurrentApplication()->onDataReceive(buffer,this->systemInterface,controllerNumber);





    }


}

void ControlSocket::acceptNewSockets() {

    while (1) {
        int tmpSocket = accept(server_fd, (struct sockaddr *) &address, (socklen_t *) &addrlen);

        if (tmpSocket < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        } else {

            this->sockets.insert(this->sockets.begin(), tmpSocket);
            send(tmpSocket, "connected", strlen("connected"), 0);
            printf("New Player: %ld\n", controllerCount.operator long());

            controllerCount++;

            std::thread clientConnected(&ControlSocket::readFromSocket, *this, controllerCount.operator long(),
                                        tmpSocket);
            clientConnected.detach();
        }


        // for(auto x:this->sockets) {

        //valread = read( new_socket , buffer, 1024);


        // }

    }


}

void ControlSocket::begin() {



    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 8080
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,
                   &opt, sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(8888);

    // Forcefully attaching socket to the port 8080
    if (bind(server_fd, (struct sockaddr *) &address,
             sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    //pthread_t threadId;
    //  pthread_create( &threadId, NULL,  &ControlSocket::acceptNewSockets, *this);
    std::thread t(&ControlSocket::acceptNewSockets, *this);
    t.detach();


}