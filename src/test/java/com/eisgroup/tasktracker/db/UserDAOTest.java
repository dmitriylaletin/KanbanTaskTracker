package com.eisgroup.tasktracker.db;

import com.eisgroup.tasktracker.dao.UserDAO;
import com.eisgroup.tasktracker.model.Role;
import com.eisgroup.tasktracker.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml"})
public class UserDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void testAddUser() {
        int userInitCount = countRowsInTable("users");

        User testUser = getTestUser();
        userDAO.addUser(testUser);
        int userAfterCount = countRowsInTable("users");

        assertEquals("User count must be +1 after adding user", userAfterCount, userInitCount + 1);
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void testUniqueLoginAdd() {
        userDAO.addUser(getTestUser());
        userDAO.addUser(getTestUser());
    }

    @Test
    public void testGetUserByLogin() {
        User testUser = getTestUser();
        userDAO.addUser(testUser);
        User userFromDb = userDAO.getUserByLogin(testUser.getLogin());

        assertEquals(testUser.getLogin(), userFromDb.getLogin());
        assertEquals(testUser.getRole(), userFromDb.getRole());
        assertEquals(testUser.getPassword(), userFromDb.getPassword());
        assertEquals(testUser.getEmail(), userFromDb.getEmail());
        assertEquals(testUser.getUuid(), userFromDb.getUuid());
    }

    @Test
    public void testGetUserByUuid() {
        User testUser = getTestUser();
        userDAO.addUser(testUser);
        User userFromDb = userDAO.getUserByUuid(testUser.getUuid());

        assertEquals(testUser.getLogin(), userFromDb.getLogin());
        assertEquals(testUser.getRole(), userFromDb.getRole());
        assertEquals(testUser.getPassword(), userFromDb.getPassword());
        assertEquals(testUser.getEmail(), userFromDb.getEmail());
        assertEquals(testUser.getUuid(), userFromDb.getUuid());
    }

    @Test
    public void testUpdateUser() {
        User testUser = getTestUser();
        userDAO.addUser(testUser);
        testUser.setEmail("new_unique@mail.com");
        userDAO.updateUser(testUser);

        User userFromDb = userDAO.getUserByLogin(testUser.getLogin());
        assertEquals(testUser.getEmail(), userFromDb.getEmail());
    }

    private User getTestUser() {
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setRole(Role.USER);
        user.setUuid("test-UUID");
        return user;
    }
}
