package com.tonyprr.model;

import java.util.List;

public class ResultApply {
    private boolean applied;
    private List<Integer> values;

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
