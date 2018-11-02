package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PublisherMapperTest {
    @Test
    void mapRowTest() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getInt("id")).thenReturn(1);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Test name");
        PublisherMapper publisherMapper = new PublisherMapper();
        Publisher publisher = publisherMapper.mapRow(resultSetMock, 0);
        Publisher publisherExpected = new Publisher(1,
                "Test name");
        assertEquals(publisherExpected, publisher);
    }
}