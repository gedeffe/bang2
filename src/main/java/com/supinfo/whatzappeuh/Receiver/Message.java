package com.supinfo.whatzappeuh.Receiver;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.InetAddress;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Message {
    private final String message;
    private final InetAddress address;

}
