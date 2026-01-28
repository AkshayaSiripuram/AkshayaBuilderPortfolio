package com.builderportfolio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectStatusTest {

    @Test
    void testEnumValuesExist() {
        ProjectStatus[] statuses = ProjectStatus.values();

        assertEquals(3, statuses.length);
        assertEquals(ProjectStatus.UPCOMING, statuses[0]);
        assertEquals(ProjectStatus.IN_PROGRESS, statuses[1]);
        assertEquals(ProjectStatus.COMPLETED, statuses[2]);
    }

    @Test
    void testValueOfSuccess() {
        ProjectStatus status = ProjectStatus.valueOf("UPCOMING");
        assertEquals(ProjectStatus.UPCOMING, status);
    }

    @Test
    void testValueOfFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            ProjectStatus.valueOf("NOT_STARTED");
        });
    }

    @Test
    void testEnumName() {
        assertEquals("COMPLETED", ProjectStatus.COMPLETED.name());
    }
}
