package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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
        jdbcTemplate = mock(JdbcTemplate.class);
        bookEditionsDao.setJdbcTemplate(jdbcTemplate);
    }


    @Test
    void getBookEditionById() {
        BookEdition bookEdition = new BookEdition("title", "desc", "isbn");
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class),
                any(RowMapper.class))).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionById(5));

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
        BookEdition bookEdition = new BookEdition("title", "desc", "isbn");
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class),
                any(RowMapper.class))).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsDao.getBookEditionByIsbn("22325235"));
    }

    @Test
    void addBookEdition() {
        BookEdition bookEdition = new BookEdition("title", "desc", "isbn");
        ArgumentCaptor<String> valueCapture1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCapture2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCapture3 = ArgumentCaptor.forClass(String.class);
        when(jdbcTemplate.update(anyString(), valueCapture1.capture(), valueCapture2.capture(), valueCapture3.capture())).thenReturn(3);
        bookEditionsDao.addBookEdition(bookEdition);
        assertEquals(valueCapture1.getValue(), bookEdition.getTitle());
        assertEquals(valueCapture2.getValue(), bookEdition.getDescription());
        assertEquals(valueCapture3.getValue(), bookEdition.getIsbn());
    }
}