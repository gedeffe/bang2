package com.supinfo.recipe.ingredient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeasureUnit {
    GRAM("lang.gram"),
    MILLILITER("lang.milliliter"),
    NONE("");

    private final String name;
}
