package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookQueueDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueue;

import java.util.List;

@Service
public class BookQueueService {
    private BookQueueDao bookQueueDao;

    @Autowired
    public void setBookQueueDao(BookQueueDao bookQueueDao) {
        this.bookQueueDao = bookQueueDao;
    }

    public List<BookQueue> getBookQueueByBookEditionId(Integer id) {
        return bookQueueDao.getBookQueueByBookEditionId(id);
    }

    public boolean updateBookQueue(BookQueue bookQueue) {
        return bookQueueDao.updateBookQueue(bookQueue);
    }
}
