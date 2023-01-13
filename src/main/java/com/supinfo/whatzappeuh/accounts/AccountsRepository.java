package com.supinfo.whatzappeuh.accounts;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;

public interface AccountsRepository {
    /**
     * Register a new account, so it will be available for other components.
     *
     * @param newAccount element to add.
     * @return true if created with success.
     */
    boolean create(Account newAccount);

    /**
     * Retrieve account associated to provided IP address.
     *
     * @param address IP address to filter our list of accounts.
     * @return an account if we know it, otherwise an empty element.
     */
    Optional<Account> retrieve(InetAddress address);

    /**
     * Retrieve full list of known accounts.
     *
     * @return list of accounts, could be empty but never null.
     */
    List<Account> retrieveAll();

    /**
     * To remove an account from our list of known accounts.
     *
     * @param accountToRemove element to delete.
     * @return true if deleted successfully.
     */
    boolean delete(Account accountToRemove);
}
