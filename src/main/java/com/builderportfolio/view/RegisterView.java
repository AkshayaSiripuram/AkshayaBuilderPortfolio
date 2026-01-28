package com.builderportfolio.view;

import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.model.User;
import com.builderportfolio.view.util.*;

// Handles user registration interactions and role-based routing after signup
public class RegisterView {

    // Prompts user to enter registration details and registers them
    public void register() {
        try {
            System.out.println("Enter User name:");
            String name = InputUtil.readLine();

            System.out.println("Enter Email:");
            String email = InputUtil.read();

            System.out.println("Enter Phone:");
            String phone = InputUtil.read();

            System.out.println("Role: 1.Project Manager  2.Builder");
            int role = InputUtil.readInt();

            System.out.println("Experience:");
            int exp = InputUtil.readInt();

            System.out.println("Password:");
            String password = InputUtil.read();

            // Create User object
            User user = new User(name, email, phone, exp, password, role);

            // Register user via service layer
            ServiceFactory.userService().register(user);

            System.out.println("Registered Successfully");
            System.out.println("User ID: " + user.getUserId());

            Session.login(user); // log the user in after registration

            // Direct user to the appropriate menu based on role
            if (role == 1) {
                new ManagerMenu().show();
            } else {
                new BuilderMenu().show();
            }

        } catch (UserAlreadyExistsException e) { // handle duplicate user registration
            System.out.println(e.getMessage());
        }
    }
}
