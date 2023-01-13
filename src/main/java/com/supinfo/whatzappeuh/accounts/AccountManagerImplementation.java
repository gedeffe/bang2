package com.supinfo.whatzappeuh.accounts;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class AccountManagerImplementation implements AccountManager {

    private final AccountsRepository accountRepository;

    public AccountManagerImplementation(AccountsRepository accountRepositoryParam) {
        this.accountRepository = accountRepositoryParam;
    }

    @Override
    public void addAccount(String ipAddress, String pseudo) throws UnknownHostException, IllegalArgumentException, AccountException {
        if (ipAddress == null || ipAddress.isBlank()) {
            throw new IllegalArgumentException("Missing value for : IP Address");
        } else if (pseudo == null || pseudo.isBlank()) {
            throw new IllegalArgumentException("Missing value for : pseudo");
        }
        InetAddress address = InetAddress.getByAddress(ipAddress.getBytes());

        // check duplication
        List<Account> accountList = this.accountRepository.retrieveAll();
        for (Account account : accountList) {
            if (address.equals(account.getAddress())) {
                throw new AccountException("Address " + ipAddress + " has been already used by " + account.getPseudo());
            } else if (pseudo.equals(account.getPseudo())) {
                throw new AccountException("Pseudo " + pseudo + " has been already used with " + account.getAddress());
            }
        }

        Account account = new Account(pseudo, address);

        this.accountRepository.create(account);
    }

    @Override
    public void removeAccount(Account accountToRemove) {
        this.accountRepository.delete(accountToRemove);
    }
}
