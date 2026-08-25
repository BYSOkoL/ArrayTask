package com.arrayprocessor.warehouse;

import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.observer.WarehouseObserver;
import com.arrayprocessor.repository.ArrayRepository;
import com.arrayprocessor.repository.ArrayRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayWarehouseTest {

    private ArrayWarehouse warehouse;
    private ArrayRepository repository;

    @BeforeEach
    void setUp() {
        warehouse = ArrayWarehouse.getInstance();
        repository = ArrayRepositoryImpl.getInstance();
        WarehouseObserver observer = new WarehouseObserver(warehouse);
        repository.addObserver(observer);
    }

    @AfterEach
    void tearDown() {
        repository.findAll().forEach(a -> repository.remove(a.getId()));
    }

    @Test
    void shouldRecalculateOnAdd() {
        // given
        repository.add(new IntegerArray("test1", new int[]{1, 2, 3}));

        // then
        assertEquals(6, warehouse.getTotalSum());
        assertEquals(2.0, warehouse.getTotalAvg());
    }

    @Test
    void shouldRecalculateOnUpdate() {
        // given
        repository.add(new IntegerArray("test1", new int[]{1, 2, 3}));

        // when
        repository.update("test1", new int[]{10, 20, 30});

        // then
        assertEquals(60, warehouse.getTotalSum());
        assertEquals(20.0, warehouse.getTotalAvg());
    }

    @Test
    void shouldRecalculateOnRemove() {
        // given
        repository.add(new IntegerArray("test1", new int[]{5, 5}));
        repository.add(new IntegerArray("test2", new int[]{10}));

        // when
        repository.remove("test1");

        // then
        assertEquals(10, warehouse.getTotalSum());
        assertEquals(10.0, warehouse.getTotalAvg());
    }
}
