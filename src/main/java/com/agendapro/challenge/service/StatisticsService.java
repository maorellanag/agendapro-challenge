package com.agendapro.challenge.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatisticsService {

    private final ConcurrentHashMap<String, Integer> categoryCount = new ConcurrentHashMap<>();

    @Async
    public void increaseCounter(String category) {
        String lowerCaseCategory = category.toLowerCase();
        Integer count = categoryCount.get(lowerCaseCategory);

        if (count == null) {
            categoryCount.put(lowerCaseCategory, 1);
        } else {
            categoryCount.put(lowerCaseCategory, ++count);
        }
    }

    @Async
    public void decreaseCounter(String category) {
        String lowerCaseCategory = category.toLowerCase();
        Integer count = categoryCount.get(category);
        if (count != null) {
            categoryCount.put(lowerCaseCategory, --count);
        }
    }

    public HashMap<String, Integer> getCategoryCount() {
        return new HashMap<>(categoryCount);
    }

    public Integer getCategoryCount(String category) {
        return this.categoryCount.get(category.toLowerCase());
    }

}
