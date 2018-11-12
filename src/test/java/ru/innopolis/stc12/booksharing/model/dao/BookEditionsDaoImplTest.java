package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.Publisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsDaoImplTest {

    @Mock
    BookEditionsDaoImpl bookEditionsDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookEditionsDao = new BookEditionsDaoImpl();
        bookEditionsDao.setJdbcTemplate(jdbcTemplate);
    }


    @Test
    void getBookEditionById() {
        BookEdition bookEdition = new BookEdition();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionById(5));
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(new DataAccessException("this was the reason") {
        });
        assertEquals(null, bookEditionsDao.getBookEditionById(6));

    }

    @Test
    void getAllBookEditions() {
        List<BookEdition> list = new ArrayList<>();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class),
                any(RowMapper.class))).thenReturn(list);
        assertEquals(list, bookEditionsDao.getAllBookEditions());
    }

    @Test
    void getBookEditionByIsbn() {
        BookEdition bookEdition = new BookEdition();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionByIsbn("isbn"));
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(new DataAccessException("this was the reason") {
        });
        assertEquals(null, bookEditionsDao.getBookEditionByIsbn("some searchString"));
    }


    @Test
    void getBookEditionsByPublisher() {
        List<BookEdition> bookEditions = new ArrayList<>();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookEditions);
        assertEquals(bookEditions, bookEditionsDao.getBookEditionsByPublisher("some publisher"));
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(new DataAccessException("this was the reason") {
        });
        assertEquals(bookEditions, bookEditionsDao.getBookEditionsByPublisher("some searchString"));

    }

    @Test
    void addBookEdition() {
        BookEdition bookEdition = new BookEdition(1,
                "Title",
                "Description",
                "ISBN",
                new Publisher(1, "name"),
                2018);
        ArgumentCaptor<String> valueCapture1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> valueCapture2 = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> valueCapture3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCapture4 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> valueCapture5 = ArgumentCaptor.forClass(Integer.class);
        when(jdbcTemplate.update(anyString(),
                valueCapture1.capture(),
                valueCapture2.capture(),
                valueCapture3.capture(),
                valueCapture4.capture(),
                valueCapture5.capture())).thenReturn(5);
        bookEditionsDao.addBookEdition(bookEdition);
        assertEquals(valueCapture1.getValue(), bookEdition.getIsbn());
        assertEquals(valueCapture2.getValue(), bookEdition.getPublisher().getId());
        assertEquals(valueCapture3.getValue(), bookEdition.getTitle());
        assertEquals(valueCapture4.getValue(), bookEdition.getDescription());
        assertEquals(valueCapture5.getValue(), bookEdition.getYearOfPublication());
    }

    @Test
    void getBookEditionsBySearchValue() {
        List<BookEdition> bookEditions = new ArrayList<>();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookEditions);
        assertEquals(bookEditions, bookEditionsDao.getBookEditionsBySearchValue("some searchString"));
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(new DataAccessException("this was the reason") {
        });
        assertEquals(bookEditions, bookEditionsDao.getBookEditionsBySearchValue("some searchString"));

    }
}