package agency.model;

import agency.data.Place;
import agency.data.Trip;
import agency.model.database.DbTools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class TripModel implements TripModelEventsSubscriber {
    private final DbTools database;
    private final PlaceModel placeModel;

    private final List<TripModelEvents> listeners = new ArrayList<>();

    public void addTrip(Trip trip) {
        String sql = "INSERT INTO trip (id, fromPlaceId, toPlaceId, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, trip.getId().toString());
            statement.setString(2, trip.getFrom().getId().toString());
            statement.setString(3, trip.getTo().getId().toString());
            statement.setDouble(4, trip.getPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public List<Trip> getTrips() {
        List<Trip> tripList = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery("SELECT * FROM trip");
            while (rs.next()) {
                String id = rs.getString("id");
                String fromId = rs.getString("fromPlaceId");
                String toId = rs.getString("toPlaceId");
                double price = rs.getDouble("price");

                Place from = this.placeModel.getPlace(fromId);
                Place to = this.placeModel.getPlace(toId);

                Trip trip = new Trip(UUID.fromString(id), from, to, price);


                tripList.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tripList;
    }

    @Override
    public void subscribe(TripModelEvents listener) {
        this.listeners.add(listener);
    }
}