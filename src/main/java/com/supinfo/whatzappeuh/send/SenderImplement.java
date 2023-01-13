package com.supinfo.whatzappeuh.send;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SenderImplement implements Sender {

    @Override
    public void sender(InetAddress ip, int port, String text) throws IOException {
        Socket s = new Socket(ip, port);
        OutputStream out = s.getOutputStream();
        out.write(text.getBytes());
        s.close();
        out.flush();
        out.close();
    }
}
