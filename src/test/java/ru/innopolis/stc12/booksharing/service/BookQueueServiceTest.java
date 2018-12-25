package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookQueueDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookQueueServiceTest {
    @InjectMocks
    private BookQueueService bookQueueService;
    @Mock
    private BookQueueDao bookQueueDao;
    @Mock
    private BookCopiesDao bookCopiesDao;
    @Mock
    private List<BookQueue> bookQueueList;
    @Mock
    private BookEditionsDao bookEditionsDao;
    @Mock
    private BookEdition bookEdition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookQueueService.setBookQueueDao(bookQueueDao);
        bookQueueService.setBookCopiesDao(bookCopiesDao);
        bookQueueService.setBookEditionsDao(bookEditionsDao);
    }

    @Test
    void getBookQueueByBookEditionId() {
        when(bookEditionsDao.findOne(anyInt())).thenReturn(bookEdition);
        assertEquals(bookEdition.getBookQueue(), bookQueueService.getBookQueueByBookEditionId(anyInt()));
    }

    @Test
    void updateBookQueue() {
        BookQueue bookQueue = new BookQueue();
        when(bookQueueDao.update(any())).thenReturn(bookQueue);
        assertEquals(bookQueue, bookQueueService.updateBookQueue(any()));
    }

    @Test
    void addUserToBookQueueInWaitStatus() {
        BookEdition bookEdition = new BookEdition();
        BookCopy first = new BookCopy(bookEdition, new User(), BookCopiesStatus.BUSY);
        BookCopy second = new BookCopy(bookEdition, new User(), BookCopiesStatus.BUSY);
        List<BookCopy> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        when(bookCopiesDao.getBookCopiesOfBookEdition(any(BookEdition.class))).thenReturn(list);
        ArgumentCaptor<BookQueue> valueCapture = ArgumentCaptor.forClass(BookQueue.class);
        doNothing().when(bookQueueDao).save(valueCapture.capture());
        bookQueueService.addUserToBookQueue(new User(), new BookEdition());
        assertEquals(BookQueueStatus.WAIT, valueCapture.getValue().getStatus());
    }

    @Test
    void addUserToBookQueueInGettingStatus() {
        BookEdition bookEdition = new BookEdition();
        BookCopy first = new BookCopy(bookEdition, new User(), BookCopiesStatus.BUSY);
        BookCopy second = new BookCopy(bookEdition, new User(), BookCopiesStatus.FREE);
        List<BookCopy> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        when(bookCopiesDao.getBookCopiesOfBookEdition(any(BookEdition.class))).thenReturn(list);
        ArgumentCaptor<BookQueue> valueCapture = ArgumentCaptor.forClass(BookQueue.class);
        doNothing().when(bookQueueDao).save(valueCapture.capture());
        bookQueueService.addUserToBookQueue(new User(), new BookEdition());
        assertEquals(BookQueueStatus.GETTING, valueCapture.getValue().getStatus());
    }

    @Test
    void deleteUserFromQueue() {
        BookQueue bookQueue = new BookQueue();
        when(bookQueueDao.getUserBookQueueByBookEdition(any(User.class), any(BookEdition.class))).thenReturn(bookQueue);
        ArgumentCaptor<BookQueue> valueCapture = ArgumentCaptor.forClass(BookQueue.class);
        doNothing().when(bookQueueDao).delete(valueCapture.capture());
        bookQueueService.deleteUserFromQueue(new User(), new BookEdition());
        assertEquals(bookQueue, valueCapture.getValue());
    }

    @Test
    void getById() {
        BookQueue bookQueue = new BookQueue();
        when(bookQueueDao.findOne(1)).thenReturn(bookQueue);
        assertEquals(bookQueue, bookQueueService.getById(1));
    }
}