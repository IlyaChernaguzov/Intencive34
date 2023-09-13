package ru.aston.chernaguzov_is.task4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import ru.aston.chernaguzov_is.task1.exceptions.CodException;
import ru.aston.chernaguzov_is.task4.dao.UserDAO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.exception.MyException;
import ru.aston.chernaguzov_is.task4.model.User;
import ru.aston.chernaguzov_is.task4.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceimpl implements UserService {
    private final UserDAO userDAO;

    private final ObjectMapper objectMapper;

    public UserServiceimpl(UserDAO userDAO, ObjectMapper objectMapper) {
        this.userDAO = userDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDTO read (String email){
        User user = getUser(email);
        UserDTO result = objectMapper.convertValue(user, UserDTO.class);
        result.setId(user.getId());
        return result;
    }

    @Override
    public String create (UserDTO userDTO){
        if (StringUtils.isBlank(userDTO.getSurname()) ||
                StringUtils.isBlank(userDTO.getName()) ||
                StringUtils.isBlank(userDTO.getPhone()) ||
                StringUtils.isBlank(userDTO.getEmail()) ||
                userDTO.getOrderId() == null){
            throw new MyException("Fields cannot be empty", CodException.BAD_REQUEST);
        }

        Optional.ofNullable(userDAO.findUserByEmail(userDTO.getEmail())).ifPresent(
                c -> {throw new MyException("User with email: " + userDTO.getEmail() + " exists", CodException.BAD_REQUEST);
                });

        User user = objectMapper.convertValue(userDTO, User.class);
        String save = "Not OK";
        if(userDAO.create(user)) {
            save = "OK";
        }

        return save;
    }

    @Override
    public UserDTO update (UserDTO userDTO){
        User user = getUser(userDTO.getEmail());

        user.setSurname(userDTO.getSurname() == null ? user.getSurname() : userDTO.getSurname());
        user.setName(userDTO.getName() == null ? user.getName() : userDTO.getName());
        user.setPhone(userDTO.getPhone() == null ? user.getPhone() : userDTO.getPhone());
        user.setEmail(userDTO.getEmail() == null ? user.getEmail() : userDTO.getEmail());
        user.setOrderId(userDTO.getOrderId() == null ? user.getOrderId() : userDTO.getOrderId());

        User afterUpdate = userDAO.update(user);
        UserDTO result = objectMapper.convertValue(afterUpdate, UserDTO.class);
        result.setId(user.getId());
        return result;

    }

    @Override
    public String delete (String email){
        String delete = "Not OK";
        User user = getUser(email);
        if(userDAO.delete(user.getId())){
            delete = "OK";
        }
        return delete;
    }

    private User getUser (String email){
        Optional<User> optionalUser = Optional.ofNullable(userDAO.findUserByEmail(email));
        return optionalUser.orElseThrow(() -> new MyException("User with email: " + email+ " not founded", CodException.BAD_REQUEST));
    }


}
