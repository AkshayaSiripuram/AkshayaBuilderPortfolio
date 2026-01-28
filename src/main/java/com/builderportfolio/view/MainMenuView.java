package com.builderportfolio.view;

import com.builderportfolio.view.util.InputUtil;

// Displays the main menu of the application and handles top-level user choices
public class MainMenuView {

    // Main menu loop for registration, login, and exit
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
