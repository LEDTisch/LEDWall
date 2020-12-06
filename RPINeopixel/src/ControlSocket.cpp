//
// Created by tim on 06.12.20.
//

#include <cstring>
#include <thread>
#include <iostream>
#include "ControlSocket.h"

ControlSocket::ControlSocket() {


}

void ControlSocket::acceptNewSockets() {

    while (1) {
        int tmpSocket = accept(server_fd, (struct sockaddr *) &address, (socklen_t *) &addrlen);

        if (tmpSocket < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        } else {

            this->sockets.push_back(tmpSocket);

        }


        for(auto x:this->sockets) {

            //valread = read( new_socket , buffer, 1024);
            printf("%s\n", buffer);
            send(x, "Test", strlen("Test"), 0);
            close(x);
            printf("Hello message sent\n");

        }

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
  std::thread t(&ControlSocket::acceptNewSockets,*this);
    t.detach();




}