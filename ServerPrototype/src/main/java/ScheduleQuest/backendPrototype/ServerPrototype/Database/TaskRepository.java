package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import ScheduleQuest.backendPrototype.ServerPrototype.Model.Difficulty;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void create(int userId, Task task, Connection connection) throws SQLException {
        String addQuery = "INSERT INTO Task (taskname, internalId, difficulty, pointvalue) VALUES (?, ?, CAST(? AS difficulty), ?)";

        PreparedStatement addStatement = connection.prepareStatement(addQuery);

        addStatement.setString(1, task.getTaskName());
        addStatement.setInt(2, userId);
        addStatement.setObject(3, task.getDifficulty().name(), Types.OTHER);
        addStatement.setInt(4, task.getPointValue());

        addStatement.executeUpdate();
        System.out.println(" " + task.getTaskName() + " " + " has been added");
    }

    public Task getById(int id, Connection connection) throws SQLException {
        Task task = null;
        String sql = "SELECT * FROM Task WHERE internalid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String difficultyString = rs.getString("difficulty");
                Difficulty difficulty = Difficulty.valueOf(difficultyString);
                task = new Task(rs.getInt("id"), rs.getString("taskname"), difficulty, rs.getInt("pointvalue"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching task by id", e);
        }
        return task;
    }

    public void update(int id, Connection connection) throws SQLException {

    }

    public void delete(int id, Connection connection) throws SQLException {
        String query = "DELETE FROM Task WHERE internalId = ?";
        Task queriedTask = getById(id, connection);

        if (queriedTask!= null){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, queriedTask.getInternalId());
            preparedStatement.executeUpdate();
            System.out.println("Task deleted!");
        } else {
            System.out.println("It don't exist, my man");
        }
    }

    public List<Task> getAllTasksDB(Connection connection) throws SQLException {
        List<Task> allTasks = new ArrayList<>();

        String query = "SELECT internalid, taskname, difficulty, pointvalue FROM Task";
        PreparedStatement addStatement = connection.prepareStatement(query);
        ResultSet resultSet = addStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("internalid");
            String taskName = resultSet.getString("taskname");
            Difficulty difficulty = Difficulty.valueOf(resultSet.getString("difficulty"));
            int pointValue = resultSet.getInt("pointvalue");

            allTasks.add(new Task(id, taskName, difficulty, pointValue));
        }
        return allTasks;
    }




}
