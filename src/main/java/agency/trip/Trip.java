package agency.trip;

import agency.place.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private UUID id;
    private Place from;
    private Place to;
    private double price;

}
