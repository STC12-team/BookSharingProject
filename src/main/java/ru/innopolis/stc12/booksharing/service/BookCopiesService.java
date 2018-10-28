package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookCopiesDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;

@Service
public class BookCopiesService {
    private BookCopiesDaoImpl bookCopiesDaoImpl;

    @Autowired
    public void setBookCopiesDaoImpl(BookCopiesDaoImpl bookCopiesDaoImpl) {
        this.bookCopiesDaoImpl = bookCopiesDaoImpl;
    }

    public boolean addBook(BookCopy book) {
        return bookCopiesDaoImpl.addBookCopies(book);
    }
}
