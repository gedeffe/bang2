package com.supinfo.whatzappeuh;

import com.supinfo.whatzappeuh.accounts.AccountManager;
import com.supinfo.whatzappeuh.accounts.AccountManagerImplementation;
import com.supinfo.whatzappeuh.accounts.AccountsRepository;
import com.supinfo.whatzappeuh.accounts.AccountsRepositoryImpl;

import javax.swing.*;

public class WhatzappeuhPrincipal {

    public static void main(String[] args) {
        // prepare backend
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        AccountManager accountManager = new AccountManagerImplementation(accountsRepository);

        // prepare and launch frontend
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Whatzappeuh");

            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
