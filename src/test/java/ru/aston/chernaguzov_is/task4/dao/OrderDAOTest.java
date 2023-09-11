package ru.aston.chernaguzov_is.task4.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import ru.aston.chernaguzov_is.task4.model.Order;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOTest {

    private OrderDAO orderDAO;

    @Test
    public void findAll() {
        OrderDAO orderDAO = new OrderDAO();

        List<Order> orders = orderDAO.findAll();
        assertEquals("Book", orders.get(0).getItem());
    }

    @Test
    public void findEntityById() {
        OrderDAO orderDAO = new OrderDAO();

        Order order = orderDAO.findEntityById(2L);
        assertEquals("Phone", order.getItem());

    }

    @Test
    public void delete() {
        OrderDAO orderDAO = new OrderDAO();

        assertTrue(orderDAO.delete(1L));
    }

    @Test
    public void create() {
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order("Book");

        assertTrue(orderDAO.create(order));
    }

    @Test
    public void update() {
        OrderDAO orderDAO = new OrderDAO();
        Order beforeUpdate = new Order(3L,"Book");
        Order afterUpdate = new Order(3L,"Phone");

        assertNotEquals(beforeUpdate.getItem(), orderDAO.update(afterUpdate).getItem());


    }
}