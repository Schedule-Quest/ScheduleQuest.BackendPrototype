package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDB {

    public static Connection connectToDB(String dbname, String user, String password) throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

            if (conn != null) {
                System.out.println("Connection Established");
                //createTaskTableAndEnum(conn);
            } else {
                System.out.println("Connection failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

}
