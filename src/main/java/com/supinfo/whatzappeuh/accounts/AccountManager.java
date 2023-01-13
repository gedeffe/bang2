package com.supinfo.whatzappeuh.accounts;

import java.net.UnknownHostException;

public interface AccountManager {
    void addAccount(String ipAddress, String pseudo) throws UnknownHostException, IllegalArgumentException, AccountException;

    void removeAccount(Account accountToRemove);

}
