package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;

import java.util.List;

public interface BookEditionsDao {
    BookEdition getBookEditionById(int id);

    List<BookEdition> getAllBookEditions();

    BookEdition getBookEditionByIsbn(String isbn);

    boolean addBookEdition(BookEdition bookEdition);

    List<BookEdition> getBookEditionByTitle(String name);

    List<BookEdition> getBookEditionsByPublisher(String publisher);

    List<BookEdition> getBookEditionsBySearchValue(String searchValue);
}
