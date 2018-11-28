package ru.innopolis.stc12.booksharing.model.dao;

import java.io.Serializable;

public interface BookCopiesDao<T extends Serializable> extends AbstractDao<T> {
    int getBookCopyCountByBookEditionId(int id);

    int getBookCopyCountByBookEditionIdInStatusFree(int id);
}
