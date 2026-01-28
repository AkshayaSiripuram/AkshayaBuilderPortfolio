package com.builderportfolio.dao;

// DAO class responsible for maintaining builder-to-project mapping in memory

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BuilderDao {

    // Thread-safe map that stores builderId as key and list of projectIds assigned to that builder
    public static Map<String, List<Long>> builderProjects = new ConcurrentHashMap<>();

    // Inserts a new builder entry with an empty project list when a builder is registered
    public static void insertBuilder(String builderId) {
        builderProjects.put(builderId, new CopyOnWriteArrayList<>());
    }

    // Adds a project ID to the corresponding builder, creating the list if builder is not already present
    public static void addProjectToBuilder(String builderId, Long projectId) {
        if (builderId == null) throw new IllegalArgumentException("Builder ID cannot be null");
        builderProjects.computeIfAbsent(builderId, k -> new CopyOnWriteArrayList<>()).add(projectId);
    }


    // Retrieves all project IDs assigned to a builder or returns an empty list if builder does not exist
    public static List<Long> getProjectIds(String builderId) {
        return builderProjects.getOrDefault(builderId, new CopyOnWriteArrayList<>());
    }

    // Removes a specific project from a builder when the project is deleted or reassigned
    public static void removeProjectFromBuilder(String builderId, long projectId) {
        List<Long> projects = builderProjects.get(builderId);       // fetches the project list for the builder
        if (projects != null) {                 // checks if builder exists before removal
            projects.remove(String.valueOf(projectId));         // removes the project ID from the builder's list
        }
    }
}
