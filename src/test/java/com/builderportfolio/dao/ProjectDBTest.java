package com.builderportfolio.dao;

import com.builderportfolio.model.Client;
import com.builderportfolio.model.Project;
import com.builderportfolio.model.ProjectStatus;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectDBTest {

    private Client client;

    @BeforeEach
    void setUp() {
        // Reset static DB
        ProjectDao.projectsList.clear();
        // Reset static project ID counter
        client = new Client("Akshaya", "akshaya@gmail.com", "1234567890");
    }

    @Test
    @Order(1)
    void testSaveAndGetProject() {
        Project project = new Project("Bridge", "Bridge construction", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), ProjectStatus.UPCOMING, client, "B1", "M1");

        ProjectDao.saveProject(project);

        Project stored = ProjectDao.getProjectById(project.getProjectId());

        assertNotNull(stored);
        assertEquals("Bridge", stored.getProjectName());
        assertEquals(ProjectStatus.UPCOMING, stored.getProjectStatus());
        assertEquals("B1", stored.getAssignedBuilder());
        assertEquals("M1", stored.getAssignedProjectManager());
    }

    @Test
    @Order(2)
    void testRemoveProject_success() {
        Project project = new Project("Mall", "Mall project", LocalDate.now(), LocalDate.now().plusMonths(6), ProjectStatus.IN_PROGRESS, client, "B2", "M2");

        ProjectDao.saveProject(project);
        long id = project.getProjectId();

        ProjectDao.removeProject(id);

        Project deleted = ProjectDao.getProjectById(id);
        assertNull(deleted);
    }

    @Test
    @Order(3)
    void testRemoveProject_nonExisting_shouldNotThrowException() {
        assertDoesNotThrow(() -> ProjectDao.removeProject(9999L));
    }

    @Test
    @Order(4)
    void testGetProject_notFound() {
        Project project = ProjectDao.getProjectById(999L);
        assertNull(project);
    }

    @Test
    @Order(5)
    void testMultipleProjects_autoIncrement() {
        Project p1 = new Project("P1", "Desc1", LocalDate.now(), LocalDate.now().plusDays(10), ProjectStatus.UPCOMING, client, "B1", "M1");

        Project p2 = new Project("P2", "Desc2", LocalDate.now(), LocalDate.now().plusDays(20), ProjectStatus.COMPLETED, client, "B2", "M2");

        ProjectDao.saveProject(p1);
        ProjectDao.saveProject(p2);

        assertTrue(p2.getProjectId() > p1.getProjectId());
    }

    @Test
    @Order(6)
    void testSaveProject_nullProject_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> ProjectDao.saveProject(null));
    }

    @Test
    @Order(7)
    void testProjectWithNullFields() {
        Project project = new Project(null, null, LocalDate.now(), LocalDate.now().plusDays(1), ProjectStatus.UPCOMING, null, null, null);

        ProjectDao.saveProject(project);
        Project stored = ProjectDao.getProjectById(project.getProjectId());

        assertNotNull(stored);
        assertNull(stored.getProjectName());
        assertNull(stored.getProjectDescription());
        assertNull(stored.getAssignedBuilder());
        assertNull(stored.getAssignedProjectManager());
        assertNull(stored.getAssignedClient());
    }

    @Test
    @Order(8)
    void testProjectWithEmptyStrings() {
        Project project = new Project("", "", LocalDate.now(), LocalDate.now().plusDays(1), ProjectStatus.COMPLETED, client, "", "");

        ProjectDao.saveProject(project);
        Project stored = ProjectDao.getProjectById(project.getProjectId());

        assertEquals("", stored.getProjectName());
        assertEquals("", stored.getProjectDescription());
        assertEquals("", stored.getAssignedBuilder());
        assertEquals("", stored.getAssignedProjectManager());
    }

    @Test
    @Order(9)
    void testBoundaryDates_startEqualsEnd() {
        LocalDate today = LocalDate.now();
        Project project = new Project("Boundary", "Start==End", today, today, ProjectStatus.UPCOMING, client, "B1", "M1");

        ProjectDao.saveProject(project);
        Project stored = ProjectDao.getProjectById(project.getProjectId());

        assertEquals(today, stored.getProjectStartDate());
        assertEquals(today, stored.getProjectEndDate());
    }

    @Test
    @Order(10)
    void testBoundaryDates_startAfterEnd() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        Project project = new Project("InvalidDates", "Start>End", today, yesterday, ProjectStatus.UPCOMING, client, "B1", "M1");

        ProjectDao.saveProject(project);
        Project stored = ProjectDao.getProjectById(project.getProjectId());

        assertTrue(stored.getProjectStartDate().isAfter(stored.getProjectEndDate()));
    }
}
