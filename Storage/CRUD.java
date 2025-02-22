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

    /**
     * Adds a task to the DB.
     * @param task The task to be added.
     * @param connection The DB in question.
     * @throws SQLException
     */
    public void addTaskToDB(Task task, Connection connection) throws SQLException {
        String addQuery = "INSERT INTO Task (taskname, internalId, difficulty, pointvalue) VALUES (?, ?, CAST(? AS difficulty), ?)";

        PreparedStatement addStatement = connection.prepareStatement(addQuery);

        addStatement.setString(1, task.getTaskName());
        addStatement.setInt(2, task.getInternalId());
        addStatement.setObject(3, task.getDifficulty().name(), java.sql.Types.OTHER);
        addStatement.setInt(4, task.getPointValue());

        addStatement.executeUpdate();
        System.out.println(" " + task.getTaskName() + " " + "has been added");
    }

    /**
     * Gathers all Tasks that are in the database.
     * @param connection = the database in question.
     * @return Returns it in a list.
     * @throws SQLException
     */
    public List<Task> getAllTasksDB(Connection connection) throws SQLException {
        List<Task> allTasks = new ArrayList<>();

        String query = "SELECT taskname, difficulty, pointvalue FROM Task";
        PreparedStatement addStatement = connection.prepareStatement(query);
        ResultSet resultSet = addStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String taskName = resultSet.getString("taskname");
            Difficulty difficulty = Difficulty.valueOf(resultSet.getString("difficulty"));
            int pointValue = resultSet.getInt("pointvalue");

            allTasks.add(new Task(id, taskName, difficulty, pointValue));
        }
        return allTasks;
    }

    /**
     * Checks if a Task Exists in the database, and deletes it accordingly.
     * @param task used to identify a tasks internalID, which is then used in the preparedStatement variable.
     * @param connection to the db.
     * @return a boolean to verify whether it exists or not.
     * @throws SQLException
     */

    public boolean checkIfTaskExists(Task task, Connection connection) throws SQLException {
        String query = "SELECT internalid, taskname FROM Task WHERE internalId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, task.getInternalId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int internalId = resultSet.getInt("internalid");
            String taskName = resultSet.getString("taskname");

            System.out.println("Task found: " + internalId + ", " + taskName);
            return true;
        }
        return false;
    }


    /**
     * Deletes a task from the database.
     * @param task Targets the tasks internalId, which is used for deletion.
     * @param connection
     * @throws SQLException
     */
    public void deleteTaskFromDB(Task task, Connection connection) throws SQLException {
        String query = "DELETE FROM Task WHERE internalId = ?";

        if (checkIfTaskExists(task, connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, task.getInternalId());
            preparedStatement.executeUpdate();
            System.out.println("Task deleted!");
        } else {
            System.out.println("It don't exist, my man");
        }
    }
}

