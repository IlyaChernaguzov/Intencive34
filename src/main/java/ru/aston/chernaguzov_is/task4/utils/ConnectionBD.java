package ru.aston.chernaguzov_is.task4.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionBD {

    private static Connection connection;

    private ConnectionBD(){}

    public static Connection getConnection() {
        if(connection == null){
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            String driver = resourceBundle.getString("driver");
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;

        }

        return connection;
    }
}
