package ru.aston.chernaguzov_is.task4;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.chernaguzov_is.task1.exceptions.CodException;
import ru.aston.chernaguzov_is.task4.dao.OrderDAO;
import ru.aston.chernaguzov_is.task4.dao.UserDAO;
import ru.aston.chernaguzov_is.task4.dto.OrderDTO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.exception.MyException;
import ru.aston.chernaguzov_is.task4.model.User;
import ru.aston.chernaguzov_is.task4.service.impl.OrderServiceimpl;
import ru.aston.chernaguzov_is.task4.service.impl.UserServiceimpl;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        OrderDAO orderDAO = new OrderDAO();
//        ObjectMapper objectMapper = new ObjectMapper();
//        OrderServiceimpl orderService = new OrderServiceimpl(orderDAO, objectMapper);
//
//        System.out.println(orderService.read(7L));

//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setItem("Main");
//
//        System.out.println(orderService.create(orderDTO));

//        OrderDTO update = new OrderDTO();
//        update.setItem("Book2");
//        update.setId(5L);
//
//        System.out.println(orderService.update(update));

//        System.out.println(orderService.delete(7L));


        UserDAO userDAO = new UserDAO();
        ObjectMapper objectMapper = new ObjectMapper();
        UserServiceimpl userService = new UserServiceimpl(userDAO, objectMapper);

        UserDTO res = userService.read("ivan@test.ru");
        System.out.println(res);

//        User user = new User("Test", "Test", "Test", "Test", 5L);
        UserDTO test = new UserDTO();
        test.setSurname("Test3");
        test.setEmail("Test");
        test.setOrderId(2L);

       System.out.println(userService.delete("Test"));
//
//        UserDTO test = new UserDTO();
//        test.setSurname("Surname2");
//        test.setName("Name");
//        test.setPhone("Phone");
//        test.setEmail("Email");
//        test.setOrderId(5L);
//
//        System.out.println(userService.update(test));

//        User user = userDAO.findEntityById(1L);
//        System.out.println(user);
//
//        Optional<User> optionalUser = Optional.ofNullable(user);
//
//        Optional<User> userFromOptional = optionalUser.filter(y -> y.getId() != null);
//
//        boolean isNull = optionalUser.isPresent();
//
//        User user1 = optionalUser.orElseThrow(() -> new MyException("User with id not founded", CodException.BAD_REQUEST));
//
//        System.out.println(isNull);
//        System.out.println(userFromOptional.isPresent());
//        System.out.println(user1);
//
//        System.out.println(userDAO.findUserByEmail("ivan@test.rus"));
//
//
//        Optional.ofNullable(userDAO.findUserByEmail("ivan@test.russs")).ifPresent(
//                c -> {throw new MyException("User with email exists", CodException.BAD_REQUEST);
//                });
//
//
//        System.out.println(true);
//
//        System.out.println(userDAO.findEntityById(2L));


    }
}
