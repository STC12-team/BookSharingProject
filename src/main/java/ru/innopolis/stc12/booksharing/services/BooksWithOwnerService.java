package ru.innopolis.stc12.booksharing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc12.booksharing.dao.BooksWithOwnerDao;
import ru.innopolis.stc12.booksharing.entitys.BookWithOwner;

@Service
public class BooksWithOwnerService {
    private BooksWithOwnerDao booksWithOwnerDao;

    @Autowired
    public void setBooksWithOwnerDao(BooksWithOwnerDao booksWithOwnerDao) {
        this.booksWithOwnerDao = booksWithOwnerDao;
    }

    public boolean addBook(BookWithOwner book) {
        return booksWithOwnerDao.create(book);
    }
}
