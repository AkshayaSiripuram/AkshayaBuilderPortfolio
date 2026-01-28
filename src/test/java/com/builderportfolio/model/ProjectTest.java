package com.builderportfolio.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectTest {

    private static Client client = new Client("Akshaya", "akshaya@gmail.com", "1234567890");

    private static Project project;

    @Test
    @Order(1)
    public void testProjectCreation() {

        project = new Project("Bridge Construction", "River bridge project", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), ProjectStatus.UPCOMING, client, "B1", "M1");

        assert project != null;
        assert project.getProjectId() == 1;
        assert project.getProjectName().equals("Bridge Construction");
        assert project.getProjectDescription().equals("River bridge project");
        assert project.getProjectStatus() == ProjectStatus.UPCOMING;
        assert project.getAssignedBuilder().equals("B1");
        assert project.getAssignedProjectManager().equals("M1");
    }

    @Test
    @Order(2)
    public void testSettersAndGetters() {

        project.setProjectName("Highway Construction");
        project.setProjectDescription("National highway project");
        project.setProjectStatus(ProjectStatus.IN_PROGRESS);

        assert project.getProjectName().equals("Highway Construction");
        assert project.getProjectDescription().equals("National highway project");
        assert project.getProjectStatus() == ProjectStatus.IN_PROGRESS;
    }

    @Test
    @Order(3)
    public void testDateSetters() {

        LocalDate newStart = LocalDate.of(2025, 2, 1);
        LocalDate newEnd = LocalDate.of(2025, 11, 30);

        project.setProjectStartDate(newStart);
        project.setProjectEndDate(newEnd);

        assert project.getProjectStartDate().equals(newStart);
        assert project.getProjectEndDate().equals(newEnd);
    }

    @Test
    @Order(4)
    public void testClientAssignment() {

        Client newClient = new Client("Ravi", "ravi@gmail.com", "9876543210");

        project.setAssignedClient(newClient);

        assert project.getAssignedClient() != null;
        assert project.getAssignedClient().getClientName().equals("Ravi");
    }

    @Test
    @Order(5)
    public void testToString() {

        String projectString = project.toString();

        assert projectString.contains("projectId=1");
        assert projectString.contains("Highway Construction");
        assert projectString.contains("IN_PROGRESS");
        assert projectString.contains("M1");
        assert projectString.contains("B1");
    }

    @Test
    @Order(6)
    public void testAutoIncrementProjectId() {

        Project secondProject = new Project("Mall Construction", "Commercial mall", LocalDate.now(), LocalDate.now().plusMonths(6), ProjectStatus.UPCOMING, client, "B2", "M2");

        assert secondProject.getProjectId() == 2;
    }

    @Test
    @Order(7)
    void testSettersWithNullValues() {
        project.setProjectName(null);
        project.setProjectDescription(null);
        project.setAssignedBuilder(null);
        project.setAssignedProjectManager(null);
        project.setAssignedClient(null);

        assertNull(project.getProjectName());
        assertNull(project.getProjectDescription());
        assertNull(project.getAssignedBuilder());
        assertNull(project.getAssignedProjectManager());
        assertNull(project.getAssignedClient());
    }

    @Test
    @Order(8)
    void testSetProjectStatusNull() {
        project.setProjectStatus(null);
        assertNull(project.getProjectStatus());
    }


}
