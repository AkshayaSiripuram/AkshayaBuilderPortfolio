package com.builderportfolio.view.util;

// Utility class for validating user roles based on userId
public class ValidationUtil {

    // Returns true if the userId corresponds to a Project Manager
    public static boolean isManager(String userId) {
        return userId != null && userId.startsWith("M");
    }

    // Returns true if the userId corresponds to a Builder
    public static boolean isBuilder(String userId) {
        return userId != null && userId.startsWith("B");
    }
}
