package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesDaoImplTest {
    private BookCopiesDaoImpl bookCopiesDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookCopiesDao = new BookCopiesDaoImpl();
        bookCopiesDao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void getBookCopiesById() {
        List<BookCopy> list = new ArrayList<>();
        list.add(new BookCopy());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(list);
        assertEquals(list.get(0), bookCopiesDao.getBookCopiesById(0));
    }

    @Test
    void getAllBookCopies() {
        List<BookCopy> list = new ArrayList<>();
        list.add(new BookCopy());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(list);
        assertEquals(list, bookCopiesDao.getAllBookCopies());
    }

    @Test
    void getBookCopiesByIsbn() {
        List<BookCopy> list = new ArrayList<>();
        list.add(new BookCopy());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(list);
        assertEquals(list.get(0), bookCopiesDao.getBookCopiesByIsbn("isbn"));
    }

    @Test
    void addBookCopiesWhenTrue() {
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(1);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookCopy.getUser()).thenReturn(user);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((long) 1);
        when(bookCopy.getBookEdition().getId()).thenReturn(1);
        when(bookCopy.getUser().getId()).thenReturn((long) 1);
        assertEquals(true, bookCopiesDao.addBookCopies(bookCopy));
    }

    @Test
    void addBookCopiesWhenFalse() {
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(0);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookCopy.getUser()).thenReturn(user);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((long) 1);
        when(bookCopy.getBookEdition().getId()).thenReturn(1);
        when(bookCopy.getUser().getId()).thenReturn((long) 1);
        assertEquals(false, bookCopiesDao.addBookCopies(bookCopy));
    }
}