package com.arrayprocessor;

import com.arrayprocessor.factory.NumericArrayFactory;
import com.arrayprocessor.parser.ArrayParser;
import com.arrayprocessor.reader.ArrayFileReader;
import com.arrayprocessor.service.api.ArraySortingService;
import com.arrayprocessor.service.api.IntegerArrayStatisticsService;
import com.arrayprocessor.service.impl.ArraySortingServiceImpl;
import com.arrayprocessor.service.impl.IntegerArrayStatisticsServiceImpl;
import com.arrayprocessor.validator.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_FILE_PATH = "data/arrays_data.txt";

    public static void main(String[] args) {
        logger.info("Initializing application dependencies...");

        ArrayFileReader reader = new ArrayFileReader(DATA_FILE_PATH);
        ArrayValidator validator = new ArrayValidator();
        ArrayParser parser = new ArrayParser();
        NumericArrayFactory factory = new NumericArrayFactory();
        IntegerArrayStatisticsService statisticsService = new IntegerArrayStatisticsServiceImpl();
        ArraySortingService sortingService = new ArraySortingServiceImpl();

        ArrayProcessor processor = new ArrayProcessor(
                reader, validator, parser, factory, statisticsService, sortingService);

        logger.info("Starting application execution...");
        processor.run();
        logger.info("Application execution completed");
    }
}