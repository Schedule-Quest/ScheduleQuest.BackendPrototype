package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDB {

    public static String loadEnv(String key) {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get(key);
    }
    public static Connection connectToDB() throws SQLException {
        Connection conn = null;
        String dbUser = loadEnv("DB_USER");
        String dbName = loadEnv("DB_NAME");
        String dbPassword = loadEnv("DB_PASSWORD");


        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbUser, dbName, dbPassword);

            if (conn != null) {
                System.out.println("Connection Established");
                //createTaskTableAndEnum(conn);
            } else {
                System.out.println("Connection failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }


}
