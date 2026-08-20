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

    public double[] parseToDoubleArray(String line) {
        List<Double> numbers = extractDoubles(line);
        double[] result = new double[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

    private List<Integer> extractIntegers(String line) {
        List<Integer> numbers = new ArrayList<>();
        String trimmedLine = line.trim();
        if (trimmedLine.isEmpty()) {
            return numbers;
        }
        String[] tokens = DELIMITER_PATTERN.split(trimmedLine);
        for (String token : tokens) {
            String cleanToken = token.trim();
            if (cleanToken.isEmpty()) {
                continue;
            }
            int value = Integer.parseInt(cleanToken);
            numbers.add(value);
        }
        return numbers;
    }

    private List<Double> extractDoubles(String line) {
        List<Double> numbers = new ArrayList<>();
        String trimmedLine = line.trim();
        if (trimmedLine.isEmpty()) {
            return numbers;
        }
        String[] tokens = DELIMITER_PATTERN.split(trimmedLine);
        for (String token : tokens) {
            String cleanToken = token.trim();
            if (cleanToken.isEmpty()) {
                continue;
            }
            double value = Double.parseDouble(cleanToken);
            numbers.add(value);
        }
        return numbers;
    }
}
