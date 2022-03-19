package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Abashka2016";
    private static Connection connection=null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
            if(!connection.isClosed()){
                System.out.println("Соединение с БД установлено.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Не удалось загрузить класс драйвера!");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Подключение к БД закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось закрыть подключение");
        }
    }
}
