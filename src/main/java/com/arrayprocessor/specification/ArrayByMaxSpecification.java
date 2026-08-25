package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

import java.util.Arrays;

public record ArrayByMaxSpecification(int threshold, ComparisonOperator operator) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        int max = Arrays.stream(array.getData()).max().orElse(Integer.MAX_VALUE);
        return compare(max, threshold);
    }

    private boolean compare(int value, int threshold) {
        return switch (operator) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }
}
