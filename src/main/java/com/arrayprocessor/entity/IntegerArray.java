package com.arrayprocessor.entity;

public class IntegerArray extends NumericArray {

    private final String id;
    private final int[] data;

    public IntegerArray(String id, int[] data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public int[] getData() {
        return data;
    }
}
