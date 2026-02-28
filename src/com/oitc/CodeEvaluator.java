package com.oitc;

public class CodeEvaluator {

    public boolean evaluateCode(String code) {

        if (!code.contains("public class")) {
            System.out.println("Error: Missing public class declaration.");
            return false;
        }

        if (!code.contains("main")) {
            System.out.println("Warning: main method not found.");
        }

        if (code.contains("int") && code.contains("\"")) {
            System.out.println("Possible type mismatch detected.");
            return false;
        }

        System.out.println("Basic structure looks valid.");
        return true;
    }
}