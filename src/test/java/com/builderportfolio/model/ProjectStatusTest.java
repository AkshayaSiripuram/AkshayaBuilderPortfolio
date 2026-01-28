//package com.builderportfolio.model;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProjectStatusTest {
//
//    @Test
//    void testEnumValuesExist() {
//        ProjectStatus[] statuses = ProjectStatus.values();
//
//        assertEquals(3, statuses.length);
//        assertEquals(ProjectStatus.UPCOMING, statuses[0]);
//        assertEquals(ProjectStatus.IN_PROGRESS, statuses[1]);
//        assertEquals(ProjectStatus.COMPLETED, statuses[2]);
//    }
//
//    @Test
//    void testValueOfSuccess() {
//        ProjectStatus status = ProjectStatus.valueOf("UPCOMING");
//        assertEquals(ProjectStatus.UPCOMING, status);
//    }
//
//    @Test
//    void testValueOfFailure() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            ProjectStatus.valueOf("NOT_STARTED");
//        });
//    }
//
//    @Test
//    void testEnumName() {
//        assertEquals("COMPLETED", ProjectStatus.COMPLETED.name());
//    }
//}


package com.builderportfolio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link ProjectStatus} enum.
 * <p>
 * This test suite validates the existence, order,
 * name resolution, and error handling behavior
 * of the ProjectStatus enum constants.
 * </p>
 */
public class ProjectStatusTest {

    /**
     * Verifies that all expected enum values exist
     * and are declared in the correct order.
     */
    @Test
    void testEnumValuesExist() {
        ProjectStatus[] statuses = ProjectStatus.values();

        assertEquals(3, statuses.length);
        assertEquals(ProjectStatus.UPCOMING, statuses[0]);
        assertEquals(ProjectStatus.IN_PROGRESS, statuses[1]);
        assertEquals(ProjectStatus.COMPLETED, statuses[2]);
    }

    /**
     * Ensures {@link ProjectStatus#valueOf(String)}
     * correctly returns the matching enum constant
     * for a valid input string.
     */
    @Test
    void testValueOfSuccess() {
        ProjectStatus status = ProjectStatus.valueOf("UPCOMING");
        assertEquals(ProjectStatus.UPCOMING, status);
    }

    /**
     * Validates that {@link ProjectStatus#valueOf(String)}
     * throws {@link IllegalArgumentException}
     * for an invalid enum name.
     */
    @Test
    void testValueOfFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            ProjectStatus.valueOf("NOT_STARTED");
        });
    }

    /**
     * Confirms that the {@link Enum#name()}
     * method returns the correct enum constant name.
     */
    @Test
    void testEnumName() {
        assertEquals("COMPLETED", ProjectStatus.COMPLETED.name());
    }
}
