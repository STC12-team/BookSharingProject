package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;

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
    }

    @Test
    void getAllBookEditions() {
        List<BookEdition> list = new ArrayList<>();
        when(bookEditionsDao.findAll()).thenReturn(list);
        assertEquals(list, bookEditionsService.getAllBookEditions());
    }

    @Test
    void addBookEdition() {
        BookEdition bookEdition = new BookEdition();
        bookEditionsService.addBookEdition(bookEdition);
        verify(bookEditionsDao, times(1)).save(bookEdition);
    }

    @Test
    void getBookEditionsBySearchValue() {
        List<BookEdition> expected = new ArrayList<>();
        List<BookEdition> test1 = new ArrayList<>();
        List<BookEdition> test2 = new ArrayList<>();
        BookEdition bookEdition1 = new BookEdition("My1", null, null, null, null);
        BookEdition bookEdition2 = new BookEdition("Search1", null, null, null, null);
        test1.add(bookEdition1);
        test1.add(bookEdition2);
        BookEdition bookEdition3 = new BookEdition("My2", null, null, null, null);
        BookEdition bookEdition4 = new BookEdition("Search2", null, null, null, null);
        test2.add(bookEdition3);
        test2.add(bookEdition4);
        expected.add(bookEdition1);
        expected.add(bookEdition2);
        expected.add(bookEdition3);
        expected.add(bookEdition4);
        when(bookEditionsDao.getBookEditionsBySearchValue("my")).thenReturn(test1);
        when(bookEditionsDao.getBookEditionsBySearchValue("search")).thenReturn(test2);
        assertEquals(expected, bookEditionsService.getBookEditionsBySearchValue("My sEarch"));
    }

    @Test
    void getBookCopiesByBookEditionIdInStatusFree() {
        List<BookCopy> bookCopyListAll = new ArrayList<>();
        List<BookCopy> bookCopyListFree = new ArrayList<>();
        bookCopyListAll.add(new BookCopy(null, null, BookCopiesStatus.BUSY));
        bookCopyListFree.add(new BookCopy(null, null, BookCopiesStatus.FREE));
        bookCopyListFree.add(new BookCopy(null, null, BookCopiesStatus.FREE));
        bookCopyListAll.addAll(bookCopyListFree);
        bookCopyListAll.add(new BookCopy(null, null, BookCopiesStatus.BUSY));
        when(bookEditionsDao.getBookCopiesByBookEditionId(1)).thenReturn(bookCopyListAll);
        List<BookCopy> bookCopyListExp = bookEditionsService.getBookCopiesByBookEditionIdInStatusFree(1);
        assertEquals(bookCopyListExp, bookCopyListFree);
    }

    @Test
    void getById() {
        BookEdition bookEdition = new BookEdition();
        when(bookEditionsDao.findOne(12)).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsService.getById(12));
    }

    @Test
    void getBookEditionByIsbn() {
        BookEdition bookEdition = new BookEdition();
        when(bookEditionsDao.getBookEditionByIsbn("isbn")).thenReturn(bookEdition);
        assertEquals(bookEdition, bookEditionsService.getBookEditionByIsbn("isbn"));
    }

    @Test
    void getBookEditionsByPublisher() {
        List<BookEdition> bookEditions = new ArrayList<>();
        when(bookEditionsDao.getBookEditionsByPublisher("TestPublisher")).thenReturn(bookEditions);
        assertEquals(bookEditions, bookEditionsService.getBookEditionsByPublisher("TestPublisher"));
    }

    @Test
    void getBookEditionByTitle() {
        List<BookEdition> bookEditions = new ArrayList<>();
        when(bookEditionsDao.getBookEditionByTitle("TestTitle")).thenReturn(bookEditions);
        assertEquals(bookEditions, bookEditionsService.getBookEditionByTitle("TestTitle"));
    }

}