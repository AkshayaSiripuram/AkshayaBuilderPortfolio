package com.builderportfolio.model;

import java.time.LocalDate;

/**
 * Represents a project with its details, including associated client,
 * assigned builder, project manager, and project status.
 * <p>
 * Each project has a unique project ID that is auto-incremented for every new instance.
 * </p>
 */
public class Project {

    /** Tracks the last assigned project ID for auto-increment purposes */
    static long lastProjectId = 0;

    private long projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private ProjectStatus projectStatus;
    private Client assignedClient;
    private String assignedBuilder;
    private String assignedProjectManager;

    /**
     * Constructs a new Project with the specified details.
     * <p>
     * The project ID is automatically assigned by incrementing the last generated ID.
     * </p>
     *
     * @param projectName            the name of the project
     * @param projectDescription     the description of the project
     * @param projectStartDate       the start date of the project
     * @param projectEndDate         the end date of the project
     * @param projectStatus          the current status of the project
     * @param assignedClient         the client associated with the project
     * @param assignedBuilder        the builder assigned to the project
     * @param assignedProjectManager the project manager assigned to the project
     */
    public Project(String projectName, String projectDescription, LocalDate projectStartDate, LocalDate projectEndDate, ProjectStatus projectStatus, Client assignedClient, String assignedBuilder, String assignedProjectManager) {
        projectId = ++lastProjectId; // auto-increment project ID
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectStatus = projectStatus;
        this.assignedClient = assignedClient;
        this.assignedBuilder = assignedBuilder;
        this.assignedProjectManager = assignedProjectManager;
    }

    /**
     * Returns the unique ID of the project.
     *
     * @return the project ID
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * Returns the name of the project.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Updates the name of the project.
     *
     * @param projectName the new project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the description of the project.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Updates the description of the project.
     *
     * @param projectDescription the new project description
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Returns the start date of the project.
     *
     * @return the project start date
     */
    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * Updates the start date of the project.
     *
     * @param projectStartDate the new start date
     */
    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * Returns the end date of the project.
     *
     * @return the project end date
     */
    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * Updates the end date of the project.
     *
     * @param projectEndDate the new end date
     */
    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * Returns the current status of the project.
     *
     * @return the project status
     */
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    /**
     * Updates the status of the project.
     *
     * @param projectStatus the new project status
     */
    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Returns the client assigned to the project.
     *
     * @return the assigned client
     */
    public Client getAssignedClient() {
        return assignedClient;
    }

    /**
     * Updates the client assigned to the project.
     *
     * @param assignedClient the new client
     */
    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    /**
     * Returns the builder assigned to the project.
     *
     * @return the assigned builder
     */
    public String getAssignedBuilder() {
        return assignedBuilder;
    }

    /**
     * Updates the builder assigned to the project.
     *
     * @param assignedBuilder the new builder
     */
    public void setAssignedBuilder(String assignedBuilder) {
        this.assignedBuilder = assignedBuilder;
    }

    /**
     * Returns the project manager assigned to the project.
     *
     * @return the assigned project manager
     */
    public String getAssignedProjectManager() {
        return assignedProjectManager;
    }

    /**
     * Updates the project manager assigned to the project.
     *
     * @param assignedProjectManager the new project manager
     */
    public void setAssignedProjectManager(String assignedProjectManager) {
        this.assignedProjectManager = assignedProjectManager;
    }

    /**
     * Returns a string representation of the project, including ID, name, description,
     * dates, status, client, builder, and project manager.
     *
     * @return string representation of the project
     */
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", projectStatus=" + projectStatus +
                ", assignedClient=" + assignedClient +
                ", assignedBuilder=" + assignedBuilder +
                ", assignedProjectManager=" + assignedProjectManager +
                '}';
    }
}

