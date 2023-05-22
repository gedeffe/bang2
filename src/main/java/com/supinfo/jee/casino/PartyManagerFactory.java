package com.supinfo.jee.casino;

public class PartyManagerFactory {

    public static PartyManager getPartyManager() {
        return new PartyManagerImpl();
    }
}
