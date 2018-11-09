package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.List;

@Service
public class BookEditionsService {
    private BookEditionsDao bookEditionsDao;

    @Autowired
    public void setBookEditionsDao(BookEditionsDao bookEditionsDao) {
        this.bookEditionsDao = bookEditionsDao;
    }

    public BookEdition getByIsbn(String isbn) {
        return bookEditionsDao.getBookEditionByIsbn(isbn);
    }

    public List<BookEdition> getByName(String name) {
        return bookEditionsDao.getBookEditionByTitle(name);
    }

    public List<BookEdition> getAllBookEditions() {
        return bookEditionsDao.getAllBookEditions();
    }

    public boolean addBookEdition(BookEdition bookEdition) {
        return bookEditionsDao.addBookEdition(bookEdition);
    }

    public BookEdition getById(int id) {
        return bookEditionsDao.getBookEditionById(id);
    }
}
