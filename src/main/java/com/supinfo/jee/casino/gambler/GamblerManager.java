package com.supinfo.jee.casino.gambler;

public interface GamblerManager {

    Gambler getGambler(String pseudo);

    Gambler creditBalance(String pseudo, int amount);

    Gambler playGame(String pseudo, int initialValue, int bet, int numberOfLaunch);

    void authenticateGambler(String pseudo, String password);
}

