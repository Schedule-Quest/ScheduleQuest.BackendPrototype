package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import static ScheduleQuest.backendPrototype.ServerPrototype.Utils.PasswordUtils.generateSalt;
import static ScheduleQuest.backendPrototype.ServerPrototype.Utils.PasswordUtils.hashPassword;

public class UserRepository implements BaseRepository<User>{


    @Override
    public void create(User user, Connection connection) throws SQLException {
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(user.getPassWord(), salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        String addQuery = "INSERT INTO \"user\" (internalId, username, password, salt) VALUES (?, ?, ?, ?)";
        PreparedStatement addStatement = connection.prepareStatement(addQuery);

        addStatement.setInt(1, user.getInternalId());
        addStatement.setString(2, user.getUserName());
        addStatement.setString(3, hashedPassword);
        addStatement.setString(4, saltString);

       addStatement.executeUpdate();
        System.out.println(" " + user.getUserName() + "has been added");

    }

    @Override
    public User getById(int id, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public void update(int id, Connection connection) throws SQLException {

    }

    @Override
    public void delete(int id, Connection connection) throws SQLException {

    }
}
