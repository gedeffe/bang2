package com.supinfo.jee.casino.gambler;

import lombok.Getter;

@Getter
public class WrongBalanceException extends RuntimeException {
    private final long balance;
    private final String pseudo;

    public WrongBalanceException(long balanceParam, String pseudoParam) {
        super("Please, pay to play ! Your balance is " + balanceParam + " €");
        this.balance = balanceParam;
        this.pseudo = pseudoParam;
    }
}
