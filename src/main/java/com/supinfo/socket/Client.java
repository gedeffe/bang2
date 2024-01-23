package com.supinfo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        // Listening Port : 18000 || Max connection queue: 5
        try (Socket clientSocket = new Socket("localhost", 18000)) {


            try (OutputStream outputStream = clientSocket.getOutputStream();
                 Scanner input = new Scanner(System.in);) {
                while (true) {
                    String name = input.nextLine();
                    // While there is byte to read
                    outputStream.write(name.getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
