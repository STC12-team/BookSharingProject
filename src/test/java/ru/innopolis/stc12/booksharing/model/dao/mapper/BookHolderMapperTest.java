package ru.innopolis.stc12.booksharing.model.dao.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookHolderMapperTest {
    private BookHolderMapper bookHolderMapper;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Timestamp timestamp;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookHolderMapper = new BookHolderMapper();
    }

    @Test
    void mapRow() throws SQLException {
        when(resultSet.getInt(11)).thenReturn(11);
        when(resultSet.getString(12)).thenReturn("12");
        when(resultSet.getString(13)).thenReturn("13");
        when(resultSet.getString(14)).thenReturn("14");
        when(resultSet.getInt(17)).thenReturn(17);
        when(resultSet.getString(18)).thenReturn("18");
        when(resultSet.getInt(16)).thenReturn(16);
        when(resultSet.getInt(19)).thenReturn(19);
        when(resultSet.getString(20)).thenReturn("20");
        when(resultSet.getString(21)).thenReturn("21");
        when(resultSet.getInt(24)).thenReturn(24);
        when(resultSet.getString(25)).thenReturn("25");
        when(resultSet.getInt(23)).thenReturn(23);
        when(resultSet.getInt(7)).thenReturn(7);
        when(resultSet.getString(10)).thenReturn("FREE");
        when(resultSet.getInt(26)).thenReturn(26);
        when(resultSet.getString(27)).thenReturn("27");
        when(resultSet.getString(28)).thenReturn("28");
        when(resultSet.getInt(31)).thenReturn(31);
        when(resultSet.getString(32)).thenReturn("32");
        when(resultSet.getInt(30)).thenReturn(30);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getTimestamp(4)).thenReturn(timestamp);
        when(resultSet.getTimestamp(5)).thenReturn(timestamp);
        when(resultSet.getInt(6)).thenReturn(6);
        BookEdition bookEdition = new BookEdition(11, "12", "13", "14", new Publisher(17, "18"), 16);
        User owner = new User(19, "20", "21", 24, "25", 23);
        BookCopy bookCopy = new BookCopy(7, bookEdition, owner, BookCopiesStatus.valueOf("FREE"));
        User user = new User(26, "27", "28", 31, "32", 30);
        BookHolder bookHolder = new BookHolder(1, bookCopy, user, timestamp, timestamp, 6);
        assertEquals(bookHolder, bookHolderMapper.mapRow(resultSet, 0));
    }
}