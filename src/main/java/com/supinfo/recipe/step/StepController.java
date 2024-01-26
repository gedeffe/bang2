package com.supinfo.recipe.step;

import com.supinfo.recipe.step.event.StepEventProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StepController {
    private final StepModel model;
    private final StepEventProducer eventProducer;

    public void addStep(Step step) {
        this.model.add(step);
        this.eventProducer.onCreated(step);
    }

    public void deleteStep(Step step) {
        this.model.delete(step);
        this.eventProducer.onDeleted(step);
    }
}
