package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc12.booksharing.model.dao.BookHoldersDao;
import ru.innopolis.stc12.booksharing.model.dao.UserDao;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import java.util.List;

@EnableTransactionManagement
@Service
@Transactional
public class BookHoldersService {
    private BookHoldersDao<BookHolder> bookHoldersDao;
    private UserDao<User> userDao;

    @Autowired
    public void setBookHoldersDao(BookHoldersDao<BookHolder> bookHoldersDao) {
        this.bookHoldersDao = bookHoldersDao;
        this.bookHoldersDao.setClazz(BookHolder.class);
    }

    @Autowired
    public void setUserDao(UserDao<User> userDao) {
        this.userDao = userDao;
        this.userDao.setClazz(User.class);
    }

    public List<BookHolder> getBookHoldersByUserLogin(String login) {
        User user = userDao.getUserByLogin(login);
        return user.getBookHolders();
    }
}
