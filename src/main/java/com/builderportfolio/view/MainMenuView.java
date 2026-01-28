package com.builderportfolio.view;

import com.builderportfolio.view.util.InputUtil;

/**
 * Displays the main menu of the application and handles top-level user choices.
 * <p>
 * Provides options for user registration, login, and exiting the application.
 * The menu runs in a loop until the user chooses to exit.
 * </p>
 */
public class MainMenuView {

    /**
     * Main menu loop that presents options for registration, login, and exit.
     * <p>
     * Based on the user's choice:
     * <ul>
     *     <li>1 - Navigates to {@link RegisterView} for user registration</li>
     *     <li>2 - Navigates to {@link LoginView} for user login</li>
     *     <li>3 - Exits the application</li>
     *     <li>Other - Displays an "Invalid choice" message</li>
     * </ul>
     * </p>
     */
    public void show() {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = InputUtil.readInt();

            switch (choice) {
                case 1:
                    new RegisterView().register(); // navigate to registration
                    break;
                case 2:
                    new LoginView().login(); // navigate to login
                    break;
                case 3:
                    System.out.println("Exiting..."); // exit application
                    return;
                default:
                    System.out.println("Invalid choice"); // invalid menu selection
            }
        }
    }
}
