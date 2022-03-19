package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Обработка всех исключений
public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }
    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS db.users" +
                    "(id mediumint not null auto_increment," +
                    "name VARCHAR(45)," +
                    "lastname VARCHAR(45)," +
                    "age tinyint," +
                    "PRIMARY KEY (id))");
            System.out.println("Таблица создана");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS db.users");
            System.out.println("Таблица удалена");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO db.users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User – " + name + " " + lastName + " добавлен в базу данных");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM db.users WHERE id";
            statement.executeUpdate(sql);
            System.out.println("User " + id + " удален");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM db.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;
    }
    // Очистка содержания таблицы
    public void cleanUsersTable() {
        String sql = "TRUNCATE db.users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось очистить");
        }
    }
}
