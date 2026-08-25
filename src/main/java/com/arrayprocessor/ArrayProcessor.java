package com.arrayprocessor;

import com.arrayprocessor.dto.ArrayStatistics;
import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.exception.ArrayProcessingException;
import com.arrayprocessor.factory.NumericArrayFactory;
import com.arrayprocessor.parser.ArrayParser;
import com.arrayprocessor.reader.ArrayFileReader;
import com.arrayprocessor.repository.ArrayRepository;
import com.arrayprocessor.service.api.ArraySortingService;
import com.arrayprocessor.service.api.IntegerArrayStatisticsService;
import com.arrayprocessor.service.api.SortingAlgorithm;
import com.arrayprocessor.validator.ArrayValidator;
import com.arrayprocessor.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;

public class ArrayProcessor {

    private static final Logger logger = LogManager.getLogger(ArrayProcessor.class);

    private final ArrayFileReader reader;
    private final ArrayValidator validator;
    private final ArrayParser parser;
    private final NumericArrayFactory factory;
    private final IntegerArrayStatisticsService statisticsService;
    private final ArraySortingService sortingService;
    private final ArrayRepository repository;
    private final ArrayWarehouse warehouse;

    public ArrayProcessor(
            ArrayFileReader reader,
            ArrayValidator validator,
            ArrayParser parser,
            NumericArrayFactory factory,
            IntegerArrayStatisticsService statisticsService,
            ArraySortingService sortingService,
            ArrayRepository repository,
            ArrayWarehouse warehouse) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
        this.factory = factory;
        this.statisticsService = statisticsService;
        this.sortingService = sortingService;
        this.repository = repository;
        this.warehouse = warehouse;
    }

    public void run() {
        logger.info("Application started");
        try {
            List<String> lines = reader.readLines();
            processAllLines(lines);
            logWarehouseStatistics();
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
                    logger.info("Skipping double values line: '{}'", line.strip());
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
        String id = generateId();
        IntegerArray array = factory.createIntegerArray(id, parsedArray);

        repository.add(array);

        ArrayStatistics stats = statisticsService.calculateStatistics(array.getData());
        logger.info("Array [{}] (id={}): min={}, max={}, sum={}, avg={}",
                line.strip(),
                id,
                stats.min(),
                stats.max(),
                stats.sum(),
                stats.avg());

        int[] bubbleArray = array.getData().clone();
        try {
            sortingService.sort(bubbleArray, SortingAlgorithm.BUBBLE);
            logger.info("After Bubble Sort: {}", formatArray(bubbleArray));
        } catch (ArrayProcessingException e) {
            logger.error("Sorting failed: {}", e.getMessage(), e);
        }
    }

    private String generateId() {
        return "array_" + UUID.randomUUID().toString().substring(0, 8);
    }

    private void logWarehouseStatistics() {
        logger.info("=== Warehouse Statistics ===");
        logger.info("Total sum: {}", warehouse.getTotalSum());
        logger.info("Total avg: {}", warehouse.getTotalAvg());
        logger.info("Total max: {}", warehouse.getTotalMax());
        logger.info("Total min: {}", warehouse.getTotalMin());
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
