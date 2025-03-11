package ScheduleQuest.backendPrototype.ServerPrototype.Service;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.UserRepository;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserCRUD {
    public User create(User user, Connection connection) throws SQLException {
        if(user == null || connection == null) {
            throw new IllegalArgumentException("Task and Connection must not be null");
        }
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.create(user, connection);
        } catch (SQLException e) {
            throw new SQLException("Error creating task in the repository: " + e.getMessage(), e);
        }
        return user;
    }


    public boolean getById(int id, Connection connection) throws SQLException {
        return false;
    }

    public void delete(int id, Connection connection) throws SQLException {

    }
}
