package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.BookCopy;

import java.util.List;

public interface BookCopiesDao {
    BookCopy getBookCopiesById(int id);

    List<BookCopy> getAllBookCopies();

    BookCopy getBookCopiesByIsbn(String isbn);

    boolean addBookCopy(BookCopy bookCopy);

    boolean updateBookCopy(BookCopy bookCopy);

    int getBookCopyCountByBookEditionId(int id);

    int getBookCopyCountByBookEditionIdInStatusFree(int id);
}
