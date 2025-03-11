package ScheduleQuest.backendPrototype.ServerPrototype.Validation;

import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserValidation {
    // TODO REFACTOR, so that it instead calls on Service Layer
    public boolean verifyUserExists(int userId, Connection connection) throws SQLException {
        String checkQuery = "SELECT 1 FROM `user` WHERE `user`.internalid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(checkQuery)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }



}
