package com.supinfo.java2.agency.place;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class PlaceModelTests {

    @Test
    void testGetNextIdentifier() throws SQLException {
        // Given
        PlaceModel placeModel = new PlaceModel("jdbc:h2:mem:noId");

        // When
        Long nextIdentifier = placeModel.getNextIdentifier();

        // Then
        Assertions.assertEquals(1, nextIdentifier);
    }

    @Test
    void testGetNextIdentifierWithOneElement() throws SQLException {
        // Given
        PlaceModel placeModel = new PlaceModel("jdbc:h2:mem:existingId");
        Place place = new Place();
        place.setId(5L);
        place.setName("truc");
        placeModel.save(place);

        // When
        Long nextIdentifier = placeModel.getNextIdentifier();

        // Then
        Assertions.assertEquals(6, nextIdentifier);
    }

    @Test
    void testSave() throws SQLException {
        // Given
        PlaceModel placeModel = new PlaceModel("jdbc:h2:mem:save");
        Place place = new Place();
        place.setId(1L);
        place.setName("truc");

        // When
        boolean saved = placeModel.save(place);

        // Then
        Assertions.assertTrue(saved);
    }
}
