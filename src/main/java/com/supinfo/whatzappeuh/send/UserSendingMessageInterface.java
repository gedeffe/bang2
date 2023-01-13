package com.supinfo.whatzappeuh.send;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserSendingMessageInterface implements ActionListener {

    static JDialog jDialog;
    private JButton sendButton;

    private JTextField messageField;

    private JPanel panelMessageInterface;

    private JPanel panelMessageError;

    private JLabel labelMessageError;


    private JButton errorButton;


    public JPanel sendingMessageInterface() {
        if (this.panelMessageInterface == null) {
            this.panelMessageInterface = new JPanel();

            UserSendingMessageInterface placeView = new UserSendingMessageInterface();


            this.messageField = (new JTextField(""));
            this.messageField.setColumns(30);
            this.panelMessageInterface.add(this.messageField, BorderLayout.CENTER);

            this.sendButton = (new JButton(("send")));
            this.sendButton.addActionListener(this); //remplacer l'action par celle souhaitez
            this.panelMessageInterface.add(this.sendButton, BorderLayout.EAST);


            this.panelMessageInterface.setVisible(true);


        }


        return this.panelMessageInterface;


    }

    public void userInterfaceError(String errorMessage) {
        this.jDialog = new JDialog();
        this.panelMessageError = new JPanel();

        this.labelMessageError = new JLabel(errorMessage); // Mettre en parametre le message d'erreur

        this.errorButton = new JButton("Ok");


        this.panelMessageError.add(this.labelMessageError, BorderLayout.NORTH);
        this.panelMessageError.add(this.errorButton, BorderLayout.SOUTH);

        this.jDialog.add(this.panelMessageError);
        this.jDialog.setSize(300, 100);
        this.jDialog.setVisible(true);

        //return this.jDialog;
    }

    public void actionPerformed(ActionEvent e/*, String errorMessage*/) {
        Object source = e.getSource();

    }

}






