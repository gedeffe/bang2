package com.supinfo.whatzappeuh.accounts;

import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountsRepositoryImpl implements AccountsRepository {
    @Override
    public boolean create(Account newAccount) {
        return false;
    }

    @Override
    public Optional<Account> retrieve(InetAddress address) {
        return Optional.empty();
    }

    @Override
    public List<Account> retrieveAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean delete(Account accountToRemove) {
        return false;
    }
}
