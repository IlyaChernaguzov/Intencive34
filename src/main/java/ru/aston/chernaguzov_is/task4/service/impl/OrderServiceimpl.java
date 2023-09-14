package ru.aston.chernaguzov_is.task4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import ru.aston.chernaguzov_is.task1.exceptions.CodException;
import ru.aston.chernaguzov_is.task4.dao.OrderDAO;
import ru.aston.chernaguzov_is.task4.dto.OrderDTO;
import ru.aston.chernaguzov_is.task4.dto.UserDTO;
import ru.aston.chernaguzov_is.task4.exception.MyException;
import ru.aston.chernaguzov_is.task4.model.Order;
import ru.aston.chernaguzov_is.task4.model.User;
import ru.aston.chernaguzov_is.task4.service.OrderService;

import java.util.Optional;

public class OrderServiceimpl implements OrderService {

    private final OrderDAO orderDAO;
    private final ObjectMapper objectMapper;

    public OrderServiceimpl(OrderDAO orderDAO, ObjectMapper objectMapper) {
        this.orderDAO = orderDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    public String create(OrderDTO orderDTO) {
        if (StringUtils.isBlank(orderDTO.getItem())){
            throw new MyException("Item cannot be empty", CodException.BAD_REQUEST);
        }

        Order order = objectMapper.convertValue(orderDTO, Order.class);
        String result = "Not OK";
        if(orderDAO.create(order)) {
            result = "OK";
        }

        return result;
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = getOrder(orderDTO.getId());

        order.setItem(orderDTO.getItem() == null ? order.getItem() : orderDTO.getItem());

        Order afterUpdate = orderDAO.update(order);
        OrderDTO result = objectMapper.convertValue(afterUpdate, OrderDTO.class);
        result.setId(order.getId());
        return result;
    }

    @Override
    public OrderDTO read(Long id) {
        Order order = getOrder(id);
        OrderDTO result = objectMapper.convertValue(order, OrderDTO.class);
        result.setId(order.getId());
        return result;
    }

    @Override
    public String delete(Long id) {
        String result = "Not OK";
        Order order = getOrder(id);
        if(orderDAO.delete(id)){
            result = "OK";
        }
        return result;
    }

    private Order getOrder (Long id){
        Optional<Order> optionalOrder = Optional.ofNullable(orderDAO.findEntityById(id));
        return optionalOrder.orElseThrow(() -> new MyException("User with id: " + id+ " not founded", CodException.BAD_REQUEST));
    }
}
