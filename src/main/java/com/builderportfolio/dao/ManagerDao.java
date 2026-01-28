package com.builderportfolio.dao;

// DAO class responsible for maintaining manager-to-project mapping in memory

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ManagerDao {

    // Thread-safe map that stores managerId as key and list of projectIds handled by that manager
    public static Map<String, List<Long>> managerProjects = new ConcurrentHashMap<>();

    // Inserts a new manager entry with an empty project list if it does not already exist
    public static void insertManager(String managerId) {
        managerProjects.putIfAbsent(managerId, new CopyOnWriteArrayList<>());
    }

    // Associates a project ID with a manager, creating the list if manager entry is missing
    public static void addProjectToManager(String managerId, Long projectId) {
        managerProjects.computeIfAbsent(managerId, k -> new CopyOnWriteArrayList<>()) // ensures manager always has a project list
                .add(projectId);
    }

    // Returns all project IDs assigned to a manager or an empty list if manager does not exist
    public static List<Long> getProjectIds(String managerId) {
        return managerProjects.getOrDefault(managerId, new CopyOnWriteArrayList<>());
    }

    // Removes a project from the manager when the project is deleted or reassigned
    public static void removeProjectFromManager(String managerId, long projectId) {
        List<Long> projects = managerProjects.get(managerId); // fetches the project list for the manager
        if (projects != null) {          // checks if manager exists before attempting removal
            projects.remove(projectId);
        }
    }
}
