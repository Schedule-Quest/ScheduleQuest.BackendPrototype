package ScheduleQuest.backendPrototype.ServerPrototype.Controller;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.UserCRUD;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserCRUD userCRUD;

    public UserController(UserCRUD userCRUD) {
        this.userCRUD = userCRUD;
    }

    @PostMapping("/create")

    public User createUser(@RequestBody User user) throws SQLException {
        return userCRUD.create(user);
    }
}
