package com.supinfo.recipe.tools.event;

import com.supinfo.recipe.common.DefaultEventProducer;
import com.supinfo.recipe.common.EventProducer;
import com.supinfo.recipe.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class ToolEventProducer extends DefaultEventProducer<ToolEventListener> implements ToolEventListener {
    @Override
    public void onCreated(Tool tool) {
        this.getListeners().forEach(listener -> listener.onCreated(tool));
    }

    @Override
    public void onDeleted(Tool tool) {
        this.getListeners().forEach(listener -> listener.onDeleted(tool));
    }
}
