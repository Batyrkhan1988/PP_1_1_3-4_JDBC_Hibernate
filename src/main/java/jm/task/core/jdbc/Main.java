package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Метхун", "Чакраборти", (byte) 66);
        userService.saveUser("Ашвари", "Рай", (byte) 45);
        userService.saveUser("Шахрух", "Хан", (byte) 54);
        userService.saveUser("Радж", "Капур", (byte) 32);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(1);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
