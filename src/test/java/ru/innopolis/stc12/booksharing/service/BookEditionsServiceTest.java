package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookEditionsServiceTest {
    @InjectMocks
    private BookEditionsService bookEditionsService;

    @Mock
    private BookEditionsDao bookEditionsDao;

    @BeforeEach
    void setUp() {
        initMocks(this);

        bookEditionsService = new BookEditionsService();
        bookEditionsDao = mock(BookEditionsDao.class);
    }

    @Test
    void getAllBookEditions() {
        List<BookEdition> list = new ArrayList<>();
        when(bookEditionsDao.getAllBookEditions()).thenReturn(list);
        bookEditionsService.setBookEditionsDao(bookEditionsDao);
        assertEquals(list, bookEditionsService.getAllBookEditions());
    }

    @Test
    void addBookEdition() {
        BookEdition bookEdition = new BookEdition("title", "desc", "isbn");
        ArgumentCaptor<BookEdition> valueCapture = ArgumentCaptor.forClass(BookEdition.class);
        doNothing().when(bookEditionsDao).addBookEdition(valueCapture.capture());
        bookEditionsService.setBookEditionsDao(bookEditionsDao);
        bookEditionsService.addBookEdition(bookEdition.getTitle(), bookEdition.getDescription(), bookEdition.getIsbn());
        assertEquals(bookEdition, valueCapture.getValue());
    }
}