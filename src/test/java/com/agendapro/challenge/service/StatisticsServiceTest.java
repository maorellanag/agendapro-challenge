package com.agendapro.challenge.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsServiceTest {

    private final StatisticsService statisticsService = new StatisticsService();

    @Test
    void increaseCounter() {
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category1");

        Integer category1Count = statisticsService.getCategoryCount().get("category1");

        assertEquals(3, category1Count);
    }

    @Test
    void decreaseCounter() {
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category1");

        statisticsService.decreaseCounter("category1");
        Integer count = statisticsService.getCategoryCount().get("category1");

        assertEquals(2, count);
    }

    @Test
    void getCategoryCount_whenThereAreTwoCategories() {
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category1");
        statisticsService.increaseCounter("category2");

        HashMap<String, Integer> categoryCount = statisticsService.getCategoryCount();

        assertEquals(2, categoryCount.get("category1"));
        assertEquals(1, categoryCount.get("category2"));
    }
}