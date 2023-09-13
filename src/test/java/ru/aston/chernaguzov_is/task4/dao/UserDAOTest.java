package ru.aston.chernaguzov_is.task4.dao;

import org.junit.Test;
import ru.aston.chernaguzov_is.task4.model.Order;
import ru.aston.chernaguzov_is.task4.model.User;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userDAO;

    @Test
    public void findAll() {
        UserDAO userDAO = new UserDAO();

        List<User> users = userDAO.findAll();
        assertEquals("Ivan", users.get(0).getName());
    }

    @Test
    public void findEntityById() {
        UserDAO userDAO = new UserDAO();

        User user = userDAO.findEntityById(2L);
        assertEquals("Sergeev", user.getSurname());
    }

    @Test
    public void delete() {
        UserDAO userDAO = new UserDAO();

        assertTrue(userDAO.delete(4L));
    }

    @Test
    public void create() {
        UserDAO userDAO = new UserDAO();
        User user = new User("Pertrov", "Petr", "9012346754", "petr@test.ru", 3L);

        assertTrue(userDAO.create(user));
    }

    @Test
    public void update() {
        UserDAO userDAO = new UserDAO();
        User beforeUpdate = new User(3L,"Nikolaev", "Nikolay", "9219876543", "nikolay@test.ru", 3L);
        User afterUpdate = new User(3L,"Nikolaev", "Nikolay", "9219876543", "nikolay@test.ru", 2L);

        assertNotEquals(beforeUpdate.getOrderId(), userDAO.update(afterUpdate).getOrderId());
    }

    @Test
    public void findAllUsersAndOrders() {
        UserDAO userDAO = new UserDAO();
        User user = new User(6L, "Pertrov", "Petr", "9012346754", "petr@test.ru", 3L);

        assertEquals(4, userDAO.findAllUsersAndOrders().size());
        assertEquals("Phone", userDAO.findAllUsersAndOrders().get(3).get(user).getItem());

    }
}