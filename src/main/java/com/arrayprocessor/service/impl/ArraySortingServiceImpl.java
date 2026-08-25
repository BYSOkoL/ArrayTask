package com.arrayprocessor.service.impl;

import com.arrayprocessor.exception.ArrayProcessingException;
import com.arrayprocessor.service.api.ArraySortingService;
import com.arrayprocessor.service.api.SortingAlgorithm;

public class ArraySortingServiceImpl implements ArraySortingService {

    @Override
    public void sort(int[] array, SortingAlgorithm algorithm) throws ArrayProcessingException {
        if (array == null) {
            throw new ArrayProcessingException("Array must not be null");
        }
        if (algorithm == SortingAlgorithm.BUBBLE) {
            bubbleSortInt(array);
        } else {
            insertionSortInt(array);
        }
    }

    private void bubbleSortInt(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void insertionSortInt(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
