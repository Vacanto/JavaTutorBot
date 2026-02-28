package com.oitc;

public class IntentDetector {

    public String detectIntent(String input) {

        input = input.toLowerCase();

        if (input.contains("help") || input.contains("guide") || input.contains("explain")) {
            return "explanation";
        } else if (input.contains("evaluate") || input.contains("check")) {
            return "evaluation";
        } else if (input.contains("quiz")) {
            return "quiz";
        } else if (input.contains("progress")) {
            return "progress";
        } else if (input.contains("exit")) {
            return "exit";
        }

        return "unknown";
    }
}