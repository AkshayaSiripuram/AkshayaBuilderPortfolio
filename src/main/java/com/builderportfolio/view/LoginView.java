package com.builderportfolio.view;

import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.view.util.*;

// Handles user login interactions and directs users to the appropriate menu based on role
public class LoginView {

    // Prompts user for credentials and logs them in
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
