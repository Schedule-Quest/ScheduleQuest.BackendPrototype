package ScheduleQuest.backendPrototype.ServerPrototype.Controller;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.TaskCRUD;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB.connectToDB;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskCRUD taskCRUD;

    public TaskController(TaskCRUD taskCRUD) {
        this.taskCRUD = taskCRUD;
    }


    @GetMapping
    public List<Task> getAllTasks() {
        try (Connection connection = PostgreDB.connectToDB()) {
            return taskCRUD.getAllTasks(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }
}
