package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEditionMapperTest {

    @Test
    void mapRowTest() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getString("title")).thenReturn("Mocked Title");
        Mockito.when(resultSetMock.getString("description")).thenReturn("Mocked Description");
        Mockito.when(resultSetMock.getString("isbn")).thenReturn("Mocked ISBN");
        BookEditionMapper bookEditionMapper = new BookEditionMapper();
        BookEdition bookEdition = bookEditionMapper.mapRow(resultSetMock, 0);
        BookEdition bookEditionExpected = new BookEdition("Mocked Title", "Mocked Description", "Mocked ISBN");
        assertEquals(bookEditionExpected, bookEdition);
    }
}