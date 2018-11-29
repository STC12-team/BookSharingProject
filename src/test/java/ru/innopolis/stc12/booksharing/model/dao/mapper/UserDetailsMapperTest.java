package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDetailsMapperTest {
    @Test
    void mapRowTest() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getLong("id")).thenReturn((long) 1);
        Mockito.when(resultSetMock.getLong("user_id")).thenReturn((long) 1);
        Mockito.when(resultSetMock.getString("firstname")).thenReturn("Mocked firstname");
        Mockito.when(resultSetMock.getString("surname")).thenReturn("Mocked surname");
        Mockito.when(resultSetMock.getString("lastname")).thenReturn("Mocked lastname");
        Mockito.when(resultSetMock.getString("email")).thenReturn("Mocked email");
        Mockito.when(resultSetMock.getString("password")).thenReturn("Mocked password");
        UserDetailsMapper mapper = new UserDetailsMapper();
        UserDetails details = mapper.mapRow(resultSetMock, 0);
        UserDetails detailsExpected = new UserDetails(1, 1, "Mocked firstname", "Mocked lastname", "Mocked surname", "Mocked email", "Mocked password");
        assertEquals(detailsExpected, details);
    }
}
