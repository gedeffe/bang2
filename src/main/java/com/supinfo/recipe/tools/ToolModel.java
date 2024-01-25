package com.supinfo.recipe.tools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ToolModel {
    private final List<Tool> tools;

    public void addRecipe(Tool ingredient) { tools.add(ingredient); }

    public void deleteRecipe(Tool tool) {
        tools.remove(tool);
    }

    public void updateRecipe(Tool tool) {
        tools.set(tools.indexOf(tool), tool);
    }

    public Tool getIngredient(int index) {
        return tools.get(index);
    }

    public List<Tool> getIngredients() {
        return tools;
    }
}
