package ru.innopolis.stc12.booksharing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.booksharing.model.dao.BookQueueDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookQueueServiceTest {
    private BookQueueService bookQueueService;
    @Mock
    private BookQueueDao bookQueueDao;
    @Mock
    private List<BookQueue> bookQueueList;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookQueueService = new BookQueueService();
        bookQueueService.setBookQueueDao(bookQueueDao);
    }

    @Test
    void getBookQueueByBookEditionId() {
        when(bookQueueDao.getBookQueueByBookEditionId(anyInt())).thenReturn(bookQueueList);
        assertEquals(bookQueueList, bookQueueService.getBookQueueByBookEditionId(anyInt()));
    }

    @Test
    void updateBookQueue() {
        when(bookQueueDao.updateBookQueue(any())).thenReturn(true);
        assertEquals(true, bookQueueService.updateBookQueue(any()));
    }
}