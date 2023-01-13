package com.supinfo.whatzappeuh.accounts;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public class AccountsRepositoryTests {

    @BeforeEach
    void setup() {
        // Clean up temporary file which store accounts information
        // We reuse same path than location in file src/test/resources/configuration.properties
        File file = new File("./build/accounts.xml");
        if (file.exists()) {
            Assertions.assertTrue(file.delete());
        }
        Assertions.assertFalse(file.exists());
    }

    @Test
    void testCreateAccount() throws IOException {
        // Given
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        Account account = new Account("yolo", InetAddress.getLocalHost());

        // When
        boolean created = accountsRepository.create(account);

        // Then
        Assertions.assertTrue(created);
        File file = new File("./build/accounts.xml");
        InputStream inputStream = new FileInputStream(file);
        InputStream inputStreamExpectations = this.getClass().getResourceAsStream("/accounts/accounts.xml");
        Assertions.assertTrue(IOUtils.contentEquals(inputStream, inputStreamExpectations));
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
