package ScheduleQuest.backendPrototype.ServerPrototype;

import ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Difficulty;
import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;
import ScheduleQuest.backendPrototype.ServerPrototype.Service.TaskCRUD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

import static ScheduleQuest.backendPrototype.ServerPrototype.Database.PostgreDB.connectToDB;

@SpringBootApplication
public class ServerPrototypeApplication {

	public static void main(String[] args) throws SQLException {
		Connection connection = connectToDB("postgres", "postgres", "Ern39sur");
		Task task = new Task(1,"Text Maxee", Difficulty.HARD, 100);
		TaskCRUD taskCRUD = new TaskCRUD();
		taskCRUD.getById(1,connection);


		SpringApplication.run(ServerPrototypeApplication.class, args);

	}

}
