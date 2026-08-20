package com.arrayprocessor.reader;

import com.arrayprocessor.exception.ArrayProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayFileReader {

    private static final Logger logger = LogManager.getLogger(ArrayFileReader.class);
    private final Path filePath;

    public ArrayFileReader(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    public List<String> readLines() throws ArrayProcessingException {
        logger.info("Reading data from file: {}", filePath);
        try {
            List<String> lines = Files.readAllLines(filePath);
            return lines;
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            throw new ArrayProcessingException("Cannot read file: " + filePath, e);
        }
    }
}
