package com.arrayprocessor.builder;

import com.arrayprocessor.entity.IntegerArray;

public class IntegerArrayBuilder {

    private int[] data;

    public IntegerArrayBuilder setData(int[] data) {
        this.data = data;
        return this;
    }

    public IntegerArray build() {
        return new IntegerArray(data);
    }
}
