package com.arrayprocessor.service.api;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface IntegerArrayStatisticsService {

    OptionalInt findMin(int[] array);

    OptionalInt findMax(int[] array);

    OptionalInt findSum(int[] array);

    OptionalDouble findAverage(int[] array);
}
