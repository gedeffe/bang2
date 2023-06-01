package com.supinfo.jee.casino.gambler;

public interface GamblerManager {

    Gambler getGambler(String pseudo);

    Gambler creditBalance(String pseudo, int amount);
}
