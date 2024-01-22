package agency.control;

import agency.data.Place;
import agency.model.PlaceModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@RequiredArgsConstructor
public class PlaceController {

    private final PlaceModel placeModel;

    public void createPlace(String text) {
        if (StringUtils.isNotEmpty(text)) {

            Place place = new Place(UUID.randomUUID(), text);

            this.placeModel.addPlace(place);
        }
    }
}
