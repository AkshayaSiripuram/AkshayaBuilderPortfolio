package com.builderportfolio.view;

import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.model.User;
import com.builderportfolio.view.UserMenuView.BuilderMenu;
import com.builderportfolio.view.UserMenuView.ManagerMenu;
import com.builderportfolio.view.util.*;

/**
 * Handles user registration flow from the console UI.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Collect user registration details</li>
 *   <li>Create a {@link User} object</li>
 *   <li>Invoke service layer to register the user</li>
 *   <li>Automatically log in the user after successful registration</li>
 *   <li>Route the user to the appropriate menu based on role</li>
 * </ul>
 * </p>
 */
public class RegisterView {

    /**
     * Prompts the user for registration details and registers them.
     * <p>
     * Flow:
     * <ol>
     *   <li>Read user input (name, email, phone, role, experience, password)</li>
     *   <li>Create {@link User} object</li>
     *   <li>Call {@link com.builderportfolio.service.UserService#register(User)}</li>
     *   <li>Store user in {@link Session}</li>
     *   <li>Redirect to Manager or Builder menu</li>
     * </ol>
     * </p>
     *
     * Handles {@link UserAlreadyExistsException}
     * when a user with the same email already exists.
     */
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

            // Create User domain object
            User user = new User(name, email, phone, exp, password, role);

            // Register user via service layer
            ServiceFactory.userService().register(user);

            System.out.println("Registered Successfully");
            System.out.println("User ID: " + user.getUserId());

            // Automatically log in user after registration
            Session.login(user);

            // Navigate user to role-based menu
            if (role == 1) {
                new ManagerMenu().show();
            } else {
                new BuilderMenu().show();
            }

        } catch (UserAlreadyExistsException e) {
            // Handles duplicate registration attempts
            System.out.println(e.getMessage());
        }
    }
}

