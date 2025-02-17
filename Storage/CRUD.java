package Storage;

import Models.Difficulty;
import Models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public void AddTaskToDB(Task task, Connection connection) throws SQLException {
        String addQuery = "INSERT INTO Task (taskname, difficulty, pointvalue) VALUES (?, CAST(? AS difficulty), ?)";

        PreparedStatement addStatement = connection.prepareStatement(addQuery);

        addStatement.setString(1, task.getTaskName());
        addStatement.setObject(2, task.getDifficulty().name(), java.sql.Types.OTHER);
        addStatement.setInt(3, task.getPointValue());

        addStatement.executeUpdate();
        System.out.println(" " + task.getTaskName() + " " + "has been added");
    }

    public List<Task> GetAllTasksDB(Connection connection) throws SQLException {
        List<Task> AllTasks = new ArrayList<>();

        String Query = "SELECT taskname, difficulty, pointvalue FROM Task";
        PreparedStatement addStatement = connection.prepareStatement(Query);
        ResultSet resultSet = addStatement.executeQuery();

        while (resultSet.next()) {
            String taskName = resultSet.getString("taskname");
            Difficulty difficulty = Difficulty.valueOf(resultSet.getString("difficulty"));
            int pointValue = resultSet.getInt("pointvalue");

            AllTasks.add(new Task(taskName, difficulty, pointValue));
        }
        return AllTasks;
    }

    public void DeleteTaskFromDB(StorageTask task, Connection connection) throws SQLException {

    }


}
