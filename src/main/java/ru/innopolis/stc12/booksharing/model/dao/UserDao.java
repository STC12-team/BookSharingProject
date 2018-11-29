package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy;
import ru.innopolis.stc12.booksharing.model.dao.entity.User;
import ru.innopolis.stc12.booksharing.model.pojo.UserDetails;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T extends Serializable> extends AbstractDao<T> {

    List<BookCopy> getBookCopyByUser(int userId);

    User getUserByLogin(String login);

    UserDetails getUserDetails();

    boolean updateUserDetails(UserDetails userDetails);

    User addUser(String login, String passwordHash);
}
