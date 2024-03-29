package com.supinfo.recipe.recipe;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Recipe {

    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private List<Tool> tools;
    private int personNumber;
    private int duration;
    private RecipeDifficulty difficulty;

    public Recipe() {
        this.name = "";
        this.description = "";
        this.steps = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        this.personNumber = 0;
        this.duration = 0;
        this.difficulty = RecipeDifficulty.EASY;
    }

    public Recipe(String name,
                  String description,
                  List<Ingredient> ingredients,
                  List<Step> steps,
                  List<Tool> tools,
                  int personNumber,
                  int duration,
                  RecipeDifficulty difficulty) {
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.tools = tools;
        this.ingredients = ingredients;
        this.personNumber = personNumber;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }
}

