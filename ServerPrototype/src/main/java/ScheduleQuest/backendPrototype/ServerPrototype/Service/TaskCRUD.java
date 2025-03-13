package ScheduleQuest.backendPrototype.ServerPrototype.Service;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.TaskRepository;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Service
public class TaskCRUD {

    private final TaskRepository taskRepository;

    public TaskCRUD(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(int userId, Task task) throws SQLException {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        taskRepository.create(userId, task);

        return task;
    }

    public Task getbyId(int taskId) throws SQLException {
        return taskRepository.getById(taskId);
    }


   /* public void delete(int id, Connection connection) throws SQLException {

        if (id == 0 || connection == null) {
            throw new IllegalArgumentException("Task and Connection must not be null");
        }

        TaskRepository taskRepository = new TaskRepository();
        try {
            taskRepository.delete(id, connection);
        } catch (SQLException e) {
            throw new SQLException("Error creating task in the repository: " + e.getMessage(), e);
        }
    }

    /*public List<Task> getAllTasks(Connection connection) throws SQLException {
        TaskRepository taskRepository = new TaskRepository();
        List<Task> queriedTasks = taskRepository.getAllTasksDB(connection);

        for (Task task : queriedTasks) {
            System.out.println(task.getTaskName() + " - " + task.getDifficulty() + " - " + task.getPointValue());
        }
        return queriedTasks;
    } */
}
