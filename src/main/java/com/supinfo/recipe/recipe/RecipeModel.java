package com.supinfo.recipe.recipe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Getter
public class RecipeModel {

    private final List<Recipe> recipes;

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipes.set(recipes.indexOf(recipe), recipe);
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    public List<Recipe> listRecipes(RecipeSortType sortType) {
        return switch (sortType) {
            case NAME -> recipes.stream().sorted(Comparator.comparing(Recipe::getName)).collect(Collectors.toCollection(ArrayList::new));
            case DURATION -> recipes.stream().sorted(Comparator.comparing(Recipe::getDuration)).collect(Collectors.toCollection(ArrayList::new));
            case DIFFICULTY -> recipes.stream().sorted(Comparator.comparingInt(o -> o.getDifficulty().ordinal())).collect(Collectors.toCollection(ArrayList::new));
            default -> recipes;
        };
    }
}
