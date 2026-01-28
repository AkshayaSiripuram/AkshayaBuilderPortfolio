package com.builderportfolio.exception;

/**
 * Custom exception thrown when attempting to create or register a user
 * that already exists in the system.
 * <p>
 * This exception extends {@link RuntimeException} and can be used
 * to indicate duplicate user registration scenarios.
 * </p>
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
