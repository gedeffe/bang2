package agency;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Trip {
    private UUID id;
    private Place from;
    private Place to;
    private double price;


}
