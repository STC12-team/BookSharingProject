package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEditionMapperTest {
    @Test
    void mapRowTest() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getInt("b_id")).thenReturn(1);
        Mockito.when(resultSetMock.getString("b_title")).thenReturn("Mocked Title");
        Mockito.when(resultSetMock.getString("b_description")).thenReturn("Mocked Description");
        Mockito.when(resultSetMock.getString("b_isbn")).thenReturn("Mocked ISBN");
        Mockito.when(resultSetMock.getInt("b_year")).thenReturn(2018);
        Mockito.when(resultSetMock.getInt("p_id")).thenReturn(1);
        Mockito.when(resultSetMock.getString("p_name")).thenReturn("Mocked name");


        BookEdition bookEditionExpected = new BookEdition(1,
                "Mocked Title",
                "Mocked Description",
                "Mocked ISBN",
                new Publisher(1, "Mocked name"),
                2018);
    //todo   assertEquals(bookEditionExpected, bookEdition);
    }
}