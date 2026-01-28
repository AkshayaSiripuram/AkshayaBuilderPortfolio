package com.builderportfolio.dao;

// DAO class responsible for storing and managing Project entities in memory

import com.builderportfolio.model.Project;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectDao {

    // Thread-safe map that stores projectId as key and Project object as value
    public static Map<Long, Project> projectsList = new ConcurrentHashMap<>();

    // Saves or updates a project in the in-memory project store
    public static void saveProject(Project project) {
        projectsList.put(project.getProjectId(), project);
    }

    // Retrieves a project using its unique project ID
    public static Project getProjectById(Long projectId) {
        return projectsList.get(projectId);
    }

    // Removes a project from the store when it is deleted
    public static void removeProject(Long projectId) {
        projectsList.remove(projectId);
    }

}
