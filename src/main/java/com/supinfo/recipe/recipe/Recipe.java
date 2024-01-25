package com.supinfo.recipe.recipe;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Recipe {

    private final String name;
    private final String description;
    private final List<Ingredient> ingredients;
    private final List<Step> steps;
    private final List<Tool> tools;
    private final int personNumber;
    private final int duration;
    private final RecipeDifficulty difficulty;

    public void addStep(Step step) {
        steps.add(step);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}

