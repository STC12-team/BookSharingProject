package ru.innopolis.stc12.booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.model.dao.BookEditionsDaoImpl;
import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.List;

@Service
public class BookEditionsService {
    private BookEditionsDaoImpl bookEditionsDaoImpl;

    @Autowired
    public void setBookEditionsDaoImpl(BookEditionsDaoImpl bookEditionsDaoImpl) {
        this.bookEditionsDaoImpl = bookEditionsDaoImpl;
    }

    public BookEdition getByIsbn(String isbn) {
        return bookEditionsDaoImpl.getBookEditionByIsbn(isbn);
    }

    public List<BookEdition> getByName(String name) {
        return bookEditionsDaoImpl.getBookEditionByTitle(name);
    }
}
