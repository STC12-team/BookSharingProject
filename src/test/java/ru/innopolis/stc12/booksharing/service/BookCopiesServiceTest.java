package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesServiceTest {
    @InjectMocks
    private BookCopiesService bookCopiesService;
    @Mock
    private BookCopiesDao<BookCopy> bookCopiesDao;
    @Mock
    private BookEditionsDao bookEditionsDao;
    @Mock
    private UserDao userDao;
    @Mock
    private BookCopy bookCopy;
    @Mock
    private User user;
    @Mock
    private BookEdition bookEdition;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void addBook() {
        bookCopiesService.addBook(bookCopy);
        verify(bookCopiesDao, times(1)).save(bookCopy);
    }

    @Test
    void setBookCopiesDao() {
        verify(bookCopiesDao, times(1)).setClazz(BookCopy.class);
    }

    @Test
    void setUserDao() {
        verify(userDao, times(1)).setClazz(User.class);
    }

    @Test
    void setBookEditionsDao() {
        verify(bookEditionsDao, times(1)).setClazz(BookEdition.class);
    }

    @Test
    void getBookCopiesByUser() {
        List<BookCopy> bookCopies = new ArrayList<>();
        when(user.getBookCopies()).thenReturn(bookCopies);
        assertEquals(bookCopies, bookCopiesService.getBookCopiesByUser(user));
    }

    @Test
    void getBookCopiesByEdition() {
        when(bookEditionsDao.findOne(1)).thenReturn(bookEdition);
        List<BookCopy> bookCopies = new ArrayList<>();
        when(bookEdition.getBookCopies()).thenReturn(bookCopies);
        assertEquals(bookCopies, bookCopiesService.getBookCopiesByEdition(1));
    }

    @Test
    void getBookCopyById() {
        when(bookCopiesDao.findOne(2)).thenReturn(bookCopy);
        assertEquals(bookCopy, bookCopiesService.getBookCopyById(2));
    }

    @Test
    void updateBookCopy() {
        BookCopy bookCopy2 = new BookCopy();
        when(bookCopiesDao.update(bookCopy)).thenReturn(bookCopy2);
        assertEquals(bookCopy2, bookCopiesService.updateBookCopy(bookCopy));
    }

}