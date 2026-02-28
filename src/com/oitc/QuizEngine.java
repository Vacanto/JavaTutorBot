package com.oitc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizEngine {

    private List<Question> questions;

    public QuizEngine() {
        questions = new ArrayList<>();

        questions.add(new Question(
                "Which keyword is used to define a class in Java?",
                new String[]{"class", "define", "struct", "object"},
                1
        ));

        questions.add(new Question(
                "Which loop executes at least once?",
                new String[]{"for", "while", "do-while", "none"},
                3
        ));
    }

    public int startQuiz() {

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {

            System.out.println("\n" + question.getQuestion());

            String[] options = question.getOptions();

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Enter your answer: ");
            int answer = scanner.nextInt();

            if (answer == question.getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
        }

        System.out.println("\nQuiz Finished. Your Score: " + score + "/" + questions.size());

        return score;
    }
}