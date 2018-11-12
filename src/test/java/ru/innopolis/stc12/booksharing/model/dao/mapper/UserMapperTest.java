package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    @Test
    void mapRowTestFull() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getLong("id")).thenReturn((long) 1);
        Mockito.when(resultSetMock.getString("login")).thenReturn("Mocked login");
        Mockito.when(resultSetMock.getString("password")).thenReturn("Mocked password");
        Mockito.when(resultSetMock.getString("role_name")).thenReturn("Mocked role");
        Mockito.when(resultSetMock.getInt("role_id")).thenReturn(2);
        Mockito.when(resultSetMock.getInt("enabled")).thenReturn(1);
        UserMapper userMapper = new UserMapper();
        User userEdition = userMapper.mapRow(resultSetMock, 0);
        User userExpected = new User(1, "Mocked login", "Mocked password", 2, "Mocked role", 1);
        assertEquals(userExpected, userEdition);
    }

    @Test
    void mapRowTestNull() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        UserMapper userMapper = new UserMapper();
        User userEdition = userMapper.mapRow(resultSetMock, 0);
        User userExpected = new User();
        assertEquals(userExpected, userEdition);
    }

}
