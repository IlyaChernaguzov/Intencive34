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
        return objectMapper.convertValue(user, UserDTO.class);
    }

    @Override
    public UserDTO create (UserDTO userDTO){

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
        UserDTO save = null;
        if(userDAO.create(user)) {
            save = userDTO;
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
        return objectMapper.convertValue(afterUpdate, UserDTO.class);

    }

    @Override
    public boolean delete (String email){
        boolean isDelete = false;
        User user = getUser(email);
        if(userDAO.delete(user.getId())){
            isDelete = true;
        }
        return isDelete;
    }

    private User getUser (String email){
        Optional<User> optionalUser = Optional.ofNullable(userDAO.findUserByEmail(email));
        return optionalUser.orElseThrow(() -> new MyException("User with email: " + email+ " not founded", CodException.BAD_REQUEST));
    }


}
