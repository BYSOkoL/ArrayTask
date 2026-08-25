package com.arrayprocessor.warehouse;

import com.arrayprocessor.dto.WarehouseStatistics;
import com.arrayprocessor.entity.IntegerArray;
import com.arrayprocessor.repository.ArrayRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArrayWarehouse {

    private static final Logger logger = LogManager.getLogger(ArrayWarehouse.class);
    private static ArrayWarehouse instance;
    private WarehouseStatistics statistics;

    private ArrayWarehouse() {
        this.statistics = new WarehouseStatistics(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void recalculate(ArrayRepository repository) {
        logger.info("Recalculating warehouse statistics...");

        List<IntegerArray> allArrays = repository.findAll();

        if (allArrays.isEmpty()) {
            this.statistics = new WarehouseStatistics(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return;
        }

        int globalSum = 0;
        int globalCount = 0;
        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;

        for (IntegerArray array : allArrays) {
            int[] data = array.getData();
            for (int value : data) {
                globalSum += value;
                globalCount++;
                if (value > globalMax) {
                    globalMax = value;
                }
                if (value < globalMin) {
                    globalMin = value;
                }
            }
        }

        double globalAvg = globalCount > 0 ? (double) globalSum / globalCount : 0;
        this.statistics = new WarehouseStatistics(globalSum, globalAvg, globalMax, globalMin);

        logger.info("Recalculated: sum={}, avg={}, max={}, min={}",
                statistics.totalSum(), statistics.totalAvg(),
                statistics.totalMax(), statistics.totalMin());
    }

    public WarehouseStatistics getStatistics() {
        return statistics;
    }

    public double getTotalSum() {
        return statistics.totalSum();
    }

    public double getTotalAvg() {
        return statistics.totalAvg();
    }

    public int getTotalMax() {
        return statistics.totalMax() == Integer.MIN_VALUE ? 0 : statistics.totalMax();
    }

    public int getTotalMin() {
        return statistics.totalMin() == Integer.MAX_VALUE ? 0 : statistics.totalMin();
    }
}
