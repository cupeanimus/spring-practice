package com.kyle.springpractice.practice.booleanget;

import lombok.Getter;

@Getter
public class BooleanGet {
    private boolean primitiveBoolean;
    private Boolean classBoolean;

    public BooleanGet(boolean primitiveBoolean, Boolean classBoolean) {
        this.primitiveBoolean = primitiveBoolean;
        this.classBoolean = classBoolean;
    }
}
