package com.arrayprocessor.service;

import com.arrayprocessor.exception.ArrayProcessingException;
import com.arrayprocessor.service.api.ArraySortingService;
import com.arrayprocessor.service.api.SortingAlgorithm;
import com.arrayprocessor.service.impl.ArraySortingServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArraySortingServiceImplTest {

    private static final int[] UNSORTED_INT = {5, 2, 8, 1, 9, 3};
    private static final int[] EXPECTED_SORTED_INT = {1, 2, 3, 5, 8, 9};

    private final ArraySortingService sortService = new ArraySortingServiceImpl();

    @Test
    void shouldSortIntByBubble() throws ArrayProcessingException {
        // given
        int[] data = UNSORTED_INT.clone();

        // when
        sortService.sort(data, SortingAlgorithm.BUBBLE);

        // then
        assertArrayEquals(EXPECTED_SORTED_INT, data);
    }

    @Test
    void shouldSortIntByInsertion() throws ArrayProcessingException {
        // given
        int[] data = UNSORTED_INT.clone();

        // when
        sortService.sort(data, SortingAlgorithm.INSERTION);

        // then
        assertArrayEquals(EXPECTED_SORTED_INT, data);
    }

    @Test
    void shouldThrowExceptionForNullArray() {
        // given
        int[] data = null;

        // when & then
        assertThrows(ArrayProcessingException.class, () -> {
            sortService.sort(data, SortingAlgorithm.BUBBLE);
        });
    }
}
