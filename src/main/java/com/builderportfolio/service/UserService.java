package com.builderportfolio.service;

import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;
import com.builderportfolio.dao.UserDao;

import java.util.logging.Logger;

/**
 * Service class handling user-related business logic, including registration,
 * login, and fetching user details.
 * <p>
 * This class interacts with DAO classes to manage users and assign them to
 * role-specific mappings (Project Manager or Builder).
 * </p>
 */
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    /**
     * Registers a new user and assigns role-specific DAO entries.
     *
     * @param user the User object containing registration details
     * @throws UserAlreadyExistsException if a user with the same email already exists
     */
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

    /**
     * Authenticates a user based on userId and password.
     *
     * @param userId   the unique identifier of the user
     * @param password the password entered by the user
     * @return the authenticated User object if login is successful
     * @throws UserNotFoundException if no user exists with the provided userId
     */
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

    /**
     * Fetches user details by userId.
     * <p>
     * This method is typically used for displaying user information in menus
     * or dashboards.
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return the User object if found; otherwise returns null
     */
    public User fetchDetails(String userId) {
        return UserDao.getUserbyId(userId);
    }
}

