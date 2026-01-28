
package com.builderportfolio.view.util;

import java.util.Scanner;

/**
 * Utility class for standardized input reading from the console.
 * <p>
 * Provides methods to safely read integers, single words, and full lines from
 * user input while handling invalid inputs gracefully.
 * </p>
 */
public class InputUtil {

    /** Single Scanner instance used for all console input operations */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer from user input.
     * <p>
     * If the user enters invalid input, the method repeatedly prompts
     * until a valid integer is entered.
     * </p>
     *
     * @return the integer entered by the user
     */
    public static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number:");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }

    /**
     * Reads a single word (token) from user input.
     *
     * @return the word entered by the user
     */
    public static String read() {
        return scanner.next();
    }

    /**
     * Reads a full line from user input.
     * <p>
     * Consumes any leftover newline characters to avoid skipping input.
     * </p>
     *
     * @return the full line entered by the user
     */
    public static String readLine() {
        scanner.nextLine(); // consume leftover newline
        return scanner.nextLine();
    }
}
