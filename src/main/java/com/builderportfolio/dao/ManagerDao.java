package com.builderportfolio.dao;

// DAO class responsible for maintaining manager-to-project mapping in memory

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Data Access Object (DAO) for managing the mapping between managers and their assigned projects.
 * <p>
 * This class maintains an in-memory thread-safe map where each manager ID is associated
 * with a list of project IDs they are handling.
 * </p>
 * <p>
 * All methods are static and thread-safe to allow concurrent access from multiple threads.
 * </p>
 */
public class ManagerDao {

    /**
     * Thread-safe map storing manager IDs as keys and a list of assigned project IDs as values.
     */
    public static Map<String, List<Long>> managerProjects = new ConcurrentHashMap<>();

    /**
     * Inserts a new manager entry into the system with an empty project list.
     * <p>
     * If the manager already exists, this method does nothing.
     * </p>
     *
     * @param managerId the unique identifier of the manager to insert
     */
    public static void insertManager(String managerId) {
        managerProjects.putIfAbsent(managerId, new CopyOnWriteArrayList<>());
    }

    /**
     * Adds a project ID to the list of projects assigned to a specific manager.
     * <p>
     * If the manager does not exist, a new entry is created with the project ID added.
     * </p>
     *
     * @param managerId the unique identifier of the manager
     * @param projectId the ID of the project to assign
     */
    public static void addProjectToManager(String managerId, Long projectId) {
        managerProjects.computeIfAbsent(managerId, k -> new CopyOnWriteArrayList<>()) // ensures manager always has a project list
                .add(projectId);
    }

    /**
     * Retrieves all project IDs assigned to a given manager.
     *
     * @param managerId the unique identifier of the manager
     * @return a list of project IDs assigned to the manager; returns an empty list if the manager does not exist
     */
    public static List<Long> getProjectIds(String managerId) {
        return managerProjects.getOrDefault(managerId, new CopyOnWriteArrayList<>());
    }

    /**
     * Removes a specific project from a manager's list of assigned projects.
     * <p>
     * This is typically called when a project is deleted or reassigned to another manager.
     * </p>
     *
     * @param managerId the unique identifier of the manager
     * @param projectId the ID of the project to remove
     */
    public static void removeProjectFromManager(String managerId, long projectId) {
        List<Long> projects = managerProjects.get(managerId); // fetches the project list for the manager
        if (projects != null) {                               // checks if manager exists before attempting removal
            projects.remove(Long.valueOf(projectId));        // CORRECTED: properly removes from List<Long>
        }
    }
}

