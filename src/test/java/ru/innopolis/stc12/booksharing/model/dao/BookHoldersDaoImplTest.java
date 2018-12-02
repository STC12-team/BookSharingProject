package ru.innopolis.stc12.booksharing.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

class BookHoldersDaoImplTest {
    private BookHoldersDaoImpl bookHoldersDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private List<BookHolder> bookHolderList;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookHoldersDao = new BookHoldersDaoImpl();
//        bookHoldersDao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void getBookHoldersByUserLogin() {
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookHolderList);
//        assertEquals(bookHolderList, bookHoldersDao.getBookHoldersByUserLogin("login"));
    }
}