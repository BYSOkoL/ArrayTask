package com.arrayprocessor.builder;

import com.arrayprocessor.entity.IntegerArray;

public class IntegerArrayBuilder {

    private String id;
    private int[] data;

    public IntegerArrayBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public IntegerArrayBuilder setData(int[] data) {
        this.data = data;
        return this;
    }

    public IntegerArray build() {
        return new IntegerArray(id, data);
    }
}
