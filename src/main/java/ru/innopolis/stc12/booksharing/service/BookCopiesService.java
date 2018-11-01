package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDao;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;

import java.util.List;

@Service
public class BookCopiesService {
    private BookCopiesDao bookCopiesDao;

    @Autowired
    public void setBookCopiesDao(BookCopiesDao bookCopiesDao) {
        this.bookCopiesDao = bookCopiesDao;
    }

    public boolean addBook(BookCopy book) {
        return bookCopiesDao.addBookCopies(book);
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopiesDao.getAllBookCopies();
    }
}
