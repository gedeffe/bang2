package com.supinfo.java2.agency.place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceModel implements PlaceEventsSubscriber {

    private final Connection connection;
    private final List<PlaceEvents> listeners = new ArrayList<>();

    public PlaceModel() {
        this("jdbc:h2:./build/places;AUTO_SERVER=TRUE");
    }

    PlaceModel(String url) {
        try {
            // load H2 driver
            Class.forName("org.h2.Driver");
            // Start database connection
            this.connection = DriverManager.getConnection(url, "sa", "");
            try (Statement statement = connection.createStatement()) {
                // create our table structure (if needed) to store our text
                String sqlCreateCommand = "CREATE TABLE IF NOT EXISTS place (id INT NOT NULL, name VARCHAR(1500) NOT NULL)";
                statement.executeUpdate(sqlCreateCommand);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Unable to connect to places database !");
            throw new RuntimeException(e);
        }
    }

    public boolean save(Place place) throws SQLException {
        boolean saved;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO place (id, name) VALUES (?,?)")) {
            preparedStatement.setLong(1, place.getId());
            preparedStatement.setString(2, place.getName());
            saved = preparedStatement.executeUpdate() > 0;
            this.connection.commit();
            this.listeners.forEach(listener -> listener.notifyPlaceAdded(place));
        } catch (SQLException e) {
            this.connection.rollback();
            throw e;
        }
        return saved;
    }


    public boolean update(Place place) {
        return false;
    }

    public Long getNextIdentifier() throws SQLException {
        long next = 1;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM place ORDER BY id DESC FETCH FIRST 1 ROWS ONLY");
            while (resultSet.next()) {
                next = resultSet.getLong(1) + 1;
            }
        }
        return next;
    }

    public List<Place> findAll() throws SQLException {
        List<Place> places = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM place")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Place place = new Place();
                place.setId(resultSet.getLong("id"));
                place.setName(resultSet.getString("name"));
                places.add(place);
            }
        }
        return places;
    }

    public boolean remove(long placeId) throws SQLException {
        boolean removed;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM place WHERE id=?")) {
            preparedStatement.setLong(1, placeId);
            removed = preparedStatement.executeUpdate() > 0;
            this.connection.commit();
            Place fakeRemovedPlace = new Place();
            fakeRemovedPlace.setId(placeId);
            this.listeners.forEach(listener -> listener.notifyPlaceRemoved(fakeRemovedPlace));
        } catch (SQLException e) {
            this.connection.rollback();
            throw e;
        }
        return removed;
    }

    @Override
    public void subscribe(PlaceEvents listener) {
        this.listeners.add(listener);
    }

    @Override
    public void unsubscribe(PlaceEvents listener) {
        this.listeners.remove(listener);
    }
}
