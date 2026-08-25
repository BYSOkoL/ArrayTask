package com.arrayprocessor.comparator;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayBySumComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray a1, IntegerArray a2) {
        int sum1 = Arrays.stream(a1.getData()).sum();
        int sum2 = Arrays.stream(a2.getData()).sum();
        return Integer.compare(sum1, sum2);
    }
}
