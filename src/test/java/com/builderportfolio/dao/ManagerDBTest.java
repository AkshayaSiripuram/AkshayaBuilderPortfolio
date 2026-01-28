//package com.builderportfolio.dao;
//
//import org.junit.jupiter.api.*;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ManagerDBTest {
//
//    @BeforeEach
//    void setUp() {
//        // Clear static data before each test to avoid interference
//        ManagerDao.managerProjects.clear();
//    }
//
//    @AfterEach
//    void tearDown() {
//        // Clear again for safety
//        ManagerDao.managerProjects.clear();
//    }
//
//
//    @Test
//    void testInsertManager_success() {
//        ManagerDao.insertManager("M1");
//
//        assertTrue(ManagerDao.managerProjects.containsKey("M1"));
//        assertNotNull(ManagerDao.getProjectIds("M1"));
//        assertTrue(ManagerDao.getProjectIds("M1").isEmpty());
//    }
//
//    @Test
//    void testAddProjectToManager_success() {
//        ManagerDao.insertManager("M1");
//
//        ManagerDao.addProjectToManager("M1", 1L);
//        ManagerDao.addProjectToManager("M1", 2L);
//
//        List<Long> projects = ManagerDao.getProjectIds("M1");
//
//        assertEquals(2, projects.size());
//        assertTrue(projects.contains(1L));
//        assertTrue(projects.contains(2L));
//    }
//
//    @Test
//    void testAddProjectToManager_withoutInsert_shouldCreateManager() {
//        ManagerDao.addProjectToManager("M2", 10L);
//
//        List<Long> projects = ManagerDao.getProjectIds("M2");
//
//        assertEquals(1, projects.size());
//        assertEquals(10L, projects.get(0));
//    }
//
//    @Test
//    void testGetProjectIds_forNonExistingManager() {
//        List<Long> projects = ManagerDao.getProjectIds("UNKNOWN");
//
//        assertNotNull(projects);
//        assertTrue(projects.isEmpty());
//    }
//
//    @Test
//    void testRemoveProjectFromManager_success() {
//        ManagerDao.insertManager("M3");
//        ManagerDao.addProjectToManager("M3", 100L);
//        ManagerDao.addProjectToManager("M3", 200L);
//
//        ManagerDao.removeProjectFromManager("M3", 100L);
//
//        List<Long> projects = ManagerDao.getProjectIds("M3");
//
//        assertEquals(1, projects.size());
//        assertFalse(projects.contains(100L));
//        assertTrue(projects.contains(200L));
//    }
//
//    @Test
//    void testRemoveProjectFromManager_nonExistingProject() {
//        ManagerDao.insertManager("M4");
//        ManagerDao.addProjectToManager("M4", 300L);
//
//        ManagerDao.removeProjectFromManager("M4", 999L);
//
//        List<Long> projects = ManagerDao.getProjectIds("M4");
//
//        assertEquals(1, projects.size());
//        assertTrue(projects.contains(300L));
//    }
//
//    @Test
//    void testRemoveProjectFromNonExistingManager_shouldNotThrowException() {
//        assertDoesNotThrow(() -> ManagerDao.removeProjectFromManager("NO_MANAGER", 1L));
//    }
//
//
//    @Test
//    void testAddProjectToManager_nullManager_shouldThrowException() {
//        assertThrows(NullPointerException.class, () -> ManagerDao.addProjectToManager(null, 1L));
//    }
//
//    @Test
//    void testInsertManager_nullManager_shouldThrowException() {
//        assertThrows(NullPointerException.class, () -> ManagerDao.insertManager(null));
//    }
//
//
//    @Test
//    void testAddDuplicateProjectId() {
//        ManagerDao.insertManager("M5");
//        ManagerDao.addProjectToManager("M5", 50L);
//        ManagerDao.addProjectToManager("M5", 50L); // duplicate
//
//        List<Long> projects = ManagerDao.getProjectIds("M5");
//
//        assertEquals(2, projects.size()); // duplicates allowed
//        assertEquals(50L, projects.get(0));
//        assertEquals(50L, projects.get(1));
//    }
//
//    @Test
//    void testEmptyStringManagerId() {
//        ManagerDao.addProjectToManager("", 123L);
//        List<Long> projects = ManagerDao.getProjectIds("");
//
//        assertEquals(1, projects.size());
//        assertEquals(123L, projects.get(0));
//    }
//
//}


package com.builderportfolio.dao;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link ManagerDao}.
 * <p>
 * This class validates manager-project relationships including
 * insertion, project assignment, removal, duplicate handling,
 * and edge cases.
 * </p>
 */
class ManagerDBTest {

