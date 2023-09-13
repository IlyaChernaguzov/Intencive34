package ru.aston.chernaguzov_is.task4.dao;

import ru.aston.chernaguzov_is.task4.model.Order;

import java.util.List;

public interface DaoDataEntityLayer <T>{
    List<T> findAll();
    T findEntityById(Long id);
    boolean delete(Long id);
    boolean create(T t);
    T update(T t);
}
