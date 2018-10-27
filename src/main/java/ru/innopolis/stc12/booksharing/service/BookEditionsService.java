package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.List;

@Service
public class BookEditionsService {
    private BookEditionsDao bookEditionsDao;

    public BookEditionsService(BookEditionsDao bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
    }

    public BookEditionsService() {
    }

    @Autowired
    public void setBookEditionsDao(BookEditionsDao bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
    }

    public List<BookEdition> getAllBookEditions() {
        return bookEditionsDao.getAllBookEditions();
    }

    public void addBookEdition(String title, String description, String isbn) {
        BookEdition bookEdition = new BookEdition(title, description, isbn);
        bookEditionsDao.addBookEdition(bookEdition);
    }
}
