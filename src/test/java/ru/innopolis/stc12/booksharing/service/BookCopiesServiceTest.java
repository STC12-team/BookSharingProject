package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

 //   private BookCopiesDao<BookCopy> bookCopiesDao;
 //   private BookEditionsDao<BookEdition> bookEditionsDao;
 //   private UserDao<User> userDao;

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


}