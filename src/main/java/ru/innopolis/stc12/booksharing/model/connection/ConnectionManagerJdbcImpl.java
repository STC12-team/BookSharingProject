package ru.innopolis.stc12.booksharing.model.connection;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@EnableWebMvc
@Configuration
public class ConnectionManagerJdbcImpl extends WebMvcConfigurerAdapter implements ConnectionManager {

    private Properties property;
    private static Logger logger = Logger.getLogger(ConnectionManagerJdbcImpl.class);

    public ConnectionManagerJdbcImpl() {
        this.property = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("config.properties");
            property.load(stream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(property.getProperty("driver"));
        driverManagerDataSource.setUrl(property.getProperty("url"));
        driverManagerDataSource.setUsername(property.getProperty("username"));
        driverManagerDataSource.setPassword(property.getProperty("password"));

        return driverManagerDataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource().getConnection();
    }
}
