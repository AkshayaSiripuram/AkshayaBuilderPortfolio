package com.builderportfolio.view.util;

import java.util.Scanner;

// Utility class for standardized input reading from console
public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    // Reads an integer from user input; repeatedly prompts until a valid integer is entered
    public static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number:");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }

    // Reads a single word (token) from user input
    public static String read() {
        return scanner.next();
    }

    // Reads a full line from user input
    public static String readLine() {
        scanner.nextLine(); // consume leftover newline
        return scanner.nextLine();
    }
}
