package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Arrays;

public record ArrayByMinSpecification(int threshold, ComparisonOperator operator) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        int min = Arrays.stream(array.getData()).min().orElse(Integer.MIN_VALUE);
        return compare(min, threshold);
    }

    private boolean compare(int value, int threshold) {
        return switch (operator) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }
}
