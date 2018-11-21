package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.Publisher;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookQueueMapperTest {
    private BookQueueMapper bookQueueMapper;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Timestamp timestamp;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookQueueMapper = new BookQueueMapper();
    }

    @Test
    void mapRow() throws SQLException {
        when(resultSet.getInt(6)).thenReturn(6);
        when(resultSet.getString(7)).thenReturn("7");
        when(resultSet.getString(8)).thenReturn("8");
        when(resultSet.getString(9)).thenReturn("9");
        when(resultSet.getInt(12)).thenReturn(12);
        when(resultSet.getString(13)).thenReturn("13");
        when(resultSet.getInt(11)).thenReturn(11);
        when(resultSet.getInt(14)).thenReturn(14);
        when(resultSet.getString(15)).thenReturn("15");
        when(resultSet.getString(16)).thenReturn("16");
        when(resultSet.getInt(17)).thenReturn(17);
        when(resultSet.getString(20)).thenReturn("20");
        when(resultSet.getInt(18)).thenReturn(18);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getTimestamp(4)).thenReturn(timestamp);
        when(resultSet.getString(5)).thenReturn("WAIT");
        BookEdition bookEdition = new BookEdition(6, "7", "8", "9", new Publisher(12, "13"), 11);
        User user = new User(14, "15", "16", 17, "20", 18);
        BookQueue bookQueue = new BookQueue(1, bookEdition, user, timestamp, BookQueueStatus.valueOf("WAIT"));
        assertEquals(bookQueue, bookQueueMapper.mapRow(resultSet, 0));
    }
}