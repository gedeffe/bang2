package com.supinfo.recipe.step;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class StepModel {
    private final List<Step> steps;

    public void addStep(Step step) { steps.add(step); }

    public void deleteStep(Step step) {
        steps.remove(step);
    }

    public void updateStep(Step step) {
        steps.set(steps.indexOf(step), step);
    }

    public Step getStep(int index) {
        return steps.get(index);
    }

    public List<Step> getSteps() {
        return steps;
    }
}
