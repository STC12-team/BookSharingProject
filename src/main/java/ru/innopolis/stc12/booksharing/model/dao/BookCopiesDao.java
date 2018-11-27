package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;

public interface BookCopiesDao extends AbstractDao {

    int getBookCopyCountByBookEditionId(int id);

    int getBookCopyCountByBookEditionIdInStatusFree(int id);
}
