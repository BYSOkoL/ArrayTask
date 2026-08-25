package com.arrayprocessor.comparator;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Comparator;

public class ArrayByLengthComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray a1, IntegerArray a2) {
        return Integer.compare(a1.getData().length, a2.getData().length);
    }
}
