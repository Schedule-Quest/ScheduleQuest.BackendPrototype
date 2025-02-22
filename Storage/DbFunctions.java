package Storage;

import Models.Task;

import java.sql.*;
import java.util.List;

public class DbFunctions {

    public Task addTask(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        crud.addTaskToDB(task, connection);
        return task;
    }

    public void deleteTask(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        System.out.println("Moving into DB function");
        crud.deleteTaskFromDB(task, connection);

    }

    public List<Task> getAllTasks(Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        List<Task> queriedTasks = crud.getAllTasksDB(connection);

        for (Task task : queriedTasks) {
            System.out.println(task.getTaskName() + " - " + task.getDifficulty() + " - " + task.getPointValue());
        }
        return queriedTasks;
    }

    public boolean checkIfExists(Task task, Connection connection) throws SQLException {
        CRUD crud = new CRUD();
        return crud.checkIfTaskExists(task, connection);
    }
}

