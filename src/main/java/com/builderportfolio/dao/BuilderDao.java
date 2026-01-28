package com.builderportfolio.dao;

// DAO class responsible for maintaining builder-to-project mapping in memory

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Data Access Object (DAO) for managing the mapping between builders and their assigned projects.
 * <p>
 * This class maintains an in-memory thread-safe map where each builder ID is associated
 * with a list of project IDs assigned to that builder.
 * </p>
 * <p>
 * All methods are static and thread-safe to allow concurrent access from multiple threads.
 * </p>
 */
public class BuilderDao {

    /**
     * Thread-safe map storing builder IDs as keys and a list of assigned project IDs as values.
     */
    public static Map<String, List<Long>> builderProjects = new ConcurrentHashMap<>();

    /**
     * Inserts a new builder entry into the system with an empty project list.
     * <p>
     * This method is typically called when a new builder is registered.
     * </p>
     *
     * @param builderId the unique identifier of the builder to insert
     */
    public static void insertBuilder(String builderId) {
        builderProjects.put(builderId, new CopyOnWriteArrayList<>());
    }

    /**
     * Adds a project ID to the list of projects assigned to a specific builder.
     * <p>
     * If the builder does not exist, a new entry is created with the project ID added.
     * </p>
     *
     * @param builderId the unique identifier of the builder
     * @param projectId the ID of the project to assign
     * @throws IllegalArgumentException if builderId is null
     */
    public static void addProjectToBuilder(String builderId, Long projectId) {
        if (builderId == null) throw new IllegalArgumentException("Builder ID cannot be null");
        builderProjects.computeIfAbsent(builderId, k -> new CopyOnWriteArrayList<>()).add(projectId);
    }

    /**
     * Retrieves all project IDs assigned to a given builder.
     *
     * @param builderId the unique identifier of the builder
     * @return a list of project IDs assigned to the builder; returns an empty list if the builder does not exist
     */
    public static List<Long> getProjectIds(String builderId) {
        return builderProjects.getOrDefault(builderId, new CopyOnWriteArrayList<>());
    }

    /**
     * Removes a specific project from a builder's list of assigned projects.
     * <p>
     * This is typically called when a project is deleted or reassigned to another builder.
     * </p>
     *
     * @param builderId the unique identifier of the builder
     * @param projectId the ID of the project to remove
     */
    public static void removeProjectFromBuilder(String builderId, long projectId) {
        List<Long> projects = builderProjects.get(builderId);
        if (projects != null) {
            projects.remove(Long.valueOf(projectId)); // CORRECTED
        }
    }

}

