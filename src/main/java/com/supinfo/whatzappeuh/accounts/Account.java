package com.supinfo.whatzappeuh.accounts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.InetAddress;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Account {
    private final String pseudo;
    private final InetAddress address;
}
