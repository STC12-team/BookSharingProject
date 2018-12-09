package ru.innopolis.stc12.booksharing.service;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.BookQueueDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import ru.innopolis.stc12.booksharing.model.pojo.BookQueueStatus;

import java.sql.Timestamp;
import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class BookQueueService {
    private Logger logger = Logger.getLogger(BookQueueService.class);

    private BookQueueDao<BookQueue> bookQueueDao;
    private BookEditionsDao<BookEdition> bookEditionsDao;
    private BookCopiesDao<BookCopy> bookCopiesDao;

    @Autowired
    public void setBookCopiesDao(BookCopiesDao<BookCopy> bookCopiesDao) {
        this.bookCopiesDao = bookCopiesDao;
        this.bookCopiesDao.setClazz(BookCopy.class);
    }

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

    public List<BookQueue> getBookQueueByBookEditionId(int id) {
        BookEdition bookEdition = bookEditionsDao.findOne(id);
        //TODO hibernate init
        Hibernate.initialize(bookEdition.getBookQueue());
        return bookEdition.getBookQueue();
    }

    public BookQueue updateBookQueue(BookQueue bookQueue) {
        return bookQueueDao.update(bookQueue);
    }

    public int getBookQueueCountByBookEditionId(int id) {
        List<BookQueue> bookQueueList = getBookQueueByBookEditionId(id);
        return bookQueueList.size();
    }

    @Transactional
    public void addUserToBookQueue(User user, BookEdition bookEdition) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BookQueue bookQueue = new BookQueue(bookEdition, user, timestamp, BookQueueStatus.WAIT);
        List<BookCopy> listOfBookCopies = bookCopiesDao.getBookCopiesOfBookEdition(bookEdition);
        for (BookCopy bookCopy : listOfBookCopies) {
            if (bookCopy.getStatus().equals(BookCopiesStatus.FREE)) {
                bookQueue.setStatus(BookQueueStatus.GETTING);
                logger.info(user + " is in " + BookQueueStatus.GETTING + " status");
                break;
            }
        }
        bookQueueDao.save(bookQueue);
    }

    @Transactional
    public void deleteUserFromQueue(User user, BookEdition bookEdition) {
        BookQueue bookQueue = bookQueueDao.getUserBookQueueByBookEdition(user, bookEdition);
        bookQueueDao.delete(bookQueue);
    }

    public BookQueue getById(int id) {
        return bookQueueDao.findOne(id);
    }
}
