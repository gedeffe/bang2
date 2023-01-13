package com.supinfo.whatzappeuh.send;

import com.supinfo.whatzappeuh.accounts.AccountsRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SenderImplement implements Sender {

    private Socket s;
    private PrintWriter out;
    private BufferedReader in;
    private AccountsRepository accountsRepository;

    public SenderImplement(AccountsRepository accountsRepository) {
    }

    @Override
    public void sender(InetAddress ip, int port, String text) throws IOException {
        if (!text.equals("")) {
            connection(ip, port);
            sendText(text);
            stopConn();
        }
    }

    public void connection(InetAddress ip, int port) throws IOException {
        s = new Socket(ip, port);
        out = new PrintWriter(s.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public void sendText(String msg) throws IOException {
        out.println(msg);
        in.readLine();
    }

    public void stopConn() throws IOException {
        in.close();
        out.close();
        s.close();
    }
}

//    public void sender(InetAddress ip, int port, String text) throws IOException {
//        if (!text.isEmpty() || !text.equals("")) {
//            Socket s = new Socket(ip, port);
//            OutputStream out = s.getOutputStream();
//            out.write(text.getBytes());
//            s.close();
//            out.flush();
//            out.close();
//        }
//    }