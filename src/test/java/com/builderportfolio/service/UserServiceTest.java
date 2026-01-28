package com.builderportfolio.service;

import com.builderportfolio.dao.UserDao;
import com.builderportfolio.exception.UserAlreadyExistsException;
import com.builderportfolio.exception.UserNotFoundException;
import com.builderportfolio.model.User;
import com.builderportfolio.dao.BuilderDao;
import com.builderportfolio.dao.ManagerDao;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    private static UserService userService;

    @BeforeAll
    static void setup() {
        userService = new UserService();
    }

    @Test
    @Order(1)
    void testRegister_Manager_Success() throws Exception {

        User manager = new User("Akshaya", "akshaya@gmail.com", "1234567890", 5, "pass123", 1   // Manager
        );

        userService.register(manager);

        assertNotNull(UserDao.getUserbyId(manager.getUserId()));
        assertTrue(ManagerDao.managerProjects.containsKey(manager.getUserId()));
    }

    @Test
    @Order(2)
    void testRegister_Builder_Success() throws Exception {

        User builder = new User("Ravi", "ravi@gmail.com", "9876543210", 3, "builder123", 2);

        userService.register(builder);

        assertNotNull(UserDao.getUserbyId(builder.getUserId()));
        assertTrue(BuilderDao.builderProjects.containsKey(builder.getUserId()));
    }

    @Test
    @Order(3)
    void testRegister_UserAlreadyExists() {

        User duplicateUser = new User("Akshaya", "akshaya@gmail.com", // same email
                "9999999999", 6, "newpass", 1);

        assertThrows(UserAlreadyExistsException.class, () -> userService.register(duplicateUser));
    }

    @Test
    @Order(4)
    void testLogin_Success() throws Exception {

        User loggedInUser = userService.login("M1", "pass123");

        assertNotNull(loggedInUser);
        assertEquals("Akshaya", loggedInUser.getUserName());
    }

    @Test
    @Order(5)
    void testLogin_WrongPassword() throws Exception {

        User user = userService.login("M1", "wrongpassword");

        assertNull(user);
    }

    @Test
    @Order(6)
    void testLogin_UserNotFound() {

        assertThrows(UserNotFoundException.class, () -> userService.login("M999", "pass123"));
    }

    @Test
    @Order(7)
    void testFetchDetails() {

        User user = userService.fetchDetails("M1");

        assertNotNull(user);
        assertEquals("akshaya@gmail.com", user.getUserEmail());
    }

    @Test
    @Order(8)
    void testRegister_NullUser() {
        assertThrows(NullPointerException.class, () -> userService.register(null));
    }

    @Test
    @Order(9)
    void testLogin_NullInputs() {
        assertThrows(NullPointerException.class, () -> userService.login(null, null));
    }

    @Test
    @Order(10)
    void testRegister_InvalidRole() {
        User invalidRoleUser = new User("Test", "test@gmail.com", "123", 1, "pass", 999);
        userService.register(invalidRoleUser);
        assertNotNull(UserDao.getUserbyId(invalidRoleUser.getUserId()));
        // role is invalid but user is still created
    }


}
