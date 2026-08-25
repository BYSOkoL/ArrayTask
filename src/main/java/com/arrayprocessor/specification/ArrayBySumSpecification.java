package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Arrays;

public record ArrayBySumSpecification(double threshold, ComparisonOperator operator) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        int sum = Arrays.stream(array.getData()).sum();
        return compare(sum, threshold);
    }

    private boolean compare(int value, double threshold) {
        return switch (operator) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }
}
