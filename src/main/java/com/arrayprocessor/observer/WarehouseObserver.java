package com.arrayprocessor.observer;

import com.arrayprocessor.repository.ArrayRepository;
import com.arrayprocessor.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WarehouseObserver implements Observer {

    private static final Logger logger = LogManager.getLogger(WarehouseObserver.class);
    private final ArrayWarehouse warehouse;

    public WarehouseObserver(ArrayWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ArrayRepository repository) {
            logger.info("WarehouseObserver notified, recalculating...");
            warehouse.recalculate(repository);
        }
    }
}
