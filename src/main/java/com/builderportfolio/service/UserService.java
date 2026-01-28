package com.builderportfolio.service;

import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;
import com.builderportfolio.dao.UserDao;

import java.util.logging.Logger;

// Service class handling user-related business logic: registration, login, and fetching user details
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    // Registers a new user and assigns role-specific mappings
    public void register(User user) throws UserAlreadyExistsException {

        logger.info("Registration attempt...");

        // Check if a user with the same email already exists
        if (UserDao.exists(user.getUserEmail()) != null) {
            logger.warning("Registration failed ");
            throw new UserAlreadyExistsException("User already exists with ID: " + UserDao.exists(user.getUserEmail()));
        }

        // Insert user into in-memory UserDao
        UserDao.insertUser(user.getUserId(), user);
        logger.info("User inserted into UserDao: " + user.getUserId());

        // Role-based DAO insertion
        if (user.getSelectedRole() == 1) { // Project Manager
            ManagerDao.insertManager(user.getUserId());
            logger.info("User added as Project Manager: " + user.getUserId());
        } else { // Builder
            BuilderDao.insertBuilder(user.getUserId());
            logger.info("User added as Builder: " + user.getUserId());
        }

        logger.info("Registration successful for userId: " + user.getUserId());
    }

    // Authenticates a user based on userId and password
    public User login(String userId, String password) throws UserNotFoundException {

        logger.info("LoginView attempt for userId: " + userId);

        User user = UserDao.getUserbyId(userId);

        if (user == null) { // User not found
            logger.warning("LoginView failed - user not found: " + userId);
            throw new UserNotFoundException("User not found with id " + userId);
        }

        if (!user.getPassword().equals(password)) { // Password mismatch
            logger.warning("Invalid password for userId: " + userId);
            return null;
        }

        logger.info("LoginView successful for userId: " + userId);
        return user;
    }

    // Fetches user details by userId (used for menus or info display)
    public User fetchDetails(String userId) {
        return UserDao.getUserbyId(userId);
    }
}
