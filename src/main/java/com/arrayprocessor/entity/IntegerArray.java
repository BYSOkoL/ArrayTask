package com.arrayprocessor.entity;

public class IntegerArray extends NumericArray {

    private final int[] data;

    public IntegerArray(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }
}
