package com.supinfo.java2.agency.place;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlaceController {
    private final PlaceModel placeModel = new PlaceModel();

    public void handleAction(int choice) {
        switch (choice) {
            case 1 -> this.addPlace();
            case 2 -> this.listPlaces();
            case 3 -> this.editPlace();
            case 4 -> this.removePlace();
            default -> {
            }
        }
    }

    private void removePlace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter identifier of place to remove:");
        int identifier = scanner.nextInt();
        try {
            this.placeModel.remove(identifier);
        } catch (SQLException e) {
            System.err.println("Unable to remove place !!!");
        }
    }

    private void editPlace() {
    }

    private void listPlaces() {
        try {
            List<Place> places = this.placeModel.findAll();
            for (Place place : places) {
                System.out.println(place.toString());
            }
        } catch (SQLException e) {
            System.err.println("Unable to list places !!!");
        }
    }

    private void addPlace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        this.addPlace(name);
    }

    public void addPlace(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name should not be empty !");
        }
        try {
            Place place = new Place();
            place.setName(name);
            place.setId(placeModel.getNextIdentifier());
            this.placeModel.save(place);
        } catch (SQLException e) {
            System.err.println("Unable to save place !!!");
        }
    }
}
