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
            // Frame
            JFrame frame = new JFrame("Whatzappeuh");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            MenuBar menuBar = new MenuBar();
            menuBar.display(frame);
            frame.setVisible(true);
        });
    }
}
