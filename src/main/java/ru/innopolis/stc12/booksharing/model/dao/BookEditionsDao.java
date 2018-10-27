package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.BookEdition;

import java.util.List;

public interface BookEditionsDao {

    BookEdition getBookEditionById(int id);

    List<BookEdition> getAllBookEditions();

    BookEdition getBookEditionByIsbn(String isbn);

    void addBookEdition(BookEdition bookEdition);
}
