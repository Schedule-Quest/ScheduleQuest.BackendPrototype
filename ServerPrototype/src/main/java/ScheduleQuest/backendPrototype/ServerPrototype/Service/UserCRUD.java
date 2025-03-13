package ScheduleQuest.backendPrototype.ServerPrototype.Service;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.UserRepository;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserCRUD {
    private final UserRepository userRepository;

    public UserCRUD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws SQLException {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.create(user);
        return user;
    }


    public boolean getById(int id, Connection connection) throws SQLException {
        return false;
    }

    public void delete(int id, Connection connection) throws SQLException {

    }
}
