package com.supinfo.recipe.step;

import com.supinfo.recipe.common.DataModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StepModel implements DataModel<Step> {
    private final List<Step> steps = new ArrayList<>();

    @Override
    public void add(Step item) {
        this.steps.add(item);
    }

    @Override
    public void delete(Step item) {
        this.steps.remove(item);
    }

    @Override
    public void update(Step item) {
        this.steps.set(this.steps.indexOf(item), item);
    }

    @Override
    public Step get(int index) {
        return this.steps.get(index);
    }

    @Override
    public Iterator<Step> iterator() {
        return this.steps.iterator();
    }
}
