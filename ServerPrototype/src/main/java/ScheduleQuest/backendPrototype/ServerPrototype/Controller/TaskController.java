package ScheduleQuest.backendPrototype.ServerPrototype.Controller;

import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.TaskCRUD;
import ScheduleQuest.backendPrototype.ServerPrototype.Utils.Validation.UserValidation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskCRUD taskCRUD;

    private final UserValidation userValidation;

    public TaskController(TaskCRUD taskCRUD, UserValidation userValidation) {
        this.taskCRUD = taskCRUD;
        this.userValidation = userValidation;
    }

    @PostMapping("/{userId}/create")
    public Task createTask(@PathVariable("userId") int userId, @RequestBody Task task) throws SQLException {
        return taskCRUD.create(userId, task);
    }




    /*@GetMapping
    public List<Task> getAllTasks() {
        try (Connection connection = PostgreDB.connectToDB()) {
            return taskCRUD.getAllTasks(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    } */

   /* @PostMapping("/{userId}/create")
    public Task createTaskForUser(@PathVariable("userId") int userId, @RequestBody Task task) {
        try (Connection connection = PostgreDB.connectToDB()) {
            if (userValidation.verifyUserExists(userId, connection)) {
                return taskCRUD.create(userId, task, connection);
            }
            throw new RuntimeException("User with ID " + userId + " does not exist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } */

}
