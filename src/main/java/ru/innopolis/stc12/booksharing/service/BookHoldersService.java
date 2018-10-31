package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookHoldersDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.BookHolder;

import java.util.List;

@Service
public class BookHoldersService {
    private BookHoldersDaoImpl bookHoldersDao;

    @Autowired
    public void setBookHoldersDao(BookHoldersDaoImpl bookHoldersDao) {
        this.bookHoldersDao = bookHoldersDao;
    }

    List<BookHolder> getBookHoldersByUserLogin(String login) {
        return bookHoldersDao.getBookHoldersByUserLogin(login);
    }
}
