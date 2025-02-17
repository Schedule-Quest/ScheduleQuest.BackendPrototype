import Models.Difficulty;
import Models.Task;
import Storage.DbFunctions;

import java.sql.Connection;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DbFunctions dbFunctions = new DbFunctions();

        Connection connection =  dbFunctions.connect_to_db("postgres", "postgres", "password");
        dbFunctions.GetAllTasks(connection);

    }
}
