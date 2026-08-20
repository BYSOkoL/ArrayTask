package com.arrayprocessor.service.impl;

import com.arrayprocessor.service.api.IntegerArrayStatisticsService;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class IntegerArrayStatisticsServiceImpl implements IntegerArrayStatisticsService {

    @Override
    public OptionalInt findMin(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalInt.empty();
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalInt.empty();
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return OptionalInt.of(max);
    }

    @Override
    public OptionalInt findSum(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalInt.empty();
        }
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return OptionalInt.of(sum);
    }

    @Override
    public OptionalDouble findAverage(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalDouble.empty();
        }
        OptionalInt sumOptional = findSum(array);
        int sum = sumOptional.getAsInt();
        double average = (double) sum / array.length;
        return OptionalDouble.of(average);
    }
}
