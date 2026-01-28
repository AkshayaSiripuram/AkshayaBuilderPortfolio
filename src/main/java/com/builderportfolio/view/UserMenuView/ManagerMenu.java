package com.builderportfolio.view.UserMenuView;

import com.builderportfolio.view.ProjectView;
import com.builderportfolio.view.util.*;

/**
 * Console menu for Project Manager users.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Create new projects</li>
 *   <li>Delete managed projects</li>
 *   <li>View all assigned projects</li>
 *   <li>View logged-in manager details</li>
 *   <li>Handle logout</li>
 * </ul>
 * </p>
 */
public class ManagerMenu {

    /**
     * Displays the Manager menu and handles user interaction.
     * <p>
     * Menu Options:
     * <ol>
     *   <li>Add a new project</li>
     *   <li>Delete an existing project</li>
     *   <li>View projects managed by the user</li>
     *   <li>View manager profile details</li>
     *   <li>Logout</li>
     * </ol>
     * </p>
     *
     * The menu continues to display until the manager chooses to logout.
     */
    public void show() {
        int choice;
        do {
            System.out.println("\nManager Menu");
            System.out.println("1. Add Project");
            System.out.println("2. Delete Project");
            System.out.println("3. View Projects");
            System.out.println("4. View My Details");
            System.out.println("5. Logout");

            choice = InputUtil.readInt(); // safely read numeric input

            switch (choice) {
                case 1:
                    // Navigate to project creation flow
                    new ProjectView().addProject();
                    break;

                case 2:
                    // Navigate to project deletion flow
                    new ProjectView().deleteProject();
                    break;

                case 3:
                    // Display all projects assigned to the logged-in manager
                    ServiceFactory.projectService()
                            .getManagerProjects(Session.getUser().getUserId())
                            .forEach(System.out::println);
                    break;

                case 4:
                    // Display logged-in manager's details
                    System.out.println(Session.getUser());
                    break;

                case 5:
                    // Logout and clear session
                    Session.logout();
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5); // loop until logout
    }
}
