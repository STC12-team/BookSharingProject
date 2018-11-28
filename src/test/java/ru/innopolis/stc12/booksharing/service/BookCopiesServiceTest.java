package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookCopiesServiceTest {
    private BookCopiesService bookCopiesService;
    @Mock
    private BookCopiesDao bookCopiesDao;


    @BeforeEach
    void setUp() {
        initMocks(this);
        bookCopiesService = new BookCopiesService();
        bookCopiesService.setBookCopiesDao(bookCopiesDao);
    }

    @Test
    void addBook() {
//todo       when(bookCopiesDao.addBookCopy(any())).thenReturn(true);
//todo      assertEquals(true, bookCopiesService.addBook(any()));
    }
}