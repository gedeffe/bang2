package com.supinfo.whatzappeuh.sendGraphique;

import javax.swing.*;
import java.awt.*;

public class UserSendingMessageInterface {

    static JFrame frame;
    private JButton sendButton;

    private JTextField messageField;

    private JPanel panelMessageInterface;


    public  JPanel sendingMessageInterface(){
        if (this.panelMessageInterface == null){
            this.panelMessageInterface =  new JPanel();
            //this.panelMessageInterface.setLayout(new FlowLayout());

            UserSendingMessageInterface placeView = new UserSendingMessageInterface();


            this.messageField = (new JTextField(""));
            //this.messageField.setBounds();
            this.messageField.setColumns(20);
            this.panelMessageInterface.add(this.messageField, BorderLayout.CENTER);

            this.sendButton = (new JButton(("send")));
            this.panelMessageInterface.add(this.sendButton, BorderLayout.EAST);

            //this.panelMessageInterface.setSize();

            this.panelMessageInterface.setVisible(true);



        }


        return this.panelMessageInterface;


    }



}
/*
package com.supinfo.java2.agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphique implements ActionListener {
    static JFrame frame;
    public JButton add_tripe;
    private JTextField placeNameTextField;
    private JPanel panel1;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;

    private JMenu menu;
    private JMenuBar menu1;

    private MenuElement test;

    public JPanel getmenue() {
        this.panel2 = new JPanel();
        menu.setLayout(new GridLayout(0, 1));

        return panel2;
    }

    public JPanel getRootPanel() {

        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;

        this.add_tripe = (new JButton("Add TRIP"));
        this.add_tripe.addActionListener(this);
        jp.add(this.add_tripe);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridy = 0;
        jp.add(new JButton("Add place"), gbc);


        return jp;
    }

    public JPanel getTab() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        jp.add(new JButton("Add place"), gbc);

        Object[][] donnees = {
                {"Johnathan", "Sykes"},
                {"Nicolas", "Van de Kampf"},
                {"Damien", "Cuthbert"},
                {"Corinne", "Valance"},
                {"Emilie", "Schrödinger"},
                {"Delphine", "Duke"},
                {"Eric", "Trump"},
        };

        String[] entetes = {"ID", "Dépard", "Arriver", "Price"};

        JTable tableau = new JTable(donnees, entetes);
        jp.add(tableau);


        return jp;
    }

    public JDialog add_trajet(Frame frame, ActionEvent e) {
        String s = e.getActionCommand();
        JDialog d = null;
        if (s.equals("Add place")) {
            // Créer une boîte de dialogue
            d = new JDialog(frame, "add trajet");
            // Créer une étiquette
            JLabel l = new JLabel("Ceci est une boite de dialogue.");
            // Ajouter l'étiquette à la boîte de dialogue
            d.add(l);
            // Définir la taille de la boîte de dialogue
            d.setSize(200, 100);
            // Définir la visibilité de la boîte de dialogue

        }
        return d;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.add_tripe) ;
        {
            // Créer une boîte de dialogue
            JDialog d = new JDialog(frame, "add trajet");
            // Créer une étiquette


            d.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridwidth = 0;
            gbc.gridheight = 5;
            gbc.gridy = 0;

            d.add(new JLabel("Ajouter un nouveau trajet"), gbc);

            //d.add(l);
            JTextField aller = new JTextField();
            JTextField retour = new JTextField();
            d.add(aller);
            d.add(retour);

            gbc.gridx = 1;
            gbc.gridwidth = 0;
            gbc.gridheight = 2;
            gbc.gridy = 5;
            aller.setSize(200, 20);

            d.add(aller, gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 0;
            gbc.gridheight = 2;
            gbc.gridy = 10;
            retour.setSize(200, 20);

            d.add(retour, gbc);

            // Définir la taille de la boîte de dialogue
            d.setSize(800, 1000);
            // Définir la visibilité de la boîte de dialogue
            d.setVisible(true);
        }

    }


}




 */