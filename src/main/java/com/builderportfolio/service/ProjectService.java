package com.builderportfolio.service;

import com.builderportfolio.dao.ProjectDao;
import com.builderportfolio.model.Client;
import com.builderportfolio.model.Project;
import com.builderportfolio.model.ProjectStatus;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling project-related business logic.
 * <p>
 * This class interacts with DAO classes to create, retrieve, update, and delete projects,
 * as well as manage project assignments to builders and managers.
 * </p>
 */
public class ProjectService {

    /**
     * Creates a new project, persists it in the ProjectDao, and assigns it to
     * the specified manager and builder.
     *
     * @param projectName the name of the project
     * @param description the project description
     * @param startDate   the start date of the project
     * @param endDate     the end date of the project
     * @param status      the initial status of the project
     * @param client      the client associated with the project
     * @param builderId   the ID of the builder assigned to the project
     * @param managerId   the ID of the manager assigned to the project
     */
    public void createProject(String projectName, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status, Client client, String builderId, String managerId) {
        Project project = new Project(projectName, description, startDate, endDate, status, client, builderId, managerId);

        ProjectDao.saveProject(project); // persist project

        ManagerDao.addProjectToManager(managerId, project.getProjectId()); // assign project to manager
        BuilderDao.addProjectToBuilder(builderId, project.getProjectId()); // assign project to builder
    }

    /**
     * Retrieves all projects assigned to a specific manager.
     *
     * @param managerId the ID of the manager
     * @return a list of projects assigned to the manager; skips projects that were deleted
     */
    public List<Project> getManagerProjects(String managerId) {
        List<Long> projectIds = ManagerDao.getProjectIds(managerId);
        List<Project> projects = new ArrayList<>();

        for (Long id : projectIds) {
            Project project = ProjectDao.getProjectById(id);
            if (project != null) { // skip if project was deleted
                projects.add(project);
            }
        }
        return projects;
    }

    /**
     * Retrieves all projects assigned to a specific builder.
     *
     * @param builderId the ID of the builder
     * @return a list of projects assigned to the builder; skips projects that were deleted
     */
    public List<Project> getBuilderProjects(String builderId) {
        List<Long> projectIds = BuilderDao.getProjectIds(builderId);
        List<Project> projects = new ArrayList<>();

        for (Long id : projectIds) {
            Project project = ProjectDao.getProjectById(id);
            if (project != null) { // skip if project was deleted
                projects.add(project);
            }
        }
        return projects;
    }

    /**
     * Updates the status of a project if the specified builder is authorized to do so.
     *
     * @param builderId the ID of the builder attempting to update the project
     * @param projectId the ID of the project to update
     * @param newStatus the new status to set
     * @return true if the update was successful; false if the project does not exist or the builder is unauthorized
     */
    public boolean updateProjectStatus(String builderId, long projectId, ProjectStatus newStatus) {
        Project project = ProjectDao.getProjectById(projectId);

        if (project == null) { // project does not exist
            return false;
        }

        if (!builderId.equals(project.getAssignedBuilder())) { // builder not authorized
            return false;
        }

        project.setProjectStatus(newStatus); // update status
        return true;
    }

    /**
     * Deletes a project if the specified manager is authorized to do so.
     * <p>
     * The project is removed from the ProjectDao and also from the assigned manager's
     * and builder's project lists.
     * </p>
     *
     * @param managerId the ID of the manager attempting to delete the project
     * @param projectId the ID of the project to delete
     * @return true if the deletion was successful; false if the project does not exist or the manager is unauthorized
     */
    public boolean deleteProject(String managerId, long projectId) {
        Project project = ProjectDao.getProjectById(projectId);
        if (project == null) return false; // project does not exist

        if (!managerId.equals(project.getAssignedProjectManager())) return false; // manager not authorized

        ProjectDao.removeProject(projectId); // remove project from DAO

        ManagerDao.removeProjectFromManager(managerId, projectId); // remove project from manager's list

        String builderId = project.getAssignedBuilder();
        if (builderId != null) {
            BuilderDao.removeProjectFromBuilder(builderId, projectId); // remove project from builder's list
        }

        return true;
    }
}
