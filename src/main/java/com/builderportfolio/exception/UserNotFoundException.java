package com.builderportfolio.exception;

/**
 * Custom exception thrown when a user is not found in the system.
 * <p>
 * This exception extends {@link RuntimeException} and can be used
 * to indicate that a requested user does not exist.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
