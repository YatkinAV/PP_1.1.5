package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Andrew", "Pavlov", (byte) 29);
        User user2 = new User("Alex", "Bybnov", (byte) 23);
        User user3 = new User("Dmitrii", "Dorlov", (byte) 39);
        User user4 = new User("Vasilii", "Kotov", (byte) 19);

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> userList = userService.getAllUsers();

        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }

        userList.removeAll(userList);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
