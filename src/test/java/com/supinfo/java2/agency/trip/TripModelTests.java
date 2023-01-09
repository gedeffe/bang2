package com.supinfo.java2.agency.trip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TripModelTests {

    @Test
    void testGetNextIdentifier() throws SQLException {
        // Given
        TripModel tripModel = new TripModel("jdbc:h2:mem:noId");

        // When
        Long nextIdentifier = tripModel.getNextIdentifier();

        // Then
        Assertions.assertEquals(1, nextIdentifier);
    }

    @Test
    void testGetNextIdentifierWithOneElement() throws SQLException {
        // Given
        TripModel tripModel = new TripModel("jdbc:h2:mem:existingId");
        Trip trip = new Trip();
        trip.setId(5L);
        trip.setDepartureId(46L);
        trip.setDestinationId(99L);
        trip.setPrice(59.99);
        tripModel.save(trip);

        // When
        Long nextIdentifier = tripModel.getNextIdentifier();

        // Then
        Assertions.assertEquals(6, nextIdentifier);
    }

    @Test
    void testSave() throws SQLException {
        // Given
        TripModel tripModel = new TripModel("jdbc:h2:mem:save");
        Trip trip = new Trip();
        trip.setId(5L);
        trip.setDepartureId(46L);
        trip.setDestinationId(99L);
        trip.setPrice(59.99);
        tripModel.save(trip);

        // When
        boolean saved = tripModel.save(trip);

        // Then
        Assertions.assertTrue(saved);
    }

    @Test
    void testRemove() throws SQLException {
        // Given
        TripModel tripModel = new TripModel("jdbc:h2:mem:existingId");
        Trip trip = new Trip();
        trip.setId(5L);
        trip.setDepartureId(46L);
        trip.setDestinationId(99L);
        trip.setPrice(59.99);
        tripModel.save(trip);

        // When
        boolean removed = tripModel.remove(5L);

        // Then
        Assertions.assertTrue(removed);
    }
}
