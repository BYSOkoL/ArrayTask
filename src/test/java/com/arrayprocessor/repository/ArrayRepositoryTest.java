package com.arrayprocessor.repository;

import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.observer.WarehouseObserver;
import com.arrayprocessor.specification.ArrayBySumSpecification;
import com.arrayprocessor.specification.ComparisonOperator;
import com.arrayprocessor.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayRepositoryTest {

    private ArrayRepository repository;

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
        ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
        WarehouseObserver observer = new WarehouseObserver(warehouse);
        repository.addObserver(observer);
    }

    @AfterEach
    void tearDown() {
        repository.findAll().forEach(a -> repository.remove(a.getId()));
    }

    @Test
    void shouldAddArray() {
        // given
        IntegerArray array = new IntegerArray("test1", new int[]{1, 2, 3});

        // when
        repository.add(array);

        // then
        assertTrue(repository.findById("test1").isPresent());
    }

    @Test
    void shouldFindBySumSpecification() {
        // given
        repository.add(new IntegerArray("arr1", new int[]{1, 2, 3}));
        repository.add(new IntegerArray("arr2", new int[]{10, 20}));
        ArrayBySumSpecification spec = new ArrayBySumSpecification(10, ComparisonOperator.GREATER);

        // when
        List<IntegerArray> result = repository.findBySpecification(spec);

        // then
        assertEquals(1, result.size());
        assertEquals("arr2", result.get(0).getId());
    }

    @Test
    void shouldRemoveArray() {
        // given
        repository.add(new IntegerArray("test1", new int[]{1, 2, 3}));

        // when
        repository.remove("test1");

        // then
        assertTrue(repository.findById("test1").isEmpty());
    }
}
