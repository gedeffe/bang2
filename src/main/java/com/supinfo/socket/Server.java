package com.supinfo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // Listening Port : 18000 || Max connection queue: 5
        try (ServerSocket listen = new ServerSocket(18000, 5);) {
            Socket service;
            while (true) {
                // Ready to accept client connection
                service = listen.accept();
                try (InputStream inputStream = service.getInputStream()) {
                    // listen to client messages
                    byte[] buffer = new byte[256];
                    // While there is byte to read
                    while (inputStream.read(buffer) != -1) {
                        System.out.println(new String(buffer));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
