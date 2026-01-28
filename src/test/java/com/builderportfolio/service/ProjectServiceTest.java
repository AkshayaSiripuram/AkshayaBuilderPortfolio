package com.builderportfolio.service;

import com.builderportfolio.dao.ProjectDao;
import com.builderportfolio.model.Client;
import com.builderportfolio.model.Project;
import com.builderportfolio.model.ProjectStatus;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTest {

    private static ProjectService projectService;
    private static Client client;

    private static String managerId = "M1";
    private static String builderId = "B1";

    @BeforeAll
    static void setup() {
        projectService = new ProjectService();
        client = new Client("Akshaya", "akshaya@gmail.com", "1234567890");

        // initialize DBs
        ManagerDao.insertManager(managerId);
        BuilderDao.insertBuilder(builderId);
    }

    @Test
    @Order(1)
    void testCreateProject() {

        projectService.createProject("Bridge Project", "River bridge", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), ProjectStatus.UPCOMING, client, builderId, managerId);

        List<Project> managerProjects = projectService.getManagerProjects(managerId);

        assertEquals(1, managerProjects.size());
        assertEquals("Bridge Project", managerProjects.get(0).getProjectName());
    }

    @Test
    @Order(2)
    void testGetManagerProjects() {

        List<Project> projects = projectService.getManagerProjects(managerId);

        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }

    @Test
    @Order(3)
    void testGetBuilderProjects() {

        List<Project> projects = projectService.getBuilderProjects(builderId);

        assertNotNull(projects);
        assertEquals(1, projects.size());
    }

    @Test
    @Order(4)
    void testUpdateProjectStatus_Success() {

        Project project = projectService.getBuilderProjects(builderId).get(0);

        boolean updated = projectService.updateProjectStatus(builderId, project.getProjectId(), ProjectStatus.IN_PROGRESS);

        assertTrue(updated);
        assertEquals(ProjectStatus.IN_PROGRESS, project.getProjectStatus());
    }

    @Test
    @Order(5)
    void testUpdateProjectStatus_Failure_WrongBuilder() {

        Project project = projectService.getBuilderProjects(builderId).get(0);

        boolean updated = projectService.updateProjectStatus("B999", project.getProjectId(), ProjectStatus.COMPLETED);

        assertFalse(updated);
    }

    @Test
    @Order(6)
    void testDeleteProject_Success() {

        Project project = projectService.getManagerProjects(managerId).get(0);

        boolean deleted = projectService.deleteProject(managerId, project.getProjectId());

        assertTrue(deleted);
        assertNull(ProjectDao.getProjectById(project.getProjectId()));
    }

    @Test
    @Order(7)
    void testDeleteProject_Failure_WrongManager() {

        projectService.createProject("Mall Project", "Commercial mall", LocalDate.now(), LocalDate.now().plusMonths(6), ProjectStatus.UPCOMING, client, builderId, managerId);

        Project project = projectService.getManagerProjects(managerId).get(0);

        boolean deleted = projectService.deleteProject("M999", project.getProjectId());

        assertFalse(deleted);
    }

    @Test
    @Order(8)
    void testUpdateProjectStatus_nonExistingProject() {
        boolean updated = projectService.updateProjectStatus(builderId, 999L, ProjectStatus.COMPLETED);
        assertFalse(updated);
    }

    @Test
    @Order(9)
    void testDeleteProject_nonExistingProject() {
        boolean deleted = projectService.deleteProject(managerId, 999L);
        assertFalse(deleted);
    }

    @Test
    @Order(10)
    void testNullInputs() {
        assertThrows(NullPointerException.class, () -> projectService.createProject(null, null, null, null, null, null, null, null));
    }

    @Test
    @Order(11)
    void testGetManagerProjects_empty() {
        List<Project> projects = projectService.getManagerProjects("M999");
        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

    @Test
    @Order(12)
    void testGetBuilderProjects_empty() {
        List<Project> projects = projectService.getBuilderProjects("B999");
        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

}
