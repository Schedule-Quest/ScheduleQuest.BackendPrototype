package Storage;

import Models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {

    /**
     * Connects to the database
     * @param dbname
     * @param user
     * @param password
     * @return the connection object to further manipulate with the database
     */
    public Connection connect_to_db(String dbname, String user, String password) {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +dbname, user,password);
            if (conn != null) {
                System.out.println("Connection Established");

                String createTableQuery =
                        "CREATE TABLE IF NOT EXISTS task (" +
                                "id SERIAL PRIMARY KEY, " +
                                "taskname VARCHAR(100) NOT NULL, " +
                                "difficulty ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL, " +
                                "pointvalue INT NOT NULL);";
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(createTableQuery);
                    System.out.println("Table check complete or created");
                }
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }

    public Task AddTask(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        crud.AddTaskToDB(task, connection);

        return task;
    }

    public List<Task> GetAllTasks(Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        List<Task> queriedTasks = crud.GetAllTasksDB(connection);

        for (Task task : queriedTasks) {
            System.out.println(task.getTaskName() + " - " + task.getDifficulty() + " - " + task.getPointValue());
        }
        return queriedTasks;
    }
}

