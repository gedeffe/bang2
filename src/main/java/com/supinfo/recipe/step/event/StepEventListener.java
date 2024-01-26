package com.supinfo.recipe.step.event;

import com.supinfo.recipe.step.Step;

public interface StepEventListener {
    public void onCreated(Step step);
    public void onDeleted(Step step);
}
