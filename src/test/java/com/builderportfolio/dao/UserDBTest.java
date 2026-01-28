package com.builderportfolio.dao;

import com.builderportfolio.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDBTest {

    @BeforeEach
    void setUp() {
        UserDao.users.clear(); // Reset static DB before each test
    }


    @Test
    void testInsertUser_success() {
        User user = new User("Akshaya", "akshaya@gmail.com", "9876543210", 5, "pass123", 1);

        UserDao.insertUser(user.getUserId(), user);
        User storedUser = UserDao.getUserbyId(user.getUserId());

        assertNotNull(storedUser);
        assertEquals("Akshaya", storedUser.getUserName());
    }

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

    @Test
    void testExists_emailExists() {
        User user = new User("Exists", "exists@gmail.com", "9999999999", 2, "pass", 2);
        UserDao.insertUser(user.getUserId(), user);

        assertEquals(user.getUserId(), UserDao.exists("exists@gmail.com"));
    }

    @Test
    void testExists_emailNotExists() {
        assertNull(UserDao.exists("missing@gmail.com"));
    }

    @Test
    void testGetUserById_notFound() {
        assertNull(UserDao.getUserbyId("INVALID"));
    }

    // -------------------- Edge Case Tests --------------------

    @Test
    void testInsertNullUser_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> UserDao.insertUser(null, null));
    }

    @Test
    void testExists_nullEmail() {
        assertNull(UserDao.exists(null));
    }

    @Test
    void testExists_emptyEmail() {
        assertNull(UserDao.exists(""));
    }


    @Test
    void testGetUserById_emptyUserId() {
        assertNull(UserDao.getUserbyId(""));
    }
}
