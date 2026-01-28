package com.builderportfolio.view;

import com.builderportfolio.view.util.*;

// Displays the menu and handles interactions for Project Manager users
public class ManagerMenu {

    // Main menu loop for manager actions
    public void show() {
        int choice;
        do {
            System.out.println("\nManager Menu");
            System.out.println("1. Add Project");
            System.out.println("2. Delete Project");
            System.out.println("3. View Projects");
            System.out.println("4. View My Details");
            System.out.println("5. Logout");

            choice = InputUtil.readInt();

            switch (choice) {
                case 1:
                    new ProjectView().addProject(); // navigate to add project
                    break;
                case 2:
                    new ProjectView().deleteProject(); // navigate to delete project
                    break;
                case 3:
                    // Display all projects assigned to this manager
                    ServiceFactory.projectService().getManagerProjects(Session.getUser().getUserId()).forEach(System.out::println);
                    break;
                case 4:
                    // Display manager's own details
                    System.out.println(Session.getUser());
                    break;
                case 5:
                    Session.logout(); // clear session and log out
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5); // loop until user logs out
    }
}
