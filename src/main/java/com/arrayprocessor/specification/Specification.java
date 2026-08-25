package com.arrayprocessor.specification;

import com.arrayprocessor.entity.IntegerArray;

public interface Specification {
    boolean isSatisfiedBy(IntegerArray array);
}
