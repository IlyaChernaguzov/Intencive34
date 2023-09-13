package ru.aston.chernaguzov_is.task4.dao;

import ru.aston.chernaguzov_is.task4.model.Order;
import ru.aston.chernaguzov_is.task4.model.User;
import ru.aston.chernaguzov_is.task4.utils.ConnectionBD;

import java.sql.*;
import java.util.*;

public class UserDAO implements DaoDataEntityLayer<User>{

//    private static final String URL = "jdbc:postgresql://localhost:5432/task_4";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "443175007143175ii";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            Connection connection = ConnectionBD.getConnection();
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setOrderId(resultSet.getLong("orderID"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findEntityById(Long id) {
        User user = null;

        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setOrderId(resultSet.getLong("orderID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByEmail(String email) {
        User user = null;

        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where email = ?");
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setOrderId(resultSet.getLong("orderID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        boolean modifiedRows = false;
        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);


            if (preparedStatement.executeUpdate() > 0)
                modifiedRows = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modifiedRows;
    }

    @Override
    public boolean create(User user) {
        boolean isModifiedRows = false;

        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users (surname, name, phone, email, orderID) VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getOrderId());

            if (preparedStatement.executeUpdate() > 0)
                isModifiedRows = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isModifiedRows;
    }

    @Override
    public User update(User user) {
        User updateUser = null;
        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE users SET surname=?, name=?, phone=?, email=?, orderID=? WHERE id=?");
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getOrderId());
            preparedStatement.setLong(6, user.getId());

            preparedStatement.executeUpdate();

            updateUser = findEntityById(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateUser;
    }


    public List <Map<User, Order>> findAllUsersAndOrders(){
        List<Map<User, Order>> usersAndOrders = new ArrayList<>();

        try {
            Connection connection = ConnectionBD.getConnection();
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users u JOIN orders o ON o.id=u.orderID";
            ResultSet resultSet = statement.executeQuery(SQL);


            while (resultSet.next()){
                Map<User, Order> userAndOrder = new HashMap<>();

                User userMap = new User();
                userMap.setId(resultSet.getLong("id"));
                userMap.setSurname(resultSet.getString("surname"));
                userMap.setName(resultSet.getString("name"));
                userMap.setPhone(resultSet.getString("phone"));
                userMap.setEmail(resultSet.getString("email"));
                userMap.setOrderId(resultSet.getLong("orderID"));

                Order orderMap = new Order();
                orderMap.setId(resultSet.getLong("orderID"));
                orderMap.setItem(resultSet.getString("item_name"));

                userAndOrder.put(userMap, orderMap);
                usersAndOrders.add(userAndOrder);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersAndOrders;
    }
}
