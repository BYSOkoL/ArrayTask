package com.arrayprocessor.service;

import com.arrayprocessor.service.api.IntegerArrayStatisticsService;
import com.arrayprocessor.service.impl.IntegerArrayStatisticsServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IntegerArrayStatisticsServiceImplTest {

    private static final int[] SAMPLE_ARRAY = {3, 1, 4, 1, 5, 9, 2, 6};
    private static final int[] EMPTY_ARRAY = {};
    private static final int EXPECTED_MIN = 1;
    private static final double EXPECTED_AVG = 3.875;
    private static final double DELTA = 0.001;

    private final IntegerArrayStatisticsService service = new IntegerArrayStatisticsServiceImpl();

    @Test
    void shouldFindMin() {
        // given
        int[] data = SAMPLE_ARRAY;

        // when
        OptionalInt result = service.findMin(data);

        // then
        assertEquals(EXPECTED_MIN, result.getAsInt());
    }

    @Test
    void shouldFindAverage() {
        // given
        int[] data = SAMPLE_ARRAY;

        // when
        OptionalDouble result = service.findAverage(data);

        // then
        assertEquals(EXPECTED_AVG, result.getAsDouble(), DELTA);
    }

    @Test
    void shouldReturnEmptyForEmptyArray() {
        // given
        int[] data = EMPTY_ARRAY;

        // when
        OptionalInt minResult = service.findMin(data);

        // then
        assertFalse(minResult.isPresent());
    }
}
