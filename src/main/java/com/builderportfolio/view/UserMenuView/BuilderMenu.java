package com.builderportfolio.view.UserMenuView;

import com.builderportfolio.view.ProjectView;
import com.builderportfolio.view.util.*;

/**
 * Console menu for Builder users.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Display builder-specific menu options</li>
 *   <li>Allow project status updates</li>
 *   <li>View assigned projects</li>
 *   <li>View logged-in builder details</li>
 *   <li>Handle logout</li>
 * </ul>
 * </p>
 */
public class BuilderMenu {

    /**
     * Displays the Builder menu and handles user interaction.
     * <p>
     * Menu Options:
     * <ol>
     *   <li>Update project status</li>
     *   <li>View projects assigned to the builder</li>
     *   <li>View builder profile details</li>
     *   <li>Logout</li>
     * </ol>
     * </p>
     *
     * The menu continues to loop until the user chooses to logout.
     */
    public void show() {
        int choice;
        do {
            System.out.println("\nBuilder Menu");
            System.out.println("1. Update Project");
            System.out.println("2. View My Projects");
            System.out.println("3. View My Details");
            System.out.println("4. Logout");

            choice = InputUtil.readInt(); // safely read numeric input

            switch (choice) {
                case 1:
                    // Update status of a project assigned to this builder
                    new ProjectView().updateProjectStatus();
                    break;

                case 2:
                    // Display all projects assigned to the logged-in builder
                    ServiceFactory.projectService()
                            .getBuilderProjects(Session.getUser().getUserId())
                            .forEach(System.out::println);
                    break;

                case 3:
                    // Display logged-in builder's details
                    System.out.println(Session.getUser());
                    break;

                case 4:
                    // Logout and clear session
                    Session.logout();
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4); // loop until logout
    }
}
