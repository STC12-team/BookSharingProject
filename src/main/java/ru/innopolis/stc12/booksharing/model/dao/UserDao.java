package ru.innopolis.stc12.booksharing.model.dao;

import ru.innopolis.stc12.booksharing.model.pojo.User;

import java.util.List;

public interface UserDao {
    User getUserByLogin(String login);
    List<User> getAllUsers();
}
