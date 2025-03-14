package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import java.sql.*;

public class DBAssets {
    // Temporary file, until I find a better way to create DB migrations
    public static void initializeSchema(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        createUsersTable(stmt);
        createDifficultyTable(stmt);
        createTaskTable(stmt);
    }

    public static void createUsersTable(Statement stmt) throws SQLException {
        String checkUserTable = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'user');";
        try (ResultSet rs = stmt.executeQuery(checkUserTable)) {
            if (rs.next() && !rs.getBoolean(1)) {
                String userStmt = """
                CREATE TABLE "user" (
                userId SERIAL PRIMARY KEY,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                salt VARCHAR(255) NOT NULL
                );
                """;
                stmt.executeUpdate(userStmt);
                System.out.println("User table has been created");
            }
        }
    }

    public static void createDifficultyTable(Statement stmt) throws SQLException {
        String checkDifficultyTable = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'difficulty');";
        try (ResultSet rs = stmt.executeQuery(checkDifficultyTable)) {
            if (rs.next() && !rs.getBoolean(1)) {
                String difficultyStmt = """
                    CREATE TABLE difficulty (
                    difficultyId SERIAL PRIMARY KEY,
                    level VARCHAR(50) NOT NULL UNIQUE
                    );
                    """;
                stmt.executeUpdate(difficultyStmt);
                System.out.println("Difficulty table has been created");
            }
        }
    }

    public static void createTaskTable(Statement stmt) throws SQLException {
        String checkTaskTable = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'tasks');";
        try (ResultSet rs = stmt.executeQuery(checkTaskTable)) {
            if (rs.next() && !rs.getBoolean(1)) {
                String taskStmt = """
                CREATE TABLE tasks (
                taskId SERIAL PRIMARY KEY,
                userId INT REFERENCES "user"(userId),
                taskname VARCHAR(100) NOT NULL,
                difficultyId INT REFERENCES difficulty(difficultyId),
                pointvalue INT NOT NULL,
                createdAt TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
                );
                """;
                stmt.executeUpdate(taskStmt);
                System.out.println("Tasks table has been created");
            }
        }
    }
}
