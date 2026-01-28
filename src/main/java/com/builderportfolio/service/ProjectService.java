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

// Service class responsible for handling project-related business logic
public class ProjectService {

    // Creates a new project, saves it, and assigns it to manager and builder
    public void createProject(String projectName, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status, Client client, String builderId, String managerId) {
        Project project = new Project(projectName, description, startDate, endDate, status, client, builderId, managerId);

        ProjectDao.saveProject(project); // persist project

        ManagerDao.addProjectToManager(managerId, project.getProjectId()); // assign project to manager
        BuilderDao.addProjectToBuilder(builderId, project.getProjectId()); // assign project to builder
    }

    // Retrieves all projects assigned to a manager
    public List<Project> getManagerProjects(String managerId) {
        List<Long> projectIds = ManagerDao.getProjectIds(managerId);
        List<Project> projects = new ArrayList<>();

        for (Long id : projectIds) {
            Project project = ProjectDao.getProjectById(id);
            if (project != null) {                  // skip if project was deleted
                projects.add(project);
            }
        }
        return projects;
    }

    // Retrieves all projects assigned to a builder
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

    // Updates the status of a project if the builder is authorized
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

    // Deletes a project if the manager is authorized, and removes references from DAO and builder/manager lists
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
