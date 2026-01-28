package com.builderportfolio.dao;

// DAO class responsible for storing and managing Project entities in memory

import com.builderportfolio.model.Project;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data Access Object (DAO) for managing Project entities in memory.
 * <p>
 * This class maintains a thread-safe map of projects where each project ID
 * is associated with its corresponding Project object.
 * </p>
 * <p>
 * All methods are static and thread-safe to allow concurrent access from multiple threads.
 * </p>
 */
public class ProjectDao {

    /**
     * Thread-safe map storing project IDs as keys and Project objects as values.
     */
    public static Map<Long, Project> projectsList = new ConcurrentHashMap<>();

    /**
     * Saves a new project or updates an existing project in the in-memory store.
     *
     * @param project the Project object to save or update
     */
    public static void saveProject(Project project) {
        projectsList.put(project.getProjectId(), project);
    }

    /**
     * Retrieves a project from the store using its unique project ID.
     *
     * @param projectId the unique identifier of the project
     * @return the Project object if found; otherwise, returns null
     */
    public static Project getProjectById(Long projectId) {
        return projectsList.get(projectId);
    }

    /**
     * Removes a project from the in-memory store.
     * <p>
     * Typically called when a project is deleted.
     * </p>
     *
     * @param projectId the unique identifier of the project to remove
     */
    public static void removeProject(Long projectId) {
        projectsList.remove(projectId);
    }

}

