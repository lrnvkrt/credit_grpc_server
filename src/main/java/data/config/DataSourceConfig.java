package data.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    public static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5555/finansial_models");
        config.setUsername("postgres");
        config.setPassword("45dq71");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
