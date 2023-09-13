package ru.aston.chernaguzov_is.task4.service;

import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.model.User;

public interface UserService {
    UserDTO create(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO read(String email);

    boolean delete(String email);

}
