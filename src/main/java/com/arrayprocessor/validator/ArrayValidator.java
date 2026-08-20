package com.arrayprocessor.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class ArrayValidator {

    private static final Logger logger = LogManager.getLogger(ArrayValidator.class);

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("[,;\\s-]+");

    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }

        String trimmedLine = line.trim();
        if (trimmedLine.isEmpty()) {
            return true;
        }

        String[] tokens = DELIMITER_PATTERN.split(trimmedLine);
        for (String token : tokens) {
            String cleanToken = token.trim();
            if (cleanToken.isEmpty()) {
                continue;
            }
            boolean matches = NUMBER_PATTERN.matcher(cleanToken).matches();
            if (!matches) {
                logger.warn("Invalid token detected: '{}'", cleanToken);
                return false;
            }
        }
        return true;
    }
}
