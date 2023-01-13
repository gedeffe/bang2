package com.supinfo.whatzappeuh.send;

import java.io.IOException;
import java.net.InetAddress;


public interface Sender {
    void sender(InetAddress ip, int port, String text) throws IOException;
}
