package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreDB {

    private static final Dotenv dotenv = Dotenv.load();

    public static String loadEnv(String key) {
        return dotenv.get(key);
    }

    public static Properties createConnectionProps() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", loadEnv("DB_USER"));
        connectionProps.put("password", loadEnv("DB_PASSWORD"));
        return connectionProps;
    }

    public static Connection connectToDB() throws SQLException {
        String dbUrl = loadEnv("DB_URL");
        Properties connectionProps = createConnectionProps();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, connectionProps);
            DBAssets.initializeSchema(conn);
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw e;
        }
        return conn;
    }
}
