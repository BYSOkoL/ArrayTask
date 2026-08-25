package com.arrayprocessor.service;

import com.arrayprocessor.dto.ArrayStatistics;
import com.arrayprocessor.service.api.IntegerArrayStatisticsService;
import com.arrayprocessor.service.impl.IntegerArrayStatisticsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerArrayStatisticsServiceImplTest {

    private static final int[] SAMPLE_ARRAY = {3, 1, 4, 1, 5, 9, 2, 6};
    private static final int[] EMPTY_ARRAY = {};
    private static final int EXPECTED_MIN = 1;
    private static final int EXPECTED_MAX = 9;
    private static final int EXPECTED_SUM = 31;
    private static final double EXPECTED_AVG = 3.875;
    private static final double DELTA = 0.001;

    private final IntegerArrayStatisticsService service = new IntegerArrayStatisticsServiceImpl();

    @Test
    void shouldCalculateStatistics() {
        // given
        int[] data = SAMPLE_ARRAY;

        // when
        ArrayStatistics result = service.calculateStatistics(data);

        // then
        assertEquals(EXPECTED_MIN, result.min());
        assertEquals(EXPECTED_MAX, result.max());
        assertEquals(EXPECTED_SUM, result.sum());
        assertEquals(EXPECTED_AVG, result.avg(), DELTA);
    }

    @Test
    void shouldReturnZerosForEmptyArray() {
        // given
        int[] data = EMPTY_ARRAY;

        // when
        ArrayStatistics result = service.calculateStatistics(data);

        // then
        assertEquals(0, result.min());
        assertEquals(0, result.max());
        assertEquals(0, result.sum());
        assertEquals(0.0, result.avg(), DELTA);
    }
}
