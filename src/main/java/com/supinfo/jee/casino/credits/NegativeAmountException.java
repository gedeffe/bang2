package com.supinfo.jee.casino.credits;

public class NegativeAmountException extends Throwable {
    public NegativeAmountException() {
        super("Amount attribute should have a positive value !");
    }

    public NegativeAmountException(int amount, String pseudo) {
        super("Amount attribute should have a positive value ! (amount: " + amount);
    }

}
