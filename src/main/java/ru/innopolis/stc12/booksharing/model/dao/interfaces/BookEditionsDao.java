package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;

import java.io.Serializable;
import java.util.List;

public interface BookEditionsDao<T extends Serializable> extends AbstractDao<T> {

    BookEdition getBookEditionByIsbn(String isbn);

    List<BookEdition> getBookEditionByTitle(String name);

    List<BookEdition> getBookEditionsByPublisher(String publisher);

    List<BookEdition> getBookEditionsBySearchValue(String searchValue);

    List<BookCopy> getBookCopiesByBookEditionId(int id);
}
