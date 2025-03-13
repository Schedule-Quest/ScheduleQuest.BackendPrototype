package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import ScheduleQuest.backendPrototype.ServerPrototype.Utils.EnvRead;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class PostgreDB {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(EnvRead.loadEnv("DB_URL"));
        config.setUsername(EnvRead.loadEnv("DB_USER"));
        config.setPassword(EnvRead.loadEnv("DB_PASSWORD"));

        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection conn = dataSource.getConnection()) {
            DBAssets.initializeSchema(conn);
            System.out.println("Schema initialized");
        } catch (SQLException e) {
            System.out.println("Schema initialization failed");
            throw new RuntimeException(e);
        }

        return dataSource;
    }
}
