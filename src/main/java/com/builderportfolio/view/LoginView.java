package com.builderportfolio.view;

import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.view.UserMenuView.BuilderMenu;
import com.builderportfolio.view.UserMenuView.ManagerMenu;
import com.builderportfolio.view.util.*;

/**
 * Handles user login interactions and directs users to the appropriate menu based on role.
 * <p>
 * Prompts users for their credentials, authenticates them using {@link ServiceFactory#userService()},
 * stores the logged-in user in {@link Session}, and then navigates to either the
 * {@link ManagerMenu} or {@link BuilderMenu} based on their role.
 * </p>
 */
public class LoginView {

    /**
     * Prompts the user for credentials, performs authentication, and navigates
     * the user to the appropriate menu based on role.
     * <p>
     * If the user enters an incorrect password, a message is displayed.
     * If the user ID is not found, a {@link UserNotFoundException} message is displayed.
     * </p>
     */
    public void login() {
        try {
            System.out.print("Enter User ID: ");
            String userId = InputUtil.read();

            System.out.print("Enter Password: ");
            String password = InputUtil.read();

            // Authenticate user
            User user = ServiceFactory.userService().login(userId, password);

            if (user == null) { // password incorrect
                System.out.println("Incorrect password");
                return;
            }

            Session.login(user); // store logged-in user in session
            System.out.println("Login successful");

            // Direct user to the appropriate menu based on role
            if (ValidationUtil.isManager(userId)) {
                new ManagerMenu().show();
            } else {
                new BuilderMenu().show();
            }

        } catch (UserNotFoundException e) { // user ID not found
            System.out.println(e.getMessage());
        }
    }
}
