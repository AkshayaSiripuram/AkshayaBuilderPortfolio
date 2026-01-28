package com.builderportfolio;

import com.builderportfolio.view.MainMenuView;

/**
 * Entry point of the Builder Portfolio Management application.
 * <p>
 * Initializes the main menu view and starts the application loop.
 * </p>
 */
public class Main {

    /**
     * Main method that launches the application.
     * <p>
     * Creates an instance of {@link MainMenuView} and invokes its {@link MainMenuView#show()} method
     * to display the main menu and handle user interactions.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.show();
    }
}
