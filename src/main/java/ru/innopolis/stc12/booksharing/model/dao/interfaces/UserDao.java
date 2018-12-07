package ru.innopolis.stc12.booksharing.model.dao.interfaces;

import ru.innopolis.stc12.booksharing.model.dao.entity.User;

import java.io.Serializable;

public interface UserDao<T extends Serializable> extends AbstractDao<T> {

    User getUserByLogin(String login);

    User getUserByEmail(String email);
}
