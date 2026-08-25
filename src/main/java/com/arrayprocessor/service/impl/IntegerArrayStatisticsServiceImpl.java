package com.arrayprocessor.service.impl;

import com.arrayprocessor.dto.ArrayStatistics;
import com.arrayprocessor.service.api.IntegerArrayStatisticsService;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class IntegerArrayStatisticsServiceImpl implements IntegerArrayStatisticsService {

    @Override
    public ArrayStatistics calculateStatistics(int[] array) {
        if (array == null || array.length == 0) {
            return new ArrayStatistics(0, 0, 0, 0.0);
        }

        OptionalInt min = findMin(array);
        OptionalInt max = findMax(array);
        OptionalInt sum = findSum(array);
        OptionalDouble avg = findAverage(array);

        return new ArrayStatistics(
                min.orElse(0),
                max.orElse(0),
                sum.orElse(0),
                avg.orElse(0.0)
        );
    }

    private OptionalInt findMin(int[] array) {
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

    private OptionalInt findMax(int[] array) {
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

    private OptionalInt findSum(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalInt.empty();
        }
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return OptionalInt.of(sum);
    }

    private OptionalDouble findAverage(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalDouble.empty();
        }
        OptionalInt sumOptional = findSum(array);
        int sum = sumOptional.getAsInt();
        double average = (double) sum / array.length;
        return OptionalDouble.of(average);
    }
}
