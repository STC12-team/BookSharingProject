package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;

import java.util.List;
import java.util.stream.Collectors;

@EnableTransactionManagement
@Service
@Transactional
public class BookCopiesService {
    private BookCopiesDao<BookCopy> bookCopiesDao;
    private BookEditionsDao<BookEdition> bookEditionsDao;
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

    @Autowired
    public void setBookEditionsDao(BookEditionsDao<BookEdition> bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
        this.bookEditionsDao.setClazz(BookEdition.class);
    }

    public void addBook(BookCopy book) {
        bookCopiesDao.save(book);
    }

    public List<BookCopy> getBookCopiesByUser(int userId) {
        User user = userDao.findOne(userId);
        return getBookCopiesByUser(user);
    }

    public List<BookCopy> getBookCopiesByUser(User user) {
        return user.getBookCopies();
    }

    public List<BookCopy> getBookCopiesByEdition(int editionId) {
        BookEdition bookEdition = bookEditionsDao.findOne(editionId);
        return bookEdition.getBookCopies();
    }

    public BookCopy getBookCopyById(Integer id) {
        return bookCopiesDao.findOne(id);
    }

    public BookCopy updateBookCopy(BookCopy bookCopy) {
        return bookCopiesDao.update(bookCopy);
    }

    public int getBookCopyCountByBookEditionId(int id) {
        return getBookCopiesByEdition(id).size();
    }

    public int getBookCopyCountByBookEditionIdInStatusFree(int id) {
        List<BookCopy> bookCopies = getBookCopiesByEdition(id);

        return bookCopies.stream()
                .filter(item -> item.getStatus() == BookCopiesStatus.FREE)
                .collect(Collectors.toList())
                .size();
    }
}
