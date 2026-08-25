package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Arrays;

public record ArrayByAvgSpecification(double threshold, ComparisonOperator operator) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        int[] data = array.getData();
        if (data.length == 0) {
            return false;
        }
        double avg = Arrays.stream(data).average().orElse(0.0);
        return compare(avg, threshold);
    }

    private boolean compare(double value, double threshold) {
        return switch (operator) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }
}
