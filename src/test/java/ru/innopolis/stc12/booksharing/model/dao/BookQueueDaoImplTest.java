package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookQueueDaoImplTest {
    private BookQueueDaoImpl bookQueueDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private List<BookQueue> bookQueueList;
    @Mock
    private BookQueue bookQueue;
    @Mock
    private Timestamp timestamp;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookQueueDao = new BookQueueDaoImpl();
        bookQueueDao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void getBookQueueByBookEditionId() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookQueueList);
        assertEquals(bookQueueList, bookQueueDao.getBookQueueByBookEditionId(1));
    }

    @Test
    void updateBookQueue() {
        //TODO переделать
        when(bookQueue.getBookEdition()).thenReturn(bookEdition);
        when(bookQueue.getUser()).thenReturn(user);
        when(bookQueue.getStatus()).thenReturn(BookQueueStatus.WAIT);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((long) 1);
        when(bookQueue.getAddedAt()).thenReturn(timestamp);
        when(bookQueue.getId()).thenReturn(1);
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(), anyString(), anyInt())).thenReturn(0);
        assertEquals(false, bookQueueDao.updateBookQueue(bookQueue));
    }
}