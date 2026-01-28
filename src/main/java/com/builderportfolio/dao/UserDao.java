package com.builderportfolio.dao;

// DAO class responsible for storing and retrieving User entities in memory

import com.builderportfolio.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data Access Object (DAO) for managing User entities in memory.
 * <p>
 * This class maintains a thread-safe map of users where each user ID
 * is associated with its corresponding User object.
 * </p>
 * <p>
 * All methods are static and thread-safe to allow concurrent access from multiple threads.
 * </p>
 */
public class UserDao {

    /**
     * Thread-safe map storing user IDs as keys and User objects as values.
     */
    public static Map<String, User> users = new ConcurrentHashMap<>();

    /**
     * Checks if a user already exists based on their email.
     *
     * @param userEmail the email of the user to check
     * @return the userId if a user with the given email exists; otherwise, returns null
     */
    public static String exists(String userEmail) {

        // Iterates through all users to find a matching email
        for (User user : users.values()) {
            if (user.getUserEmail().equals(userEmail)) {
                return user.getUserId();    // returns existing userId if email is already registered
            }
        }
        return null; // returns null when no user with the given email exists
    }

    /**
     * Inserts a new user into the in-memory store.
     * <p>
     * The user is only added if the userId is not already present.
     * </p>
     *
     * @param userId the unique identifier of the user
     * @param user   the User object to insert
     */
    public static void insertUser(String userId, User user) {
        users.putIfAbsent(user.getUserId(), user);
    }

    /**
     * Retrieves a user from the store using the unique userId.
     *
     * @param userId the unique identifier of the user
     * @return the User object if found; otherwise, returns null
     */
    public static User getUserbyId(String userId) {
        return users.get(userId);
    }

}
