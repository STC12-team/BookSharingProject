package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import java.io.Serializable;

public interface BookQueueDao<T extends Serializable> extends AbstractDao<T> {
    BookQueue getUserBookQueueByBookEdition(User user, BookEdition bookEdition);
}
