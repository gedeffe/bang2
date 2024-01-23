package com.supinfo.recipe;

public class Recipe {

    private final String name;
    private final String description;
    private final String image;
    private final String[] ingredients;
    private final String[] steps;
    private final int personNumber;
    private final int duration;
    private final RecipeDifficulty difficulty;

    public Recipe(String name, String description, String image, String[] ingredients, String[] steps, int personNumber, int duration, RecipeDifficulty difficulty) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
        this.personNumber = personNumber;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getSteps() {
        return steps;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public int getDuration() {
        return duration;
    }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }
}
