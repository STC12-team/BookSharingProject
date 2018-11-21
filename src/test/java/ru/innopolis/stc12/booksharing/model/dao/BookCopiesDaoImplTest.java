package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopyEntity;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;
import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesDaoImplTest {

    @InjectMocks
    private BookCopiesDaoImpl bookCopiesDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private BookEdition bookEdition;
    @Mock
    private User user;
    @Mock
    private Session session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBookCopiesById() {
        BookCopy bookCopy = new BookCopy();
        BookCopyEntity bookCopyEntity = new BookCopyEntity();
        when(session.get(BookCopyEntity.class, 1)).thenReturn(bookCopyEntity);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookCopy);
        assertEquals(bookCopy, bookCopiesDao.getBookCopiesById(0));
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
        BookCopy bookCopy = new BookCopy();
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(bookCopy);
        assertEquals(bookCopy, bookCopiesDao.getBookCopiesByIsbn("isbn"));
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
        when(bookCopy.getStatus()).thenReturn(BookCopiesStatus.FREE);
        assertEquals(true, bookCopiesDao.addBookCopy(bookCopy));
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
        when(bookCopy.getStatus()).thenReturn(BookCopiesStatus.FREE);
        assertEquals(false, bookCopiesDao.addBookCopy(bookCopy));
    }

    @Test
    void getBookCopyCountByBookEditionIdInStatusFree() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
        assertEquals(1, bookCopiesDao.getBookCopyCountByBookEditionIdInStatusFree(1));
    }

    @Test
    void getBookCopyCountByBookEditionId() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
        assertEquals(1, bookCopiesDao.getBookCopyCountByBookEditionId(1));
    }
}
