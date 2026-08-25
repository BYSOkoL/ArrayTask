package com.arrayprocessor.factory;

import com.arrayprocessor.builder.IntegerArrayBuilder;
import com.arrayprocessor.entity.IntegerArray;

public class NumericArrayFactory {

    public IntegerArray createIntegerArray(String id, int[] data) {
        IntegerArrayBuilder builder = new IntegerArrayBuilder();
        builder.setId(id);
        builder.setData(data);
        IntegerArray array = builder.build();
        return array;
    }
}
