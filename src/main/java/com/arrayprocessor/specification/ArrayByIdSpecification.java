package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

public record ArrayByIdSpecification(String id) implements Specification {

    @Override
    public boolean isSatisfiedBy(IntegerArray array) {
        return array.getId().equals(id);
    }
}
