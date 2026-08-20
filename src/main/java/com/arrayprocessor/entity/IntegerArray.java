package com.arrayprocessor.entity;

public class IntegerArray extends NumericArray {

    private final int[] data;

    public IntegerArray(int[] data) {
        this.data = data.clone();  // Клон при создании
    }

    public int[] getData() {
        return data.clone();  // Клон при получении
    }

    public int getLength() {
        return data.length;
    }

    public int get(int index) {
        return data[index];
    }
}
