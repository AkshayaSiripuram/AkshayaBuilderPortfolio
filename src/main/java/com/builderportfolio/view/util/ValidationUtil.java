package com.builderportfolio.view.util;

/**
 * Utility class for validating user roles based on their userId.
 * <p>
 * Provides methods to determine if a user is a Project Manager or a Builder
 * by checking the prefix of the userId.
 * </p>
 */
public class ValidationUtil {

    /**
     * Determines if the given userId corresponds to a Project Manager.
     * <p>
     * Project Manager IDs start with the prefix "M".
     * </p>
     *
     * @param userId the user ID to check
     * @return true if the userId represents a Project Manager, false otherwise
     */
    public static boolean isManager(String userId) {
        return userId != null && userId.startsWith("M");
    }

    /**
     * Determines if the given userId corresponds to a Builder.
     * <p>
     * Builder IDs start with the prefix "B".
     * </p>
     *
     * @param userId the user ID to check
     * @return true if the userId represents a Builder, false otherwise
     */
    public static boolean isBuilder(String userId) {
        return userId != null && userId.startsWith("B");
    }
}
