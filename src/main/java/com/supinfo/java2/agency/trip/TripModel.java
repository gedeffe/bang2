package com.supinfo.java2.agency.trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripModel implements TripEventsSubscriber {

    private final Connection connection;
    private final List<TripEvents> listeners = new ArrayList<>();

    public TripModel() {
        this("jdbc:h2:./build/trips;AUTO_SERVER=TRUE");
    }

    TripModel(String url) {
        try {
            // load H2 driver
            Class.forName("org.h2.Driver");
            // Start database connection
            this.connection = DriverManager.getConnection(url, "sa", "");
            try (Statement statement = connection.createStatement()) {
                // create our table structure (if needed) to store our text
                String sqlCreateCommand = "CREATE TABLE IF NOT EXISTS trip (id INT NOT NULL, departureId INT NOT NULL, destinationId INT NOT NULL, price DOUBLE PRECISION NOT NULL)";
                statement.executeUpdate(sqlCreateCommand);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Unable to connect to trips database !");
            throw new RuntimeException(e);
        }
    }

    public Long getNextIdentifier() throws SQLException {
        long next = 1;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM trip ORDER BY id DESC FETCH FIRST 1 ROWS ONLY");
            while (resultSet.next()) {
                next = resultSet.getLong(1) + 1;
            }
        }
        return next;
    }

    public boolean save(Trip trip) throws SQLException {
        boolean saved;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO trip (id, departureId, destinationId, price) VALUES (?,?,?,?)")) {
            preparedStatement.setLong(1, trip.getId());
            preparedStatement.setLong(2, trip.getDepartureId());
            preparedStatement.setLong(3, trip.getDestinationId());
            preparedStatement.setDouble(4, trip.getPrice());
            saved = preparedStatement.executeUpdate() > 0;
            this.connection.commit();
            this.listeners.forEach(listener -> listener.notifyTripAdded(trip));
        } catch (SQLException e) {
            this.connection.rollback();
            throw e;
        }
        return saved;
    }

    public boolean remove(long tripId) throws SQLException {
        boolean removed;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM trip WHERE id=?")) {
            preparedStatement.setLong(1, tripId);
            removed = preparedStatement.executeUpdate() > 0;
            this.connection.commit();
            Trip fakeRemovedTrip = new Trip();
            fakeRemovedTrip.setId(tripId);
            this.listeners.forEach(listener -> listener.notifyTripRemoved(fakeRemovedTrip));
        } catch (SQLException e) {
            this.connection.rollback();
            throw e;
        }
        return removed;
    }

    public List<Trip> findAll() throws SQLException {
        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM trip")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getLong("id"));
                trip.setDepartureId(resultSet.getLong("departureId"));
                trip.setDestinationId(resultSet.getLong("destinationId"));
                trip.setPrice(resultSet.getDouble("price"));
                trips.add(trip);
            }
        }
        return trips;
    }

    @Override
    public void subscribe(TripEvents listener) {
        this.listeners.add(listener);
    }
}
