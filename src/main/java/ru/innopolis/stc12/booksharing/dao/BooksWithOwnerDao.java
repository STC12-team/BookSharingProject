package ru.innopolis.stc12.booksharing.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.entitys.BookWithOwner;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BooksWithOwnerDao {
    private List<BookWithOwner> bookWithOwnerList = new ArrayList<>();

    public boolean create(BookWithOwner book) {
        return bookWithOwnerList.add(book);
    }
}
