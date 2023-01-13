package com.supinfo.whatzappeuh.accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public class AccountsRepositoryTests {
    @Test
    void testCreateAccount() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account("yolo", InetAddress.getLocalHost());

        // When
        boolean created = accountsRepository.create(account);

        // Then
        Assertions.assertTrue(created);
    }

    @Test
    void testDeleteAccount() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account("yolo", InetAddress.getLocalHost());
        accountsRepository.create(account);

        // When
        boolean deleted = accountsRepository.delete(account);

        // Then
        Assertions.assertTrue(deleted);
    }

    @Test
    void testRetrieveAccount() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        String pseudo = "yolo";
        InetAddress address = InetAddress.getLocalHost();
        Account account = new Account(pseudo, address);
        accountsRepository.create(account);

        // When
        Optional<Account> accountOptional = accountsRepository.retrieve(address);

        // Then
        Assertions.assertTrue(accountOptional.isPresent());
        Assertions.assertEquals(account, accountOptional.get());
    }

    @Test
    void testRetrieveAccountWhenUnknown() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        InetAddress address = InetAddress.getLocalHost();

        // When
        Optional<Account> accountOptional = accountsRepository.retrieve(address);

        // Then
        Assertions.assertTrue(accountOptional.isEmpty());
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
    void testRetrieveAll() throws UnknownHostException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account("yolo", InetAddress.getLocalHost());
        accountsRepository.create(account);

        // When
        List<Account> accountList = accountsRepository.retrieveAll();

        // Then
        Assertions.assertNotNull(accountList);
        Assertions.assertEquals(1, accountList.size());
        Assertions.assertTrue(accountList.contains(account));
    }
}
