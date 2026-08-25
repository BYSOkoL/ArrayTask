package com.arrayprocessor.comparator;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Comparator;

public class ArrayByIdComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray a1, IntegerArray a2) {
        return a1.getId().compareTo(a2.getId());
    }
}
