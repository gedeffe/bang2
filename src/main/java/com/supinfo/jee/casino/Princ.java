package com.supinfo.jee.casino;

public class Princ {

    public static void main(String[] args) {
        PartyManager partyManager = PartyManagerFactory.getPartyManager();

        System.out.println(partyManager.newParty());
    }
}
