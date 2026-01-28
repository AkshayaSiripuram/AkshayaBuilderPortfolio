package com.builderportfolio.view.util;

import com.builderportfolio.service.ProjectService;
import com.builderportfolio.service.UserService;

/**
 * Factory class providing centralized access to singleton service instances.
 * <p>
 * Ensures consistent usage of the same {@link UserService} and {@link ProjectService}
 * across the application.
 * </p>
 */
public class ServiceFactory {

    /** Singleton instance of UserService */
    private static final UserService userService = new UserService();

    /** Singleton instance of ProjectService */
    private static final ProjectService projectService = new ProjectService();

    /**
     * Returns the singleton {@link UserService} instance.
     *
     * @return the UserService singleton
     */
    public static UserService userService() {
        return userService;
    }

    /**
     * Returns the singleton {@link ProjectService} instance.
     *
     * @return the ProjectService singleton
     */
    public static ProjectService projectService() {
        return projectService;
    }
}
