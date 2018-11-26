package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

import java.io.Serializable;
import java.util.List;

public interface BookCopiesDao {
    BookCopy getBookCopyById(int id);

    Serializable addBookCopy(BookCopy bookCopy);

    void updateBookCopy(BookCopy bookCopy);

    int getBookCopyCountByBookEditionId(int id);

    int getBookCopyCountByBookEditionIdInStatusFree(int id);
}