    /**
     * Clears the static manager-project mapping
     * before each test to ensure test isolation.
     */
    @BeforeEach
    void setUp() {
        // Clear static data before each test to avoid interference
        ManagerDao.managerProjects.clear();
    }

    /**
     * Cleans up the static data store
     * after each test execution.
     */
    @AfterEach
    void tearDown() {
        // Clear again for safety
        ManagerDao.managerProjects.clear();
    }

    /**
     * Verifies successful insertion of a manager
     * with an initialized empty project list.
     */
    @Test
    void testInsertManager_success() {
        ManagerDao.insertManager("M1");

        assertTrue(ManagerDao.managerProjects.containsKey("M1"));
        assertNotNull(ManagerDao.getProjectIds("M1"));
        assertTrue(ManagerDao.getProjectIds("M1").isEmpty());
    }

    /**
     * Tests adding multiple projects to an existing manager.
     */
    @Test
    void testAddProjectToManager_success() {
        ManagerDao.insertManager("M1");

        ManagerDao.addProjectToManager("M1", 1L);
        ManagerDao.addProjectToManager("M1", 2L);

        List<Long> projects = ManagerDao.getProjectIds("M1");

        assertEquals(2, projects.size());
        assertTrue(projects.contains(1L));
        assertTrue(projects.contains(2L));
    }

    /**
     * Verifies that adding a project to a non-existing manager
     * automatically creates the manager entry.
     */
    @Test
    void testAddProjectToManager_withoutInsert_shouldCreateManager() {
        ManagerDao.addProjectToManager("M2", 10L);

        List<Long> projects = ManagerDao.getProjectIds("M2");

        assertEquals(1, projects.size());
        assertEquals(10L, projects.get(0));
    }

    /**
     * Ensures requesting project IDs for a non-existing manager
     * returns an empty list instead of null.
     */
    @Test
    void testGetProjectIds_forNonExistingManager() {
        List<Long> projects = ManagerDao.getProjectIds("UNKNOWN");

        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

    /**
     * Verifies successful removal of an existing project
     * from a manager.
     */
    @Test
    void testRemoveProjectFromManager_success() {
        ManagerDao.insertManager("M3");
        ManagerDao.addProjectToManager("M3", 100L);
        ManagerDao.addProjectToManager("M3", 200L);

        ManagerDao.removeProjectFromManager("M3", 100L);

        List<Long> projects = ManagerDao.getProjectIds("M3");

        assertEquals(1, projects.size());
        assertFalse(projects.contains(100L));
        assertTrue(projects.contains(200L));
    }

    /**
     * Ensures attempting to remove a non-existing project ID
     * does not modify the manager's project list.
     */
    @Test
    void testRemoveProjectFromManager_nonExistingProject() {
        ManagerDao.insertManager("M4");
        ManagerDao.addProjectToManager("M4", 300L);

        ManagerDao.removeProjectFromManager("M4", 999L);

        List<Long> projects = ManagerDao.getProjectIds("M4");

        assertEquals(1, projects.size());
        assertTrue(projects.contains(300L));
    }

    /**
     * Ensures removing a project from a non-existing manager
     * does not throw any exception.
     */
    @Test
    void testRemoveProjectFromNonExistingManager_shouldNotThrowException() {
        assertDoesNotThrow(() -> ManagerDao.removeProjectFromManager("NO_MANAGER", 1L));
    }

    /**
     * Validates that adding a project with a null manager ID
     * throws a {@link NullPointerException}.
     */
    @Test
    void testAddProjectToManager_nullManager_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> ManagerDao.addProjectToManager(null, 1L));
    }

    /**
     * Ensures inserting a manager with a null ID
     * throws a {@link NullPointerException}.
     */
    @Test
    void testInsertManager_nullManager_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> ManagerDao.insertManager(null));
    }

    /**
     * Verifies that duplicate project IDs
     * are allowed for a manager.
     */
    @Test
    void testAddDuplicateProjectId() {
        ManagerDao.insertManager("M5");
        ManagerDao.addProjectToManager("M5", 50L);
        ManagerDao.addProjectToManager("M5", 50L); // duplicate

        List<Long> projects = ManagerDao.getProjectIds("M5");

        assertEquals(2, projects.size()); // duplicates allowed
        assertEquals(50L, projects.get(0));
        assertEquals(50L, projects.get(1));
    }

    /**
     * Validates behavior when an empty string is used
     * as the manager ID.
     */
    @Test
    void testEmptyStringManagerId() {
        ManagerDao.addProjectToManager("", 123L);
        List<Long> projects = ManagerDao.getProjectIds("");

        assertEquals(1, projects.size());
        assertEquals(123L, projects.get(0));
    }
}
