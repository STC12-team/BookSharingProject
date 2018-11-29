package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class BookCopiesService {
    private BookCopiesDao<BookCopy> bookCopiesDao;
    private UserDao<User> userDao;

    @Autowired
    public void setBookCopiesDao(BookCopiesDao<BookCopy> bookCopiesDao) {
        this.bookCopiesDao = bookCopiesDao;
        this.bookCopiesDao.setClazz(BookCopy.class);
    }

    @Autowired
    public void setUserDao(UserDao<User> userDao) {
        this.userDao = userDao;
        this.userDao.setClazz(User.class);
    }

    public void addBook(BookCopy book) {
        bookCopiesDao.save(book);
    }

    public List<BookCopy> getBookCopyByUser(int userId) {
        User user = userDao.findOne(userId);
        return user.getBookCopies();
    }

    public BookCopy getBookCopyById(Integer id) {
        return bookCopiesDao.findOne(id);
    }

    public BookCopy updateBookCopy(BookCopy bookCopy) {
        return bookCopiesDao.update(bookCopy);
    }

    public int getBookCopyCountByBookEditionId(int id) {
        return bookCopiesDao.getBookCopyCountByBookEditionId(id);
    }

    public int getBookCopyCountByBookEditionIdInStatusFree(int id) {
        return bookCopiesDao.getBookCopyCountByBookEditionIdInStatusFree(id);
    }
}
