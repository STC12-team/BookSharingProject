package ru.innopolis.stc12.booksharing.model.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.postgresql.jdbc.PgConnection;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ConnectionManagerJdbcImplTest {
    private ConnectionManagerJdbcImpl connectionManagerJdbc;

    @BeforeEach
    void setUp() {
        initMocks(this);
        connectionManagerJdbc = new ConnectionManagerJdbcImpl();
    }

    @Test
    void usedCorrectConnectionType() throws SQLException {
        Assertions.assertEquals(PgConnection.class, connectionManagerJdbc.getConnection().getClass());
    }
}
