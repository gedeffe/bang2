package com.supinfo.jee.casino.user;

public interface GamblerManager {
    void addUser(Gambler user);

    Gambler getUser(String pseudo);
}
