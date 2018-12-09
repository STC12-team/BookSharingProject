package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder;
import ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T extends Serializable> extends AbstractDao<T> {

    User getUserByLogin(String login);

    User getUserByEmail(String email);

    List<BookHolder> getBookHoldersByUserLogin(String login);

    List<BookCopy> getBookCopiesByUserLogin(String login);

    List<BookQueue> getBookQueueByUserLogin(String login);
}
