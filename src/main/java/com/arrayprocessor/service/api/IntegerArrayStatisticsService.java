package com.arrayprocessor.service.api;

import com.arrayprocessor.dto.ArrayStatistics;

public interface IntegerArrayStatisticsService {

    ArrayStatistics calculateStatistics(int[] array);
}
