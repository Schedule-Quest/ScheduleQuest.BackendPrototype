import Models.DBManager;
import Models.Difficulty;
import Models.Task;
import Storage.DbFunctions;

import java.sql.Connection;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {

        DBManager dbManager = new DBManager();
        DbFunctions dbFunctions = new DbFunctions();


        Connection connection = dbManager.connect_to_db("postgres", "postgres", "password");
        // Task task = new Task(1, "Mop the floor", Difficulty.MEDIUM, 50);

        // dbFunctions.addTask(task, connection);
        // dbFunctions.deleteTask(task1, connection);





    }
}
