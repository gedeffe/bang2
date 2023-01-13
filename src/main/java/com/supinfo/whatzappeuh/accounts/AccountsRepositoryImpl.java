package com.supinfo.whatzappeuh.accounts;

import java.net.InetAddress;
import java.util.*;

public class AccountsRepositoryImpl implements AccountsRepository {

    private final Map<InetAddress, Account> accountMap = new HashMap<>();

    @Override
    public boolean create(Account newAccount) {
        boolean created;
        this.accountMap.put(newAccount.getAddress(), newAccount);
        created = true;
        this.serializeAccounts();
        return created;
    }

    @Override
    public Optional<Account> retrieve(InetAddress address) {
        return Optional.ofNullable(this.accountMap.get(address));
    }

    @Override
    public List<Account> retrieveAll() {
        // prepare a copy to avoid accidental modifications by users
        return new ArrayList<>(this.accountMap.values());
    }

    @Override
    public boolean delete(Account accountToRemove) {
        boolean deleted;
        Account removedAccount = this.accountMap.remove(accountToRemove.getAddress());
        deleted = removedAccount != null;
        this.serializeAccounts();
        return deleted;
    }

    private void serializeAccounts() {

    }
}
