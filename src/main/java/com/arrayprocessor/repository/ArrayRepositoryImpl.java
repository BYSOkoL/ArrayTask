package com.arrayprocessor.repository;

import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.observer.Observer;
import com.arrayprocessor.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrayRepositoryImpl implements ArrayRepository {

    private static final Logger logger = LogManager.getLogger(ArrayRepositoryImpl.class);
    private static ArrayRepository instance;
    private final Map<String, IntegerArray> arrays = new HashMap<>();
    private final List<Observer> observers = new ArrayList<>();

    private ArrayRepositoryImpl() {
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(IntegerArray array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arrays.containsKey(array.getId())) {
            throw new IllegalArgumentException("Array with ID " + array.getId() + " already exists");
        }
        arrays.put(array.getId(), array);
        logger.info("Added array with ID: {}", array.getId());
        notifyObservers();
    }

    @Override
    public void remove(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        arrays.remove(id);
        logger.info("Removed array with ID: {}", id);
        notifyObservers();
    }

    @Override
    public void update(String id, int[] newData) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (newData == null) {
            throw new IllegalArgumentException("New data cannot be null");
        }
        if (arrays.containsKey(id)) {
            arrays.put(id, new IntegerArray(id, newData));
            logger.info("Updated array with ID: {}", id);
            notifyObservers();
        }
    }

    @Override
    public Optional<IntegerArray> findById(String id) {
        return Optional.ofNullable(arrays.get(id));
    }

    @Override
    public List<IntegerArray> findAll() {
        return new ArrayList<>(arrays.values());
    }

    @Override
    public List<IntegerArray> findBySpecification(Specification specification) {
        if (specification == null) {
            return findAll();
        }
        return arrays.values().stream()
                .filter(specification::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    @Override
    public List<IntegerArray> sortBy(Comparator<IntegerArray> comparator) {
        return arrays.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
