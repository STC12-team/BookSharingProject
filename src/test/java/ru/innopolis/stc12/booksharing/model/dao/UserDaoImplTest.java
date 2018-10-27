package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
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
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getString(anyString())).thenReturn("dummy");

        userDao = new UserDaoImpl(connectionManager);
    }

    @Test
    void getUserByLogin() throws SQLException {
        userDao.getUserByLogin("dummy");
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(anyInt(), anyString());
        verify(resultSet, times(1)).next();
    }

    @Test
    void getAllUsers() throws SQLException {
        userDao.getAllUsers();
        verify(connection, times(1)).prepareStatement(anyString());
        verify(resultSet, times(1)).next();
    }
}
