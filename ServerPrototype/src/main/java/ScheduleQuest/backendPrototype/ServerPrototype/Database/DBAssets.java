package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAssets {
    // Temporary file, until I find a better way to create DB migrations
    public static void initializeSchema(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        createUsersTable(stmt);
        createDifficultyEnum(stmt);
        createTaskTable(stmt);
    }

    public static void createUsersTable(Statement stmt) throws SQLException {
        String userStmt = """
                 CREATE TABLE IF NOT EXISTS "user" (
                 id SERIAL PRIMARY KEY,
                 internalId INT NOT NULL,
                 username VARCHAR(50) NOT NULL,
                 password VARCHAR(255) NOT NULL,
                 salt VARCHAR(255) NOT NULL
                 );
                """;
        stmt.executeUpdate(userStmt);
        System.out.println("UserTable has been created");
    }

    public static void createDifficultyEnum(Statement stmt) throws SQLException {
        String enumStmt = """
                    DO $$
                    BEGIN
                        -- Check if the 'difficulty' type already exists
                        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'difficulty') THEN
                            CREATE TYPE difficulty AS ENUM ('EASY', 'MEDIUM', 'HARD');
                        END IF;
                    END
                    $$;
                """;
        stmt.executeUpdate(enumStmt);
        System.out.println("Enum Difficulty has been created");
    }

    public static void createTaskTable(Statement stmt) throws SQLException {
        String taskStmt = """
                CREATE TABLE IF NOT EXISTS task (
                id SERIAL PRIMARY KEY,
                internalId INT NOT NULL,
                taskname VARCHAR(100) NOT NULL,
                difficulty difficulty NOT NULL,
                pointvalue INT NOT NULL
                );
                """;
        stmt.executeUpdate(taskStmt);
        System.out.println("TaskTable has been created");
    }


}
