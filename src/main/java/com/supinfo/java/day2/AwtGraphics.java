package com.supinfo.java.day2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;

/**
 * Exercise 3 : Graphics with  AWT & Swing
 * <p>
 * 3.1 : Using AWT, write a java program displaying a button with a label : "I'm a button"
 * <p>
 * 3.2 : Add a red colored circle and a green rectangle
 */
public class AwtGraphics {

    public static void main(final String[] args) {
        final AwtGraphics awtGraphics = new AwtGraphics();

        awtGraphics.showFrame();
    }

    public void showFrame() {
        final Frame mainFrame = new Frame("AWT Graphics (excercice 3)");
        // size should be big enough to display our inner elements
        mainFrame.setSize(400, 800);
        mainFrame.setLayout(new GridLayout(3, 1));
        // to be able to close the window and exit the program !
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        // button
        final Button button = new Button("I'm a button");

        // red circle (to be draw inside a panel)
        final Panel circlePanel = new Panel() {
            @Override
            public void paint(final Graphics g) {
                final Graphics2D g2 = (Graphics2D) g;
                final Shape circle = new Ellipse2D.Double(100, 100, 100, 100);
                g2.setColor(Color.RED);
                g2.fill(circle);
            }
        };

        // green rectangle (to be draw inside a panel)
        final Panel rectanglePanel = new Panel() {
            @Override
            public void paint(final Graphics g) {
                final Graphics2D g2 = (Graphics2D) g;
                final Shape rectangle = new Rectangle(3, 3, 303, 303);
                g2.setColor(Color.GREEN);
                g2.fill(rectangle);
            }
        };
        mainFrame.add(button);
        mainFrame.add(circlePanel);
        mainFrame.add(rectanglePanel);
        mainFrame.setVisible(true);
    }
}
