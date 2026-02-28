package com.oitc;

import java.util.HashMap;
import java.util.Map;

public class ProgressTracker {

    private Map<String, Integer> progress = new HashMap<>();

    public void saveProgress(String topic, int score) {
        progress.put(topic, score);
    }

    public void showProgress() {

        if (progress.isEmpty()) {
            System.out.println("No progress recorded yet.");
            return;
        }

        System.out.println("\nYour Progress:");

        for (String topic : progress.keySet()) {
            System.out.println(topic + " : " + progress.get(topic) + "%");
        }
    }
}