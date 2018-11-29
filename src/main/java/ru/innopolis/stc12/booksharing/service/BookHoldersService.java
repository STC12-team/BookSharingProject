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
    private UserDao<User> user;

    @Autowired
    public void setBookHoldersDao(BookHoldersDao<BookHolder> bookHoldersDao) {
        this.bookHoldersDao = bookHoldersDao;
        this.bookHoldersDao.setClazz(BookHolder.class);
    }

    public List<BookHolder> getBookHoldersByUserLogin(String login) {
        User user = null;


        return null;
    }
}
