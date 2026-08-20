package com.arrayprocessor.service.api;

import com.arrayprocessor.exception.ArrayProcessingException;

public interface ArraySortingService {

    void sort(int[] array, SortingAlgorithm algorithm) throws ArrayProcessingException;

    void sort(double[] array, SortingAlgorithm algorithm) throws ArrayProcessingException;
}
