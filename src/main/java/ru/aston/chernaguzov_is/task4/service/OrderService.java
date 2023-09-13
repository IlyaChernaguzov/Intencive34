package ru.aston.chernaguzov_is.task4.service;

import ru.aston.chernaguzov_is.task4.dto.OrderDTO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;

public interface OrderService {

    String create(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    OrderDTO read(Long id);

    String delete(Long id);
}
