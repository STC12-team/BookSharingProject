package ru.innopolis.stc12.booksharing.model.connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    private static Logger LOGGER = Logger.getLogger(ConnectionManagerJdbcImpl.class);
    private Properties property = new Properties();

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJdbcImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("config.properties");
            property.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        String driver = property.getProperty("driver");
        String password = property.getProperty("password");
        String user = property.getProperty("username");
        String url = property.getProperty("url");
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }
}
