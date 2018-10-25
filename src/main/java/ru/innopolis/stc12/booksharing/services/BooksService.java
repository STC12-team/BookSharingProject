package ru.innopolis.stc12.booksharing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.dao.BooksDao;
import ru.innopolis.stc12.booksharing.entitys.Book;

import java.util.List;

@Service
public class BooksService {
    private BooksDao booksDao;

    @Autowired
    public void setBooksDao(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    public Book getByIsbn(Integer isbn) {
        return booksDao.getByIsbn(isbn);
    }

    public List<Book> getByName(String name) {
        return booksDao.getByName(name);
    }

    public List<Book> getByAuthor(String author) {
        return booksDao.getByAuthor(author);
    }
}
