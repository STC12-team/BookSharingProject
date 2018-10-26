package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManager;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoImplTest {
    @Mock
    ConnectionManagerJdbcImpl connectionManager;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        when(connectionManager.getConnection()).thenReturn(connection);
        doNothing().when(connection).commit();
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(true);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getString(anyString())).thenReturn("dummy");

        userDao = new UserDaoImpl(connectionManager);
    }

    @Test
    void getUserByLogin() throws SQLException {
        verify(connection, times(0)).prepareStatement(anyString());
        verify(preparedStatement, times(0)).setString(anyInt(), anyString());
        verify(connection, times(0)).commit();
        verify(resultSet, times(0)).next();
    }
}
