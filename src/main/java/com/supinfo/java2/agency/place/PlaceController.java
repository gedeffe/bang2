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
            default -> {
            }
        }
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
        try {
            Place place = new Place();
            place.setName(name);
            place.setId(placeModel.getNextIdentifier());
            this.placeModel.save(place);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Unable to save place !!!");
        }
    }
}
