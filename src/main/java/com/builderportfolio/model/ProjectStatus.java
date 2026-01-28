package com.builderportfolio.model;

/**
 * Enum representing the possible status values of a project.
 * <p>
 * The project can have one of the following statuses:
 * <ul>
 *     <li>{@link #UPCOMING} - Project is planned but not yet started</li>
 *     <li>{@link #IN_PROGRESS} - Project is currently in progress</li>
 *     <li>{@link #COMPLETED} - Project has been finished</li>
 * </ul>
 * </p>
 */
public enum ProjectStatus {
    /** Project is planned but not yet started */
    UPCOMING,

    /** Project is currently in progress */
    IN_PROGRESS,

    /** Project has been finished */
    COMPLETED
}
