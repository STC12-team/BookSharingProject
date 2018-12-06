package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;

import java.io.Serializable;
import java.util.List;

public interface BookCopiesDao<T extends Serializable> extends AbstractDao<T> {
    List<BookCopy> getBookCopiesOfBookEdition(BookEdition bookEdition);
}
