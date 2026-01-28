//package com.builderportfolio.model;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserTest {
//
//    private static User managerUser;
//    private static User builderUser;
//
//    @Test
//    @Order(1)
//    void testManagerUserCreation() {
//
//        managerUser = new User("Ramesh", "ramesh@gmail.com", "9999999999", 10, "manager123", 1   // Manager
//        );
//
//        assertNotNull(managerUser);
//        assertEquals("M1", managerUser.getUserId());
//        assertEquals("Ramesh", managerUser.getUserName());
//        assertEquals("ramesh@gmail.com", managerUser.getUserEmail());
//        assertEquals("9999999999", managerUser.getUserPhNumber());
//        assertEquals(10, managerUser.getUserExperience());
//        assertEquals("manager123", managerUser.getPassword());
//        assertEquals(1, managerUser.getSelectedRole());
//    }
//
//    @Test
//    @Order(2)
//    void testBuilderUserCreation() {
//
//        builderUser = new User("Suresh", "suresh@gmail.com", "8888888888", 5, "builder123", 2   // Builder
//        );
//
//        assertNotNull(builderUser);
//        assertEquals("B1", builderUser.getUserId());
//        assertEquals("Suresh", builderUser.getUserName());
//        assertEquals("suresh@gmail.com", builderUser.getUserEmail());
//        assertEquals("8888888888", builderUser.getUserPhNumber());
//        assertEquals(5, builderUser.getUserExperience());
//        assertEquals("builder123", builderUser.getPassword());
//        assertEquals(2, builderUser.getSelectedRole());
//    }
//
//    @Test
//    @Order(3)
//    void testSettersAndGetters() {
//
//        managerUser.setUserName("Ramesh Kumar");
//        managerUser.setUserEmail("ramesh.kumar@gmail.com");
//        managerUser.setUserPhNumber("7777777777");
//        managerUser.setUserExperience(12);
//        managerUser.setPassword("newpass123");
//
//        assertEquals("Ramesh Kumar", managerUser.getUserName());
//        assertEquals("ramesh.kumar@gmail.com", managerUser.getUserEmail());
//        assertEquals("7777777777", managerUser.getUserPhNumber());
//        assertEquals(12, managerUser.getUserExperience());
//        assertEquals("newpass123", managerUser.getPassword());
//    }
//
//    @Test
//    @Order(4)
//    void testAutoIncrementManagerId() {
//
//        User secondManager = new User("Mahesh", "mahesh@gmail.com", "6666666666", 8, "manager456", 1);
//
//        assertEquals("M2", secondManager.getUserId());
//    }
//
//    @Test
//    @Order(5)
//    void testAutoIncrementBuilderId() {
//
//        User secondBuilder = new User("Ravi", "ravi@gmail.com", "5555555555", 6, "builder456", 2);
//
//        assertEquals("B2", secondBuilder.getUserId());
//    }
//
//    @Test
//    @Order(6)
//    void testToString() {
//
//        String userString = managerUser.toString();
//
//        assertTrue(userString.contains("M1"));
//        assertTrue(userString.contains("Ramesh Kumar"));
//        assertTrue(userString.contains("ramesh.kumar@gmail.com"));
//        assertTrue(userString.contains("7777777777"));
//        assertTrue(userString.contains("12"));
//        assertTrue(userString.contains("newpass123"));
//        assertTrue(userString.contains("selectedRole=1"));
//    }
//
//    @Test
//    @Order(7)
//    void testNullFields() {
//        User user = new User(null, null, null, 0, null, 2);
//        assertNull(user.getUserName());
//        assertNull(user.getUserEmail());
//        assertNull(user.getUserPhNumber());
//        assertEquals(0, user.getUserExperience());
//        assertNull(user.getPassword());
//    }
//
//    @Test
//    @Order(8)
//    void testInvalidSelectedRole() {
//        User user = new User("Test", "test@gmail.com", "1234", 1, "pass", 99);
//        assertEquals("B4", user.getUserId()); // Anything other than 1 defaults to builder
//    }
//
//}


