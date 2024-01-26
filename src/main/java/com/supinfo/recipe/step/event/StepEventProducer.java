package com.supinfo.recipe.step.event;

import com.supinfo.recipe.common.DefaultEventProducer;
import com.supinfo.recipe.common.EventProducer;
import com.supinfo.recipe.step.Step;

import java.util.ArrayList;
import java.util.List;

public class StepEventProducer extends DefaultEventProducer<StepEventListener> implements StepEventListener {
    @Override
    public void onCreated(Step step) {
        this.getListeners().forEach(listener -> listener.onCreated(step));
    }

    @Override
    public void onDeleted(Step step) {
        this.getListeners().forEach(listener -> listener.onDeleted(step));
    }
}
