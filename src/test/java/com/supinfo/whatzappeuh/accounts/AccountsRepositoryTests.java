package com.supinfo.whatzappeuh.accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public class AccountsRepositoryTests {
    @Test
    void testCreateAccount() {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account();

        // When
        boolean created = accountsRepository.create(account);

        // Then
        Assertions.assertTrue(created);
    }

    @Test
    void testDeleteAccount() {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account();

        // When
        boolean deleted = accountsRepository.delete(account);

        // Then
        Assertions.assertTrue(deleted);
    }

    @Test
    void testRetrieveAccount() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        InetAddress address = InetAddress.getLocalHost();

        // When
        Optional<Account> accountOptional = accountsRepository.retrieve(address);

        // Then
        Assertions.assertTrue(accountOptional.isPresent());
    }

    @Test
    void testRetrieveAllWithoutAccounts() {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();

        // When
        List<Account> accountList = accountsRepository.retrieveAll();

        // Then
        Assertions.assertNotNull(accountList);
        Assertions.assertTrue(accountList.isEmpty());
    }

    @Test
    void testRetrieveAll() {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account();
        accountsRepository.create(account);

        // When
        List<Account> accountList = accountsRepository.retrieveAll();

        // Then
        Assertions.assertNotNull(accountList);
        Assertions.assertEquals(1, accountList.size());
        Assertions.assertTrue(accountList.contains(account));
    }
}
