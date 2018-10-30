package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;

import java.util.List;

public interface BookCopiesDao {
    BookCopy getBookCopiesById(int id);

    List<BookCopy> getAllBookCopies();

    BookCopy getBookCopiesByIsbn(String isbn);

    boolean addBookCopies(BookCopy bookCopy);
}
