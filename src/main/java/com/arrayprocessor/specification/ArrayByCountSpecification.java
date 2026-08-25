package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

public record ArrayByCountSpecification(int threshold, ComparisonOperator operator) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        int count = array.getData().length;
        return compare(count, threshold);
    }

    private boolean compare(int value, int threshold) {
        return switch (operator) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }
}
