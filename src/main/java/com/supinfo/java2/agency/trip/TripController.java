package com.supinfo.java2.agency.trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripController {

    private final TripModel tripModel = new TripModel();

    public void handleAction(int choice) {
        switch (choice) {
            case 5 -> this.addTrip();
            case 6 -> this.listTrips();
            case 7 -> this.removeTrip();
            default -> {
            }
        }
    }

    private void removeTrip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter identifier of trip to remove:");
        int identifier = scanner.nextInt();
        try {
            this.tripModel.remove(identifier);
        } catch (SQLException e) {
            System.err.println("Unable to remove trip !!!");
        }
    }

    private void listTrips() {
        try {
            List<Trip> trips = this.tripModel.findAll();
            for (Trip trip : trips) {
                System.out.println(trip.toString());
            }
        } catch (SQLException e) {
            System.err.println("Unable to list trips !!!");
        }
    }

    private void addTrip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter departure identifier:");
        int departureId = scanner.nextInt();
        System.out.println("Enter destination identifier:");
        int destinationId = scanner.nextInt();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        try {
            Trip trip = new Trip();
            trip.setId(tripModel.getNextIdentifier());
            trip.setDepartureId((long) departureId);
            trip.setDestinationId((long) destinationId);
            trip.setPrice(price);
            this.tripModel.save(trip);
        } catch (SQLException e) {
            System.err.println("Unable to save trip !!!");
        }
    }
}
