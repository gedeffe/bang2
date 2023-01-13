package com.supinfo.whatzappeuh;

import com.supinfo.whatzappeuh.accounts.AccountsRepository;
import com.supinfo.whatzappeuh.accounts.AccountsRepositoryImpl;
import com.supinfo.whatzappeuh.send.UserSendingMessageInterface;

import javax.swing.*;

public class WhatzappeuhPrincipal {


    public static void main(String[] args) {
        // prepare backend
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();

        // prepare and launch frontend
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Whatzappeuh");
            UserSendingMessageInterface placeView = new UserSendingMessageInterface();

            frame.add(placeView.sendingMessageInterface());

            frame.setSize(600, 200);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
