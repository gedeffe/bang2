package com.supinfo.whatzappeuh.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;

public class MessageReceiver implements ReceiverSubscriber {

    private List<Receiver> subscriberList;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    public void MessageReceiver(){
        this.subscriberList = new ArrayList<>();
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

    public void establishConnection(int port){

    }
}
