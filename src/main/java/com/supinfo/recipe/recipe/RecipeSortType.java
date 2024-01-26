package com.supinfo.recipe.recipe;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum RecipeSortType {
    NAME("name"),
    DURATION("duration"),
    DIFFICULTY("difficulty");

    private final String name;

    public static RecipeSortType fromString(String name) {
        for (RecipeSortType type : RecipeSortType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
