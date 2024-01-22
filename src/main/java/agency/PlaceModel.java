package agency;

import agency.database.DbTools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PlaceModel {

    private final DbTools database;

    public void addPlace(Place place) {
        String sql = "INSERT INTO place (id, name) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, place.getId().toString());
            statement.setString(2, place.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Place> getPlaces() {
        List<Place> placeList = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery("SELECT * FROM place");
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Place place = new Place(UUID.fromString(id), name);

                placeList.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return placeList;
    }
}
