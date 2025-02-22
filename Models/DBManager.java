package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    public Connection connect_to_db(String dbname, String user, String password) throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

            if (conn != null) {
                System.out.println("Connection Established");
                createTaskTableAndEnum(conn);
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    public void createTaskTableAndEnum(Connection conn) {
        String createEnum =
                "DO $$ " +
                        "BEGIN " +
                        " IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'difficulty') THEN " +
                        "   CREATE TYPE Difficulty AS ENUM ('EASY', 'MEDIUM', 'HARD'); " +
                        "  END IF; " +
                        "END $$;";

        String createTable =
                "CREATE TABLE IF NOT EXISTS Task (" +
                        "id SERIAL PRIMARY KEY, " +
                        "internalId INT NOT NULL, " +
                        "taskname VARCHAR(100) NOT NULL, " +
                        "difficulty Difficulty NOT NULL, " +
                        "pointvalue INT NOT NULL);";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createEnum);
            stmt.executeUpdate(createTable);
            System.out.println("Table exists or it was created!");
        } catch (SQLException e) {
            System.out.println("Oopsie daisy, we got an error: " + e.getMessage());
            e.printStackTrace();

        }
    }
}
