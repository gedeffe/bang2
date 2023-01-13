package com.supinfo.whatzappeuh.accounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AccountsRepositoryImpl implements AccountsRepository {

    private final Map<InetAddress, Account> accountMap = new HashMap<>();
    private String accountsPath;

    public AccountsRepositoryImpl() {
        retrieveAccountsPath();
        this.deserializeAccountsInformation();
    }


    @Override
    public boolean create(Account newAccount) {
        boolean created;
        this.accountMap.put(newAccount.getAddress(), newAccount);
        created = true;
        this.serializeAccountsInformation();
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
        this.serializeAccountsInformation();
        return deleted;
    }

    private void serializeAccountsInformation() {
        Properties properties = new Properties();
        for (Map.Entry<InetAddress, Account> mapEntry : this.accountMap.entrySet()) {
            InetAddress address = mapEntry.getKey();
            Account account = mapEntry.getValue();
            properties.put(address.getHostAddress(), account.getPseudo());
        }
        try (FileOutputStream fileInputStream = new FileOutputStream(this.accountsPath)) {
            properties.storeToXML(fileInputStream, "information about senders");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void retrieveAccountsPath() {
        try {
            // retrieve path to serialized accounts information
            URL resource = ClassLoader.getSystemClassLoader().getResource("configuration.properties");
            URI uri = resource.toURI();
            Path path = Path.of(uri);
            this.accountsPath = Files.readString(path);
        } catch (IOException | URISyntaxException e) {
            // use default path
            this.accountsPath = "accounts.xml";
        }
    }

    private void deserializeAccountsInformation() {
        // check file existenz
        File file = new File(this.accountsPath);
        if (file.exists()) {
            // load information
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(this.accountsPath)) {
                properties.loadFromXML(fileInputStream);
                properties.forEach((keyAddress, pseudoValue) -> {
                    try {
                        InetAddress address = InetAddress.getByName((String) keyAddress);
                        String pseudo = (String) pseudoValue;
                        Account account = new Account(pseudo, address);
                        this.accountMap.put(address, account);
                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
