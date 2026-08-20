package com.arrayprocessor.factory;

import com.arrayprocessor.builder.DoubleArrayBuilder;
import com.arrayprocessor.builder.IntegerArrayBuilder;
import com.arrayprocessor.entity.DoubleArray;
import com.arrayprocessor.entity.IntegerArray;

public class NumericArrayFactory {

    public IntegerArray createIntegerArray(int[] data) {
        IntegerArrayBuilder builder = new IntegerArrayBuilder();
        builder.setData(data);
        IntegerArray array = builder.build();
        return array;
    }

    public DoubleArray createDoubleArray(double[] data) {
        DoubleArrayBuilder builder = new DoubleArrayBuilder();
        builder.setData(data);
        DoubleArray array = builder.build();
        return array;
    }
}
