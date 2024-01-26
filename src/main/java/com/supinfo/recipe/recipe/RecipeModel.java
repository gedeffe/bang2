package com.supinfo.recipe.recipe;

import com.supinfo.recipe.recipe.event.RecipeEventProducer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class RecipeModel extends RecipeEventProducer {

    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        this.onCreated(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        this.onDeleted(recipe);
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
