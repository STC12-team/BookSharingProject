package ru.innopolis.stc12.booksharing.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookQueueDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;

import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class BookQueueService {
    private Logger logger = Logger.getLogger(BookQueueService.class);

    private BookQueueDao<BookQueue> bookQueueDao;
    private BookEditionsDao<BookEdition> bookEditionsDao;

    @Autowired
    public void setBookEditionsDao(BookEditionsDao<BookEdition> bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
        this.bookEditionsDao.setClazz(BookEdition.class);
    }

    @Autowired
    public void setBookQueueDao(BookQueueDao<BookQueue> bookQueueDao) {
        this.bookQueueDao = bookQueueDao;
        this.bookQueueDao.setClazz(BookQueue.class);
    }

    public List<BookQueue> getBookQueueByBookEditionId(long id) {
        BookEdition bookEdition = bookEditionsDao.findOne(id);
        return bookEdition.getBookQueue();
    }

    public BookQueue updateBookQueue(BookQueue bookQueue) {
        return bookQueueDao.update(bookQueue);
    }

    public int getBookQueueCountByBookEditionId(long id) {
        List<BookQueue> bookQueueList = getBookQueueByBookEditionId(id);
        return bookQueueList.size();
    }
}
