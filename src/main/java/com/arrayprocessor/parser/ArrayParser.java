package com.arrayprocessor.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArrayParser {

    private static final Pattern DELIMITER_PATTERN = Pattern.compile("[,;\\s-]+");

    public int[] parseToIntArray(String line) {
        List<Integer> numbers = extractIntegers(line);
        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

    private List<Integer> extractIntegers(String line) {
        List<Integer> numbers = new ArrayList<>();
        String strippedLine = line.strip();
        if (strippedLine.isBlank()) {
            return numbers;
        }
        String[] tokens = DELIMITER_PATTERN.split(strippedLine);
        for (String token : tokens) {
            String cleanToken = token.strip();
            if (cleanToken.isBlank()) {
                continue;
            }
            int value = Integer.parseInt(cleanToken);
            numbers.add(value);
        }
        return numbers;
    }
}
