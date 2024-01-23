package ingredient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Ingredient {
    private final String name;
    private final double quantity;
    private final MeasureUnit unit;
}
