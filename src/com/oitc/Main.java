package com.oitc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JavaTutorBot bot = new JavaTutorBot();

        System.out.println("=== Java Coding Tutor Bot ===");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Goodbye! Keep coding!");
                break;
            }

            String response = bot.getResponse(input);
            System.out.println("Bot: " + response);
        }

        scanner.close();
    }
}