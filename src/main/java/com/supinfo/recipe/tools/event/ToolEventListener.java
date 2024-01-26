package com.supinfo.recipe.tools.event;

import com.supinfo.recipe.tools.Tool;

public interface ToolEventListener {
    public void onCreated(Tool tool);
    public void onDeleted(Tool tool);
}
