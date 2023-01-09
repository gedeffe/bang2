package com.supinfo.java2.agency;

import com.supinfo.java2.agency.place.PlaceController;
import com.supinfo.java2.agency.trip.TripController;

import java.util.Scanner;

public class AgencyMain {

    public static void main(String[] args) {
        AgencyMain agencyMain = new AgencyMain();

        System.out.println("Welcome aboard !");
        while (agencyMain.actions()) {
            // continue ...
        }
    }

    public boolean actions() {
        boolean stay = true;

        this.displayChoices();

        int choice = this.waitForUserInput();

        if (choice == 8) {
            stay = false;
        } else {
            if (choice > 0 && choice < 5) {
                this.managePlace(choice);
            } else if (choice > 4 && choice < 8) {
                this.manageTrip(choice);
            } else {
                System.out.println("Unknown command...");
            }
        }
        return stay;
    }

    private void manageTrip(int choice) {
        TripController tripController = new TripController();
        tripController.handleAction(choice);
    }

    private void managePlace(int choice) {
        PlaceController placeController = new PlaceController();
        placeController.handleAction(choice);
    }

    private int waitForUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void displayChoices() {
        System.out.println("What do you want to do ?");
        System.out.println("1 - Add a place");
        System.out.println("2 - List every places");
        System.out.println("3 - Edit a place");
        System.out.println("4 - Remove a place");
        System.out.println("5 - Add a trip");
        System.out.println("6 - List every trips");
        System.out.println("7 - Remove a trip");
        System.out.println("8 - Quit");
    }
}
