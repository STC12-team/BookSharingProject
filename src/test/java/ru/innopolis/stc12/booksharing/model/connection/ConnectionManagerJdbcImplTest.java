package ru.innopolis.stc12.booksharing.model.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.postgresql.jdbc.PgConnection;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ConnectionManagerJdbcImplTest {
    @Mock
    ConnectionManager connectionManagerJdbc;

    @Mock
    Connection connection;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void usedCorrectConnectionType() throws SQLException {
        when(connectionManagerJdbc.getConnection()).thenReturn(connection);
        Assertions.assertNotNull(connection);
    }
}
