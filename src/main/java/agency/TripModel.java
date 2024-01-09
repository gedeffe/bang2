package agency;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TripModel {

    private final List<Trip> trips = new ArrayList<>();
}
