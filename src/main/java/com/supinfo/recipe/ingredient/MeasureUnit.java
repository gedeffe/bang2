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

    private static String[] getAll() {
        MeasureUnit[] units = MeasureUnit.values();
        String[] names = new String[units.length];
        for (int i = 0; i < units.length; i++) {
            names[i] = units[i].getName();
        }
        return names;
    }
}
