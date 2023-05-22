package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Util.getStatement().execute("CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `age` TINYINT DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")");
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Util.getStatement().execute("\t\n" +
                    "DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу.");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String comm = String.format("INSERT INTO users (name, lastName, age) VALUES ('%s', '%s', %d);", name, lastName, age);
        try {
            Util.getStatement().execute(comm);
            System.out.println("User с именем - " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            System.out.println("Не удалось добавить юзера в таблицу.");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String comm = String.format("DELETE FROM users WHERE id = %o", id);
        try {
            Util.getStatement().execute(comm);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить юзера по айди");
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> toReturn = new ArrayList<>();
        try {
            ResultSet resultSet = Util.getStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                toReturn.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось считать юзеров из таблицы");
            e.printStackTrace();
        }
        return toReturn;
    }

    public void cleanUsersTable() {
        try {
            Util.getStatement().execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Не удолась очистить таблицу");
            e.printStackTrace();
        }
    }
}
