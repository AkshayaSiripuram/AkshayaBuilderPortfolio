package com.builderportfolio.dao;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerDBTest {

    @BeforeEach
    void setUp() {
        // Clear static data before each test to avoid interference
        ManagerDao.managerProjects.clear();
    }

    @AfterEach
    void tearDown() {
        // Clear again for safety
        ManagerDao.managerProjects.clear();
    }


    @Test
    void testInsertManager_success() {
        ManagerDao.insertManager("M1");

        assertTrue(ManagerDao.managerProjects.containsKey("M1"));
        assertNotNull(ManagerDao.getProjectIds("M1"));
        assertTrue(ManagerDao.getProjectIds("M1").isEmpty());
    }

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

    @Test
    void testAddProjectToManager_withoutInsert_shouldCreateManager() {
        ManagerDao.addProjectToManager("M2", 10L);

        List<Long> projects = ManagerDao.getProjectIds("M2");

        assertEquals(1, projects.size());
        assertEquals(10L, projects.get(0));
    }

    @Test
    void testGetProjectIds_forNonExistingManager() {
        List<Long> projects = ManagerDao.getProjectIds("UNKNOWN");

        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

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

    @Test
    void testRemoveProjectFromManager_nonExistingProject() {
        ManagerDao.insertManager("M4");
        ManagerDao.addProjectToManager("M4", 300L);

        ManagerDao.removeProjectFromManager("M4", 999L);

        List<Long> projects = ManagerDao.getProjectIds("M4");

        assertEquals(1, projects.size());
        assertTrue(projects.contains(300L));
    }

    @Test
    void testRemoveProjectFromNonExistingManager_shouldNotThrowException() {
        assertDoesNotThrow(() -> ManagerDao.removeProjectFromManager("NO_MANAGER", 1L));
    }


    @Test
    void testAddProjectToManager_nullManager_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> ManagerDao.addProjectToManager(null, 1L));
    }

    @Test
    void testInsertManager_nullManager_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> ManagerDao.insertManager(null));
    }


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

    @Test
    void testEmptyStringManagerId() {
        ManagerDao.addProjectToManager("", 123L);
        List<Long> projects = ManagerDao.getProjectIds("");

        assertEquals(1, projects.size());
        assertEquals(123L, projects.get(0));
    }

}
