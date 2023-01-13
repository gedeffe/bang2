package com.supinfo.whatzappeuh.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;

public class MessageReceiver implements ReceiverSubscriber {
    private final List<Receiver> subscriberList;
    private int port;
    private final ServerSocket serverSocket;
    private boolean running;

    public MessageReceiver(int port) throws IOException {
        this(port, new ServerSocket(port));
    }

    public MessageReceiver(int port, ServerSocket serverSocket) throws IOException {
        this.subscriberList = new ArrayList<>();
        this.port = port;

        this.serverSocket = serverSocket;
        this.serverSocket.setSoTimeout(1000);

        this.running = false;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean subscribe(Receiver receiver) {
        try {
            this.subscriberList.add(receiver);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void start() throws IOException {
        this.running = true;
        while (this.running) {
            Message message = this.receive();
            this.subscriberList.forEach((subscriber) -> subscriber.notifyMessage(message));
        }
    }

    public Message receive() throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String received = in.readLine();
        InetAddress remoteAddress = ((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress();
        out.println("connected");

        in.close();
        out.close();
        clientSocket.close();

        return new Message(received, remoteAddress);
    }

    public void stop() throws IOException {
        this.running = false;
        this.serverSocket.close();
    }
}
