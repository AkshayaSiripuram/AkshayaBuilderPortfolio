package com.builderportfolio.view;

import com.builderportfolio.model.*;
import com.builderportfolio.view.util.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles all project-related interactions in the console UI.
 * <p>
 * Provides methods for adding new projects, updating project status, and deleting projects.
 * Interacts with {@link ServiceFactory#projectService()} for all business logic operations
 * and uses {@link Session} to identify the currently logged-in user.
 * </p>
 */
public class ProjectView {

    /**
     * Prompts the manager to input project details and creates a new project.
     * <p>
     * Collects project name, description, start/end dates, client details, status,
     * and builder assignment. Validates the builder exists before creating the project.
     * Uses {@link ServiceFactory#projectService()} to persist the project.
     * </p>
     */
    public void addProject() {

        System.out.println("Project Name:");
        String projectName = InputUtil.readLine();

        System.out.println("Description:");
        String description = InputUtil.readLine();

        System.out.println("Start Date (YYYY-MM-DD):");
        LocalDate startDate = LocalDate.parse(InputUtil.read()); // parse start date

        System.out.println("End Date (YYYY-MM-DD):");
        LocalDate endDate = LocalDate.parse(InputUtil.read()); // parse end date

        // Collect client information
        System.out.println("Client Name:");
        String clientName = InputUtil.readLine();
        System.out.println("Client Email:");
        String clientEmail = InputUtil.read();
        System.out.println("Client Phone:");
        String clientPhone = InputUtil.read();

        Client client = new Client(clientName, clientEmail, clientPhone);

        System.out.println("Status (UPCOMING / IN_PROGRESS / COMPLETED):");
        ProjectStatus projectStatus = ProjectStatus.valueOf(InputUtil.read().toUpperCase());

        System.out.println("Builder ID:");
        String builderId = InputUtil.read();

        // Validate builder existence before assigning
        if (ServiceFactory.userService().fetchDetails(builderId) == null) {
            System.out.println("Builder with id " + builderId + " does not exist!!");
            return;
        }

        // Create project via service
        ServiceFactory.projectService().createProject(projectName, description, startDate, endDate, projectStatus, client, builderId, Session.getUser().getUserId());

        System.out.println("Project Created");
    }

    /**
     * Allows a builder to update the status of one of their assigned projects.
     * <p>
     * Displays all projects assigned to the currently logged-in builder,
     * prompts for the project ID and new status, and attempts to update the project
     * using {@link ServiceFactory#projectService()}.
     * </p>
     */
    public void updateProjectStatus() {
        List<Project> projects = ServiceFactory.projectService().getBuilderProjects(Session.getUser().getUserId());

        projects.forEach(System.out::println); // display assigned projects

        System.out.println("Project ID:");
        long projectId = InputUtil.readInt(); // read project ID to update

        System.out.println("New Status:");
        ProjectStatus newProjectStatus = ProjectStatus.valueOf(InputUtil.read().toUpperCase());

        // Attempt update and report success/failure
        boolean updated = ServiceFactory.projectService().updateProjectStatus(Session.getUser().getUserId(), projectId, newProjectStatus);

        System.out.println(updated ? "Updated Successfully" : "Failed to Update - Invalid Authentication");
    }

    /**
     * Allows a manager to delete a project they manage.
     * <p>
     * Prompts the manager for the project ID, deletes the project via
     * {@link ServiceFactory#projectService()}, and reports success or failure.
     * </p>
     */
    public void deleteProject() {
        System.out.println("Project ID:");
        long projectId = InputUtil.readInt(); // read project ID to delete

        boolean deleted = ServiceFactory.projectService().deleteProject(Session.getUser().getUserId(), projectId);

        System.out.println(deleted ? "Deleted Project Successfully" : "Failed to delete the Project"); // report result
    }
}
