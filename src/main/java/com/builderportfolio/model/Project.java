package com.builderportfolio.model;

import java.time.LocalDate;

// Represents a project with its details, client, assigned builder, and project manager
public class Project {

    static long lastProjectId = 0; // tracks last assigned project ID for auto-increment

    private long projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private ProjectStatus projectStatus;
    private Client assignedClient;
    private String assignedBuilder;
    private String assignedProjectManager;

    // Constructor initializes project details and auto-generates projectId
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

    public long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Client getAssignedClient() {
        return assignedClient;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public String getAssignedBuilder() {
        return assignedBuilder;
    }

    public void setAssignedBuilder(String assignedBuilder) {
        this.assignedBuilder = assignedBuilder;
    }

    public String getAssignedProjectManager() {
        return assignedProjectManager;
    }

    public void setAssignedProjectManager(String assignedProjectManager) {
        this.assignedProjectManager = assignedProjectManager;
    }

    // Returns a string representation of the project, useful for debugging and logging
    @Override
    public String toString() {
        return "Project{" + "projectId=" + projectId + ", projectName='" + projectName + '\'' + ", projectDescription='" + projectDescription + '\'' + ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", projectStatus=" + projectStatus + ", assignedClient=" + assignedClient + ", assignedBuilder=" + assignedBuilder + ", assignedProjectManager=" + assignedProjectManager + '}';
    }
}
