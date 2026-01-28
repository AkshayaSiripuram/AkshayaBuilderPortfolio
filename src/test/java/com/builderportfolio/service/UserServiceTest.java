//package com.builderportfolio.service;
//
//import com.builderportfolio.dao.UserDao;
//import com.builderportfolio.exception.UserAlreadyExistsException;
//import com.builderportfolio.exception.UserNotFoundException;
//import com.builderportfolio.model.User;
//import com.builderportfolio.dao.BuilderDao;
//import com.builderportfolio.dao.ManagerDao;
//import org.junit.jupiter.api.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserServiceTest {
//
//    private static UserService userService;
//
//    @BeforeAll
//    static void setup() {
//        userService = new UserService();
//    }
//
//    @Test
//    @Order(1)
//    void testRegister_Manager_Success() throws Exception {
//
//        User manager = new User("Akshaya", "akshaya@gmail.com", "1234567890", 5, "pass123", 1   // Manager
//        );
//
//        userService.register(manager);
//
//        assertNotNull(UserDao.getUserbyId(manager.getUserId()));
//        assertTrue(ManagerDao.managerProjects.containsKey(manager.getUserId()));
//    }
//
//    @Test
//    @Order(2)
//    void testRegister_Builder_Success() throws Exception {
//
//        User builder = new User("Ravi", "ravi@gmail.com", "9876543210", 3, "builder123", 2);
//
//        userService.register(builder);
//
//        assertNotNull(UserDao.getUserbyId(builder.getUserId()));
//        assertTrue(BuilderDao.builderProjects.containsKey(builder.getUserId()));
//    }
//
//    @Test
//    @Order(3)
//    void testRegister_UserAlreadyExists() {
//
//        User duplicateUser = new User("Akshaya", "akshaya@gmail.com", // same email
//                "9999999999", 6, "newpass", 1);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userService.register(duplicateUser));
//    }
//
//    @Test
//    @Order(4)
//    void testLogin_Success() throws Exception {
//
//        User loggedInUser = userService.login("M1", "pass123");
//
//        assertNotNull(loggedInUser);
//        assertEquals("Akshaya", loggedInUser.getUserName());
//    }
//
//    @Test
//    @Order(5)
//    void testLogin_WrongPassword() throws Exception {
//
//        User user = userService.login("M1", "wrongpassword");
//
//        assertNull(user);
//    }
//
//    @Test
//    @Order(6)
//    void testLogin_UserNotFound() {
//
//        assertThrows(UserNotFoundException.class, () -> userService.login("M999", "pass123"));
//    }
//
//    @Test
//    @Order(7)
//    void testFetchDetails() {
//
//        User user = userService.fetchDetails("M1");
//
//        assertNotNull(user);
//        assertEquals("akshaya@gmail.com", user.getUserEmail());
//    }
//
//    @Test
//    @Order(8)
//    void testRegister_NullUser() {
//        assertThrows(NullPointerException.class, () -> userService.register(null));
//    }
//
//    @Test
//    @Order(9)
//    void testLogin_NullInputs() {
//        assertThrows(NullPointerException.class, () -> userService.login(null, null));
//    }
//
//    @Test
//    @Order(10)
//    void testRegister_InvalidRole() {
//        User invalidRoleUser = new User("Test", "test@gmail.com", "123", 1, "pass", 999);
//        userService.register(invalidRoleUser);
//        assertNotNull(UserDao.getUserbyId(invalidRoleUser.getUserId()));
//        // role is invalid but user is still created
//    }
//
//
//}

package com.builderportfolio.service;

