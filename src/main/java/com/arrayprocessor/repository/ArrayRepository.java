package com.arrayprocessor.repository;

import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.observer.Observable;
import com.arrayprocessor.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository extends Observable {
    void add(IntegerArray array);
    void remove(String id);
    void update(String id, int[] newData);
    Optional<IntegerArray> findById(String id);
    List<IntegerArray> findAll();
    List<IntegerArray> findBySpecification(Specification specification);
    List<IntegerArray> sortBy(Comparator<IntegerArray> comparator);
}
