package com.builderportfolio.view;

import com.builderportfolio.view.util.*;

// Displays the menu and handles interactions for Builder users
public class BuilderMenu {

    // Main menu loop for builder actions
    public void show() {
        int choice;
        do {
            System.out.println("\nBuilder Menu");
            System.out.println("1. Update Project");
            System.out.println("2. View My Projects");
            System.out.println("3. View My Details");
            System.out.println("4. Logout");

            choice = InputUtil.readInt(); // read user choice safely

            switch (choice) {
                case 1:
                    new ProjectView().updateProjectStatus(); // update status of assigned project
                    break;
                case 2:
                    // Display all projects assigned to this builder
                    ServiceFactory.projectService().getBuilderProjects(Session.getUser().getUserId()).forEach(System.out::println);
                    break;
                case 3:
                    // Display builder's own details
                    System.out.println(Session.getUser());
                    break;
                case 4:
                    Session.logout(); // clear session and log out
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4); // loop until user logs out
    }
}
