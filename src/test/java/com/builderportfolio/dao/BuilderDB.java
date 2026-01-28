//package com.builderportfolio.dao;
//
//import org.junit.jupiter.api.*;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BuilderDBTest {
//
//    @BeforeEach
//    void setUp() {
//        BuilderDao.builderProjects.clear(); // reset state before each test
//    }
//
//    @AfterEach
//    void tearDown() {
//        BuilderDao.builderProjects.clear(); // cleanup after each test
//    }
//
//    @Test
//    void testInsertBuilder_success() {
//        BuilderDao.insertBuilder("B1");
//
//        assertTrue(BuilderDao.builderProjects.containsKey("B1"));
//        assertNotNull(BuilderDao.getProjectIds("B1"));
//        assertTrue(BuilderDao.getProjectIds("B1").isEmpty());
//    }
//
//    @Test
//    void testInsertBuilder_nullId_shouldThrowException() {
//        assertThrows(NullPointerException.class, () -> BuilderDao.insertBuilder(null));
//    }
//
//    @Test
//    void testAddProjectToBuilder_success() {
//        BuilderDao.insertBuilder("B1");
//
//        BuilderDao.addProjectToBuilder("B1", 1L);
//        BuilderDao.addProjectToBuilder("B1", 2L);
//
//        List<Long> projects = BuilderDao.getProjectIds("B1");
//
//        assertEquals(2, projects.size());
//        assertTrue(projects.contains(1L));
//        assertTrue(projects.contains(2L));
//    }
//
//    @Test
//    void testAddProjectToBuilder_withoutInsert_shouldCreateBuilder() {
//        BuilderDao.addProjectToBuilder("B2", 10L);
//
//        List<Long> projects = BuilderDao.getProjectIds("B2");
//
//        assertEquals(1, projects.size());
//        assertEquals(10L, projects.get(0));
//    }
//
//    @Test
//    void testAddProjectToBuilder_nullBuilder_shouldThrowException() {
//        assertThrows(IllegalArgumentException.class, () -> BuilderDao.addProjectToBuilder(null, 5L));
//    }
//
//
//    @Test
//    void testAddDuplicateProjectId() {
//        BuilderDao.insertBuilder("B4");
//        BuilderDao.addProjectToBuilder("B4", 10L);
//        BuilderDao.addProjectToBuilder("B4", 10L); // duplicate allowed
//
//        List<Long> projects = BuilderDao.getProjectIds("B4");
//        assertEquals(2, projects.size()); // duplicates exist
//    }
//
//    @Test
//    void testGetProjectIds_forNonExistingBuilder() {
//        List<Long> projects = BuilderDao.getProjectIds("UNKNOWN");
//
//        assertNotNull(projects);
//        assertTrue(projects.isEmpty());
//    }
//
//    @Test
//    void testRemoveProjectFromBuilder_bugDemonstration() {
//        BuilderDao.insertBuilder("B3");
//        BuilderDao.addProjectToBuilder("B3", 100L);
//        BuilderDao.addProjectToBuilder("B3", 200L);
//
//        BuilderDao.removeProjectFromBuilder("B3", 100L);
//
//        List<Long> projects = BuilderDao.getProjectIds("B3");
//
//        assertEquals(2, projects.size());
//        assertTrue(projects.contains(100L));
//        assertTrue(projects.contains(200L));
//    }
//
//    @Test
//    void testRemoveProjectFromNonExistingBuilder_shouldNotThrowException() {
//        assertDoesNotThrow(() -> BuilderDao.removeProjectFromBuilder("NO_BUILDER", 1L));
//    }
//
//
//    @Test
//    void testRemoveProject_invalidProjectId_shouldNotThrowException() {
//        BuilderDao.insertBuilder("B5");
//        BuilderDao.addProjectToBuilder("B5", 10L);
//
//        // Try removing a project ID that doesn't exist
//        assertDoesNotThrow(() -> BuilderDao.removeProjectFromBuilder("B5", 999L));
//
//        List<Long> projects = BuilderDao.getProjectIds("B5");
//        assertEquals(1, projects.size()); // original project remains
//        assertTrue(projects.contains(10L));
//    }
//}

