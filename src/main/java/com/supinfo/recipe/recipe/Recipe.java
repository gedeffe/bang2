package com.supinfo.recipe.recipe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Recipe {

    private final String name;
    private final String description;
    private final String image;
    private final String[] ingredients;
    private final String[] steps;
    private final int personNumber;
    private final int duration;
    private final RecipeDifficulty difficulty;

}
