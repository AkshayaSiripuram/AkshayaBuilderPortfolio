package com.builderportfolio.view.util;

import com.builderportfolio.model.User;

// Manages the current logged-in user session in the application
public class Session {

    private static User loggedInUser; // stores the currently logged-in user

    // Sets the logged-in user when login occurs
    public static void login(User user) {
        loggedInUser = user;
    }

    // Retrieves the currently logged-in user
    public static User getUser() {
        return loggedInUser;
    }

    // Clears the current session on logout
    public static void logout() {
        loggedInUser = null;
    }
}
