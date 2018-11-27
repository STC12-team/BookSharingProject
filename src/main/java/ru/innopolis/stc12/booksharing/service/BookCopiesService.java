package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

import java.io.Serializable;
import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class BookCopiesService {
    private BookCopiesDao bookCopiesDao;
    private UserDao userDao;

    @Autowired
    public void setBookCopiesDao(BookCopiesDao bookCopiesDao) {
        this.bookCopiesDao = bookCopiesDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addBook(BookCopy book) {
        bookCopiesDao.save(book);
    }

    public List<BookCopy> getBookCopyByUser() {
       return userDao.getAllBookCopy();
    }

    public Serializable getBookCopyById(Integer id) {
        return bookCopiesDao.findOne(id);
    }

    public Serializable updateBookCopy(BookCopy bookCopy) {
        return bookCopiesDao.update(bookCopy);
    }

    public int getBookCopyCountByBookEditionId(int id) {
        return bookCopiesDao.getBookCopyCountByBookEditionId(id);
    }

    public int getBookCopyCountByBookEditionIdInStatusFree(int id) {
        return bookCopiesDao.getBookCopyCountByBookEditionIdInStatusFree(id);
    }
}
