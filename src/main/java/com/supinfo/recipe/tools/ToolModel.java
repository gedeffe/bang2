package com.supinfo.recipe.tools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ToolModel {
    private final List<Tool> tools;

    public void addTool(Tool ingredient) { tools.add(ingredient); }

    public void deleteTool(Tool tool) {
        tools.remove(tool);
    }

    public void updateTool(Tool tool) {
        tools.set(tools.indexOf(tool), tool);
    }

    public Tool getTool(int index) {
        return tools.get(index);
    }

    public List<Tool> getTools() {
        return tools;
    }
}