package com.builderportfolio.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link User}.
 * <p>
 * This test suite verifies:
 * <ul>
 *   <li>User creation for different roles (Manager & Builder)</li>
 *   <li>Auto-increment logic for user IDs</li>
 *   <li>Setter and getter functionality</li>
 *   <li>Null field handling</li>
 *   <li>Default behavior for invalid roles</li>
 *   <li>toString() output validation</li>
 * </ul>
 * </p>
 * <p>
 * Test execution order is controlled to maintain
 * predictable static ID generation.
 * </p>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    /**
     * Shared manager user instance.
     */
    private static User managerUser;

    /**
     * Shared builder user instance.
     */
    private static User builderUser;

    /**
     * Tests creation of a Manager user
     * and validates assigned fields and user ID.
     */
    @Test
    @Order(1)
    void testManagerUserCreation() {

        managerUser = new User(
                "Ramesh",
                "ramesh@gmail.com",
                "9999999999",
                10,
                "manager123",
                1   // Manager
        );

        assertNotNull(managerUser);
        assertEquals("M1", managerUser.getUserId());
        assertEquals("Ramesh", managerUser.getUserName());
        assertEquals("ramesh@gmail.com", managerUser.getUserEmail());
        assertEquals("9999999999", managerUser.getUserPhNumber());
        assertEquals(10, managerUser.getUserExperience());
        assertEquals("manager123", managerUser.getPassword());
        assertEquals(1, managerUser.getSelectedRole());
    }

    /**
     * Tests creation of a Builder user
     * and validates assigned fields and user ID.
     */
    @Test
    @Order(2)
    void testBuilderUserCreation() {

        builderUser = new User(
                "Suresh",
                "suresh@gmail.com",
                "8888888888",
                5,
                "builder123",
                2   // Builder
        );

        assertNotNull(builderUser);
        assertEquals("B1", builderUser.getUserId());
        assertEquals("Suresh", builderUser.getUserName());
        assertEquals("suresh@gmail.com", builderUser.getUserEmail());
        assertEquals("8888888888", builderUser.getUserPhNumber());
        assertEquals(5, builderUser.getUserExperience());
        assertEquals("builder123", builderUser.getPassword());
        assertEquals(2, builderUser.getSelectedRole());
    }

    /**
     * Verifies setter and getter methods
     * for user fields.
     */
    @Test
    @Order(3)
    void testSettersAndGetters() {

        managerUser.setUserName("Ramesh Kumar");
        managerUser.setUserEmail("ramesh.kumar@gmail.com");
        managerUser.setUserPhNumber("7777777777");
        managerUser.setUserExperience(12);
        managerUser.setPassword("newpass123");

        assertEquals("Ramesh Kumar", managerUser.getUserName());
        assertEquals("ramesh.kumar@gmail.com", managerUser.getUserEmail());
        assertEquals("7777777777", managerUser.getUserPhNumber());
        assertEquals(12, managerUser.getUserExperience());
        assertEquals("newpass123", managerUser.getPassword());
    }

    /**
     * Ensures manager user IDs auto-increment correctly.
     */
    @Test
    @Order(4)
    void testAutoIncrementManagerId() {

        User secondManager = new User(
                "Mahesh",
                "mahesh@gmail.com",
                "6666666666",
                8,
                "manager456",
                1
        );

        assertEquals("M2", secondManager.getUserId());
    }

    /**
     * Ensures builder user IDs auto-increment correctly.
     */
    @Test
    @Order(5)
    void testAutoIncrementBuilderId() {

        User secondBuilder = new User(
                "Ravi",
                "ravi@gmail.com",
                "5555555555",
                6,
                "builder456",
                2
        );

        assertEquals("B2", secondBuilder.getUserId());
    }

    /**
     * Tests the {@link User#toString()} method
     * to ensure it contains all important user details.
     */
    @Test
    @Order(6)
    void testToString() {

        String userString = managerUser.toString();

        assertTrue(userString.contains("M1"));
        assertTrue(userString.contains("Ramesh Kumar"));
        assertTrue(userString.contains("ramesh.kumar@gmail.com"));
        assertTrue(userString.contains("7777777777"));
        assertTrue(userString.contains("12"));
        assertTrue(userString.contains("newpass123"));
        assertTrue(userString.contains("selectedRole=1"));
    }

    /**
     * Verifies user creation with null fields
     * does not throw exceptions and values are preserved.
     */
    @Test
    @Order(7)
    void testNullFields() {
        User user = new User(null, null, null, 0, null, 2);

        assertNull(user.getUserName());
        assertNull(user.getUserEmail());
        assertNull(user.getUserPhNumber());
        assertEquals(0, user.getUserExperience());
        assertNull(user.getPassword());
    }

    /**
     * Tests behavior when an invalid role is provided.
     * Any role other than 1 defaults to Builder.
     */
    @Test
    @Order(8)
    void testInvalidSelectedRole() {
        User user = new User("Test", "test@gmail.com", "1234", 1, "pass", 99);

        assertEquals("B4", user.getUserId());
    }
}