package com.builderportfolio.dao;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link BuilderDao}.
 * <p>
 * Validates builder-project mapping behavior including insertion,
 * project assignment, duplicate handling, and edge cases.
 * </p>
 */
class BuilderDBTest {

    /**
     * Clears the in-memory builder-project mapping
     * before each test to ensure isolation.
     */
    @BeforeEach
    void setUp() {
        BuilderDao.builderProjects.clear(); // reset state before each test
    }

    /**
     * Cleans up the in-memory data store
     * after each test execution.
     */
    @AfterEach
    void tearDown() {
        BuilderDao.builderProjects.clear(); // cleanup after each test
    }

    /**
     * Verifies successful insertion of a builder
     * and initialization of an empty project list.
     */
    @Test
    void testInsertBuilder_success() {
        BuilderDao.insertBuilder("B1");

        assertTrue(BuilderDao.builderProjects.containsKey("B1"));
        assertNotNull(BuilderDao.getProjectIds("B1"));
        assertTrue(BuilderDao.getProjectIds("B1").isEmpty());
    }

    /**
     * Ensures inserting a builder with null ID
     * throws a {@link NullPointerException}.
     */
    @Test
    void testInsertBuilder_nullId_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> BuilderDao.insertBuilder(null));
    }

    /**
     * Tests adding multiple projects to an existing builder.
     */
    @Test
    void testAddProjectToBuilder_success() {
        BuilderDao.insertBuilder("B1");

        BuilderDao.addProjectToBuilder("B1", 1L);
        BuilderDao.addProjectToBuilder("B1", 2L);

        List<Long> projects = BuilderDao.getProjectIds("B1");

        assertEquals(2, projects.size());
        assertTrue(projects.contains(1L));
        assertTrue(projects.contains(2L));
    }

    /**
     * Verifies that adding a project to a non-existing builder
     * automatically creates the builder entry.
     */
    @Test
    void testAddProjectToBuilder_withoutInsert_shouldCreateBuilder() {
        BuilderDao.addProjectToBuilder("B2", 10L);

        List<Long> projects = BuilderDao.getProjectIds("B2");

        assertEquals(1, projects.size());
        assertEquals(10L, projects.get(0));
    }

    /**
     * Ensures adding a project with null builder ID
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testAddProjectToBuilder_nullBuilder_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> BuilderDao.addProjectToBuilder(null, 5L));
    }

    /**
     * Validates that duplicate project IDs
     * are allowed for a builder.
     */
    @Test
    void testAddDuplicateProjectId() {
        BuilderDao.insertBuilder("B4");
        BuilderDao.addProjectToBuilder("B4", 10L);
        BuilderDao.addProjectToBuilder("B4", 10L); // duplicate allowed

        List<Long> projects = BuilderDao.getProjectIds("B4");
        assertEquals(2, projects.size()); // duplicates exist
    }

    /**
     * Ensures fetching project IDs for a non-existing builder
     * returns an empty list instead of null.
     */
    @Test
    void testGetProjectIds_forNonExistingBuilder() {
        List<Long> projects = BuilderDao.getProjectIds("UNKNOWN");

        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }



    /**
     * Ensures removing a project from a non-existing builder
     * does not throw any exception.
     */
    @Test
    void testRemoveProjectFromNonExistingBuilder_shouldNotThrowException() {
        assertDoesNotThrow(() -> BuilderDao.removeProjectFromBuilder("NO_BUILDER", 1L));
    }

    /**
     * Ensures attempting to remove a non-existing project ID
     * does not affect existing projects.
     */
    @Test
    void testRemoveProject_invalidProjectId_shouldNotThrowException() {
        BuilderDao.insertBuilder("B5");
        BuilderDao.addProjectToBuilder("B5", 10L);

        // Try removing a project ID that doesn't exist
        assertDoesNotThrow(() -> BuilderDao.removeProjectFromBuilder("B5", 999L));

        List<Long> projects = BuilderDao.getProjectIds("B5");
        assertEquals(1, projects.size()); // original project remains
        assertTrue(projects.contains(10L));
    }
}

