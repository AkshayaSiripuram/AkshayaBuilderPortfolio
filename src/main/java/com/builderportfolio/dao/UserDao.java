package com.builderportfolio.dao;

// DAO class responsible for storing and retrieving User entities in memory

import com.builderportfolio.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserDao {

    // Thread-safe map that stores userId as key and User object as value
    public static Map<String, User> users = new ConcurrentHashMap<>();

    // Checks if a user already exists based on email and returns the userId if found
    public static String exists(String userEmail) {

        // Iterates through all users to find a matching email
        for (User user : users.values()) {
            if (user.getUserEmail().equals(userEmail)) {
                return user.getUserId();    // returns existing userId if email is already registered
            }
        }
        return null; // returns null when no user with the given email exists
    }

    // Inserts a new user atomically only if the userId is not already present
    public static void insertUser(String userId, User user) {
        users.putIfAbsent(user.getUserId(), user);
    }

    // Retrieves a user using the unique userId
    public static User getUserbyId(String userId) {
        return users.get(userId);
    }

}
