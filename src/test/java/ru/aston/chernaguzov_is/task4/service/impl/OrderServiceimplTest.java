package ru.aston.chernaguzov_is.task4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.aston.chernaguzov_is.task4.dao.OrderDAO;
import ru.aston.chernaguzov_is.task4.dto.OrderDTO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.model.Order;
import ru.aston.chernaguzov_is.task4.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderServiceimplTest {

    static OrderDAO mock;
    static ObjectMapper objectMapper;
    static OrderServiceimpl orderService;
    static Order order;


    @BeforeAll
    static void init (){
        mock = Mockito.mock(OrderDAO.class);
        objectMapper = new ObjectMapper();
        orderService = new OrderServiceimpl(mock, objectMapper);
        order = new Order(1L, "Book");
    }

    @Test
    void create() {
        OrderDTO test = new OrderDTO();
        test.setItem("Test");

        when(mock.create(any(Order.class))).thenReturn(true);

        String actual = orderService.create(test);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals("OK", actual);
    }

    @Test
    void update() {
        OrderDTO test = new OrderDTO();
        test.setId(1L);
        test.setItem("Test");

        Order afterUpdate = new Order(1L, "Test");

        when(mock.findEntityById(anyLong())).thenReturn(order);
        when(mock.update(any(Order.class))).thenReturn(afterUpdate);

        OrderDTO actual = orderService.update(test);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(test.getItem(), actual.getItem());
    }

    @Test
    void read() {
        when(mock.findEntityById(anyLong())).thenReturn(order);

        OrderDTO actual = orderService.read(1L);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(order.getItem(), actual.getItem());
    }

    @Test
    void delete() {
        when(mock.findEntityById(anyLong())).thenReturn(order);
        when(mock.delete(anyLong())).thenReturn(true);

        String actual = orderService.delete(order.getId());

        verify(mock, times(1)).delete(anyLong());
        assertEquals("OK", actual);
    }
}