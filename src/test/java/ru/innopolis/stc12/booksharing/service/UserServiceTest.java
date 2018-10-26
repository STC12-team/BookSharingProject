package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.connection.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.UserDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @Mock
    ConnectionManagerJdbcImpl connectionManager;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    UserDao userDao;
    UserService userService;

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        userDao = new UserDaoImpl(connectionManager);
        userService = new UserService(userDao);
    }

    @Test
    void getUsers() throws SQLException {
        userService.getUsers();
        verify(connection, times(1)).prepareStatement(anyString());
        verify(resultSet, times(1)).next();
    }

    @Test
    void getUserByLogin() throws SQLException {
        userService.getUserByLogin("dummy");
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(anyInt(), anyString());
        verify(resultSet, times(1)).next();
    }
}