import com.builderportfolio.dao.UserDao;
import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link UserService}.
 * <p>
 * This test suite validates:
 * <ul>
 *   <li>User registration for managers and builders</li>
 *   <li>Duplicate user registration handling</li>
 *   <li>User login success and failure scenarios</li>
 *   <li>User detail retrieval</li>
 *   <li>Null and invalid input handling</li>
 * </ul>
 * </p>
 * <p>
 * Ordered execution is used due to static,
 * in-memory DAO state.
 * </p>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    /**
     * Service under test.
     */
    private static UserService userService;

    /**
     * Initializes {@link UserService}
     * before executing tests.
     */
    @BeforeAll
    static void setup() {
        userService = new UserService();
    }

    /**
     * Tests successful registration of a manager user.
     * <p>
     * Verifies:
     * <ul>
     *   <li>User is stored in {@link UserDao}</li>
     *   <li>Manager entry is created in {@link ManagerDao}</li>
     * </ul>
     * </p>
     */
    @Test
    @Order(1)
    void testRegister_Manager_Success() throws Exception {

        User manager = new User(
                "Akshaya",
                "akshaya@gmail.com",
                "1234567890",
                5,
                "pass123",
                1   // Manager
        );

        userService.register(manager);

        assertNotNull(UserDao.getUserbyId(manager.getUserId()));
        assertTrue(ManagerDao.managerProjects.containsKey(manager.getUserId()));
    }

    /**
     * Tests successful registration of a builder user.
     * <p>
     * Verifies builder-specific initialization.
     * </p>
     */
    @Test
    @Order(2)
    void testRegister_Builder_Success() throws Exception {

        User builder = new User(
                "Ravi",
                "ravi@gmail.com",
                "9876543210",
                3,
                "builder123",
                2   // Builder
        );

        userService.register(builder);

        assertNotNull(UserDao.getUserbyId(builder.getUserId()));
        assertTrue(BuilderDao.builderProjects.containsKey(builder.getUserId()));
    }

    /**
     * Ensures registration fails when
     * a user with an existing email is registered again.
     */
    @Test
    @Order(3)
    void testRegister_UserAlreadyExists() {

        User duplicateUser = new User(
                "Akshaya",
                "akshaya@gmail.com", // duplicate email
                "9999999999",
                6,
                "newpass",
                1
        );

        assertThrows(
                UserAlreadyExistsException.class,
                () -> userService.register(duplicateUser)
        );
    }

    /**
     * Tests successful login with valid credentials.
     */
    @Test
    @Order(4)
    void testLogin_Success() throws Exception {

        User loggedInUser = userService.login("M1", "pass123");

        assertNotNull(loggedInUser);
        assertEquals("Akshaya", loggedInUser.getUserName());
    }

    /**
     * Verifies login fails when password is incorrect.
     * <p>
     * Expected behavior: returns {@code null}.
     * </p>
     */
    @Test
    @Order(5)
    void testLogin_WrongPassword() throws Exception {

        User user = userService.login("M1", "wrongpassword");

        assertNull(user);
    }

    /**
     * Ensures {@link UserNotFoundException}
     * is thrown when attempting to login
     * with a non-existing user ID.
     */
    @Test
    @Order(6)
    void testLogin_UserNotFound() {

        assertThrows(
                UserNotFoundException.class,
                () -> userService.login("M999", "pass123")
        );
    }

    /**
     * Tests fetching user details
     * using a valid user ID.
     */
    @Test
    @Order(7)
    void testFetchDetails() {

        User user = userService.fetchDetails("M1");

        assertNotNull(user);
        assertEquals("akshaya@gmail.com", user.getUserEmail());
    }

    /**
     * Ensures registering a null user
     * results in a {@link NullPointerException}.
     */
    @Test
    @Order(8)
    void testRegister_NullUser() {
        assertThrows(
                NullPointerException.class,
                () -> userService.register(null)
        );
    }

    /**
     * Ensures login with null inputs
     * results in a {@link NullPointerException}.
     */
    @Test
    @Order(9)
    void testLogin_NullInputs() {
        assertThrows(
                NullPointerException.class,
                () -> userService.login(null, null)
        );
    }

    /**
     * Verifies user registration still succeeds
     * when an invalid role is provided.
     * <p>
     * System defaults such users to builder behavior.
     * </p>
     */
    @Test
    @Order(10)
    void testRegister_InvalidRole() {

        User invalidRoleUser = new User(
                "Test",
                "test@gmail.com",
                "123",
                1,
                "pass",
                999
        );

        userService.register(invalidRoleUser);

        assertNotNull(UserDao.getUserbyId(invalidRoleUser.getUserId()));
        // Invalid role does not block registration
    }
}

