//package com.builderportfolio.dao;
//
//import com.builderportfolio.model.User;
//import org.junit.jupiter.api.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserDBTest {
//
//    @BeforeEach
//    void setUp() {
//        UserDao.users.clear(); // Reset static DB before each test
//    }
//
//
//    @Test
//    void testInsertUser_success() {
//        User user = new User("Akshaya", "akshaya@gmail.com", "9876543210", 5, "pass123", 1);
//
//        UserDao.insertUser(user.getUserId(), user);
//        User storedUser = UserDao.getUserbyId(user.getUserId());
//
//        assertNotNull(storedUser);
//        assertEquals("Akshaya", storedUser.getUserName());
//    }
//
//    @Test
//    void testInsertUser_duplicateUserId_shouldNotOverride() {
//        User user1 = new User("First", "first@gmail.com", "1111111111", 3, "pass", 1);
//        User user2 = new User("Second", "second@gmail.com", "2222222222", 4, "pass", 1);
//
//        UserDao.insertUser(user1.getUserId(), user1);
//        UserDao.insertUser(user1.getUserId(), user2);
//
//        User storedUser = UserDao.getUserbyId(user1.getUserId());
//        assertNotNull(storedUser);
//        assertEquals("First", storedUser.getUserName());
//    }
//
//    @Test
//    void testExists_emailExists() {
//        User user = new User("Exists", "exists@gmail.com", "9999999999", 2, "pass", 2);
//        UserDao.insertUser(user.getUserId(), user);
//
//        assertEquals(user.getUserId(), UserDao.exists("exists@gmail.com"));
//    }
//
//    @Test
//    void testExists_emailNotExists() {
//        assertNull(UserDao.exists("missing@gmail.com"));
//    }
//
//    @Test
//    void testGetUserById_notFound() {
//        assertNull(UserDao.getUserbyId("INVALID"));
//    }
//
//    // -------------------- Edge Case Tests --------------------
//
//    @Test
//    void testInsertNullUser_shouldThrowException() {
//        assertThrows(NullPointerException.class, () -> UserDao.insertUser(null, null));
//    }
//
//    @Test
//    void testExists_nullEmail() {
//        assertNull(UserDao.exists(null));
//    }
//
//    @Test
//    void testExists_emptyEmail() {
//        assertNull(UserDao.exists(""));
//    }
//
//
//    @Test
//    void testGetUserById_emptyUserId() {
//        assertNull(UserDao.getUserbyId(""));
//    }
//}


package com.builderportfolio.dao;

import com.builderportfolio.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link UserDao}.
 * <p>
 * This test suite validates user insertion, lookup,
 * duplicate handling, existence checks by email,
 * and edge case scenarios such as null or empty inputs.
 * </p>
 */
class UserDBTest {

    /**
     * Clears the static user database before each test
     * to ensure test isolation and consistent results.
     */
    @BeforeEach
    void setUp() {
        UserDao.users.clear(); // Reset static DB before each test
    }

    /**
     * Verifies successful insertion and retrieval
     * of a user into the UserDao.
     */
    @Test
    void testInsertUser_success() {
        User user = new User(
                "Akshaya",
                "akshaya@gmail.com",
                "9876543210",
                5,
                "pass123",
                1
        );

        UserDao.insertUser(user.getUserId(), user);
        User storedUser = UserDao.getUserbyId(user.getUserId());

        assertNotNull(storedUser);
        assertEquals("Akshaya", storedUser.getUserName());
    }

    /**
     * Ensures that inserting a user with an existing
     * userId does not override the previously stored user.
     */
    @Test
    void testInsertUser_duplicateUserId_shouldNotOverride() {
        User user1 = new User("First", "first@gmail.com", "1111111111", 3, "pass", 1);
        User user2 = new User("Second", "second@gmail.com", "2222222222", 4, "pass", 1);

        UserDao.insertUser(user1.getUserId(), user1);
        UserDao.insertUser(user1.getUserId(), user2);

        User storedUser = UserDao.getUserbyId(user1.getUserId());

        assertNotNull(storedUser);
        assertEquals("First", storedUser.getUserName());
    }

    /**
     * Validates that {@link UserDao#exists(String)}
     * returns the correct userId when the email exists.
     */
    @Test
    void testExists_emailExists() {
        User user = new User("Exists", "exists@gmail.com", "9999999999", 2, "pass", 2);
        UserDao.insertUser(user.getUserId(), user);

        assertEquals(user.getUserId(), UserDao.exists("exists@gmail.com"));
    }

    /**
     * Verifies that {@link UserDao#exists(String)}
     * returns {@code null} when the email is not found.
     */
    @Test
    void testExists_emailNotExists() {
        assertNull(UserDao.exists("missing@gmail.com"));
    }

    /**
     * Ensures fetching a user by a non-existing userId
     * returns {@code null}.
     */
    @Test
    void testGetUserById_notFound() {
        assertNull(UserDao.getUserbyId("INVALID"));
    }

    // -------------------- Edge Case Tests --------------------

    /**
     * Verifies that inserting a null user
     * results in a {@link NullPointerException}.
     */
    @Test
    void testInsertNullUser_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> UserDao.insertUser(null, null));
    }

    /**
     * Ensures passing {@code null} to exists()
     * safely returns {@code null}.
     */
    @Test
    void testExists_nullEmail() {
        assertNull(UserDao.exists(null));
    }

    /**
     * Ensures passing an empty string to exists()
     * returns {@code null}.
     */
    @Test
    void testExists_emptyEmail() {
        assertNull(UserDao.exists(""));
    }

    /**
     * Verifies that querying with an empty userId
     * returns {@code null}.
     */
    @Test
    void testGetUserById_emptyUserId() {
        assertNull(UserDao.getUserbyId(""));
    }
}
