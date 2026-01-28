package com.builderportfolio.view.util;

import com.builderportfolio.model.User;

/**
 * Manages the current logged-in user session in the application.
 * <p>
 * Stores the active {@link User} object during the session and provides
 * methods to login, retrieve, and logout the user.
 * </p>
 */
public class Session {

    /** Stores the currently logged-in user */
    private static User loggedInUser;

    /**
     * Sets the logged-in user when a login occurs.
     *
     * @param user the User object to mark as logged in
     */
    public static void login(User user) {
        loggedInUser = user;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the active User object, or null if no user is logged in
     */
    public static User getUser() {
        return loggedInUser;
    }

    /**
     * Clears the current session, effectively logging out the user.
     */
    public static void logout() {
        loggedInUser = null;
    }
}

