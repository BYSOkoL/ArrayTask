package com.arrayprocessor.comparator;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Comparator;

public class ArrayByFirstElementComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray a1, IntegerArray a2) {
        int[] data1 = a1.getData();
        int[] data2 = a2.getData();

        if (data1.length == 0 && data2.length == 0) {
            return 0;
        }
        if (data1.length == 0) {
            return -1;
        }
        if (data2.length == 0) {
            return 1;
        }

        return Integer.compare(data1[0], data2[0]);
    }
}
