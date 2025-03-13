package ScheduleQuest.backendPrototype.ServerPrototype;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB;
import ScheduleQuest.backendPrototype.ServerPrototype.Database.UserRepository;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Difficulty;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.User;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.TaskCRUD;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.UserCRUD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
public class ServerPrototypeApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ServerPrototypeApplication.class, args);
	}

}
