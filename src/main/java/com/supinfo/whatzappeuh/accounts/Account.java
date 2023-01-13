package com.supinfo.whatzappeuh.accounts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;

@Getter
@Setter
@EqualsAndHashCode
public class Account {

    private String pseudo;
    private InetAddress address;
}
