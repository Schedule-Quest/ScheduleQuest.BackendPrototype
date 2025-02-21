package Storage;

import Models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {

    /**
     *  Connects to the DB, creates the enum and table if they do not already exist in the database.
     * @param dbname
     * @param user
     * @param password
     * @return
     */
    public Connection connect_to_db(String dbname, String user, String password) {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

            if (conn != null) {
                System.out.println("Connection Established");

                String createDifficultyEnum =
                        "DO $$ " +
                                "BEGIN " +
                                "    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'difficulty') THEN " +
                                "        CREATE TYPE Difficulty AS ENUM('EASY', 'MEDIUM', 'HARD'); " +
                                "    END IF; " +
                                "END $$;";

                String createTableQuery =
                        "CREATE TABLE IF NOT EXISTS Task (" +
                                "id SERIAL PRIMARY KEY, " +
                                "internalID INT NOT NULL, " +
                                "taskname VARCHAR(100) NOT NULL, " +
                                "difficulty Difficulty NOT NULL, " +
                                "pointvalue INT NOT NULL);";

                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(createDifficultyEnum);
                    stmt.executeUpdate(createTableQuery);
                    System.out.println("Table check complete or created");
                }
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Task AddTask(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        crud.AddTaskToDB(task, connection);

        return task;
    }

    public void DeleteTask(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        System.out.println("Moving into DB function");
        crud.DeleteTaskFromDB(task, connection);

    }

    public List<Task> GetAllTasks(Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        List<Task> queriedTasks = crud.GetAllTasksDB(connection);

        for (Task task : queriedTasks) {
            System.out.println(task.getTaskName() + " - " + task.getDifficulty() + " - " + task.getPointValue());
        }
        return queriedTasks;
    }

    public boolean CheckIfExists(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        return crud.CheckIfTaskExists(task, connection);
    }
}

