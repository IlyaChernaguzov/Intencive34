package ru.aston.chernaguzov_is.task4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import ru.aston.chernaguzov_is.task4.dao.UserDAO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.exception.MyException;
import ru.aston.chernaguzov_is.task4.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class UserServiceimplTest {

    static UserDAO mock;
    static ObjectMapper objectMapper;
    static UserServiceimpl userService;
    static User user;


    @BeforeAll
    static void init (){
        mock = Mockito.mock(UserDAO.class);
        objectMapper = new ObjectMapper();
        userService = new UserServiceimpl(mock, objectMapper);
        user = new User(1L, "Surname", "Name", "Phone", "Email", 3L);
    }



    @Test
    void read() {

        when(mock.findUserByEmail(anyString())).thenReturn(user);

        UserDTO actual = userService.read("Email");

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user.getName(), actual.getName());
    }


    @Test
    void create() {

        UserDTO test = new UserDTO();
        test.setSurname("Surname");
        test.setName("Name");
        test.setPhone("Phone");
        test.setEmail("Email");
        test.setOrderId(3L);


        when(mock.findUserByEmail(Mockito.anyString())).thenReturn(null);
        when(mock.create(any(User.class))).thenReturn(true);

        UserDTO actual = userService.create(test);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(test.getName(), actual.getName());

    }

    @Test
    void update() {

        UserDTO test = new UserDTO();
        test.setSurname("Surname2");
        test.setEmail("Email");
        test.setOrderId(5L);

        User userAfterUpdate = new User(1L, "Surname2", "Name", "Phone", "Email", 5L);

        when(mock.findUserByEmail(anyString())).thenReturn(user);
        when(mock.update(any(User.class))).thenReturn(userAfterUpdate);

        UserDTO actual = userService.update(test);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(test.getSurname(), actual.getSurname());

    }

    @Test
    void delete() {

        when(mock.findUserByEmail(anyString())).thenReturn(user);
        when(mock.delete(anyLong())).thenReturn(true);

        boolean isDelete = userService.delete(user.getEmail());

        verify(mock, times(1)).delete(anyLong());
        assertTrue(isDelete);

    }
}