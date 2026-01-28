package com.builderportfolio.view.util;

import com.builderportfolio.service.ProjectService;
import com.builderportfolio.service.UserService;

// Factory class providing centralized access to singleton service instances
// Ensures consistent usage of the same UserService and ProjectService across the application
public class ServiceFactory {

    private static final UserService userService = new UserService(); // singleton UserService
    private static final ProjectService projectService = new ProjectService(); // singleton ProjectService

    // Returns the singleton UserService instance
    public static UserService userService() {
        return userService;
    }

    // Returns the singleton ProjectService instance
    public static ProjectService projectService() {
        return projectService;
    }
}
