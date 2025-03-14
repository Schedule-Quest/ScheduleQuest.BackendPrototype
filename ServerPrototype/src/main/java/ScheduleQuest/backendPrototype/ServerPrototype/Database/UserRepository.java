package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import static ScheduleQuest.backendPrototype.ServerPrototype.Utils.PasswordUtils.generateSalt;
import static ScheduleQuest.backendPrototype.ServerPrototype.Utils.PasswordUtils.hashPassword;

@Repository
public class UserRepository{

    private final DataSource dataSource;
    @Autowired
    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(User user) throws SQLException {
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(user.getPassWord(), salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        String addQuery = "INSERT INTO \"user\" (username, password, salt) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement addStatement = connection.prepareStatement(addQuery)) {
            addStatement.setString(1, user.getUserName());
            addStatement.setString(2, hashedPassword);
            addStatement.setString(3, saltString);

            addStatement.executeUpdate();
            System.out.println(" " + user.getUserName() + "has been added");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public User getById(int id, Connection connection) throws SQLException {
        return null;
    }

    public void update(int id, Connection connection) throws SQLException {

    }

    public void delete(int id, Connection connection) throws SQLException {

    }
}
