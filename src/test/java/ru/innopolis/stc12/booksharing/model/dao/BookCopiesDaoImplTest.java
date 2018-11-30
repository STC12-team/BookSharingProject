package ru.innopolis.stc12.booksharing.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.implementation.BookCopiesDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void findOne() {
        BookCopy bookCopy = new BookCopy();
        when(session.get(BookCopy.class, 1)).thenReturn(bookCopy);
//todo        assertEquals(bookCopy, bookCopiesDao.findOne(1));
    }

    @Test
    void addBookCopiesWhenFalse() {
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(0);
        when(bookCopy.getBookEdition()).thenReturn(bookEdition);
        when(bookCopy.getOwner()).thenReturn(user);
        when(bookEdition.getId()).thenReturn(1);
        when(user.getId()).thenReturn((long) 1);
        when(bookCopy.getBookEdition().getId()).thenReturn(1);
        when(bookCopy.getOwner().getId()).thenReturn((long) 1);
        when(bookCopy.getStatus()).thenReturn(BookCopiesStatus.FREE);
//todo        bookCopiesDao.save(bookCopy);
//todo        verify(bookCopy, times()).
    }

    @Test
    void getBookCopyCountByBookEditionIdInStatusFree() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
        assertEquals(1, bookCopiesDao.getBookCopyCountByBookEditionIdInStatusFree(1));
    }

    @Test
    void getBookCopyCountByBookEditionId() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
//        assertEquals(1, bookCopiesDao.getBookCopyCountByBookEditionId(1));
    }
}
