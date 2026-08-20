package com.arrayprocessor.factory;

import com.arrayprocessor.builder.IntegerArrayBuilder;
import com.arrayprocessor.entity.IntegerArray;

public class NumericArrayFactory {

    public IntegerArray createIntegerArray(int[] data) {
        IntegerArrayBuilder builder = new IntegerArrayBuilder();
        builder.setData(data);
        IntegerArray array = builder.build();
        return array;
    }
}