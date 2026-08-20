package com.arrayprocessor;

import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.exception.ArrayProcessingException;
import com.arrayprocessor.factory.NumericArrayFactory;
import com.arrayprocessor.parser.ArrayParser;
import com.arrayprocessor.reader.ArrayFileReader;
import com.arrayprocessor.service.api.ArraySortingService;
import com.arrayprocessor.service.api.IntegerArrayStatisticsService;
import com.arrayprocessor.service.api.SortingAlgorithm;
import com.arrayprocessor.validator.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayProcessor {

    private static final Logger logger = LogManager.getLogger(ArrayProcessor.class);

    private final ArrayFileReader reader;
    private final ArrayValidator validator;
    private final ArrayParser parser;
    private final NumericArrayFactory factory;
    private final IntegerArrayStatisticsService statisticsService;
    private final ArraySortingService sortingService;

    public ArrayProcessor(
            ArrayFileReader reader,
            ArrayValidator validator,
            ArrayParser parser,
            NumericArrayFactory factory,
            IntegerArrayStatisticsService statisticsService,
            ArraySortingService sortingService) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
        this.factory = factory;
        this.statisticsService = statisticsService;
        this.sortingService = sortingService;
    }

    public void run() {
        logger.info("Application started");
        try {
            List<String> lines = reader.readLines();
            processAllLines(lines);
        } catch (ArrayProcessingException e) {
            logger.error("Application terminated with error: {}", e.getMessage(), e);
        }
        logger.info("Application finished");
    }

    private void processAllLines(List<String> lines) {
        for (String line : lines) {
            boolean valid = validator.isValid(line);
            if (valid) {
                if (containsOnlyIntegers(line)) {
                    processValidLine(line);
                } else {
                    logger.info("Skipping double values line: '{}'", line.trim());
                }
            } else {
                logger.warn("Skipping invalid line: '{}'", line);
            }
        }
    }

    private boolean containsOnlyIntegers(String line) {
        String strippedLine = line.strip();
        if (strippedLine.isBlank()) {
            return true;
        }

        String[] tokens = strippedLine.split("[,;\\s-]+");
        for (String token : tokens) {
            String cleanToken = token.strip();
            if (!cleanToken.isBlank() && cleanToken.contains(".")) {
                return false;
            }
        }
        return true;
    }

    private void processValidLine(String line) {
        int[] parsedArray = parser.parseToIntArray(line);
        IntegerArray array = factory.createIntegerArray(parsedArray);
        int[] data = array.getData();

        if (data.length == 0) {
            logger.info("Empty array processed from line: '{}'", line);
            return;
        }

        logArrayStatistics(data, line);
        sortAndLogResults(data);
    }

    private void logArrayStatistics(int[] data, String originalLine) {
        OptionalInt min = statisticsService.findMin(data);
        OptionalInt max = statisticsService.findMax(data);
        OptionalInt sum = statisticsService.findSum(data);
        OptionalDouble average = statisticsService.findAverage(data);

        logger.info("Array [{}]: min={}, max={}, sum={}, avg={}",
                originalLine.trim(),
                min.orElse(0),
                max.orElse(0),
                sum.orElse(0),
                average.orElse(0.0));
    }

    private void sortAndLogResults(int[] data) {
        try {
            int[] bubbleArray = data.clone();
            sortingService.sort(bubbleArray, SortingAlgorithm.BUBBLE);
            logger.info("After Bubble Sort: {}", formatArray(bubbleArray));

            int[] insertionArray = data.clone();
            sortingService.sort(insertionArray, SortingAlgorithm.INSERTION);
            logger.info("After Insertion Sort: {}", formatArray(insertionArray));
        } catch (ArrayProcessingException e) {
            logger.error("Sorting failed: {}", e.getMessage(), e);
        }
    }

    private String formatArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}