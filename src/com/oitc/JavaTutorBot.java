package com.oitc;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
/*
 * JavaTutorBot
 * -------------------------
 * A console-based Java learning chatbot.
 * Features:
 * - Concept explanations
 * - 5-question quiz system
 * - Score tracking
 * - Difficulty selection
 * - Fuzzy keyword matching (Levenshtein Distance)
 */

public class JavaTutorBot {
    
    // ==============================
    // 🎯 BOT SETTINGS & VARIABLES
    // ==============================

    // Difficulty mode (beginner / intermediate / advanced)
    private String difficulty = "beginner";
    
    //topic
    private String topic = "oops";

    // Quiz state tracker
    private boolean quizMode = false;

    // Stores the correct answer for current question
    private String currentAnswer = "";

    // Score tracking
    private int totalQuestions = 0;
    private int correctAnswers = 0;

    // Quiz control (limit to 5 questions per session)
    private int quizLimit = 5;
    private int quizCount = 0;

    // Random object for generating random questions
    private Random random = new Random();


    // ==============================
    // 🧠 MAIN RESPONSE ENGINE
    // ==============================

    public String getResponse(String input) {

        // Convert input to lowercase for case-insensitive matching
        input = input.toLowerCase();

        // ==============================
        // 📝 QUIZ MODE HANDLER
        // ==============================
        if (quizMode) {

            totalQuestions++;
            quizCount++;

            // Check if answer is correct
            if (input.equals(currentAnswer)) {

                correctAnswers++;

                String response = "Correct! 🎉\nScore: "
                        + correctAnswers + " / " + totalQuestions;

                // If quiz not finished → ask next question
                if (quizCount < quizLimit) {
                    return response + "\n\n" + generateQuestion();
                }
                // If quiz finished → show final result
                else {
                    quizMode = false;
                    return response +
                            "\n\nQuiz Finished!\nFinal Score: "
                            + correctAnswers + " / " + totalQuestions;
                }
            }

            // If answer is wrong
            else {

                String response = "Wrong ❌ The correct answer is: "
                        + currentAnswer +
                        "\nScore: "
                        + correctAnswers + " / " + totalQuestions;

                if (quizCount < quizLimit) {
                    return response + "\n\n" + generateQuestion();
                } else {
                	quizMode = false;
                	saveProgress();
                	return response + "\n\nQuiz Finished!\nFinal Score: "
                	        + correctAnswers + " / " + totalQuestions
                	        + "\nProgress saved to file.";
                }
            }
        }

        // ==============================
        // ⚙️ DIFFICULTY MODE SWITCHING
        // ==============================

        else if (input.contains("mode beginner")) {
            difficulty = "beginner";
            return "Difficulty set to Beginner.";
        }
        else if (input.contains("mode intermediate")) {
            difficulty = "intermediate";
            return "Difficulty set to Intermediate.";
        }
        else if (input.contains("mode advanced")) {
            difficulty = "advanced";
            return "Difficulty set to Advanced.";
        }
        
        //topic selection
    
        else if (input.contains("topic oops")) {
            topic = "oops";
            return "Topic set to OOPS.";
        }
        else if (input.contains("topic basics")) {
            topic = "basics";
            return "Topic set to Java Basics.";
        }
        
        //generate report
        
        else if (input.contains("report")) {
            return generateReport();
        }

        // ==============================
        // 🎮 START QUIZ
        // ==============================

        if (input.contains("quiz")) {
            startFullQuiz();
            return "Starting 5 Question Quiz!\n\n" + generateQuestion();
        }

        // ==============================
        // 📊 SCORE CHECK
        // ==============================

        else if (input.contains("score")) {
            return "Current Score: "
                    + correctAnswers + " / " + totalQuestions;
        }

        // ==============================
        // 📚 CONCEPT EXPLANATIONS
        // Uses fuzzy matching
        // ==============================

        else if (isSimilar(input, "java")) {
            return "Java is an object-oriented programming language used to build applications.";
        }
        else if (isSimilar(input, "class")) {
            return "A class is a blueprint for creating objects.";
        }
        else if (isSimilar(input, "object")) {
            return "An object is an instance of a class.";
        }
        else if (isSimilar(input, "inheritance")) {
            return "Inheritance allows one class to acquire properties of another class.";
        }
        else if (isSimilar(input, "polymorphism")) {
            return "Polymorphism allows objects to take many forms.";
        }

        // ==============================
        // ❓ DEFAULT RESPONSE
        // ==============================

        else {
            return "Sorry, I don't understand that yet.";
        }
    }


    // ==============================
    // 🔎 FUZZY MATCHING SYSTEM
    // ==============================

    /*
     * Checks if input contains keyword
     * OR if it is close using Levenshtein distance
     */
    private boolean isSimilar(String input, String keyword) {
        return input.contains(keyword) ||
                levenshteinDistance(input, keyword) <= 2;
    }


    /*
     * Calculates Levenshtein Distance
     * (Number of single-character edits required to change one word to another)
     */
    private int levenshteinDistance(String a, String b) {

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {

                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] +
                                    (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1));
                }
            }
        }

        return dp[a.length()][b.length()];
    }


    // ==============================
    // 🚀 QUIZ CONTROL METHODS
    // ==============================

    // Starts a new quiz session
    private void startFullQuiz() {
        quizMode = true;
        quizCount = 0;
    }


    /*
     * Generates a random question
     * Sets correct answer for validation
     */
    private String generateQuestion() {

        // ================= OOPS TOPIC =================
        if (topic.equals("oops")) {

            int q = random.nextInt(3);

            switch (q) {

                case 0:
                    currentAnswer = "inheritance";
                    return "[OOPS] What concept allows one class to acquire properties of another?";

                case 1:
                    currentAnswer = "polymorphism";
                    return "[OOPS] What allows objects to take multiple forms?";

                case 2:
                    currentAnswer = "encapsulation";
                    return "[OOPS] What hides data using getters and setters?";
            }
        }

        // ================= BASICS TOPIC =================
        else if (topic.equals("basics")) {

            int q = random.nextInt(3);

            switch (q) {

                case 0:
                    currentAnswer = "jvm";
                    return "[Basics] What runs Java bytecode?";

                case 1:
                    currentAnswer = "main";
                    return "[Basics] What is the entry point method in Java?";

                case 2:
                    currentAnswer = "compiler";
                    return "[Basics] What converts Java code into bytecode?";
            }
        }

        return "";
    }
    
    private String generateReport() {

        double percentage = 0;

        if (totalQuestions > 0) {
            percentage = (correctAnswers * 100.0) / totalQuestions;
        }

        return "===== Progress Report =====\n"
                + "Topic: " + topic + "\n"
                + "Difficulty: " + difficulty + "\n"
                + "Total Questions: " + totalQuestions + "\n"
                + "Correct Answers: " + correctAnswers + "\n"
                + "Accuracy: " + String.format("%.2f", percentage) + "%";
    }
    
    //save progress Save Score to File (Persistent Storage)
    private void saveProgress() {

        try {
            FileWriter writer = new FileWriter("progress.txt");

            writer.write("Total Questions: " + totalQuestions + "\n");
            writer.write("Correct Answers: " + correctAnswers + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving progress.");
        }
    }
}