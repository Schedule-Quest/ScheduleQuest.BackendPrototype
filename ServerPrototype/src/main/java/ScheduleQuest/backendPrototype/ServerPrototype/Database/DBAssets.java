package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import java.sql.*;

public class DBAssets {
    // Temporary file, until I find a better way to create DB migrations
    public static void initializeSchema(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        createUsersTable(stmt);
        createDifficultyEnum(stmt);
        createTaskTable(stmt);
    }

    public static void createUsersTable(Statement stmt) throws SQLException {
        String checkUserTable = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'user');";
        ResultSet rs = stmt.executeQuery(checkUserTable);
        if (rs.next() && !rs.getBoolean(1)) {
            String userStmt = """
             CREATE TABLE "user" (
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
    }

    public static void createDifficultyEnum(Statement stmt) throws SQLException {
        String checkEnum = "SELECT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'difficulty');";
        ResultSet rs = stmt.executeQuery(checkEnum);
        if (rs.next() && !rs.getBoolean(1)) {
            String enumStmt = """
                CREATE TYPE difficulty AS ENUM ('EASY', 'MEDIUM', 'HARD');
            """;
            stmt.executeUpdate(enumStmt);
            System.out.println("Enum Difficulty has been created");
        }
    }

    public static void createTaskTable(Statement stmt) throws SQLException {
        String checkTaskTable = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'task');";
        ResultSet rs = stmt.executeQuery(checkTaskTable);
        if (rs.next() && !rs.getBoolean(1)) {
            String taskStmt = """
            CREATE TABLE task (
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

}
