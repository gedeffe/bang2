package agency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Place {

    private final UUID id;
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }
}
