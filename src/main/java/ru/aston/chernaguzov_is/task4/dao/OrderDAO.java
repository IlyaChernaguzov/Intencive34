package ru.aston.chernaguzov_is.task4.dao;

import jdk.nashorn.internal.objects.annotations.Property;
import ru.aston.chernaguzov_is.task4.model.Order;
import ru.aston.chernaguzov_is.task4.utils.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDAO implements DaoDataEntityLayer<Order>{

//    private static final String URL = "jdbc:postgresql://localhost:5432/task_4";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "443175007143175ii";
//
//   private static Connection connection;
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
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = ConnectionBD.getConnection();
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM orders";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setItem(resultSet.getString("item_name"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order findEntityById(Long id) {
        Order order = null;

        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setItem(resultSet.getString("item_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;

    }

    @Override
    public boolean delete(Long id) {
        boolean isModifiedRows = false;
        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM orders WHERE id=?");
            preparedStatement.setLong(1, id);


            if (preparedStatement.executeUpdate() > 0)
                isModifiedRows = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isModifiedRows;
    }

    @Override
    public boolean create(Order order) {
        boolean isModifiedRows = false;
        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO orders (item_name) VALUES(?)");
            preparedStatement.setString(1, order.getItem());

            if (preparedStatement.executeUpdate() > 0)
                isModifiedRows = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isModifiedRows;
    }

    //        @Override
//        public User create(User user) {
//            Connection connection = connectionPoolBuilder.getConnection();
//            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname,lastname,email,phone) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
//                preparedStatement.setString(1,user.getFirstname());
//                preparedStatement.setString(2,user.getLastname());
//                preparedStatement.setString(3,user.getEmail());
//                preparedStatement.setString(4,user.getPhone());
//                preparedStatement.executeUpdate();
//                ResultSet res =  preparedStatement.getGeneratedKeys();
//                res.next();
//                user.setId(res.getInt(1));
//                return user;
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            finally {
//                connectionPoolBuilder.releaseConnection(connection);
//            }
//        }

    @Override
    public Order update(Order order) {
        Order updateOrder = null;
        try {
            Connection connection = ConnectionBD.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE orders SET item_name=? WHERE id=?");
            preparedStatement.setString(1, order.getItem());
            preparedStatement.setLong(2, order.getId());

            preparedStatement.executeUpdate();

            updateOrder = findEntityById(order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateOrder;
    }

}
